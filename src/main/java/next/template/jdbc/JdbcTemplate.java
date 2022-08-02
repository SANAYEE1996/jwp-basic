 package next.template.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.template.config.PreparedStatementSetter;
import next.template.config.RowMapper;


public class JdbcTemplate {
	
	
	public void update(String query, Object... o) throws Exception{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);){
			for(int i = 0; i < o.length; i++) {
				pstmt.setObject(i+1, o[i]);
			}
			pstmt.executeUpdate();
		}
	}
	
	public void update(String query, PreparedStatementSetter pss) throws Exception{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);){
			pss.setValues(pstmt);
			pstmt.executeUpdate();
		}
	}
	
	public <T> List<T> query(String query, RowMapper<T> rm, Object... o) throws Exception{
		ResultSet rs = null;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);) {
			for(int i = 0; i < o.length; i++) {
				pstmt.setObject(i+1, o[i]);
			}
			rs = pstmt.executeQuery();
			List<T> result = new ArrayList<>();
			while(rs.next()) { 
				result.add(rm.mapRow(rs));
			}
			return result;
		}
		finally {
			if(rs!= null) rs.close();
		}
	}
	
	public <T> List<T> query(String query, PreparedStatementSetter pss, RowMapper<T> rm) throws Exception{
		ResultSet rs = null;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);) {
			pss.setValues(pstmt);
			rs = pstmt.executeQuery();
			List<T> result = new ArrayList<>();
			while(rs.next()) { 
				result.add(rm.mapRow(rs));
			}
			return result;
		}
		finally {
			if(rs!= null) rs.close();
		}
	}
	
	public <T> T queryForObject(String query, PreparedStatementSetter pss, RowMapper<T> rm) throws Exception {
		List<T> result = query(query,pss,rm);
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	
	public <T> T queryForObject(String query, RowMapper<T> rm, Object... o) throws Exception {
		List<T> result = query(query,rm,o);
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	
}
