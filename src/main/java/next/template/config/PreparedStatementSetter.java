package next.template.config;

import java.sql.PreparedStatement;

public interface PreparedStatementSetter {
	public void setValues(PreparedStatement pstmt) throws Exception;
}
