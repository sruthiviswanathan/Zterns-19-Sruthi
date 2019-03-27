package com.zilker.onlinejobsearch.delegate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.User;
import com.zilker.onlinejobsearch.dao.CompanyDAO;

public class CompanyDelegate {

	public ArrayList<Company> displayCompanies(Company company) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			CompanyDAO companyDao = new CompanyDAO();
			comp = companyDao.displayCompanies(company);
		} catch (SQLException e) {
			throw e;
		}
		return comp;
	}

	public int fetchCompanyId(Company company) throws SQLException {
		// TODO Auto-generated method stub
		int companyId = 0;
		try {

			CompanyDAO companyDao = new CompanyDAO();
			companyId = companyDao.fetchCompanyId(company);
			return companyId;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<Company> retrieveVacancyByCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {

			CompanyDAO companyDao = new CompanyDAO();
			comp = companyDao.retrieveVacancyByCompany(company);
		} catch (SQLException e) {
			throw e;
		}
		return comp;
	}

	public ArrayList<Company> retrieveVacancyByCompany1(Company company) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			CompanyDAO companyDao = new CompanyDAO();
			comp = companyDao.retrieveVacancyByCompany1(company);
		} catch (SQLException e) {
			throw e;
		}
		return comp;
	}

	public ArrayList<Company> retrieveVacancyByCompanyAdmin(Company company) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			CompanyDAO companyDao = new CompanyDAO();
			comp = companyDao.retrieveVacancyByCompanyAdmin(company);
		} catch (SQLException e) {
			throw e;
		}
		return comp;
	}

	public ArrayList<Company> retrieveVacancyByLocation(Company company) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			CompanyDAO companyDao = new CompanyDAO();
			comp = companyDao.retrieveVacancyByLocation(company);
		} catch (SQLException e) {
			throw e;
		}
		return comp;
	}

	public int addNewCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.addNewCompany(company);
		} catch (SQLException e) {
			throw e;
		}

		return flag;
	}

	public int addNewCompanyBySiteAdmin(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.addNewCompanyBySiteAdmin(company, user);
		} catch (SQLException e) {
			throw e;
		}

		return flag;
	}

	public int publishVacancy(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.publishVacancy(company, user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public void compareVacancyWithRequest(Company company) throws SQLException {
		// TODO Auto-generated method stub
		try {
			CompanyDAO companyDao = new CompanyDAO();
			companyDao.compareVacancyWithRequest(company);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int removeVacancy(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.removeVacancy(company, user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public int deleteCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.deleteCompany(company);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean updateVacancyJobId(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.updateVacancyJobId(company, user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateVacancyLocation(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.updateVacancyLocation(company, user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateVacancyDescription(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.updateVacancyDescription(company, user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateVacancySalary(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.updateVacancySalary(company, user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateVacancyCount(Company company, User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			CompanyDAO companyDao = new CompanyDAO();
			flag = companyDao.updateVacancyCount(company, user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public static void insertIntoCompanyDetails(User user, Company company) throws SQLException {
		// TODO Auto-generated method stub
		try {
			CompanyDAO companyDao = new CompanyDAO();
			companyDao.insertIntoCompanyDetails(user, company);
		} catch (SQLException e) {
			throw e;
		}

	}

}
