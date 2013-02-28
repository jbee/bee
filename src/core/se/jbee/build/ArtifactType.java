package se.jbee.build;

public enum ArtifactType {

	/**
	 * e.g a java file
	 */
	SOURCE,
	/**
	 * e.g. a class file
	 */
	BINARY,
	/**
	 * e.g. a jar file
	 */
	LIBRARY,
	/**
	 * e.g. javadoc
	 */
	DOCUMENTATION,
	/**
	 * e.g. a zip file containing a hole project
	 */
	ARCHIVE,
	/**
	 * e.g. a exe file
	 */
	EXECUTABLE
}
