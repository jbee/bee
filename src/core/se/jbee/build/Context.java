package se.jbee.build;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import se.jbee.build.Goal.Goals;
import se.jbee.build.Module.Modules;
import se.jbee.build.Production.Productions;

/**
 * Contains all the state of a {@link Build}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public class Context
		implements Builder, Goals, Modules, Productions {

	private final Map<Name, Goal> goals = new HashMap<Name, Goal>();
	private final Map<Name, Module> modules = new HashMap<Name, Module>();
	private final Map<Artifact, Set<Name>> sources = new HashMap<Artifact, Set<Name>>();
	private final Map<Artifact, Production> productions = new HashMap<Artifact, Production>();

	public boolean canProduce( Artifact outcome ) {
		Production p = productions.get( outcome );
		return p != null && sources.containsKey( p.source );
	}

	@Override
	public Goal goal( Name name ) {
		return Goal.goal( name, this );
	}

	@Override
	public Module module( Name name ) {
		return Module.module( name, this );
	}

	@Override
	public Production produce( Artifact outcome ) {
		return Production.production( outcome, this );
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

}
