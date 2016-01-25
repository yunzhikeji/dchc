package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ITroubleshootingDao;
import com.yz.model.Troubleshooting;
import com.yz.model.UserRole;
import com.yz.service.ITroubleshootingService;
@Component("troubleshootingService")
public class TroubleshootingServiceImp implements ITroubleshootingService {
	private ITroubleshootingDao troubleshootingDao;
	public ITroubleshootingDao getTroubleshootingDao() {
		return troubleshootingDao;
	}
	@Resource
	public void setTroubleshootingDao(ITroubleshootingDao troubleshootingDao) {
		this.troubleshootingDao = troubleshootingDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#add(com.yz.model.Troubleshooting)
	 */
	public void add(Troubleshooting troubleshooting) throws Exception {
		troubleshootingDao.save(troubleshooting);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#delete(com.yz.model.Troubleshooting)
	 */
	public void delete(Troubleshooting troubleshooting) {
		troubleshootingDao.delete(troubleshooting);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		troubleshootingDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#update(com.yz.model.Troubleshooting)
	 */
	public void update(Troubleshooting troubleshooting) {
		troubleshootingDao.update(troubleshooting);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#getTroubleshootings()
	 */
	public List<Troubleshooting> getTroubleshootings() {
		return troubleshootingDao.getTroubleshootings();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#loadById(int)
	 */
	public Troubleshooting loadById(int id) {
		return troubleshootingDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Troubleshooting mo where 1=1 ";
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
		
		return troubleshootingDao.getUniqueResult(queryString,p);
	}
	public Troubleshooting getTroubleshootingByTroubleshootingname(String troubleshootingname) {
		String queryString="from Troubleshooting mo where mo.name=:troubleshootingname";
		String[] paramNames=new String[]{"troubleshootingname"};
		Object[] values=new Object[]{troubleshootingname};
		return troubleshootingDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ITroubleshootingServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Troubleshooting> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Troubleshooting mo where 1=1 ";
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
		return troubleshootingDao.pageList(queryString,p,page,size);
	}

	public Troubleshooting getTroubleshootingById(Integer uptroubleshootingid) {
		// TODO Auto-generated method stub
		return troubleshootingDao.getTroubleshootingById(uptroubleshootingid);
	}
}
