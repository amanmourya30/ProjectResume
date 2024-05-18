package package1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Draft5_withTextFileReader {
	public static void main(String[] args) throws InterruptedException, IOException {
		// WebDriver set up
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		String userProfile = "C:\\localhost";
		options.addArguments("user-data-dir=" + userProfile);
		options.addArguments("--remote-debugging-port=9111");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://gmail.com");

		// getting email list from Excel file
		Draft5_withTextFileReader reader = new Draft5_withTextFileReader();
		ArrayList<String> emailAddresses = reader.getEmailAddresses("C:\\check\\emailSheet.xlsx");

		// getting email body from text file
		String EmailBody = readFileToString("./Files//EmailBody.txt");

		for (String email : emailAddresses) {

			driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click(); // clicks on compose button
			Thread.sleep(500);
			// interacting with email send email format elements
			driver.findElement(By.cssSelector(".agP.aFw")).click(); // clicks on To field
			driver.findElement(By.cssSelector(".agP.aFw")).sendKeys(email, Keys.ENTER); // enter email
			driver.findElement(By.cssSelector(".aoT")).click(); // clicks on Subject field
			driver.findElement(By.cssSelector(".aoT")).sendKeys("This is a Subject of Email."); // Enter Subject text
			driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).click(); // clicks on Body field
			driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys(EmailBody); // enter email body
			driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click(); // clicks on Attachment icon
			Thread.sleep(2000);
			// Uploading file from local machine by handling windows elements
			ProcessBuilder pb = new ProcessBuilder("./Files//fileupload.exe"); // running Auto it script to handle file
																				// upload
			pb.start();
			waitForElementToAppear(driver, By.xpath("//div[contains(@aria-label,'Attachment')]")); // waits for file to
																									// upload completely
			driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")).click(); // clicks on send button

			Thread.sleep(1000);
			invisibilityOfElementWithText(driver, By.cssSelector("#link_undo"));// waits for Undo message to disappear
			System.out.println(email);

		}
		driver.quit();
	}

	public ArrayList<String> getEmailAddresses(String excelFilePath) throws IOException {
		ArrayList<String> emailList = new ArrayList<String>(); // initialized arraylist to store email addresses
		FileInputStream fis = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0); // get the first sheet at index 0
		Iterator<Row> rows = sheet.iterator(); // iterator to iterate over rows in the sheet
		while (rows.hasNext()) {
			Row row = rows.next(); // get the current row
			Cell emailCell = row.getCell(0); // get the cell in the first column (assuming email addresses are in the
												// first column)
			if (emailCell != null) { // check if the email address is not empty
				String email = emailCell.getStringCellValue().trim(); // get the email address from the cell and trim to
																		// remove whitespaces
				if (!email.isEmpty()) // check if the email address is not empty
				{
					emailList.add(email); // Add the email address to the ArrayList
				}
			}
		}
		workbook.close();
		fis.close();
		return emailList;
	}

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

	public static void waitForElementToAppear(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void invisibilityOfElementWithText(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, "Undo"));
	}

}
