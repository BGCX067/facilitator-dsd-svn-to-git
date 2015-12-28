package th.ac.kmutt.dsd.train.api.model;

public class Match {
	
	private String id;
	private Integer similarity;
	
	public Match() {
		super();
	}
	
	public Match(String id, Integer similarity) {
		this.id = id;
		this.similarity = similarity;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getSimilarity() {
		return similarity;
	}
	
	public void setSimilarity(Integer similarity) {
		this.similarity = similarity;
	}

}
