package com.yz.util;

import java.util.HashSet;
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


	public IdsOperator(String objectIds, int operationType) {
		this.objectIds = objectIds;
		this.operationType = operationType;
		new IdsOperator(objectIds,"",operationType);
	}

	public IdsOperator(String objectIds, String optionIds) {
		this.objectIds = objectIds;
		this.optionIds = optionIds;
		new IdsOperator(objectIds,optionIds,1);
	}

	public IdsOperator(String objectIds, String optionIds, int operationType) {
		this.objectIds = objectIds;
		this.optionIds = optionIds;
		this.operationType = operationType;
	}

	public String mergeIds() {

		Set<String> objIDSet = stringToSetWithIds(objectIds);

		Set<String> opIDSet = stringToSetWithIds(optionIds);

		if (operationType == 1) {
			objIDSet.addAll(opIDSet);
		} else if (operationType == -1) {
			objIDSet.removeAll(opIDSet);
		}

		return setToStringWithIds(objIDSet);
	}



	private Set<String> stringToSetWithIds(String ids) {

		Set<String> idSet = new HashSet<String>();

		if (ids == null || ids.equals("")) {
			return idSet;
		}

		ids = ids.replace(" ", "");

		if (ids.contains(",")) {

			String[] arrayIDs = ids.split(",");
			for (int i = 0; i < arrayIDs.length; i++) {
				if (!arrayIDs[i].equals("")) {
					idSet.add(arrayIDs[i]);
				}
			}

		} else {
			idSet.add(ids);
		}
		return idSet;

	}



	private String setToStringWithIds(Set<String> idsSet) {

		String newIDs = "";

		for (String id : idsSet) {
			if (!id.equals("")) {
				newIDs = newIDs + id + ",";
			}
		}
		return newIDs;
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
