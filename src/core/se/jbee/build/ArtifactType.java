package se.jbee.build;

public enum ArtifactType {

	/**
	 * Sources are expected to exist in the src folders of the different modules.
	 * 
	 * e.g a java file.
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
