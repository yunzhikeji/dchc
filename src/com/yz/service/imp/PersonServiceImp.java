package com.yz.service.imp;

import com.yz.dao.PersonDao;
import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.GenerateSqlFromExcel;
import com.yz.util.IdsOperator;
import com.yz.util.InfoType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("personService")
public class PersonServiceImp extends RoleServiceImp implements PersonService {

	@Resource
	private PersonDao personDao;
	@Resource
	private UnitService unitService;
	@Resource
	private GamblingCriminalManService gamblingCriminalManService;
	@Resource
	private GuiltSafeguardManService guiltSafeguardManService;
	@Resource
	private DisappearManService disappearManService;
	@Resource
	private AnalyzeManService analyzeManService;
	@Resource
	private ContrastManService contrastManService;


	public void add(Person person) throws Exception {

		changeUnitByUserRoleAndIdsOperator(person.getUserRole(), new IdsOperator(person.getId() + "", 1));
		personDao.save(person);
	}


	public void delete(Person person) {

		changeUnitByUserRoleAndIdsOperator(person.getUserRole(), new IdsOperator(person.getId() + "", -1));
		personDao.delete(person);
	}


	public void deleteById(int id) {
		personDao.deleteById(id);
	}


	public void update(Person person) {
		personDao.update(person);
	}


	public List<Person> getPersons() {
		return personDao.getPersons();
	}


