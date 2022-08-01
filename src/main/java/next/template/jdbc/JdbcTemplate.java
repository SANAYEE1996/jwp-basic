 package next.template.jdbc;

import java.sql.PreparedStatement;


public class JdbcTemplate {
	
	public void update(String query) throws Exception{
		PreparedStatement pstmt = null;
		setValues(pstmt, query);
	}
	
	public void setValues(PreparedStatement pstmt, String query) throws Exception{
    	
    }
}
