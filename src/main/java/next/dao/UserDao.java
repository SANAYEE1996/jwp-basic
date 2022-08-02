package next.dao;

import java.sql.ResultSet;
import java.util.List;

import next.model.User;
import next.template.config.RowMapper;
import next.template.jdbc.JdbcTemplate;

public class UserDao {
	
	
	public void insert(User user) throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserId(),user.getPassword(), user.getName(), user.getEmail());
	}
    
	public void update(User user)  throws Exception  {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update users set password = ?, name = ?, email = ? where userId = ?";
		jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
	
    public List<User> findAll() throws Exception {
    	RowMapper<User> rm = (ResultSet rs) -> new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email"));
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	return jdbcTemplate.query("SELECT userId, password, name, email FROM USERS",rm);
    }

    public User findByUserId(String userId) throws Exception {
		RowMapper<User> rm = (ResultSet rs) -> new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email"));
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	return jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userId=?",rm,userId);
    }
    
}
