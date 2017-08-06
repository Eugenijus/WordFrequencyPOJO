package info.eugenijus.wordfrequency;

import java.util.List;
import java.util.Map;

public class Handler extends Thread{
	private String filename = "";
	private WordFrequency wf;
	
	public Handler(String _filename, boolean extraPrint) {
		filename = _filename;
		wf = new WordFrequency(extraPrint);
	}
	
	@Override
	public void run() {
		System.out.println(this.getName() + ": Processing file " + filename + " from command line input");
		Map<String, Integer> map = wf.getWordFrequenciesFromFile(filename);
		List<Map<String, Integer>> result_maps = wf.splitIntoMaps(map);
		wf.splitResultIntoFiles(filename, result_maps);
		System.out.println(this.getName() + "========================================\n");
	}

}
