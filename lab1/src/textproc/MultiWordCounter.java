package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	
	private Map<String, Integer> m; 

	public MultiWordCounter(String[] REGIONS) {
		this.m = new HashMap<>(); 
		for(String i : REGIONS) {
			m.put(i, 0); 
		}
	}
	@Override
	public void process(String word) {
		// TODO Auto-generated method stub
		
		for(String Key : m.keySet()) {
			if(word.equals((Key))) {
				m.replace(Key, m.get(Key), m.get(Key)+1); 
			}
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		for (String Key : m.keySet()) {
			System.out.println(Key + ": " +  m.get(Key));
		}
		
	}
}
