
public class File extends Content{

	public File(String name, int size) {
		
		super(name, size);
	}

	@Override
	public String getName() {
		
		return this.name;
	}

}
