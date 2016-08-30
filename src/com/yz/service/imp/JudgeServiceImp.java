package com.yz.service.imp;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import com.yz.dao.IClueDao;
import com.yz.dao.IInjurycaseDao;
import com.yz.dao.IJudgeDao;
import com.yz.dao.IPersonDao;
import com.yz.dao.IUnitDao;
import com.yz.model.Clue;
import com.yz.model.Injurycase;
import com.yz.model.Judge;
import com.yz.model.Person;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IJudgeService;
import com.yz.util.DateTimeKit;

public class JudgeServiceImp implements IJudgeService {

	private IJudgeDao judgeDao;
	private IPersonDao personDao;
	private IInjurycaseDao injurycaseDao;
	private IClueDao clueDao;
	private IUnitDao unitDao;

	public IJudgeDao getJudgeDao() {
		return judgeDao;
	}

	@Resource
	public void setJudgeDao(IJudgeDao judgeDao) {
		this.judgeDao = judgeDao;
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	public IInjurycaseDao getInjurycaseDao() {
		return injurycaseDao;
	}

	@Resource
	public void setInjurycaseDao(IInjurycaseDao injurycaseDao) {
		this.injurycaseDao = injurycaseDao;
	}

	public IClueDao getClueDao() {
		return clueDao;
	}

	@Resource
	public void setClueDao(IClueDao clueDao) {
		this.clueDao = clueDao;
	}

	public IUnitDao getUnitDao() {
		return unitDao;
	}

	@Resource
	public void setUnitDao(IUnitDao unitDao) {
		this.unitDao = unitDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#add(com.yz.model.Judge)
	 */
	public void add(Judge judge) throws Exception {
		judgeDao.save(judge);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#delete(com.yz.model.Judge)
	 */
	public void delete(Judge judge) {
		judgeDao.delete(judge);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		judgeDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#update(com.yz.model.Judge)
	 */
	public void update(Judge judge) {
		judgeDao.update(judge);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#getJudges()
	 */
	public List<Judge> getJudges() {
		return judgeDao.getJudges();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#loadById(int)
	 */
	public Judge loadById(int id) {
		return judgeDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#getPageCount(int,
	 *      java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Judge mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}

		return judgeDao.getUniqueResult(queryString, p);
	}

	public Judge getJudgeByJudgename(String judgename) {
		String queryString = "from Judge mo where mo.name=:judgename";
		String[] paramNames = new String[] { "judgename" };
		Object[] values = new Object[] { judgename };
		return judgeDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IJudgeServiceImp#queryList(int, java.lang.String,
	 *      int, int)
	 */
	public List<Judge> queryList(int con, String convalue, UserRole userRole,
			int page, int size) {
		String queryString = "from Judge mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";

			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return judgeDao.pageList(queryString, p, page, size);
	}

	public Judge getJudgeById(Integer upjudgeid) {
		// TODO Auto-generated method stub
		return judgeDao.getJudgeById(upjudgeid);
	}

	public List<Judge> loadByTypeAndPid(int jtype, Integer pid) {
		// TODO Auto-generated method stub
		String queryString = "from Judge mo where mo.jtype=:jtype and mo.person.id=:pid";
		String[] paramNames = new String[] { "jtype", "pid" };
		Object[] values = new Object[] { jtype, pid };
		return judgeDao.queryList(queryString, paramNames, values);
	}

	public List<Judge> loadClueByTypeAndPid(int jtype, Integer cid) {
		String queryString = "from Judge mo where mo.jtype=:jtype and mo.clue.id=:cid";
		String[] paramNames = new String[] { "jtype", "cid" };
		Object[] values = new Object[] { jtype, cid };
		return judgeDao.queryList(queryString, paramNames, values);
	}

	public List<Judge> loadInjurycaseByTypeAndPid(int jtype, Integer inid) {
		String queryString = "from Judge mo where mo.jtype=:jtype and mo.injurycase.id=:inid";
		String[] paramNames = new String[] { "jtype", "inid" };
		Object[] values = new Object[] { jtype, inid };
		return judgeDao.queryList(queryString, paramNames, values);
	}
	
	
	public List<Judge> getNewJudges() {
		// TODO Auto-generated method stub
		String queryString = "from Judge mo where mo.isNew=1 ";
		return judgeDao.queryList(queryString);
	}

	// 设置单个事项超期办理
	public void handleOutOfTime(Judge judge) {

		if (judge.getPerson() != null) {

			Person person = personDao.getPersonById(judge.getPerson().getId());

			if (person != null) {
				if (person.getIsOutOfTime() == null
						|| person.getIsOutOfTime() != 1) {

					person.setIsOutOfTime(1);
					personDao.update(person);
				}
			}

		} else if (judge.getClue() != null) {

			Clue clue = clueDao.getClueById(judge.getClue().getId());

			if (clue != null) {
				if (clue.getIsOutOfTime() == null || clue.getIsOutOfTime() != 1) {
					clue.setIsOutOfTime(1);
					clueDao.update(clue);
				}
			}

		} else if (judge.getInjurycase() != null) {

			Injurycase injurycase = injurycaseDao.getInjurycaseById(judge
					.getInjurycase().getId());
			if (injurycase != null) {
				if (injurycase.getIsOutOfTime() == null
						|| injurycase.getIsOutOfTime() != 1) {
					injurycase.setIsOutOfTime(1);
					injurycaseDao.update(injurycase);
				}
			}
		}
	}

	public void doJob() {

		String queryString = "from Judge mo where mo.deadline!=null and mo.deadline!=''";
		List<Judge> judges = judgeDao.queryList(queryString);

		for (Judge judge : judges) {

			//1:获得当前研判信息，判断当前时间是否超时
			String nowDay = DateTimeKit.getLocalDate();
			String deadline = judge.getDeadline();
			int daysBetween = 0;
			try {
				daysBetween = DateTimeKit.daysBetween(deadline, nowDay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("时间比较异常");
			}
			if (daysBetween > 12) {
			//2：如果超期进行超期处理
				// 获得研判下发的部门
				String units = judge.getReportUnit().replace(" ", "");

				String[] names = units.split(",");

				for (int i = 0; i < names.length; i++) {

					String queryStringUnit = "from Unit mo where mo.name=:name";
					String[] paramNames = new String[] { "name" };
					Object[] values = new Object[] { names[i] };
					Unit unit = unitDao.queryByNamedParam(queryStringUnit,
							paramNames, values);
					if (unit != null) {
						if (unit.getNumber().equals("DC001")) {
							if (judge.getCriminalJudge() == null
									|| judge.getCriminalJudge()
											.replace(" ", "").equals("")) {
								handleOutOfTime(judge);
							}
						} else if (unit.getNumber().equals("DC002")) {
							if (judge.getIntelligenceJudge() == null
									|| judge.getIntelligenceJudge().replace(
											" ", "").equals("")) {
								handleOutOfTime(judge);
							}
						} else if (unit.getNumber().equals("DC003")) {
							if (judge.getImageJudge() == null
									|| judge.getImageJudge().replace(" ", "")
											.equals("")) {
								handleOutOfTime(judge);
							}
						} else if (unit.getNumber().equals("DC004")) {
							if (judge.getNetworkJudge() == null
									|| judge.getNetworkJudge().replace(" ", "")
											.equals("")) {
								handleOutOfTime(judge);
							}
						}
					}

				}
			}

		}
	}

}
