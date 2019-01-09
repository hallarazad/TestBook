package sindhi.quiz.daos.interfaces;

import java.sql.SQLException;
import java.util.HashMap;

import sindhi.quiz.daos.beans.UserBean;

public interface UsersDaoInterface {
	public void addNewUser(UserBean user) throws SQLException;
	public HashMap loginCheck(String username, String password) throws SQLException;
}
