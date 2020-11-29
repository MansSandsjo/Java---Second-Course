package textproc;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class BookReaderApplication {
	
	public static void main(String args[]) throws FileNotFoundException {
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Set<String> stopwords = new HashSet<>(); 
		
		
		Scanner scan = new Scanner(new File("undantagsord.txt")); 
		while(scan.hasNext()) {
			stopwords.add(scan.next()); 
		}
		GeneralWordCounter r2 = new GeneralWordCounter(stopwords); 

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r2.process(word);
		}
		s.close();
		r2.report();
		
		new BookReaderController(r2);
		
	}

}