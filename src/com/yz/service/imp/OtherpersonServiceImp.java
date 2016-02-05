package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IOtherpersonDao;
import com.yz.model.Otherperson;
import com.yz.model.UserRole;
import com.yz.service.IOtherpersonService;
@Component("otherpersonService")
public class OtherpersonServiceImp implements IOtherpersonService {
	private IOtherpersonDao otherpersonDao;
	public IOtherpersonDao getOtherpersonDao() {
		return otherpersonDao;
	}
	@Resource
	public void setOtherpersonDao(IOtherpersonDao otherpersonDao) {
		this.otherpersonDao = otherpersonDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#add(com.yz.model.Otherperson)
	 */
	public void add(Otherperson otherperson) throws Exception {
		otherpersonDao.save(otherperson);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#delete(com.yz.model.Otherperson)
	 */
	public void delete(Otherperson otherperson) {
		otherpersonDao.delete(otherperson);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		otherpersonDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#update(com.yz.model.Otherperson)
	 */
	public void update(Otherperson otherperson) {
		otherpersonDao.update(otherperson);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#getOtherpersons()
	 */
	public List<Otherperson> getOtherpersons() {
		return otherpersonDao.getOtherpersons();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#loadById(int)
	 */
	public Otherperson loadById(int id) {
		return otherpersonDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Otherperson mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		
		return otherpersonDao.getUniqueResult(queryString,p);
	}
	public Otherperson getOtherpersonByOtherpersonname(String otherpersonname) {
		String queryString="from Otherperson mo where mo.name=:otherpersonname";
		String[] paramNames=new String[]{"otherpersonname"};
		Object[] values=new Object[]{otherpersonname};
		return otherpersonDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IOtherpersonServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Otherperson> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Otherperson mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
				
			}
			if(con==2){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return otherpersonDao.pageList(queryString,p,page,size);
	}

	public Otherperson getOtherpersonById(Integer upotherpersonid) {
		// TODO Auto-generated method stub
		return otherpersonDao.getOtherpersonById(upotherpersonid);
	}
	public List<Otherperson> getOtherpersonByOtype(int otype,Integer pid) {
		// TODO Auto-generated method stub.
		String queryString = "from Otherperson mo where 1=1 and mo.otype=? and mo.person.id=? ";
		Object[] p = new Object[]{otype,pid};
		return otherpersonDao.getObjectsByCondition(queryString, p);
	}
}
