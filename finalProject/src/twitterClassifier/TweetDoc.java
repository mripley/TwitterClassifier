package twitterClassifier;

import java.util.ArrayList;

import twitter4j.Tweet;

public class TweetDoc {
	private String tweetText;
	private String userID;
	private ArrayList<String> hashTags;
	
	public TweetDoc(Tweet t){
		hashTags = new ArrayList<String>();
		userID = t.getFromUser();
		tweetText = cleanText(t.getText());
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = cleanText(tweetText);
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
		// replace all links with empty strings
		String retval = text.replaceAll("(https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?)", "");
		
		// replace all @ references
		retval = retval.replaceAll("@[\\w*\\d([\\^$()#%)]]*", "");
		
		// replace all RT's
		retval = retval.replaceAll("RT:?", "");
		
		// get all # tags
		String[] splitString = retval.split(" ");
		for(String s : splitString){
			if(s.startsWith("#")){
				this.hashTags.add(s);
			}
		}
		
		
		retval = retval.replaceAll("\\d", "");
		
		retval = retval.replaceAll("@\\w*", "");
		retval = retval.replaceAll("@\\w*", "");
		// remove all hash tags
		retval = retval.replaceAll("#\\w*", "");
		
		retval = retval.replaceAll("^\\w*", "");
		retval = retval.replaceAll("&amp;", "");
		retval = retval.replaceAll("&lt;", "");
		retval = retval.replaceAll(" lol ", "laugh");
		return retval;
	}
	
}
