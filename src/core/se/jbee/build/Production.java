package se.jbee.build;

public final class Production {

	public static Production production( Artifact outcome, Productions productions ) {
		return new Production( productions, Artifacts.NONE, outcome, null );
	}

	public static interface Productions {

		void update( Production production );
	}

	public final Artifacts sources;
	public final Artifact outcome;
	public final Producer producer;
	private final Productions productions;

	private Production( Productions productions, Artifacts sources, Artifact outcome,
			Producer producer ) {
		super();
		this.sources = sources;
		this.outcome = outcome;
		this.producer = producer;
		this.productions = productions;
		productions.update( this );
	}

	public Production from( Artifact source ) {
		return from( Artifacts.artifacts( source ) );
	}

	public Production from( Artifacts sources ) {
		return new Production( productions, sources, outcome, producer );
	}

	public Production with( Producer producer ) {
		return new Production( productions, sources, outcome, producer );
	}

	@Override
	public String toString() {
		return sources + " -> " + outcome + " [" + producer + "]";
	}
}
