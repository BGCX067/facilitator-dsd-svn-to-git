package th.ac.kmutt.dsd.train.pojo.db;

import java.io.Serializable;

public class Server implements Serializable{
	
	private static final long serialVersionUID = 2089145405470604517L;
	
	Long id;
	String name;
	String url;
	double weight;
	int active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
		
}
