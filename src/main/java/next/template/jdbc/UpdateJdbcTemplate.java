package next.template.jdbc;

import next.dao.UserDao;
import next.model.User;

public class UpdateJdbcTemplate {
	public void update(User user, UserDao dao) throws Exception{
		dao.update(user);
	}
}
