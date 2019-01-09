package sindhi.quiz.daos.beans;

import java.util.ArrayList;






public class CompetitionBean {
	private int post_id;
	private int competition_id;
	private int test_id;
	private String test_name;
	private String date_time;
	private ArrayList<UserBean> competition_users;
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getCompetition_id() {
		return competition_id;
	}
	public void setCompetition_id(int competition_id) {
		this.competition_id = competition_id;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public ArrayList<UserBean> getCompetition_users() {
		return competition_users;
	}
	public void setCompetition_users(ArrayList<UserBean> competition_users) {
		this.competition_users = competition_users;
	}
}
