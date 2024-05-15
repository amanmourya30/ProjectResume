package package1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmailDataReader {
	
	public ArrayList<String> getEmailAddresses(String excelFilePath) throws IOException{
		
		ArrayList<String> emailList=new ArrayList<String>(); //initialized arraylist to store email addresses
		
		FileInputStream fis=new FileInputStream(excelFilePath);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		XSSFSheet sheet= workbook.getSheetAt(0); //get the first sheet at index 0
	
		Iterator<Row> rows= sheet.iterator();	//iterator to iterate over rows in the sheet
		
		while(rows.hasNext())
		{
			Row row=rows.next(); //get the current row
			Cell emailCell=row.getCell(0); //get the cell in the first column (assuming email addresses are in the first column)
			
			if(emailCell !=null) { //check if the email address is not empty
				String email =emailCell.getStringCellValue().trim();		//get the email address from the cell and trim to remove whitespaces
				if(!email.isEmpty()) //check if the email address is not empty
				{
					emailList.add(email); //Add the email address to the ArrayList
				}
			}
		}
		
		workbook.close();
		fis.close();
		return emailList;	
		
	}
	
	public static void main(String[] args) throws IOException {
		
		String excelFilePath = "C:\\check\\emailSheet.xlsx";
		EmailDataReader reader =new EmailDataReader();
		ArrayList<String>emailAddresses= reader.getEmailAddresses(excelFilePath);
		for (String email: emailAddresses) {
			System.out.println(email);
		}
		
	}

}
