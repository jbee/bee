package se.jbee.build;

public interface Producer {
	// fluent interface example
	// produce(class).from(java).with(javac); // class and java are artifacts (that can be filtered as well) 
	// produce(jar).from(class).with(jar);
	// produce(javadoc).from(java).with(javadoc);
}
