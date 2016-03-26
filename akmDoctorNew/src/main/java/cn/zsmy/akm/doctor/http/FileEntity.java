package cn.zsmy.akm.doctor.http;

/**
 * @author Stay
 * @version create time：Mar 11, 2014 9:06:17 PM
 */
public class FileEntity {

	private String fileName;
	private String fileType;
	private String filePath;

	public FileEntity(String fileName, String fileType, String filePath) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
	}

	public FileEntity(){}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
