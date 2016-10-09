package com.jack.rms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jack.rms.common.core.BaseController;

@Controller
public class UploadController extends BaseController {

	@RequestMapping("toUpload")
	public String toUpload() {
		return "upload";
	}

	@RequestMapping("/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("name====>" + request.getParameter("name"));
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		// 设置临时文件存储位置
		String base = "d:/uploadFiles";
		File file = new File(base);
		if (!file.exists()) {
			file.mkdirs();
		}
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setFileSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");

		String fileNames = "";
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mrequest.getFileMap();
			Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
			// 用hasNext() 判断是否有值，用next()方法把元素取出。
			while (it.hasNext()) {
				Map.Entry<String, MultipartFile> entry = it.next();
				MultipartFile mFile = entry.getValue();
				if (mFile.getSize() != 0 && !"".equals(mFile.getName())) {
					String originalFilename = mFile.getOriginalFilename();
					String tFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
					
					File dest = new File(base + File.separator + tFileName);
					mFile.transferTo(dest);
					fileNames += tFileName;
				}
			}
			
			response.getWriter().write(fileNames);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
