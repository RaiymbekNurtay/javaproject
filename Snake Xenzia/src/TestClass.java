
public class TestClass {
	PostgreSqlConnect con = new PostgreSqlConnect();
	
	public void draw() {
		con.select();
		for(int i = 0; i < con.scores.size(); i++) {
			System.out.println(con.scores.get(i));
		}
	}
}
