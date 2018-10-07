import java.util.Scanner;

public class LegalWordGeneratorApp {
	
	WordGenerator generator;
	
	public LegalWordGeneratorApp() {
		
		generator = new WordGenerator();
	}
	
	private void inputHandler() {
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String word = scanner.nextLine().toLowerCase();
			int allowedLetterNumbers = scanner.nextInt();

			generator.manipulateStrings(word, allowedLetterNumbers);
		}
	}

	public static void main(String[] args) {
		
		LegalWordGeneratorApp app = new LegalWordGeneratorApp();
		app.inputHandler();
	}
}
