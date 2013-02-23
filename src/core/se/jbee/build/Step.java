package se.jbee.build;

/**
 * A {@link Step} during execution of a {@link Build} {@link Goal}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public class Step {

	public static Step step( Module module, Production production ) {
		return new Step( module, production );
	}

	public final Module module;
	public final Production production;

	private Step( Module module, Production production ) {
		super();
		this.module = module;
		this.production = production;
	}

}
