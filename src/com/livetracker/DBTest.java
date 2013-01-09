package com.livetracker;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBTest {
	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/gps_data?"
							+ "user=root&password=ncsu");

			int id = 75890;
			double lat = 30;
			double lng = 70;
			String time = "Jan, 9, fuck";

			// test insert
			stmt = (Statement) conn.createStatement();
			String sql;

			sql = "INSERT INTO gps VALUES (" + id + "," + lat + "," + lng + ",'"
					+ time + "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			sql = "SELECT object_id, lat, lng, time FROM gps";
			ResultSet rs = stmt.executeQuery(sql);
			
			// STEP 5: Extract data from result set
			while (rs.next()) {
				id = rs.getInt("object_id");
				lat = rs.getDouble("lat");
				lng = rs.getDouble("lng");
				time = rs.getString("time");

				// Display values
				System.out.println(id + " || " + lat + " || " + lng + " || "
						+ time);
			}
			// STEP 6: Clean-up environment
			rs.close();
			
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}

}
