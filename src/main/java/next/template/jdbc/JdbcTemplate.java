 package next.template.jdbc;

import java.sql.PreparedStatement;

import next.dao.UserDao;
import next.model.User;

public class UpdateJdbcTemplate {
	public void update(User user, UserDao dao) throws Exception{
		PreparedStatement pstmt = null;
		dao.setValuesForUpdate(user, pstmt);
	}
}
