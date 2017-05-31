package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append(" user_id");
			sql.append(", text");
			sql.append(", message_id");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // user_id
			sql.append(", ?"); // text
			sql.append(", ?"); // message_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getUserId());
			ps.setString(2, comment.getText());
			ps.setInt(3, comment.getMessageId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
