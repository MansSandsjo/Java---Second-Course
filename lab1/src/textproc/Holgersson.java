package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor n = new SingleWordCounter("norge"); 
	//	TextProcessor r = new MultiWordCounter(REGIONS);
	
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));  
		Set<String> stopwords = new HashSet<>(); 
		
		while(scan.hasNext()) {
			stopwords.add(scan.next()); 
		}
		TextProcessor r2 = new GeneralWordCounter(stopwords); 
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
		//	p.process(word);
		//	n.process(word); 
		//	r.process(word);
			r2.process(word);
		}
		
		s.close();
		//p.report();
		//n.report();
		//r.report();
		r2.report();
		
		long t1 = System.nanoTime(); 
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms"); 
	}
	
	
}
