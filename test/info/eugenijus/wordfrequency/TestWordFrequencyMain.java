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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link info.eugenijus.wordfrequency.WordFrequencyMain#getWordFrequencies(java.lang.String)}.
	 */
	@Test
	public void testGetWordFrequencies() {
		fail("Not yet implemented");
	}

}
