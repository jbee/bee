package se.jbee.build.produce;

import java.io.PrintWriter;

import se.jbee.build.Instruction;
import se.jbee.build.Producer;

import com.sun.tools.javac.Main;

public class Javac
		implements Producer {

	public static final Producer _1_6 = new Javac();

	@Override
	public void produce( Instruction instruction ) {
		// TODO Auto-generated method stub

		String[] cmdLineArgs = {};
		Main.compile( cmdLineArgs, new PrintWriter( System.out ) );
	}

}
