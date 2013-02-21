package se.jbee.build.produce;

import java.io.PrintWriter;

import se.jbee.build.Artifacts;
import se.jbee.build.Folder;
import se.jbee.build.Producer;

import com.sun.tools.javac.Main;

public class Javac
		implements Producer {

	@Override
	public void produce( Artifacts input, Folder root ) {
		// TODO Auto-generated method stub

		String[] cmdLineArgs = {};
		Main.compile( cmdLineArgs, new PrintWriter( System.out ) );
	}

}
