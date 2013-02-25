package se.jbee.build;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * On linux use this to pass a message to this build server.
 * 
 * <pre>
 * echo "message" > /dev/tcp/localhost/9999
 * </pre>
 * 
 * @author Jan Bernitt (jan@jbee.se)
 */
public class Server {

	public static void main( String[] args )
			throws Exception {
		ServerSocket acceptor = new ServerSocket( 9999 );
		System.out.println( "On port " + acceptor.getLocalPort() );
		while ( true ) {
			Socket client = acceptor.accept();
			BufferedReader in = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
			System.out.println( in.readLine() );
			client.close();
		}

	}
}
