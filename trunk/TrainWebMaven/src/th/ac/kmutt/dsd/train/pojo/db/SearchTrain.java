package th.ac.kmutt.dsd.train.pojo.db;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="searchTrain")
public class SearchTrain {
	
	private List<String> studentList;
	private List<String> imageList;
	public List<String> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	

}
