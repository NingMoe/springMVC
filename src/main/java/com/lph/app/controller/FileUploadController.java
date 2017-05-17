package com.lph.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.lph.app.common.FileBucket;
import com.lph.app.common.FileValidator;
import com.lph.app.common.MultiFileBucket;
import com.lph.app.common.MultiFileValidator;

/**
 * 以上控制器是相当微不足道。它处理上传视图的GET和POST请求的文件。
 * 当文件从文件选择器，用户选择点击上传，
 * 我们只是创建具有相同的名称和字节的内容作为原始文件的新文件，从原始复制文件的字节数。
 * 为此，我们正在使用Spring FileCopyUtils工具类流从源复制到目的地。
 * 在这个例子中，我们指定的目的地是 D:/mytemp 文件夹，所有文件将存在这个文件夹中
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static String UPLOAD_LOCATION = "D:/mytemp/";

    @Autowired
    FileValidator fileValidator;

    @Autowired
    MultiFileValidator multiFileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
	binder.setValidator(fileValidator);
    }

    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
	binder.setValidator(multiFileValidator);
    }

    @RequestMapping(value = { "/", "/downloadWelcome" }, method = RequestMethod.GET)
    public String getHomePage(ModelMap model) {
	return "download_welcome";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
	FileBucket fileModel = new FileBucket();
	model.addAttribute("fileBucket", fileModel);
	return "singleFileUploader";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model)
	    throws IOException {

	if (result.hasErrors()) {
	    System.out.println("validation errors");
	    return "singleFileUploader";
	} else {
	    System.out.println("Fetching file");
	    MultipartFile multipartFile = fileBucket.getFile();

	    // Now do something with file...
	    FileCopyUtils.copy(fileBucket.getFile().getBytes(),
		    new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
	    String fileName = multipartFile.getOriginalFilename();
	    model.addAttribute("fileName", fileName);
	    return "success_upload";
	}
    }

    @RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
    public String getMultiUploadPage(ModelMap model) {
	MultiFileBucket filesModel = new MultiFileBucket();
	model.addAttribute("multiFileBucket", filesModel);
	return "multiFileUploader";
    }

    @RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
    public String multiFileUpload(@Valid MultiFileBucket multiFileBucket, BindingResult result, ModelMap model)
	    throws IOException {

	if (result.hasErrors()) {
	    System.out.println("validation errors in multi upload");
	    return "multiFileUploader";
	} else {
	    System.out.println("Fetching files");
	    List<String> fileNames = new ArrayList<String>();
	    // Now do something with file...
	    for (FileBucket bucket : multiFileBucket.getFiles()) {
		FileCopyUtils.copy(bucket.getFile().getBytes(),
			new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
		fileNames.add(bucket.getFile().getOriginalFilename());
	    }

	    model.addAttribute("fileNames", fileNames);
	    return "multiSuccess";
	}
    }

}