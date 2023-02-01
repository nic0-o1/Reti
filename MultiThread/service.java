import java.io.InputStream;
import java.net.Socket;

public class service extends Thread {

	private Socket toClient;

	public service(Socket toClient) {
		this.toClient = toClient;
	}

	public void run() {
		int dim_buf = 100;
		byte[] buff = new byte[dim_buf];

		while (true) {
			try {
				InputStream is = toClient.getInputStream();
				int read = is.read(buff);

				if (read > 0) {
					String rec = new String(buff, 0, read);
					System.out.println(
							"Received: " + rec + " da: " + toClient.getInetAddress() + ":" + toClient.getPort());
				} else {
					toClient.close();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
