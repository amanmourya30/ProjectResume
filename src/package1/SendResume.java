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

public class SendResume {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Set up WebDriver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		String userProfile = "C:\\localhost"; // Specify user profile for Chrome
		options.addArguments("user-data-dir=" + userProfile);
		options.addArguments("--remote-debugging-port=9111");

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Navigate to Gmail
		driver.get("https://gmail.com");

		// Read email addresses from Excel file
		SendResume reader = new SendResume();
		ArrayList<String> emailAddresses = reader.getEmailAddresses("./Files/emailSheet.xlsx");

		// Read email body from text file
		String emailBody = readFileToString("./Files/EmailBody.txt");

		// Iterate over each email address and send email
		for (String email : emailAddresses) {
			sendEmail(driver, email, emailBody);
		}

		// Quit the driver
		driver.quit();
	}

	public static void sendEmail(WebDriver driver, String email, String emailBody)
			throws InterruptedException, IOException {
		// Click on the Compose button
		driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
		Thread.sleep(500);

		// Enter the recipient email address
		driver.findElement(By.cssSelector(".agP.aFw")).sendKeys(email, Keys.ENTER);

		// Enter the email subject
		driver.findElement(By.cssSelector(".aoT")).sendKeys("This is a Subject of Email.");

		// Enter the email body
		driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys(emailBody);

		// Click on the Attachment icon and handle file upload
		driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click();
		Thread.sleep(2000);
		ProcessBuilder pb = new ProcessBuilder("./Files/fileupload.exe");
		pb.start();

		// Wait for the attachment to upload
		waitForElementToAppear(driver, By.xpath("//div[contains(@aria-label,'Attachment')]"));

		// Send the email
		driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")).click();
		Thread.sleep(1000);

		// Wait for the "Undo" message to disappear
		invisibilityOfElementWithText(driver, By.cssSelector("#link_undo"));

		// Print the sent email address to console
		System.out.println("Email sent to: " + email);
	}

	public ArrayList<String> getEmailAddresses(String excelFilePath) throws IOException {
		ArrayList<String> emailList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(excelFilePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();

			while (rows.hasNext()) {
				Row row = rows.next();
				Cell emailCell = row.getCell(0);
				if (emailCell != null) {
					String email = emailCell.getStringCellValue().trim();
					if (!email.isEmpty()) {
						emailList.add(email);
					}
				}
			}
		}
		return emailList;
	}

	public static String readFileToString(String filePath) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
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
