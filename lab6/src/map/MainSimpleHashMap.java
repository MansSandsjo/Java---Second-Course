package map;

public class MainSimpleHashMap {

	public static void main(String[] args) {
SimpleHashMap<Integer,Integer> map = new SimpleHashMap<Integer,Integer>();
map.put(10, 20);
map.put(30, 20);
map.put(55, 2);
map.put(1, 33);
map.put(3, 61);
map.put(5, 31);
System.out.print(map.show());
	}

}
