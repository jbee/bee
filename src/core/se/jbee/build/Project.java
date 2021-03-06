package se.jbee.build;

import static se.jbee.build.Name.named;

/**
 * A {@link Project} add a utility level to the minimal {@link Builder} interface so that the
 * {@link Builder}s themselves doesn't have to implement that each on their own.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Project
		implements Builder {

	public static Project project( Builder builder ) {
		return new Project( builder );
	}

	private final Builder builder;

	private Project( Builder builder ) {
		super();
		this.builder = builder;
	}

	@Override
	public final Module module( Name name, Module... parents ) {
		return builder.module( name, parents );
	}

	public Module module( String name, Module... parents ) {
		return module( named( name ), parents );
	}

	@Override
	public Goal goal( Name name ) {
		return builder.goal( name );
	}

	public Goal goal( String name ) {
		return goal( named( name ) );
	}

	@Override
	public Production produce( Artifact outcome ) {
		return builder.produce( outcome );
	}
}
