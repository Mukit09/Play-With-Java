
public class ContentFactory {

	public Content getContent(String contentType, String contentName) {
		
		if(contentType == null)
			return null;
		if(contentType.equalsIgnoreCase("FILE")) {
			int size = (int) (Math.random() * 10);
			return new File(contentName, size);
		}
		else if(contentType.equalsIgnoreCase("DIRECTORY"))
			return new Directory(contentName);
		return null;
	}
}
