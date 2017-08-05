package info.eugenijus.wordfrequency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
			word = word.toLowerCase();	//much faster than list.replaceALl
			if(word.charAt(word.length()-1) == ',') {
				System.out.print(word + " is trimmed to: ");
				word = word.substring(0, word.length()-2);
				System.out.println(word);
			}
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
		list.add(new HashMap<String, Integer>());
		list.add(new HashMap<String, Integer>());
		list.add(new HashMap<String, Integer>());
		list.add(new HashMap<String, Integer>());
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    char first_letter = entry.getKey().charAt(0);
		    if(first_letter >= Constants.SMALL_A && first_letter <= Constants.SMALL_G) {
		    	list.get(0).put(entry.getKey(), entry.getValue());
		    } else if (first_letter >= Constants.SMALL_H && first_letter <= Constants.SMALL_N) {
		    	list.get(1).put(entry.getKey(), entry.getValue());
		    } else if (first_letter >= Constants.SMALL_O && first_letter <= Constants.SMALL_U) {
		    	list.get(2).put(entry.getKey(), entry.getValue());
		    } else if (first_letter >= Constants.SMALL_V && first_letter <= Constants.SMALL_Z) {
		    	list.get(3).put(entry.getKey(), entry.getValue());
		    }
		    // Integer value = entry.getValue();
		}
		
		return list;		
	}
	
	/**
	 * TODO this is draft method
	 */
	public String readFile(String filename) {		
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(Constants.FOLDER+"/"+filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(" ");
				//System.out.println(line);
			}

		} catch (IOException e) {
			System.out.println("Oops! I couldn't read file " + filename + " sorry! :S");
			e.printStackTrace();
		}
		System.out.println("File " + filename + " was read succesfully!");
		return sb.toString();
	}
		
	/**
	 * TODO this is draft method
	 */
	public Map<String, Integer> getWordFrequencies(String wordFileName){
		String text = readFile(wordFileName);
		String[] words = text.split(Constants.DELIMETER_REGEX);
		Map<String, Integer> map = getWordFrequenciesFromArray(words);
		return map;
	}

	public Map<String, Integer> getWordFrequenciesFromArray(String[] words) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		if(words.length > 0) {
			for(String word : words) {
				if(!word.isEmpty()) {
					word = word.toLowerCase();		//much faster than list.replaceALl
					/*
					if(word.charAt(word.length()-1) == ',') {
						System.out.print(word + " is trimmed to: ");
						word = word.substring(0, word.length()-1);
						System.out.println(word);
					} */
					if(map.containsKey(word)) {
						map.put(word, map.get(word)+1);
					} else {
						map.put(word, 1);
					}
				}				
			}
			System.out.println("Word Frequencies counted successfully!");
			System.out.println(map.toString());
			System.out.println();
		} else {
			System.out.println("The provided word list is empty!");
			System.out.println(map.toString());
			System.out.println();
		}
		return map;
	}

	public static void main(String[] args) {
		WordFrequencyMain wf = new WordFrequencyMain();
		Map<String, Integer> map = wf.getWordFrequencies("lorem-ipsum.txt");
		List<Map<String, Integer>> result_maps = wf.splitIntoFiles(map);
		
		System.out.println("Words with frequencies split into 4 lists:");
		for(Map<String, Integer> result : result_maps) {
			System.out.println(result.toString());
		}
	}

}
