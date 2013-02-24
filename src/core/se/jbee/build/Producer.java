package se.jbee.build;

/**
 * {@link Producer}s are used in production rules described using a fluent interface. They are
 * usually singletons or instances that allow for some configuration that are than passed to the
 * with-clause. They are an implementation to produce the artifacts.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public interface Producer {

	void produce( Instruction instruction );

}
