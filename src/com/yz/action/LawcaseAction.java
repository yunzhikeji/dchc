package com.yz.action;

import com.yz.model.*;
import com.yz.service.ClueService;
import com.yz.service.InjurycaseService;
import com.yz.service.LawcaseService;
import com.yz.service.PersonService;
import com.yz.util.AjaxMsgUtil;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("lawcaseAction")
@Scope("prototype")
public class LawcaseAction extends BaseAction{

	// 条件
	private int id;
	private int pid;// 人员id
	private int inid; // 案件id
	private int cid;// 刑侦线索
	private int lawid;// 涉及案件

	// service层对象
	@Resource
	private PersonService personService;
	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private ClueService clueService;
	@Resource
	private LawcaseService lawcaseService;

	// 单个表对象
	private Person person;
	private Injurycase injurycase;
	private Clue clue;
	private Lawcase lawcase;

	/**
	 * 涉案情况模块
	 */
	public String goToAdd() {
		
		if(pid!=0)
		{
			person = personService.loadById(pid);
		}
		if(cid!=0)
		{
			clue = clueService.loadById(cid);
		}
		
		return "add";
	}

	public String add() throws Exception {
		
		if(lawcase.getPerson()!=null)
		{
			changePersonHandleState(lawcase.getPerson().getId());
		}
		if(lawcase.getClue()!=null)
		{
			changeClueHandleState(lawcase.getClue().getId());
		}
		lawcaseService.add(lawcase);
		return "success_child";
	}

	// 改变人员当前处理状态
	private void changePersonHandleState(int perid) {

		Person per = personService.loadById(perid);
		if (per != null) {
			if (per.getHandleState() == 1) {
				per.setHandleState(2);
				personService.update(per);
			}
		}

	}
	
	// 改变线索当前处理状态
	private void changeClueHandleState(int clid) {
		Clue clue = clueService.loadById(clid);
		if (clue != null) {
			if (clue.getHandleState() == 1) {
				clue.setHandleState(2);
				clueService.update(clue);
			}
		}

	}

	public String deleteLawcase() throws Exception {
		lawcase = lawcaseService.loadById(lawid);
		lawcaseService.delete(lawcase);
		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {
		lawcase = lawcaseService.loadById(lawid);
		return "load";
	}

	public String update() {
		lawcaseService.update(lawcase);
		return "success_child";
	}

	// get、set-------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LawcaseService getLawcaseService() {
		return lawcaseService;
	}

	public void setLawcaseService(LawcaseService lawcaseService) {
		this.lawcaseService = lawcaseService;
	}

	public Lawcase getLawcase() {
		return lawcase;
	}

	public void setLawcase(Lawcase lawcase) {
		this.lawcase = lawcase;
	}

	public int getLawid() {
		return lawid;
	}

	public void setLawid(int lawid) {
		this.lawid = lawid;
	}

	public InjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(InjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public int getInid() {
		return inid;
	}

	public void setInid(int inid) {
		this.inid = inid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public ClueService getClueService() {
		return clueService;
	}

	public void setClueService(ClueService clueService) {
		this.clueService = clueService;
	}

	public Injurycase getInjurycase() {
		return injurycase;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public Clue getClue() {
		return clue;
	}

	public void setClue(Clue clue) {
		this.clue = clue;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}
	
}
