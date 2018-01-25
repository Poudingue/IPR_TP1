//...

package istic.pr.socket.tcp.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientTCP {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			Socket socketDeConnection = new Socket("localhost", 9999);
			BufferedReader liseur = creerReader(socketDeConnection);
			PrintWriter ecriveur = creerPrinter(socketDeConnection);
			while (true) {
				String message = lireMessageAuClavier();
				if (message.equals("fin")) {
					break;
				}
				envoyerMessage(ecriveur, message);
				System.out.println("message reçu : " + recevoirMessage(liseur));
			}
			socketDeConnection.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// envoyer le message au serveur

		// recevoir et afficher la réponse du serveur
	}

	public static String lireMessageAuClavier() {
		return sc.nextLine();
	}

	public static BufferedReader creerReader(Socket socketDepuisUnServeur) throws IOException {
		return new BufferedReader(new InputStreamReader(socketDepuisUnServeur.getInputStream()));
	}

	public static PrintWriter creerPrinter(Socket socketVersUnServeur) throws IOException {
		return new PrintWriter(new OutputStreamWriter(socketVersUnServeur.getOutputStream()));
	}

	public static String recevoirMessage(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	public static void envoyerMessage(PrintWriter printer, String message) throws IOException {
		printer.println(message);
		printer.flush();
	}

}