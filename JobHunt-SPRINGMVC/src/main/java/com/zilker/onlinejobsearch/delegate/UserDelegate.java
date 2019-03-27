package com.zilker.onlinejobsearch.delegate;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.JobMapping;
import com.zilker.onlinejobsearch.beans.JobRequest;
import com.zilker.onlinejobsearch.beans.Technology;
import com.zilker.onlinejobsearch.beans.User;
import com.zilker.onlinejobsearch.beans.UserTechnologyMapping;
import com.zilker.onlinejobsearch.dao.UserDAO;

@Service
public class UserDelegate {

	public boolean register(String name, String email,String password,String companyName,String designation,String skills) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			User user = new User();
			user.setUserName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setCompany(companyName);
			user.setDesignation(designation);
			UserDAO userdao = new UserDAO();
			flag = userdao.register(user);
			int userId = fetchUserId(email);
			user.setUserId(userId);
			insertIntoUser(userId,email);
			addSkillsToProfile(skills,userId);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public void addTechnologyDetails(UserTechnologyMapping usertechnology) throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			UserDAO userdao = new UserDAO();
			userdao.addTechnologyDetails(usertechnology);
		} catch (SQLException e) {
			throw e;
		}
		
	}

	public String fetchUserNameById(int userId) throws SQLException {
		String userName = "";
		try {
			
			UserDAO userDao = new UserDAO();
			userName = userDao.fetchUserNameById(userId);
			
			return userName;
		} catch (SQLException e) {
			throw e;
		} 
	}

	
	public int fetchTechnologyId(Technology technology) throws SQLException {
		// TODO Auto-generated method stub
		int technologyId = 0;
		// TODO Auto-generated method stub
		try {
			UserDAO userDao = new UserDAO();
			technologyId = userDao.fetchTechnologyId(technology);
			return technologyId;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	public int fetchUserId(String email) throws SQLException {
		int userId = 0;
		// TODO Auto-generated method stub
		try {
			UserDAO userDao = new UserDAO();
			userId = userDao.fetchUserId(email);
			return userId;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int login(User user) throws SQLException {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			UserDAO userdao = new UserDAO();
			i = userdao.login(user);
		} catch (SQLException e) {
			throw e;
		}
		return i;
	}

	public boolean requestNewVacancy(String email,int userId,String jobId,String location,String salary) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			User user = new User();
			user.setEmail(email);
			user.setUserId(userId);
			JobRequest jobrequest = new JobRequest();
			jobrequest.setEmail(user.getEmail());
			jobrequest.setJobId(Integer.parseInt(jobId));
			jobrequest.setLocation(location);
			jobrequest.setSalary(Float.parseFloat(salary));
			flag = userDao.requestNewVacancy(jobrequest, user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean reviewAndRateCompany(int userId, int companyId,String review,String rating) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			User user = new User();
			Company company = new Company();
			user.setUserId(userId);
			company.setCompanyId(companyId);
			company.setReview(review);
			company.setRating(Float.parseFloat(rating));
			flag = userDao.reviewAndRateCompany(user, company);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean interviewProcess(int userId, int companyId, String jobRole, String interviewProcess) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			User user = new User();
			Company company = new Company();
			JobMapping jobmapping = new JobMapping();
			JobDelegate jobDelegate = new JobDelegate();
			int jobId = jobDelegate.fetchJobId(jobRole);
			jobmapping.setJobId(jobId);
			user.setUserId(userId);
			company.setCompanyId(companyId);
			company.setInterviewProcess(interviewProcess);
			flag = userDao.interviewProcess(user, company, jobmapping);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public int checkPasswordBeforeDelete(User user) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.checkPasswordBeforeDelete(user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public ArrayList<Technology> displayTechnologies(Technology technology) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Technology> tech = new ArrayList<Technology>();
		try {
			UserDAO userDao = new UserDAO();
			tech = userDao.displayTechnologies(technology);

		} catch (SQLException e) {
			throw e;
		}
		return tech;

	}

	public ArrayList<Company> retrieveReview(int companyId) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			UserDAO userDao = new UserDAO();
			comp = userDao.retrieveReview(companyId);

		} catch (SQLException e) {
			throw e;
		}

		return comp;
	}

	public ArrayList<Company> retrieveInterviewProcess(int companyId) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> comp = new ArrayList<Company>();
		try {
			UserDAO userDao = new UserDAO();
			comp = userDao.retrieveInterviewProcess(companyId);

		} catch (SQLException e) {
			throw e;
		}

		return comp;
	}
	

	public ArrayList<User> retrieveUserData(int userId)throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<User> userData = new ArrayList<User>();
		try {
			UserDAO userDao = new UserDAO();
			userData = userDao.retrieveUserData(userId);

		} catch (SQLException e) {
			throw e;
		}

		return userData;
	}

	public boolean ifAlreadyExists(User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.ifAlreadyExists(user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean ifTechnologyIdExists(Technology technology) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.ifTechnologyIdExists(technology);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}
	
	
	
	
	public boolean registerAsAdmin(String name, String email, String password,String companyId) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		int flag1=0;
		try {
			User user = new User();
			CompanyDelegate companyDelegate = new CompanyDelegate(); 
			UserDAO userDao = new UserDAO();
			String companyname = companyDelegate.fetchCompanyName(Integer.parseInt(companyId));
			user.setUserName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setCompany(companyname);
			user.setDesignation("admin");
			user.setRoleId(2);
			flag = userDao.registerAsAdmin(user);
			int userId = fetchUserId(email);
			insertIntoUser(userId,email);
			if (userId != 0) {
				flag1 = insertIntoAdmin(userId, Integer.parseInt(companyId));
				companyDelegate.insertIntoCompanyDetails(userId, Integer.parseInt(companyId));
				if (flag1 == 1) {
					flag=true;
				}
			}

		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public int insertIntoAdmin(int userId, int companyId) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.insertIntoAdmin(userId, companyId);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public void insertIntoUser(int userId,String email) throws SQLException {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			UserDAO userDao = new UserDAO();
			user.setUserId(userId);
			user.setEmail(email);
			userDao.insertIntoUser(user);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int fetchCompanyIdByAdmin(int userId) throws SQLException {
		// TODO Auto-generated method stub
		int companyId = 0;
		try {
			UserDAO userDao = new UserDAO();
			companyId = userDao.fetchCompanyIdByAdmin(userId);
			return companyId;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateUserName(User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.updateUserName(user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateCompanyName(User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.updateCompanyName(user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public boolean updateUserDesignation(User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			;
			flag = userDao.updateUserDesignation(user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<UserTechnologyMapping> displayUserTechnologies(UserTechnologyMapping userTechnologyMapping,
			int userId) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<UserTechnologyMapping> usertechnology = new ArrayList<UserTechnologyMapping>();
		try {
			UserDAO userDao = new UserDAO();
			usertechnology = userDao.displayUserTechnologies(userTechnologyMapping, userId);

		} catch (SQLException e) {
			throw e;
		}
		return usertechnology;
	}

	public boolean updateUserTechnology(UserTechnologyMapping userTechnologyMapping, User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.updateUserTechnology(userTechnologyMapping, user);
			return flag;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int addNewTechnology(Technology technology, int userId) throws SQLException {
		// TODO Auto-generated method stub
		int technologyId = 0;
		try {
			UserDAO userDao = new UserDAO();
			technologyId = userDao.addNewTechnology(technology, userId);
			return technologyId;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int deleteUserAccount(User user)throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.deleteUserAccount(user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean markContacted(int userId,String emailId,int companyId,int jobId,String location)throws SQLException  {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
		
			UserDAO userDao = new UserDAO();
			User user = new User();
			Company company = new Company();
			user.setUserId(userId);
			user.setEmail(emailId);
			company.setCompanyId(companyId);
			company.setJobId(jobId);
			company.setLocation(location);
			flag = userDao.markContacted(company,user);
		} catch (SQLException e) {
			
			throw e;
		}
		return flag;
	}
	
	public boolean applyForJob(int companyId,int jobId,String location,int userId,String email)throws SQLException  {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			Company company = new Company();
			User user = new User();
			user.setEmail(email);
			user.setUserId(userId);
			company.setCompanyId(companyId);
			company.setJobId(jobId);
			company.setLocation(location);
			flag = userDao.applyForJob(company,user);
		} catch (SQLException e) {
			
			throw e;
		}
		return flag;
	}

	public boolean deleteTechnologyDetails(UserTechnologyMapping userTechnology, User user)throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			UserDAO userDao = new UserDAO();
			flag = userDao.deleteTechnologyDetails(userTechnology,user);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public void addSkillsToProfile(String skills,int userId) throws SQLException {
		// TODO Auto-generated method stub
		try {
		int technologyId=0;
		String[] technology;
		Technology techh = new Technology();
		UserTechnologyMapping usertechnology = new UserTechnologyMapping();
		if (skills != "") {
			technology = skills.split("@");
			if (technology != null) {

				for (int i = 0; i < technology.length; i++) {

					usertechnology.setUserId(userId);
					techh.setTechnology(technology[i]);
					technologyId = fetchTechnologyId(techh);
					if (technologyId == 0) {
						techh.setTechnology(technology[i]);
						technologyId = addNewTechnology(techh, userId);
						usertechnology.setTechnologyId(technologyId);
						addTechnologyDetails(usertechnology);
					} else {
						usertechnology.setTechnologyId(technologyId);
						addTechnologyDetails(usertechnology);
					}
				}

			}
		}
	}catch(SQLException e) {
		throw e;
	}
}

	public boolean updateUserProfile(String userName, String companyName, String designation, String skills,int userId) throws SQLException{
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			int technologyId = 0;
			String[] technology;
			UserTechnologyMapping usertechnology = new UserTechnologyMapping();
			UserTechnologyMapping userTechnologyMapping = new UserTechnologyMapping();
			ArrayList<UserTechnologyMapping> userTechnology = null;

			Technology techh = new Technology();
			User user = new User();

			user.setUserId(userId);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			user.setCurrentTime(dtf.format(now));

			user.setUserName(userName);
			if (updateUserName(user)) {

			}
			user.setCompany(companyName);
			if (updateCompanyName(user)) {

			}
			user.setDesignation(designation);
			if (updateUserDesignation(user)) {

			}

			if (skills != "") {
				technology = skills.split("@");
				if (technology != null) {

					userTechnology = displayUserTechnologies(userTechnologyMapping, userId);
					if (userTechnology.isEmpty()) {
					} else {
						deleteTechnologyDetails(userTechnologyMapping, user);
					}
					for (int i = 0; i < technology.length; i++) {

						usertechnology.setUserId(user.getUserId());
						techh.setTechnology(technology[i]);
						technologyId = fetchTechnologyId(techh);
						if (technologyId == 0) {
							techh.setTechnology(technology[i]);
							technologyId = addNewTechnology(techh, userId);
							usertechnology.setTechnologyId(technologyId);
							addTechnologyDetails(usertechnology);
						} else {
							usertechnology.setTechnologyId(technologyId);
							addTechnologyDetails(usertechnology);
						}
					}

				}
			}
			flag= true;
		}catch(SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean UpdateVacancy(int oldJobId, int companyId, int userId, String newJobDesignation, String location,
			String jobDescription, String salary, String count)throws SQLException {
		// TODO Auto-generated method stub
		boolean status =false;
		try {
			
			User user = new User();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			user.setCurrentTime(dtf.format(now));
			Company company = new Company();
			CompanyDelegate companyDelegate = new CompanyDelegate();
			company.setOldJobId(oldJobId);
			user.setUserId(userId);
			int newJobId = Integer.parseInt(newJobDesignation);
			company.setJobId(newJobId);
			company.setCompanyId(companyId);
			if (companyDelegate.updateVacancyJobId(company, user)) {
				
				status=true;
			}
			company.setLocation(location);
			if (companyDelegate.updateVacancyLocation(company, user)) {
				
				status=true;
			}

			company.setJobDescription(jobDescription);
			if (companyDelegate.updateVacancyDescription(company, user)) {
			
				status=true;
			}
			company.setSalary(Float.parseFloat(salary));
			if (companyDelegate.updateVacancySalary(company, user)) {
				status=true;
			}
			int vacancyCount = Integer.parseInt(count);
			company.setVacancyCount(vacancyCount);
			if (vacancyCount == 0) {
				company.setVacancyStatus("expired");
			} else {
				company.setVacancyStatus("available");
				
			}
			if (companyDelegate.updateVacancyCount(company, user)) {
				
				status=true;
			}
			
		}catch(SQLException e) {
			throw e;
		}
		return status;
	}
}


