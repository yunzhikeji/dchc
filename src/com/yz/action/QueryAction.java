package com.yz.action;

import com.yz.model.Injurycase;
import com.yz.model.Person;
import com.yz.model.UserRole;
import com.yz.service.InjurycaseService;
import com.yz.service.PersonService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("queryAction")
@Scope("prototype")
public class QueryAction extends BaseAction {


	private int personOption;
	private int injurycaseOption;
	@Resource
	private PersonService personService;
	@Resource
	private InjurycaseService injurycaseService;

	private List<Person> persons;
	private List<Injurycase> injurycases;


	public String query() throws Exception {

		if (isNotBlankString(convalue)) {
			convalue = decodeAndReplaceBlank(convalue);
		}
		if (isNotBlankString(starttime)) {
			starttime = decodeAndReplaceBlank(starttime);
		}
		if (isNotBlankString(endtime)) {
			endtime = decodeAndReplaceBlank(endtime);
		}

		if (personOption != 0) {
			persons = personService.getPersonsByOption(personOption, convalue, currentUserRole);
			if (persons != null && persons.size() > 0) {
				totalCount = totalCount + persons.size();
			}
		}
		if (injurycaseOption != 0) {
			injurycases = injurycaseService.getInjurycasesByOption(injurycaseOption, convalue, currentUserRole);
			if (injurycases != null && injurycases.size() > 0) {
				totalCount = totalCount + injurycases.size();
			}
		}


		return "manage";
	}

	public String goToQuery() {

		return "query";
	}

	// get、set-------------------------------------------
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

	public int getPersonOption() {
		return personOption;
	}

	public void setPersonOption(int personOption) {
		this.personOption = personOption;
	}

	public int getInjurycaseOption() {
		return injurycaseOption;
	}

	public void setInjurycaseOption(int injurycaseOption) {
		this.injurycaseOption = injurycaseOption;
	}


	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

}
