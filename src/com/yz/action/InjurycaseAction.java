package com.yz.action;

import com.yz.model.Injurycase;
import com.yz.model.Media;
import com.yz.model.Otherperson;
import com.yz.model.UserRole;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.InjurycaseExcel;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.InjurycaseVO;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Component("injurycaseAction")
@Scope("prototype")
public class InjurycaseAction extends BaseAction{


	// 条件
	private int id;
	private int itype;// 类型
	private String idcard;


	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private OtherpersonService otherpersonService;
	@Resource
	private MediaService mediaService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private PersonService personService;
	@Resource
	private FileService fileService;

	// 单个表对象
	private Injurycase injurycase;


	// list表对象
	private List<Injurycase> injurycases;
	private List<InjurycaseVO> injurycaseVOList;
	private List<Otherperson> tars;// 同案人员
	// 同系列案件
	private List<Media> mediaVideos;
	private List<Media> mediaImages;


	// 串并案系列名称
	private String series;


	/**
	 * 案件管理
	 */
	public String list() throws Exception {

		if (isNotBlankString(convalue)) {
			convalue = decodeAndReplaceBlank(convalue);
		}
		if (isNotBlankString(starttime)) {
			starttime = decodeAndReplaceBlank(starttime);
		}
		if (isNotBlankString(endtime)) {
			endtime = decodeAndReplaceBlank(endtime);
		}
		
		if (page < 1) {
			page = 1;
		}
		pageTileName = selectTileName(itype);

		// 总记录数
		totalCount = injurycaseService.getTotalCount(con, convalue,
				currentUserRole, itype, queryState, starttime, endtime);
		// 总页数
		pageCount = injurycaseService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		injurycases = injurycaseService.queryList(con, convalue,
				currentUserRole, page, size, itype, queryState, starttime,
				endtime);
		return "list";
	}

	// 选择页面名称
	private String selectTileName(int type) {
		String pageName = "案件信息";
		switch (type) {
			case 0:
				pageName = "案件";
				break;
			case 1:
				pageName = "刑事案件";
				break;
			case 2:
				pageName = "重伤案件";
				break;
			case 3:
				pageName = "团伙系列案件";
				break;
			case 4:
				pageName = "行政案件";
				break;
			default:
				pageName = "串并案";
				break;
		}
		return pageName;
	}

	/**
	 * 跳转到添加页面
	 *
	 * @return
	 */
	public String goToAdd() {

		return "add";
	}

