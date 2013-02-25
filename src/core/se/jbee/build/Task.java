package se.jbee.build;

/**
 * A {@link Task} during execution of a {@link Build} {@link Goal}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public class Task {

	//TODO later on a task has to become an interface since there are also distribution tasks and such
	// or distribution is done over production ?!

	public static Task task( Module module, Production production ) {
		return new Task( 0, module, production );
	}

	/**
	 * A stage a period where all {@link Task} with it can run in parallel.
	 */
	public final int stage;
	public final Module module;
	public final Production production;

	private Task( int stage, Module module, Production production ) {
		super();
		this.stage = stage;
		this.module = module;
		this.production = production;
	}

	@Override
	public String toString() {
		return module.name + ": " + production;
	}
}
