package th.ac.kmutt.dsd.train.pojo.db;

import java.sql.Timestamp;

public class TrainHistory {

	private static final long serialVersionUID = 2089145405470604517L;
	
	Long historyID;
	String studenId;
	String fileImageURL;
	String locationDisk;
	String status;
	Timestamp craeteDate;
	String createBy;
	Timestamp updateDate;
	String updateBy;
	
	public Long getHistoryID() {
		return historyID;
	}
	public void setHistoryID(Long historyID) {
		this.historyID = historyID;
	}
	public String getStudenId() {
		return studenId;
	}
	public void setStudenId(String studenId) {
		this.studenId = studenId;
	}
	public String getFileImageURL() {
		return fileImageURL;
	}
	public void setFileImageURL(String fileImageURL) {
		this.fileImageURL = fileImageURL;
	}
	public String getLocationDisk() {
		return locationDisk;
	}
	public void setLocationDisk(String locationDisk) {
		this.locationDisk = locationDisk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCraeteDate() {
		return craeteDate;
	}
	public void setCraeteDate(Timestamp craeteDate) {
		this.craeteDate = craeteDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
