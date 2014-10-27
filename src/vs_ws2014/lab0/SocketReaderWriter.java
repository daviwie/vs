package vs_ws2014.lab0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Reads and writes to a socket. Used to register for Distributed Systems as
 * part of Lab0.
 * 
 * @author David Wietstruk 0706376
 *
 */
public class SocketReaderWriter {

	private static SocketReaderWriter instance;

	private SocketReaderWriter() {

	}

	/**
	 * Returns an instance of SocketReaderWriter.
	 * 
	 * @return
	 */
	public static SocketReaderWriter getInstance() {
		if (instance == null)
			instance = new SocketReaderWriter();

		return instance;
	}

	/**
	 * Connects to a url:port combination, writes to the socket, reads the
	 * server input to the socket and also handles user input to and from the
	 * socket.
	 * 
	 * @param url
	 * @param port
	 * @throws IOException
	 */
	public void connect(String url, int port) throws IOException {
		Socket regSocket = null;
		PrintWriter userOutputWriter = null;
		BufferedReader in = null;
		BufferedReader stdIn = null;

		try {
			/*
			 * From user/program perspective, we are outputting data to the
			 * server and the server is inputting data back to the user/program.
			 */
			regSocket = new Socket(url, port);
			/*
			 * Writes the actual user output to the socket.
			 */
			userOutputWriter = new PrintWriter(regSocket.getOutputStream(), true);
			/*
			 * Readers the socket/server's output.
			 */
			in = new BufferedReader(new InputStreamReader(regSocket.getInputStream()));
			/*
			 * Reads user cmd line input.
			 */
			stdIn = new BufferedReader(new InputStreamReader(System.in));

			String userCmd;
			/*
			 * stdIn.readLine() listens for user input and reads until an end of
			 * line character is received. The outputWriter writes the socket
			 * output stream, sends userCmd to the server and in.readLine()
			 * prints the server response received via the socket's input
			 * stream.
			 */
			while ((userCmd = stdIn.readLine()) != null) {
				userOutputWriter.println(userCmd);
				System.out.println("dsLab: " + in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stdIn != null)
				stdIn.close();
			if (userOutputWriter != null)
				userOutputWriter.close();
			if (in != null)
				in.close();
			if (regSocket != null)
				regSocket.close();
		}
	}

}
