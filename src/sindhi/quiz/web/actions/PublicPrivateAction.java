package sindhi.quiz.web.actions;

import java.sql.SQLException;

import sindhi.quiz.daos.concrete.TimelineDao;

public class PublicPrivateAction {
	
	private int historyId;
	
	public int getHistoryId() {
		return historyId;
	}



	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}


	public String makeItPublic() throws SQLException {
		TimelineDao dao = new TimelineDao();
		dao.makeItPublic(historyId);
		return null;
	}
	
	public String makeItPrivate() throws SQLException {
		TimelineDao dao = new TimelineDao();
		dao.makeItPrivate(historyId);
		return null;
	}

}
