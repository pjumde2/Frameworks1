package Driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import commonclasses.PropertyRead;


public class ReadExcel extends LaunchApplication{


	Map<Integer, HashMap<String, String>> mapHm = new HashMap<Integer, HashMap<String, String>>();

	@SuppressWarnings("resource")
	public static void Excel_Report_Generation(String a, String b) throws FileNotFoundException, IOException
	{
		System.out.println("In Result report Generation");		
		//System.out.println("fileName :"+Create_Report_Excel.fileName);
		//	File fp = new File (Create_Report_Excel.fileName);
		//	System.out.println("file object: "+fp.getPath());

		//File fp = new File(createExl.fileName);
		//	File fp=new File(Create_Report_Excel.fileName);
		File fp = new File(commonclasses.Create_Report_Excel.fileName);
		File fp1 = new File(commonclasses.Create_Report_Excel.fileName);
		if (!fp1.exists()) {
			fp1.createNewFile();
		}
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fp));
		//        HSSFSheet sheet = workbook.getSheetAt(0);
		System.out.println("File object created");
		//FileInputStream inputStream= new FileInputStream(fp);
		System.out.println("ZXC");

		//HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);

		//Get the current count of rows in excel file

		int rowCount = sheet.getPhysicalNumberOfRows();


		System.out.println("ASD");

		System.out.println("number of rows : "+rowCount);
		//System.out.println("header: "+sheet.getHeader().toString());

		if(sheet!=null)
		{
			System.out.println("Writing result in sheet");
			for(int i=0;i<rowCount+1;i++)
			{
				HSSFRow row= sheet.getRow(i);

				if(row==null)
				{
					System.out.println("writing incolumn name");
					row=sheet.createRow(i);
					row.createCell(0).setCellValue(a);
					System.out.println("writing column name");
					try{

						String status[]=b.split(",");
						row.createCell(1).setCellValue(status[1]);
						row.createCell(2).setCellValue(status[2]);
						row.createCell(3).setCellValue(status[0]);


					}catch(Exception e)
					{
						System.out.println(e.getMessage());
					}


				}

			}
		}
		FileOutputStream fileout=new FileOutputStream(fp1);
		workbook.write(fileout);
		//inputStream.close();

		fileout.close();
	}

	@SuppressWarnings("unused")
	public void Readxls( String Sheetname, String Filepath) throws IOException
	{
		HashMap<String,String> hm ;
		FileInputStream File= new FileInputStream(Filepath);
		HSSFWorkbook wb=new HSSFWorkbook(File);

		HSSFSheet sheet=wb.getSheet(Sheetname);
		int rows=sheet.getPhysicalNumberOfRows();
		System.out.println("Rows are: "+rows);
		HSSFRow row=sheet.getRow(1);
		int columns=row.getPhysicalNumberOfCells();
		System.out.println("No. of Columns are: "+columns);

		String Methodnname="";
		String Methodname="";
		String classname="";
		if(Sheetname.equalsIgnoreCase("TestSuite"))
		{

			for(int k=0;k<rows;k++)
			{
				for(int m=0;m<columns;m++)
				{
					row=sheet.getRow(k);
					HSSFCell cell=row.getCell(m);
					String value3=cell.toString();
					System.out.println("Cell Value is: "+value3);
					if(value3.equalsIgnoreCase("Yes"))
					{
						HSSFCell Methodrname=row.getCell(--m);
						Methodnname=Methodrname.toString();
						System.out.println("Methodname is :"+Methodnname);
						//break;
						// New code
						
//						System.out.println("MethodName is: "+Methodnname);
						HSSFSheet TD_Worksheet = wb.getSheet(Methodnname);
						//System.out.println("Sheetname is: "+TD_Worksheet);
//						int TD_Rows=TD_Worksheet.getPhysicalNumberOfRows();
						int TD_Rows=TD_Worksheet.getPhysicalNumberOfRows();
						System.out.println("TD Rows are: "+TD_Rows);
						HSSFRow TD_Row=TD_Worksheet.getRow(0);
						HSSFHeader roHeader=TD_Worksheet.getHeader();
						//HSSFRow row=sheet.getRow(1);
						int TD_Column = TD_Row.getPhysicalNumberOfCells();
						System.out.println("TD Columns are: "+TD_Column);
						//	roHeader.
						for(int p=1;p<TD_Rows;p++)
						{
							
							HSSFRow Row1=TD_Worksheet.getRow(p);
							if(Row1.getCell(0) == null || String.valueOf(Row1.getCell(0)) =="") { 
								break;
							}
							String value2=Row1.getCell(1).toString();
							//String value2=value1.toString();
							classname= Row1.getCell(2).toString();
							//	classname=classname1.toString();
							Methodname=Row1.getCell(0).toString();
							//	Methodname=Methodname1.toString();
							System.out.println("Class name is: "+classname);
							System.out.println("Method Name is: "+Methodname);
							if(value2.equalsIgnoreCase("Yes"))
							{
								hm= new HashMap<String, String>();
								for(int q=0;q<TD_Column;q++)
								{
									String TD_Key="";
									String TD_KeyValue="";
									TD_Key = TD_Row.getCell(q).getStringCellValue();
									TD_KeyValue = Row1.getCell(q).getStringCellValue();
									System.out.println(TD_Key+" "+TD_KeyValue);
									hm.put(TD_Key, TD_KeyValue);
								}
								//break;
								mapHm.put(p,hm);
								System.out.println("Put value in HashMap: "+mapHm);
							}
							
						}
						try {
							PropertyRead.readProperty();
							publishInExcel(classname, Methodname,mapHm,PropertyRead.Browsername,PropertyRead.Browserexepath);
							mapHm = new HashMap<Integer, HashMap<String, String>>();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;	
					}
					
				}
				
			}
			/*System.out.println("MethodName is: "+Methodnname);
			HSSFSheet TD_Worksheet = wb.getSheet(Methodnname);
			//System.out.println("Sheetname is: "+TD_Worksheet);
//			int TD_Rows=TD_Worksheet.getPhysicalNumberOfRows();
			int TD_Rows=TD_Worksheet.getPhysicalNumberOfRows();
			System.out.println("TD Rows are: "+TD_Rows);
			HSSFRow TD_Row=TD_Worksheet.getRow(0);
			HSSFHeader roHeader=TD_Worksheet.getHeader();
			//HSSFRow row=sheet.getRow(1);
			int TD_Column = TD_Row.getPhysicalNumberOfCells();
			System.out.println("TD Columns are: "+TD_Column);
			//	roHeader.
			for(int p=1;p<TD_Rows;p++)
			{
				
				HSSFRow Row1=TD_Worksheet.getRow(p);
				if(Row1.getCell(0) == null || String.valueOf(Row1.getCell(0)) =="") { 
					break;
				}
				String value2=Row1.getCell(1).toString();
				//String value2=value1.toString();
				classname= Row1.getCell(2).toString();
				//	classname=classname1.toString();
				Methodname=Row1.getCell(0).toString();
				//	Methodname=Methodname1.toString();
				System.out.println("Class name is: "+classname);
				System.out.println("Method Name is: "+Methodname);
				if(value2.equalsIgnoreCase("Yes"))
				{
					hm= new HashMap<String, String>();
					for(int q=0;q<TD_Column;q++)
					{
						String TD_Key="";
						String TD_KeyValue="";
						TD_Key = TD_Row.getCell(q).getStringCellValue();
						TD_KeyValue = Row1.getCell(q).getStringCellValue();
						System.out.println(TD_Key+" "+TD_KeyValue);
						hm.put(TD_Key, TD_KeyValue);
					}
					//break;
					mapHm.put(p,hm);
					System.out.println("Put value in HashMap: "+mapHm);
				}
				
			}
			try {
				PropertyRead.readProperty();
				publishInExcel(classname, Methodname,mapHm,PropertyRead.Browsername);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		wb.close();


	}

	private void publishInExcel(String classname, String methodname,
			Map<Integer, HashMap<String, String>> mapHm2,String browsername,String browserexepath) throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		LaunchApplication LA=new LaunchApplication();
		//		HashMap<String,String> hm= new HashMap<String, String>();



		for (Entry<Integer, HashMap<String, String>> hashMap : mapHm2.entrySet())			
		{
			LA.initialise(browsername,browserexepath);
			LA.callclass(classname, methodname, hashMap.getValue());
			driver.close();
		}


		/*
		mapHm2.entrySet()
		mapHm2.get(key).;*/


	}

}
