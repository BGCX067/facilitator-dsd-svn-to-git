package th.ac.kmutt.dsd.train.pojo.db;

import java.util.Arrays;

public class TrainObject {
	
	private String id;
	private String group ;
	private String[] faceset;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String[] getFaceset() {
		return faceset;
	}
	public void setFaceset(String[] faceset) {
		this.faceset = faceset;
	}

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "TrainObject [id=" + id + ", group=" + group
				+ ", faceset=" + Arrays.toString(faceset) + "]";
	}

	
	
}
