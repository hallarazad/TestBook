package sindhi.quiz.web.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sindhi.quiz.daos.concrete.LikesDao;





public class LoadLikesAction {
	
	private int historyId;
	
	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public String loadLikesCount() throws SQLException, IOException {
//		System.out.println("Load Likes Method");
		LikesDao dao = new LikesDao();
		HttpServletResponse res = ServletActionContext.getResponse();
		int likesCount = dao.getLikesCount(historyId);
		
//		System.out.println("Likes Count: " + likesCount);
		
		res.getWriter().write(""+likesCount);
		return null;
	}
}
