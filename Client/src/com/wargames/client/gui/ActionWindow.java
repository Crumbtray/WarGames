package com.wargames.client.gui;

// Fri Oct 25 18:07:43 EST 2004
//
// Written by Sean R. Owens, sean at guild dot net, released to the
// public domain.  Share and enjoy.  Since some people argue that it is
// impossible to release software to the public domain, you are also free
// to use this code under any version of the GPL, LPGL, or BSD licenses,
// or contact me for use of another license.
// http://darksleep.com/player

// A very simple custom dialog that takes a string as a parameter,
// displays it in a JLabel, along with two Jbuttons, one labeled Yes,
// and one labeled No, and waits for the user to click one of them.

import javax.swing.JDialog; 

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.util.*;

public class ActionWindow extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -777333000945971309L;
	private JPanel myPanel = null;
	private GameClientGui client;
	private GameMouseListener listener;
	
	private int mouseClickX;
	private int mouseClickY;

	public ActionWindow(GameClientGui client, MouseEvent e, ArrayList<UnitActionType> actions, GameMouseListener listener) {
		super(client.f, true);
		this.client = client;
		this.listener = listener;
		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout(new GridLayout(actions.size() + 1, 1));
		
		for(UnitActionType actionType : actions)
		{
			JButton actionButton = new JButton(actionType.toString());
			actionButton.addActionListener(this);
			myPanel.add(actionButton);
		}
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		myPanel.add(cancelButton);
		
		// Set location
		mouseClickX = e.getX();
		mouseClickY = e.getY();
		this.setLocation(mouseClickX, mouseClickY);		
		this.setUndecorated(true);
		pack();
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		switch(selectedButton.getText())
		{
		case "Attack":
			System.out.println("Attacking!");
			listener.mouseState = MouseState.FindingAttackTarget;
			listener.lastClick = client.guiMap.getCoordinate(mouseClickX, mouseClickY);
			break;
		case "Move":
			System.out.println("Moved!");
			client.guiMap.moveSelectedUnit(client.selectedUnit, client.guiMap.getCoordinate(mouseClickX, mouseClickY));
			listener.mouseState = MouseState.NothingSelected;
			break;
		case "Capture":
			System.out.println("Capturing!");
			break;
		default:
			listener.mouseState = MouseState.NothingSelected;
		}
		client.repaint();
		this.dispose();
	}

}