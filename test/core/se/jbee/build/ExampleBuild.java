package se.jbee.build;

import static se.jbee.build.Artifact.c1ass;
import static se.jbee.build.Artifact.java;

public class ExampleBuild
		implements Build {

	@Override
	public void build( Project project ) {
		Module main = project.module( "main" ).includes( java );
		Module db = project.module( "db" ).includes( java ).uses( main );

		project.goal( "compile" ).is( c1ass ).in( main, db );
	}

}
