package sindhi.quiz.daos.beans;

import java.util.ArrayList;

public class TimelineBean {
	
	private String post_type;
	
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

	private int history_id;
	private int total_attempted;
	private int correct_answers;
	private int wrong_answers;
	private float score;
	private String date_time;
	private int test_id;
	private String test_name;
	private int sub_category_id;
	private String sub_category_name;
	private int category_id;
	private String category_name;
	private int user_id;
	private boolean is_liked;
	private int likesCount;
	private ArrayList<FollowerBean> likeDetails;
	
	
	public ArrayList<FollowerBean> getLikeDetails() {
		return likeDetails;
	}
	public void setLikeDetails(ArrayList<FollowerBean> likeDetails) {
		this.likeDetails = likeDetails;
	}
	public boolean isIs_liked() {
		return is_liked;
	}
	public void setIs_liked(boolean is_liked) {
		this.is_liked = is_liked;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	
	//this is for fullname
	private String user_name;
	
	private String pic_url;
	private boolean is_public;
	//this is for username
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isIs_public() {
		return is_public;
	}
	public void setIs_public(boolean is_public) {
		this.is_public = is_public;
	}
	public int getHistory_id() {
		return history_id;
	}
	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}
	public int getTotal_attempted() {
		return total_attempted;
	}
	public void setTotal_attempted(int total_attempted) {
		this.total_attempted = total_attempted;
	}
	public int getCorrect_answers() {
		return correct_answers;
	}
	public void setCorrect_answers(int correct_answers) {
		this.correct_answers = correct_answers;
	}
	public int getWrong_answers() {
		return wrong_answers;
	}
	public void setWrong_answers(int wrong_answers) {
		this.wrong_answers = wrong_answers;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
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
	public int getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	public String getSub_category_name() {
		return sub_category_name;
	}
	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	
	
	
	
	private int post_id;
	private int competition_id;
	private int num_of_questions;
	private ArrayList<UserBean> competition_users;
	
	public int getNum_of_questions() {
		return num_of_questions;
	}
	public void setNum_of_questions(int num_of_questions) {
		this.num_of_questions = num_of_questions;
	}
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
	
	public ArrayList<UserBean> getCompetition_users() {
		return competition_users;
	}
	public void setCompetition_users(ArrayList<UserBean> competition_users) {
		this.competition_users = competition_users;
	}
	
	
	
	

}
