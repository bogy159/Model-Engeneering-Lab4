package at.ac.tuwien.big.roverml.codegen

import at.ac.tuwien.big.roverml.AngleUnit
import at.ac.tuwien.big.roverml.Block
import at.ac.tuwien.big.roverml.Command
import at.ac.tuwien.big.roverml.Light
import at.ac.tuwien.big.roverml.Move
import at.ac.tuwien.big.roverml.Repeat
import at.ac.tuwien.big.roverml.Rotate
import at.ac.tuwien.big.roverml.Rover
import at.ac.tuwien.big.roverml.RoverProgram
import at.ac.tuwien.big.roverml.RoverSystem
import at.ac.tuwien.big.roverml.SetLightColor
import at.ac.tuwien.big.roverml.Terminate
import at.ac.tuwien.big.roverml.TriggeredTransition
import at.ac.tuwien.big.roverml.Wait
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import at.ac.tuwien.big.roverml.Component
import org.eclipse.emf.common.util.EList

class SimulatorGenerator implements IGenerator {

	// Helper functions START
	// The maps and functions below are used to save (and generate) the ids of rovers, lights and commands.
	val Map<Rover, Integer> roverId = newHashMap
	val Map<Light, Integer> lightId = newHashMap
	val Map<Command, Integer> commandId = newHashMap
	
	Command prevCommand = null;

	def Integer getRoverId(Rover rover) {
		roverId.computeIfAbsent(rover, [r|roverId.size])
	}

	def Integer getLightId(Light light) {
		lightId.computeIfAbsent(light, [l|lightId.size])
	}

	def Integer getCommandId(Command command) {
		commandId.computeIfAbsent(command, [c|commandId.size])
	}
	
	def void setPrevCommand(Command c) {
		this.prevCommand = c
	}

	/**
	 * This functions computes the hue-angle for the CSS filter of the rover element.
	 */
	def float getHueAngle(Rover rover) {
		return 360.0f * (rover.roverId / (roverId.size * 1.0f)) * rover.roverId
	}

	// Helper functions END
	def dispatch generateHtml(RoverSystem roverSystem) {
		// TODO: generate .html
		'''
			<!DOCTYPE html>
			<html lang="en">
			<head>
			
			<meta name="description" content="">
			<meta name="author" content="">
			
			<meta charset="utf-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<title>Example1-ozobotEvo Simulation</title>
			
			<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
			<script src="bootstrap/js/jquery-3.3.1.min.js"></script>
			<script src="bootstrap/js/bootstrap.min.js"></script>
			<link href="lib/simulation.css" rel="stylesheet">
			<script src="lib/simulation.js"></script>
			<script src="Example1-ozobotEvo.js"></script>
			</head>
			
			<body onload="drawOnLoad();">
			
				<nav class="navbar navbar-inverse navbar-fixed-top">
					<div class="container">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand">Example1-ozobotEvo Simulation</a>
						</div>
						<div id="navbar" class="collapse navbar-collapse">
							<ul class="nav navbar-nav">
								<li><a onclick="runSimulation();">Run</a></li>
								<li><a onclick="step();">Next step</a></li>
							</ul>
						</div>
					</div>
				</nav>
				
				<div class="page-header">
					<br/>
					<div class="container">
						<div class="row">
							<div class="col-sm-4">
								<div id="roversImages" class="panel panel-primary">
									<div class="panel-heading">Rovers</div>
									<div class="panel-body">
										
										«FOR Rover rover : roverSystem.rovers»
											<div class="col-sm-6">
												<h3>«rover.name»</h3>
												<img src="«getRoverImage()»" id="rover_image_«getRoverId(rover)»" width="50" style="filter:hue-rotate(«getHueAngle(rover)»deg)"/>
											</div>
											
										«ENDFOR»
									</div> 
								</div>
							</div>
							
							<div class="col-sm-8">
								<div class="panel panel-primary">
									<div class="panel-heading"><span style="font-size:16px;">Simulation canvas</span></div>
									<div class="panel-body" id="simulationDiv"><canvas id="simulationCanvas" width="700" height="300"></canvas></div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="container">
						«FOR Rover rover : roverSystem.rovers»
							<div class="col-sm-3">
								<div class="panel panel-success">
									<div class="panel-heading">Lights of «rover.name»</div>
									<div class="panel-body">
										«FOR Component comp: rover.components»
											«IF comp instanceof Light»
												<span id="light_«getLightId(comp)»" class="light off">&ensp;&ensp;</span>
											«ENDIF»
										«ENDFOR»
									</div> 
								</div>
								
								«FOR i: 0..roverSystem.roverPrograms.size - 1»
									«var roverProgram = roverSystem.roverPrograms.get(i)»
									«IF roverProgram.rover == rover»
										<div class="panel panel-danger">
											<div class="panel-heading">«roverProgram.name»</div>
											«val block = roverProgram.block»
											«IF block instanceof Repeat»
												<li class="list-group-item">
													<h4>repeat «block.count» times</h4>
													
													<ul class="list-group">
													«FOR j: 0..roverProgram.block.commands.size - 1»
														«var command = roverProgram.block.commands.get(j)»
															<li id="command_«getCommandId(command)»" class="list-group-item">
																«IF command.name !== ""»
																	«command.name»
																«ELSE»
																	//@roverPrograms.«i»/@block/@commands.«j»
																«ENDIF»
															</li>
													«ENDFOR»
													</ul>
												</li>
											«ELSE»
												<ul class="list-group">
												«FOR j: 0..roverProgram.block.commands.size - 1»
													«var command = roverProgram.block.commands.get(j)»
														«IF command instanceof Repeat»
															<li class="list-group-item">
																<h4>repeat «command.count» times</h4>
																
																«FOR k: 0..command.commands.size - 1»
																	«var com = command.commands.get(k)»
																	<ul class="list-group">
																		<li id="command_«getCommandId(com)»" class="list-group-item">
																			«IF com.name !== null && com.name !== ""»
																				«com.name»
																			«ELSE»
																				//@roverPrograms.«i»/@block/@commands.«j»/@commands.«k»
																			«ENDIF»
																		</li>
																«ENDFOR»
																</ul>
															</li>
														«ELSE»
															<li id="command_«getCommandId(command)»" class="list-group-item">
																«IF command.name !== ""»
																	«command.name»
																«ELSE»
																	//@roverPrograms.«i»/@block/@commands.«j»
																«ENDIF»
															</li>
														«ENDIF»
												«ENDFOR»
												</ul>
											«ENDIF»
										</div>
									«ENDIF»
								«ENDFOR»
							</div>
							
						«ENDFOR»
					
					</div>
				</div>
			</body>
			</html>
		'''

	}

