package com.lph.app.common;

import java.util.ArrayList;
import java.util.List;
/**
 * 这个类可以处理多达3个文件上传。
 * @author Administrator
 *
 */
public class MultiFileBucket {

    List<FileBucket> files = new ArrayList<FileBucket>();

    public MultiFileBucket() {
	files.add(new FileBucket());
	files.add(new FileBucket());
	files.add(new FileBucket());
    }

    public List<FileBucket> getFiles() {
	return files;
    }

    public void setFiles(List<FileBucket> files) {
	this.files = files;
    }
}
