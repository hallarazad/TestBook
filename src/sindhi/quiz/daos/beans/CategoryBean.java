package sindhi.quiz.daos.beans;

import java.util.ArrayList;








public class CategoryBean {
	
	int category_id;
	String category_name;
	
	int user_progress;
	
	ArrayList<SubcategoryBean> sub_categories;
	
	public int getUser_progress() {
		return user_progress;
	}
	public void setUser_progress(int user_progress) {
		this.user_progress = user_progress;
	}
	public ArrayList<SubcategoryBean> getSub_categories() {
		return sub_categories;
	}
	public void setSub_categories(ArrayList<SubcategoryBean> sub_categories) {
		this.sub_categories = sub_categories;
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
}
