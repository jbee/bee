package se.jbee.build;

import java.util.regex.Pattern;

public final class Name {

	private static final Pattern VALID = Pattern.compile( "[a-z][-a-z]*" );

	public static Name named( String name ) {
		if ( !VALID.matcher( name ).matches() ) {
			throw new IllegalArgumentException( "Not a valid name: " + name );
		}
		return new Name( name );
	}

	private final String name;

	private Name( String name ) {
		super();
		this.name = name.intern();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals( Object obj ) {
		return obj instanceof Name && isEqual( (Name) obj );
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public boolean isEqual( Name other ) {
		return name == other.name; // we intern them so it is ok
	}
}
