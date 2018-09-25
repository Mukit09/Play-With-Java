
public abstract class Content {

	protected int size;
	protected String name;
	
	public abstract String getName();
	
	public Content(String name) {

		this.name = name;
		this.size = 0;
	}
	
	public Content(String name, int size) {

		this.name = name;
		this.size = size;
	}
}
