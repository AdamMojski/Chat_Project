
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
	Socket s;
	DataInputStream in;
	String message, name;

	public ClientThread(Socket s, String name) {
		this.s = s;
		this.name = name;
	}

	public ClientThread() {

	}

	public void run() {
		try {
			in = new DataInputStream(s.getInputStream());
			while (true) {
				message = in.readUTF();
				System.out.println(message);
				if (message.equalsIgnoreCase("uzytkownik: " + name + " odlaczony")) {
					s.close();
				}
			}

		} catch (IOException io) {
			System.out.println("blad client thread");
			io.printStackTrace();
		}
	}
}
