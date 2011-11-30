package twitterClassifier;

public class Word implements Comparable<Word> {

	private double pInCat;
	private double infoGain;
	private double pNotInCat;
	private String term;

	public Word(String word, double pIn, double pNotIn) {
		this.term = word;
		this.pInCat = pIn;
		this.pNotInCat = pNotIn;
	}

	@Override
	public int compareTo(Word w) {
      if (infoGain == w.infoGain) {
          return 0;
        } else {
          return infoGain > w.infoGain ? -1 : 1;
        }
	}

	public String toString(){
		 return term + "(" + pInCat + "," + pNotInCat + ")=" + infoGain;
	}
	public double getpInCat() {
		return pInCat;
	}

	public void setpInCat(double pInCat) {
		this.pInCat = pInCat;
	}

	public double getInfoGain() {
		return infoGain;
	}

	public void setInfoGain(double infoGain) {
		this.infoGain = infoGain;
	}

	public double getpNotInCat() {
		return pNotInCat;
	}

	public void setpNotInCat(double pNotInCat) {
		this.pNotInCat = pNotInCat;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
}
