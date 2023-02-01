import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String[] args) {
		ServerSocket sServer;
		Socket toClient;

		try {
			sServer = new ServerSocket(0);
			System.out.println("Indirizzo: " + sServer.getInetAddress() + " port: " + sServer.getLocalPort());

			while (true) {
				toClient = sServer.accept();
				System.out.println("Client address: " + toClient.getInetAddress() + " port: " + toClient.getPort());

				Thread t = new service(toClient);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
