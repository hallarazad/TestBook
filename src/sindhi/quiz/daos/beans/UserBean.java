package sindhi.quiz.daos.beans;

public class UserBean {
	
	private int user_id;
	private String username;
	private String password;
	private String fullname;
	private String gender;
	private String email;
	private String picurl;
	
	//fields for competition result
	private boolean has_attempted;
	private float score;
	private int correct_answers;
	private int wrong_answers;
	private String date_time;
	
	
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
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
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public boolean isHas_attempted() {
		return has_attempted;
	}
	public void setHas_attempted(boolean has_attempted) {
		this.has_attempted = has_attempted;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
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
}









