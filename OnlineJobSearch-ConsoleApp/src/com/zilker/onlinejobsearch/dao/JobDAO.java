package com.zilker.onlinejobsearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.JobMapping;
import com.zilker.onlinejobsearch.beans.User;
import com.zilker.onlinejobsearch.constants.QueryConstants;
import com.zilker.onlinejobsearch.utils.DButils;

public class JobDAO {

	private Connection connection = null;
	private PreparedStatement preparestatement, preparestatement1 = null;
	private ResultSet resultset, resultset1 = null;
	private Statement statement = null;

	/*
	 * method for displaying existing job designations.
	 */
	public ArrayList<JobMapping> displayJobs(JobMapping jobmapping) throws SQLException {
		ArrayList<JobMapping> job = new ArrayList<JobMapping>();
		try {
			connection = DButils.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery(QueryConstants.RETRIEVEJOBDATA);
			while (resultset.next()) {
				JobMapping jm = new JobMapping();
				jm.setJobId(resultset.getInt(1));
				jm.setJobRole(resultset.getString(2));
				job.add(jm);
			}

		} catch (SQLException e) {

			throw e;
		} finally {
			DButils.closeConnection(connection, preparestatement, resultset);
		}

		return job;
	}

	/*
	 * method for adding new jobs.
	 */
	public int addNewJob(JobMapping jobmapping, User user) throws SQLException {
		try {
			int jobId = 0;
			connection = DButils.getConnection();
			preparestatement = connection.prepareStatement(QueryConstants.INSERTJOB);
			preparestatement.setString(1, jobmapping.getJobRole());
			preparestatement.setInt(2, user.getUserId());
			preparestatement.setInt(3, user.getUserId());
			preparestatement.executeUpdate();
			jobId = fetchJobId(jobmapping);
			return jobId;
		} catch (SQLException e) {

			throw e;
		} finally {
			DButils.closeConnection(connection, preparestatement, resultset);
		}

	}

	/*
	 * method for fetching job id given job designation.
	 */
	public int fetchJobId(JobMapping jobmapping) throws SQLException {
		try {
			connection = DButils.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery(QueryConstants.RETRIEVEJOBDATA);
			int jobId = 0;
			String jobRole = jobmapping.getJobRole();
			while (resultset.next()) {
				if (jobRole.equalsIgnoreCase(resultset.getString(2))) {
					jobId = resultset.getInt(1);
					break;
				}
			}
			return jobId;
		} catch (SQLException e) {

			throw e;
		} finally {
			DButils.closeConnection(connection, preparestatement, resultset);
		}
	}

	/*
	 * method 1 for retrieving vacancy based on job.
	 */
	public ArrayList<Company> retrieveVacancyByJob(Company company) throws SQLException {
		ArrayList<Company> comp = new ArrayList<Company>();
		try {

			connection = DButils.getConnection();
			preparestatement = connection.prepareStatement(QueryConstants.RETRIEVEJOBDESIGNATION);
			preparestatement.setInt(1, company.getJobId());
			resultset = preparestatement.executeQuery();
			while (resultset.next()) {
				Company c = new Company();
				c.setJobRole(resultset.getString(1));
				comp.add(c);
			}

		} catch (SQLException e) {
			throw e;

		} finally {
			DButils.closeConnection(connection, preparestatement, resultset);
		}
		return comp;
	}

	/*
	 * method 2 for retrieving vacancy based on job.
	 */
	public ArrayList<Company> retrieveVacancyByJob1(Company company) throws SQLException {
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			float averageRating = 0;
			CompanyDAO companyDao = new CompanyDAO();
			connection = DButils.getConnection();
			int companyId = 0;
			preparestatement = connection.prepareStatement(QueryConstants.RETRIEVEVACANCYBYJOBID);
			preparestatement.setInt(1, company.getJobId());
			resultset = preparestatement.executeQuery();
			while (resultset.next()) {

				Company c = new Company();
				c.setLocation(resultset.getString(3));
				c.setJobDescription(resultset.getString(4));
				c.setSalary(resultset.getFloat(5));
				c.setVacancyCount(resultset.getInt(6));

				String companyid = resultset.getString(1);
				companyId = Integer.parseInt(companyid);
				company.setCompanyId(companyId);
				averageRating = companyDao.calculateAverageRating(company);
				preparestatement1 = connection.prepareStatement(QueryConstants.RETRIEVECOMPANYNAME);
				preparestatement1.setInt(1, companyId);
				resultset1 = preparestatement1.executeQuery();
				while (resultset1.next()) {

					c.setCompanyName(resultset1.getString(1));
					c.setCompanyWebsiteUrl(resultset1.getString(2));
					c.setAverageRating(averageRating);
					c.setCompanyId(resultset1.getInt(3));
					comp.add(c);
				}

			}
		} catch (SQLException e) {

			throw e;
		} finally {
			DButils.closeConnection(connection, preparestatement, resultset);
			DButils.closeConnection(connection, preparestatement1, resultset1);
		}
		return comp;
	}

	public boolean ifTechnologyIdExists(JobMapping jobmapping) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			connection = DButils.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery(QueryConstants.RETRIEVEJOBDATA);
			int jobId = jobmapping.getJobId();
			while (resultset.next()) {
				int check = Integer.parseInt(resultset.getString(1));
				if (jobId == check) {
					flag = true;
					break;
				}
			}
			return flag;

		} catch (SQLException e) {
			throw e;

		} finally {
			DButils.closeConnection(connection, preparestatement, resultset);
		}
	}

}
