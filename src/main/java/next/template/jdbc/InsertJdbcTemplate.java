package next.template.jdbc;

import java.sql.PreparedStatement;

import next.dao.UserDao;
import next.model.User;

public class InsertJdbcTemplate {
	public void insert(User user, UserDao dao) throws Exception {
		PreparedStatement pstmt = null;
		dao.setValuesForInsert(user, pstmt);
	}
}
