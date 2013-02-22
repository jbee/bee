package se.jbee.build;

import static se.jbee.build.Artifact.c1ass;
import static se.jbee.build.Artifact.java;
import static se.jbee.build.Goal.goal;
import static se.jbee.build.Module.module;

public class ExampleBuild
		implements Build {

	@Override
	public void goals() {
		Module main = module( "main" ).includes( java );
		Module db = module( "db" ).includes( java ).uses( main );

		Goal compile = goal( "compile" ).is( c1ass ).in( main, db );
	}

	@Override
	public void productions() {
		// TODO Auto-generated method stub

	}

}
