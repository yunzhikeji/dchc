package com.yz.dao;

import java.util.List;

import com.yz.model.DisappearMan;

public interface DisappearManDao {

	//保存一条记录
	void save(DisappearMan disappearman);

	//保存一条记录
	Integer savereturn(DisappearMan disappearman);

	//删除一条记录
	void delete(DisappearMan disappearman);

	//根据ID删除一条记录
	void deleteById(int id);

	//修改一条记录
	void update(DisappearMan disappearman);

	//根据hql语句、条件、条件值修改某些记录
	void updateByHql(final String hql,
			final String[] paramNames, final Object[] values);

	//获得所有记录
	List<DisappearMan> getDisappearMen();

	//根据hql语句来查询所有记录
	List<DisappearMan> queryList(String queryString);

	//根据hql、条件值查询某些记录
	List<DisappearMan> getObjectsByCondition(String queryString,
			Object[] p);

	//根据hql语句、条件、条件值查询某些记录
	List<DisappearMan> queryList(String queryString,
			String[] paramNames, Object[] values);

	//根据hql、id列表查询某些记录
	List<DisappearMan> getObjectsByIdList(final String hql,
			final List<Integer> idList);

	//根据hql语句、条件值、分页查询某些记录
	List<DisappearMan> pageList(final String queryString,
			final Object[] p, final Integer page, final Integer size);

	//根据hql、条件值获得一个唯一值
	int getUniqueResult(final String queryString,
			final Object[] p);

	//根据id查询一条记录
	DisappearMan loadById(int id);

	//根据hql语句、条件、值来查询一条记录
	DisappearMan queryByNamedParam(String queryString,
			String[] paramNames, Object[] values);

	//根据hql语句、条件值来查询是否存在该记录
	boolean checkDisappearManExistsWithName(String queryString,
			Object[] p);

	//根据hql批量修改
	int updatePersonByhql(String queryString, Object[] p);

	DisappearMan getDisappearManById(Integer updisappearmanid);

}