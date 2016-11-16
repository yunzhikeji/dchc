package com.yz.util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestMain {

	/**
	 * @param args
	 */
	private static String infoExtractionMsg;

	public static void main(String[] args) throws ParseException {
		
		String ids1 = ",,,,28    ,,,,,,3,30,18,20,53,39,36,48,21,59,35,16,37,56,33,15,62,19,67,45,";
					//   59,56,19,35,36,18,33,15,16,39,37,21,20,62,67,48,45,23,28,3,30,7,6,5,4,53,
		String ids2 = ", , , ,  , , , 1231231";
		
		System.out.println(handleIDs(ids1,ids2,1));

		
	}

	// 处理ids,operationType 1:增加 -1 删除
	public static String handleIDs(String objIDs, String opIDs,
			int operationType) {

		String newIDs = "";

		Set<String> objIDSet = handleSet(objIDs);

		Set<String> opIDSet = handleSet(opIDs);

		if (operationType == 1) {
			objIDSet.addAll(opIDSet);
		} else if (operationType == -1) {
			objIDSet.removeAll(opIDSet);
		}

		for (String id : objIDSet) {
			if(!id.equals(""))
			{
				newIDs = newIDs + id + ",";
			}
		}
		return newIDs;
	}

	public static Set<String> handleSet(String ids) {

		Set<String> idSet = new HashSet<String>();

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

	private static boolean isContainID(String ids, String id) {

		String[] idString = ids.split(",");

		List<String> list = Arrays.asList(idString);

		return list.contains(id);

	}

	private static void handleInfoExtractionMsg(String infoExtraction) {
		// TODO Auto-generated method stub
		if (infoExtraction != null && infoExtraction.length() > 0
				&& infoExtraction.contains(",")) {
			String[] infoExtractions = infoExtraction.split(",");
			infoExtractionMsg = "{'";
			for (int i = 0; i < infoExtractions.length; i++) {
				infoExtractionMsg = infoExtractionMsg + infoExtractions[i]
						+ "','";
			}
			infoExtractionMsg = (infoExtractionMsg.substring(0,
					infoExtractionMsg.length() - 2) + "}").trim();
		} else {
			infoExtractionMsg = "{}";
		}

	}

}
