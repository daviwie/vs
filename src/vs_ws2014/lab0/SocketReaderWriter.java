package vs_ws2014.lab0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketReaderWriter {
	private static SocketReaderWriter instance;
	
	private SocketReaderWriter() {
		
	}
	
	public static SocketReaderWriter getInstance() {
		if(instance == null)
			instance = new SocketReaderWriter();
		
		return instance;
	}
	
	public void connect(String url, int port) throws IOException {
		Socket regSocket = null;
		PrintWriter userOutputWriter = null;
		BufferedReader serverOutputReader = null;
		BufferedReader userInputReader = null;

		try {
			/**
			 * From user/program perspective, we are outputting data to the
			 * server and the server is inputting data back to the user/program.
			 */
			regSocket = new Socket(url, port);
			userOutputWriter = new PrintWriter(regSocket.getOutputStream(), true);
			serverOutputReader = new BufferedReader(new InputStreamReader(regSocket.getInputStream()));
			userInputReader = new BufferedReader(new InputStreamReader(System.in));

			String userCmd;
			while ((userCmd = userInputReader.readLine()) != null) {
				userOutputWriter.println(userCmd);
				System.out.println("dsLab: " + serverOutputReader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (userInputReader != null)
				userInputReader.close();
			if (userOutputWriter != null)
				userOutputWriter.close();
			if (serverOutputReader != null)
				serverOutputReader.close();
			if (regSocket != null)
				regSocket.close();
		}
	}

}
