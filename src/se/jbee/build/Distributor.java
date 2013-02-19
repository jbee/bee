package se.jbee.build;

public interface Distributor {

	// fluent interface example
	// distribute(binariesJar, javadocJar, sourcesJar).with(mvn);
	// distribute(javadoc).to(github-pages);
}
