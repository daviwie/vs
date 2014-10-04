package vs_ws2014.lab0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class RegisterClient {
	static String url = "stockholm.vitalab.tuwien.ac.at";
	static int port = 9000;
	// String login = "!login 0706376 23704";
	// String exit = "!exit";

	public static void main(String[] args) throws IOException {
		connect0();
	}

	private static void connect0() throws IOException {
		Socket regSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader response = null;
		
		try {
			regSocket = new Socket(url, port);
			out = new PrintWriter(regSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(regSocket.getInputStream()));
			response = new BufferedReader(new InputStreamReader(System.in));

			String userInput;
			while ((userInput = response.readLine()) != null) {
				out.println(userInput);
				System.out.println("dsLab: " + in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(regSocket != null)
				regSocket.close();
			if(in != null)
				in.close();
			if(out != null)
				out.close();
			if(response != null)
				response.close();
		}
	}
}
