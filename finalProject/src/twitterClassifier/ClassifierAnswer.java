package twitterClassifier;

import java.util.ArrayList;

public class ClassifierAnswer {
	private double ratio;
	private ArrayList<String> exampleTweets;
	private int numPositive;
	private int numNegative;
	private int numUndecided;
	private ArrayList<String> examplePos;
	private ArrayList<String> exampleNeg;
	
	public ClassifierAnswer(){
		
	}
	
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public ArrayList<String> getExampleTweets() {
		return exampleTweets;
	}
	public void setExampleTweets(ArrayList<String> exampleTweets) {
		this.exampleTweets = exampleTweets;
	}
	public int getNumPositive() {
		return numPositive;
	}
	public void setNumPositive(int numPositive) {
		this.numPositive = numPositive;
		ratio = numPositive / numNegative;
	}
	public int getNumNegative() {
		return numNegative;
	}
	public void setNumNegative(int numNegative) {
		this.numNegative = numNegative;
		ratio = numPositive / numNegative;
	}
	public ArrayList<String> getExamplePos() {
		return examplePos;
	}
	public void setExamplePos(ArrayList<String> examplePos) {
		this.examplePos = examplePos;
	}
	public ArrayList<String> getExampleNeg() {
		return exampleNeg;
	}
	public void setExampleNeg(ArrayList<String> exampleNeg) {
		this.exampleNeg = exampleNeg;
	}
	public int getNumUndecided() {
		return numUndecided;
	}
	public void setNumUndecided(int numUndecided) {
		this.numUndecided = numUndecided;
	}
	
	public String toString(){
		return new String("Not Implemented yet");
	}

}
