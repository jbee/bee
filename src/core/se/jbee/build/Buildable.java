package se.jbee.build;

public interface Buildable {

	Activity[] activities( Name goal, Name... modules );
}
