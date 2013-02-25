package se.jbee.build;

public enum ArtifactMode {

	/**
	 * The artifact is not created per module but combines all (included) modules into a project
	 * 'global' artifact. Note that an 'artifact' in this sense can be multiple files.
	 */
	PROJECT,

	/**
	 * The artifact is recreated. That means a 'clean' is done before producing it.
	 */
	CLEAN
}
