package twitterClassifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LingPipeClassifier extends TwitterClassifier {

	public LingPipeClassifier(String trainingFile){
		
	}
	
	@Override
	public boolean classify(String query) {
		return true;
	}

	@Override
	protected void trainClassifier(String trainingFile) {
		
	}

}
