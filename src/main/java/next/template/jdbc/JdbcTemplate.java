 package next.template.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import core.jdbc.ConnectionManager;


public abstract class JdbcTemplate {
	
	public void update(String query) throws Exception{
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionManager.getConnection();){
			pstmt = con.prepareStatement(query);
			setValues(pstmt);
			pstmt.executeUpdate();
		}
	}
	
	public void setValues(PreparedStatement pstmt) throws Exception{}
}
