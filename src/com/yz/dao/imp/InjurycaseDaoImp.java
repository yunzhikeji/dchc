/**
 * 
 */
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


import com.yz.dao.InjurycaseDao;
import com.yz.model.Injurycase;

/**
 * @author jiang
 *
 */
@Component("injurycaseDao")
public class InjurycaseDaoImp implements InjurycaseDao {
	
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#checkInjurycaseExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkInjurycaseExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#delete(com.yz.model.Injurycase)
	 */
	public void delete(Injurycase injurycase) {
		this.hibernateTemplate.delete(injurycase);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#getInjurycaseById(java.lang.Integer)
	 */
	public Injurycase getInjurycaseById(Integer upinjurycaseid) {
		return (Injurycase) this.hibernateTemplate.get(Injurycase.class, upinjurycaseid);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#getInjurycases()
	 */
	public List<Injurycase> getInjurycases() {
		return this.hibernateTemplate.loadAll(Injurycase.class);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Injurycase> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Injurycase> getObjectsByIdList(final String hql,final List<Integer> idList) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setParameterList("idList", idList);
				return query.list();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#getUniqueResult(java.lang.String, java.lang.Object[])
	 */
	public int getUniqueResult(final String queryString, final Object[] p) {
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

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#loadById(int)
	 */
	public Injurycase loadById(int id) {
		return (Injurycase) this.hibernateTemplate.load(Injurycase.class, id);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Injurycase> pageList(final String queryString, final Object[] p,
			final Integer page, final Integer size) {
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

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Injurycase queryByNamedParam(String queryString,
			String[] paramNames, Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Injurycase)list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#queryList(java.lang.String)
	 */
	public List<Injurycase> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Injurycase> queryList(String queryString, String[] paramNames,
			Object[] values) {
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#save(com.yz.model.Injurycase)
	 */
	public void save(Injurycase injurycase) {
		this.hibernateTemplate.save(injurycase);

	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#savereturn(com.yz.model.Injurycase)
	 */
	public Integer savereturn(Injurycase injurycase) {
		return (Integer) this.hibernateTemplate.save(injurycase);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#update(com.yz.model.Injurycase)
	 */
	public void update(Injurycase injurycase) {
		this.hibernateTemplate.update(injurycase);
	}

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public void updateByHql(final String hql, final String[] paramNames, final Object[] values) {
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

	/* (non-Javadoc)
	 * @see com.yz.dao.InjurycaseDao#updateInjurycaseByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateInjurycaseByhql(String queryString, Object[] p) {
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
}
