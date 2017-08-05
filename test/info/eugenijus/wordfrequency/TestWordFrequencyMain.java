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
public class TestWordFrequencyMain {
	
	private WordFrequencyMain wf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		wf = new WordFrequencyMain();
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
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequencyMain#getWordFrequenciesFromList(java.util.List)}.
	 */
	@Test
	public void getWordFrequenciesFromList() {
		//This list is unmodifiable
		List<String> strings_list = Arrays.asList("java", "Java", "z-lang", "javascript", "ruby", "angularjs",
				"python", "python", "assembler", "javascript", "JavaScript");
						
		Map<String, Integer> map = wf.getWordFrequenciesFromList(strings_list);
				
		System.out.println(map.toString());
		
		//map.forEach((key, value) -> System.out.println(key + " " + value));
		
		assertTrue(map.get("java").intValue() == 2);
	}
		
	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequencyMain#readFile(java.lang.String)}.
	 */
	@Test
	public void testReadFile() {
		assertNotNull(wf.readFile("lorem-ipsum.txt"));
	}

	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequencyMain#getWordFrequencies(java.lang.String)}.
	 */
	@Test
	public void testGetWordFrequencies() {
		Map<String, Integer> map = wf.getWordFrequencies("lorem-ipsum.txt");
		assertNotNull(map);
		assertFalse(map.containsKey("accumsan."));
		assertFalse(map.containsKey("curae;"));
		assertFalse(map.containsKey("amet!"));
		assertFalse(map.containsKey("elit?"));
		
		List<Map<String, Integer>> result_maps = wf.splitIntoFiles(map);
		assertTrue(result_maps.size() == 4);
		
		System.out.println("Words with frequencies split into 4 lists:");
		for(Map<String, Integer> result : result_maps) {
			assertTrue(result.size() > 0);
			System.out.println(result.toString());
		}
	}
}
