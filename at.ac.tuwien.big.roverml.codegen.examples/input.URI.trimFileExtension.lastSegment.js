var xPos = [[0],[0]];
var yPos = [[10],[80]];

var rover0angle = 0;
var rover1angle = 0;

var nextStep = 0;

var ozobotEvo_commands =[
		()=>{setLight("light_0","green");highlight("command_0");},
		()=>{moveRover("light_0","green");unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","green");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle("light_0","green");unHighlight("command_2");highlight("command_3");},
		()=>{setLight("light_0","green");highlight("command_0");},
		()=>{moveRover("light_0","green");unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","green");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle("light_0","green");unHighlight("command_2");highlight("command_3");},
		()=>{setLight("light_0","green");highlight("command_0");},
		()=>{moveRover("light_0","green");unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","green");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle("light_0","green");unHighlight("command_2");highlight("command_3");},
		()=>{setLight("light_0","green");highlight("command_0");},
		()=>{moveRover("light_0","green");unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","green");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle("light_0","green");unHighlight("command_2");highlight("command_3");},
	];
var ozobotBit_commands =[
		()=>{setLight("light_0","green");highlight("command_4");},
		
		()=>{moveRover("light_0","green");unHighlight("command_4");highlight("command_5");},
		()=>{moveRover("light_0","green");unHighlight("command_5");highlight("command_5");},
		()=>{moveRover("light_0","green");unHighlight("command_5");highlight("command_5");},
		()=>{moveRover("light_0","green");unHighlight("command_5");highlight("command_5");},
		()=>{moveRover("light_0","green");unHighlight("command_5");highlight("command_5");},
		()=>{moveRover("light_0","green");unHighlight("command_5");highlight("command_5");},
		
		()=>{setLight("light_0","green");unHighlight("command_5");highlight("command_6");},
		
	];
