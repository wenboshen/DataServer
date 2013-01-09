package com.livetracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Server {

	public static void main(String[] args) {

		//init database
		Connection conn = null;
		Statement stmt = null;
		
		//init net socket
		ServerSocket serverSocket = null;
		boolean listening = true;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gps_data?"
							+ "user=root&password=ncsu");
			
			serverSocket = new ServerSocket(4444);
			while (listening){
				new ServerThread(serverSocket.accept(), conn).start();
			}
			
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
