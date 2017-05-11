package com.yz.util;

import java.util.Set;

/**
 * Created by Administrator on 2017/4/27.
 */
public class IdsOperator {


	private String objectIds;
	private String optionIds;
	private int operationType;


	public IdsOperator() {
	}


	public IdsOperator(String optionIds, int operationType) {
		this.optionIds = optionIds;
		this.operationType = operationType;
		new IdsOperator(objectIds, "", operationType);
	}


	public IdsOperator(String objectIds, String optionIds, int operationType) {
		this.objectIds = objectIds;
		this.optionIds = optionIds;
		this.operationType = operationType;
	}

	public String mergeIds() {

		Set<String> objIDSet = IdsUtil.stringToSetWithIds(objectIds);

		Set<String> opIDSet = IdsUtil.stringToSetWithIds(optionIds);

		if (operationType == 1) {
			objIDSet.addAll(opIDSet);
		} else if (operationType == -1) {
			objIDSet.removeAll(opIDSet);
		}

		return IdsUtil.setToStringWithIds(objIDSet);
	}


	//get set

	public String getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(String objectIds) {
		this.objectIds = objectIds;
	}

	public String getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(String optionIds) {
		this.optionIds = optionIds;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
}
