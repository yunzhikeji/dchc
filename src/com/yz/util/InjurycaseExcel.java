package com.yz.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yz.model.Injurycase;

public class InjurycaseExcel {

	// 表头-up
	public static final String[] tableHeader = { "序号", "案件类型", "案件编号", "案件名称",
			"录入单位", "录入民警", "录入时间", "办理状态", "是否已串并案", "案发时间", "案发地点", "简要案情",
			"鉴定人", "鉴定人联系电话", "警情编号", "作案目标", "作案对象", "作案方式", "人员特征", "物品特征",
			"完结情况", "综合情况", "领导批示" };
	// 创建工作本
	public static HSSFWorkbook demoWorkBook = new HSSFWorkbook();
	// 创建表-up
	public static HSSFSheet demoSheet = demoWorkBook
			.createSheet("injurycaseeportExcel");
	// 表头的单元格个数目
	public static final short cellNumber = (short) tableHeader.length;
	// 数据库表的列数-up
	public static final int columNumber = 21;

	/** */
	/**
	 * 创建表头
	 * 
	 * @return
	 */
	public static void createTableHeader() {
		HSSFHeader header = demoSheet.getHeader();
		header.setCenter("项目导出数据");
		HSSFRow headerRow = demoSheet.createRow((short) 0);
		for (int i = 0; i < cellNumber; i++) {
			HSSFCell headerCell = headerRow.createCell((short) i);
			headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			headerCell.setCellValue(tableHeader[i]);
		}
	}

	/** */
	/**
	 * 创建行
	 * 
	 * @param cells
	 * @param rowIndex
	 */
	public static void createTableRow(List<String> cells, short rowIndex) {
		// 创建第rowIndex行
		HSSFRow row = demoSheet.createRow((short) rowIndex);
		for (short i = 0; i < cells.size(); i++) {
			// 创建第i个单元格
			HSSFCell cell = row.createCell((short) i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(cells.get(i));
		}
	}

	/** */
	/**
	 * 创建整个Excel表-up
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void createExcelSheeet(List<Injurycase> injurycases) {
		// 生成表头
		createTableHeader();
		try {
			for (int k = 0; k < injurycases.size(); k++) {
				Injurycase injurycase = injurycases.get(k);
				// 用来装行中的每个单元格
				List<String> list = new ArrayList<String>();

				list.add(k + 1 + "");

				if (injurycase.getItype() != null) {
					switch (injurycase.getItype()) {
					case 0:
						list.add("刑事案件");
						break;
					case 1:
						list.add("刑事案件");
						break;
					case 2:
						list.add("重伤案件");
						break;
					case 3:
						list.add("团伙系列案件");
						break;
					case 4:
						list.add("行政案件");
						break;
					default:
						list.add("刑事案件");
						break;
					}
				} else {
					list.add("刑事案件");
				}

				list.add(injurycase.getCaseNumber() == null ? "" : injurycase
						.getCaseNumber());
				list.add(injurycase.getCaseName() == null ? "" : injurycase
						.getCaseName());
				list
						.add(injurycase.getUserRole().getUnit().getName() == null ? ""
								: injurycase.getUserRole().getUnit().getName());
				list.add(injurycase.getUserRole().getRealname() == null ? ""
						: injurycase.getUserRole().getRealname());
				list.add(injurycase.getJoinDate() == null ? "" : injurycase
						.getJoinDate());

				if (injurycase.getHandleState() != null) {
					switch (injurycase.getHandleState()) {
					case 0:
						list.add("未办理");
						break;
					case 1:
						list.add("未办理");
						break;
					case 2:
						list.add("在办理");
						break;
					case 3:
						list.add("已完结");
						break;
					default:
						list.add("未办理");
						break;
					}
				} else {
					list.add("未办理");
				}

				if(injurycase.getIsRelated() != null)
				{
					if (injurycase.getIsRelated() == 1) {
						list.add("已串并");
					} else {
						list.add("未串并");
					}
				}else
				{
					list.add("未串并");
				}
				

				list.add(injurycase.getStartTime() == null ? "" : injurycase
						.getStartTime());
				list.add(injurycase.getCasePlace() == null ? "" : injurycase
						.getCasePlace().toString());
				list.add(injurycase.getBriefCase() == null ? "" : injurycase
						.getBriefCase());
				list.add(injurycase.getAppraiser() == null ? "" : injurycase
						.getAppraiser());
				list.add(injurycase.getTelphone() == null ? "" : injurycase
						.getTelphone());
				list.add(injurycase.getSituationNum() == null ? "" : injurycase
						.getSituationNum());
				list.add(injurycase.getCrimeTarget() == null ? "" : injurycase
						.getCrimeTarget());
				list.add(injurycase.getCrimeObject() == null ? "" : injurycase
						.getCrimeObject());
				list.add(injurycase.getCrimePattern() == null ? "" : injurycase
						.getCrimePattern());
				list.add(injurycase.getPersonFeature() == null ? ""
						: injurycase.getPersonFeature());
				list.add(injurycase.getGoodsDescription() == null ? ""
						: injurycase.getGoodsDescription());

				// '1':'抓获', '2':'死亡', '3':'撤销案件', '4':'释放', '5':'治安拘留',
				// '6':'刑事拘留', '7':'留置盘问', '8':'其他' }"

				if (injurycase.getEndSituation() != null
						&& !injurycase.getEndSituation().replace(" ", "")
								.equals("")) {
					int endType = 0;
					try {
						endType = Integer
								.parseInt(injurycase.getEndSituation());
					} catch (Exception e) {
						// TODO: handle exception
						endType = 8;
					}

					switch (endType) {
					case 1:
						list.add("抓获");
						break;
					case 2:
						list.add("死亡");
						break;
					case 3:
						list.add("撤销案件");
						break;
					case 4:
						list.add("释放");
						break;
					case 5:
						list.add("治安拘留");
						break;
					case 6:
						list.add("刑事拘留");
						break;
					case 7:
						list.add("留置盘问");
						break;
					case 8:
						list.add("其他");
						break;

					default:
						break;
					}

				} else {
					list.add("");
				}

				list.add(injurycase.getComprehensiveJudge() == null ? ""
						: injurycase.getComprehensiveJudge());
				list.add(injurycase.getLeaderInstruction() == null ? ""
						: injurycase.getLeaderInstruction());
				createTableRow(list, (short) (k + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** */
	/**
	 * 导出表格
	 * 
	 * @param sheet
	 * @param os
	 * @throws IOException
	 */
	public void exportExcel(HSSFSheet sheet, OutputStream os)
			throws IOException {
		sheet.setGridsPrinted(true);
		HSSFFooter footer = sheet.getFooter();
		footer.setRight("Page " + HSSFFooter.page() + " of "
				+ HSSFFooter.numPages());
		demoWorkBook.write(os);
	}

	public static boolean exportExcel(String savePath,
			List<Injurycase> injurycases) {

		FileOutputStream fos = null;
		try {
			InjurycaseExcel pd = new InjurycaseExcel();
			pd.createExcelSheeet(injurycases);
			fos = new FileOutputStream(savePath);
			pd.exportExcel(demoSheet, fos);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
