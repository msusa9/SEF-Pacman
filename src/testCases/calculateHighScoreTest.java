package testCases;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.calculateHighScore;

public class calculateHighScoreTest {

	calculateHighScore calHS;
	TreeMap<String,Integer> testMap;
	LinkedHashMap<String,Integer> finalMap;
	LinkedHashMap<String,Integer> compareMap;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		calHS = new calculateHighScore();
		testMap = new TreeMap<String,Integer>();
		finalMap = new LinkedHashMap<String,Integer>();
		compareMap = new LinkedHashMap<String,Integer>();
		
		compareMap.put("Paul", 700);
		compareMap.put("Gareth", 500);
		compareMap.put("John", 300);
		compareMap.put("Mitchell", 100);
		compareMap.put("Howard", 50);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void sortedSortMapAfter() throws Exception {
		
	}
	
	/*
	 * Expected output is a map sorted with values added
	 */
	@Test
	public void sortedSortMap() {
		testMap.put("Mitchell", 100);
		testMap.put("Gareth", 500);
		testMap.put("John", 300);
		testMap.put("Paul", 700);
		testMap.put("Howard", 50);
		for(Entry<String, Integer> val : calHS.sortByValue(testMap).descendingMap().entrySet()) {
			finalMap.put(val.getKey(), val.getValue());
		}
		assertEquals(compareMap, finalMap);
	}
	
	/*
	 * Expected output is a map sorted with 2 values added, "Jesus, 400" and "Mitchell, 365"
	 * Also these 2 values are added to scores.txt and any previous mentions of name are deleted
	 */
	@Test
	public void addScore() {
		for(Entry<String, Integer> val : calHS.scoreMap().entrySet()) {
			finalMap.put(val.getKey(), val.getValue());
		}
		calHS.addScore("Jesus", 400);
		calHS.addScore("Mitchell", 365);
		calHS.addScoresToMap();
		Assert.assertNotEquals(finalMap, calHS.scoreMap());
	}

}
