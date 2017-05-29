package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Department;
import dao.DepartmentDao;


public class DepartmentService {

	private static final int LIMIT_NUM = 1000;

	public List<Department> getDepartment() {

		Connection connection = null;
		try {
			connection = getConnection();

			DepartmentDao departmentDao = new DepartmentDao();
			List<Department> ret = departmentDao.getDepartment(connection, LIMIT_NUM);

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

}
