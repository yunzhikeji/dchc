package com.yz.service.imp;

import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.util.IdsOperator;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2017/4/21.
 */
public abstract class RoleServiceImp {


	protected String assembleLimitSqlByUserRole(String querySql, UserRole userRole) {

		if (userRole == null || userRole.getUnit() == null) {

			querySql += " and mo.id in (0) ";
			return setSqlIdsWithZero(querySql);
		}
		if (userRole.getUserLimit() == 2) {
			return querySql;
		}
		return assembleSqlByUserRole(querySql, userRole);

	}


	protected String changeObjectIds(UserRole userRole, IdsOperator idsOperator) {

		idsOperator.setObjectIds(getObjectIds(userRole));

		return idsOperator.mergeIds();

	}


	protected void changeUnitByUserRoleAndIdsOperator(UserRole userRole,IdsOperator idsOperator)
	{
		if(userRole!=null)
		{
			Unit unit = userRole.getUnit();
			if(unit!=null)
			{
				unit.setInids(changeObjectIds(userRole,idsOperator));
				changeUnitIds(unit);
			}
		}
	}

	protected abstract String getObjectIds(UserRole userRole);

	protected abstract void changeUnitIds(Unit unit);


	private String assembleSqlByUserRole(String querySql, UserRole userRole) {

		String objectIds = getObjectIds(userRole);
		if (StringUtils.isBlank(objectIds)) {
			setSqlIdsWithZero(querySql);
		}
		querySql = setSqlIds(querySql, objectIds);
		return querySql;

	}


	private String setSqlIds(String querySql, String ids) {
		return querySql += " and mo.id in (" + removeLastCommaInSql(ids) + ")";
	}

	private String removeLastCommaInSql(String ids) {
		String lastChar = "";
		do {
			lastChar = ids.substring(ids.length() - 1, ids.length());
			if (lastChar.equals(",")) {
				ids = ids.substring(0, ids.length() - 1);
			}
		} while (lastChar.equals(","));

		return ids;

	}


	private String setSqlIdsWithZero(String querySql) {
		return querySql += " and mo.id in (0) ";
	}
}
