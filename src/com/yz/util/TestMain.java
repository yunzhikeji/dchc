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
		
	
		
		System.out.println();

	
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
