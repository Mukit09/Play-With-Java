
public class Main {
	
	Node root;
	
	public void insert(int tmpValue){
		
		Node tmpNode=new Node(tmpValue);
		tmpNode.next=root;
		root=tmpNode;
	}
	
	public void showNodes(Node now){
		if(now==null)
			return;
		System.out.println(now.value);
		showNodes(now.next);
	}
	
	public void deleteNode(int value, Node tmp){
		
		if(tmp.value==value){
			root=root.next;
			return;
		}
		while(true){
			
			if(tmp==null)
				break;
			if(tmp.next.value==value){
				tmp.next=tmp.next.next;
				break;
			}
			tmp=tmp.next;
		}
	}

	public static void main(String[] args) {
		
		Main obj=new Main();
		obj.root=new Node(100);
		
		for(int i=1;i<=10;i++){
			obj.insert(i);
		}
		System.out.println("Before Deletion");
		obj.showNodes(obj.root);
		
		obj.deleteNode(5, obj.root);
		
		System.out.println("After Deleting 5");
		obj.showNodes(obj.root);
		
		obj.deleteNode(10, obj.root);
		
		System.out.println("After Deleting 10");
		obj.showNodes(obj.root);
		
		obj.deleteNode(100, obj.root);
		
		System.out.println("After Deleting 100");
		obj.showNodes(obj.root);
	}

}
