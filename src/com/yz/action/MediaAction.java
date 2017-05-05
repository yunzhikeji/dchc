package com.yz.action;

import com.yz.model.Injurycase;
import com.yz.model.Judge;
import com.yz.model.Media;
import com.yz.model.UserRole;
import com.yz.service.FileService;
import com.yz.service.InjurycaseService;
import com.yz.service.JudgeService;
import com.yz.service.MediaService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Component("mediaAction")
@Scope("prototype")
public class MediaAction extends BaseAction {

	// 条件
	private int inid; // 案件id
	private int jid; // 研判
	private int mid;// 媒体ID
	private int mtype;

	// service层对象
	@Resource
	private MediaService mediaService;
	@Resource
	private JudgeService judgeService;
	@Resource
	private InjurycaseService injurycaseService;
	@Resource
	private FileService fileService;

	// 单个表对象
	private Injurycase injurycase;
	private Judge judge;
	private Media media;

	// list表对象
	private List<Media> medias;


	public String goToAdd() {

		if (mtype > 1) {
			judge = judgeService.loadById(jid);
		} else {
			injurycase = injurycaseService.loadById(inid);
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

		media.setSrc(fileService.upload(file, fileFileName, fileContentType,
				"media"));
		mediaService.add(media);
		changeInjurycaseHandleState(media);
		return "success_child";
	}

	public String addVideoScreenshot() throws IOException {

		if (!media.getPicSrc().contains("data:image/png;base64")) {
			return "success_child1";
		}
		fileService.addVideoScreenshot(media);
		media.setSrc("/media/" + DateTimeKit.getDateRandom() + ".png");
		mediaService.add(media);
		changeInjurycaseHandleState(media);
		return "success_child1";
	}

	public String deleteMedia() throws Exception {

		media = mediaService.loadById(mid);
		fileService.deleteFileBySrc(media.getSrc());
		mediaService.delete(media);
		AjaxMsgUtil.outputJSONObjectToAjax(response, new AjaxMsgVO("删除成功."));
		return null;
	}

	public String load() {
		media = mediaService.loadById(mid);
		return "load";
	}

	public String update() throws Exception {

		if(isFilesNotNull())
		{
			fileService.deleteFileBySrc(media.getSrc());
			media.setSrc(fileService.upload(file, fileFileName, fileContentType,
					"media"));
		}
		mediaService.update(media);
		return "success_child";
	}

	/**
	 * 查看视频信息
	 */
	public String view() {
		media = mediaService.loadById(mid);
		return "view";
	}
	
	
	
	public String goToViewMedias() {
		medias = mediaService.loadInjurycaseByTypeAndPid(mtype, inid);
		return "views";
	}
	
	

	private void changeInjurycaseHandleState(Media media) {
		if (media.getInjurycase() != null) {
			injurycaseService.changeInjurycaseHandleState(media.getInjurycase()
					.getId());
		}
	}

	// get、set-------------------------------------------
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
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

	public Injurycase getInjurycase() {
		return injurycase;
	}

	public void setInjurycase(Injurycase injurycase) {
		this.injurycase = injurycase;
	}

	public int getMtype() {
		return mtype;
	}

	public void setMtype(int mtype) {
		this.mtype = mtype;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public JudgeService getJudgeService() {
		return judgeService;
	}

	public void setJudgeService(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

}
