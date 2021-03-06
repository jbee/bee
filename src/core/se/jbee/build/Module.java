package se.jbee.build;

import java.util.Arrays;
import java.util.Iterator;

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
public final class Module
		implements Iterable<Module> {

	public static interface Modules {

		void update( Module module );
	}

	private static final Library[] NO_LIBRARY_DEPENDENCIES = new Library[0];

	public static Module module( Name name, Modules modules, Module... parents ) {
		return new Module( name, modules, Artifacts.NONE, parents, NO_LIBRARY_DEPENDENCIES );
	}

	public final Name name;
	public final Artifacts artifacts;
	private final Modules modules;
	private final Module[] parents;
	private final Library[] libraries;

	private Module( Name name, Modules modules, Artifacts artifacts, Module[] parents,
			Library[] libraries ) {
		super();
		this.name = name;
		this.modules = modules;
		this.artifacts = artifacts;
		this.parents = parents;
		this.libraries = libraries;
		modules.update( this );
	}

	/**
	 * Defines what kinds of {@link Artifact}s are expected within this module. Just those will be
	 * processed.
	 * 
	 * @param artifacts
	 * @return
	 */
	public Module includes( Artifact... artifacts ) {
		return includes( Artifacts.artifacts( artifacts ) );
	}

	public Module includes( Artifacts artifacts ) {
		return new Module( name, modules, artifacts, parents, libraries );
	}

	public Module uses( Library... libraries ) {
		return new Module( name, modules, artifacts, parents, libraries );
	}

	@Override
	public Iterator<Module> iterator() {
		return Arrays.asList( parents ).iterator();
	}

	@Override
	public String toString() {
		return name + " " + artifacts;
	}
}