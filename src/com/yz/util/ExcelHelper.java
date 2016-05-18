package com.yz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yz.model.Person;

/**
 * Excel组件
 * 
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 */
public abstract class ExcelHelper {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";
	/**
	 * 分隔符
	 */
	private final static String SEPARATOR = "|";

	/**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static List<Person> exportListFromExcel(File file, int sheetNum)
			throws IOException {
		return exportListFromExcel(new FileInputStream(file), FilenameUtils
				.getExtension(file.getName()), sheetNum);
	}

	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<Person> exportListFromExcel(InputStream is,
			String extensionName, int sheetNum) throws IOException {

		Workbook workbook = null;
		System.out.println(sheetNum);
		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		System.out.println(sheetNum);
		return exportListFromExcel(workbook, sheetNum);
	}

	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<Person> exportListFromExcel(Workbook workbook,
			int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		System.out.println(sheetNum);
		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();

		List<Person> list = new ArrayList<Person>();
		
		Person person = null;

		int minRowIx = sheet.getFirstRowNum();
		int maxRowIx = sheet.getLastRowNum();

		for (int rowIx = minRowIx; rowIx < maxRowIx; rowIx++) {
			Row row = sheet.getRow(rowIx);
				
			person = new Person();
			short minColIx = row.getFirstCellNum();
			short maxColIx = row.getLastCellNum();
			for (short colIx = minColIx; colIx < maxColIx; colIx++) {
				Cell cell = row.getCell(new Integer(colIx));
				CellValue cellValue = evaluator.evaluate(cell);

				if (cellValue == null) {
					continue;
				}
				
				//人员编号	姓名	性别	出生日期	QQ	微信号	身份证号	手机号码	户籍地址	户籍区域
				// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
				// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					break;
				case Cell.CELL_TYPE_NUMERIC:
					switch (colIx) {
					case 0:
						System.out.println("number0：" + cellValue.getNumberValue());
						break;
					case 1:
						System.out.println("number1：" + cellValue.getNumberValue());
						break;
					default:
						break;
					}
					break;
				case Cell.CELL_TYPE_STRING:
					switch (colIx) {
					case 0:
						System.out.println("String0：" + cellValue.getStringValue());
						person.setNumber(cellValue.getStringValue());
						break;
					case 1:
						System.out.println("String1：" + cellValue.getStringValue());
						person.setName(cellValue.getStringValue());
						break;
					case 2:
						System.out.println("String2：" + cellValue.getStringValue());
						if(cellValue.getStringValue().contains("女"))
						{
							person.setSex(0);
						}
						break;
					case 3:
						System.out.println("String3：" + cellValue.getStringValue());
						person.setBirthday(cellValue.getStringValue());
						break;
					case 4: 
						System.out.println("String4：" + cellValue.getStringValue());
						person.setQq(cellValue.getStringValue());
						break;
					case 5:
						System.out.println("String5：" + cellValue.getStringValue());
						person.setWechat(cellValue.getStringValue());
						break;
					case 6:
						System.out.println("String6：" + cellValue.getStringValue());
						person.setIdcard(cellValue.getStringValue());
						break;
					case 7:
						System.out.println("String7：" + cellValue.getStringValue());
						person.setTelphone(cellValue.getStringValue());
						break;
					case 8:
						System.out.println("String8：" + cellValue.getStringValue());
						person.setRegisterAddress(cellValue.getStringValue());
						break;
					case 9:
						System.out.println("String9：" + cellValue.getStringValue());
						person.setRegisterAddressArea(cellValue.getStringValue());
						break;
					default:
						break;
					}
					break;
				case Cell.CELL_TYPE_FORMULA:
					System.out.println("FORMULA"+cellValue.getStringValue());
					break;
				case Cell.CELL_TYPE_BLANK:
					System.out.println("BLANK"+cellValue.getStringValue());
					break;
				case Cell.CELL_TYPE_ERROR:
					System.out.println("error"+cellValue.getStringValue());
					break;
				default:
					break;
				}
			}
			list.add(person);
		}
		return list;
	}
}