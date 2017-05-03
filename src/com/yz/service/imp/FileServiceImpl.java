package com.yz.service.imp;

import com.yz.auth.AuthObject;
import com.yz.model.Media;
import com.yz.service.FileService;
import com.yz.util.DateTimeKit;
import com.yz.video.ThreadTransCode;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/18.
 */
@Component("fileService")
public class FileServiceImpl implements FileService {

	// 环境变量
	@Resource(name = "authObject")
	private AuthObject authObject;

	@Resource
	private ThreadPoolTaskExecutor taskExecutor;


	public String upload(File[] file, String[] fileFileName, String[] fileContentType, String modelName) {

		String fileSrc = "";
		switch (checkFileNumber(file)) {
			case 0:
				break;
			case 1:
			default:
				for (int index = 0; index < file.length; index++) {

					File fileObj = file[index];
					String contentType = fileContentType[index];

					String suffix = fileFileName[index].substring(fileFileName[index]
							.indexOf("."));

					String fileRealName = DateTimeKit.getDateRandom() + suffix;

					this.saveFile(modelName, fileRealName, fileObj);

					if (isVideoFile(contentType)) {

						String videoName = DateTimeKit.getDateRandom() + ".mp4";
						taskExecutor.execute(new ThreadTransCode(authObject
								.getFileRoot()
								+ "/" + modelName + "/" + fileRealName, authObject
								.getFileRoot()
								+ "/" + modelName + "/" + videoName));

						fileSrc = fileSrc + "/" + modelName + "/" + videoName + ",";
					} else {
						fileSrc = fileSrc + "/" + modelName + "/" + fileRealName + ",";
					}
				}

				break;
		}


		return removeLastComma(fileSrc);
	}


	public void addVideoScreenshot(Media media) throws IOException {
		String fileName = DateTimeKit.getDateRandom();

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bs = new byte[0]; // picSrc为Base64字符串
		bs = decoder.decodeBuffer(media.getPicSrc().substring(
				"data:image/png;base64,".length()));

		InputStream is = new ByteArrayInputStream(bs);
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			fileName = fileName + random.nextInt(10);
		}
		String realPath = authObject.getFileRoot() + "\\media\\" + fileName + ".png";

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
	}


	public void deleteFileBySrc(String src) {
		if (src != null && !src.equals("")) {
			if (src.contains(",")) {
				String[] fileSrc = src.split(",");
				for (String singleSrc : fileSrc) {
					deleteFile(singleSrc);
				}
			} else {
				deleteFile(src);
			}
		}
	}


	private void deleteFile(String fileSrc) {
		File fileObj = new File(authObject.getFileRoot() + fileSrc);
		fileObj.delete();
	}


	// 文件上传
	private void saveFile(String modelName, String fileRealName, File file) {
		File saved = new File(authObject.getFileRoot() + "/" + modelName, fileRealName);
		InputStream ins = null;
		OutputStream ous = null;
		try {
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(file);
			ous = new FileOutputStream(saved);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				ous.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ous != null)
					ous.close();
				if (ins != null)
					ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	//private方法
	private int checkFileNumber(File[] file) {
		if (file == null) {
			return 0;
		} else {
			return file.length;
		}
	}

	private boolean isVideoFile(String MIME) {
		if (MIME.contains("video/")) {
			return true;
		}
		return false;
	}

	private String removeLastComma(String src) {

		if (src.equals(""))
			return src;

		String comma = src.substring(src.length() - 1, src.length());

		if (comma.equals(",")) {
			return src.substring(0, src.length() - 1);
		}

		return src;

	}


}
