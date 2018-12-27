//These variable stores the successive positions of rovers
// xPos[0][0] = the 1st X position of rover 0
// yPos[1][3] = the 4th Y position of rover 1
var xPos = [[0],[0]];
var yPos = [[0],[70]];

//These variables are used to know in which direction the rovers have to move
//Init them to 0;
var rover0angle = 0;
var rover1angle = 0;

//This variable stores the number of next executed command
var nextStep = 0;

var ozobotEvo_commands = [()=>{setLight("0_toplight","green");unHighlight("command_0_0");highlight("command_0_0");},
						  ()=>{moveRover(0,rover0angle,20);unHighlight("command_0_0");highlight("command_0_1");},
						  ()=>{setLight("0_toplight","red");unHighlight("command_0_1");highlight("command_0_2");},
						  ()=>{rover0angle=rotate(rover0angle,1.5708);unHighlight("command_0_2");highlight("command_0_3");},
						  
						  ()=>{setLight("0_toplight","green");unHighlight("command_0_3");highlight("command_0_0");},
						  ()=>{moveRover(0,rover0angle,20);unHighlight("command_0_0");highlight("command_0_1");},
						  ()=>{setLight("0_toplight","red");unHighlight("command_0_1");highlight("command_0_2");},
						  ()=>{rover0angle=rotate(rover0angle,1.5708);unHighlight("command_0_2");highlight("command_0_3");},

						  ()=>{setLight("0_toplight","green");unHighlight("command_0_3");highlight("command_0_0");},
						  ()=>{moveRover(0,rover0angle,20);unHighlight("command_0_0");highlight("command_0_1");},
						  ()=>{setLight("0_toplight","red");unHighlight("command_0_1");highlight("command_0_2");},
						  ()=>{rover0angle=rotate(rover0angle,1.5708);unHighlight("command_0_2");highlight("command_0_3");},

						  ()=>{setLight("0_toplight","green");unHighlight("command_0_3");highlight("command_0_0");},
						  ()=>{moveRover(0,rover0angle,20);unHighlight("command_0_0");highlight("command_0_1");},
						  ()=>{setLight("0_toplight","red");unHighlight("command_0_1");highlight("command_0_2");},
						  ()=>{rover0angle=rotate(rover0angle,1.5708);unHighlight("command_0_2");highlight("command_0_3");},
						  ];

var ozobotBit_commands = [()=>{moveRover(1,rover1angle,20);unHighlight("command_1_0");highlight("command_1_0");},
						  ()=>{rover1angle=rotate(rover1angle,1.5708);unHighlight("command_1_0");highlight("command_1_1");},
						  ()=>{moveRover(1,rover1angle,20);unHighlight("command_1_1");highlight("command_1_2");},
						  ()=>{rover1angle=rotate(rover1angle,-1.5708);unHighlight("command_1_2");highlight("command_1_3");},
						  ()=>{moveRover(1,rover1angle,20);unHighlight("command_1_3");highlight("command_1_4");},
						  ()=>{moveRover(1,rover1angle,-50);unHighlight("command_1_4");highlight("command_1_5");},
						  ];


function drawOnLoad(){
	drawRover(0);
	drawRover(1);
}

function ozobotEvo(){

	for (var i = 4; i > 0; i--) {
		setLight("0_toplight","green");
		moveRover(0,rover0angle,20);	
		setLight("0_toplight","red");
		rover0angle = rotate(rover0angle,1.5708);
	}
}

function ozobotBit(){

	moveRover(1,rover1angle,20);
	rover1angle = rotate(rover1angle,1.5708);
	moveRover(1,rover1angle,20);
	rover1angle=rotate(rover1angle,-1.5708);
	moveRover(1,rover1angle,20);
	moveRover(1,rover1angle,-50);
}

function runSimulation(){

	ozobotEvo();
	ozobotBit();
	nextStep = ozobotEvo_commands.length + ozobotBit_commands.length;
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