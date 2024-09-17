package rahulshettyacademy.data;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	Object[][] data;
	private FileInputStream fis;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelReader(String fileName, String sheetName) throws Exception {

		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\" + fileName
				+ ".xlsx";
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		
		sheet = workbook.getSheet(sheetName);
		
	}

	public static void main(String[] args) throws Exception {

		ExcelReader reader = new ExcelReader("TestData", "CustomerDat2");

		reader.getAllData();
		//reader.getDataByTestName("TC_02");

	}

	
	public Object[][] getAllData() throws Exception {

		// String fileName ="TestData";
		// String sheetName ="CustomerData";

		XSSFRow headerRow;
		XSSFCell cell;

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		// System.out.println("rowCount="+rowCount);
		// System.out.println("colCount="+colCount);
		
		
		if (rowCount < 2 || colCount < 2)
			throw new Exception("No Data in Current Sheet");

		headerRow = sheet.getRow(0);
		
		data = new Object[rowCount - 1][colCount-1];

		for (int rowIdx = 1; rowIdx < rowCount; rowIdx++) {

			XSSFRow row = sheet.getRow(rowIdx);

			for (int colIdx = 1; colIdx < colCount; colIdx++) {

				cell = row.getCell(colIdx);
				addCelldataToArray(cell, rowIdx - 1, colIdx-1);

			}

		}

		return data;

	}

	public Object[][] getDataByTestName(String TestName) throws Exception {

		// String fileName ="TestData";
		// String sheetName ="CustomerData";

		XSSFRow headerRow;
		XSSFCell cell;

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		// System.out.println("rowCount="+rowCount);
		// System.out.println("colCount="+colCount);

		if (rowCount < 2 || colCount < 2)
			throw new Exception("No Data in Current Sheet");

		headerRow = sheet.getRow(0);

		int testCaseRowNum = getRowNumByTestName(rowCount, TestName);

		if (testCaseRowNum < 1) {

			throw new Exception("Testcase Not Found");
		}
		
		
		data = new Object[1][colCount-1];

		XSSFRow row = sheet.getRow(testCaseRowNum);

		for (int colIdx = 1; colIdx < colCount; colIdx++) {

			cell = row.getCell(colIdx);
			addCelldataToArray(cell, 0, colIdx-1);

		}

		return data;

	}

	private void addCelldataToArray(XSSFCell cell, int rowIdx, int colIdx) {

		// System.out.println("cellType="+cell.getCellType());

		switch (cell.getCellType()) {

		case STRING:
			data[rowIdx][colIdx] = cell.getStringCellValue();
			 System.out.println(cell.getStringCellValue());
			break;
		case NUMERIC:
			data[rowIdx][colIdx] = (int) cell.getNumericCellValue();
			 System.out.println(cell.getNumericCellValue());
			break;
		case BOOLEAN:
			data[rowIdx][colIdx] = cell.getBooleanCellValue();
			 System.out.println(cell.getBooleanCellValue());
			break;
		default:
			 System.out.println("CELL TYPE NOT KNOWN");
			break;

		}

	}

	private int getRowNumByTestName(int rowCount, String TestName) {

		for (int rowIdx = 1; rowIdx < rowCount; rowIdx++) {

			XSSFRow row = sheet.getRow(rowIdx);

			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(TestName)) {
				return rowIdx;
			}

		}

		return -1;
	}

}
