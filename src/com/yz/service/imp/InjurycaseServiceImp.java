package com.yz.service.imp;

import com.yz.dao.InjurycaseDao;
import com.yz.model.Injurycase;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.InjurycaseService;
import com.yz.service.MediaService;
import com.yz.service.UnitService;
import com.yz.util.ExceptionUtil;
import com.yz.util.GenerateSqlFromExcel;
import com.yz.util.IdsOperator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
@Component("injurycaseService")
public class InjurycaseServiceImp extends RoleServiceImp implements InjurycaseService {

	@Resource
	private InjurycaseDao injurycaseDao;

	@Resource
	private UnitService unitService;

	@Resource
	private MediaService mediaService;

	public void add(Injurycase injurycase) throws Exception {

		changeUnitInidsByUserRoleAndIdsOperator(injurycase.getUserRole(), new IdsOperator(injurycase.getId() + "", 1));
		injurycaseDao.save(injurycase);

	}


	public void delete(Injurycase injurycase) {

		changeUnitInidsByUserRoleAndIdsOperator(injurycase.getUserRole(), new IdsOperator(injurycase.getId() + "", -1));
		injurycaseDao.delete(injurycase);
	}


	public void deleteById(int id) {
		injurycaseDao.deleteById(id);
	}


	public List<Injurycase> getInjurycases() {
		return injurycaseDao.getInjurycases();
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole user,
							 int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Injurycase mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 5) {
				queryString = mediaService.setInjurycaseIdsSql(queryString,
						convalue, 0);
			}
			if (con == 6) {
				queryString = mediaService.setInjurycaseIdsSql(queryString,
						convalue, 1);
			}
			if (con == 7) {
				queryString += "and mo.crimePattern like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 8) {
				queryString += "and mo.briefCase like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 9) {
				queryString += "and mo.series like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 10) {
				queryString += "and mo.crimeObject like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}

		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.startTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.startTime<='" + endtime + "'";
		}
		return injurycaseDao.getUniqueResult(queryString, p);
	}

	public Injurycase loadById(int id) {
		return injurycaseDao.loadById(id);
	}

	public Injurycase queryInjurycaseById(int id) {
		String queryString = "from Injurycase mo where mo.id=:id";
		String[] paramNames = new String[]{"id"};
		Object[] values = new Object[]{id};
		return injurycaseDao.queryByNamedParam(queryString, paramNames, values);
	}

	public List<Injurycase> queryList(int con, String convalue, UserRole user,
									  int page, int size, int queryState, String starttime, String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 5) {
				queryString = mediaService.setInjurycaseIdsSql(queryString,
						convalue, 0);
			}
			if (con == 6) {
				queryString = mediaService.setInjurycaseIdsSql(queryString,
						convalue, 1);
			}
			if (con == 7) {
				queryString += "and mo.crimePattern like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 8) {
				queryString += "and mo.briefCase like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 9) {
				queryString += "and mo.series like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
			if (con == 10) {
				queryString += "and mo.crimeObject like ? ";
				p = new Object[]{'%' + convalue + '%'};
			}
		}

		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.startTime>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.startTime<='" + endtime + "'";
		}
		return injurycaseDao.pageList(queryString, p, page, size);
	}

	public void update(Injurycase injurycase) {
		injurycaseDao.update(injurycase);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole,
							 int itype, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Injurycase mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}

			p = new Object[]{'%' + convalue + '%'};
		}
		if (itype != 0) {
			queryString += " and mo.itype =" + itype;
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
		return injurycaseDao.getUniqueResult(queryString, p);
	}

	public List<Injurycase> queryList(int con, String convalue,
									  UserRole userRole, int page, int size, int itype, int queryState,
									  String starttime, String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like ? ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like ? ";
			}
			if (con == 3) {
				queryString += "and mo.caseName like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[]{'%' + convalue + '%'};
		}
		if (itype != 0) {
			queryString += " and mo.itype =" + itype;
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
		return injurycaseDao.pageList(queryString, p, page, size);
	}


	public List<Injurycase> queryInjurycaseByKeyword(String param, int id) {

		String queryString = "from Injurycase mo where  mo.id!=" + id;
		Object[] p = null;
		if (param != null && !param.equals("")) {
			queryString += "and  (mo.caseNumber like ?  or mo.caseType like ? or mo.caseName like ? ) ";
			p = new Object[]{'%' + param + '%', '%' + param + '%',
					'%' + param + '%'};
		}
		queryString += " order by mo.id desc ";
		return injurycaseDao.getObjectsByCondition(queryString, p);

	}

	public List<Injurycase> queryList(int con, String convalue,
									  UserRole userRole, int itype, int queryState, String starttime,
									  String endtime) {
		String queryString = "from Injurycase mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.caseNumber like  '%" + convalue + "%' ";
			}
			if (con == 2) {
				queryString += "and mo.casePlace like  '%" + convalue + "%' ";
			}
			if (con == 3) {
				queryString += "and mo.caseName llike  '%" + convalue + "%' ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like  '%" + convalue
						+ "%' ";
			}
		}
		if (itype != 0) {
			queryString += " and mo.itype =" + itype;
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

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycaseByTypeAndHandleState(int con,
															  String convalue, String starttime, String endtime, int itype,
															  int handleState, UserRole userRole) {
		String queryString = "from Injurycase mo where mo.itype=" + itype
				+ " and mo.handleState=" + handleState;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByHandleState(int con,
														String convalue, String starttime, String endtime, int state,
														UserRole userRole) {
		String queryString = "from Injurycase mo where  mo.handleState="
				+ state;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInOutOfTimejurycasesByUserRole(int con,
															  String convalue, String starttime, String endtime, UserRole userRole) {
		String queryString = "from Injurycase mo where  mo.isOutOfTime=1";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByType(int con, String convalue,
												 String starttime, String endtime, int itype, UserRole userRole) {
		String queryString = "from Injurycase mo where  mo.itype=" + itype;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByUserRole(int con, String convalue,
													 String starttime, String endtime, UserRole userRole) {
		String queryString = "from Injurycase mo where  1=1 ";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getOutOfTimeInjurycasesByType(int con,
														  String convalue, String starttime, String endtime, int itype,
														  UserRole userRole) {
		String queryString = "from Injurycase mo where   mo.itype=" + itype
				+ " and mo.isOutOfTime=1";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}


	// 设置 sql语句 参数配置
	private String setSqlParms(int con, String convalue, String starttime,
							   String endtime, String queryString) {
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

		return queryString;
	}

	public List<Injurycase> getNewInjurycaseByUserRole(UserRole userRole) {
		String queryString = "from Injurycase mo where   mo.isNew=1 and mo.handleState=1 ";

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByOption(int con, String convalue,
												   UserRole userRole) {
		// TODO Auto-generated method stub
		/*
		 * 0:'选择类型',1:'案件编号',2:'案件地址',3:'案件名称',4:'录入人员姓名',5:'录入单位',6:'案发时间',7:'作案对象',8:'作案目标',9:'作案方式',10:'物品特征',11:'联系电话'
		 * ,12:'简要案情',13:'图像实物描述',14:'警情编号'
		 */
		String queryString = "from Injurycase mo where   1=1 ";

		if (con != 0 && convalue != null && !convalue.equals("")) {
			switch (con) {
				case 1:
					queryString += " and mo.caseNumber like  '%" + convalue + "%' ";
					break;
				case 2:
					queryString += " and mo.casePlace like  '%" + convalue + "%' ";
					break;
				case 3:
					queryString += " and mo.caseName like  '%" + convalue + "%' ";
					break;
				case 4:
					queryString += " and mo.userRole.realname like  '%" + convalue
							+ "%' ";
					break;
				case 5:
					queryString += " and mo.userRole.unit.name like  '%" + convalue
							+ "%' ";
					break;
				case 6:
					queryString += " and mo.startTime like  '%" + convalue + "%' ";
					break;
				case 7:
					queryString += " and mo.crimeObject like  '%" + convalue
							+ "%' ";
					break;
				case 8:
					queryString += " and mo.crimeTarget like  '%" + convalue
							+ "%' ";
					break;
				case 9:
					queryString += " and mo.crimePattern like  '%" + convalue
							+ "%' ";
					break;
				case 10:
					queryString += " and mo.goodsFeature like  '%" + convalue
							+ "%' ";
					break;
				case 11:
					queryString += " and mo.telphone like  '%" + convalue + "%' ";
					break;
				case 12:
					queryString += " and mo.briefCase like  '%" + convalue + "%' ";
					break;
				case 13:
					queryString += " and mo.goodsDescription like  '%" + convalue
							+ "%' ";
					break;
				case 14:
					queryString += " and mo.situationNum like  '%" + convalue
							+ "%' ";
					break;
				default:
					break;
			}
		}

		queryString = assembleLimitSqlByUserRole(queryString, userRole);

		return injurycaseDao.queryList(queryString);
	}



	public boolean saveInjurycaseWithExcel(File file, UserRole userRole, int itype) {
		try {
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = generate
					.generateStation(file);
			/*
			 * "案件类型", "案件编号", "案件名称", "录入单位", "录入民警", "录入时间", "办理状态", "是否已串并案",
			 * "案发时间", "案发地点", "简要案情", "办案单位","办案人", "办案人联系电话", "警情编号", "作案目标",
			 * "作案对象", "作案方式", "人员特征", "物品特征", "完结情况", "综合情况", "领导批示"
			 */

			String inids = "";

			for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				Injurycase injurycase = new Injurycase();

				
				String caseTypeName = data[0].toString();
				if (caseTypeName.contains("刑事")) {
					injurycase.setItype(1);
				} else if (caseTypeName.contains("重伤")) {
					injurycase.setItype(2);
				} else if (caseTypeName.contains("团伙系列")) {
					injurycase.setItype(3);
				} else if (caseTypeName.contains("行政")) {
					injurycase.setItype(4);
				} else {
					break;
				}
				injurycase.setCaseNumber(data[1].toString());
				injurycase.setCaseName(data[2].toString());



				injurycase.setUserRole(userRole);

				injurycase.setJoinDate(data[5].toString());

				String handleStateString = data[6].toString();
				if (handleStateString.contains("未")) {
					injurycase.setHandleState(1);
				} else if (handleStateString.contains("在")) {
					injurycase.setHandleState(2);
				} else if (handleStateString.contains("已")) {
					injurycase.setHandleState(3);
				} else {
					injurycase.setHandleState(1);
				}

				String isReltive = data[7].toString();
				if (isReltive.contains("未")) {
					injurycase.setIsRelated(0);
				} else if (isReltive.contains("已")) {
					injurycase.setIsRelated(1);
				} else {
					injurycase.setIsRelated(0);
				}

				injurycase.setStartTime(data[8].toString());
				injurycase.setCasePlace(data[9].toString());
				injurycase.setBriefCase(data[10].toString());
				injurycase.setAppraiserUnitName(data[11].toString());
				injurycase.setAppraiser(data[12].toString());
				injurycase.setTelphone(data[13].toString());
				injurycase.setSituationNum(data[14].toString());
				injurycase.setCrimeTarget(data[15].toString());
				injurycase.setCrimeObject(data[16].toString());
				injurycase.setCrimePattern(data[17].toString());

				injurycase.setPersonFeature(data[18].toString());
				injurycase.setGoodsFeature(data[19].toString());

				// '1':'抓获', '2':'死亡', '3':'撤销案件', '4':'释放', '5':'治安拘留',
				// '6':'刑事拘留', '7':'留置盘问', '8':'其他' }"
				String endSituation = data[20].toString();
				if (endSituation.contains("抓获")) {
					injurycase.setEndSituation(1 + "");
				} else if (endSituation.contains("死亡")) {
					injurycase.setEndSituation(2 + "");
				} else if (endSituation.contains("撤销案件")) {
					injurycase.setEndSituation(3 + "");
				} else if (endSituation.contains("释放")) {
					injurycase.setEndSituation(4 + "");
				} else if (endSituation.contains("治安拘留")) {
					injurycase.setEndSituation(5 + "");
				} else if (endSituation.contains("刑事拘留")) {
					injurycase.setEndSituation(6 + "");
				} else if (endSituation.contains("留置盘问")) {
					injurycase.setEndSituation(7 + "");
				} else if (endSituation.contains("其他")) {
					injurycase.setEndSituation(8 + "");
				}

				injurycase.setComprehensiveJudge(data[21].toString());
				injurycase.setLeaderInstruction(data[22].toString());

				// 设置部门inids
				int inid = injurycaseDao.savereturn(injurycase);
				inids = inids + inid + ",";
			}

			changeUnitInidsByUserRoleAndIdsOperator(userRole, new IdsOperator(inids + "", 1));

			return true;
		} catch (Exception e) {
			ExceptionUtil.getStackTrace(e);
			return false;
		}
	}


	public void changeInjurycaseHandleState(int inid) {
		Injurycase injurycase = injurycaseDao.loadById(inid);
		if (injurycase != null) {
			if (injurycase.getHandleState() == 1) {
				injurycase.setHandleState(2);
				injurycaseDao.update(injurycase);
			}
		}
	}

	@Override
	protected String getObjectIds(UserRole userRole) {
		return userRole.getUnit().getInids();
	}

	@Override
	protected void changeUnitIds(Unit unit) {
		unitService.update(unit);
	}
}
