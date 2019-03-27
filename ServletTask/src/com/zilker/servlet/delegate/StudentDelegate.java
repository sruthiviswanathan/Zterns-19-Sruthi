package com.zilker.servlet.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.servlet.bean.Student;
import com.zilker.servlet.dao.StudentDao;

public class StudentDelegate {

	public boolean insertStudentDetails(Student student) {
		try {
			
			StudentDao studentDao = new StudentDao();
			int count = studentDao.insertStudentDetails(student);
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	public ArrayList<Student> DisplayStudentDetails(Student student) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Student> stud = new ArrayList<Student>();
		try {
			StudentDao StudentDao = new StudentDao();
			stud = StudentDao.DisplayStudentDetails(student);

		} catch (SQLException e) {
			throw e;
		}

		return stud;
	}
}
