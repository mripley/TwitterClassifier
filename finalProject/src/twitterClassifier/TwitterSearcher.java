package twitterClassifier;

import java.util.ArrayList;

public class TwitterSearcher {
	public static void main(String[] args){
		System.out.println("Twitter test running");
		TwitterSearch ts = new TwitterSearch();
		LuceneClassifier classifier = new LuceneClassifier("resources/twitter-sentiment.csv", "positive", true, true);
		ArrayList<TweetDoc> docs = ts.search("iphone");	
		try {
			for(TweetDoc t : docs){
				if(classifier.classify(t.getTweetText())){
					System.out.println("true: " + t.getTweetText());
				}
				else{
					System.out.println("false: " + t.getTweetText());
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		System.out.println("done");
	}
}
