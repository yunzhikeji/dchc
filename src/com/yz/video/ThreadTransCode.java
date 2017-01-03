package com.yz.video;

public class ThreadTransCode implements Runnable {
	// 原始文件
	private String videoPath;
	// 目标文件
	private String targetPath;

	public ThreadTransCode(String videoPath, String targetPath) {
		this.videoPath = videoPath;
		this.targetPath = targetPath;
	}

	public void run() {
		synchronized (this) {
			System.out.println("转码开始..............");
			ConvertVideo cv = new ConvertVideo(videoPath, targetPath);
			cv.process();
		}
	}
}