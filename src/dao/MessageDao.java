package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Message;
import exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
		//	sql.append("id");
			sql.append(" user_id");
			sql.append(", text");
			sql.append(", title");
			sql.append(", category");
			sql.append(", insert_date");
			sql.append(") VALUES (");
		//	sql.append("NEXT VALUE FOR my_seq "); // id
			sql.append(" ?"); // user_id
			sql.append(", ?"); // text
			sql.append(", ?"); // title
			sql.append(", ?"); // category
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUserId());
			ps.setString(2, message.getText());
			ps.setString(3, message.getTitle());
			ps.setString(4, message.getCategory());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
