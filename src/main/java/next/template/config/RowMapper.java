package next.template.config;

import java.sql.ResultSet;

@FunctionalInterface
public interface RowMapper<T> {
	T mapRow(ResultSet rs) throws Exception;
}
