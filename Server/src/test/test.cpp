#include "test.h"


void Test::run(){
	ShowInfo(CL_CYAN"Running WarGames Server Model Test"CL_RESET"\n");

	bool allTestsPassed = true;
	TestUnit *testUnits = new TestUnit();

	ShowStatus("Testing Unit Creation\n");
	if (!testUnits->run())
		allTestsPassed = false;
	/*
	ShowStatus("Testing Terrain Creation\n");
	if (!testTerrain())
		allTestsPassed = false;
	
	ShowStatus("Testing Map Creation\n");
	if (!testMap())
		allTestsPassed = false;
	*/

	if (allTestsPassed)
		ShowInfo(CL_CYAN"All Tests Passed"CL_RESET"\n\nPress enter to exit test.");
	else
		ShowInfo(CL_CYAN"Test(s) Failed"CL_RESET"\n\nPress enter to exit test.");

	char c = getchar();
}

bool Test::testTerrain(){
	return true;
}

bool Test::testMap(){
	return true;
}