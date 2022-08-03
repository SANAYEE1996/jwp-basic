package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Questions;

public class QuestionsDao {
	public List<Questions> findAll(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer"
					+ " FROM QUESTIONS";
		
		RowMapper<Questions> rm = new RowMapper<Questions>() {
            @Override
            public Questions mapRow(ResultSet rs) throws SQLException {
                return new Questions(rs.getString("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"),rs.getString("createdDate"),rs.getInt("countOfAnswer"));
            }
        };
        
        return jdbcTemplate.query(sql, rm);
	}
	
	public Questions findByQuestionsId(String questionsId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer"
				+ " FROM QUESTIONS WHERE questionId = ?";
		
		RowMapper<Questions> rm = new RowMapper<Questions>() {
			@Override
			public Questions mapRow(ResultSet rs) throws SQLException {
				return new Questions(rs.getString("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"),rs.getString("createdDate"),rs.getInt("countOfAnswer"));
			}
		};
		
		return jdbcTemplate.queryForObject(sql, rm, questionsId);
	}
}
