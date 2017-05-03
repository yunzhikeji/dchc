package com.yz.service.imp;

import com.yz.action.UnitAction;
import com.yz.dao.UnitDao;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.UnitService;
import com.yz.util.InfoType;
import com.yz.util.MyHandleUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("unitService")
public class UnitServiceImp implements UnitService {

	@Resource
	private UnitDao unitDao;


	public void add(Unit unit) throws Exception {
		unitDao.save(unit);
	}

	public void delete(Unit unit) {
		unitDao.delete(unit);
	}

	public void deleteById(int id) {
		unitDao.deleteById(id);
	}

	public void update(Unit unit) {
		unitDao.update(unit);
	}

	public List<Unit> getUnits() {
		return unitDao.getUnits();
	}

	public Unit loadById(int id) {
		return unitDao.loadById(id);
	}

	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Unit mo where 1=1 ";
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

		return unitDao.getUniqueResult(queryString, p);
	}

	public List<Unit> queryList(int con, String convalue, UserRole userRole,
								int page, int size) {
		String queryString = "from Unit mo where 1=1 ";
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
		return unitDao.pageList(queryString, p, page, size);
	}

	public Unit getUnitById(Integer upunitid) {
		return unitDao.getUnitById(upunitid);
	}

	public Unit getUnitByName(String unitName) {
		String queryString = "from Unit mo where mo.name=:unitName";
		String[] paramNames = new String[]{"unitName"};
		Object[] values = new Object[]{unitName};
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Unit getUnitByNumber(String unitNumber) {
		String queryString = "from Unit mo where mo.number=:unitNumber";
		String[] paramNames = new String[]{"unitNumber"};
		Object[] values = new Object[]{unitNumber};
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Unit getUnitByUid(int uid) {
		String queryString = "from Unit mo where mo.id=:uid";
		String[] paramNames = new String[]{"uid"};
		Object[] values = new Object[]{uid};
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	public void updateByCondition(String pids, String inids, String cids,
								  int uid) {
		String queryString = "update Unit mo set mo.pids=:pids,mo.inids=:inids,mo.cids=:cids where mo.id=:uid";
		String[] paramNames = new String[]{"pids", "inids", "cids", "uid"};
		Object[] values = new Object[]{pids, inids, cids, uid};
		unitDao.updateByHql(queryString, paramNames, values);
	}

	public void updateUnitByUserRoleAndInfoType(Unit unit, String ids,
												InfoType infoType, int operationType) {

		if (unit != null) {
			String pids = unit.getPids();
			String inids = unit.getInids();
			String cids = unit.getCids();
			switch (infoType) {
				case PERSON:

					pids = MyHandleUtil.handleIDs(pids, ids, operationType);

					break;
				case CASE:

					inids = MyHandleUtil.handleIDs(inids, ids, operationType);

					break;
				case CLUE:

					cids = MyHandleUtil.handleIDs(cids, ids, operationType);

					break;
				default:
					break;
			}
			this.updateByCondition(pids, inids, cids, unit.getId());
		}

		if (operationType == -1) {
			this.updateJudgeUnit(ids, infoType, operationType);
		}

	}

	// 修改指定研判部门 编号为：371402020000,主要为删除人员、案件、线索同时更新研判部门中的相应ids
	public void updateJudgeUnit(String ids, InfoType infoType, int operationType) {
		Unit unit = this.getUnitByNumber(UnitAction.OPERTION_UNIT_NUMBER);
		if (unit != null) {
			String pids = unit.getPids();
			String inids = unit.getInids();
			String cids = unit.getCids();
			switch (infoType) {
				case PERSON:

					pids = MyHandleUtil.handleIDs(pids, ids, operationType);

					break;
				case CASE:

					inids = MyHandleUtil.handleIDs(inids, ids, operationType);

					break;
				case CLUE:

					cids = MyHandleUtil.handleIDs(cids, ids, operationType);

					break;
				default:
					break;
			}
			this.updateByCondition(pids, inids, cids, unit.getId());
		}
	}
}
