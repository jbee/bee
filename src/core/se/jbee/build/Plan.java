package se.jbee.build;

import java.util.ArrayList;
import java.util.Arrays;
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
	private final List<Module> modules = new ArrayList<Module>();
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
		int index = moduleIndex( name );
		return index >= 0
			? modules.get( index )
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
		int index = moduleIndex( module.name );
		if ( index < 0 ) {
			modules.add( module );
		} else {
			modules.set( index, module );
		}
		for ( Artifact a : module.artifacts() ) {
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

	private int moduleIndex( Name name ) {
		for ( int i = 0; i < modules.size(); i++ ) {
			if ( modules.get( i ).name.isEqual( name ) ) {
				return i;
			}
		}
		return -1;
	}

	public boolean canProduce( Artifact outcome ) {
		Production p = productions.get( outcome );
		return p != null && sources.containsKey( p.source );
	}

	public Step[] execution( Name goal, Name... modules ) {
		Goal g = goals.get( goal );
		List<Step> steps = new ArrayList<Step>();
		Set<Name> scope = new HashSet<Name>( Arrays.asList( modules ) );
		for ( Subgoal sg : g ) {
			Production p = productions.get( sg.outcome );
			Set<Name> required = new HashSet<Name>();
			for ( Module m : this.modules ) {
				if ( sg.concernsModule( m.name ) && ( scope.isEmpty() || scope.contains( m.name ) ) ) {
					required.add( m.name );
					addParents( required, m );
				}
			}
			for ( Module m : this.modules ) {
				if ( required.contains( m.name ) ) {
					steps.add( Step.step( m, p ) );
				}
			}
		}
		return steps.toArray( new Step[steps.size()] );
	}

	private static void addParents( Set<Name> required, Module m ) {
		for ( Module parent : m ) {
			required.add( parent.name );
			addParents( required, parent );
		}
	}
}
