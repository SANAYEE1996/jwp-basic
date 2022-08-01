package next.template.config;

import java.sql.ResultSet;

public interface RowMapper {
	Object mapRow(ResultSet rs) throws DataAccessException;
}
