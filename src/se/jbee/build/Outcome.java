package se.jbee.build;

/**
 * A {@link Outcome} is a state or result (similar to an ant target). It is described by the set of
 * artifacts that are given and those that are not given (any longer).
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public interface Outcome {
	// fluent interface example: 
	// outcome("jar").has(jar,class); // jar and class are production rule instance references
	// outcome("compile).has(class);
	// outcome("dist").has(jar, javadoc);
	// outcome("clean").has(); // implicitly given by this illustrates what clean means.
}
