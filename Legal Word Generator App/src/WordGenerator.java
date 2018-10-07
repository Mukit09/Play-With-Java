import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WordGenerator {
	
	private final static String LANGUAGE = "en";
	private final static String APP_ID = "03f2f7e1";
	private final static String APP_KEY = "41ced50cdb212f538dea28b92b6e114c";
	private final static String API_BASED_URL = "https://od-api.oxforddictionaries.com:443/api/v1/entries/";
	
	private static StringBuilder permutedString = new StringBuilder();
	private static int MAX_LETTER_ALLOWED;
	private static int STRING_LENGTH;
	private static int TOTAL_STRING;
	private static int TOTAL_LEGAL_STRING;
	private static String SEARCHED_WORD;
	private static int[] permuteArray;

	private void init(String word, int allowedLetterNumbers) {
		
		SEARCHED_WORD = word;
		STRING_LENGTH = word.length();
		MAX_LETTER_ALLOWED = allowedLetterNumbers;
		permuteArray = new int[STRING_LENGTH];
		TOTAL_STRING = 0;
		TOTAL_LEGAL_STRING = 0;
		
		for(int i=0; i<permuteArray.length; i++) {
			permuteArray[i] = 0;
		}
	}
	
	private boolean isLegalString(String data) {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(data);
			JSONArray array = (JSONArray) jsonObject.get("results");
			for(int i=0; i<array.size(); i++) {
				JSONObject jsonObject1 = (JSONObject) array.get(i);
				JSONArray array1 = (JSONArray) jsonObject1.get("lexicalEntries");
				
				for(int j=0;j<array1.size();j++) {
					JSONObject jsonObject2 = (JSONObject) array1.get(j);
					JSONArray array2 = (JSONArray) jsonObject2.get("entries");
					
					for(int k=0;k<array2.size();k++) {
						JSONObject jsonObject3 = (JSONObject) array2.get(k);
						if(jsonObject3.containsKey("etymologies")) {
							System.out.println(jsonObject3.get("etymologies"));
							return true;
						}
					}
				}
			}
		}  catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void checkString(String wordId) {
		
		String api = API_BASED_URL + LANGUAGE + "/" + wordId;
		
		try {
			URL url = new URL(api);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestProperty("Application", "application/json");
			connection.setRequestProperty("app_id", APP_ID);
			connection.setRequestProperty("app_key", APP_KEY);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			
			boolean isFound =isLegalString(stringBuilder.toString());
			if(isFound) {
				System.out.println(wordId + ": Word Found!");
				TOTAL_LEGAL_STRING++;
			}
			sleepInSeconds(0.5);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			//System.out.println(wordId + " is not a word!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sleepInSeconds(double seconds) {
		
		int miliSeconds = (int) (seconds * 1000.0);
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void wordPermutator(int letterTaken) {
		
		if(letterTaken == MAX_LETTER_ALLOWED) {
			
			TOTAL_STRING++;
			checkString(permutedString.toString());
		}
		
		for(int i=0; i<STRING_LENGTH; i++) {
			
			if(permuteArray[i] == 0) {
				
				permuteArray[i] = 1;
				permutedString.append(SEARCHED_WORD.charAt(i));
				wordPermutator(letterTaken + 1);
				permuteArray[i] = 0;
				permutedString.deleteCharAt(permutedString.length() - 1);
			}
		}
	}

	public void manipulateStrings(String wordId, int allowedLetterNumbers) {
		
		init(wordId, allowedLetterNumbers);
		wordPermutator(0);
		System.out.println("TOTAL_STRING = " + TOTAL_STRING);
		System.out.println("TOTAL_LEGAL_STRING = " + TOTAL_LEGAL_STRING);
	}
}
