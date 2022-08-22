package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;
import next.model.Question;

public class QuestionDao {
    public Question insert(Question question) {
        String sql = "INSERT INTO QUESTIONS " + 
                "(writerId, writer, title, contents, createdDate) " + 
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, question.getWriterId());
                pstmt.setString(2, question.getWriter());
                pstmt.setString(3, question.getTitle());
                pstmt.setString(4, question.getContents());
                pstmt.setTimestamp(5, new Timestamp(question.getTimeFromCreateDate()));
                return pstmt;
            }
        };

        KeyHolder keyHolder = new KeyHolder();
        JdbcTemplate.getInstance().update(psc, keyHolder);
        return findById(keyHolder.getId());
    }
    
    public void update(Question question) {
        String sql = "UPDATE QUESTIONS set title = ?, contents = ? WHERE questionId = ?";
        JdbcTemplate.getInstance().update(sql, question.getTitle(), question.getContents(),question.getQuestionId());
    }
    
    public List<Question> findAll() {
        String sql = "SELECT questionId, writerId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writerId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }

        };

        return JdbcTemplate.getInstance().query(sql, rm);
    }

    public Question findById(long questionId) {
        String sql = "SELECT questionId, writerId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writerId"),rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return JdbcTemplate.getInstance().queryForObject(sql, rm, questionId);
    }
    
    public void delete(long questionId) {
    	String sql = "DELETE FROM QUESTIONS where questionId = ?";
    	JdbcTemplate.getInstance().update(sql, questionId);
    }
}
