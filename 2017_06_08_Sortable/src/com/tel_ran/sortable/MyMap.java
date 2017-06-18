package com.tel_ran.sortable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyMap {
	HashMap<String, Integer> m = new HashMap<>();

	public MyMap() {
		super();
	}

	public HashMap<String, Integer> getMap() {
		return m;
	}

	void putString(String s) {
		Integer val = m.get(s);
		if (val != null) {
			val++;
			m.put(s, val);
		} else
			m.put(s, 1);
	}

	static public void displayOccurences(String[] storage) {
		HashMap<String, Integer> mapOccur;
		mapOccur = new HashMap<>();
		TreeMap<Integer, TreeSet<String>> res = new TreeMap<>(Comparator.reverseOrder());
		for (Map.Entry<String, Integer> entry : mapOccur.entrySet()) {
			addOccur(res, entry.getKey(), entry.getValue());
		}
		return res;
		/*
		 * for (String k : storage) { m.putString(k); } Set<Entry<String,
		 * Integer>> s = m.getMap().entrySet(); ArrayList<Integer> al =new
		 * ArrayList<>(); for(Entry<String, Integer> vs:s){ al.add(vs.getKey())
		 * 
		 * } System.out.println(s);
		 */
	}

	private static void addOccur(TreeMap<Integer, TreeSet<String>> res, String key, Integer value) {
		TreeSet<String> setStrings = res.get(value);
		if (setStrings == null) {
			setStrings = new TreeSet<>();
			res.put(value, setStrings);
		}
		setStrings.add(key);
	}

	public static void main(String[] args) {
		String[] keys = { "lmn", "abc", "abc", "lmn", "tt", "tt", "tt", "aa", "aa" };
		displayOccurences(keys);

	}
}
