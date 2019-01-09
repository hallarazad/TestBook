package sindhi.quiz.daos.beans;





public class OptionBean {
	private String text;
	private boolean is_selected = false;
	private boolean is_correct;
	
	
	public boolean isIs_correct() {
		return is_correct;
	}
	public void setIs_correct(boolean is_correct) {
		this.is_correct = is_correct;
	}
	public boolean isIs_selected() {
		return is_selected;
	}
	public void setIs_selected(boolean is_selected) {
		this.is_selected = is_selected;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
