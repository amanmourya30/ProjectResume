package package1;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileReaderPractice {

	public static String readFileToString(String filePath) {

		StringBuilder content = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();
	}

	public static void main(String[] args) {

		String filePath = "./Files//EmailBody.txt";
		String fileContent = readFileToString(filePath);
		System.out.println(fileContent);

	}

}
