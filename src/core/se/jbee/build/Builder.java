package se.jbee.build;

public interface Builder {

	Module module( Name name, Module... parents );

	Goal goal( Name name );

	Production produce( Artifact outcome );

}