package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private int capacity;
	private double loadFactor;
	private Entry<K, V>[] table;
	private int size;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and
	 * the default load factor (0.75).
	 */
	public SimpleHashMap() {
		capacity = 16;
		this.loadFactor = 0.75;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		loadFactor = 0.75;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	// return value for key
	@Override
	public V get(Object obj) {
		K key = (K) obj;
		Entry<K, V> nextOne = find(index(key), key);
		if (nextOne != null) {
			return nextOne.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	// Om det fanns ett gammalt värde ska detta returneras.
	// Annars returneras null. Tänk på att fyllnadsgraden inte ska överstiga
	// 0.75
	// och öka kapaciteten om så är fallet. Det är lämpligt att skriva en privat
	// metod rehash för detta.

	@Override
	public V put(K key, V value) {
		Entry<K, V> temp = find(index(key), key);
		if (temp != null) {
			return temp.setVandReturnOld(value);
		}
		temp = new Entry<K, V>(key, value);

		// listan har värden peka på listan
		if (table[index(key)] != null) {
			temp.next = table[index(key)];
		}
		table[index(key)] = temp;
		size++;
		rehash();

		return null;
	}

	private void rehash() {
		Entry <K,V>[] oldtable = table;
		if (size  >= 0.75* capacity) {
			capacity *=2;
			table = (Entry<K, V>[]) new Entry[capacity];
			for (Entry <K,V> e : oldtable){
				put(e.key,e.value);
				e=e.next;
//				size--;
			}
		}

	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		Entry<K,V> tillf = find(index(key), key);
		//om listan är tom return null
		if(table[index(key)]==null){
			return null;
		}
		//om nyckeln inte finns ska man returnera null
		if(tillf==null){
			return null;
		}
		//om första elementet ska tas bort returnera value som tas bort
		if(table[index(key)].getKey().equals(key)){
			V old = table[index(key)].setVandReturnOld(null);
			Entry<K,V> e = table[index(key)];
			table[index(key)] = e.next;
			size--;
			return old;
		}
		//om elementet finns senare i listan 
		Entry<K, V> preEl = table[index(key)];
		Entry<K, V> curEl = table[index(key)].next;
		while (curEl != null) {
			if (curEl.getKey().equals(key)) {
				preEl.next = curEl.next;
				size--;
				return curEl.getValue();
			}
			preEl = curEl;
			curEl = curEl.next;
			
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	// Ger en sträng med innehållet på varje position i tabellen på egen rad.
	// 0 key=value key=value etc.
	// 1 key=value key=value etc.
	// ...
	public String show() {
		StringBuilder s = new StringBuilder();
		for (int n = 0; n < table.length; n++) {
			s.append(n);
			s.append("\t");
			Entry<K, V> entry = table[n];
			while (entry != null) {
				s.append(entry.toString() + " ");
				entry = entry.next;
			}
			s.append("\n");
		}
		return s.toString();

	}

	// returnera det index som ska användas för nyckeln key.
	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	// returnera det Entry-par som har nyckeln key i listan
	// som finns på position index i tabellen.
	// Om det inte finns något sådant ska metoden returnera null.
	private Entry<K, V> find(int index, K key) {
		Entry<K, V> entry = table[index];
		if (entry == null) {
			return null;
		}
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				return entry;
			}
			entry = entry.next;
		}
		return null;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		private Entry(K key, V value) {
			this.key = key;
			this.value = value;

		}

		@Override
		public String toString() {
			return key + "=" + value;

		}

		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setVandReturnOld(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

	}

}
