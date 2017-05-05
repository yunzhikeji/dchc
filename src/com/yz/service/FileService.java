package com.yz.service;

import com.yz.model.Media;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface FileService {

	String upload(File[] file,String[] fileFileName,String[] fileContentType,String modelName);

	String uploadOneFile(File file,String fileFileName,String fileContentType,String modelName);

	void addVideoScreenshot(Media media) throws IOException;

	void deleteFileBySrc(String src);
}
