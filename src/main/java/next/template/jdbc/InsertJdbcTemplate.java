package next.template.jdbc;

import next.dao.UserDao;
import next.model.User;

public class InsertJdbcTemplate {
	public void insert(User user, UserDao dao) throws Exception {
		dao.insert(user);
	}
}
