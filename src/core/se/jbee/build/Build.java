package se.jbee.build;


/**
 * This is implemented by a concrete project to describe the build using the builder.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public interface Build {

	void goals();

	void productions();

	//void libraries();
}