	def generateJavascript(RoverSystem roverSystem) {
		// TODO generate .js
		'''
		var xPos = [[0],[0]];
		var yPos = [[10],[80]];
		
		var rover0angle = 0;
		var rover1angle = 0;
		
		var nextStep = 0;
		
		«FOR i: 0..roverSystem.roverPrograms.size - 1»
			«var roverProgram = roverSystem.roverPrograms.get(i)»
			«var block = roverProgram.block»
			«var rover = roverProgram.rover»
			var «rover.name»_commands =[
					«IF block instanceof Repeat»
						«FOR j: 0..block.count - 1»
							«FOR k: 0..block.commands.size - 1»
								«var command = block.commands.get(k)»
								«IF k > 0»
									«setPrevCommand(block.commands.get(k - 1))»
									()=>{«setCommandName(rover, command)»("light_0","green");«unHighlightPreviousCommand(prevCommand)»;highlight("command_«command.commandId»");},
								«ELSE»
									()=>{«setCommandName(rover, command)»("light_0","green");highlight("command_«command.commandId»");},
								«ENDIF»
							«ENDFOR»
						«ENDFOR»
					«ELSE»
						«FOR k: 0..block.commands.size - 1»
							«var command = block.commands.get(k)»
							«IF command instanceof Repeat»
								«FOR j: 0..command.count - 1»
									«FOR t: 0..command.commands.size - 1»
										«var com = command.commands.get(t)»
										«IF t > 0»
											«setPrevCommand(command.commands.get(t - 1))»
											()=>{«setCommandName(rover, com)»("light_0","green");«unHighlightPreviousCommand(prevCommand)»;highlight("command_«com.commandId»");},
										«ELSEIF t == 0 && k > 0 && j == 0»
											«setPrevCommand(block.commands.get(k - 1))»
											()=>{«setCommandName(rover, com)»("light_0","green");«unHighlightPreviousCommand(prevCommand)»;highlight("command_«com.commandId»");},
										«ELSEIF t == 0 && j > 0»
											«setPrevCommand(command.commands.get(t))»
											()=>{«setCommandName(rover, com)»("light_0","green");«unHighlightPreviousCommand(prevCommand)»;highlight("command_«com.commandId»");},
										«ELSE»
											()=>{«setCommandName(rover, com)»("light_0","green");highlight("command_«com.commandId»");},
										«ENDIF»
									«ENDFOR»
								«ENDFOR»
							«ELSE»
								«IF k > 0»
									()=>{«setCommandName(rover, command)»("light_0","green");«unHighlightPreviousCommand(prevCommand)»;highlight("command_«command.commandId»");},
								«ELSE»
									()=>{«setCommandName(rover, command)»("light_0","green");highlight("command_«command.commandId»");},
								«ENDIF»
							«ENDIF»
							
						«ENDFOR»
					«ENDIF»
				];
		«ENDFOR»
		'''
	}
	
//	()=>{setLight("light_0","green");highlight("command_0");},
//								()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
//								()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
//								()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");},
//							()=>{setLight("light_0","green");unHighlight("command_3");highlight("command_0");},
//							()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
//							()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
//							()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");},
//							()=>{setLight("light_0","green");unHighlight("command_3");highlight("command_0");},
//							()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
//							()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
//							()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");},
//							()=>{setLight("light_0","green");unHighlight("command_3");highlight("command_0");},
//							()=>{moveRover(0,rover0angle,100.0);unHighlight("command_0");highlight("command_1");},
//							()=>{setLight("light_0","red");unHighlight("command_1");highlight("command_2");},
//							()=>{rover0angle=rotate(rover0angle,1.5707964);unHighlight("command_2");highlight("command_3");}

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		val system = input.contents.findFirst[o|o instanceof RoverSystem] as RoverSystem
		fsa.generateFile('''input.URI.trimFileExtension.lastSegment.html''', system.generateHtml)
		fsa.generateFile('''input.URI.trimFileExtension.lastSegment.js''', system.generateJavascript)
	}

	private def getRoverImage() {
		return "img/rover1.gif"
	}
	
	private def String unHighlightPreviousCommand(Command c1) {
		if (c1 !== null) {
			return "unHighlight(\"command_" +  c1.commandId.toString() + "\")";
		}	
	}
	
	private def String setCommandName(Rover rover, Command c) {
		if (c instanceof SetLightColor) {
			return "setLight"
		} else if (c instanceof Move) {
			return "moveRover"
		} else if (c instanceof Rotate) {
			return new StringBuilder().append("rover").append(rover.roverId.toString()).append("angle").toString();
		}
	}

}
