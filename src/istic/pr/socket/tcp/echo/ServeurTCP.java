//...

package istic.pr.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {

	public static void main(String[] args) throws IOException {
		int port = 9999;
		ServerSocket socketDeBienvenue = new ServerSocket(port);

		while (true) {
			try {
				Socket socketDeConnection = socketDeBienvenue.accept();
				traiterSocketCliente(socketDeConnection);
			} catch (IOException e) {
				System.out.println("Erreur avec la socket");
				break;
			}
		}
		socketDeBienvenue.close();

		// Dans une boucle, pour chaque socket clientes, appeler traiterSocketCliente

	}

	public static void traiterSocketCliente(Socket socketVersUnClient) {
		try {
			BufferedReader liseur = creerReader(socketVersUnClient);
			PrintWriter ecriveur = creerPrinter(socketVersUnClient);
			while (true) {
				String message = recevoirMessage(liseur);
				if (message == null) {
					break;
				}
				System.out.println("Serveur : message reçu : " + message);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				envoyerMessage(ecriveur, message);
			}
			socketVersUnClient.close();

		} catch (IOException e) {
			System.out.println("Problème lors de la création des buffers");
			e.printStackTrace();
		}

		// Tant qu’il y’a un message à lire via recevoirMessage

		// Envoyer message au client via envoyerMessage

		// Si plus de ligne à lire fermer socket cliente
	}

	public static BufferedReader creerReader(Socket socketDepuisUnClient) throws IOException {
		return new BufferedReader(new InputStreamReader(socketDepuisUnClient.getInputStream()));
	}

	public static PrintWriter creerPrinter(Socket socketVersUnClient) throws IOException {
		return new PrintWriter(new OutputStreamWriter(socketVersUnClient.getOutputStream()));
	}

	public static String recevoirMessage(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	public static void envoyerMessage(PrintWriter printer, String message) throws IOException {
		printer.println(message);
		printer.flush();
	}

}