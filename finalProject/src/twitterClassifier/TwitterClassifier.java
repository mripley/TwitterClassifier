package twitterClassifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public abstract class TwitterClassifier {
	
    protected RAMDirectory index = new RAMDirectory();

    
	public TwitterClassifier(){
		index = new RAMDirectory();		// Create a new index in RAM
	}
	
	public abstract boolean classify(String query) throws Exception;
	protected abstract void trainClassifier(String trainingFile) throws Exception;
	
	protected void biuldIndexFromCSV(String trainingFile){
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(trainingFile));
			
			// create a new index
			IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_34, new StandardAnalyzer(Version.LUCENE_34));
			IndexWriter writer = new IndexWriter(index, indexConfig);
			
			String currentLine;
			String text;
			
			while((currentLine = reader.readLine()) != null){
				text = new String("");
				String[] splitLine = currentLine.split(",");
				// grab the category 
				String category = splitLine[0].toLowerCase().trim();
				
				// concatenate the rest of the array back into the original document
				for(int i=1; i< splitLine.length; i++){
					text += splitLine[i];
				}
				addDocument(writer, text, category);				
			}
			writer.commit();
			writer.optimize();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + trainingFile + " not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Caught IO Exception in BuildIndex");
			e.printStackTrace();
		}
	}
	
	public static void addDocument(IndexWriter writer, String text, String sentiment){
		Document doc = new Document();
		doc.add(new Field("text", cleanText(text), Store.YES, Index.ANALYZED));
		doc.add(new Field("sentiment", sentiment, Store.YES, Index.ANALYZED));
		
		try {
			writer.addDocument(doc);
		} catch (CorruptIndexException e) {
			System.out.println("Corrupt index in addDocument");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception in addDocument");
			e.printStackTrace();
		}
	}
	
	public static String cleanText(String input){
		// replace all links with empty strings
		String retval = input.replaceAll("(https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?)", "");
		
		// replace all @ references
		retval = retval.replaceAll("@[\\w*\\d([\\^$()#%)]]*", "");
		
		// replace all RT's
		retval = retval.replaceAll("RT:?", "");
		
		retval = retval.replaceAll("\\d", "");
		
		// remove all hash tags
		retval = retval.replaceAll("#\\w*", "");
		
		retval = retval.replaceAll("^\\w*", "");
		return retval;
	}
	
	public RAMDirectory getIndex() {
		return index;
	}

	public void setIndex(RAMDirectory index) {
		this.index = index;
	}
}
