import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MemoryFileSystem {

	private static final int COMMAND_WORD_NUMBER = 2;
	
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		LinkedList<Directory> directoryList = new LinkedList<>();
		
		String command;
		
		System.out.println("You have following commands:");
		System.out.println("pwd, ls, cd, touch, mkdir, size or rm (command syntax is given in the code)");
		
		ContentFactory factory = new ContentFactory();
		
		Directory rootDirectory = (Directory) factory.getContent("DIRECTORY", "root");
		Directory currentDirectory = rootDirectory;
		directoryList.add(rootDirectory);
		
		while(scanner.hasNext()) {
			
			command = scanner.nextLine().trim();
			if(command.equals("pwd")) {  // ex: pwd
				
				currentDirectory.presentWorkingDirectory(directoryList);
			}
			else if(command.equals("ls")) {
				
				currentDirectory.showListSegments();
			}
			else if(command.contains("cd ")) { // ex: cd directoryName
							
				String[] temp = command.split(" ");
				if(temp.length == COMMAND_WORD_NUMBER) {
					
					String directoryName = temp[1];
					Directory tempDirectory;
					
					if(directoryName.equals("..")) {
						
						directoryList.removeLast();
						currentDirectory = directoryList.getLast();
					}
					else {
						
						tempDirectory = currentDirectory.changeDirectory(directoryName);
						if(tempDirectory.equals(currentDirectory) == false) {
							
							directoryList.add(tempDirectory);
							currentDirectory = tempDirectory;
						}
					}
				}
				else {
					
					System.out.println("Wrong Command Format!");
				}
			}
			else if(command.contains("mkdir ")) {  // ex: mkdir directoryName
				
				String[] temp = command.split(" ");
				if(temp.length == COMMAND_WORD_NUMBER) {
					
					String directoryName = temp[1];		
					Content content = factory.getContent("DIRECTORY", directoryName);
					currentDirectory.addContentIntoList(content);
					rootDirectory.calculateSizes();
				}
				else {
					
					System.out.println("Wrong Command Format!");
				}
			}
			else if(command.contains("touch ")) { // ex: touch fileName
				
				String[] temp = command.split(" ");
				if(temp.length == COMMAND_WORD_NUMBER) {
					
					String fileName = temp[1];
					Content content = factory.getContent("FILE", fileName);
					currentDirectory.addContentIntoList(content);
					rootDirectory.calculateSizes();
				}
				else {
					
					System.out.println("Wrong Command Format!");
				}
			}
			else if(command.contains("size ")) {   // ex: size contentName
				
				String[] temp = command.split(" ");
				if(temp.length == COMMAND_WORD_NUMBER) {
					
					String contentName = temp[1];
					boolean isChildContent = currentDirectory.checkIfChildContent(contentName);
					if(isChildContent) {
						
						int size = currentDirectory.getSizeofContent(contentName);
						System.out.println(size);
					}
					else
						System.out.println(currentDirectory.getName() + " has no content named " + contentName);
				}
				else {
					
					System.out.println("Wrong Command Format!");
				}
			}
			else if(command.contains("rm ")) {  // ex: rm contentName
				
				String[] temp = command.split(" ");
				if(temp.length == COMMAND_WORD_NUMBER) {
					
					String contentName = temp[1];
					boolean isChildContent = currentDirectory.checkIfChildContent(contentName);
					if(isChildContent) {
						
						currentDirectory.delete(contentName);
						rootDirectory.calculateSizes();
					}
					else
						System.out.println(currentDirectory.getName() + " has no content named " + contentName);
				}
				else {
					
					System.out.println("Wrong Command Format!");
				}
			}
			else
				System.out.println("Wrong Command Format!");
		}
	}
}
