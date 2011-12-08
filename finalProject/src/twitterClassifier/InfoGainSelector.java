package twitterClassifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InfoGainSelector {
	private double pCategory;
	private List<Word> wordProbs;
	
	public InfoGainSelector(){
		
	}
	
	public List<Word> getWordProbs() {
		return wordProbs;
	}
	
	public void setWordProbs(Map<String, double[]> wordProbabilities) {
	 this.wordProbs = new ArrayList<Word>();
	    for (String word : wordProbabilities.keySet()) {
	      double[] probs = wordProbabilities.get(word);
	      this.wordProbs.add(new Word(word, probs[0], probs[1]));
	    }
	}
	
	public double getpCategory() {
		return pCategory;
	}
	
	public void setpCategory(double pCategory) {
		this.pCategory = pCategory;
	}
	
	public Map<String, double[]> getFeatures(){
	   for (Word word : wordProbs) {
	      if (word.getpInCat() > 0.0) {
		        word.setInfoGain(word.getpInCat() * Math.log(
		          word.getpInCat() / ((word.getpInCat() + word.getpNotInCat()) * pCategory)));
	      } else {
		        word.setInfoGain(0.0);
	      }
	    }
		    Collections.sort(wordProbs);
		    List<Word> topFeaturesList = wordProbs.subList(
		      0, (int) Math.round(Math.sqrt(wordProbs.size())));
		    Map<String,double[]> topFeatures = new HashMap<String,double[]>();
	    for (Word topFeature : topFeaturesList) {
	    	topFeatures.put(topFeature.getTerm(),  new double[] {topFeature.getpInCat(), topFeature.getpNotInCat()});
	    }
		    return topFeatures;
	}
	
}
