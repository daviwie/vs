package vs_ws2014.lab0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class RegisterClient {
	//URL url = new URL("stockholm.vitalab.tuwien.ac.at:9000");
	static String url = "stockholm.vitalab.tuwien.ac.at";
	static int port = 9000;
	String login = "!login 0706376 23704";
	String exit = "!exit";
	static Socket regSocket;
	
	public static void main(String args[]){
		try{
			Socket regSocket = new Socket(url,port);
			PrintWriter out = new PrintWriter(regSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(regSocket.getInputStream()));
			BufferedReader response = new BufferedReader(new InputStreamReader(System.in));
			
			String userInput;
			while((userInput = response.readLine()) != null){
				out.println(userInput);
				System.out.println("dsLab: " + in.readLine());
			}
			regSocket.close();
		} catch (Exception e){
			
		}
	}
}
