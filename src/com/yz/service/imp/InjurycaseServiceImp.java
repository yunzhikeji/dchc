/**
 * 
 */
package com.yz.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IInjurycaseDao;
import com.yz.model.Injurycase;
import com.yz.model.UserRole;
import com.yz.service.IInjurycaseService;
import com.yz.service.IUnitService;
import com.yz.util.GenerateSqlFromExcel;
import com.yz.util.InfoType;
import com.yz.util.MyHandleUtil;

/**
 * @author
 * 
 */
@Component("injurycaseService")
public class InjurycaseServiceImp implements IInjurycaseService {
	private IInjurycaseDao injurycaseDao;

	private IUnitService unitService;

	public IUnitService getUnitService() {
		return unitService;
	}

	@Resource
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public IInjurycaseDao getInjurycaseDao() {
		return injurycaseDao;
	}

	@Resource
	public void setInjurycaseDao(IInjurycaseDao injurycaseDao) {
		this.injurycaseDao = injurycaseDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#add(com.yz.model.Injurycase)
	 */
	public void add(Injurycase injurycase) throws Exception {
		injurycaseDao.save(injurycase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#delete(com.yz.model.Injurycase)
	 */
	public void delete(Injurycase injurycase) {
		injurycaseDao.delete(injurycase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#deleteById(int)
	 */
	public void deleteById(int id) {
		injurycaseDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getInjurycaseById(java.lang.Integer)
	 */
	public Injurycase getInjurycaseById(Integer upinjurycaseid) {
		return injurycaseDao.getInjurycaseById(upinjurycaseid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getInjurycases()
	 */
	public List<Injurycase> getInjurycases() {
		return injurycaseDao.getInjurycases();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getPageCount(int, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#getTotalCount(int,
	 *      java.lang.String, com.yz.model.UserRole, int, int, java.lang.String,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole user,
			int queryState, String starttime, String endtime) {
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
			p = new Object[] { '%' + convalue + '%' };
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

	public Injurycase getInjurycaseByInjurycasename(String injurycasename) {
		String queryString = "from Injurycase mo where mo.casename=:injurycasename";
		String[] paramNames = new String[] { "injurycasename" };
		Object[] values = new Object[] { injurycasename };
		return injurycaseDao.queryByNamedParam(queryString, paramNames, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#loadById(int)
	 */
	public Injurycase loadById(int id) {
		return injurycaseDao.loadById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#queryInjurycaseById(int)
	 */
	public Injurycase queryInjurycaseById(int id) {
		String queryString = "from Injurycase mo where mo.id=:id";
		String[] paramNames = new String[] { "id" };
		Object[] values = new Object[] { id };
		return injurycaseDao.queryByNamedParam(queryString, paramNames, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#queryList(int, java.lang.String,
	 *      com.yz.model.UserRole, int, int, int, int, java.lang.String,
	 *      java.lang.String)
	 */
	public List<Injurycase> queryList(int con, String convalue, UserRole user,
			int page, int size, int queryState, String starttime, String endtime) {
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
			p = new Object[] { '%' + convalue + '%' };
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IInjurycaseService#update(com.yz.model.Injurycase)
	 */
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

			p = new Object[] { '%' + convalue + '%' };
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
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);
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
			p = new Object[] { '%' + convalue + '%' };
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
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);
		return injurycaseDao.pageList(queryString, p, page, size);
	}

	public List<Injurycase> getInjurycaseByTypeAndHandleState(int itype,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where mo.itype=" + itype
				+ " and mo.handleState=" + handleState;
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);
		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> queryInjurycaseBySeries(String series, int id) {
		String queryString = "from Injurycase mo where mo.series=:series and mo.isRelated=1 and mo.id!="
				+ id;
		String[] paramNames = new String[] { "series" };
		Object[] values = new Object[] { series };
		return injurycaseDao.queryList(queryString, paramNames, values);
	}

	public List<Injurycase> queryInjurycaseByKeyword(String param, int id) {

		String queryString = "from Injurycase mo where  mo.id!=" + id;
		Object[] p = null;
		if (param != null && !param.equals("")) {
			queryString += "and  (mo.caseNumber like ?  or mo.caseType like ? or mo.caseName like ? ) ";
			p = new Object[] { '%' + param + '%', '%' + param + '%',
					'%' + param };
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
				queryString += "and mo.userRole.realname like  '%" + convalue + "%' ";
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
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);
		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycaseByTypeAndHandleState(int con,
			String convalue, String starttime, String endtime, int itype,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where mo.itype=" + itype
				+ " and mo.handleState=" + handleState;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByHandleState(int con,
			String convalue, String starttime, String endtime, int state,
			UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  mo.handleState="
				+ state;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInOutOfTimejurycasesByUserRole(int con,
			String convalue, String starttime, String endtime, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  mo.isOutOfTime=1";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByType(int con, String convalue,
			String starttime, String endtime, int itype, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  mo.itype=" + itype;

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getInjurycasesByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where  1=1 ";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getOutOfTimeInjurycasesByType(int con,
			String convalue, String starttime, String endtime, int itype,
			UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where   mo.itype=" + itype
				+ " and mo.isOutOfTime=1";

		queryString = setSqlParms(con, convalue, starttime, endtime,
				queryString);

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return injurycaseDao.queryList(queryString);
	}

	public List<Injurycase> getNewInjurycaseByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Injurycase mo where   mo.isNew=1 and mo.handleState=1 ";

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

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

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

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

	public void saveInjurycaseWithExcel(File file, UserRole userRole, int itype) {
		// TODO Auto-generated method stub
		try {
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = generate
					.generateStationInjurycase(file);
			/*
			 * "案件类型", "案件编号", "案件名称", "录入单位", "录入民警", "录入时间", "办理状态", "是否已串并案",
			 * 8* "案发时间", "案发地点", "简要案情", "鉴定人", "鉴定人联系电话", "警情编号", "作案目标",
			 * "作案对象", 16 * "作案方式", "人员特征", "物品特征", "完结情况", "综合情况", "领导批示"
			 */
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
				}
				injurycase.setCaseNumber(data[1].toString());
				injurycase.setCaseName(data[2].toString());

				injurycase.setUserRole(userRole);
				injurycase.setJoinDate(data[5].toString());

				String handleStateString = data[6].toString();
				if (handleStateString.contains("未")) {
					injurycase.setHandleState(1);
				} else if (caseTypeName.contains("在")) {
					injurycase.setHandleState(2);
				} else if (caseTypeName.contains("已")) {
					injurycase.setHandleState(3);
				}

				String isReltive = data[7].toString();
				if (isReltive.contains("未")) {
					injurycase.setIsRelated(0);
				} else if (isReltive.contains("已")) {
					injurycase.setIsRelated(1);
				}

				injurycase.setStartTime(data[8].toString());
				injurycase.setCasePlace(data[9].toString());
				injurycase.setBriefCase(data[10].toString());
				injurycase.setAppraiser(data[11].toString());
				injurycase.setTelphone(data[12].toString());
				injurycase.setSituationNum(data[13].toString());
				injurycase.setCrimeTarget(data[14].toString());
				injurycase.setCrimeObject(data[15].toString());
				injurycase.setCrimePattern(data[16].toString());

				injurycase.setPersonFeature(data[17].toString());
				injurycase.setGoodsFeature(data[18].toString());

				// '1':'抓获', '2':'死亡', '3':'撤销案件', '4':'释放', '5':'治安拘留',
				// '6':'刑事拘留', '7':'留置盘问', '8':'其他' }"
				String endSituation = data[19].toString();
				if (handleStateString.contains("抓获")) {
					injurycase.setEndSituation(1 + "");
				} else if (caseTypeName.contains("死亡")) {
					injurycase.setEndSituation(2 + "");
				} else if (caseTypeName.contains("已")) {
					injurycase.setEndSituation(3 + "");
				} else if (caseTypeName.contains("在")) {
					injurycase.setEndSituation(4 + "");
				} else if (caseTypeName.contains("已")) {
					injurycase.setEndSituation(5 + "");
				} else if (caseTypeName.contains("在")) {
					injurycase.setEndSituation(6 + "");
				} else if (caseTypeName.contains("已")) {
					injurycase.setEndSituation(7 + "");
				} else if (caseTypeName.contains("已")) {
					injurycase.setEndSituation(8 + "");
				}

				injurycase.setComprehensiveJudge(data[20].toString());
				injurycase.setLeaderInstruction(data[21].toString());

				injurycaseDao.save(injurycase);

				// 设置部门inids
				int inid = injurycaseDao.savereturn(injurycase);
				unitService.updateUnitByUserRoleAndInfoType(userRole.getUnit(),
						inid + "", InfoType.CASE, 1);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
