package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IUnitDao;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IUnitService;
import com.yz.util.InfoType;
import com.yz.util.MyHandleUtil;

@Component("unitService")
public class UnitServiceImp implements IUnitService {
	private IUnitDao unitDao;

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
	 * @see com.yz.service.imp.IUnitServiceImp#add(com.yz.model.Unit)
	 */
	public void add(Unit unit) throws Exception {
		unitDao.save(unit);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#delete(com.yz.model.Unit)
	 */
	public void delete(Unit unit) {
		unitDao.delete(unit);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		unitDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#update(com.yz.model.Unit)
	 */
	public void update(Unit unit) {
		unitDao.update(unit);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#getUnits()
	 */
	public List<Unit> getUnits() {
		return unitDao.getUnits();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#loadById(int)
	 */
	public Unit loadById(int id) {
		return unitDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#getPageCount(int,
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
	 * @see com.yz.service.imp.IUnitServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
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
			p = new Object[] { '%' + convalue + '%' };
		}

		return unitDao.getUniqueResult(queryString, p);
	}

	public Unit getUnitByUnitname(String unitname) {
		String queryString = "from Unit mo where mo.name=:unitname";
		String[] paramNames = new String[] { "unitname" };
		Object[] values = new Object[] { unitname };
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IUnitServiceImp#queryList(int, java.lang.String,
	 *      int, int)
	 */
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
			p = new Object[] { '%' + convalue + '%' };
		}
		return unitDao.pageList(queryString, p, page, size);
	}

	public Unit getUnitById(Integer upunitid) {
		// TODO Auto-generated method stub
		return unitDao.getUnitById(upunitid);
	}

	public Unit getUnitByName(String unitName) {
		// TODO Auto-generated method stub
		String queryString = "from Unit mo where mo.name=:unitName";
		String[] paramNames = new String[] { "unitName" };
		Object[] values = new Object[] { unitName };
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Unit getUnitByNumber(String unitNumber) {
		// TODO Auto-generated method stub
		String queryString = "from Unit mo where mo.number=:unitNumber";
		String[] paramNames = new String[] { "unitNumber" };
		Object[] values = new Object[] { unitNumber };
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Unit queryByUid(int uid) {
		String queryString = "from Unit mo where mo.id=:uid";
		String[] paramNames = new String[] { "uid" };
		Object[] values = new Object[] { uid };
		return unitDao.queryByNamedParam(queryString, paramNames, values);
	}

	public void updateByCondition(String pids, String inids, String cids,
			int uid) {
		String queryString = "update Unit mo set mo.pids=:pids,mo.inids=:inids,mo.cids=:cids where mo.id=:uid";
		String[] paramNames = new String[] { "pids", "inids", "cids", "uid" };
		Object[] values = new Object[] { pids, inids, cids, uid };
		unitDao.updateByHql(queryString, paramNames, values);
	}

	public void updateUnitByUserRoleAndInfoType(Unit unit, String id,
			InfoType infoType, int operationType) {
		// TODO Auto-generated method stub
		
		if (unit != null) {
			String pids = unit.getPids();
			String inids = unit.getInids();
			String cids = unit.getCids();
			switch (infoType) {
			case PERSON:
				
				pids = MyHandleUtil.handleIDs(pids, id, operationType);
				
				break;
			case CASE:
				
				inids = MyHandleUtil.handleIDs(inids, id, operationType);
				
				break;
			case CLUE:
				
				cids = MyHandleUtil.handleIDs(cids, id, operationType);
				
				break;
			default:
				break;
			}
			this.updateByCondition(pids, inids, cids, unit.getId());
		}
		
		if(operationType==-1)
		{
			this.updateJudgeUnit(id, infoType,operationType);
		}

	}

	//修改指定研判部门 编号为：371402020000,主要为删除人员、案件、线索同时更新研判部门中的相应ids
	public void updateJudgeUnit(String id, InfoType infoType, int operationType) {
		// TODO Auto-generated method stub
			Unit unit = this.getUnitByNumber("371402020000");
			if(unit!=null)
			{
				String pids = unit.getPids();
				String inids = unit.getInids();
				String cids = unit.getCids();
				switch (infoType) {
				case PERSON:
					
					pids = MyHandleUtil.handleIDs(pids, id, operationType);
					
					break;
				case CASE:
					
					inids = MyHandleUtil.handleIDs(inids, id, operationType);
					
					break;
				case CLUE:
					
					cids = MyHandleUtil.handleIDs(cids, id, operationType);
					
					break;
				default:
					break;
				}
				this.updateByCondition(pids, inids, cids, unit.getId());
			}
	}
}
