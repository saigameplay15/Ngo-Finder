package com.project.transactions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Transact {

	private final FirebaseDatabase database;
	private final FirebaseAuth auth;
	private static int ret=0;
	
	public static class Ngo {
		public String email;
		public String name;
		public String ph_no;
		public String lat;
		public String lon;
		public String pass;
		public String type;
		
		public Ngo(String memail, String mname,String mph_no, String mlat, String mlon, String mpass, String mtype) {
			this.email = memail;
			this.name = mname;
			this.ph_no = mph_no;
			this.lat = mlat;
			this.lon = mlon;
			this.pass = mpass;
			this.type = mtype;
		}
	}
	
	public Transact() {
		database = FirebaseDatabase.getInstance();
		auth = FirebaseAuth.getInstance();
	}
	
	public void addNgo(String email, String name, String ph_no, String pass, String lat, String lon, String type) {
		DatabaseReference ref = database.getReference("NgoDetails");
		char[] ch = email.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '@' || ch[i] == '.') {
				ch[i] = '-';
			}
		}
		CreateRequest request = new CreateRequest()
				.setEmail(email)
				.setEmailVerified(true)
				.setPassword(pass)
				.setUid(String.valueOf(ch))
				.setDisabled(false);
				
		try {
			auth.createUser(request);
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ref.child(String.valueOf(ch)).setValueAsync(new Ngo(email,name, ph_no, lat, lon, pass, type));
	}
	
	public int isNgo(String email, String pass) {
		int ret = -1;
		char[] ch = email.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '@' || ch[i] == '.') {
				ch[i] = '-';
			}
		}
		
		try {
			UserRecord userRecord = auth.getUser(String.copyValueOf(ch));
			
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
}

