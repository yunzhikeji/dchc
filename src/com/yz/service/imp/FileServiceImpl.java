package com.yz.service.imp;

import com.yz.auth.AuthObject;
import com.yz.service.IFileService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by Administrator on 2017/4/18.
 */
@Component("fileService")
public class FileServiceImpl implements IFileService {

	// 环境变量
	@Resource(name = "authObject")
	private AuthObject authObject;

	// 文件上传
	public void upload(String fileName, String imageName, File file)
			throws Exception {
		File saved = new File(authObject.getFileRoot() + fileName, imageName);
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
			if (ous != null)
				ous.close();
			if (ins != null)
				ins.close();
		}
	}

}
