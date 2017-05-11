package com.yz.action;

import com.yz.model.*;
import com.yz.service.ClueService;
import com.yz.service.InjurycaseService;
import com.yz.service.PersonService;
import com.yz.service.SuccessexampleService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.List;

@Component("successexampleAction")
@Scope("prototype")
public class SuccessexampleAction extends BaseAction {


	private int id;
	private int pid;// 人员id
	private int inid; // 案件id
	private int cid;// 刑侦线索
	@Resource
	private SuccessexampleService successexampleService;
	@Resource
	private PersonService personService;
	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private ClueService clueService;
	private Person person;
	private Injurycase injurycase;
	private Clue clue;
	private Successexample successexample;
	private List<Successexample> successexamples;


	/**
	 * 机构管理
	 */
	public String list() throws Exception {

		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
			convalue = convalue.replace(" ", "");
		}
		if (starttime != null && !starttime.equals("")) {
			starttime = URLDecoder.decode(starttime, "utf-8");
			starttime = starttime.replace(" ", "");
		}
		if (endtime != null && !endtime.equals("")) {
			endtime = URLDecoder.decode(endtime, "utf-8");
			endtime = endtime.replace(" ", "");
		}
		if (page < 1) {
			page = 1;
		}
		// 总记录数
		totalCount = successexampleService.getTotalCount(con, convalue, currentUserRole, starttime, endtime);
		// 总页数
		pageCount = successexampleService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		successexamples = successexampleService.queryList(con, convalue, currentUserRole, page, size, starttime, endtime);
		return "list";
	}


	/**
	 * 跳转到添加页面
	 *
	 * @return
	 */
	public String goToAdd() {

		if (pid != 0) {
			person = personService.loadById(pid);
		}
		if (inid != 0) {
			injurycase = injurycaseService.loadById(inid);
		}
		if (cid != 0) {
			clue = clueService.loadById(cid);
		}
		return "add";
	}

	/**
	 * 添加
	 *
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {

		successexampleService.add(successexample);

		arg[0] = "successexampleAction!list";
		arg[1] = "成功案例管理";
		return "success_child";
	}

	/**
	 * 删除一
	 *
	 * @return
	 */
	public String delete() {

		successexampleService.deleteById(id);
		arg[0] = "successexampleAction!list";
		arg[1] = "成功案例管理";
		return SUCCESS;
	}


	/**
	 * 删除二(批量删除)
	 *
	 * @return
	 */
	public String deleteSuccessexamples() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			successexampleService.deleteById(ids[i]);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}


	/**
	 * 跳转到修改页面
	 *
	 * @return
	 */
	public String load() throws Exception {

		successexample = successexampleService.loadById(id);// 当前修改成功案例的id
		return "load";
	}

	/**
	 * 修改
	 *
	 * @return
	 */
	public String update() throws Exception {

		successexampleService.update(successexample);
		arg[0] = "successexampleAction!list";
		arg[1] = "成功案例管理";
		return "success_child";
	}


	/**
	 * 查看信息
	 *
	 * @return
	 */
	public String view() {

		successexample = successexampleService.loadById(id);
		return "view";
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

	public SuccessexampleService getSuccessexampleService() {
		return successexampleService;
	}

	public void setSuccessexampleService(SuccessexampleService successexampleService) {
		this.successexampleService = successexampleService;
	}

	public Successexample getSuccessexample() {
		return successexample;
	}

	public void setSuccessexample(Successexample successexample) {
		this.successexample = successexample;
	}

	public List<Successexample> getSuccessexamples() {
		return successexamples;
	}

	public void setSuccessexamples(List<Successexample> successexamples) {
		this.successexamples = successexamples;
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


	public ClueService getClueService() {
		return clueService;
	}


	public void setClueService(ClueService clueService) {
		this.clueService = clueService;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
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
