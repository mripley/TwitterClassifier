package twitterClassifier;

import java.util.ArrayList;

public class TwitterClassifier {
	
	public static void main(String[] args){
		System.out.println("Twitter test running");
		TwitterSearch ts = new TwitterSearch();
		ArrayList<TweetDoc> docs = ts.search("iPhone");	
		
		for(TweetDoc t : docs){
			System.out.println("From: " + t.getUserID() + " Text: " + t.getTweetText());
		}
		
		System.out.println("done");
	}
	
}
