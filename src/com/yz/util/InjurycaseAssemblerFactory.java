package com.yz.util;

import com.yz.vo.AjaxMsgVO;

/**
 * Created by Administrator on 2017/4/21.
 */
public class InjurycaseAssemblerFactory extends  AssemblerFactory {

	AjaxMsgVO assembAjaxMsgVO() {
		return new AjaxMsgVO();
	}

}
