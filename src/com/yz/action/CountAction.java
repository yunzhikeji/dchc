package com.yz.action;

import com.yz.model.Clue;
import com.yz.model.Injurycase;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.ClueService;
import com.yz.service.InjurycaseService;
import com.yz.service.PersonService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("countAction")
@Scope("prototype")
public class CountAction extends BaseAction {


	// service层对象
	@Resource
	private PersonService personService;
	@Resource
	private ClueService clueService;
	@Resource
	private InjurycaseService injurycaseService;

	// list表对象
	private List<Clue> clues;
	private List<Person> persons;
	private List<Injurycase> injurycases;

	private List<Integer[]> personCounts;
	private List<Integer[]> injurycaseCounts;
	private List<Integer[]> clueCounts;


	public String personCount() throws Exception {

		decodeParameters();

		personCounts = new ArrayList<Integer[]>();

		// 1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
		for (int handleState = 1; handleState <= 5; handleState++) {
			personCounts.add(personCountArray(con, convalue, starttime, endtime, handleState, currentUserRole));
		}

		return "personCount";
	}

	private int getCount(List list) {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	private Integer[] personCountArray(int con, String convalue, String starttime, String endtime, int i, UserRole userRole) {
		Integer[] personCount = new Integer[16];
		switch (i) {
			case 0:
				break;
			case 1:
			case 2:
			case 3:
				for (int j = 0; j < personCount.length - 1; j++) {

					personCount[j] = getCount(personService
							.getPersonsByTypeAndHandleState(con, convalue, starttime, endtime, j + 1, i, userRole));
				}

				personCount[personCount.length - 1] = getCount(personService.getPersonsByHandleState(con, convalue, starttime, endtime, i, userRole));

				break;
			case 4:
				for (int j = 0; j < personCount.length - 1; j++) {

					personCount[j] = getCount(personService.getOutOfTimePersonsByType(con, convalue, starttime, endtime,
							j + 1, userRole));
				}
				personCount[personCount.length - 1] = getCount(personService.getOutOfTimePersons(con, convalue, starttime, endtime, userRole));

				break;
			case 5:
				for (int j = 0; j < personCount.length - 1; j++) {

					personCount[j] = getCount(personService.getPersonsByType(con, convalue, starttime, endtime, j + 1,
							userRole));
				}
				personCount[personCount.length - 1] = getCount(personService.getPersonsByUserRole(con, convalue, starttime, endtime, userRole));
				break;
			default:
				break;
		}

		return personCount;
	}


	public String injurycaseCount() throws Exception {

		decodeParameters();

		injurycaseCounts = new ArrayList<Integer[]>();

		// 1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
		for (int i = 1; i <= 5; i++) {
			injurycaseCounts.add(injurycaseCountArray(con, convalue, starttime, endtime, i, currentUserRole));
		}
		return "injurycaseCount";
	}

	private Integer[] injurycaseCountArray(int con, String convalue,
										   String starttime, String endtime, int i, UserRole userRole) {
		Integer[] injurycaseCount = new Integer[5];
		switch (i) {
			case 0:
				break;
			case 1:
			case 2:
			case 3:
				for (int j = 0; j < injurycaseCount.length - 1; j++) {
					injurycaseCount[j] = getCount(injurycaseService.getInjurycaseByTypeAndHandleState(con, convalue, starttime, endtime, j + 1, i, userRole));
				}
				injurycaseCount[injurycaseCount.length - 1] = getCount(injurycaseService.getInjurycasesByHandleState(con, convalue, starttime, endtime, i, userRole));
				break;
			case 4:
				for (int j = 0; j < injurycaseCount.length - 1; j++) {
					injurycaseCount[j] = getCount(injurycaseService.getOutOfTimeInjurycasesByType(con, convalue, starttime, endtime, j + 1,
							userRole));
				}
				injurycaseCount[injurycaseCount.length - 1] = getCount(injurycaseService.getInOutOfTimejurycasesByUserRole(con, convalue, starttime, endtime, userRole));
				break;
			case 5:
				for (int j = 0; j < injurycaseCount.length - 1; j++) {
					injurycaseCount[j] = getCount(injurycaseService.getInjurycasesByType(con, convalue, starttime, endtime, j + 1,
							userRole));
				}
				injurycaseCount[injurycaseCount.length - 1] = getCount(injurycaseService.getInjurycasesByUserRole(con, convalue, starttime, endtime, userRole));
				break;
			default:
				break;
		}

		return injurycaseCount;
	}

	public String clueCount() throws Exception {

		decodeParameters();

		clueCounts = new ArrayList<Integer[]>();

		// 1:未办理 2：在办理 3：已完结 4:超期办理 5:合计
		for (int i = 1; i <= 5; i++) {
			clueCounts.add(clueCountArray(con, convalue, starttime, endtime, i, currentUserRole));
		}

		return "clueCount";
	}

	private Integer[] clueCountArray(int con, String convalue,
									 String starttime, String endtime, int i, UserRole userRole) {
		Integer[] clueCount = new Integer[3];
		switch (i) {
			case 0:
				break;
			case 1:
			case 2:
			case 3:
				clueCount[0] = getCount(clueService.getCluesByTypeAndHandleState(con, convalue, starttime, endtime, 1, i, userRole));
				clueCount[1] = getCount(clueService.getCluesByTypeAndHandleState(con, convalue, starttime, endtime, 2, i, userRole));
				clueCount[2] = clueCount[0] + clueCount[1];
				break;
			case 4:
				clueCount[0] = getCount(clueService.getOutOfTimeCluesByType(con, convalue, starttime, endtime, 1, userRole));
				clueCount[1] = getCount(clueService.getOutOfTimeCluesByType(con, convalue, starttime, endtime, 2, userRole));
				clueCount[2] = clueCount[0] + clueCount[1];
				break;
			case 5:
				clueCount[0] = getCount(clueService.getCluesByType(con, convalue, starttime, endtime, 1, userRole));
				clueCount[1] = getCount(clueService.getCluesByType(con, convalue, starttime, endtime, 2, userRole));
				clueCount[2] = clueCount[0] + clueCount[1];
				break;
			default:
				break;
		}

		return clueCount;
	}

	// get、set-------------------------------------------

	public ClueService getClueService() {
		return clueService;
	}

	public void setClueService(ClueService clueService) {
		this.clueService = clueService;
	}

	public List<Clue> getClues() {
		return clues;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public InjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(InjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Injurycase> getInjurycases() {
		return injurycases;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}

	public List<Integer[]> getPersonCounts() {
		return personCounts;
	}

	public void setPersonCounts(List<Integer[]> personCounts) {
		this.personCounts = personCounts;
	}

	public List<Integer[]> getInjurycaseCounts() {
		return injurycaseCounts;
	}

	public void setInjurycaseCounts(List<Integer[]> injurycaseCounts) {
		this.injurycaseCounts = injurycaseCounts;
	}

	public List<Integer[]> getClueCounts() {
		return clueCounts;
	}

	public void setClueCounts(List<Integer[]> clueCounts) {
		this.clueCounts = clueCounts;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

}
