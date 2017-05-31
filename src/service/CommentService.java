package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Comment;
import dao.CommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao messageDao = new CommentDao();
			messageDao.insert(connection, comment);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



//	public List<MessageComment> getMessage() {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			MessageCommentDao messageDao = new MessageCommentDao();
//			List<MessageComment> ret = messageDao.getUserMessages(connection);
//
//			commit(connection);
//
//			return ret;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
}
