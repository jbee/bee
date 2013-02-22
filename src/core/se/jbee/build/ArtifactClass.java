package se.jbee.build;

/**
 * A logical kind of artifacts.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class ArtifactClass {

	public static ArtifactClass java = new ArtifactClass( ArtifactType.SOURCE_CODE, "*.java" );
	public static ArtifactClass c1ass = new ArtifactClass( ArtifactType.BINARY_CODE, "*.class" );

	//TODO make a class for the file pattern

	public static ArtifactClass artifactClass( ArtifactType type, String pattern ) {
		return new ArtifactClass( type, pattern );
	}

	private final String pattern;
	public final ArtifactType type;

	private ArtifactClass( ArtifactType type, String pattern ) {
		super();
		this.pattern = pattern;
		this.type = type;
	}

	@Override
	public String toString() {
		return pattern;
	}

}
