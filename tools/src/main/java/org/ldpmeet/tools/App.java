package org.ldpmeet.tools;

public class App {
	public static int a = 0;
	public static int b = 0;
	private App() {
	};

	private static final App app = new App();

	public static App get() {
		return app;
	}

	public synchronized static void add() {
		a++;
		System.out.println("a===" + a);
	}
	public synchronized static void adda() {
		b++;
		System.out.println("b===" + b);
	}
	public static void main(String[] args) {
		 my();
		//you();
	}

	public static void you() {
		SuperMap<Integer, String> map = new SuperMap<>();
		for (int i = 0; i < 30; i++) {
			Thread b = new Thread(new ATest(map));
			b.start();
		}
	}

	public static void my() {
		SuperMap<Long, String> map = new SuperMap<>();
		// long my = System.currentTimeMillis();
		for (int i = 1; i < 16; i++) {
			map.initObj(i, String.valueOf(i));
		}
		map.delete(5);
		map.delete(6);
		map.delete(7);
		for (int i = 16; i < 46; i++) {
			map.initObj(i, String.valueOf(i));
		}
		map.delete(22);
		map.delete(28);
		map.delete(37);
		for (int i = 46; i < 66; i++) {
			map.initObj(i, String.valueOf(i));
		}
		map.delete(55);
		map.delete(61);
		StringBuilder bu = new StringBuilder();
		for (int k = 1; k < 66; k++) {
			String mr = map.get(k);
			bu.append((mr == null ? "null" : mr) + ",");
		}
		System.out.println(bu.toString());
		// long mr =System.currentTimeMillis();
		// long mc =mr-my;
		// System.out.println(mr-my);
	}
}

class ATest implements Runnable {
	int roomid = 0;
	SuperMap<Integer, String> map;

	ATest(SuperMap<Integer, String> map) {
		this.map = map;
	}

	@Override
	public void run() {
		for (int i = 1; i < 16; i++) {
			String mr = map.get(i);
			if (mr == null) {
				boolean bm = map.initObj(i, String.valueOf(i));
				if (!bm) {
					App.get().add();
				}
			}
		}
	}

}