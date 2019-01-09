package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.ArrayList;

import sindhi.quiz.daos.beans.OptionBean;
import sindhi.quiz.daos.concrete.QuestionsDao;






public class AddQuestionAction {
	
	private String question;
	private int selectedOption;
	private int optionCount;
	private int testId;
	
	private OptionBean option0;
	private OptionBean option1;
	private OptionBean option2;
	private OptionBean option3;
	private OptionBean option4;
	
	
	public AddQuestionAction() {
		
		System.out.println("Request accepted...");
		
		option0 = new OptionBean();
		option1 = new OptionBean();
		option2 = new OptionBean();
		option3 = new OptionBean();
		option4 = new OptionBean();
	}
	
	
	
	public int getTestId() {
		return testId;
	}



	public void setTestId(int testId) {
		this.testId = testId;
	}



	public int getSelectedOption() {
		return selectedOption;
	}



	public void setSelectedOption(int selectedOption) {
		this.selectedOption = selectedOption;
	}



	public int getOptionCount() {
		return optionCount;
	}

	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	}


	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public OptionBean getOption0() {
		return option0;
	}
	public void setOption0(OptionBean option0) {
		this.option0 = option0;
	}
	public OptionBean getOption1() {
		return option1;
	}
	public void setOption1(OptionBean option1) {
		this.option1 = option1;
	}
	public OptionBean getOption2() {
		return option2;
	}
	public void setOption2(OptionBean option2) {
		this.option2 = option2;
	}
	public OptionBean getOption3() {
		return option3;
	}
	public void setOption3(OptionBean option3) {
		this.option3 = option3;
	}
	public OptionBean getOption4() {
		return option4;
	}
	public void setOption4(OptionBean option4) {
		this.option4 = option4;
	}
	
	
	public String addQuestion() throws SQLException {
		
		System.out.println("Selected Option = " + selectedOption);
		
		switch(selectedOption) {
		
		case 0:
			option0.setIs_selected(true);
			break;
		case 1:
			option1.setIs_selected(true);
			break;
		case 2:
			option2.setIs_selected(true);
			break;
		case 3:
			option3.setIs_selected(true);
			break;
		case 4:
			option4.setIs_selected(true);
			break;
		
		}
		
		//Create options list
		ArrayList<OptionBean> options = new ArrayList<OptionBean>();
		
		options.add(option0);
		options.add(option1);
		
		if(optionCount > 2)
			options.add(option2);
		
		if(optionCount > 3)
			options.add(option3);
		
		if(optionCount > 4)
			options.add(option4);
		
		
		QuestionsDao dao = new QuestionsDao();
		
		dao.addQuestion(testId, question, options);
		
//		System.out.println(optionCount);
		
		
		
		
		
		
		
		return null;
	}
	
	
}
