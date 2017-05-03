package com.yz.dao.imp;

import com.yz.dao.AnalyzeManDao;
import com.yz.model.AnalyzeMan;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("analyzeManDao")
public class AnalyzeManDaoImp implements AnalyzeManDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(AnalyzeMan analyzeManman) {
		this.hibernateTemplate.save(analyzeManman);
	}

	public Integer savereturn(AnalyzeMan analyzeManman) {
		return (Integer) this.hibernateTemplate.save(analyzeManman);
	}

	public void delete(AnalyzeMan analyzeManman) {
		this.hibernateTemplate.delete(analyzeManman);
	}

	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}

	public void update(AnalyzeMan analyzeManman) {
		this.hibernateTemplate.update(analyzeManman);
	}

	public void updateByHql(final String hql, final String[] paramNames, final Object[] values) {
		this.hibernateTemplate.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], values[i]);
				}
				query.executeUpdate();
				return null;
			}

		});
	}

	public List<AnalyzeMan> getDisappearMen() {
		return this.hibernateTemplate.loadAll(AnalyzeMan.class);
	}

	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#queryList(java.lang.String)
	 */
	public List<AnalyzeMan> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}

	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<AnalyzeMan> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString, p);
	}

	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<AnalyzeMan> queryList(String queryString, String[] paramNames,
									  Object[] values) {
		List list = this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}


	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<AnalyzeMan> getObjectsByIdList(final String hql, final List<Integer> idList) {
		return this.hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameterList("idList", idList);
				return query.list();
			}

		});
	}

	//根据hql语句、条件值、分页查询某些记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<AnalyzeMan> pageList(final String queryString, final Object[] p, final Integer page,
									 final Integer size) {
		return this.hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);
				if (p != null && p.length > 0) {
					for (int i = 0; i < p.length; i++) {
						query.setParameter(i, p[i]);
					}
				}
				if (page != null && page > 0 && size != null && size > 0) {
					query.setFirstResult((page - 1) * size).setMaxResults(size);
				}
				return query.list();
			}

		});
	}


	//根据hql、条件值获得一个唯一值
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#getUniqueResult(java.lang.String, java.lang.Object[])
	 */
	public int getUniqueResult(final String queryString, final Object[] p) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString);
		if (p != null && p.length > 0) {
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		Object obj = query.uniqueResult();
		return ((Long) obj).intValue();

	}

	//根据id查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#loadById(int)
	 */
	public AnalyzeMan loadById(int id) {
		return (AnalyzeMan) this.hibernateTemplate.load(AnalyzeMan.class, id);
	}

	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.yz.dao.imp.PersonDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public AnalyzeMan queryByNamedParam(String queryString, String[] paramNames,
										Object[] values) {
		List list = this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size() > 0 ? (AnalyzeMan) list.get(0) : null;
	}



}
