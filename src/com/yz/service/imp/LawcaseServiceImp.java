package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ILawcaseDao;
import com.yz.model.Lawcase;
import com.yz.model.UserRole;
import com.yz.service.ILawcaseService;
@Component("lawcaseService")
public class LawcaseServiceImp implements ILawcaseService {
	private ILawcaseDao lawcaseDao;
	public ILawcaseDao getLawcaseDao() {
		return lawcaseDao;
	}
	@Resource
	public void setLawcaseDao(ILawcaseDao lawcaseDao) {
		this.lawcaseDao = lawcaseDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#add(com.yz.model.Lawcase)
	 */
	public void add(Lawcase lawcase) throws Exception {
		lawcaseDao.save(lawcase);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#delete(com.yz.model.Lawcase)
	 */
	public void delete(Lawcase lawcase) {
		lawcaseDao.delete(lawcase);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		lawcaseDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#update(com.yz.model.Lawcase)
	 */
	public void update(Lawcase lawcase) {
		lawcaseDao.update(lawcase);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#getLawcases()
	 */
	public List<Lawcase> getLawcases() {
		return lawcaseDao.getLawcases();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#loadById(int)
	 */
	public Lawcase loadById(int id) {
		return lawcaseDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Lawcase mo where 1=1 ";
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
		
		return lawcaseDao.getUniqueResult(queryString,p);
	}
	public Lawcase getLawcaseByLawcasename(String lawcasename) {
		String queryString="from Lawcase mo where mo.name=:lawcasename";
		String[] paramNames=new String[]{"lawcasename"};
		Object[] values=new Object[]{lawcasename};
		return lawcaseDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ILawcaseServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Lawcase> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Lawcase mo where 1=1 ";
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
		return lawcaseDao.pageList(queryString,p,page,size);
	}

	public Lawcase getLawcaseById(Integer uplawcaseid) {
		// TODO Auto-generated method stub
		return lawcaseDao.getLawcaseById(uplawcaseid);
	}
}
