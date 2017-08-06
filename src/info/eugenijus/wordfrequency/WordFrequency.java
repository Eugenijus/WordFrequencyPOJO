package info.eugenijus.wordfrequency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
public class WordFrequency {
	private boolean extraConsolePrinting = true;
	
	public WordFrequency(boolean _consolePrinting) {
		extraConsolePrinting = _consolePrinting;
	}
	
	/**
	 * This method takes a String holding a filename,
	 * reads that file and returns it as a String
	 * @param filename - String object with filename
	 */
	public String readFile(String filename) {		
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(
					new FileReader(Constants.FOLDER+"/"+filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(" ");
				//if(consolePrinting) System.out.println(line);
			}

		} catch (FileNotFoundException e) {
			System.out.println(">>>>>> Oops! I couldn't read file " + filename + "!");
			//e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			e.printStackTrace();
			return null;
		}
		System.out.println("File " + filename + " was read succesfully!");
		return sb.toString();
	}
	
	public boolean writeFile(String filename, Map<String, Integer> data) {
		boolean successfull = false;
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FOLDER+"/"+filename))){
			writer.write(data.toString());
			successfull = true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			successfull = false;
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			successfull = false;
			e.printStackTrace();
		}
		return successfull;
	}
		
	/**
	 * This method takes a list of English words and counts the frequency of those words
	 * Then it places them into Map
	 * see {@link info.eugenijus.wordfrequency.WordFrequency#getWordFrequenciesFromArray(java.lang.String[])}
	 */
	public Map<String, Integer> getWordFrequenciesFromList(List<String> strings_list){
		return getWordFrequenciesFromArray((String[]) strings_list.toArray());
	}
	
	/**
	 * This method takes an array of English words and counts the frequency of those words
	 * Then it places them into Map
	 * @param words - an array of String objects which contain English words
	 * @return a <tt>Map&lt;String, Integer&gt;</tt> object with String words
	 *  and Integer frequencies
	 */
	public Map<String, Integer> getWordFrequenciesFromArray(String[] words) {
		//using Treemap so that keys (words) get alphabetically places
		//this will be handy when placing words into 4 separate files
		Map<String, Integer> map = new TreeMap<String, Integer>();
		if(words.length > 0) {
			for(String word : words) {
				if(!word.isEmpty()) {
					word = word.toLowerCase();		//much faster than list.replaceALl
					//if the word exists in the map, then increase freq by 1
					if(map.containsKey(word)) {
						map.put(word, map.get(word)+1);
					} else {
						map.put(word, 1);
					}
				}				
			}
			if(extraConsolePrinting) {
				System.out.println("Word Frequencies counted successfully!");
				System.out.println(map.toString());
			}
		} else {
			System.out.println("The provided word list is empty!");
		}
		if(extraConsolePrinting) System.out.println();
		return map;
	}
	
	/**
	 * This method takes a filename (String) where English words are stored
	 * It then opens it, reads it and counts the frequency of those words
	 * Then it places them into Map
	 * @param filename - String object with filename
	 * @return a <tt>Map&lt;String, Integer&gt;</tt> object with String words
	 *  and Integer frequencies
	 * see {@link info.eugenijus.wordfrequency.WordFrequency#getWordFrequenciesFromArray(java.lang.String[])}
	 */
	public Map<String, Integer> getWordFrequenciesFromFile(String filename){
		String text = readFile(filename);
		Map<String, Integer> map = null;
		if(text != null) {
			//Splitting the text and getting rid of punctuation
			String[] words = text.split(Constants.DELIMETER_REGEX);
			map = getWordFrequenciesFromArray(words);
		}		
		return map;
	}
	
	/**
	 * This method takes a map where English words are saved
	 * It iterates it and reads every key, then puts it in one of the four
	 *  lists based on the first letter {A-G, H-N, O-U, V-Z)
	 * Then it returns a List of these four Maps
	 * @param filename - String object with filename
	 * @return a <tt>Map&lt;String, Integer&gt;</tt> object with String words
	 *  and Integer frequencies
	 */
	public List<Map<String, Integer>> splitIntoMaps(Map<String, Integer> map) {
		//put into four different lists
		List<Map<String, Integer>> list = new ArrayList<>();
		list.add(new HashMap<String, Integer>());
		list.add(new HashMap<String, Integer>());
		list.add(new HashMap<String, Integer>());
		list.add(new HashMap<String, Integer>());
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    char first_letter = entry.getKey().charAt(0);
		    //splitting into four lists: {A-G, H-N, O-U, V-Z)
		    if(first_letter >= Constants.SMALL_A && first_letter <= Constants.SMALL_G) {
		    	list.get(0).put(entry.getKey(), entry.getValue());
		    } else if (first_letter >= Constants.SMALL_H && first_letter <= Constants.SMALL_N) {
		    	list.get(1).put(entry.getKey(), entry.getValue());
		    } else if (first_letter >= Constants.SMALL_O && first_letter <= Constants.SMALL_U) {
		    	list.get(2).put(entry.getKey(), entry.getValue());
		    } else if (first_letter >= Constants.SMALL_V && first_letter <= Constants.SMALL_Z) {
		    	list.get(3).put(entry.getKey(), entry.getValue());
		    }
		}
		
		if(extraConsolePrinting) {
			System.out.println("Words with frequencies split into 4 lists:");
			for(Map<String, Integer> result : list) {
				System.out.println(result.toString());
			}
			System.out.println();
		}
		return list;		
	}
	
	/**
	 * Adds some appendix to a file, e.g. file.txt + "_A-C" becomes "file_A-C.txt"
	 * If filename does not contain .txt, like file.data, then it becomes "file.data_appendix.txt"
	 * @param filename
	 * @param file_appendix
	 * @return - string of correct file name
	 */
	public String addAppendix(String filename, String file_appendix) {
		StringBuilder filename_tmp = new StringBuilder();		
		//lets see if the file name has .txt extension
		if(filename.endsWith(".txt")) {
			filename_tmp.append(filename.substring(0, filename.indexOf('.')));
		} else {
			filename_tmp.append(filename);
		}
		filename_tmp.append("_").append(file_appendix);
		filename_tmp.append(".txt"); 
		
		if(extraConsolePrinting) System.out.println("filename_tmp: " + filename_tmp);
		return filename_tmp.toString();
	}
	
	public boolean splitResultIntoFiles(String filename, List<Map<String, Integer>> result_maps) {
		boolean successfull = true;
		for (int i = 0; i < Constants.FILE_APPENDIXES.length; i++) {
			if(result_maps.get(i) != null && result_maps.get(i).size() > 0) {
				String result_file_name = addAppendix(filename, Constants.FILE_APPENDIXES[i]);
				successfull = successfull && writeFile(result_file_name, result_maps.get(i));
			}
		}
		if(successfull) {
			System.out.println("All files written successfully!");
		} else {
			System.out.println("Some of the files failed to write!");
		}
		return successfull;
	}

	public boolean isExtraConsolePrinting() {
		return extraConsolePrinting;
	}
}
