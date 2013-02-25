package se.jbee.build;

/**
 * Represents the state of a concrete build plan that can be expressed as a serious of {@link Task}.
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
interface Schedule {

	Task[] execution( Name goal, Name... modules );

}