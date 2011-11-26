package twitterClassifier;

import java.util.ArrayList;

import twitter4j.Tweet;

public class TweetDoc {
	private String tweetText;
	private String userID;
	private ArrayList<String> hashTags;
	
	public TweetDoc(Tweet t){
		userID = t.getFromUser();
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ArrayList<String> getHashTags() {
		return hashTags;
	}

	public void setHashTags(ArrayList<String> hashTags) {
		this.hashTags = hashTags;
	}
	
	private String cleanText(String text){
		String retval = text.replaceAll("(https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?)", "");
		
		return "Not implemented yet\n";
	}
	
	private ArrayList<String> getHashTags(String inputText){
		ArrayList<String> retval = new ArrayList<String>();
		return retval;
		
	}
	
}
