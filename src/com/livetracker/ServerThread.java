package com.livetracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ServerThread extends Thread {
	private Socket socket = null;
	private Connection conn = null;

	public ServerThread(Socket socket, Connection conn) {
		super();
		this.socket = socket;
		this.conn = conn;
	}

	public void run() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String inputLine;
			Statement stmt = (Statement) conn.createStatement();

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("end"))
					break;
				
				String[] values = inputLine.split("\\|\\|");
				
				int id = Integer.parseInt(values[0].trim());
				double lat = Double.parseDouble(values[1].trim());
				double lng = Double.parseDouble(values[2].trim());
				String time = values[3];

				String sql = "INSERT INTO gps VALUES (" + id + "," + lat + "," + lng + ",'"
						+ time + "')";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
			//clean up net socket
			out.close();
			in.close();
			socket.close();
			
			//clean up database
			stmt.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
