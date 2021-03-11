import java.sql.*;
import java.util.ArrayList;

import org.postgresql.jdbc.PgConnection;


public class PostgreSqlConnect {
	
	private final String url = "jdbc:postgresql://localhost/javaproject";
	private final String user = "postgres";
	private final String password = "Rais7777.";
	ArrayList<Integer> scores = new ArrayList<Integer>();
	ResultSet rs ;
	Statement stm ;
	
	public void connect(int score) {
		try(PgConnection con = (PgConnection) DriverManager.getConnection(url, user, password);){
			String sql = "INSERT INTO Leaderboard values(?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, score);
			int i=stmt.executeUpdate();
			stm = con.createStatement();
	
			if(con != null) {
				System.out.println("connected to postgresql");
			}
			
	} catch(SQLException e) {
				e.printStackTrace();
			}	
	}
	
	
	
	public void select() {
		
		try (PgConnection con = (PgConnection) DriverManager.getConnection(url, user, password);){
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM Leaderboard ORDER BY Score DESC FETCH FIRST 10 ROWS ONLY;");			
			while(rs.next()) {
				int score = rs.getInt("Score");
				scores.add(score);
			}

			
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

