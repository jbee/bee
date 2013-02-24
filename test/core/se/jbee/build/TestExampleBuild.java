package se.jbee.build;

import static org.junit.Assert.assertTrue;
import static se.jbee.build.Artifact._class;
import static se.jbee.build.Artifact._java;
import static se.jbee.build.Artifact.javadoc;
import static se.jbee.build.Name.named;

import org.junit.Test;

import se.jbee.build.produce.Javac;
import se.jbee.build.produce.Javadoc;

public class TestExampleBuild {

	public static class ExampleBuild
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

	private final Plan plan = Bootstrap.plan( ExampleBuild.class );

	@Test
	public void thatGoalExectionHasCorrectSequence() {
		assertGoalSequence( "compile", new String[] {}, "main", "db", "test" );
		assertGoalSequence( "compile", new String[] { "main" }, "main" );
		assertGoalSequence( "compile", new String[] { "db" }, "main", "db" );
		assertGoalSequence( "compile", new String[] { "test" }, "main", "test" );
	}

	public void assertGoalSequence( String goal, String[] modules, String... expected ) {
		Step[] steps = plan.execution( named( goal ), named( modules ) );
		assertTrue( steps.length >= modules.length );
		for ( int i = 0; i < steps.length; i++ ) {
			assertTrue( steps[i].module.name.isEqual( named( expected[i] ) ) );
		}
	}
}
