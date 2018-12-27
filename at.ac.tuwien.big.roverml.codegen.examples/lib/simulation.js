function drawRover(roverNumber){

	var canvas = document.getElementById("simulationCanvas");
	var ctx = canvas.getContext("2d");

	var lastIndex = xPos[roverNumber].length - 1;

	var img = document.getElementById("rover_image_"+roverNumber);
	ctx.filter = img.style.filter;
	ctx.drawImage(img, xPos[roverNumber][lastIndex], yPos[roverNumber][lastIndex], 50, 50);
	ctx.filter = "none";
}

function changeRoverPlace(roverNumber, addX, addY){

	var lastIndex = xPos[roverNumber].length - 1;

	var oldX = xPos[roverNumber][lastIndex];
	var oldY = yPos[roverNumber][lastIndex];	

	var newX = xPos[roverNumber][lastIndex] + addX;
	var newY = yPos[roverNumber][lastIndex] + addY;

	xPos[roverNumber].push(newX);
	yPos[roverNumber].push(newY);
	
	var canvas = document.getElementById("simulationCanvas");
	var ctx = canvas.getContext("2d");
	
	//Fill the previous space with white rect
	ctx.fillStyle = "#ffffff";
	ctx.fillRect(oldX, oldY, 50, 50);

	//Replace old position by a small rect
	ctx.fillStyle = "red";
	var img = document.getElementById("rover_image_"+roverNumber);
	ctx.filter = img.style.filter;
	ctx.fillRect(oldX, oldY, 10, 10);
	ctx.filter = "none";

	//Draw rover on new position
  	drawRover(roverNumber);
}

//The way to move a rover depends on the angle
function moveRover(roverNumber,roverAngle,distance){

	var addX = Math.cos(roverAngle) * distance;
	var addY = Math.sin(roverAngle) * distance;

	changeRoverPlace(roverNumber,addX,addY);
}

function setLight(lightID, newColor) {
	var light = document.getElementById(lightID);
	light.className= "light "+newColor;
}

//Returns the new angle of the rover
function rotate(oldAngle,addAngle){
	return oldAngle + addAngle;
}

function highlight(commandID){
	var command = document.getElementById(commandID);
	command.className = "list-group-item active";
}

function unHighlight(commandID){
	var command = document.getElementById(commandID);
	command.className = "list-group-item";
}