	/**
	 * 添加
	 *
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {

		injurycase.setUserRole(currentUserRole);// 设置录入人员
		injurycase.setJoinDate(DateTimeKit.getLocalDate());// 设置录入时间
		injurycase.setHandleState(1);// 初始化处理状态
		injurycase.setIsOutOfTime(0);
		injurycase.setIsNew(1);
		injurycase.setImageCase(fileService.upload(file, fileFileName,
				fileContentType, "case"));
		injurycaseService.add(injurycase);

		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
		return "success_child";
	}

	/**
	 * 删除一
	 *
	 * @return
	 */
	public String delete() throws Exception {

		injurycase = injurycaseService.loadById(id);
		fileService.deleteFileBySrc(injurycase.getImageCase());
		injurycaseService.delete(injurycase);
		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 *
	 * @return
	 */
	public String deleteInjurycases() throws Exception {
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			injurycase = injurycaseService.loadById(ids[i]);
			fileService.deleteFileBySrc(injurycase.getImageCase());
			injurycaseService.delete(injurycase);
		}

		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	public String getPersonByIdcard() throws Exception {
		AjaxMsgUtil.outputJSONOToAjax(response, JSONObject.fromObject(
				personService.getPersonByIdcard(idcard)).toString());
		return null;
	}

	/**
	 * 跳转到修改页面
	 *
	 * @return
	 */
	public String load() throws Exception {

		pageTileName = selectTileName(itype);
		tars = otherpersonService.getInjurycaseOtherpersonByOtype(2, id);// 同案人
		mediaVideos = mediaService.loadInjurycaseByTypeAndPid(1, id);// 视频文件
		mediaImages = mediaService.loadInjurycaseByTypeAndPid(0, id);// 图像文件
		injurycase = injurycaseService.queryInjurycaseById(id);// 当前修改案件的id
		return "load";

	}

	/**
	 * 修改
	 *
	 * @return
	 */
	public String update() throws Exception {


		if(isFilesNotNull())
		{
			fileService.deleteFileBySrc(injurycase.getImageCase());
			injurycase.setImageCase(fileService.upload(file, fileFileName,
					fileContentType, "case"));
		}

		if(injurycase.getHandleState()!=3)
		{
			injurycase.setHandleState(2);
		}


		if (injurycase.getEndSituation() != null
				&& injurycase.getEndSituation() != "") {
			injurycase.setHandleState(3);// 完结
		}

		if (injurycase.getUserRole() == null) {
			injurycase.setUserRole(currentUserRole);
		}

		injurycaseService.update(injurycase);

		arg[0] = "injurycaseAction!list?itype=" + injurycase.getItype();
		arg[1] = "案件管理";
		return "success_child";
	}

	/**
	 * 查看信息
	 *
	 * @return
	 */
	public String view() {

		injurycase = injurycaseService.loadById(id);
		return "view";
	}

	/***************************************************************************
	 * 串并案
	 *
	 * @throws UnsupportedEncodingException
	 */
	public String listcba() throws UnsupportedEncodingException {
		// 登陆验证

		if (isNotBlankString(convalue)) {
			convalue = decodeAndReplaceBlank(convalue);
		}
		if (isNotBlankString(starttime)) {
			starttime = decodeAndReplaceBlank(starttime);
		}
		if (isNotBlankString(endtime)) {
			endtime = decodeAndReplaceBlank(endtime);
		}

		int size = 9;

		// 总记录数
		totalCount = injurycaseService.getTotalCount(con, convalue,
				currentUserRole, queryState, starttime, endtime);
		// 总页数
		pageCount = injurycaseService.getPageCount(totalCount, 9);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		injurycases = injurycaseService.queryList(con, convalue,
				currentUserRole, page, size, queryState, starttime, endtime);

		if (injurycases != null && injurycases.size() > 0) {
			injurycaseVOList = new ArrayList<InjurycaseVO>();
			for (int i = 0; i < injurycases.size(); i++) {
				InjurycaseVO injurycaseVO = new InjurycaseVO();
				int vNumber = 0;
				int iNumber = 0;
				injurycaseVO.setId(injurycases.get(i).getId());
				injurycaseVO.setCaseName(injurycases.get(i).getCaseName());
				injurycaseVO
						.setHandleState(injurycases.get(i).getHandleState());
				injurycaseVO.setImageCase(injurycases.get(i).getImageCase());
				injurycaseVO.setIsRelated(injurycases.get(i).getIsRelated());
				injurycaseVO.setSeries(injurycases.get(i).getSeries());
				injurycaseVO.setStartTime(injurycases.get(i).getStartTime());
				injurycaseVO.setUserRole(injurycases.get(i).getUserRole());
				for (Media media : injurycases.get(i).getMedias()) {
					if (media.getMtype() == 1) {
						vNumber++;
					} else if (media.getMtype() == 0) {
						iNumber++;
					}
				}
				injurycaseVO.setVideoNumber(vNumber);
				injurycaseVO.setImageNumher(iNumber);
				injurycaseVOList.add(injurycaseVO);
			}
		}
		return "listcba";
	}

	/**
	 * 跳转到修改页面
	 *
	 * @return
	 */
	public String loadcba() throws Exception {

		injurycase = injurycaseService.queryInjurycaseById(id);// 当前修改案件的id
		mediaVideos = mediaService.loadInjurycaseByTypeAndPid(1, id);// 视频文件
		mediaImages = mediaService.loadInjurycaseByTypeAndPid(0, id);// 图像文件
		return "loadcba";

	}


	/**
	 * 案件串并操作
	 *
	 * @return
	 */
	public String handleInjurycaseSeries() throws Exception {

		if (series != null && !series.equals("")) {
			series = URLDecoder.decode(series, "utf-8");
		}
		// ---------------------------修改其他案件串并属性
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			injurycase = injurycaseService.loadById(ids[i]);
			if (injurycase.getIsRelated() == null
					|| injurycase.getIsRelated() != 1) {
				injurycase.setIsRelated(1);
			}
			if (injurycase.getSeries() == null
					|| !injurycase.getSeries().equals(series)) {
				injurycase.setSeries(series);
			}
			injurycaseService.update(injurycase);
		}

		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("串并案操作成功."));
		return null;
	}

	/**
	 * 获取新增案件的事项信息
	 *
	 * @return
	 */
	public String getNewInjurycases() {

		currentUserRole = (UserRole) session.get("currentUserRole");

		List<Injurycase> injurycases = injurycaseService
				.getNewInjurycaseByUserRole(currentUserRole);

		List<AjaxMsgVO> ajaxMsgVOList = new ArrayList<AjaxMsgVO>();

		if (injurycases != null && injurycases.size() > 0) {
			for (Injurycase injurycase : injurycases) {
				AjaxMsgVO caseVO = new AjaxMsgVO();
				caseVO.setId(injurycase.getId());
				caseVO.setName(injurycase.getCaseName());
				caseVO.setJoinDate(injurycase.getJoinDate());
				caseVO.setType(injurycase.getItype());
				switch (injurycase.getItype()) {
					case 0:
						caseVO.setTypeName("未知案件");
						break;
					case 1:
						caseVO.setTypeName("刑事案件");
						break;
					case 2:
						caseVO.setTypeName("重伤案件");
						break;
					case 3:
						caseVO.setTypeName("团伙系列案件");
					case 4:
						caseVO.setTypeName("行政案件");
						break;
					default:
						break;
				}
				ajaxMsgVOList.add(caseVO);
			}
		}

		AjaxMsgUtil.outputJSONArrayToAjax(response, ajaxMsgVOList);
		return null;
	}

	/***************************************************************************
	 * 导出excel表格
	 *
	 * @throws UnsupportedEncodingException
	 */
	public String export(){

		if (isNotBlankString(convalue)) {
			convalue = decodeAndReplaceBlank(convalue);
		}
		if (isNotBlankString(starttime)) {
			starttime = decodeAndReplaceBlank(starttime);
		}
		if (isNotBlankString(endtime)) {
			endtime = decodeAndReplaceBlank(endtime);
		}
		
		// / 所有当前页记录对象
		List<Injurycase> injurycasesExcel = injurycaseService.queryList(con, convalue, currentUserRole,
				itype, queryState, starttime, endtime);

		if (injurycasesExcel.size() > 0) {
			// 导出数据-------------------------------------
			String filename = "output\\" + DateTimeKit.getDateRandom()
					+ "_injurycases.xls";
			String savePath = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ filename;
			System.out.println("[--------------------savePath=" + savePath);
			System.out.println(injurycasesExcel.size());

			boolean isexport = new InjurycaseExcel().exportExcel(savePath,
					injurycasesExcel);
			if (isexport) {
				request.put("errorInfo", "导出数据成功,下载点<a href='" + filename
						+ "'>-这里-</a>");
				return "opexcel";
			} else {
				request.put("errorInfo", "导出数据失败！");
				return "opexcel";
			}
		} else {
			request.put("errorInfo", "查询失败，未导出数据！");
			return "opexcel";
		}
	}

	public String importExcel() {

		return "import";
	}


	public String importData(){

		if (!isFilesExitExcel()) {
			request.put("errorInfo", "导入文件格式不正确");
			return "opexcel";

		}
		boolean isImportSuccess = injurycaseService.saveInjurycaseWithExcel(file[0], currentUserRole,
				itype);
		if (isImportSuccess) {
			request.put("errorInfo", "导入数据成功");
			return "opexcel";
		} else {
			request.put("errorInfo", "导入数据失败");
			return "opexcel";
		}
	}

	// get、set-------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public OtherpersonService getOtherpersonService() {
		return otherpersonService;
	}

	public void setOtherpersonService(OtherpersonService otherpersonService) {
		this.otherpersonService = otherpersonService;
	}

	public List<Otherperson> getTars() {
		return tars;
	}

	public void setTars(List<Otherperson> tars) {
		this.tars = tars;
	}


	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}

	public InjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(InjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public Injurycase getInjurycase() {
		return injurycase;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public List<Injurycase> getInjurycases() {
		return injurycases;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}


	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public List<Media> getMediaVideos() {
		return mediaVideos;
	}

	public void setMediaVideos(List<Media> mediaVideos) {
		this.mediaVideos = mediaVideos;
	}

	public List<Media> getMediaImages() {
		return mediaImages;
	}

	public void setMediaImages(List<Media> mediaImages) {
		this.mediaImages = mediaImages;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

	public List<InjurycaseVO> getInjurycaseVOList() {
		return injurycaseVOList;
	}

	public void setInjurycaseVOList(List<InjurycaseVO> injurycaseVOList) {
		this.injurycaseVOList = injurycaseVOList;
	}

}
