package se.jbee.build;

/**
 * Represents the state of a concrete build plan that can be expressed as a serious of {@link Step}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
interface Schedule {

	Step[] execution( Name goal, Name... modules );

}