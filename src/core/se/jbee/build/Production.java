package se.jbee.build;

public final class Production {

	public final Artifact input;
	public final Artifact output;
	public final Producer producer;

	private Production( Artifact input, Artifact output, Producer producer ) {
		super();
		this.input = input;
		this.output = output;
		this.producer = producer;
	}

}
