package next.template.config;

import java.sql.PreparedStatement;

public interface PreparedStatementSetter {
	void setValues(PreparedStatement pstmt) throws DataAccessException;
}
