/**
 * 
 */
package info.eugenijus.wordfrequency;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Eug
 *
 */
public class TestWordFrequency {
	
	private WordFrequency wf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		wf = new WordFrequency();
		//maybe create test files here
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		wf = null;
		//delete test files here
	}
	
	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequency#readFile(java.lang.String)}.
	 */
	@Test
	public void testReadFile() {
		String file_text = wf.readFile("lorem-ipsum.txt");
		assertNotNull(file_text);
		assertTrue(file_text.length() > 5600);
		System.out.println("Text has " + file_text.length() + " chars!");
	}

	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequency#getWordFrequenciesFromList(java.util.List)}.
	 */
	@Test
	public void testGetWordFrequenciesFromList() {
		//This list is unmodifiable
		List<String> strings_list = Arrays.asList("java", "Java", "z-lang", "javascript", "ruby", "angularjs",
				"python", "python", "assembler", "javascript", "JavaScript");
						
		Map<String, Integer> map = wf.getWordFrequenciesFromList(strings_list);
				
		//map.forEach((key, value) -> System.out.println(key + " " + value));
		
		assertTrue(map.get("java").intValue() == 2);
	}
	
	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequency#getWordFrequenciesFromArray(java.lang.String[])}.
	 */
	@Test
	public void testGetWordFrequenciesFromArray() {
		String[] strings_array = {"java", "Java", "z-lang", "javascript", "ruby", "angularjs",
				"python", "python", "assembler", "javascript", "JavaScript"};
						
		Map<String, Integer> map = wf.getWordFrequenciesFromArray(strings_array);
				
		assertTrue(map.get("java").intValue() == 2);
	}

	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequency#getWordFrequenciesFromFile(java.lang.String)}.
	 */
	@Test
	public void testGetWordFrequenciesFromFile() {
		Map<String, Integer> map = wf.getWordFrequenciesFromFile("lorem-ipsum.txt");
		assertNotNull(map);
		//testing if the punctuations were removed
		assertFalse(map.containsKey("accumsan."));
		assertFalse(map.containsKey("curae;"));
		assertFalse(map.containsKey("amet!"));
		assertFalse(map.containsKey("elit?"));
		
		List<Map<String, Integer>> list_of_maps = wf.splitIntoMaps(map);
		assertTrue(list_of_maps.size() == 4);
		
		System.out.println("Words with frequencies split into 4 lists:");
		for(Map<String, Integer> result : list_of_maps) {
			assertTrue(result.size() > 0);
			System.out.println(result.toString());
		}
	}
	
	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequency#splitIntoFiles(java.util.Map)}.
	 */
	@Test
	public void testSplitIntoMaps() {
		String[] strings_array = {"java", "Java", "z-lang", "javascript", "angularjs", 
				"assembler", "javascript", "JavaScript"};
						
		Map<String, Integer> map = wf.getWordFrequenciesFromArray(strings_array);
		List<Map<String, Integer>> list_of_maps = wf.splitIntoMaps(map);
		
		//making sure there are 4 maps
		assertTrue(list_of_maps.size() == 4);
		//making sure each map has words
		assertTrue(list_of_maps.get(0).get("angularjs") == 1);
		assertTrue(list_of_maps.get(1).get("javascript") == 3);
		assertNull(list_of_maps.get(2).get("python"));
		assertTrue(list_of_maps.get(3).get("z-lang") == 1);
	}
	
	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequency#addAppendix(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddAppendix() {
		assertTrue(wf.addAppendix("failas.txt", "appendix").equals("failas_appendix.txt"));
		assertTrue(wf.addAppendix("failas.data", "appendix").equals("failas.data_appendix.txt"));
	}
}
