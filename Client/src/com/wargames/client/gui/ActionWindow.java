package com.wargames.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.wargames.client.communication.packet.outgoing.ActionPacket;
import com.wargames.client.helpers.Coordinate;
import com.wargames.client.helpers.PacketSender;

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
			
			//client.guiMap.moveSelectedUnit(client.selectedUnit, client.guiMap.getCoordinate(mouseClickX, mouseClickY));
			// Setup and send the action packet to move here.
			short moverUnitID = (short) this.client.selectedUnit.getLogicalUnit().id;
			Coordinate moveCoordinate = client.guiMap.getCoordinate(mouseClickX, mouseClickY);
			byte xPos = (byte) moveCoordinate.x;
			byte yPos = (byte) moveCoordinate.y;
			byte moveAction = 0;
			short targetUnitID = 0;
			ActionPacket packet = new ActionPacket(moverUnitID, xPos, yPos, moveAction, targetUnitID);
			PacketSender.sendPacket(packet);
			
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