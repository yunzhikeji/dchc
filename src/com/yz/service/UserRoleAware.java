package com.yz.service;

import com.yz.model.UserRole;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface UserRoleAware {

	void setCurrentUserRole(UserRole userRole);
}
