import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class ServerThread extends Thread {
	Socket s;
	int id;
	DataInputStream in;
	DataOutputStream out;
	Users list;
	public boolean exit;
	String message;
	String name;

	public ServerThread(Socket s, Users list, int id) {
		this.s = s;
		this.id = id;
		this.list = list;
	}

	public ServerThread() {

	}

	public void run() {
		try {
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			name = in.readUTF();
			sendMessage(name + " polaczony");
		} catch (IOException io1) {
			System.out.println("Blad Server Thread");
		}
		while (true) {
			try {
				message = in.readUTF();
				sendMessage(name + ": " + message);
				if (message.equalsIgnoreCase("exit")) {
					sendMessage(name + " odlaczony");
					removeClient();
				}
			} catch (IOException io) {
				System.out.println("Error Server Thread");
				io.printStackTrace();
			}
		}
	}

	private void sendMessage(String message) {
		try {
			Iterator it = list.getUsers().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				out = new DataOutputStream(((Socket) pair.getValue()).getOutputStream());
				out.writeUTF(message);
			}
		} catch (IOException io) {
			System.out.println("blad wysylania wiadomosci");
		}
	}

	private void removeClient() {
		list.removeUser(id);
	}
}
