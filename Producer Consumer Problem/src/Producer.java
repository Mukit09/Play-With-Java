
public class Producer implements Runnable{

	MyData data;
	
	public Producer(MyData data) {

		this.data = data;
	}
	
	@Override
	public void run() {

		for(int i = 0; i < 10; i++) {
			
			data.put(i);
			System.out.println("Producer produced " + i);
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
