package se.jbee.build;

/**
 * A pattern patches files.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public class Files {

	public static Files dot( String fileExtension ) {
		return new Files( "*." + fileExtension );
	}

	private final String pattern;

	private Files( String pattern ) {
		super();
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return pattern;
	}
}
