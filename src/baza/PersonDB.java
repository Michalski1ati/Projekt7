package baza;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PersonDB implements Comparable<PersonDB>, Serializable {
	
	private static List<PersonDB> list;
	
	private String name;
	private String surname;
	private String street;
	private int housenumber;
	private int flatnumber; //only if exist
	private String city;
	private String phonenumber;
	
	public PersonDB(String name, String surname, String street, int housenumber, String city, String phonenumber){
		this(name, surname, street, housenumber, -1, city, phonenumber);
	}
	
	public PersonDB(String name, String surname, String street, int housenumber, int flatnumber, String city, String phonenumber){
		this.name = name;
		this.surname = surname;
		this.street = street;
		this.housenumber = housenumber;
		this.flatnumber = flatnumber;
		this.city = city;
		this.phonenumber = phonenumber;
		if(list == null) list = new ArrayList<PersonDB>();
		list.add(this);
	}

	@Override
	public int compareTo(PersonDB arg0) {
		
		int cmp = surname.toUpperCase().compareTo(arg0.surname.toUpperCase());
		if(cmp == 0)
			cmp = name.toUpperCase().compareTo(arg0.name.toUpperCase());
		
		return cmp;
		
	}
	
	public String toString(){
		
		return surname + " " + name + ", " + street + " " + housenumber  + ( (flatnumber == -1)?(" "):("/" + flatnumber + " ") ) + city + ", " + phonenumber ;
		
	}
	
	public static void writeDB(File dbfile){
		
		DBConnector connector = DBConnector.getInstance();
		
		connector.excuteUpdate("CREATE TABLE PERSONS(NAME VARCHAR(50), SURNAME VARCHAR(50), STREET VARCHAR(50), HOUSENUMBER INTEGER, FLATNUMBER INTEGER, CITY VARCHAR(50), PHONENUMBER VARCHAR(50))");
		
		if(list != null){
			
			connector.excuteUpdate("DELETE FROM PERSONS");
			
			for(PersonDB p:list){
				connector.excuteUpdate("INSERT INTO PERSONS VALUES ('"+p.name+"', '"+p.surname+"', '"+p.street+"', "+p.housenumber+", "+p.flatnumber+", '"+p.city+"', '"+p.phonenumber+"')");
			}
			
		}
		
	}
	
	public static void readDB(File dbfile){
		
		DBConnector connector = DBConnector.getInstance();
		
		ResultSet set = connector.excuteQuery("SELECT * FROM PERSONS;");
		
		if(set != null){
			
			list = new ArrayList<PersonDB>();
			
			try {
				while(set.next()){
					
					new PersonDB(set.getString("NAME"), set.getString("SURNAME"), set.getString("STREET"), set.getInt("HOUSENUMBER"), set.getInt("FLATNUMBER"), set.getString("CITY"), set.getString("PHONENUMBER"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public static void sortBySurname(){
		
		Collections.sort(list);
		
	}
	
	public static void removePerson(PersonDB p){
		list.remove(p);
	}
	
	public static PersonDB[] getPersonArray(){
		if(list == null) return new PersonDB[0];
		PersonDB[] ret = new PersonDB[list.size()];
		for(int i = 0, size = list.size(); i < size; i++)
			ret[i] = list.get(i);
		return ret;
	}
	
}
