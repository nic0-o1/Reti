import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class client {

	public static final Integer BUFF_SIZE = 100;

	public static void main(String[] args) {
		Socket sClient = null;

		try {
			String host = "localhost";
			int port = 0;
			if (args.length != 2)
				throw new IllegalArgumentException("Wrong number of arguments");

			host = args[0];
			port = Integer.parseInt(args[1]);
			if (port <= 0)
				throw new IllegalArgumentException("Invalid port");

			sClient = new Socket();

			InetSocketAddress isa = new InetSocketAddress(host, port);
			InputStreamReader in = new InputStreamReader(System.in);
			sClient.connect(isa);
			BufferedReader br = new BufferedReader(in);

			while (true) {
				String line = br.readLine();
				byte[] buffer = line.getBytes();
				OutputStream out = sClient.getOutputStream();
				out.write(buffer, 0, line.length());

				byte[] read = new byte[100];
				InputStream inS = sClient.getInputStream();
				int letti = inS.read(read);
				String stampa = new String(read, 0, letti);
				System.out.println("Response: " + stampa);
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
