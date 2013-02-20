package se.jbee.build;

/**
 * {@link Producer}s are used in production rules described using a fluent interface. They are
 * usually singletons or instances that allow for some configuration that are than passed to the
 * with-clause. They are an implementation to produce the artifacts.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public interface Producer {

	/**
	 * 
	 * @param input
	 *            The set of {@link Artifacts} that is processed.
	 * @param root
	 *            The root {@link Folder} of the produced artifacts.
	 */
	void produce( Artifacts input, Folder root );

	// fluent interface example
	// in(<module...>).produce(<artifact-class>).from(<artifact-class>).with(<producer>);
}
