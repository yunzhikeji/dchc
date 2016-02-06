package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IJudgeDao;
import com.yz.model.Judge;
import com.yz.model.UserRole;
import com.yz.service.IJudgeService;
@Component("judgeService")
public class JudgeServiceImp implements IJudgeService {
	private IJudgeDao judgeDao;
	public IJudgeDao getJudgeDao() {
		return judgeDao;
	}
	@Resource
	public void setJudgeDao(IJudgeDao judgeDao) {
		this.judgeDao = judgeDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#add(com.yz.model.Judge)
	 */
	public void add(Judge judge) throws Exception {
		judgeDao.save(judge);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#delete(com.yz.model.Judge)
	 */
	public void delete(Judge judge) {
		judgeDao.delete(judge);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		judgeDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#update(com.yz.model.Judge)
	 */
	public void update(Judge judge) {
		judgeDao.update(judge);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#getJudges()
	 */
	public List<Judge> getJudges() {
		return judgeDao.getJudges();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#loadById(int)
	 */
	public Judge loadById(int id) {
		return judgeDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole) {
		String queryString = "select count(*) from Judge mo where 1=1 ";
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
		
		return judgeDao.getUniqueResult(queryString,p);
	}
	public Judge getJudgeByJudgename(String judgename) {
		String queryString="from Judge mo where mo.name=:judgename";
		String[] paramNames=new String[]{"judgename"};
		Object[] values=new Object[]{judgename};
		return judgeDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IJudgeServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Judge> queryList(int con, String convalue, UserRole userRole, int page, int size) {
		String queryString = "from Judge mo where 1=1 ";
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
		return judgeDao.pageList(queryString,p,page,size);
	}

	public Judge getJudgeById(Integer upjudgeid) {
		// TODO Auto-generated method stub
		return judgeDao.getJudgeById(upjudgeid);
	}
	public List<Judge> loadByTypeAndPid(int jtype, Integer pid) {
		// TODO Auto-generated method stub
		String queryString="from Judge mo where mo.jtype=:jtype and mo.person.id=:pid";
		String[] paramNames=new String[]{"jtype","pid"};
		Object[] values=new Object[]{jtype,pid};
		return judgeDao.queryList(queryString, paramNames, values);
	}
}
