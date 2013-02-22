package se.jbee.build;

import static se.jbee.build.Name.named;

import java.util.Arrays;

/**
 * A {@link Goal} is a state or result (similar to an ant target). It is described by the set of
 * artifacts that are given and those that are not given (any longer).
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Goal {

	private static final Module[] NO_BOUNDARY = new Module[0];
	private static final Subgoal[] NO_SUBGOALS = new Subgoal[0];

	public static Goal goal( String name ) {
		return is( named( name ) );
	}

	public static Goal is( Name name ) {
		return new Goal( name, NO_SUBGOALS );
	}

	public final Name name;
	private final Subgoal[] subgoals;

	private Goal( Name name, Subgoal[] subgoals ) {
		super();
		this.name = name;
		this.subgoals = subgoals;
	}

	public Goal in( Module... modules ) {
		Subgoal[] copy = subgoals.clone();
		copy[0] = copy[0].in( modules );
		return new Goal( name, copy );
	}

	public Goal is( Artifact artifact ) {
		Subgoal[] prepanded = new Subgoal[subgoals.length];
		System.arraycopy( subgoals, 0, prepanded, 1, subgoals.length );
		prepanded[0] = new Subgoal( artifact, NO_BOUNDARY );
		return new Goal( name, prepanded );
	}

	public static class Subgoal {

		private final Artifact artifact;
		private final Module[] boundary;

		Subgoal( Artifact artifact, Module[] boundary ) {
			super();
			this.artifact = artifact;
			this.boundary = boundary;
		}

		public Subgoal in( Module... modules ) {
			return new Subgoal( artifact, modules );
		}

		@Override
		public String toString() {
			return boundary.length == 0
				? artifact.toString()
				: artifact + " " + Arrays.toString( boundary );
		}
	}

	@Override
	public String toString() {
		return name + " " + Arrays.toString( subgoals );
	}
}
