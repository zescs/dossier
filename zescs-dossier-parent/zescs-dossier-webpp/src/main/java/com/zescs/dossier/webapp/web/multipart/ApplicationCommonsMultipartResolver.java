package com.zescs.dossier.webapp.web.multipart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 * @ClassName: UtilCommonsMultipartResolver
 * @Description: TODO(文件上传解析器)
 * @author john
 * @date 2014年8月7日 上午11:09:25
 * 
 */
public class ApplicationCommonsMultipartResolver extends CommonsMultipartResolver {
	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		//获取编码
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		//注册文件上传监听器
		fileUpload.setProgressListener(new FileUploadProgressListener(request.getSession()));
		try {
			List<FileItem> fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadException e) {
			throw new MultipartException("文件转换失败", e);
		}
	}
}
