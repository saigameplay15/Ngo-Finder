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
			String query = "select * from user where (email='" + user + "')";
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
		}
		return -1;
	}
	
	public boolean addUser(String email, String name, String ph_no, String pass) {
		Connection con;
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "insert into user (name,email,ph_no,pass) values ('" + name + "','" + email + "','" + ph_no + "','" + pass + "')";
			st.executeUpdate(query);
			ret = true;
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public int isUser(String email, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			Connection con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "select * from user where (email='" + email +"' and pass='" + pass + "')";
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
	
	public ArrayList<String[]> fetchFilteredNgo(Double lat, Double lon, String filter) {
		if (lat == null || lon == null || filter == null) {
			return null;
		}
		ArrayList<String[]> ret = new ArrayList<String[]>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			Connection con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "select * from ngo";
			ResultSet result = st.executeQuery(query);
			while (result.next()) {
				double d = 0.00;
				d=distance(lat,Double.valueOf(result.getString("lat")), lon, Double.valueOf(result.getString("lon")));
				if (d < 10) {
					if ((!filter.equals("None") && result.getString("type").equals(filter)) || filter.equals("None")) {
						String str[] = new String[5];
						str[0] = result.getString("name");
						String dist = "";
						char[] cr = String.valueOf(d).toCharArray();
						for (int i = 0; i < cr.length; i++) {
							if (cr[i] == '.') {
								break;
							} else {
								dist += cr[i];
							}
						}
						str[1] = dist;
						str[2] = result.getString("email");
						str[3] = result.getString("ph_no");
						str[4] = result.getString("type");
						ret.add(str);
					}
				}
			}
			result.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<String[]> fetchEvents(String ngo) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, u, p);
			Statement st = con.createStatement();
			String query = "select * from events where ngo_id='" + ngo + "'";
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
	
	public int subscribe(String user, String ngo) {
		int ret = -1;
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, "root", "Achievement3unlocked");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from subscriptions where (user_id='" + user + "' and ngo_id='" + ngo + "')");
			rs.last();
			int length = rs.getRow();
			if (length < 1) {
				String query = "insert into subscriptions (user_id,ngo_id) values ('" + user + "','" + ngo + "')";
				st.executeUpdate(query);
			}
			ret = length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public int unsubscribe(String user, String ngo) {
		int ret = -1;
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, "root", "Achievement3unlocked");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from subscriptions where (user_id='" + user + "' and ngo_id='" + ngo + "')");
			rs.last();
			int length = rs.getRow();
			if (length == 1) {
				String query = "delete from subscriptions where (user_id='" + user + "' and ngo_id='" + ngo + "')";
				st.executeUpdate(query);
			}
			ret = length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<String[]> fetchSubscribedEvents(String email) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sen_db";
			con = DriverManager.getConnection(url, "root", "Achievement3unlocked");
			Statement st = con.createStatement();
			String query = "select * from events where ngo_id IN (select ngo_id from subscriptions where user_id='" + email + "')";
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
	
	private static double distance(double lat1, double lat2, double lon1, double lon2) { 
		lon1 = Math.toRadians(lon1); 
		lon2 = Math.toRadians(lon2); 
		lat1 = Math.toRadians(lat1); 
		lat2 = Math.toRadians(lat2); 

		double dlon = lon2 - lon1;  
		double dlat = lat2 - lat1; 
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2); 
		double c = 2 * Math.asin(Math.sqrt(a)); 
		double r = 6371; 

		return(c * r); 
	}
}
