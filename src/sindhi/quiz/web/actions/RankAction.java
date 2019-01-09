package sindhi.quiz.web.actions;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import sindhi.quiz.daos.concrete.RankDao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class RankAction implements SessionAware {
	private int rank;
	Map<String, Object> session;
	
	public String executeRank() throws SQLException {
		System.out.println("Rank action.. UserID = " + Integer.parseInt((String) session.get("user_id")));
		setRank(rank);
		System.out.println("Rank = " + this.rank);
		return "success";
	}

	public int getRank() throws SQLException {
		
		return rank;
	}

	public void setRank(int rank) throws SQLException {
//		int user_id = Integer.parseInt((String) session.get("user_id"));
		RankDao dao = new RankDao();
		this.rank = dao.getRank(Integer.parseInt((String) session.get("user_id")));
		
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}
}
