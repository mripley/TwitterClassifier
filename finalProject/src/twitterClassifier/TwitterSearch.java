package twitterClassifier;
import java.util.ArrayList;
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
	
	public ArrayList<TweetDoc> search(String queryString){
		ArrayList<TweetDoc> retval = new ArrayList<TweetDoc>();
		
		for( int i=1; i< 15; i++){
		    Query query = new Query(queryString);
		    query.setRpp(100);
		    query.setLang("en");
		    query.setPage(i);
		    QueryResult result;
			try {
				result = twitter.search(query);
				List<Tweet> results = result.getTweets();
				for(Tweet t : results){
					retval.add(new TweetDoc(t));
				}
			    
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		return retval;
	}
	
}
