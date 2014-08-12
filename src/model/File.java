package model;

public class File {
	int ID;
	java.io.File file;
	String name;
	String suffix;
	long size;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public java.io.File getFile() {
		return file;
	}
	public void setFile(java.io.File file) {
		this.file = file;
	}
	
}
