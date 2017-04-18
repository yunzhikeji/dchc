package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.auth.AuthObject;
import com.yz.model.Injurycase;
import com.yz.model.Judge;
import com.yz.model.Media;
import com.yz.service.IInjurycaseService;
import com.yz.service.IJudgeService;
import com.yz.service.IMediaService;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.DateTimeKit;
import com.yz.video.ThreadTransCode;
import com.yz.vo.AjaxMsgVO;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component("mediaAction")
@Scope("prototype")
public class MediaAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	// 分页显示
	private String[] arg = new String[2];
	private int page;
	private final int size = 10;
	private int pageCount;
	private int totalCount;

	// 条件
	private int id;
	private int inid; // 案件id
	private int jid; // 研判
	private int mid;// 媒体ID
	private int mtype;

	// service层对象
	@Resource
	private IMediaService mediaService;
	@Resource
	private IJudgeService judgeService;
	@Resource
	private IInjurycaseService injurycaseService;

	// 环境变量
	@Resource(name = "authObject")
	private AuthObject authObject;

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	// 单个表对象
	private Injurycase injurycase;
	private Judge judge;
	private Media media;

	// list表对象
	private List<Media> medias;
	private String capture;

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
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



		
		String imageName = "";
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String suffix = picture1FileName.substring(picture1FileName
					.indexOf("."));
			imageName = DateTimeKit.getDateRandom() + suffix;
			this.upload("/media", imageName, picture1);

			if (media.getMtype() == 1) {
				// 视频文件上传,
				String newFileName = DateTimeKit.getDateRandom() + ".mp4";

				// 因为扩展了前台方法,所以需要判断是否是视频格式,再进行转码
				// 调用ffmpeg执行转码工作

				if (suffix.equals("avi")||suffix.equals("mp4"))
				{
					taskExecutor.execute(new ThreadTransCode(authObject
							.getFileRoot()
							+ "/media" + "/" + imageName, authObject
							.getFileRoot()
							+ "/media" + "/" + newFileName));
				}
				media.setSrc("/media" + "/" + newFileName);
			} else {
				// 非视频文件上传
				media.setSrc("/media" + "/" + imageName);
			}

		}

		changeInjurycaseHandleState(media);

		mediaService.add(media);

		// 转换成功后将源文件删除
		// File photofile = new File(authObject.getFileRoot() + imageName);
		// photofile.delete();

		return "success_child";
	}

	public String addCanvasMedia() throws Exception {
		String serverPath = authObject.getFileRoot();
		BASE64Decoder decoder = new BASE64Decoder();

		changeInjurycaseHandleState(media);

		if (!media.getPicSrc().contains("data:image/png;base64")) {
			return "success_child1";
		}
		byte[] bs = decoder.decodeBuffer(media.getPicSrc().substring(
				"data:image/png;base64,".length())); // picSrc为Base64字符串
		InputStream is = new ByteArrayInputStream(bs);
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName = fileName + random.nextInt(10);
		}

		String realPath = serverPath + "\\media\\" + fileName + ".png";
		String relativePath = "/media/" + fileName + ".png";

		double ratio = 1.0;
		BufferedImage image = ImageIO.read(is);
		int newWidth = (int) (image.getWidth() * ratio);
		int newHeight = (int) (image.getHeight() * ratio);
		Image newimage = image.getScaledInstance(newWidth, newHeight,
				Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(newimage, 0, 0, null);
		g.dispose();
		ImageIO.write(tag, "png", new File(realPath));

		media.setSrc(relativePath);

		mediaService.add(media);

		return "success_child1";
	}


	// 上传视频
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(authObject.getFileRoot() + fileName, imageName);
		InputStream ins = null;
		OutputStream ous = null;
		try {
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(picture);
			ous = new FileOutputStream(saved);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				ous.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ous != null)
				ous.close();
			if (ins != null)
				ins.close();
		}
	}

	/**
	 * 9:负案在逃人员,10:维稳人员 上传前科照片
	 */
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;
	private File picture2;
	private String picture2ContentType;
	private String picture2FileName;
	private File picture3;
	private String picture3ContentType;
	private String picture3FileName;

	/**
	 * 删除
	 */
	public String deleteMedia() throws Exception {

		media = mediaService.loadById(mid);
		if (media.getSrc() != null
				&& !media.getSrc().replace(" ", "").equals("")) {
			File photofile = new File(authObject.getFileRoot() + media.getSrc());
			photofile.delete();
		}
		mediaService.delete(media);

		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));

		return null;
	}

	/**
	 * 加载
	 * 
	 * @return
	 */
	public String load() {
		media = mediaService.loadById(mid);
		
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/media", imageName, picture1);
			File photofile = new File(authObject.getFileRoot() + media.getSrc());
			photofile.delete();
			media.setSrc("/media" + "/" + imageName);
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



	//private 方法
	private void changeInjurycaseHandleState(Media media) {

		if (media.getInjurycase() != null) {
			injurycaseService.changeInjurycaseHandleState(media.getInjurycase().getId());
		}
	}



	// get、set-------------------------------------------
	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public int getSize() {
		return size;
	}

	public File getPicture1() {
		return picture1;
	}

	public void setPicture1(File picture1) {
		this.picture1 = picture1;
	}

	public String getPicture1ContentType() {
		return picture1ContentType;
	}

	public void setPicture1ContentType(String picture1ContentType) {
		this.picture1ContentType = picture1ContentType;
	}

	public String getPicture1FileName() {
		return picture1FileName;
	}

	public void setPicture1FileName(String picture1FileName) {
		this.picture1FileName = picture1FileName;
	}

	public File getPicture2() {
		return picture2;
	}

	public void setPicture2(File picture2) {
		this.picture2 = picture2;
	}

	public String getPicture2ContentType() {
		return picture2ContentType;
	}

	public void setPicture2ContentType(String picture2ContentType) {
		this.picture2ContentType = picture2ContentType;
	}

	public String getPicture2FileName() {
		return picture2FileName;
	}

	public void setPicture2FileName(String picture2FileName) {
		this.picture2FileName = picture2FileName;
	}

	public File getPicture3() {
		return picture3;
	}

	public void setPicture3(File picture3) {
		this.picture3 = picture3;
	}

	public String getPicture3ContentType() {
		return picture3ContentType;
	}

	public void setPicture3ContentType(String picture3ContentType) {
		this.picture3ContentType = picture3ContentType;
	}

	public String getPicture3FileName() {
		return picture3FileName;
	}

	public void setPicture3FileName(String picture3FileName) {
		this.picture3FileName = picture3FileName;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public IMediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(IMediaService mediaService) {
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

	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(IInjurycaseService injurycaseService) {
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

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public IJudgeService getJudgeService() {
		return judgeService;
	}

	public void setJudgeService(IJudgeService judgeService) {
		this.judgeService = judgeService;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

}
