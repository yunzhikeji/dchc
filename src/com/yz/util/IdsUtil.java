package com.yz.util;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/10.
 */
public class IdsUtil {

	public static Set<String> stringToSetWithIds(String ids) {

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



	public static String setToStringWithIds(Set<String> idsSet) {

		String newIDs = "";

		for (String id : idsSet) {
			if (!id.equals("")) {
				newIDs = newIDs + id + ",";
			}
		}
		return newIDs;
	}

	public static String excludeSameId(String ids)
	{


		return 	setToStringWithIds(stringToSetWithIds(ids));
	}


	public static boolean isContainID(String ids, String id) {

		if(StringUtils.isNotBlank(ids))
		{
			String[] idString = ids.split(",");
			List<String> list = Arrays.asList(idString);
			return list.contains(id);
		}
		return false;
	}


	public static  void main(String[] args)
	{

		String ids = "7,9,, 7,9,";
		System.out.println(excludeSameId(ids));

	}

}
