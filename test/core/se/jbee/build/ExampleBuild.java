package se.jbee.build;

import static se.jbee.build.Artifact._class;
import static se.jbee.build.Artifact._java;
import static se.jbee.build.Artifact.javadoc;
import se.jbee.build.produce.Javac;
import se.jbee.build.produce.Javadoc;

public class ExampleBuild
		implements Build {

	@Override
	public void build( Project project ) {
		project.produce( _class ).from( _java ).with( Javac._1_6 );
		project.produce( javadoc ).from( _java ).with( Javadoc._1_6 );

		Module main = project.module( "main" ).includes( _java );
		Module db = project.module( "db", main ).includes( _java );
		Module test = project.module( "test", main ).includes( _java );

		project.goal( "compile" ).is( _class ).mayBe( _class );
		project.goal( "test-compile" ).is( _class ).in( test );
		project.goal( "javadoc" ).is( javadoc );
	}

}
