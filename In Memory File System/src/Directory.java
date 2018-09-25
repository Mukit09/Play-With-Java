import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Directory extends Content {

	List<Content> contentList;
	
	public Directory(String name) {
		
		super(name);
		contentList = new ArrayList<Content>();
	}

	public void delete(String contentName) {
		
		for(Content content : contentList) {
			if(contentName.equals(content.name)) {
			
				contentList.remove(content);
				break;
			}
		}
	}

	public String getName() {

		return this.name;
	}
	
	public void presentWorkingDirectory(LinkedList<Directory> directoryList) {
		
		for(Directory directory : directoryList) {
			
			System.out.print("/" + directory.name);
		}
		System.out.println("");
	}
	
	public void showListSegments() {
		
		for(Content content : contentList) {
			System.out.println(content.getName());
		}
	}
	
	private boolean hasChildDirectory(String directoryName) {
		
		for(Content content : contentList) {
			
			if(Directory.class.getName() == content.getClass().getName()) {
				
				if(directoryName.equals(content.getName())) 
					return true;
			}
		}
		return false;
	}
	
	private Directory getDirectory(String directoryName) {
		
		for(Content content : contentList) {
			
			if(Directory.class.getName() == content.getClass().getName()) {
				
				if(directoryName.equals(content.getName()))
					return (Directory) content;
			}
		}
		return this;
	}
	
	public Directory changeDirectory(String directoryName) {
		
		Directory directory = this;
		if(hasChildDirectory(directoryName)) 
			directory = getDirectory(directoryName);
		else
			System.out.println(getName() + " has no directory named " + directoryName);
		return directory;
	}
	
	public void addContentIntoList(Content content) {
		
		this.contentList.add(content);
	}
	
	public int calculateSizes() {
		
		int size = 0;
		Directory directory;
		
		for(Content content : contentList) {
			
			if(Directory.class.getName() == content.getClass().getName()) {
				
				directory = (Directory) content;
				size += directory.calculateSizes();
			}
			else
				size += content.size;
		}
		this.size = size;
		return size;
	}
	
	public boolean checkIfChildContent(String contentName) {
		
		for(Content content : contentList) {
			if(contentName.equals(content.name))
				return true;
		}
		return false;
	}
	
	public int getSizeofContent(String contentName) {
		
		for(Content content : contentList) {
			if(contentName.equals(content.name))
				return content.size;
		}
		return 0;
	}
}
