package se.jbee.build;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestExampleBuild {

	@Test
	public void thatContextCanBeCreated()
			throws Exception {
		Plan context = new Plan();
		ExampleBuild.class.newInstance().build( Project.project( context ) );
		assertTrue( context.canProduce( Artifact.class_vx ) );
	}
}
