 package next.template.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.template.config.PreparedStatementSetter;
import next.template.config.RowMapper;


public class JdbcTemplate<T> {
	
	public PreparedStatementSetter createPreparedStatementSetter(Object o) {
		PreparedStatementSetter pss = null;
		return pss;
	};
	
	public void update(String query, Object o) throws Exception{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);){
			createPreparedStatementSetter(o).setValues(pstmt);
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
	
	public List<T> query(String query, Object o, RowMapper<T> rm) throws Exception{
		ResultSet rs = null;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);) {
			createPreparedStatementSetter(o).setValues(pstmt);
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
	
	public List<T> query(String query, PreparedStatementSetter pss, RowMapper<T> rm) throws Exception{
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
	
	public T queryForObject(String query, PreparedStatementSetter pss, RowMapper<T> rm) throws Exception {
		List<T> result = query(query,pss,rm);
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	
	public T queryForObject(String query, Object o, RowMapper<T> rm) throws Exception {
		List<T> result = query(query,createPreparedStatementSetter(o),rm);
		if(result.isEmpty()) return null;
		return result.get(0);
	}
	
}
