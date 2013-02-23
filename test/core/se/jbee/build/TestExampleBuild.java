package se.jbee.build;

import static org.junit.Assert.assertTrue;
import static se.jbee.build.Name.named;

import org.junit.Test;

public class TestExampleBuild {

	@Test
	public void thatContextCanBeCreated()
			throws Exception {
		Plan plan = new Plan();
		ExampleBuild.class.newInstance().build( Project.project( plan ) );
		assertTrue( plan.canProduce( Artifact._class ) );
		Step[] steps = plan.execution( named( "compile" ) );
		steps = plan.execution( named( "javadoc" ) );
		System.out.println( steps );
	}
}
