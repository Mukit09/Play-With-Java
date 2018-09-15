
public class Main {

	public static void main(String[] args) {

		MyData data = new MyData();
		
		Consumer consumer = new Consumer(data);
		Thread t1 = new Thread(consumer);
		t1.start();
		
		Producer producer = new Producer(data);
		Thread t2 = new Thread(producer);
		t2.start();
	}
}
