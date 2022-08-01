package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;
import next.template.jdbc.JdbcTemplate;

public class UserDao {
	
	
	public void insert(User user) throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
		    	pstmt.setString(1, user.getUserId());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getName());
	            pstmt.setString(4, user.getEmail());
		    }
			
		};
		jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
	}
    
	public void update(User user)  throws Exception  {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, user.getPassword());
	            pstmt.setString(2, user.getName());
	            pstmt.setString(3, user.getEmail());
	            pstmt.setString(4, user.getUserId());
		    }
		};
		jdbcTemplate.update("update users set password = ?, name = ?, email = ? where userId = ?");
	}
	
	void castObjectToUser(List<Object> list, List<User> answer) {
		for(Object o : list) answer.add((User) o);
	}
	
    public List<User> findAll() throws Exception {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
    		public Object mapRow(ResultSet rs) throws SQLException {
    			return new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email"));
    		}
    	};
    	List<User> answer = new ArrayList<>();
    	castObjectToUser(jdbcTemplate.query("SELECT userId, password, name, email FROM USERS"), answer);
    	return answer;
    }

    public User findByUserId(String userId) throws Exception {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
    		public void setValues(PreparedStatement pstmt) throws SQLException{
    			pstmt.setString(1, userId);
    		};
    		
    		public Object mapRow(ResultSet rs) throws SQLException {
    			return new User(rs.getString("userId"),rs.getString("password"),rs.getString("name"),rs.getString("email"));
    		}
    	};
    	return (User) jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?");
    }
}
