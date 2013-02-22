package se.jbee.build;

/**
 * An {@link Artifact} is a concrete variant of an {@link ArtifactClass}.
 * 
 * For example a <ocde>.class</code> file for a specific java version is an {@linkplain Artifact} of
 * the {@link ArtifactClass} of any <ocde>.class</code> file (in any version).
 * 
 * @author Jan Bernitt (jan@jbee.se)
 * 
 */
public final class Artifact {

	public static final Artifact c1ass = artifact( ArtifactClass.c1ass );
	public static final Artifact java = artifact( ArtifactClass.java );

	public static Artifact artifact( ArtifactClass clazz ) {
		return new Artifact( clazz, clazz.type );
	}

	public final ArtifactClass clazz;
	public final ArtifactType type;

	private Artifact( ArtifactClass clazz, ArtifactType type ) {
		super();
		this.clazz = clazz;
		this.type = type;
	}

}
