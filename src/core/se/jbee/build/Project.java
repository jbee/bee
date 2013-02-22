package se.jbee.build;

public interface Project {

	Module module( String name );

	Goal goal( String name );
}
