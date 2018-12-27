package at.ac.tuwien.big.roverml.codegen;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.mwe.Reader;

import at.ac.tuwien.big.roverml.RoverMLPackage;

public class ResourceReader extends Reader {

	@Override
	public void preInvoke() {
		super.preInvoke();
		EPackage.Registry.INSTANCE.put(RoverMLPackage.eNS_URI, RoverMLPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	}
}
