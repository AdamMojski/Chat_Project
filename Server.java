import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static ServerSocket serverSocket;
	static Users list;
	static int i;

	public static void main(String[] args) {
		try {
			list = new Users();
			serverSocket = new ServerSocket(4321);
			System.out.println("Wlaczono server");
			while (true) {
				Socket s = serverSocket.accept();
				list.addUser(i, s);
				ServerThread st = new ServerThread(s, list, i);
				st.start();
				i++;
			}
		} catch (IOException io) {
			System.out.println("Blad serwera");
		}
	}

}
