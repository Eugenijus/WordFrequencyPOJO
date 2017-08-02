package info.eugenijus.wordfrequency;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Eug
 *
 */
public class WordFrequencyMain {
	
	
	
	/**
	 * This method takes a list of English words and counts the frequency of those words
	 * Then it places them into Map
	 * @param strings_list - a list of String objects which contain English words
	 * @return a <tt>Map&lt;String, Integer&gt;</tt> object with String words and Integer frequencies
	 */
	public Map<String, Integer> getWordFrequenciesFromList(List<String> strings_list){
		
		//Creating a separate list to iterate and update
		List<String> list = new ArrayList<>(strings_list);
		
		//this is too slow
		//list.replaceAll(String::toLowerCase);
		
		//lets sort the list
		//Comparator<String> c = Comparator.naturalOrder();
		//list.sort(c);
		
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		for(String word : list) {
			word = word.toLowerCase();		//much faster than list.replaceALl
			if(map.containsKey(word)) {
				map.put(word, map.get(word)+1);
			} else {
				map.put(word, 1);
			}
		}
		return map;
	}
	
	/**
	 * TODO this is draft method
	 */
	public List<Map<String, Integer>> splitIntoFiles(Map<String, Integer> map) {
		//put into four different lists
		List<Map<String, Integer>> list = new ArrayList<>();
		list.get(0);
		
		return list;		
	}
	
	/**
	 * TODO this is draft method
	 */
	public File readFile(String filename) {
		
		return null;
	}
	
	/**
	 * TODO this is draft method
	 */
	public Map<String, Integer> getWordFrequencies(String wordFileName){
		
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
