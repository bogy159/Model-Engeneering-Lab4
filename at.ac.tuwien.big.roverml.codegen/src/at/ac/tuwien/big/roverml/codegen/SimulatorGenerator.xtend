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

class SimulatorGenerator implements IGenerator {
	
	// Helper functions START
	
	// The maps and functions below are used to save (and generate) the ids of rovers, lights and commands.
	val Map<Rover, Integer> roverId = newHashMap
	val Map<Light, String> lightId = newHashMap
	val Map<Command, String> commandId = newHashMap
	
	def Integer getRoverId(Rover rover) {
		roverId.computeIfAbsent(rover, [r|roverId.size])
	}
	
	def String getLightId(Light light) {
		lightId.computeIfAbsent(light, ['''light_lightId.size'''])
	}
	
	def String getCommandId(Command command) {
		commandId.computeIfAbsent(command, ['''command_commandId.size'''])
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
		'''
	}
	
	def generateJavascript(RoverSystem roverSystem) {
		// TODO generate .js
		'''
		'''
	}
	
	override doGenerate(Resource input, IFileSystemAccess fsa) {
		val system = input.contents.findFirst[o|o instanceof RoverSystem] as RoverSystem
		fsa.generateFile('''input.URI.trimFileExtension.lastSegment.html''', system.generateHtml)
		fsa.generateFile('''input.URI.trimFileExtension.lastSegment.js''', system.generateJavascript)
	}
	
}