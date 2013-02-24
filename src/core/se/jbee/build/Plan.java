package se.jbee.build;

interface Plan {

	Step[] execution( Name goal, Name... modules );

}