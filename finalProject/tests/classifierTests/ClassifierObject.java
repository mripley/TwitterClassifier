package classifierTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import twitterClassifier.*;

public class ClassifierObject {
	private ArrayList<String> testDocs;
	private String[] coffeeDocs = {
			"resources/cocoa.txt",
			"resources/cocoa1.txt",
			"resources/cocoa2.txt",
			"resources/coffee.txt",
			"resources/coffee1.txt"
	};
	
	public String readFileToString(String fileName){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StringBuffer buf = new StringBuffer();
			String curLine = "";
			while((curLine = reader.readLine()) != null){
				buf.append(curLine);
			}
			return buf.toString();
		} catch (FileNotFoundException e) {
			System.out.println("File "+ fileName + " Not found");
			return null;
		} catch (IOException e) {
			System.out.println("Caught IO Exception in readFileToString");
			return null;
		}
	}
	
	public ArrayList<String[]> getTestData(String filename){
		ArrayList<String[]> tests = new ArrayList<String[]>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			
			String curLine = new String("");
			String text;
			while((curLine = reader.readLine()) != null){
				text = new String("");
				String[] splitLine = curLine.split(",");
				// grab the category 
				String category = splitLine[0].toLowerCase().trim();
				
				// concatenate the rest of the array back into the original document
				for(int i=1; i< splitLine.length; i++){
					text += splitLine[i];
				}
				String[] test = new String[2];
				test[0] = category;
				test[1] = text;
				tests.add(test);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File: " + filename + " Not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return tests;	
	}
	
	@Before
	public void loadTests(){
		testDocs = new ArrayList<String>();
		for(String s : coffeeDocs){
			testDocs.add(readFileToString(s));
		}
	}
	
	@After
	public void after(){
		
	}

	@Test
	public void testClassify() {
		LuceneClassifier classifier = new LuceneClassifier("resources/coffee-test-set.idx", "positive", false, false );
		try {
			assertTrue(classifier.classify(testDocs.get(0)));
			assertFalse(classifier.classify(testDocs.get(1)));
			assertFalse(classifier.classify(testDocs.get(2)));
			assertFalse(classifier.classify(testDocs.get(3)));
			assertFalse(classifier.classify(testDocs.get(4)));
			
		} catch (Exception e) {
			System.out.println("caught exception in test clasify");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOverfit(){
		LuceneClassifier classifier = new LuceneClassifier("resources/coffee-test-set.idx", "positive", true, false );
		try {
						
			assertTrue(classifier.classify(testDocs.get(0)));
			assertFalse(classifier.classify(testDocs.get(1)));
			assertFalse(classifier.classify(testDocs.get(2)));
			assertFalse(classifier.classify(testDocs.get(3)));
			assertFalse(classifier.classify(testDocs.get(4)));
			
		} catch (Exception e) {
			System.out.println("caught exception in test clasify");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOverfitFeatureSelect(){
		LuceneClassifier classifier = new LuceneClassifier("resources/coffee-test-set.idx", "positive", true, true );
		try {
				
			assertTrue(classifier.classify(testDocs.get(0)));
			assertTrue(classifier.classify(testDocs.get(1)));
			assertTrue(classifier.classify(testDocs.get(2)));
			assertTrue(classifier.classify(testDocs.get(3)));
			assertTrue(classifier.classify(testDocs.get(4)));
			
		} catch (Exception e) {
			System.out.println("caught exception in test clasify");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFeatureSelect() {
		LuceneClassifier classifier = new LuceneClassifier("resources/coffee-test-set.idx", "positive", false, true );
		try {
			
				
			assertTrue(classifier.classify(testDocs.get(0)));
			assertTrue(classifier.classify(testDocs.get(1)));
			assertTrue(classifier.classify(testDocs.get(2)));
			assertTrue(classifier.classify(testDocs.get(3)));
			assertTrue(classifier.classify(testDocs.get(4)));
			
		} catch (Exception e) {
			System.out.println("caught exception in test clasify");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSentiment(){
		LuceneClassifier classifier = new LuceneClassifier("resources/sentiment.idx", "positive", false, true );
		
		try {
			assertTrue(classifier.classify("great"));
			assertFalse(classifier.classify("horrible"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void buildIndexs(){
//		TwitterClassifier.buildIndexFile("resources/coffee-test-set.csv", "resources/coffee-test-set.idx");
//		TwitterClassifier.buildIndexFile("resources/sentiment-test.csv", "resources/sentiment.idx");
//		TwitterClassifier.buildIndexFile("resources/twitter-sentiment.csv", "resources/twitter-sentiment.idx");
//	}
	
	@Test
	public void testCorpus(){
		
		LuceneClassifier posClassifier = new LuceneClassifier("resources/twitter-sentiment.idx", "positive", true, true);
		LuceneClassifier negClassifier = new LuceneClassifier("resources/twitter-sentiment.idx", "negative", true, true);
		LuceneClassifier neutralClassifier = new LuceneClassifier("resources/twitter-sentiment.idx", "neutral", true, true);
		System.out.println("Training Complete!");
		try {
			int nCorrect = 0;
			
			ArrayList<String[]> testData = getTestData("resources/test-data.csv");
			
			for(String[] s : testData){
				if(s[0].equals("positive")){
					if(posClassifier.classify(s[1])){
						nCorrect++;
					}
				}
				else if(s[0].equals("negative")){
					if(negClassifier.classify(s[1])){
						nCorrect++;
					}
				}
				else if(s[0].equals("neutral")){
					if(neutralClassifier.classify(s[1])){
						nCorrect++;
					}
				}
			}
			
			System.out.println((float)(nCorrect)/(float)(testData.size()));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
