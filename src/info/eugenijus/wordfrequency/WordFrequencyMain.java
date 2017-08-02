package info.eugenijus.wordfrequency;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eug
 *
 */
public class WordFrequencyMain {
	
	public Map<String, Integer> getWordFrequenciesFromCollection(List<String> strings_list){
		
		//Creating a separate list to iterate and update
		List<String> list = new ArrayList<>(strings_list);
		
		//this is too slow
		//list.replaceAll(String::toLowerCase);
		
		//lets sort the list
		Comparator<String> c = Comparator.naturalOrder();
		//list.sort(c);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
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
	
	public File readFile(String filename) {
		
		return null;
	}
	
	public Map<String, Integer> getWordFrequencies(String wordFileName){
		
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
