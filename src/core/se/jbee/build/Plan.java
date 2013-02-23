package se.jbee.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.jbee.build.Goal.Goals;
import se.jbee.build.Goal.Subgoal;
import se.jbee.build.Module.Modules;
import se.jbee.build.Production.Productions;

/**
 * Contains all the state of a {@link Build}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public class Plan
		implements Builder, Goals, Modules, Productions {

	private final Map<Name, Goal> goals = new HashMap<Name, Goal>();
	private final Map<Name, Module> modules = new HashMap<Name, Module>();
	private final Map<Artifact, Set<Name>> sources = new HashMap<Artifact, Set<Name>>();
	private final Map<Artifact, Production> productions = new HashMap<Artifact, Production>();

	@Override
	public Goal goal( Name name ) {
		final Goal goal = goals.get( name );
		return goal != null
			? goal
			: Goal.goal( name, this );
	}

	@Override
	public Module module( Name name, Module... parents ) {
		final Module module = modules.get( name );
		return module != null
			? module
			: Module.module( name, this, parents );
	}

	@Override
	public Production produce( Artifact outcome ) {
		final Production production = productions.get( outcome );
		return production != null
			? production
			: Production.production( outcome, this );
	}

	@Override
	public void update( Goal goal ) {
		goals.put( goal.name, goal );
	}

	@Override
	public void update( Module module ) {
		modules.put( module.name, module );
		for ( Artifact a : module ) {
			Set<Name> s = sources.get( a );
			if ( s == null ) {
				s = new HashSet<Name>();
				sources.put( a, s );
			}
			s.add( module.name );
		}
	}

	@Override
	public void update( Production production ) {
		productions.put( production.outcome, production );
	}

	public boolean canProduce( Artifact outcome ) {
		Production p = productions.get( outcome );
		return p != null && sources.containsKey( p.source );
	}

	public Step[] execution( Name goal, Name... modules ) {
		Goal g = goals.get( goal );
		List<Step> steps = new ArrayList<Step>();
		for ( Subgoal sg : g ) {
		}
		return steps.toArray( new Step[steps.size()] );
	}
}
