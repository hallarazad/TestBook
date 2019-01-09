package sindhi.quiz.daos.beans;

public class SubcategoryBean {
	

	
	int sub_category_id;
	String sub_category_name;
	String logo_url;
	int user_progress;
	
	
	public int getUser_progress() {
		return user_progress;
	}
	public void setUser_progress(int user_progress) {
		this.user_progress = user_progress;
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
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	
}
