package com.yz.action;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Injurycase;
import com.yz.model.Media;
import com.yz.service.IInjurycaseService;
import com.yz.service.IMediaService;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;

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
	private int mid;// 媒体ID
	private int mtype;

	// service层对象

	private IMediaService mediaService;
	private IInjurycaseService injurycaseService;

	// 单个表对象
	private Injurycase injurycase;
	private Media media;

	// list表对象
	private List<Media> medias;
	private String capture;
	private String picSrc;


	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		injurycase = injurycaseService.loadById(inid);
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/media", imageName, picture1);
			media.setSrc("media" + "/" + imageName);
		}
		
		if (media.getInjurycase()!= null) {
			
			
			changeInjurycaseHandleState(media.getInjurycase().getId());
		}
		mediaService.add(media);
		return "success_child";
	}
	
	
	public String add1() throws Exception {
		String serverPath = ServletActionContext.getServletContext().getRealPath("/");
		BASE64Decoder decoder = new BASE64Decoder();
		if (media.getInjurycase()!= null) {
			
			
			changeInjurycaseHandleState(media.getInjurycase().getId());
		}
		
		byte[] bs = decoder.decodeBuffer(media.getPicSrc().substring("data:image/png;base64,".length()));  //picSrc为Base64字符串
		InputStream is = new ByteArrayInputStream(bs); 
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for(int i = 0; i < 3; i++){
			fileName = fileName + random.nextInt(10);
		}
		
		String realPath = serverPath + "\\media\\" + fileName + ".png";
		String relativePath = "media/" + fileName + ".png";
		
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
	
	
	/*
	 * 修改案件状态
	 */
	private void changeInjurycaseHandleState(int inid) {
		System.out.println(inid);
		System.out.println(injurycaseService);
		Injurycase injurycase = injurycaseService.loadById(inid);
		if (injurycase != null) {
			if (injurycase.getHandleState() == 1) {
				injurycase.setHandleState(2);
				injurycaseService.update(injurycase);
			}
		}
	}

	// 上传视频
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(ServletActionContext.getServletContext()
				.getRealPath(fileName), imageName);
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
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ media.getSrc());
			photofile.delete();
		}
		mediaService.delete(media);
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("删除成功.");
		JSONObject jsonObj = JSONObject.fromObject(msgVO);
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ media.getSrc());
			photofile.delete();
			media.setSrc("media" + "/" + imageName);
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

	@Resource
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

	@Resource
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
	
	

}
