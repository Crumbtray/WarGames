package com.wargames.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wargames.client.communication.packet.outgoing.ActionPacket;
import com.wargames.client.helpers.Coordinate;
import com.wargames.client.helpers.PacketSender;
import com.wargames.client.model.UnitCosts;
import com.wargames.client.model.UnitType;

public class FactoryWindow extends JDialog implements ActionListener{

	/**
	 * Generated ID
	 */
	private static final long serialVersionUID = -8977575045322893189L;
	private GameClientGui client;
	private JPanel myPanel;
	private Coordinate factoryLocation;
	
	public FactoryWindow(GameClientGui client, Coordinate factoryLocation)
	{
		super(client.f, true);
		
		this.client = client;
		this.factoryLocation = factoryLocation;
		String color = this.client.guiMap.logicalGame.currentTurn.color;
		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout(new GridLayout(4, 1));
		myPanel.setBorder(new EmptyBorder(10,10,10,10));
		JLabel headerLabel = new JLabel("Factory: Select what to build:");
		myPanel.add(headerLabel);
		
		URL urlSoldierImage = getClass().getResource("/com/wargames/client/gui/img/soldier_" + color + "01.png");
		ImageIcon soldierImg = new ImageIcon(urlSoldierImage);
		JButton soldierButton = new JButton("Create Soldier (" + UnitCosts.getSoldierCost() + ")");
		soldierButton.setActionCommand("soldier");
		soldierButton.setIcon(soldierImg);
		soldierButton.addActionListener(this);
		myPanel.add(soldierButton);
		
		URL urlTankImage = getClass().getResource("/com/wargames/client/gui/img/tank_" + color + "01.png");
		ImageIcon tankImg = new ImageIcon(urlTankImage);
		JButton tankButton = new JButton("Create Tank (" + UnitCosts.getTankCost() + ")");
		tankButton.setActionCommand("tank");
		tankButton.setIcon(tankImg);
		tankButton.addActionListener(this);
		myPanel.add(tankButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("cancel");
		myPanel.add(cancelButton);
		
		int availableFunds = client.guiMap.logicalGame.currentTurn.funds;
		if(availableFunds < UnitCosts.getSoldierCost())
		{
			soldierButton.setEnabled(false);
		}
		if(availableFunds < UnitCosts.getTankCost())
		{
			tankButton.setEnabled(false);
		}
		
		this.setLocationRelativeTo(client.f);
		this.setUndecorated(true);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (client.guiMap.logicalGame.isLocalGame()){
			int localUnitID = this.client.guiMap.logicalGame.gameMap.newUnitId();
			switch(action)
			{
				case "soldier":
					this.client.guiMap.CreateUnit(UnitType.SOLDIER, this.client.loggedInPlayer, localUnitID, factoryLocation);
					break;
				case "tank":
					this.client.guiMap.CreateUnit(UnitType.TANK, this.client.loggedInPlayer, localUnitID, factoryLocation);
					break;
				default:
					break;
			}
		} else {
			//online game
			byte xPos = (byte) factoryLocation.x;
			byte yPos = (byte)factoryLocation.y;
			byte createAction = 3;
			short unitType;
			short actionCode = 0;
			ActionPacket packet;
			
			switch(action)
			{
				case "soldier":
					unitType = 0;
					packet = new ActionPacket(unitType, xPos, yPos, createAction, actionCode);
					PacketSender.sendPacket(packet);
					break;
				case "tank":
					unitType = 1;
					packet = new ActionPacket(unitType, xPos, yPos, createAction, actionCode);
					PacketSender.sendPacket(packet);
					break;
				default:
			}
		}
		
		client.repaint();
		this.dispose();
	}

}
