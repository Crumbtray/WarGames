#ifndef _SOCKET_H_
#define _SOCKET_H_

#include <time.h>

#ifdef WIN32
#define FD_SETSIZE 1000
	#include <winsock2.h>
	typedef long in_addr_t;
#else
	#include <sys/types.h>
	#include <sys/socket.h>
	#include <netinet/in.h>
	#include <errno.h>
#endif

#ifdef WIN32

// windows portability layer 
typedef int socklen_t;

#define sErrno WSAGetLastError()
#define S_ENOTSOCK WSAENOTSOCK
#define S_EWOULDBLOCK WSAEWOULDBLOCK
#define S_EINTR WSAEINTR
#define S_ECONNABORTED WSAECONNABORTED


#define SHUT_RD   SD_RECEIVE
#define SHUT_WR   SD_SEND
#define SHUT_RDWR SD_BOTH

// global array of sockets (emulating linux)
// fd is the position in the array
extern SOCKET sock_arr[FD_SETSIZE];
extern int sock_arr_len;

/// Returns the socket associated with the target fd.
///
/// @param fd Target fd.
/// @return Socket
#define fd2sock(fd) sock_arr[fd]

/// Returns the first fd associated with the socket.
/// Returns -1 if the socket is not found.
///
/// @param s Socket
/// @return Fd or -1
int sock2fd(SOCKET s);


/// Inserts the socket into the global array of sockets.
/// Returns a new fd associated with the socket.
/// If there are too many sockets it closes the socket, sets an error and 
//  returns -1 instead.
/// Since fd 0 is reserved, it returns values in the range [1,FD_SETSIZE[.
///
/// @param s Socket
/// @return New fd or -1
int sock2newfd(SOCKET s);

int sAccept(int fd, struct sockaddr* addr, int* addrlen);

int sClose(int fd);
int sSocket(int af, int type, int protocol);

#define sBind(fd,name,namelen) bind(fd2sock(fd),name,namelen)
#define sListen(fd,backlog) listen(fd2sock(fd),backlog)
#define sIoctl(fd,cmd,argp) ioctlsocket(fd2sock(fd),cmd,argp)
#define sConnect(fd,name,namelen) connect(fd2sock(fd),name,namelen)
#define sRecv(fd,buf,len,flags) recv(fd2sock(fd),buf,len,flags)
#define sRecvfrom(fd,buf,len,flags,from,addrlen) recvfrom(fd2sock(fd),buf,len,flags,from,addrlen)
#define sSelect select
#define sSend(fd,buf,len,flags) send(fd2sock(fd),buf,len,flags)
#define sSendto(fd,buf,len,flags,to,addrlen) sendto(fd2sock(fd),buf,len,flags,to,addrlen)
#define sSetsockopt(fd,level,optname,optval,optlen) setsockopt(fd2sock(fd),level,optname,optval,optlen)
#define sShutdown(fd,how) shutdown(fd2sock(fd),how)
#define sFD_SET(fd,set) FD_SET(fd2sock(fd),set)
#define sFD_CLR(fd,set) FD_CLR(fd2sock(fd),set)
#define sFD_ISSET(fd,set) FD_ISSET(fd2sock(fd),set)
#define sFD_ZERO FD_ZERO
#else
// nix portability layer

#define SOCKET_ERROR (-1)

#define sErrno errno
#define S_ENOTSOCK EBADF
#define S_EWOULDBLOCK EAGAIN
#define S_EINTR EINTR
#define S_ECONNABORTED ECONNABORTED

#define sAccept accept
#define sClose close
#define sSocket socket

#define sBind bind
#define sConnect connect
#define sIoctl ioctl
#define sListen listen
#define sRecv recv
#define sRecvfrom recvfrom
#define sSelect select
#define sSend send
#define sSendto sendto
#define sSetsockopt setsockopt
#define sShutdown shutdown
#define sFD_SET FD_SET
#define sFD_CLR FD_CLR
#define sFD_ISSET FD_ISSET
#define sFD_ZERO FD_ZERO

#endif
// buffer I/O macros
#define RBUFP(p,pos) (((uint8*)(p)) + (pos))
#define RBUFB(p,pos) (*(uint8*)RBUFP((p),(pos)))
#define RBUFW(p,pos) (*(uint16*)RBUFP((p),(pos)))
#define RBUFL(p,pos) (*(uint32*)RBUFP((p),(pos)))
#define RBUFF(p,pos) (*(float*)RBUFP((p),(pos)))

#define WBUFP(p,pos) (((uint8*)(p)) + (pos))
#define WBUFB(p,pos) (*(uint8*)WBUFP((p),(pos)))
#define WBUFW(p,pos) (*(uint16*)WBUFP((p),(pos)))
#define WBUFL(p,pos) (*(uint32*)WBUFP((p),(pos)))
#define WBUFU(p,pos) (*(uint64*)WBUFP((p),(pos)))
#define WBUFF(p,pos) (*(float*)WBUFP((p),(pos)))

#define TOB(n) ((uint8)((n)&UINT8_MAX))
#define TOW(n) ((uint16)((n)&UINT16_MAX))
#define TOL(n) ((uint32)((n)&UINT32_MAX))

#endif
