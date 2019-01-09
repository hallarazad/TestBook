package sindhi.quiz.daos.beans;






public class FriendRequestBean {
	
	private int friend_request_id;
	private int sender_id;
	private String sender_fullname;
	private String sender_picurl;
	private String sender_username;
	private int receiver_id;
	private String date_time;
	private boolean is_seen;
	
	public int getFriend_request_id() {
		return friend_request_id;
	}
	public void setFriend_request_id(int friend_request_id) {
		this.friend_request_id = friend_request_id;
	}
	public String getSender_username() {
		return sender_username;
	}
	public void setSender_username(String sender_username) {
		this.sender_username = sender_username;
	}
	public boolean isIs_seen() {
		return is_seen;
	}
	public void setIs_seen(boolean is_seen) {
		this.is_seen = is_seen;
	}
	public String getSender_picurl() {
		return sender_picurl;
	}
	public void setSender_picurl(String sender_picurl) {
		this.sender_picurl = sender_picurl;
	}
	public String getSender_fullname() {
		return sender_fullname;
	}
	public void setSender_fullname(String sender_fullname) {
		this.sender_fullname = sender_fullname;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

}
