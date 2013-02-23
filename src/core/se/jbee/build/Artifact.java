package se.jbee.build;

import static se.jbee.build.Name.named;

/**
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Artifact {

	public static final Artifact _class = artifact( "classes", ArtifactType.BINARY_CODE, "*.class" );
	public static final Artifact _java = artifact( "java", ArtifactType.SOURCE_CODE, "*.java" );
	public static final Artifact javadoc = artifact( "javadoc", ArtifactType.DOCUMENTATION,
			"*.html" );

	public static Artifact artifact( String name, ArtifactType type, String filePattern ) {
		return new Artifact( named( name ), type, Version.ANY, filePattern );
	}

	public final Name name;
	public final ArtifactType type;
	public final Version version;
	public final String filePattern;

	private Artifact( Name name, ArtifactType type, Version version, String filePattern ) {
		super();
		this.name = name;
		this.type = type;
		this.version = version;
		this.filePattern = filePattern;
	}

	@Override
	public boolean equals( Object obj ) {
		return obj instanceof Artifact && isEqual( (Artifact) obj );
	}

	@Override
	public int hashCode() {
		return name.hashCode() ^ version.hashCode();
	}

	public boolean isEqual( Artifact other ) {
		return type == other.type && name.isEqual( other.name ) && version.isEqual( other.version );
	}

	@Override
	public String toString() {
		return name + "[" + version + "] " + type;
	}
}
