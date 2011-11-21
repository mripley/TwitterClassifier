package twitterClassifier;


public class TwitterClassifier {
	
	public static void main(String[] args){
		System.out.println("Twitter test running");
		TwitterSearch ts = new TwitterSearch();
		ts.search("iPhone");	
		System.out.println("done");
	}
	
}
