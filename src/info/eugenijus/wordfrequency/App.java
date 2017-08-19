package info.eugenijus.wordfrequency;

public class App {

	/**
	 * Functional requirements of the assignment
	 *  <pre>
	 *  1. Application should be capable of reading N text files 
	 *  (where N is a number, could be 1, 2, 3...) which contain English words.
	 *  2. It should count the frequency of the words.
	 *  3. It should write results into 4 files where words are sorted based on first 
	 *  letter {A-G, H-N, O-U, V-Z).
	 *  </pre>
	 * @param args - array of strings of file names
	 */
	public static void main(String[] args) {	
		//int processors = Runtime.getRuntime().availableProcessors();
		//System.out.println("Number of processor cores: " + processors); //1, 2, 4..
		
		//if there are provided file name strings, then proceed with algorithms
		if(args.length > 0) {
			System.out.print("Input: ");
			for(String filename : args) {
				System.out.printf("%s ", filename);
			}
			System.out.println("\n");
			
			//this could be improved to have a Executor 
			//which only lets N threads to run where N = *processors*
			//but for now only using thread array
			Thread[] threadPool = new Thread[args.length];
			for(int i=0; i< args.length; i++) {
				threadPool[i] = new TaskHandler(args[i], false);
				threadPool[i].start();
			}
		}
		//if arguments array is empty, then use test file
		else {
			System.out.println("No CL input! Using default text file :)");
			TaskHandler th = new TaskHandler(Constants.file_prog_lang, true);
			th.start();
		}
	}
}
