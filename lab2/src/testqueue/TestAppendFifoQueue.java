package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	private FifoQueue<Integer> testTom;

	@Before
	public void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
		testTom = new FifoQueue<Integer>();
	}

	// testa att lägga till två tomma köer samt slå dem samman med append
	@Test
	public void testTvaTommaKoer() {
		assertTrue(q1.isEmpty());
		assertTrue(q2.isEmpty());
		try {
			q1.append(q2);
			fail("Should raise NoSuchElementException");
		} catch (IllegalArgumentException e) {
		}
	}

	// tom kö som slås samman med en icke tom kö
	@Test
	public void testTomKoIckeTomKo() {
		q2.offer(3);
		q2.offer(2);
		q2.offer(1);
		FifoQueue<Integer> temp = q2;
		q1.append(q2);
		assertEquals("funkar ej att slå samman tom kö med icke tom kö", temp.element(), q1.element());
	}

	@Test
	public void testIckeTomKoTomKo() {
		q1.offer(3);
		q1.offer(2);
		q1.offer(1);
		q1.append(q2);
		FifoQueue<Integer> temp = q1;
		q1.append(q2);
		assertEquals("funkar ej att slå samman icke tom kö med tom kö", temp.element(), q1.element());
		assertEquals("q2 är inte tom", testTom.size(), q2.size());
	}

	public void testTvaIckeTomma() {
		q1.offer(3);
		q1.offer(2);
		q1.offer(1);
		q2.offer(3);
		q2.offer(2);
		q2.offer(1);
		FifoQueue<Integer> temp = q1;
		q1.append(q2);
		assertEquals("funkar ej att slå samman två icke tomma köer", temp.element(), q1.element());
	}

	public void testSammaKo() {
		q1.offer(3);
		q1.offer(2);
		q1.offer(1);
		try {
			q1.append(q1);
			fail("Should raise NoSuchElementException");
		} catch (IllegalArgumentException e) {
		}
	}

	@After
	public void tearDown() {
	}

}
