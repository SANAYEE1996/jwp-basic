package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answers;

public class AnswersDao {
	
	public void insert(Answers answer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "";
		
	}
	
	public List<Answers> findByQuestionsId(String questionsId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select answerId, writer, contents, createdDate, questionId"
					+" from ANSWERS where questionId = ?";
		
		RowMapper<Answers> rm = new RowMapper<Answers>() {
			
			@Override
			public Answers mapRow(ResultSet rs) throws SQLException {
				return new Answers(
						rs.getString("answerId"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("createdDate"),
						rs.getString("questionId"));
			}
		};
		
		return jdbcTemplate.query(sql, rm, questionsId);
		
	}
}
