package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Answer;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;

public class AnswerDao {
	
	
    public Answer insert(Answer answer) {
        String sql = "INSERT INTO ANSWERS (writerId, writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, answer.getWriterId());
                pstmt.setString(2, answer.getWriter());
                pstmt.setString(3, answer.getContents());
                pstmt.setTimestamp(4, new Timestamp(answer.getTimeFromCreateDate()));
                pstmt.setLong(5, answer.getQuestionId());
                return pstmt;
            }
        };

        KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.getInstance().update(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    public Answer findById(long answerId) {
        String sql = "SELECT answerId, writerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"), rs.getString("writerId"), rs.getString("writer"), rs.getString("contents"),
                        rs.getTimestamp("createdDate"), rs.getLong("questionId"));
            }
        };

        return JdbcTemplate.getInstance().queryForObject(sql, rm, answerId);
    }

    public List<Answer> findAllByQuestionId(long questionId) {
        String sql = "SELECT answerId, writerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
                + "order by answerId desc";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"), rs.getString("writerId"), rs.getString("writer"), rs.getString("contents"),
                        rs.getTimestamp("createdDate"), questionId);
            }
        };

        return JdbcTemplate.getInstance().query(sql, rm, questionId);
    }

    public void delete(Long answerId) {
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        JdbcTemplate.getInstance().update(sql, answerId);
    }
    
    public int getAnswerCount(long questionId) {
    	String sql = "SELECT count(*) as count FROM ANSWERS WHERE questionId = ? ";
    	
    	RowMapper<Integer> rm = new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs) throws SQLException {
                return rs.getInt("count");
            }
        };
    
    	return JdbcTemplate.getInstance().queryForObject(sql, rm, questionId);
    }
}
