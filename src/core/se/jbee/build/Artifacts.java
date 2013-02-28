package se.jbee.build;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A list of artifacts.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Artifacts
		implements Iterable<Artifact> {

	public static final Artifacts NONE = artifacts( new Artifact[0] );

	public static Artifacts artifacts( Artifact... artifacts ) {
		return new Artifacts( artifacts );
	}

	private final Artifact[] artifacts;

	private Artifacts( Artifact[] artifacts ) {
		super();
		this.artifacts = artifacts;
	}

	@Override
	public Iterator<Artifact> iterator() {
		return Arrays.asList( artifacts ).iterator();
	}

	@Override
	public String toString() {
		return Arrays.toString( artifacts );
	}

}
