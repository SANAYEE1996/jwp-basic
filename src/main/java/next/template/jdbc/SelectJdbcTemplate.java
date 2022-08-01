package next.template.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;

public abstract class SelectJdbcTemplate {
	
	public List<Object> query(String query) throws Exception{
		ResultSet rs = null;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);) {
			setValues(pstmt);
			rs = pstmt.executeQuery();
			List<Object> result = new ArrayList<>();
			while(rs.next()) { 
				result.add(mapRow(rs));
			}
			return result;
		}
		finally {
			if(rs!= null) rs.close();
		}
	}
	
	public Object queryForObject(String query) throws Exception {
		List<Object> result = query(query);
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	
	public abstract void setValues(PreparedStatement pstmt) throws SQLException;
	
	public abstract Object mapRow(ResultSet rs) throws SQLException;
}
