package vs_ws2014.lab0;

import java.io.IOException;

public class RegisterClient {

	/**
	 * Commands:
	 * 
	 * !login 0706376 23704
	 * 
	 * !exit
	 */
	public static void main(String[] args) throws IOException {
		SocketReaderWriter.getInstance().connect("stockholm.vitalab.tuwien.ac.at", 9000);
	}
}
