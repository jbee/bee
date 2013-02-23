package se.jbee.build;

import static org.junit.Assert.assertTrue;
import static se.jbee.build.Name.named;

import java.util.Arrays;

import org.junit.Test;

public class TestExampleBuild {

	@Test
	public void thatContextCanBeCreated()
			throws Exception {
		Plan plan = new Plan();
		ExampleBuild.class.newInstance().build( Project.project( plan ) );
		assertTrue( plan.canProduce( Artifact._class ) );
		System.out.println( Arrays.toString( plan.execution( named( "compile" ) ) ) );
		System.out.println( Arrays.toString( plan.execution( named( "javadoc" ) ) ) );
		System.out.println( Arrays.toString( plan.execution( named( "compile" ), named( "main" ) ) ) );
		System.out.println( Arrays.toString( plan.execution( named( "compile" ), named( "db" ) ) ) );
	}
}
