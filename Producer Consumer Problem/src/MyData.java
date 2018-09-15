
public class MyData {

	private int value;
	private boolean available;
	
	public MyData() {

		available = false;
	}

	public synchronized int get() {
		
		while(available == false) {
			try {
				System.out.println("waited called from Consumer");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		available = false;
		notifyAll();
		return value;
	}
	
	public synchronized void put(int value) {
		
		while(available == true) {
			try {
				System.out.println("waited called from Producer");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.value = value;
		available = true;
		notifyAll();
	}
}
