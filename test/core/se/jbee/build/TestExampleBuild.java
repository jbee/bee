package se.jbee.build;

import static org.junit.Assert.assertTrue;
import static se.jbee.build.Artifact._class;
import static se.jbee.build.Artifact._jar;
import static se.jbee.build.Artifact._java;
import static se.jbee.build.Artifact.artifact;
import static se.jbee.build.Artifact.javadoc;
import static se.jbee.build.Name.named;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

import se.jbee.build.produce.Javac;
import se.jbee.build.produce.Javadoc;

public class TestExampleBuild {

	public static class ExampleBuild
			implements Build {

		private static final Artifact _zip = artifact( "zip", ArtifactType.ARCHIVE,
				Files.dot( "zip" ) );

		@Override
		public void build( Project project ) {
			project.produce( _jar ).from( _class ).with( Javac._1_6 );
			project.produce( _class ).from( _java ).with( Javac._1_6 );
			project.produce( _zip ).from( _jar );
			project.produce( javadoc ).from( _java ).with( Javadoc._1_6 );

			Module main = project.module( "main" ).includes( _java );
			Module db = project.module( "db", main ).includes( _java );
			Module test = project.module( "test", main ).includes( _java );

			project.goal( "compile" ).is( _class );
			project.goal( "test-compile" ).is( _class ).in( test );
			project.goal( "javadoc" ).is( javadoc ).keepByproducts();
		}
	}

	private final Schedule schedule = Bootstrap.schedule( ExampleBuild.class );

	@Test
	public void thatGoalExectionHasCorrectSequence() {
		assertGoalSequence( "compile", new String[] {}, "main", "db", "test" );
		assertGoalSequence( "compile", new String[] { "main" }, "main" );
		assertGoalSequence( "compile", new String[] { "db" }, "main", "db" );
		assertGoalSequence( "compile", new String[] { "test" }, "main", "test" );
	}

	public void assertGoalSequence( String goal, String[] modules, String... expected ) {
		Task[] steps = schedule.execution( named( goal ), named( modules ) );
		assertTrue( steps.length >= modules.length );
		LinkedList<String> expectedSequence = new LinkedList<String>( Arrays.asList( expected ) );
		int i = 0;
		while ( i < steps.length && !expectedSequence.isEmpty() ) {
			Task step = steps[i];
			if ( step.production.sources.all( ArtifactType.SOURCE )
					&& step.production.outcome.type == ArtifactType.BINARY
					&& step.module.name.isEqual( named( expectedSequence.get( 0 ) ) ) ) {
				expectedSequence.pollFirst();
			}
			i++;
		}
		assertTrue( expectedSequence.isEmpty() );
	}
}
