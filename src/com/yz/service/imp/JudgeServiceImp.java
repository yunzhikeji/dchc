package com.yz.service.imp;

import com.yz.action.UnitAction;
import com.yz.dao.*;
import com.yz.model.*;
import com.yz.service.JudgeService;
import com.yz.util.DateTimeKit;
import com.yz.util.IdsOperator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Component("judgeServiceImp")
public class JudgeServiceImp extends RoleServiceImp implements JudgeService {

	@Resource
	private JudgeDao judgeDao;
	@Resource
	private PersonDao personDao;
	@Resource
	private InjurycaseDao injurycaseDao;
	@Resource
	private ClueDao clueDao;
	@Resource
	private UnitDao unitDao;


	public void addAndChangeUnit(Judge judge) throws Exception {

		changeUnitByJudge(judge);
		judgeDao.save(judge);
	}

	public void delete(Judge judge) {

		judgeDao.delete(judge);
	}

	public void deleteById(int id) {
		judgeDao.deleteById(id);
	}

	public void update(Judge judge) {
		judgeDao.update(judge);
	}

	public List<Judge> getJudges() {
		return judgeDao.getJudges();
	}

	public Judge loadById(int id) {
		return judgeDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

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
			p = new Object[]{'%' + convalue + '%'};
		}

		return judgeDao.getUniqueResult(queryString, p);
	}


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
			p = new Object[]{'%' + convalue + '%'};
		}
		return judgeDao.pageList(queryString, p, page, size);
	}


	public List<Judge> getNewJudges() {
		// TODO Auto-generated method stub
		String queryString = "from Judge mo where mo.isNew=1 ";
		return judgeDao.queryList(queryString);
	}

	public void changeUnitByJudge(Judge judge) {

		if (judge.getPerson() != null) {
			changeUnitPidsByUserRoleAndIdsOperator(judge.getPerson().getUserRole(), new IdsOperator(judge.getPerson().getId() + "", 1));
		}

		if (judge.getInjurycase() != null) {
			changeUnitInidsByUserRoleAndIdsOperator(judge.getInjurycase().getUserRole(), new IdsOperator(judge.getInjurycase().getId() + "", 1));
		}

		if (judge.getClue() != null) {
			changeUnitCidsByUserRoleAndIdsOperator(judge.getClue().getUserRole(), new IdsOperator(judge.getClue().getId() + "", 1));
		}
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

			// 1:获得当前研判信息，判断当前时间是否超时
			String nowDay = DateTimeKit.getLocalDate();
			String deadline = judge.getDeadline();
			int daysBetween = 0;
			try {
				daysBetween = DateTimeKit.daysBetween(deadline, nowDay);
			} catch (ParseException e) {
			}
			if (daysBetween > 12) {
				// 2：如果超期进行超期处理
				// 获得研判下发的部门
				String units = judge.getReportUnit().replace(" ", "");

				String[] names = units.split(",");

				for (int i = 0; i < names.length; i++) {

					String queryStringUnit = "from Unit mo where mo.name=:name";
					String[] paramNames = new String[]{"name"};
					Object[] values = new Object[]{names[i]};
					Unit unit = unitDao.queryByNamedParam(queryStringUnit,
							paramNames, values);
					if (unit != null) {
						if (unit.getNumber().equals(UnitAction.OPERTION_UNIT_NUMBER)) {

							boolean isCriminalJudgeNull = (judge
									.getCriminalJudge() == null)
									|| (judge.getCriminalJudge()
									.replace(" ", "").equals(""));

							boolean isIntelligenceJudgeNull = (judge
									.getCriminalJudge() == null)
									|| (judge.getCriminalJudge()
									.replace(" ", "").equals(""));

							boolean isImageJudgeNull = (judge
									.getCriminalJudge() == null)
									|| (judge.getCriminalJudge()
									.replace(" ", "").equals(""));

							boolean isNetworkJudgeNull = (judge
									.getCriminalJudge() == null)
									|| (judge.getCriminalJudge()
									.replace(" ", "").equals(""));

							if (isCriminalJudgeNull && isIntelligenceJudgeNull && isImageJudgeNull && isNetworkJudgeNull) {
								handleOutOfTime(judge);
							}
						}
					}

				}
			}

		}
	}

	@Override
	protected String getObjectIds(UserRole userRole) {
		return null;
	}

	@Override
	protected void changeUnitIds(Unit unit) {

	}
}
