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
public class Module {

	public static void main( String[] args ) {

	}
	// usage example
	// Module core = module("main");
	// Module ui = module("ui").uses(core).dependsOn(<dependency>);
	// Module db = module("db).uses(core.restrictedTo(api)); // api is a strategy that filters the core module artifacts so that just those that are part of the API are available
}
