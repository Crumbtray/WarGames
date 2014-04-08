package com.wargames.client.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConversionTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		for(int i = 0; i < 16; i++)
		{
			System.out.println("Integer is: " + i);
			byte byteNum = (byte) i;
			System.out.println("Byte is: " + byteNum);
		}
		
	}

}
