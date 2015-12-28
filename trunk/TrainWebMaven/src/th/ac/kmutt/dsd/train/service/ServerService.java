package th.ac.kmutt.dsd.train.service;

import java.util.List;

import th.ac.kmutt.dsd.train.pojo.db.Server;

public interface ServerService {

	public void updateServerWeight(Double increase)throws Exception;
	public List<Server> getServerList()throws Exception;
	public Server getServer(int id)throws Exception;
	
}
