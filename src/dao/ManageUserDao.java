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
import exception.SQLRuntimeException;

public class ManageUserDao {

	public List<User> getUserAll(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			sql.append("ORDER BY insert_date DESC limit " + num);

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

	private List<User> toUserList(ResultSet rs)
			throws SQLException {

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
}