	public Person loadById(int id) {
		return personDao.loadById(id);
	}


	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}


	public int getTotalCount(int con, String convalue, UserRole userRole,
							 int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Person mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}
		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return personDao.getUniqueResult(queryString, p);
	}

	public Person getPersonByPersonname(String personname) {
		String queryString = "from Person mo where mo.name=:personname";
		String[] paramNames = new String[]{"personname"};
		Object[] values = new Object[]{personname};
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Person getPersonByIdcard(String idcard) {
		String queryString = "from Person mo where mo.idcard=:idcard";
		String[] paramNames = new String[]{"idcard"};
		Object[] values = new Object[]{idcard};
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}


	public List<Person> queryList(int con, String convalue, UserRole userRole,
								  int page, int size, int type, int queryState, String starttime,
								  String endtime) {
		String queryString = "from Person mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return personDao.pageList(queryString, p, page, size);
	}


	public Person getPersonById(Integer personid) {
		return personDao.getPersonById(personid);
	}


	public List<Person> getPersonsByTypeAndHandleState(int con,
													   String convalue, String starttime, String endtime, int type,
													   int handleState, UserRole userRole) {
		String queryString = "from Person mo where mo.type=" + type
				+ " and mo.handleState=" + handleState;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}


	public int savereturn(Person person) {
		return personDao.savereturn(person);
	}

	public void savePersonWithExcel(File file, UserRole userRole, int type) {
		try {
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = null;

			String pids = "";
			if (type == 11) {
				arrayList = generate.generateDisappearManSql(file);
				for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
					String[] data = arrayList.get(i);
					Person person = new Person();
					DisappearMan disappearMan = new DisappearMan();
					person.setUserRole(userRole);

					/*
					 * "人员编号", "姓名", "外文姓名", "别名", "民族", "性别", "出生日期", "身份证号",
					 * "户籍地详址", "其他证件名称", "其他证件号码", "单位联系人姓名", "单位联系人号码",
					 * "报案联系人姓名", "报案联系人号码", "现住地址", "失踪地址", "失踪日期", "发现失踪日期",
					 * "失踪经过原因", "身高", "体型", "脸型", "足长", "血型", "口音", "特殊特征",
					 * "体表特征", "特殊特征描述", "衣着情况", "亲属血样信息", "人员备注信息", "携带物品",
					 * "携带工具", "撤销单位", "承办人", "撤销日期", "撤销原因", "综合情况",
					 * "领导批示","办理状态"
					 */
					person.setNumber(data[0].toString());
					person.setName(data[1].toString());
					disappearMan.setForeignName(data[2].toString());
					disappearMan.setNickname(data[3].toString());

					person.setNation(data[4].toString());
					if (data[5].toString().equals("男")) {
						person.setSex(1);
					} else if (data[5].toString().equals("女")) {
						person.setSex(2);
					}
					person.setBirthday(data[6].toString());
					person.setIdcard(data[7].toString());
					person.setRegisterAddress(data[8].toString());
					disappearMan.setOtherIdname(data[9].toString());
					disappearMan.setOtherIdnumber(data[10].toString());
					disappearMan.setUnitContactName(data[11].toString());
					disappearMan.setUnitContactTelphone(data[12].toString());
					disappearMan.setReportContactName(data[13].toString());
					disappearMan.setReportContactTelphone(data[14].toString());
					disappearMan.setCurrentAddress(data[15].toString());
					disappearMan.setMissingAddress(data[16].toString());
					disappearMan.setMissingStartTime(data[17].toString());
					disappearMan.setFoundMissingTime(data[18].toString());
					disappearMan.setMissingCause(data[19].toString());
					disappearMan.setHeight(data[20].toString());
					disappearMan.setShape(data[21].toString());
					disappearMan.setFeature(data[22].toString());
					disappearMan.setFootLength(data[23].toString());
					disappearMan.setBloodType(data[24].toString());
					disappearMan.setAccent(data[25].toString());
					disappearMan.setSpecificFeature(data[26].toString());
					disappearMan.setBodyFeature(data[27].toString());
					disappearMan.setSpecificFeatureCon(data[28].toString());
					disappearMan.setDressSituation(data[29].toString());
					disappearMan.setRelativeBlood(data[30].toString());
					person.setRemark(data[31].toString());
					person.setCarrier(data[32].toString());
					person.setCarryTool(data[33].toString());
					disappearMan.setRevocateUnit(data[34].toString());
					disappearMan.setRevocateName(data[35].toString());
					disappearMan.setRevocateTime(data[36].toString());
					disappearMan.setRevocateReason(data[37].toString());
					person.setComprehensiveJudge(data[38].toString());
					person.setLeaderInstruction(data[39].toString());
					person.setType(type);

					String handleStateString = data[40].toString();
					if (handleStateString.contains("未")) {
						person.setHandleState(1);
					} else if (handleStateString.contains("在")) {
						person.setHandleState(2);
					} else if (handleStateString.contains("已")) {
						person.setHandleState(3);
					} else {
						person.setHandleState(1);
					}
					if (data[41] != null && data[41].toString() != null
							&& data[41].toString().trim() != "") {
						String endSituation = data[41].toString();

						if (endSituation.contains("抓获")) {
							person.setEndSituation(1 + "");
						} else if (endSituation.contains("死亡")) {
							person.setEndSituation(2 + "");
						} else if (endSituation.contains("撤销案件")) {
							person.setEndSituation(3 + "");
						} else if (endSituation.contains("释放")) {
							person.setEndSituation(4 + "");
						} else if (endSituation.contains("治安拘留")) {
							person.setEndSituation(5 + "");
						} else if (endSituation.contains("刑事拘留")) {
							person.setEndSituation(6 + "");
						} else if (endSituation.contains("留置盘问")) {
							person.setEndSituation(7 + "");
						} else if (endSituation.contains("其他")) {
							person.setEndSituation(8 + "");
						}
					} else {
						person.setEndSituation("");
					}

					disappearManService.add(disappearMan);
					person.setDisappearMan(disappearMan);
					int pid = personDao.savereturn(person);

					pids = pids + pid + ",";

				}
			} else {
				arrayList = generate.generateStationBugSql(file);
				for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
					String[] data = arrayList.get(i);
					Person person = new Person();

					person.setUserRole(userRole);

					// "人员编号","姓名","性别","出生日期","QQ","微信号","身份证号","手机号码","户籍地详址","户籍地区划","DNA编号","其他身份信息","指纹编号","足迹编号","现住地区划","现住地详址","虚拟身份","银行卡信息","绰号","车牌号","发动机号","车架号","手机串号","人员备注信息","携带物品","携带工具","人员分类","办理状态","民族"
					person.setNumber(data[0].toString());
					person.setName(data[1].toString());
					if (data[2].toString().equals("男")) {
						person.setSex(1);
					} else if (data[2].toString().equals("女")) {
						person.setSex(2);
					}

					person.setBirthday(data[3].toString());
					person.setQq(data[4].toString());
					person.setWechat(data[5].toString());
					person.setIdcard(data[6].toString());
					person.setTelphone(data[7].toString());
					person.setRegisterAddress(data[8].toString());
					person.setRegisterAddressArea(data[9].toString());
					if (data[26].toString().contains("赌博")) {
						person.setType(1);
					} else if (data[26].toString().contains("涉恶")) {
						person.setType(2);
					} else if (data[26].toString().contains("涉黄")) {
						person.setType(3);
					} else if (data[26].toString().contains("食药环")) {
						person.setType(4);
					} else if (data[26].toString().contains("涉毒")) {
						person.setType(5);
					} else if (data[26].toString().contains("留置盘问")) {
						person.setType(6);
					} else if (data[26].toString().contains("侵财人员")) {
						person.setType(7);
					} else if (data[26].toString().contains("刑事传唤")) {
						person.setType(8);
					} else if (data[26].toString().contains("负案在逃")) {
						person.setType(9);
					} else if (data[26].toString().contains("维稳人员")) {
						person.setType(10);
					} else if (data[26].toString().contains("失踪")) {
						person.setType(11);
					} else if (data[26].toString().contains("侵财分析")) {
						person.setType(12);
					} else if (data[26].toString().contains("技术比中")) {
						person.setType(13);
					} else if (data[26].toString().contains("前科")) {
						person.setType(14);
					} else if (data[26].toString().contains("其他")) {
						person.setType(15);
					}

					switch (person.getType()) {
						case 0:
						case 1:
							// pageName = "赌博人员";
						case 2:
							// pageName = "涉恶人员";
						case 3:
							// pageName = "涉黄人员";
						case 4:
							// pageName = "食药环人员";
						case 5:
							// pageName = "涉毒人员";
						case 6:
							// pageName = "留置盘问";
						case 7:
							// pageName = "侵财人员";
						case 8:
							// pageName = "刑事传唤";
						case 14:
							// pageName = "前科人员";
						case 15:
							// pageName = "其他人员";
							GamblingCriminalMan gamblingCriminalMan = new GamblingCriminalMan();

							gamblingCriminalMan.setDnanumber(data[10].toString());
							gamblingCriminalMan.setOtherId(data[11].toString());
							gamblingCriminalMan.setFingerPrintNumber(data[12]
									.toString());
							gamblingCriminalMan.setFootPrintNumber(data[13]
									.toString());
							gamblingCriminalMan.setCurrentAddressArea(data[14]
									.toString());
							gamblingCriminalMan.setCurrentAddress(data[15]
									.toString());
							gamblingCriminalMan.setVirtualId(data[16].toString());
							gamblingCriminalMan.setBankCard(data[17].toString());
							gamblingCriminalMan.setNickname(data[18].toString());
							gamblingCriminalMan.setCarLicenseNumber(data[19]
									.toString());
							gamblingCriminalMan
									.setEngineNumber(data[20].toString());
							gamblingCriminalMan.setCarFrameNumber(data[21]
									.toString());
							gamblingCriminalMan.setImei(data[22].toString());

							gamblingCriminalManService.add(gamblingCriminalMan);

							person.setGamblingCriminalMan(gamblingCriminalMan);
							break;
						case 9:
							// pageName = "负罪在逃";
						case 10:
							// pageName = "维稳人员";
							GuiltSafeguardMan guiltSafeguardMan = new GuiltSafeguardMan();
							guiltSafeguardManService.add(guiltSafeguardMan);
							person.setGuiltSafeguardMan(guiltSafeguardMan);
							break;
						case 11:
							// pageName = "失踪人员分析";
							DisappearMan disappearman = new DisappearMan();

							disappearManService.add(disappearman);
							person.setDisappearMan(disappearman);
							break;
						case 12:
							// pageName = "侵财人员分析";
							AnalyzeMan analyzeMan = new AnalyzeMan();
							analyzeManService.add(analyzeMan);
							person.setAnalyzeMan(analyzeMan);
							break;
						case 13:
							// pageName = "技术比中人员";
							ContrastMan contrastMan = new ContrastMan();
							contrastManService.add(contrastMan);
							person.setContrastMan(contrastMan);
							break;
						default:
							break;
					}

					person.setRemark(data[23].toString());
					person.setCarrier(data[24].toString());
					person.setCarryTool(data[25].toString());

					/*
					 *
					 *
					 *
					 * private GamblingCriminalMan gamblingCriminalMan;//
					 * 1:赌博人员，2:涉恶人员，3:涉黄人员，4:食药环人员，5:涉毒人员，6:留置盘问，7:侵财人员，8:刑事传唤
					 * private GuiltSafeguardMan guiltSafeguardMan;//
					 * 9:负案在逃人员,10:维稳人员 private DisappearMan disappearman;//
					 * 11:失踪人员 private AnalyzeMan analyzeMan;// 12:侵财分析人员
					 * private ContrastMan contrastMan;// 13:技术比中人员
					 */

					String handleStateString = data[27].toString();
					if (handleStateString.contains("未")) {
						person.setHandleState(1);
					} else if (handleStateString.contains("在")) {
						person.setHandleState(2);
					} else if (handleStateString.contains("已")) {
						person.setHandleState(3);
					} else {
						person.setHandleState(1);
					}
					person.setNation(data[28].toString());
					int pid = personDao.savereturn(person);

					pids = pids + pid + ",";

				}
			}

			// 需要每次都访问新的组织
			Unit unit = unitService.getUnitByName(userRole.getUnit().getName());

			unitService.updateUnitByUserRoleAndInfoType(unit, pids,
					InfoType.PERSON, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 获取excel的标题数据集
	public ArrayList getExcelFieldNameList(int type) {

		String[] titles = {"人员编号", "姓名", "性别", "出生日期", "QQ", "微信号", "身份证号",
				"手机号码", "户籍地详址", "户籍地区划", "DNA编号", "其他身份信息", "指纹编号", "足迹编号",
				"现住地区划", "现住地详址", "虚拟身份", "银行卡信息", "绰号", "车牌号", "发动机号", "车架号",
				"手机串号", "人员备注信息", "携带物品", "携带工具", "人员分类", "办理状态", "民族"};
		// 失踪人员表头
		String[] disappearManTitles = {"人员编号", "姓名", "外文姓名", "别名", "民族", "性别",
				"出生日期", "身份证号", "户籍地详址", "其他证件名称", "其他证件号码", "单位联系人姓名",
				"单位联系人号码", "报案联系人姓名", "报案联系人号码", "现住地址", "失踪地址", "失踪日期",
				"发现失踪日期", "失踪经过原因", "身高", "体型", "脸型", "足长", "血型", "口音", "特殊特征",
				"体表特征", "特殊特征描述", "衣着情况", "亲属血样信息", "人员备注信息", "携带物品", "携带工具",
				"撤销单位", "承办人", "撤销日期", "撤销原因", "综合情况", "领导批示", "办理状态", "完结情况"};
		ArrayList fieldName = new ArrayList();

		if (type != 11) {
			for (int i = 0; i < titles.length; i++) {
				String title = titles[i];
				fieldName.add(title);
			}
		} else if (type == 11) {
			for (int j = 0; j < disappearManTitles.length; j++) {
				String disappearManTitle = disappearManTitles[j];
				fieldName.add(disappearManTitle); // 构造失踪人员导出的表头信息
			}
		}

		return fieldName;
	}

	public ArrayList getExcelFieldDataList(int con, String convalue,
										   UserRole userRole, int type, int queryState, String starttime,
										   String endtime) {

		String queryString = "from Person mo where 1=1 ";

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like '%" + convalue + "%' ";
			}
			if (con == 2) {
				queryString += "and mo.number like'%" + convalue + "%' ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like '%" + convalue + "%' ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like '%" + convalue
						+ "%' ";
			}
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		List<Person> personList = personDao.queryList(queryString);

		String typevo = "";
		if (type == 1) {
			typevo = "赌博人员";
		}
		if (type == 2) {
			typevo = "涉恶人员";
		}
		if (type == 3) {
			typevo = "涉黄人员";
		}
		if (type == 4) {
			typevo = "食药环人员";
		}
		if (type == 5) {
			typevo = "涉毒人员";
		}
		if (type == 6) {
			typevo = "留置盘问人员";
		}
		if (type == 9) {
			typevo = "负案在逃人员";
		}
		if (type == 7) {
			typevo = "侵财人员";
		}
		if (type == 8) {
			typevo = "刑事传唤人员";
		}
		if (type == 11) {
			typevo = "失踪人员";
		}
		if (type == 12) {
			typevo = "侵财人员分析";
		}
		if (type == 13) {
			typevo = "技术比中人员";
		}
		if (type == 14) {
			typevo = "前科人员";
		}
		if (type == 15) {
			typevo = "其他人员";
		}
		// 构造报表和导出数据
		ArrayList fieldData = new ArrayList();

		for (int i = 0; personList != null && i < personList.size(); i++) {
			ArrayList dataList = new ArrayList();
			Person person = personList.get(i);
			if (type == 11) {
				/*
				 * "人员编号", "姓名", "外文姓名", "别名", "民族", "性别", "出生日期", "身份证号",
				 * "户籍地详址", "其他证件名称", "其他证件号码", "单位联系人姓名", "单位联系人号码", "报案联系人姓名",
				 * "报案联系人号码", "现住地址", "失踪地址", "失踪日期", "发现失踪日期", "失踪经过原因", "身高",
				 * "体型", "脸型", "足长", "血型", "口音", "特殊特征", "体表特征", "特殊特征描述",
				 * "衣着情况", "亲属血样信息", "人员备注信息", "携带物品", "携带工具", "撤销单位", "承办人",
				 * "撤销日期", "撤销原因", "综合情况", "领导批示","办理状态","完结情况"
				 */
				dataList.add(person.getNumber());
				dataList.add(person.getName());

				if (person.getDisappearMan() != null) {
					dataList.add(person.getDisappearMan().getForeignName());
					dataList.add(person.getDisappearMan().getNickname());
				} else {
					dataList.add("");
					dataList.add("");
				}

				dataList.add(person.getNation());
				if (person.getSex() != null) {
					if (person.getSex() == 1) {
						dataList.add("男");
					} else if (person.getSex() == 2) {
						dataList.add("女");
					} else {
						dataList.add("");
					}
				} else {
					dataList.add("");
				}
				dataList.add(person.getBirthday());
				dataList.add(person.getIdcard());
				dataList.add(person.getRegisterAddress());
				if (person.getDisappearMan() != null) {
					dataList.add(person.getDisappearMan().getOtherIdname());
					dataList.add(person.getDisappearMan().getOtherIdnumber());
					dataList.add(person.getDisappearMan().getUnitContactName());
					dataList.add(person.getDisappearMan()
							.getUnitContactTelphone());
					dataList.add(person.getDisappearMan()
							.getReportContactName());
					dataList.add(person.getDisappearMan()
							.getReportContactTelphone());
					dataList.add(person.getDisappearMan().getCurrentAddress());
					dataList.add(person.getDisappearMan().getMissingAddress());
					dataList
							.add(person.getDisappearMan().getMissingStartTime());
					dataList
							.add(person.getDisappearMan().getFoundMissingTime());
					dataList.add(person.getDisappearMan().getMissingCause());
					dataList.add(person.getDisappearMan().getHeight());
					dataList.add(person.getDisappearMan().getShape());
					dataList.add(person.getDisappearMan().getFeature());
					dataList.add(person.getDisappearMan().getFootLength());
					dataList.add(person.getDisappearMan().getBloodType());
					dataList.add(person.getDisappearMan().getAccent());
					dataList.add(person.getDisappearMan().getSpecificFeature());
					dataList.add(person.getDisappearMan().getBodyFeature());
					dataList.add(person.getDisappearMan()
							.getSpecificFeatureCon());
					dataList.add(person.getDisappearMan().getDressSituation());
					dataList.add(person.getDisappearMan().getRelativeBlood());
				} else {
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
				}

				dataList.add(person.getRemark());
				dataList.add(person.getCarrier());
				dataList.add(person.getCarryTool());
				if (person.getDisappearMan() != null) {
					dataList.add(person.getDisappearMan().getRevocateUnit());
					dataList.add(person.getDisappearMan().getRevocateName());
					dataList.add(person.getDisappearMan().getRevocateTime());
					dataList.add(person.getDisappearMan().getRevocateReason());
				} else {
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
				}

				dataList.add(person.getComprehensiveJudge());
				dataList.add(person.getLeaderInstruction());
				if (person.getHandleState() != null) {
					if (person.getHandleState() == 1) {
						dataList.add("未办理");
					} else if (person.getHandleState() == 2) {
						dataList.add("在办理");
					} else if (person.getHandleState() == 3) {
						dataList.add("已完结");
					} else {
						dataList.add("未办理");
					}
				} else {
					dataList.add("未办理");
				}

				if (person.getEndSituation() != null
						&& !person.getEndSituation().replace(" ", "")
						.equals("")) {
					int endType = Integer.parseInt(person.getEndSituation());
					switch (endType) {
						case 1:
							dataList.add("抓获");
							break;
						case 2:
							dataList.add("死亡");
							break;
						case 3:
							dataList.add("撤销案件");
							break;
						case 4:
							dataList.add("释放");
							break;
						case 5:
							dataList.add("治安拘留");
							break;
						case 6:
							dataList.add("刑事拘留");
							break;
						case 7:
							dataList.add("留置盘问");
							break;
						case 8:
							dataList.add("其他");
							break;

						default:
							dataList.add("");
							break;
					}

				} else {
					dataList.add("");
				}

			} else {

				// 默认其他type情况
				dataList.add(person.getNumber());
				dataList.add(person.getName());
				if (person.getSex() != null) {
					if (person.getSex() == 1) {
						dataList.add("男");
					} else if (person.getSex() == 2) {
						dataList.add("女");
					} else {
						dataList.add("");
					}
				} else {
					dataList.add("");
				}

				dataList.add(person.getBirthday());
				dataList.add(person.getQq());
				dataList.add(person.getWechat());
				dataList.add(person.getIdcard());
				dataList.add(person.getTelphone());
				dataList.add(person.getRegisterAddress());
				dataList.add(person.getRegisterAddressArea());
				if (person.getGamblingCriminalMan() != null) {
					dataList
							.add(person.getGamblingCriminalMan().getDnanumber());
					dataList.add(person.getGamblingCriminalMan().getOtherId());
					dataList.add(person.getGamblingCriminalMan()
							.getFingerPrintNumber());
					dataList.add(person.getGamblingCriminalMan()
							.getFootPrintNumber());
					dataList.add(person.getGamblingCriminalMan()
							.getCurrentAddressArea());
					dataList.add(person.getGamblingCriminalMan()
							.getCurrentAddress());
					dataList
							.add(person.getGamblingCriminalMan().getVirtualId());
					dataList.add(person.getGamblingCriminalMan().getBankCard());
					dataList.add(person.getGamblingCriminalMan().getNickname());
					dataList.add(person.getGamblingCriminalMan()
							.getCarLicenseNumber());
					dataList.add(person.getGamblingCriminalMan()
							.getEngineNumber());
					dataList.add(person.getGamblingCriminalMan()
							.getCarFrameNumber());
					dataList.add(person.getGamblingCriminalMan().getImei());
				}
				if (person.getGamblingCriminalMan() == null) {
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
					dataList.add("");
				}
				dataList.add(person.getRemark());
				dataList.add(person.getCarrier());
				dataList.add(person.getCarryTool());
				dataList.add(typevo);
				if (person.getHandleState() != null) {
					if (person.getHandleState() == 1) {
						dataList.add("未办理");
					} else if (person.getHandleState() == 2) {
						dataList.add("在办理");
					} else if (person.getHandleState() == 3) {
						dataList.add("已完结");
					} else {
						dataList.add("未办理");
					}
				} else {
					dataList.add("未办理");
				}

				dataList.add(person.getNation());
			}

			// "人员编号","姓名","性别","出生日期","QQ","微信号","身份证号","手机号码","户籍地详址","户籍地区划","DNA编号","其他身份信息","指纹编号","足迹编号","现住地区划","现住地详址","虚拟身份","银行卡信息","绰号","车牌号","发动机号","车架号","手机串号","人员备注信息","携带物品","携带工具","人员分类"
			fieldData.add(dataList);
		}

		return fieldData;
	}


	public List<Person> getPersonsByHandleState(int con, String convalue,
												String starttime, String endtime, int handleState, UserRole userRole) {
		String queryString = "from Person mo where 1=1 and mo.handleState="
				+ handleState;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	public List<Person> getOutOfTimePersonsByType(int con, String convalue,
												  String starttime, String endtime, int type, UserRole userRole) {
		String queryString = "from Person mo where  mo.type=" + type
				+ " and mo.isOutOfTime=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}


	public List<Person> getOutOfTimePersons(int con, String convalue,
											String starttime, String endtime, UserRole userRole) {
		String queryString = "from Person mo where  mo.isOutOfTime=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}


	public List<Person> getPersonsByType(int con, String convalue,
										 String starttime, String endtime, int type, UserRole userRole) {
		String queryString = "from Person mo where  mo.type=" + type;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}


	public List<Person> getPersonsByUserRole(int con, String convalue,
											 String starttime, String endtime, UserRole userRole) {
		String queryString = "from Person mo where  1=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}


	public List<Person> getNewPersonsByUserRole(UserRole userRole) {
		String queryString = "from Person mo where  mo.isNew=1 and mo.handleState=1 ";

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return personDao.queryList(queryString);
	}

	public List<Person> getPersonsByOption(int con, String convalue,
										   UserRole userRole) {
		/*
		 * 0:'选择类型',1:'人员姓名',2:'人员编号',3:'身份证号',4:'录入人员姓名',5:'DNA',6:'指纹',7:'户籍地',8:'手机号'
		 * ,9:'微信号',10:'性别',11:'QQ号',12:'出生日期'
		 * ,13:'银行卡号',14:'车牌号',15:'车架号',16:'手机串号'
		 * ,17:'发动机号',18:'现住地详址',19:'失踪日期',20:'失踪地址',21:'报案联系人'
		 * ,22:'失踪经过',23:'衣着情况',24:'暂住地址',25:'工作单位',26:'备注信息',27:'携带物品',28:'携带工具'
		 */
		String queryString = "from Person mo where  1=1 ";

		if (con != 0 && convalue != null && !convalue.equals("")) {
			switch (con) {
				case 1:
					queryString += " and mo.name like  '%" + convalue + "%' ";
					break;
				case 2:
					queryString += " and mo.number like  '%" + convalue + "%' ";
					break;
				case 3:
					queryString += " and mo.idcard like  '%" + convalue + "%' ";
					break;
				case 4:
					queryString += " and mo.userRole.realname like  '%" + convalue
							+ "%' ";
					break;
				case 5:
					queryString += " and mo.gamblingCriminalMan.dnanumber like  '%"
							+ convalue + "%' ";
					break;
				case 6:
					queryString += " and mo.gamblingCriminalMan.fingerPrintNumber like  '%"
							+ convalue + "%' ";
					break;
				case 7:
					queryString += " and mo.registerAddress like  '%" + convalue
							+ "%' ";
					break;
				case 8:
					queryString += " and mo.telphone like  '%" + convalue + "%' ";
					break;
				case 9:
					queryString += " and mo.wechat like  '%" + convalue + "%' ";
					break;
				case 10:
					if (convalue.contains("男")) {
						queryString += " and mo.sex =1 ";
					} else if (convalue.contains("女")) {
						queryString += " and mo.sex =0 ";
					}
					break;
				case 11:
					queryString += " and mo.qq like  '%" + convalue + "%' ";
					break;
				case 12:
					queryString += " and mo.birthday like  '%" + convalue + "%' ";
					break;
				case 13:
					queryString += " and mo.bankCard like  '%" + convalue + "%' ";
					break;
				case 14:
					queryString += " and mo.carFrameNumber like  '%" + convalue
							+ "%' ";
					break;
				case 15:
					queryString += " and mo.gamblingCriminalMan.imei like  '%"
							+ convalue + "%' ";
					break;
				case 16:
					queryString += " and mo.gamblingCriminalMan.fingerPrintNumber like  '%"
							+ convalue + "%' ";
					break;
				case 17:
					queryString += " and mo.gamblingCriminalMan.engineNumber like  '%"
							+ convalue + "%' ";
					break;
				case 18:
					queryString += " and mo.gamblingCriminalMan.currentAddress like  '%"
							+ convalue + "%' ";
					break;
				case 19:
					queryString += " and mo.disappearMan.missingEndTime>='"
							+ convalue + "'";
					queryString += " and mo.disappearMan.missingStartTime<='"
							+ convalue + "'";
					break;
				case 20:
					queryString += " and mo.disappearMan.missingAddress like  '%"
							+ convalue + "%' ";
					break;
				case 21:
					queryString += " and mo.disappearMan.reportContactName like  '%"
							+ convalue + "%' ";
					break;

				case 22:
					queryString += " and mo.disappearMan.missingCause like  '%"
							+ convalue + "%' ";
					break;
				case 23:
					queryString += " and mo.disappearMan.dressSituation like  '%"
							+ convalue + "%' ";
					break;
				case 24:
					queryString += " and mo.guiltSafeguardMan.temporaryAddress like  '%"
							+ convalue + "%' ";
					break;
				case 25:
					queryString += " and mo.guiltSafeguardMan.workdUnit like  '%"
							+ convalue + "%' ";
					break;
				case 26:
					queryString += " and mo.remark like  '%" + convalue + "%' ";
					break;
				case 27:
					queryString += " and mo.carrier like  '%" + convalue + "%' ";
					break;
				case 28:
					queryString += " and mo.carryTool like  '%" + convalue + "%' ";
					break;
				default:
					break;
			}
		}

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return personDao.queryList(queryString);
	}

	/*
	 * 提取方法
	 */
	public List<Person> queryListBySql(int con, String convalue,
									   String starttime, String endtime, UserRole userRole,
									   String queryString) {

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += " and mo.userRole.realname like  '%" + convalue
						+ "%' ";
			}
			if (con == 2) {
				queryString += " and mo.userRole.number like  '%" + convalue
						+ "%' ";
			}
			if (con == 3) {
				queryString += " and mo.userRole.unit.name like  '%" + convalue
						+ "%' ";
			}
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return personDao.queryList(queryString);
	}


	protected String getObjectIds(UserRole userRole) {
		return userRole.getUnit().getPids();
	}

	protected void changeUnitIds(Unit unit) {

		unitService.update(unit);

	}
}
