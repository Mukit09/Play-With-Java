import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Queue<Integer> queue1=new LinkedList();
		Queue<Integer> queue2=new LinkedList();
		Queue<Integer> queue3;
		
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int number, i;
		
		for(i=0;i<n;i++) {
			number=sc.nextInt();
			queue1.offer(number);  // offer is equivalent to C++ push()
			System.out.println("Input: " + number);
		}
		
		System.out.println(queue1.size());
		
		while(!queue1.isEmpty()) {
			
			if(queue1.size()>1) {
				queue2.offer(queue1.peek());
				queue1.poll();
			}
			else {
				System.out.println("Output: " + queue1.peek());
				queue1.poll();
				queue3=queue1;
				queue1=queue2;
				queue2=queue3;
			}
		}

	}
}
