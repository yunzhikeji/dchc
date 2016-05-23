/*package com.yz.util;

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

*//**
 * Excel组件
 * 
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 *//*
public abstract class ExcelHelper {

	*//**
	 * Excel 2003
	 *//*
	private final static String XLS = "xls";
	*//**
	 * Excel 2007
	 *//*
	private final static String XLSX = "xlsx";
	*//**
	 * 分隔符
	 *//*
	private final static String SEPARATOR = "|";

	*//**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 *//*
	public static List<Person> exportListFromExcel(File file, String fileName,int sheetNum)
			throws IOException {
		return exportListFromExcel(new FileInputStream(file), fileName, sheetNum);
	}

	*//**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 *//*
	public static List<Person> exportListFromExcel(InputStream is,
			String extensionName, int sheetNum) throws IOException {

		Workbook workbook = null;
		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return exportListFromExcel(workbook, sheetNum);
	}

	*//**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 *//*
	private static List<Person> exportListFromExcel(Workbook workbook,
			int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

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
				switch (colIx) {
				case 0:
					String colIx0 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx0 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx0 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setNumber(colIx0);
					break;
				case 1:
					String colIx1 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx1 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx1 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setName(colIx1);
					break;
				case 2:
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						double sex = (int)cellValue.getNumberValue();
						if(sex==1d)
						{
							person.setSex(1);
						}else
						{
							person.setSex(0);
						}
						break;
					case Cell.CELL_TYPE_STRING:
						if(cellValue.getStringValue().contains("女"))
						{
							person.setSex(0);
						}else
						{
							person.setSex(1);
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					break;
				case 3:
					String colIx3 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx3 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx3 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setBirthday(colIx3);
					break;
				case 4: 
					String colIx4 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx4 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx4 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setQq(colIx4);
					break;
				case 5:
					String colIx5 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx5 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx5 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setWechat(colIx5);
					break;
				case 6:
					String colIx6 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx6 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx6 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setIdcard(colIx6);
					break;
				case 7:
					String colIx7 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx7 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx7 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setTelphone(colIx7);
					break;
				case 8:
					String colIx8 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx8 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx8 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setRegisterAddress(colIx8);
					break;
				case 9:
					String colIx9 = "";
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						colIx9 = (int)cellValue.getNumberValue()+"";
						break;
					case Cell.CELL_TYPE_STRING:
						colIx9 = cellValue.getStringValue();
						break;
					case Cell.CELL_TYPE_FORMULA:
						System.out.println("FORMULA");
						break;
					case Cell.CELL_TYPE_BLANK:
						System.out.println("BLANK");
						break;
					case Cell.CELL_TYPE_ERROR:
						System.out.println("error");
						break;
					default:
						break;
					}
					person.setRegisterAddressArea(colIx9);
					break;
				default:
					break;
				}
				
			}
			list.add(person);
		}
		return list;
	}
}*/