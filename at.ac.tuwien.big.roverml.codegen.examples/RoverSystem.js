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

var OzobotEvo_commands =[
		()=>{setLight("light_0","green");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");},
		()=>{setLight("light_0","green");unHighlight("command_4");highlight("command_0");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{moveRover(0,rover0angle,100.0);unHighlight("command_2");highlight("command_1");},
		()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_1");highlight("command_2");},
		()=>{setLight("light_0","red");unHighlight("command_2");highlight("command_3");},
		()=>{unHighlight("command_3");highlight("command_4");}
	];

var PolarSysRover_commands =[
		()=>{setLight("light_6","green");setLight("light_5","green");highlight("command_5");},
		()=>{moveRover(1,rover1angle,30.0);unHighlight("command_5");highlight("command_6");},
		()=>{setLight("light_6","red");setLight("light_5","red");unHighlight("command_6");highlight("command_7");},
		()=>{rover1angle=rotate(rover1angle,3.1415927);unHighlight("command_7");highlight("command_8");}
	];


function drawOnLoad(){
	drawRover(0);
	drawRover(1);
}

function squareWalk() {
	for (var i0 = 10; i0 > 0; i0--) {
		setLight("light_0","green");
		for (var i1 = 4; i1 > 0; i1--) {
			moveRover(0,rover0angle,100.0);
			rover0angle=rotate(rover0angle,1.5707964);
		}
		setLight("light_0","red");
	}
}
function travelBetweenObstacles() {
	setLight("light_6","green");setLight("light_5","green");
	moveRover(1,rover1angle,30.0);
	setLight("light_6","red");setLight("light_5","red");
	rover1angle=rotate(rover1angle,3.1415927);
}

function runSimulation(){
	squareWalk();
	travelBetweenObstacles();
	nextStep = Math.max(OzobotEvo_commands.length,PolarSysRover_commands.length);
}

function step(){

	if(nextStep <= OzobotEvo_commands.length - 1){
		OzobotEvo_commands[nextStep].call();
	}
	
	if(nextStep <= PolarSysRover_commands.length - 1){
		PolarSysRover_commands[nextStep].call();
	}
	
	nextStep++;
}
