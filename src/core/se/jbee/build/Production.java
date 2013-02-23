package se.jbee.build;

public final class Production {

	public static Production production( Artifact outcome, Productions productions ) {
		return new Production( productions, outcome, outcome, null );
	}

	public static interface Productions {

		void update( Production production );
	}

	public final Artifact source;
	public final Artifact outcome;
	public final Producer producer;
	private final Productions productions;

	private Production( Productions productions, Artifact source, Artifact outcome,
			Producer producer ) {
		super();
		this.source = source;
		this.outcome = outcome;
		this.producer = producer;
		this.productions = productions;
		productions.update( this );
	}

	public Production from( Artifact source ) {
		return new Production( productions, source, outcome, producer );
	}

	public Production with( Producer producer ) {
		return new Production( productions, source, outcome, producer );
	}

	@Override
	public String toString() {
		return source + " -> " + outcome + " [" + producer + "]";
	}
}
