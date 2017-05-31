package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {
	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
//			sql.append("id");
			sql.append("account");
			sql.append(", name");
			sql.append(", password");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES (");
//			sql.append("NEXT VALUE FOR my_seq "); // id
			sql.append("?"); // account
			sql.append(", ?"); // name
			sql.append(", ?"); // password
			sql.append(", ?"); //branch_id
			sql.append(", ?"); //department_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getDepartmentId());


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String account,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE account = ? AND password = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, account);//番号順の？に値を設定
			ps.setString(2, password);

			//System.out.println(ps.toString()); セットしたSQL文の確認ができる。

			ResultSet rs = ps.executeQuery();//実行（SQLの実行）
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}




	public List<User> getUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}




	private List<User> toUserList(ResultSet rs) throws SQLException {
		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int branchId = rs.getInt("branch_id");
				int departmentId = rs.getInt("department_id");
				int isStopped = rs.getInt("is_stopped");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setPassword(password);
				user.setBranchId(branchId);
				user.setDepartmentId(departmentId);
				user.setIsstopped(isStopped);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  account = ?");
			sql.append(", name = ?");
			sql.append(", password = ?");
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			sql.append(", is_stopped = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getDepartmentId());
			ps.setInt(6, user.getIsStopped());
			ps.setInt(7, user.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public void stopUser(Connection connection, int id, int isStopped) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" is_stopped =?");
			sql.append(" Where");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, isStopped);
			ps.setInt(2, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public void deleteUser(Connection connection, int id) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from users");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
