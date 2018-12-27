package at.ac.tuwien.big.roverml.codegen;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

public class SimulatorGeneratorModule extends AbstractGenericResourceRuntimeModule {

	@Override
	protected String getLanguageName() {
		return "at.ac.tuwien.big.Roverml";
	}

	@Override
	protected String getFileExtensions() {
		return "roverml";
	}

	public Class<? extends IGenerator> bindIGenerator() {
		return SimulatorGenerator.class;
	}

	public Class<? extends ResourceSet> bindResourceSet() {
		return ResourceSetImpl.class;
	}

	public Class<? extends IEncodingProvider> bindIEncodingProvider() {
		return Utf8EncodingProvider.class;
	}

}
