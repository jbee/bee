package se.jbee.build;

/**
 * Results from a production rule description.
 * 
 * <pre>
 * in([module...]).produce([artifact-class]).from([artifacts]).with([producter]);
 * </pre>
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public final class Production {

	public static Production production( Src src, Module module, Target target ) {
		return new Production( src, module, target );
	}

	public final Src src;
	public final Module module;
	public final Target target;

	private Production( Src src, Module module, Target target ) {
		super();
		this.src = src;
		this.module = module;
		this.target = target;
	}

}
