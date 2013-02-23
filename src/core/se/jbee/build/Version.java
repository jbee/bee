package se.jbee.build;

public final class Version {

	public static final Version ANY = new Version( "x.x" );

	private final String no;

	private Version( String no ) {
		super();
		this.no = no.intern();
	}

	@Override
	public int hashCode() {
		return no.hashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		return obj instanceof Version && isEqual( (Version) obj );
	}

	public boolean isEqual( Version other ) {
		return no == other.no; // ok - we intern() them
	}

	@Override
	public String toString() {
		return "v" + no;
	}
}
