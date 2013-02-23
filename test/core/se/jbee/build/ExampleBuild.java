package se.jbee.build;

import static se.jbee.build.Artifact.class_vx;
import static se.jbee.build.Artifact.java_vx;

public class ExampleBuild
		implements Build {

	@Override
	public void build( Project project ) {
		Module main = project.module( "main" ).includes( java_vx );
		Module db = project.module( "db" ).includes( java_vx ).uses( main );
		Module test = project.module( "test" ).includes( java_vx ).uses( main );

		project.goal( "compile" ).is( class_vx ).in( main, db );
		project.goal( "test-compile" ).is( class_vx ).in( test );
	}

}