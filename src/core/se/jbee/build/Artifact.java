package se.jbee.build;

import static se.jbee.build.Name.named;

/**
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Artifact {

	public static final Artifact class_vx = artifact( "classes", ArtifactType.BINARY_CODE );
	public static final Artifact java_vx = artifact( "java", ArtifactType.SOURCE_CODE );
	public static final Artifact javadoc_vx = artifact( "javadoc", ArtifactType.DOCUMENTATION );

	public static Artifact artifact( String name, ArtifactType type ) {
		return new Artifact( named( name ), type, Version.ANY );
	}

	public final Name name;
	public final ArtifactType type;
	public final Version version;

	private Artifact( Name name, ArtifactType type, Version version ) {
		super();
		this.name = name;
		this.type = type;
		this.version = version;
	}

}
