package at.ac.tuwien.big.roverml.codegen;

import org.eclipse.xtext.ISetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class SimulatorGeneratorSetup implements ISetup {

	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		return Guice.createInjector(new SimulatorGeneratorModule());
	}

}
