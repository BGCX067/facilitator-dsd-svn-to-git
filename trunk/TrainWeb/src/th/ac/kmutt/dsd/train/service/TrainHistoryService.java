package th.ac.kmutt.dsd.train.service;

import org.primefaces.event.FileUploadEvent;
  

public interface TrainHistoryService {

	  public void trainSingleFile(FileUploadEvent event) throws Exception;
	  public void trainMultipleFile(FileUploadEvent event) throws Exception;
	  	
}
