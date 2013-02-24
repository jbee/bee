package se.jbee.build;

import java.io.OutputStream;

public class Instruction {

	public final Folder src;
	public final Folder target;
	public final Files included;
	public final OutputStream log;

	public Instruction( Folder src, Folder target, Files included, OutputStream log ) {
		super();
		this.src = src;
		this.target = target;
		this.included = included;
		this.log = log;
	}

}
