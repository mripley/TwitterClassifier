package twitterClassifier;
import java.util.List;

import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Query;

public class TwitterSearch {
	private Twitter twitter;

	public TwitterSearch(){
		twitter = new TwitterFactory().getInstance();
	}
	
	public void search(String queryString){
	    Query query = new Query(queryString);
	    query.setRpp(100);
	    query.setLang("en");
	    QueryResult result;
		try {
			result = twitter.search(query);
			List<Tweet> results = result.getTweets();
			System.out.println("Total number of results: " + results.size());
		    for (Tweet tweet : results) {
		        System.out.println(tweet.getFromUser() + ":" + tweet.getText());
		    }
		    
		} catch (TwitterException e) {
			e.printStackTrace();
		}

	}

}
