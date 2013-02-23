package se.jbee.build;

import static se.jbee.build.Artifact.class_vx;
import static se.jbee.build.Artifact.java_vx;
import se.jbee.build.produce.Javac;

public class ExampleBuild
		implements Build {

	@Override
	public void build( Project project ) {
		project.produce( class_vx ).from( java_vx ).with( Javac._1_6 );

		Module main = project.module( "main" ).includes( java_vx );
		Module db = project.module( "db", main ).includes( java_vx );
		Module test = project.module( "test", main ).includes( java_vx );

		project.goal( "compile" ).is( class_vx ).mayBe( class_vx );
		project.goal( "test-compile" ).is( class_vx ).in( test );
	}

}
