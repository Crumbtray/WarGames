#pragma once
enum UnitType : int {
	Infantry = 0, 
	Tank = 1, 
	Artillery = 2, 
	Recon = 3, 
	Bike = 4,
	Rockets = 5, 
	Mech = 6, 
	Fighter = 7, 
	Duster = 8, 
	Bomber = 9, 
	Transport_Copter = 10,
	Battleship = 11, 
	Cruiser = 12, 
	Submarine = 13, 
	Lander = 14
};

enum MobilityType { 
	Foot, Tire, Tread, Air, Boat 
};

enum TerrainType {
	Plain, Mountain, Road, Sea, Wood,
	HQ, Factory, City, Airport
};
