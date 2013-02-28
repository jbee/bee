package se.jbee.build;

import static se.jbee.build.Name.named;

import java.util.EnumSet;

/**
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Artifact {

	private static final EnumSet<ArtifactMode> DEFAULT_MODES = EnumSet.of( ArtifactMode.CLEAN );

	public static final Artifact _class = artifact( "classes", ArtifactType.BINARY,
			Files.dot( "class" ) );
	public static final Artifact _java = artifact( "java", ArtifactType.SOURCE,
			Files.dot( "java" ) );
	public static final Artifact javadoc = artifact( "javadoc", ArtifactType.DOCUMENTATION,
			Files.dot( "html" ) );
	public static final Artifact _jar = artifact( "jar", ArtifactType.ARCHIVE, Files.dot( "jar" ) ).project();

	public static Artifact artifact( String name, ArtifactType type, Files filePattern ) {
		return new Artifact( named( name ), type, Version.ANY, filePattern, DEFAULT_MODES );
	}

	public final Name name;
	public final ArtifactType type;
	public final Version version;
	public final Files pattern;
	private final EnumSet<ArtifactMode> modes;

	private Artifact( Name name, ArtifactType type, Version version, Files pattern,
			EnumSet<ArtifactMode> modes ) {
		super();
		this.name = name;
		this.type = type;
		this.version = version;
		this.pattern = pattern;
		this.modes = modes;
	}

	public Artifact project() {
		return withModes( modesPlus( ArtifactMode.PROJECT ) );
	}

	public Artifact module() {
		return withModes( modusMinus( ArtifactMode.PROJECT ) );
	}

	public Artifact clean() {
		return withModes( modesPlus( ArtifactMode.CLEAN ) );
	}

	public Artifact dirty() {
		return withModes( modusMinus( ArtifactMode.CLEAN ) );
	}

	private Artifact withModes( EnumSet<ArtifactMode> modes ) {
		return new Artifact( name, type, version, pattern, modes );
	}

	public boolean is( ArtifactMode mode ) {
		return modes.contains( mode );
	}

	private EnumSet<ArtifactMode> modesPlus( ArtifactMode mode ) {
		EnumSet<ArtifactMode> copy = modes.clone();
		copy.add( mode );
		return copy;
	}

	private EnumSet<ArtifactMode> modusMinus( ArtifactMode mode ) {
		EnumSet<ArtifactMode> copy = modes.clone();
		copy.remove( mode );
		return copy;
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
		return type == other.type && name.isEqual( other.name ) && version.isEqual( other.version )
				&& modes.equals( other.modes );
	}

	@Override
	public String toString() {
		return name + "[" + version + "] " + type + "(" + modes.toString() + ")";
	}
}
