package istic.pr.socket.tcp.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TraiteUnClient implements Runnable {

	private Socket socketVersUnClient;
	private int poolsize = 10;

	public TraiteUnClient(Socket versclient) {
		socketVersUnClient = versclient;
	}

	private final ExecutorService pool = Executors.newFixedThreadPool(poolsize);

	public void traiterClient() { // run the service
		try {
			for (;;) {
				pool.execute(new TraiteUnClient(socketVersUnClient));
				ServeurTCP.traiterSocketCliente(socketVersUnClient);
			}
		} catch (Exception e) {
			pool.shutdown();
		}
	}

	@Override
	public void run() {
		ServeurTCP.traiterSocketCliente(socketVersUnClient);

	}

}
