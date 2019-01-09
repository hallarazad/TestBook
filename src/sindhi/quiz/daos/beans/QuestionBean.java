package sindhi.quiz.daos.beans;

import java.util.ArrayList;





public class QuestionBean {
	
	private int question_id;
	private String question;
	private ArrayList<OptionBean> options;
	
	
	public ArrayList<OptionBean> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<OptionBean> options) {
		this.options = options;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
