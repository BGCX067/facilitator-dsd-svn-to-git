package th.ac.kmutt.dsd.train.pojo.db;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class TrainHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7630023969623389225L;
	
	Long historyID;
	String groupId;
	String studenId;
	String fileImageURL;
	List<String> fileImageURLList;
	String locationDisk;
	String status;
	String isDelete;
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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
