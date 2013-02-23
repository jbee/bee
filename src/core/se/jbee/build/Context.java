package se.jbee.build;

import se.jbee.build.Goal.Goals;
import se.jbee.build.Module.Modules;
import se.jbee.build.Production.Productions;

public class Context
		implements Builder, Goals, Modules, Productions {

	@Override
	public Goal goal( Name name ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module module( Name name ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Production produce( Artifact outcome ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update( Goal goal ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update( Module module ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update( Production production ) {
		// TODO Auto-generated method stub

	}

}
