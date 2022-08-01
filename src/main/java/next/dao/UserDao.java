package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.jdbc.ConnectionManager;
import next.model.User;
import next.template.jdbc.JdbcTemplate;

public class UserDao {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	
	public void insert(User user) throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {

			
			public void setValues(PreparedStatement pstmt, String query) throws Exception{
		    	try(Connection con = ConnectionManager.getConnection();){
		    		pstmt = con.prepareStatement(query);
		    		pstmt.setString(1, user.getUserId());
		            pstmt.setString(2, user.getPassword());
		            pstmt.setString(3, user.getName());
		            pstmt.setString(4, user.getEmail());
		            
		            int result = pstmt.executeUpdate();
		            log.debug("실행된 수 : {}", result);
		    	}
		    	finally {
		        	if (pstmt != null) pstmt.close();
		        }
		    }
		};
		jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
	}
    
	public void update(User user)  throws Exception  {
		JdbcTemplate jdbcTemplate = new JdbcTemplate() {

			@Override
			public void setValues(PreparedStatement pstmt, String query) throws Exception{
		    	try(Connection con = ConnectionManager.getConnection();){
		    		pstmt = con.prepareStatement(query);
		    		pstmt.setString(1, user.getPassword());
		            pstmt.setString(2, user.getName());
		            pstmt.setString(3, user.getEmail());
		            pstmt.setString(4, user.getUserId());
		            
		            int result = pstmt.executeUpdate();
		            log.debug("실행된 수 : {}", result);
		    	}
		    	finally {
		        	if (pstmt != null) pstmt.close();
		        }
		    }
		};
		jdbcTemplate.update("update users set password = ?, name = ?, email = ? where userId = ?");
	}

    public List<User> findAll() throws SQLException {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
        	con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	users.add(new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email")));
            }
            return users;
        }
        finally {
        	if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
