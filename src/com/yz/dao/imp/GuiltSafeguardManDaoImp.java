package com.yz.dao.imp;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.yz.dao.IGuiltSafeguardManDao;
import com.yz.model.GuiltSafeguardMan;
@Component("guiltSafeguardManDao")
public class GuiltSafeguardManDaoImp implements IGuiltSafeguardManDao {
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#save(com.yz.model.GuiltSafeguardMan)
	 */
	public void save(GuiltSafeguardMan guiltSafeguardMan) {
		this.hibernateTemplate.save(guiltSafeguardMan);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#savereturn(com.yz.model.GuiltSafeguardMan)
	 */
	public Integer savereturn(GuiltSafeguardMan guiltSafeguardMan) {
		return (Integer) this.hibernateTemplate.save(guiltSafeguardMan);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#delete(com.yz.model.GuiltSafeguardMan)
	 */
	public void delete(GuiltSafeguardMan guiltSafeguardMan) {
		this.hibernateTemplate.delete(guiltSafeguardMan);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#update(com.yz.model.GuiltSafeguardMan)
	 */
	public void update(GuiltSafeguardMan guiltSafeguardMan) {
		this.hibernateTemplate.update(guiltSafeguardMan);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public void updateByHql(final String hql,final String[] paramNames,final Object[] values) {
		this.hibernateTemplate.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], values[i]);
				}
				query.executeUpdate();
				return null;
			}
			
		});
	}
	
	//获得所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#getGuiltSafeguardMans()
	 */
	public List<GuiltSafeguardMan> getGuiltSafeguardMans() {
		return this.hibernateTemplate.loadAll(GuiltSafeguardMan.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#queryList(java.lang.String)
	 */
	public List<GuiltSafeguardMan> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<GuiltSafeguardMan> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<GuiltSafeguardMan> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<GuiltSafeguardMan> getObjectsByIdList(final String hql,final List<Integer> idList) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setParameterList("idList", idList);
				return query.list();
			}
			
		});
	}
	
	//根据hql语句、条件值、分页查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<GuiltSafeguardMan> pageList(final String queryString,final Object[] p,final Integer page,
			final Integer size) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(queryString);
				if(p!=null&&p.length>0){
					for (int i = 0; i < p.length; i++) {
						query.setParameter(i, p[i]);
					}
				}
				if(page!=null&&page>0&&size!=null&&size>0){
					query.setFirstResult((page-1)*size).setMaxResults(size);
				}
				return query.list();
			}
			
		});
	}
	
	
	
	//根据hql、条件值获得一个唯一值
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#getUniqueResult(java.lang.String, java.lang.Object[])
	 */
	public int getUniqueResult(final String queryString,final Object[] p) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		if(p!=null&&p.length>0){
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		Object obj=query.uniqueResult();
		return ((Long)obj).intValue();
			
	}
	
	//根据id查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#loadById(int)
	 */
	public GuiltSafeguardMan loadById(int id) {
		return (GuiltSafeguardMan) this.hibernateTemplate.load(GuiltSafeguardMan.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public GuiltSafeguardMan queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(GuiltSafeguardMan)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#checkGuiltSafeguardManExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkGuiltSafeguardManExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.IGuiltSafeguardManDao#updateGuiltSafeguardManByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateGuiltSafeguardManByhql(String queryString, Object[] p) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		if(p!=null&&p.length>0){
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		//返回受影响的行数
		return query.executeUpdate();
	}
	public GuiltSafeguardMan getGuiltSafeguardManById(Integer upguiltSafeguardManid) {
		// TODO Auto-generated method stub
		return (GuiltSafeguardMan) this.hibernateTemplate.get(GuiltSafeguardMan.class, upguiltSafeguardManid);
	}


}
