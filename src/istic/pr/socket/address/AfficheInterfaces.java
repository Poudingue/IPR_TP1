package istic.pr.socket.address;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class AfficheInterfaces {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

			for(;interfaces.hasMoreElements();) {
				NetworkInterface currInterf = interfaces.nextElement();
				System.out.println("name : "+currInterf.getName());
				Enumeration<InetAddress> inets = currInterf.getInetAddresses();
				for(;inets.hasMoreElements();) {
					System.out.println("inet : "+inets.nextElement().toString());
				}
				System.out.println();
			}
			
		
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

}
