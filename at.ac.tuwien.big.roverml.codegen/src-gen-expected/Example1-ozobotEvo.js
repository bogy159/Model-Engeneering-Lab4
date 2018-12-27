//These variable stores the successive positions of rovers
// xPos[0][0] = the 1st X position of rover 0
// yPos[1][3] = the 4th Y position of rover 1
var xPos = [[0],[0]];
var yPos = [[10],[80]];

//These variables are used to know in which direction the rovers have to move
//Init them to 0;
var rover0angle = 0;
var rover1angle = 0;

//This variable stores the number of next executed command
var nextStep = 0;

var ozobotEvo_commands =[
		()=>{setLight("light_0","green");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");},
		()=>{setLight("light_0","green");unHighlight("command_3");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");},
		()=>{setLight("light_0","green");unHighlight("command_3");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");},
		()=>{setLight("light_0","green");unHighlight("command_3");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");}
	];

var ozobotBit_commands =[
		()=>{setLight("light_6","yellow");highlight("command_4");},
		()=>{moveRover(1,rover1angle,100.0);unHighlight("command_4");highlight("command_5");},
		()=>{moveRover(1,rover1angle,100.0);unHighlight("command_5");highlight("command_5");},
		()=>{moveRover(1,rover1angle,100.0);unHighlight("command_5");highlight("command_5");},
		()=>{moveRover(1,rover1angle,100.0);unHighlight("command_5");highlight("command_5");},
		()=>{moveRover(1,rover1angle,100.0);unHighlight("command_5");highlight("command_5");},
		()=>{moveRover(1,rover1angle,100.0);unHighlight("command_5");highlight("command_5");},
		()=>{setLight("light_6","blue");unHighlight("command_5");highlight("command_6");}
	];


function drawOnLoad(){
	drawRover(0);
	drawRover(1);
}

function squareWalk() {
	for (var i0 = 4; i0 > 0; i0--) {
		setLight("light_0","green");
		moveRover(0,rover0angle,100.0);
		setLight("light_0","red");
		rover0angle=rotate(rover0angle,1.5707964);
	}
}
function program() {
	setLight("light_6","yellow");
	for (var i0 = 6; i0 > 0; i0--) {
		moveRover(1,rover1angle,100.0);
	}
	setLight("light_6","blue");
}

function runSimulation(){
	squareWalk();
	program();
	nextStep = Math.max(ozobotEvo_commands.length,ozobotBit_commands.length);
}

function step(){

	if(nextStep <= ozobotEvo_commands.length - 1){
		ozobotEvo_commands[nextStep].call();
	}
	
	if(nextStep <= ozobotBit_commands.length - 1){
		ozobotBit_commands[nextStep].call();
	}
	
	nextStep++;
}
