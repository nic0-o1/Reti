import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class client {

	public static final Integer BUFF_SIZE = 100;

	public static void main(String[] args) {
		DatagramSocket sClient = null;

		try {
			String host = "localhost";
			int port = 0;
			if (args.length != 2)
				throw new IllegalArgumentException("Wrong number of arguments");

			host = args[0];
			port = Integer.parseInt(args[1]);
			if (port <= 0)
				throw new IllegalArgumentException("Invalid port");

			sClient = new DatagramSocket();

			InetSocketAddress isa = new InetSocketAddress(host, port);
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);

			while (true) {
				byte[] buffer = br.readLine().getBytes();

				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				// DatagramPacket dp = new DatagramPacket(buffer, buffer.length, host, port);
				dp.setSocketAddress(isa);
				sClient.send(dp);

				byte[] rec = new byte[1024];
				DatagramPacket received = new DatagramPacket(rec, rec.length);
				sClient.receive(received);
				System.out.println("Response: " + new String(received.getData()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sClient.close();
			} catch (Exception e) {
				System.out.println("Errore chiusura");
				e.printStackTrace();
			}
		}
	}
}
