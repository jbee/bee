package se.jbee.build;

import java.util.Arrays;

/**
 * A {@link Goal} is a state or result (similar to an ant target). It is described by the set of
 * artifacts that are given and those that are not given (any longer).
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Goal {

	public static interface Goals {

		void update( Goal goal );
	}

	private static final Module[] NO_BOUNDARY = new Module[0];
	private static final Subgoal[] NO_SUBGOALS = new Subgoal[0];

	public static Goal goal( Name name, Goals goals ) {
		return new Goal( name, goals, NO_SUBGOALS, NO_SUBGOALS );
	}

	public final Name name;
	private final Goals goals;
	private final Subgoal[] subgoals;
	private final Subgoal[] allowes;

	private Goal( Name name, Goals goals, Subgoal[] subgoals, Subgoal[] allowes ) {
		super();
		this.name = name;
		this.goals = goals;
		this.subgoals = subgoals;
		this.allowes = allowes;
		goals.update( this );
	}

	public Goal in( Module... modules ) {
		final Subgoal[] bound = allowes.length == 0
			? subgoals
			: allowes;
		Subgoal[] copy = boundary( bound, modules );
		return new Goal( name, goals, copy, allowes );
	}

	private static Subgoal[] boundary( Subgoal[] a, Module... modules ) {
		Subgoal[] copy = a.clone();
		copy[0] = copy[0].in( modules );
		return copy;
	}

	public Goal is( Artifact artifact ) {
		return new Goal( name, goals, prepanded( artifact, subgoals ), allowes );
	}

	public Goal allows( Artifact artifact ) {
		return new Goal( name, goals, subgoals, prepanded( artifact, allowes ) );
	}

	private static Subgoal[] prepanded( Artifact artifact, Subgoal[] a ) {
		Subgoal[] prepanded = new Subgoal[a.length];
		System.arraycopy( a, 0, prepanded, 1, a.length );
		prepanded[0] = new Subgoal( artifact, NO_BOUNDARY );
		return prepanded;
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
