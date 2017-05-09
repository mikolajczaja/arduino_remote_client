import java.net.*;
import java.io.*;

public class TCPClient {

	public void send(String serverName, int port, Message message)
			throws InterruptedException {
		
		try {
			Socket client = new Socket(serverName, port);
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF(message.toString());
			
			client.close();
			} catch (IOException e) {
					e.printStackTrace();
					}

	}
}
