package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		// TODO Auto-generated method stub

		if (o1.getValue() < o2.getValue()) {
			return 1;
		} else if (o1.getValue() > o2.getValue()) {
			return -1;
		} else if (o1.getValue().equals(o2.getValue())) {

				if (o1.getKey().compareTo(o2.getKey())<0) {
					return -1;
				}
				else if (o1.getKey().compareTo(o2.getKey())>0) {
					return 1;
				}
				else return 0;
			}
		 else {
			return 0;
		}
		
	}

}
