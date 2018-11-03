package app.java.com.model.database.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.AlterException;
import app.java.com.model.Exceptions.CreateException;
import app.java.com.model.Exceptions.SelectException;

public abstract class Command {

	/*
	 * need to implement for specific Command
	 */
	public abstract boolean handle() throws Exception;
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with quote
	 */
	private static String formulateString(List<String> listOfString, char quote) {
		String result = "";
		for (String s : listOfString) {
			result = result + quote + s + quote +",";
		}
		return "(" + result.substring(0, result.length()-1) + ")";
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with single quotation mark (')
	 */
	public static String formulateData(List<String> data) {
		return formulateString(data, '\'');
	}
	
	/*
	 * formulate a list of String into a single string with comma separate them
	 * and each element in the list will wrap with back quotation mark (`)
	 */
	public static String formulateIds(List<String> columnIds) {
		return formulateString(columnIds, '`');
	}
	
	/*
	 * run Insert/Drop/Update/Alter commands directly in the database
	 * @param query create statement
	 */
	public boolean runExecuteUpdate(String query) throws Exception {
		Connection conn;
		conn = ConnectDatabase.connect();
		Statement st = conn.createStatement();
		st.executeUpdate(query);
		st.close();
		conn.close();
		return true;
	}
	
	/*
	 * insert Column with contraint given
	 */
	public boolean runExecute(String sql) throws Exception {
		Connection conn;
		conn = ConnectDatabase.connect();
		Statement st = conn.createStatement();
		st.execute(sql);
		st.close();
		conn.close();
		return true;

	}
	
	/*
	 * select all the data under the columnId and satisfies the specific constraint
	 */
	public static ResultSet RunExecuteQuery(String sql) throws Exception {
		Connection conn;
		conn = ConnectDatabase.connect();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
//		st.close();
//		conn.close();
		return rs;
		
	}
	
	public static void main(String[] argc) {
		String s = "select * from test";
		try {
			ResultSet res = RunExecuteQuery(s);
			while(res.next()) {
				String name = res.getString("name");
				String lastName = res.getString("lastName");
				System.out.println(name + " ----" + lastName);
				
			}
		} catch (Exception e) {
			
		}
		
	}
}