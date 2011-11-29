package twitterClassifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.DocIdSet; 

public class LuceneClassifier extends TwitterClassifier {

	private boolean topFeatures;
	private boolean overfit;
	private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
	private HashMap<String, double[]> wordSet;
	
	public LuceneClassifier(String trainingFile){
		// build the index
		buildIndex(trainingFile);
		trainClassifier(trainingFile);
	}
	
	@Override
	public void classify(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void trainClassifier(String trainingFile){
		wordSet = new HashMap<String, double[]>();
		IndexReader reader = null;
		
		try {
			reader = IndexReader.open(index);
			
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// return the set of ID's that match the given category
	private Set<Integer> getMatchingDocs(IndexReader reader, String category){
	    Set<Integer> matchedDocIds = new HashSet<Integer>();
	    try {
	    	Filter categoryFilter = new CachingWrapperFilter(
			      new QueryWrapperFilter(new TermQuery(
			      new Term("sentiment", "positive"))));
		
		    DocIdSet docIdSet;
		    docIdSet = categoryFilter.getDocIdSet(reader);
		    DocIdSetIterator docIdSetIterator;
		    docIdSetIterator = docIdSet.iterator();
		    int docId;

			while ((docId = docIdSetIterator.nextDoc()) != DocIdSetIterator.NO_MORE_DOCS) {
				matchedDocIds.add(docId);
			}
		} catch (IOException e) {
			System.out.println("Caught IO Exception in getMachingDOcs");
			e.printStackTrace();
		}
	    return matchedDocIds;
	}
}
