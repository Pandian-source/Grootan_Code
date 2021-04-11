package Excelpackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Xls_Reader {

	private FileInputStream fis;
	private FileOutputStream fileOut;
	private Workbook wb;
	private Sheet sh;
	private Cell cell;
	private Row row;
	private String excelFilePath;
	private Map<String, Integer> columns = new HashMap<String, Integer>();
	private String path = null;
   
	public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
		try {
			File f = new File(ExcelPath);

			if (!f.exists()) {
				f.createNewFile();
				System.out.println("File doesn't exist, so created!");
			}

			fis = new FileInputStream(ExcelPath);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(SheetName);
			//sh = wb.getSheetAt(0); //0 - index of 1st sheet
			if (sh == null) {
				sh = wb.createSheet(SheetName);
			}

			this.excelFilePath = ExcelPath;

			//adding all the column header names to the map 'columns'
			sh.getRow(0).forEach(new Consumer<Cell>() {
				public void accept(Cell cell) {
					columns.put(cell.getStringCellValue(), cell.getColumnIndex());
				}
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Xls_Reader(String path) {

		this.path  = path;
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getRowCount(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sh = wb.getSheetAt(index);
			int number = sh.getLastRowNum() + 1;
			return number;
		}

	}
	public String getCellData(String sheetName, int rownum, int colnum) throws Exception{
		try{
			cell = sh.getRow(rownum).getCell(colnum);
			String CellData = null;
			switch (cell.getCellType()){
			case STRING:
				CellData = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					CellData = String.valueOf(cell.getDateCellValue());
				}
				else
				{
					CellData = String.valueOf((long)cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			case BLANK:
				CellData = "";
				break;
			default:
				break;
			}
			return CellData;
		}catch (Exception e){
			return"";
		}
	}

	public String getCellData(String columnName, int rownum) throws Exception {
		return getCellData(columnName, rownum, columns.get(columnName));
	}
	public int getRows(){
		return sh.getPhysicalNumberOfRows();
	}
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = wb.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sh = wb.getSheetAt(index);

			row = sh.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sh.autoSizeColumn(colNum);
			row = sh.getRow(rowNum - 1);
			if (row == null)
				row = sh.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			// cell style
			// CellStyle cs = workbook.createCellStyle();
			// cs.setWrapText(true);
			// cell.setCellStyle(cs);
			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			wb.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			wb.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean removeSheet(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			wb.removeSheetAt(index);
			fileOut = new FileOutputStream(excelFilePath);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean addColumn(String sheetName, String colName) {
		// System.out.println("**************addColumn*********************");

		try {
			fis = new FileInputStream(excelFilePath);
			wb = new XSSFWorkbook(fis);
			int index = wb.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			CellStyle style = wb.createCellStyle();

			sh = wb.getSheetAt(index);

			row = sh.getRow(0);
			if (row == null)
				row = sh.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(excelFilePath);
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if (!isSheetExist(sheetName))
				return false;
			fis = new FileInputStream(excelFilePath);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet(sheetName);
			CellStyle style = wb.createCellStyle();
			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sh.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOut = new FileOutputStream(excelFilePath);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	//find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1) {
			index = wb.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sh = wb.getSheet(sheetName);
		row = sh.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}

	// String sheetName, String testCaseName,String keyword ,String URL,String
	// message
	public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url,
			String message) throws Exception {
		// System.out.println("ADDING addHyperLink******************");

		url = url.replace('\\', '/');
		if (!isSheetExist(sheetName))
			return false;

		sh = wb.getSheet(sheetName);

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
				// System.out.println("**caught "+(i+index));
				setCellData(sheetName, screenShotColName, i + index, message);
				break;
			}
		}

		return true;
	}

	public int getCellRowNum(String sheetName, String colName, String cellValue) throws Exception {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 0, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;

	}

}






