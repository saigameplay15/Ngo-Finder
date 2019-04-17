package com.project.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Transaction {
	
	private String u = "database user";
	private String p = "Your database password";
	private String url = "jdbc:mysql://localhost/sen_db";
	
	public Transaction() {
		
	}
	
	public int exists(String user) {
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, u, p);
			Statement statement = con.createStatement();
			String query = "select * from ngo where (email='" + user + "')";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.last();
			int length = resultSet.getRow();
			resultSet.close();
			statement.close();
			con.close();
			if (length == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean addNgo(String email, String name, String ph_no, String pass, String lat, String lon, String type) {
		Connection con;
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "insert into ngo (name,type,lat,lon,manager_id,email,ph_no,pass) values ('" + name + "','" + 
					 type + "','" + lat + "','" + lon + "',null,'" + email + "','" + ph_no + "','" + pass + "')";
			st.executeUpdate(query);
			ret = true;
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public int isNgo(String email, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			Connection con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "select * from ngo where (email='" + email +"' and pass='" + pass + "')";
			ResultSet result = st.executeQuery(query);
			result.last();
			int len = result.getRow();
			result.close();
			st.close();
			con.close();
			if (len == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<String[]> fetchNgoEvents(String username) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "select * from events where ngo_id='" + username + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String[] read = new String[7];
				read[0] = rs.getString("name");
				read[1] = rs.getString("description");
				read[2] = rs.getString("location");
				read[3] = rs.getString("start_date");
				read[4] = rs.getString("end_date");
				read[5] = rs.getString("start_time");
				read[6] = rs.getString("end_time");
				list.add(read);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addEvent(String username, String sd, String ed, String name, String desc, String st, String et, String loc) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement s = con.createStatement();
			String query = "insert into events values ('" + username + "', '" + sd + "', '" + ed + "', '" +
					name + "', '" + desc + "', '" + st + "', '" + et + "', '" + loc + "')";
			s.executeUpdate(query);
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEvent(String username, String event_name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement s = con.createStatement();
			String query = "delete from events where (ngo_id='" + username + "' and name='" + event_name + "')";
			s.executeUpdate(query);
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]> fetchPastEvents(String email) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "select * from pastevents where ngo_id='" + email + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String[] read = new String[7];
				read[0] = rs.getString("name");
				read[1] = rs.getString("description");
				read[2] = rs.getString("location");
				read[3] = rs.getString("start_date");
				read[4] = rs.getString("end_date");
				read[5] = rs.getString("start_time");
				read[6] = rs.getString("end_time");
				list.add(read);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deletePastEvent(String username, String event_name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement s = con.createStatement();
			String query = "delete from pastevents where (ngo_id='" + username + "' and name='" + event_name + "')";
			s.executeUpdate(query);
			s.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void addToPastEvents(String username, String name) {
		try {
			ArrayList<String[]> list = this.fetchNgoEvents(username);
			for (int i = 0; i < list.size(); i++) {
				String[] r = list.get(i);
				if (r[0].equals(name)) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						String url = "jdbc:mysql://localhost/sen_db";
						con = DriverManager.getConnection(url, u, p);
						Statement s = con.createStatement();
						String query = "insert into pastevents values ('" + username + "', '" + r[3] + "', '" + r[4] + "', '" +
								name + "', '" + r[1] + "', '" + r[5] + "', '" + r[6] + "', '" + r[2] + "')";
						s.executeUpdate(query);
						s.close();
						con.close();
					} catch (Exception f) {
						f.printStackTrace();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
