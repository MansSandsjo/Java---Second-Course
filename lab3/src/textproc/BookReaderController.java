package textproc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.sun.glass.events.KeyEvent;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();


		SortedListModel<Map.Entry<String, Integer>> list = new SortedListModel<Map.Entry<String, Integer>>(
				counter.getWordList());
		JList<Entry<String, Integer>> listView = new JList<Entry<String, Integer>>(list);
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(300, 200));

		pane.add(scrollPane);

		JPanel pan = new JPanel();
		JButton buttonA = new JButton("Alphabetic");
		JButton buttonF = new JButton("Frequency");
		buttonF.setBorder(new LineBorder(Color.BLACK));
		buttonA.setBorder(new LineBorder(Color.GREEN));

		pane.add(pan, BorderLayout.SOUTH);
		pan.add(buttonF);
		pan.add(buttonA);

		// pane är en behållarkomponent till vilken de övriga komponenterna
		// (listvy, knappar etc.) ska läggas till.
		frame.pack();
		frame.setVisible(true);

		buttonA.addActionListener(par -> {
			list.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
		});

		buttonF.addActionListener(par -> {
			list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
		});

		JTextField text = new JTextField(15);
		JPanel panSearch = new JPanel();
		JButton searchB = new JButton("Search");
		pane.add(panSearch);
		panSearch.add(searchB, BorderLayout.BEFORE_FIRST_LINE);
		panSearch.add(text, BorderLayout.BEFORE_FIRST_LINE);

		text.addActionListener(bom -> {
			KeyEvent e = new KeyEvent();
			if (e.VK_ENTER == KeyEvent.VK_ENTER) {
				searchB.doClick();
			}
		});

		searchB.addActionListener(par -> {
			String s = text.getText().toLowerCase().trim();
			for (int n = 0; n < list.getSize(); n++) {
				if (s.equals(list.getElementAt(n).getKey())) {
					listView.ensureIndexIsVisible(n);
					listView.setSelectedIndex(n);
				} else if (n == list.getSize() ) {
					JOptionPane.showMessageDialog(pane, "No result, please try again");
				}
			}
		});

	}

}
