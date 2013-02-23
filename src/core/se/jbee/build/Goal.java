package se.jbee.build;

import java.util.Arrays;
import java.util.Iterator;

import se.jbee.build.Goal.Subgoal;

/**
 * A {@link Goal} is a state or result (similar to an ant target). It is described by the set of
 * artifacts that are given and those that are not given (any longer).
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Goal
		implements Iterable<Subgoal> {

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
	private final Subgoal[] maybes;

	private Goal( Name name, Goals goals, Subgoal[] subgoals, Subgoal[] maybes ) {
		super();
		this.name = name;
		this.goals = goals;
		this.subgoals = subgoals;
		this.maybes = maybes;
		goals.update( this );
	}

	public Goal in( Module... modules ) {
		final Subgoal[] bound = maybes.length == 0
			? subgoals
			: maybes;
		Subgoal[] copy = boundary( bound, modules );
		return new Goal( name, goals, copy, maybes );
	}

	private static Subgoal[] boundary( Subgoal[] a, Module... modules ) {
		Subgoal[] copy = a.clone();
		copy[0] = copy[0].in( modules );
		return copy;
	}

	public Goal is( Artifact outcome ) {
		return new Goal( name, goals, prepanded( outcome, subgoals ), maybes );
	}

	public Goal mayBe( Artifact outcome ) {
		return new Goal( name, goals, subgoals, prepanded( outcome, maybes ) );
	}

	private static Subgoal[] prepanded( Artifact artifact, Subgoal[] a ) {
		Subgoal[] prepanded = new Subgoal[a.length + 1];
		System.arraycopy( a, 0, prepanded, 1, a.length );
		prepanded[0] = new Subgoal( artifact, NO_BOUNDARY );
		return prepanded;
	}

	public static class Subgoal
			implements Iterable<Module> {

		public final Artifact outcome;
		private final Module[] boundary;

		Subgoal( Artifact outcome, Module[] boundary ) {
			super();
			this.outcome = outcome;
			this.boundary = boundary;
		}

		public Subgoal in( Module... modules ) {
			return new Subgoal( outcome, modules );
		}

		@Override
		public String toString() {
			return boundary.length == 0
				? outcome.toString()
				: outcome + " " + Arrays.toString( boundary );
		}

		@Override
		public Iterator<Module> iterator() {
			return Arrays.asList( boundary ).iterator();
		}
	}

	@Override
	public String toString() {
		return name + " " + Arrays.toString( subgoals );
	}

	@Override
	public Iterator<Subgoal> iterator() {
		return Arrays.asList( subgoals ).iterator();
	}
}
