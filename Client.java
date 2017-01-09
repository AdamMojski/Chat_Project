import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	static DataOutputStream out;
	static BufferedReader in;
	static Socket s;
	static String message;
	static String name;
	static ClientThread client;

	public static void main(String[] args) {
		start();
		try {
			System.out.print("Podaj imie: ");
			name = in.readLine();
			client = new ClientThread(s, name);
			client.start();
			out.writeUTF(name);
		} catch (IOException io) {
			System.out.println("blad w nazwie");
		}
		while (true) {
			try {
				message = in.readLine();
				out.writeUTF(message);
				if (message.equalsIgnoreCase("exit")) {
				}
			} catch (IOException io) {
				System.out.println("Blad w while");
			}
		}
	}

	private static void start() {
		try {
			s = new Socket("127.0.0.1", 4321);
			out = new DataOutputStream(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
