package commonclasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Create_Report_Excel {

	public static String fileName="";
	
	@SuppressWarnings("resource")
	public void Excel_Report_Generation(String reportResultFilePath) throws IOException
	{
		
		HSSFWorkbook workbook=new HSSFWorkbook();
		//String reportpath="C:\\Pramod_IBM5\\Pramod_Barclay\\Report\\Result\\";
		Calendar currentdate=Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("YYYY_MMM_dd_HH_mm_ss");
		String dateNow=formatter.format(currentdate.getTime());
		
	
		fileName=reportResultFilePath+"Result_"+dateNow+".xls";
		System.out.println("fileName in Cretae : "+fileName);
		
		HSSFSheet sheet= workbook.createSheet("Automation_Result");
		HSSFRow headingRow=sheet.createRow(0);
		headingRow.createCell(0).setCellValue("ClassName");
		headingRow.createCell(1).setCellValue("TestCase Id");
		headingRow.createCell(2).setCellValue("TestCase Description");
		headingRow.createCell(3).setCellValue("Result(Pass/Fail");
		
		FileOutputStream fileout=new FileOutputStream(fileName);
		workbook.write(fileout);	
		fileout.close();
		
	}
	
}
