package th.ac.kmutt.dsd.train.api.model;

public class Usage {

	private Long quota;
	private String status;
	private String apiKey;

	public Long getQuota() {
		return quota;
	}

	public void setQuota(Object quota) {
		try {
			this.quota = (Long) quota;
		} catch (ClassCastException e) {
			try {
				this.quota = Long.parseLong((String) quota);
			} catch (Exception e2) {
				this.quota = new Long(0);
			}
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = (String) status;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(Object apiKey) {
		this.apiKey = (String) apiKey;
	}

	@Override
	public String toString() {
		return "Usage [apiKey=" + apiKey + ", quota=" + quota
				+ ", status=" + status + "]";
	}

}
