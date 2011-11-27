package twitterClassifier;

public abstract class TwitterClassifier {
	
	public TwitterClassifier(){
		
	}
	
	public abstract void classify(String query);
	protected abstract void buildClassifier(String trainingFile);
	
}
