package se.jbee.build;

/**
 * All modules are direct sub-folders of the {@link Src}.
 * 
 * <pre>
 * src/foo-module
 * src/bar-module
 * </pre>
 * 
 * {@link Module}s are used to slice larger applications into parts that depend on each other in a
 * directed acyclic graph. In addition each of them can have a list of {@link Dependency}s.
 * 
 * As default the {@link Target} gets the same structure. It is produced by invoking the
 * {@link Producer}s.
 * 
 * <pre>
 * target/foo-module/
 * target/bar-module/
 * </pre>
 * 
 * While this can be controlled in the build all targets have to be sub-folders of {@link Target}.
 * Otherwise the process is a distribution task that is done using {@link Distributor}s.
 * 
 * Distribution is always done after the artifacts have been produces. While is it given implicitly
 * how to clean produced artifacts this feature might not work for all {@link Distributor}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Module {

	private static final Artifact[] NO_ARTIFACTS = new Artifact[0];
	private static final Library[] NO_LIBRARY_DEPENDENCIES = new Library[0];
	private static final Module[] NO_MODULE_DEPENDENCIES = new Module[0];

	public static Module module( String name ) {
		return module( Name.named( name ) );
	}

	public static Module module( Name name ) {
		return new Module( name, NO_ARTIFACTS, NO_MODULE_DEPENDENCIES, NO_LIBRARY_DEPENDENCIES );
	}

	public final Name name;
	private final Artifact[] artifacts;
	private final Module[] modules;
	private final Library[] libraries;

	private Module( Name name, Artifact[] artifacts, Module[] modules, Library[] libraries ) {
		super();
		this.name = name;
		this.artifacts = artifacts;
		this.modules = modules;
		this.libraries = libraries;
	}

	public Module includes( Artifact... artifacts ) {
		return new Module( name, artifacts, modules, libraries );
	}

	public Module uses( Module... modules ) {
		return new Module( name, artifacts, modules, libraries );
	}

	public Module uses( Library... libraries ) {
		return new Module( name, artifacts, modules, libraries );
	}
}