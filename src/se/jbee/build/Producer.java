package se.jbee.build;

public interface Producer {
	// fluent interface example
	// in(<module>).produce(class).from(java).with(javac); // class and java are artifacts (that can be filtered as well) 
	// in(main, ui).produce(jar).from(class).with(jar);
	// produce(javadoc).from(java).with(javadoc);
	// produce(java).from(java,licenceHeader).with(ensureFileHeader);
}
