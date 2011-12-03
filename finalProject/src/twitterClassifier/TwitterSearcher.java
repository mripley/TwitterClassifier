package twitterClassifier;

import java.util.ArrayList;

public class TwitterSearcher {
	public static void main(String[] args){
		System.out.println("Twitter test running");
		TwitterSearch ts = new TwitterSearch();
		LuceneClassifier classifier = new LuceneClassifier("resources/training-set.txt", "positive");
		ArrayList<TweetDoc> docs = ts.search("iPhone review");	
		try {
			for(TweetDoc t : docs){
				if(classifier.classify(t.getTweetText())){
					System.out.println("");
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			

		
		
		
		System.out.println("done");
	}
}
