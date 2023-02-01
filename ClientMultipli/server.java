package ClientMultipli;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class server {
	public static final int MAX_CONN = 5;

	public static void main(String[] args) throws IOException {
		List<Socket> sockets = new ArrayList<>();
		ServerSocket sServer = new ServerSocket(0);
		System.out.println("Server address: " + sServer.getInetAddress() + " port: " + sServer.getLocalPort());
		int index = 0;

		int i = 0;
		while (true) {
			try {
				sServer.setSoTimeout(3);

				while (index < MAX_CONN) {
					sockets.add(sServer.accept());
					index++;


					while(index > 0){
						System.out.println("Client address: "+ sockets.get(i).getInetAddress()+ " port: "+ sockets.get(i).getPort());


						try {
							sockets.get(i).setSoTimeout(4);

							InputStream ic = sockets.get(i).getInputStream();

							while(true){
								int letti = ic.read(buf);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}



			} catch (SocketTimeoutException e) {
				System.out.println("Timeout expired");
			} catch (IOException e) {
				System.out.println("Socket server error");
				e.printStackTrace();
			}
		}
	}
}
