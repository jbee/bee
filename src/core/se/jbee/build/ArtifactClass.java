package se.jbee.build;

/**
 * A logical kind of artifacts.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class ArtifactClass {

	public static final ArtifactClass JAVA = artifactClass( "java", true );
	public static final ArtifactClass CLASS = artifactClass( "class", false );
	public static final ArtifactClass JAR = artifactClass( "jar", false );

	public static ArtifactClass artifactClass( String fileExtension, boolean source ) {
		return new ArtifactClass( fileExtension, source );
	}

	private final String fileExtension;
	private final boolean source;

	private ArtifactClass( String fileExtension, boolean source ) {
		super();
		this.fileExtension = fileExtension;
		this.source = source;
	}

	@Override
	public String toString() {
		return "*." + fileExtension;
	}

	public boolean isSource() {
		return source;
	}
}
