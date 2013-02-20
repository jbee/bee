package se.jbee.build;

/**
 * {@link Producer}s are used in production rules described using a fluent interface. They are
 * usually singletons or instances that allow for some configuration that are than passed to the
 * with-clause. They are an implementation to produce the artifacts.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public interface Producer {

	// fluent interface example
	// in(<module>).produce(class).from(java).with(javac); // class and java are artifacts (that can be filtered as well) 
	// in(main, ui).produce(jar).from(class).with(jar);
	// produce(html,css).from(java).with(javadoc);
	// produce(java).from(java).with(ensureFileHeader(licenceHeaderFile));

	void produce( Production production, Artifacts sources );

	// maybe produce...with should return something (Artifacts) that is later used as input to distributions  
}
