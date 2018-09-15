
public class Consumer implements Runnable {

	MyData data;
	
	public Consumer(MyData data) {
		
		this.data = data;
	}
	
	@Override
	public void run() {
	
		int value;
		for(int i = 0; i < 10; i++) {
			
			value = data.get();
			System.out.println("Consumer consumed " + value);
			sleepInSeconds(1);
		}
	}
	
	private void sleepInSeconds(int second) {
		
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
