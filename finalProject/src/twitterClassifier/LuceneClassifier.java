package twitterClassifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class LuceneClassifier extends TwitterClassifier {

	private boolean topFeatures;
	private boolean overfit;
	private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
	
	public LuceneClassifier(String trainingFile){
		
	}
	
	@Override
	public void classify(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void trainClassifier(String trainingFile){
		// build the index
		buildIndex(trainingFile);
		
	}
}
