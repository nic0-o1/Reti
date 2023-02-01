import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class server {
	public static final Integer BUFF_SIZE = 100;

	public static void main(String[] args) {
		DatagramSocket sServer = null;

		try {
			sServer = new DatagramSocket();
			System.out.println("Indirizzo: " + sServer.getLocalAddress() + " porta: " + sServer.getLocalPort());

			while (true) {
				byte[] buffer = new byte[BUFF_SIZE];
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

				sServer.receive(dp);
				System.out.println("Connesso: " + dp.getAddress().getHostAddress() + " Porta: " + dp.getPort());
				String read = new String(dp.getData());
				System.out.println("Ricevuto: " + read);

				byte[] buf = new byte[BUFF_SIZE];

				buf = read.getBytes();
				DatagramPacket echo = new DatagramPacket(buf, buf.length, dp.getAddress(), dp.getPort());
				sServer.send(echo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sServer.close();
			} catch (Exception e) {
				System.out.println("Errore chiusura");
				e.printStackTrace();
			}
		}
	}
}
