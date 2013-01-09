package com.livetracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket clientSock = null;
        PrintWriter out = null;
//        BufferedReader in = null;
 
        try {
        	clientSock = new Socket("152.14.93.145", 4444);
            out = new PrintWriter(clientSock.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            out.write("hello");
            
            out.write("end");
            out.close();
//            in.close();
            clientSock.close();
        } catch (UnknownHostException e) {
            System.err.println("UnknownHoistException");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            System.exit(1);
        }

	}

}
