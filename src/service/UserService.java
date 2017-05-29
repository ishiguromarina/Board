package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.ManageUserDao;
import dao.UserDao;
import utils.CipherUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());//ｐｗの暗号化
			user.setPassword(encPassword);//暗号化したものをセット


			UserDao userDao = new UserDao();
			userDao.insert(connection, user);//インサートメソッドを呼び出し。(引数)

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




	private static final int LIMIT_NUM = 1000;

	public List<User> getUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			ManageUserDao manageuserDao = new ManageUserDao();
			List<User> ret = manageuserDao.getUserAll(connection, LIMIT_NUM);

			commit(connection);

			return ret;
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



	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

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

}
