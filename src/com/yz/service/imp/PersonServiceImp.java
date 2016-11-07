package com.yz.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IPersonDao;
import com.yz.dao.ISocialManDao;
import com.yz.model.Person;
import com.yz.model.SocialMan;
import com.yz.model.Unit;
import com.yz.model.UserRole;
import com.yz.service.IPersonService;
import com.yz.service.IUnitService;
import com.yz.util.GenerateSqlFromExcel;
import com.yz.util.InfoType;
import com.yz.util.MyHandleUtil;

@Component("personService")
public class PersonServiceImp implements IPersonService {

	private IPersonDao personDao;
	private IUnitService unitService;
	private ISocialManDao socialManDao;

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#add(com.yz.model.Person)
	 */
	public void add(Person person) throws Exception {
		personDao.save(person);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#delete(com.yz.model.Person)
	 */
	public void delete(Person person) {
		personDao.delete(person);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		personDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#update(com.yz.model.Person)
	 */
	public void update(Person person) {
		personDao.update(person);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getPersons()
	 */
	public List<Person> getPersons() {
		return personDao.getPersons();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#loadById(int)
	 */
	public Person loadById(int id) {
		return personDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getPageCount(int,
	 *      java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, UserRole userRole,
			int type, int queryState, String starttime, String endtime) {
		String queryString = "select count(*) from Person mo where 1=1 ";
		Object[] p = null;

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}
		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);
		return personDao.getUniqueResult(queryString, p);
	}

	public Person getPersonByPersonname(String personname) {
		String queryString = "from Person mo where mo.name=:personname";
		String[] paramNames = new String[] { "personname" };
		Object[] values = new Object[] { personname };
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	public Person getPersonByIdcard(String idcard) {
		String queryString = "from Person mo where mo.idcard=:idcard";
		String[] paramNames = new String[] { "idcard" };
		Object[] values = new Object[] { idcard };
		return personDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IPersonServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<Person> queryList(int con, String convalue, UserRole userRole,
			int page, int size, int type, int queryState, String starttime,
			String endtime) {
		String queryString = "from Person mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);

		return personDao.pageList(queryString, p, page, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonById(java.lang.Integer)
	 */
	public Person getPersonById(Integer personid) {
		return personDao.getPersonById(personid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByTypeAndHandleState(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByTypeAndHandleState(int con,
			String convalue, String starttime, String endtime, int type,
			int handleState, UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Person mo where mo.type=" + type
				+ " and mo.handleState=" + handleState;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#savereturn(com.yz.model.Person)
	 */
	public int savereturn(Person person) {
		return personDao.savereturn(person);
	}

	public void saveSocialManWithExcel(File file, UserRole userRole,int type) {
		try {
			GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
			ArrayList<String[]> arrayList = generate
					.generateStationBugSql(file);

			for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				Person person = new Person();
				// 实例化PO对象，用PO对象进行保存
				SocialMan socialMan = new SocialMan();
				//"人员编号","姓名","性别","出生日期","QQ","微信号","身份证号","手机号码","户籍地详址","户籍地区划","DNA编号","其他身份信息","指纹编号","足迹编号","现住地区划","现住地详址","虚拟身份","银行卡信息","绰号","车牌号","发动机号","车架号","手机串号","人员备注信息","携带物品","携带工具","人员分类"
				person.setNumber(data[0].toString());
				person.setName(data[1].toString());
				if(data[2].toString().equals("男")){
					person.setSex(1);
				}else if(data[2].toString().equals("女")){
					person.setSex(2);
				}
				
				
				person.setBirthday(data[3].toString());
				person.setQq(data[4].toString());
				person.setWechat(data[5].toString());
				person.setIdcard(data[6].toString());
				person.setTelphone(data[7].toString());
				person.setRegisterAddress(data[8].toString());
				person.setRegisterAddressArea(data[9].toString());
				person.getGamblingCriminalMan().setDnanumber(data[10].toString());
				person.getGamblingCriminalMan().setOtherId(data[11].toString());
				person.getGamblingCriminalMan().setFingerPrintNumber(data[12].toString());
				person.getGamblingCriminalMan().setFootPrintNumber(data[13].toString());
				person.getGamblingCriminalMan().setCurrentAddressArea(data[14].toString());
				person.getGamblingCriminalMan().setCurrentAddress(data[15].toString());
				person.getGamblingCriminalMan().setVirtualId(data[16].toString());
				person.getGamblingCriminalMan().setBankCard(data[17].toString());
				person.getGamblingCriminalMan().setNickname(data[18].toString());
				person.getGamblingCriminalMan().setCarLicenseNumber(data[19].toString());
				person.getGamblingCriminalMan().setEngineNumber(data[20].toString());
				person.getGamblingCriminalMan().setCarFrameNumber(data[21].toString());
				person.getGamblingCriminalMan().setImei(data[22].toString());
				person.setRemark(data[23].toString());
				person.setCarrier(data[24].toString());
				person.setCarryTool(data[25].toString());
				if(data[26].toString().equals("赌博人员")){
					person.setType(1);
				}else if (data[26].toString().equals("涉恶人员")){
					person.setType(2);
				}else if (data[26].toString().equals("涉黄人员")){
					person.setType(3);
				}else if (data[26].toString().equals("食药环人员")){
					person.setType(4);
				}else if (data[26].toString().equals("涉毒人员")){
					person.setType(5);
				}else if (data[26].toString().equals("留置盘问人员")){
					person.setType(6);
				}else if (data[26].toString().equals("负案在逃人员")){
					person.setType(9);
				}else if (data[26].toString().equals("侵财人员")){
					person.setType(7);
				}else if (data[26].toString().equals("刑事传唤人员")){
					person.setType(8);
				}else if (data[26].toString().equals("失踪人员")){
					person.setType(11);
				}else if (data[26].toString().equals("侵财人员分析")){
					person.setType(12);
				}else if (data[26].toString().equals("技术比中人员")){
					person.setType(13);
				}else if (data[26].toString().equals("社会人员")){
					person.setType(15);
				}
				
				person.setSocialMan(socialMan);
				socialManDao.save(socialMan);
				int pid = personDao.savereturn(person);


				// 设置部门pids
				unitService.updateUnitByUserRoleAndInfoType(userRole.getUnit(),
						pid + "", InfoType.PERSON, 1);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//获取excel的标题数据集
	public ArrayList getExcelFieldNameList(int type) {

		String [] titles = {"人员编号","姓名","性别","出生日期","QQ","微信号","身份证号","手机号码","户籍地详址","户籍地区划","DNA编号","其他身份信息","指纹编号","足迹编号","现住地区划","现住地详址","虚拟身份","银行卡信息","绰号","车牌号","发动机号","车架号","手机串号","人员备注信息","携带物品","携带工具","人员分类"};
		ArrayList fieldName = new ArrayList();
		for (int i=0;i<titles.length;i++){
			String title = titles[i];
			fieldName.add(title);
		}
		return fieldName;
	}

	public ArrayList getExcelFieldDataList(int con, String convalue,
			UserRole userRole,int type, int queryState,
			String starttime, String endtime) {
		
		String queryString = "from Person mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.number like ? ";
			}
			if (con == 3) {
				queryString += "and mo.idcard like ? ";
			}
			if (con == 4) {
				queryString += "and mo.userRole.realname like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (type != 0) {
			queryString += " and mo.type =" + type;
		}
		if (queryState != 0) {
			queryString += " and mo.handleState =" + queryState;
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);
		
		List<Person> personList = personDao.queryList(queryString);
		
		String typevo ="";
		if(type==1){
			typevo = "赌博人员";
		}
		if(type==2){
			typevo = "涉恶人员";
		}

		if(type==3){
			typevo = "涉黄人员";
		}
		if(type==4){
			typevo = "食药环人员";
		}
		if(type==5){
			typevo = "涉毒人员";
		}
		if(type==6){
			typevo = "留置盘问人员";
		}
		if(type==9){
			typevo = "负案在逃人员";
		}
		if(type==7){
			typevo = "侵财人员";
		}
		if(type==8){
			typevo = "刑事传唤人员";
		}
		if(type==11){
			typevo = "失踪人员";
		}
		if(type==12){
			typevo = "侵财人员分析";
		}
		if(type==13){
			typevo = "技术比中人员";
		}
		if(type==15){
			typevo = "社会人员";
		}
		// 构造报表和导出数据
		ArrayList fieldData = new ArrayList();
		
		for(int i=0;personList !=null && i<personList.size();i++ ){
			ArrayList dataList = new ArrayList();
			Person person = personList.get(i);
			dataList.add(person.getNumber());
			dataList.add(person.getName());
			if(person.getSex()!=null){
				if(person.getSex()==1){
					dataList.add("男");
				}else
				if(person.getSex()==2){
					dataList.add("女");
				}else{
					dataList.add("");
				}
			}else {
				dataList.add("");
			}
			
			dataList.add(person.getBirthday());
			dataList.add(person.getQq());
			dataList.add(person.getWechat());
			dataList.add(person.getIdcard());
			dataList.add(person.getTelphone());
			dataList.add(person.getRegisterAddress());
			dataList.add(person.getRegisterAddressArea());
			if(person.getGamblingCriminalMan()!=null){
				dataList.add(person.getGamblingCriminalMan().getDnanumber());
				dataList.add(person.getGamblingCriminalMan().getOtherId());
				dataList.add(person.getGamblingCriminalMan().getFingerPrintNumber());
				dataList.add(person.getGamblingCriminalMan().getFootPrintNumber());
				dataList.add(person.getGamblingCriminalMan().getCurrentAddressArea());
				dataList.add(person.getGamblingCriminalMan().getCurrentAddress());
				dataList.add(person.getGamblingCriminalMan().getVirtualId());
				dataList.add(person.getGamblingCriminalMan().getBankCard());
				dataList.add(person.getGamblingCriminalMan().getNickname());
				dataList.add(person.getGamblingCriminalMan().getCarLicenseNumber());
				dataList.add(person.getGamblingCriminalMan().getEngineNumber());
				dataList.add(person.getGamblingCriminalMan().getCarFrameNumber());
				dataList.add(person.getGamblingCriminalMan().getImei());
			}
			if(person.getGamblingCriminalMan()==null){
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
				dataList.add("");
			}
			dataList.add(person.getRemark());
			dataList.add(person.getCarrier());
			dataList.add(person.getCarryTool());
			dataList.add(typevo);
			//"人员编号","姓名","性别","出生日期","QQ","微信号","身份证号","手机号码","户籍地详址","户籍地区划","DNA编号","其他身份信息","指纹编号","足迹编号","现住地区划","现住地详址","虚拟身份","银行卡信息","绰号","车牌号","发动机号","车架号","手机串号","人员备注信息","携带物品","携带工具","人员分类"
			fieldData.add(dataList);
		}

		return fieldData;
	}

	/*
	 * 条件查询人员列表
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByHandleState(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByHandleState(int con, String convalue,
			String starttime, String endtime, int handleState, UserRole userRole) {
		String queryString = "from Person mo where 1=1 and mo.handleState="
				+ handleState;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * 条件查询超期办理人员列表
	 * 
	 * @see com.yz.service.IPersonService#getOutOfTimePersonsByType(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getOutOfTimePersonsByType(int con, String convalue,
			String starttime, String endtime, int type, UserRole userRole) {
		String queryString = "from Person mo where  mo.type=" + type
				+ " and mo.isOutOfTime=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getOutOfTimePersons(int,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getOutOfTimePersons(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		String queryString = "from Person mo where  mo.isOutOfTime=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByType(int,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByType(int con, String convalue,
			String starttime, String endtime, int type, UserRole userRole) {
		String queryString = "from Person mo where  mo.type=" + type;
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getPersonsByUserRole(int,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      com.yz.model.UserRole)
	 */
	public List<Person> getPersonsByUserRole(int con, String convalue,
			String starttime, String endtime, UserRole userRole) {
		String queryString = "from Person mo where  1=1";
		return queryListBySql(con, convalue, starttime, endtime, userRole,
				queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.IPersonService#getNewPersonsByUserRole(com.yz.model.UserRole)
	 */
	public List<Person> getNewPersonsByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		String queryString = "from Person mo where  mo.isNew=1 and mo.handleState=1 ";

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.PERSON);

		return personDao.queryList(queryString);
	}

	public List<Person> getPersonsByOption(int con, String convalue,
			UserRole userRole) {
		// TODO Auto-generated method stub
		/*
		 * 0:'选择类型',1:'人员姓名',2:'人员编号',3:'身份证号',4:'录入人员姓名',5:'DNA',6:'指纹',7:'户籍地',8:'手机号'
		 * ,9:'微信号',10:'性别',11:'QQ号',12:'出生日期'
		 * ,13:'银行卡号',14:'车牌号',15:'车架号',16:'手机串号'
		 * ,17:'发动机号',18:'现住地详址',19:'失踪日期',20:'失踪地址',21:'报案联系人'
		 * ,22:'失踪经过',23:'衣着情况',24:'暂住地址',25:'工作单位',26:'备注信息',27:'携带物品',28:'携带工具'
		 */
		String queryString = "from Person mo where  1=1 ";

		if (con != 0 && convalue != null && !convalue.equals("")) {
			switch (con) {
			case 1:
				queryString += " and mo.name like  '%" + convalue + "%' ";
				break;
			case 2:
				queryString += " and mo.number like  '%" + convalue + "%' ";
				break;
			case 3:
				queryString += " and mo.idcard like  '%" + convalue + "%' ";
				break;
			case 4:
				queryString += " and mo.userRole.realname like  '%" + convalue
						+ "%' ";
				break;
			case 5:
				queryString += " and mo.gamblingCriminalMan.dnanumber like  '%"
						+ convalue + "%' ";
				break;
			case 6:
				queryString += " and mo.gamblingCriminalMan.fingerPrintNumber like  '%"
						+ convalue + "%' ";
				break;
			case 7:
				queryString += " and mo.registerAddress like  '%" + convalue
						+ "%' ";
				break;
			case 8:
				queryString += " and mo.telphone like  '%" + convalue + "%' ";
				break;
			case 9:
				queryString += " and mo.wechat like  '%" + convalue + "%' ";
				break;
			case 10:
				if (convalue.contains("男")) {
					queryString += " and mo.sex =1 ";
				} else if (convalue.contains("女")) {
					queryString += " and mo.sex =0 ";
				}
				break;
			case 11:
				queryString += " and mo.qq like  '%" + convalue + "%' ";
				break;
			case 12:
				queryString += " and mo.birthday like  '%" + convalue + "%' ";
				break;
			case 13:
				queryString += " and mo.bankCard like  '%" + convalue + "%' ";
				break;
			case 14:
				queryString += " and mo.carFrameNumber like  '%" + convalue
						+ "%' ";
				break;
			case 15:
				queryString += " and mo.gamblingCriminalMan.imei like  '%"
						+ convalue + "%' ";
				break;
			case 16:
				queryString += " and mo.gamblingCriminalMan.fingerPrintNumber like  '%"
						+ convalue + "%' ";
				break;
			case 17:
				queryString += " and mo.gamblingCriminalMan.engineNumber like  '%"
						+ convalue + "%' ";
				break;
			case 18:
				queryString += " and mo.gamblingCriminalMan.currentAddress like  '%"
						+ convalue + "%' ";
				break;
			case 19:
				queryString += " and mo.disappearMan.missingEndTime>='" + convalue + "'";
				queryString += " and mo.disappearMan.missingStartTime<='" + convalue + "'";
				break;
			case 20:
				queryString += " and mo.disappearMan.missingAddress like  '%"
						+ convalue + "%' ";
				break;
			case 21:
				queryString += " and mo.disappearMan.reportContactName like  '%"
						+ convalue + "%' ";
				break;

			case 22:
				queryString += " and mo.disappearMan.missingCause like  '%"
						+ convalue + "%' ";
				break;
			case 23:
				queryString += " and mo.disappearMan.dressSituation like  '%"
						+ convalue + "%' ";
				break;
			case 24:
				queryString += " and mo.guiltSafeguardMan.temporaryAddress like  '%"
						+ convalue + "%' ";
				break;
			case 25:
				queryString += " and mo.guiltSafeguardMan.workdUnit like  '%"
						+ convalue + "%' ";
				break;
			case 26:
				queryString += " and mo.remark like  '%" + convalue + "%' ";
				break;
			case 27:
				queryString += " and mo.carrier like  '%" + convalue + "%' ";
				break;
			case 28:
				queryString += " and mo.carryTool like  '%" + convalue + "%' ";
				break;
			default:
				break;
			}
		}

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return personDao.queryList(queryString);
	}

	/*
	 * 提取方法
	 */
	public List<Person> queryListBySql(int con, String convalue,
			String starttime, String endtime, UserRole userRole,
			String queryString) {

		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += " and mo.userRole.realname like  '%" + convalue
						+ "%' ";
			}
			if (con == 2) {
				queryString += " and mo.userRole.number like  '%" + convalue
						+ "%' ";
			}
			if (con == 3) {
				queryString += " and mo.userRole.unit.name like  '%" + convalue
						+ "%' ";
			}
		}
		if (starttime != null && !starttime.equals("")) {
			queryString += " and mo.joinDate>='" + starttime + "'";
		}
		if (endtime != null && !endtime.equals("")) {
			queryString += " and mo.joinDate<='" + endtime + "'";
		}

		queryString = MyHandleUtil.setSqlLimit(queryString, userRole,
				InfoType.CASE);

		return personDao.queryList(queryString);
	}

	public ISocialManDao getSocialManDao() {
		return socialManDao;
	}

	@Resource
	public void setSocialManDao(ISocialManDao socialManDao) {
		this.socialManDao = socialManDao;
	}

	public IUnitService getUnitService() {
		return unitService;
	}

	@Resource
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}

	@Resource
	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

}
