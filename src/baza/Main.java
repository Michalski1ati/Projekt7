
package baza;


import java.sql.ResultSet;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) {
  /*
    DBConnector.getInstance().connect("org.sqlite.JDBC", "jdbc:sqlite:baza.db");
		System.out.println(DBConnector.getInstance().excuteUpdate("drop table test"));
		System.out.println(DBConnector.getInstance().excuteUpdate("create table test (id NUMBER(4), name VARCHAR2(25))"));
		System.out.println(DBConnector.getInstance().excuteUpdate("insert into test values (1, 'aaa')"));
		System.out.println(DBConnector.getInstance().excuteUpdate("insert into test values (2, 'bbb')"));
		System.out.println(DBConnector.getInstance().excuteUpdate("insert into test values (3, 'ccc')"));
		ResultSet rs = DBConnector.getInstance().excuteQuery("select * from test");
		
		try {
			while(rs.next()){
				
				System.out.println(rs.getInt("id") + " " + rs.getString("name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   */
   new AdressBookDB();
}}
