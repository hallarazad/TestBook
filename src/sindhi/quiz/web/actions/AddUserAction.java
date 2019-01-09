package sindhi.quiz.web.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import sindhi.quiz.daos.beans.UserBean;
import sindhi.quiz.daos.concrete.UsersDao;

import com.opensymphony.xwork2.ActionSupport;

public class AddUserAction extends ActionSupport{
	
	HttpServletRequest request;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	private String username;
	private String password;
	private String fullname;
	private String gender;
	private String email;
	private String picurl;
	
	public String addUser() throws SQLException, IOException {
		System.out.println("In action class: " + fullname);
		
		UserBean user = new UserBean();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setGender(gender);
		user.setEmail(email);
		user.setPicurl(picurl);
		
		UsersDao daoObj = new UsersDao();
		
		daoObj.addNewUser(user);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		
		return "success";
	}

	
}
