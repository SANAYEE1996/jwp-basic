package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;
import next.template.config.PreparedStatementSetter;
import next.template.config.RowMapper;
import next.template.jdbc.JdbcTemplate;

public class UserDao {
	
	
	public void insert(User user) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws Exception {
				pstmt.setString(1, user.getUserId());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getName());
	            pstmt.setString(4, user.getEmail());
			}
		};
		JdbcTemplate<Object> jdbcTemplate = new JdbcTemplate<Object>() {
			
		};
		jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)",pss);
	}
    
	public void update(User user)  throws Exception  {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, user.getPassword());
	            pstmt.setString(2, user.getName());
	            pstmt.setString(3, user.getEmail());
	            pstmt.setString(4, user.getUserId());
			}
		};
		JdbcTemplate<Object> jdbcTemplate = new JdbcTemplate<Object>() {
			
		};
		jdbcTemplate.update("update users set password = ?, name = ?, email = ? where userId = ?", pss);
	}
	
    public List<User> findAll() throws Exception {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws Exception {
			}
    	};
    	RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws Exception {
				return new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email"));
			}
		};
		JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<User>() {
			
		};
    	return jdbcTemplate.query("SELECT userId, password, name, email FROM USERS",pss,rm);
    }

    public User findByUserId(String userId) throws Exception {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, userId);
			}
		};
		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws Exception {
				return new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email"));
			}
		};
		JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<User>() {
			
		};
    	return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userId=?",pss,rm);
    }
}
