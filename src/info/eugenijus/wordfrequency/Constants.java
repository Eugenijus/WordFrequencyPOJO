package info.eugenijus.wordfrequency;

public class Constants {
	//regex to check for punctuation marks (testing => https://regex101.com/)
	public static final String DELIMETER_REGEX = "\\.|\\,|\\;|\\s+|\\?|\\!|\\:|\\-";
	protected static final String FOLDER = "text-files";
	public static final String file_lorem_ipsum = "lorem-ipsum.txt";
	public static final String file_prog_lang = "prog-lang.txt";

	public static final String [] FILE_APPENDIXES = {"A-G", "H-N", "O-U", "V-Z"};
			
	public static final char SMALL_A = 97;
	public static final char SMALL_G = 103;
	
	public static final char SMALL_H = 104;
	public static final char SMALL_N = 110;
	
	public static final char SMALL_O = 111;
	public static final char SMALL_U = 117;
	
	public static final char SMALL_V = 118;
	public static final char SMALL_Z = 122;
}
