package th.ac.kmutt.dsd.train.action;

import org.json.JSONArray;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import th.ac.kmutt.dsd.helper.CommunicationHelper;
import th.ac.kmutt.dsd.helper.StorageHelper;
import th.ac.kmutt.dsd.train.pojo.db.DataRespond;
import th.ac.kmutt.dsd.train.pojo.db.Position;
import th.ac.kmutt.dsd.train.pojo.db.ReturnObject;
import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;
import th.ac.kmutt.dsd.train.pojo.db.TrainObject;
import th.ac.kmutt.dsd.train.service.TrainHistoryService;

@ManagedBean
@SessionScoped
public class TrainAction extends BaseController {

	private static final int BUFFER_SIZE = 612400;

	private String studentId;
	private String message;
	private String type = "AAA";
	private int select;
	private Long size = new Long(0);
	private List<String> studentList;
	private List<String> imageList;
	private List<String> selectDelect;
	private boolean disInput = false;
	private boolean disInput2 = true;
	private ReturnObject returnObject = new ReturnObject();
	private DataRespond dataRespond = new DataRespond();
	private Map<String, HashSet<String>> mapdata = new HashMap<String, HashSet<String>>();
	private List<String> tempTrainUrl = new ArrayList<String>();
	private List<String> tempTrainLocal = new ArrayList<String>();
	HashMap<String, String> codeMap = new HashMap<String, String>();

	public int getSelect() {
		return select;
	}

	public List<String> getTempTrainUrl() {
		return tempTrainUrl;
	}

	public void setTempTrainUrl(List<String> tempTrainUrl) {
		this.tempTrainUrl = tempTrainUrl;
	}

	public List<String> getTempTrainLocal() {
		return tempTrainLocal;
	}

	public void setTempTrainLocal(List<String> tempTrainLocal) {
		this.tempTrainLocal = tempTrainLocal;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDisInput() {
		return disInput;
	}

	public void setDisInput(boolean disInput) {
		this.disInput = disInput;
	}

	public boolean isDisInput2() {
		return disInput2;
	}

	public void setDisInput2(boolean disInput2) {
		this.disInput2 = disInput2;
	}

	public List<String> getSelectDelect() {
		return selectDelect;
	}

	public void setSelectDelect(List<String> selectDelect) {
		this.selectDelect = selectDelect;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public List<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public ReturnObject getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(ReturnObject returnObject) {
		this.returnObject = returnObject;
	}

	public DataRespond getDataRespond() {
		return dataRespond;
	}

	public void setDataRespond(DataRespond dataRespond) {
		this.dataRespond = dataRespond;
	}

	private void clearData() {
		studentId = null;
		message = null;
		select = 1;
		size = new Long(0);
		studentList = null;
		imageList = null;
		selectDelect = null;
		disInput = false;
		disInput2 = true;
	}

	@ManagedProperty(value = "#{trainHistoryService}")
	private TrainHistoryService trainHistoryService;


	public void setTrainHistoryService(TrainHistoryService trainHistoryService) {
		this.trainHistoryService = (TrainHistoryService) applicationContext
				.getBean("trainHistoryService");
	}

	public void TypeChange(ValueChangeEvent event) throws Exception {
		if ((Integer) event.getNewValue() == 1) {
			System.out.println("Val:> 1");
			disInput = false;
			disInput2 = true;
		} else {
			studentList = trainHistoryService.getListStudentId();
			System.out.println("Val: " + event.getNewValue());
			disInput = true;
			disInput2 = false;
		}

	}

	public void uploadFileMultiple(FileUploadEvent event) throws Exception {

		HttpServletRequest reques = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		ResourceBundle resource = ResourceBundle.getBundle("applicationConfig");

		/* get path in local disk */
		String path = StorageHelper.getBasePath(StorageHelper.DIR);
		if (path == null || "null".equals(path)) {
			java.net.URL url = new URL(
					StorageHelper.getBasePath(StorageHelper.DIR));
			path = url.getPath();
		}

		/* create path of image in local disk */
		long ms = Calendar.getInstance().getTimeInMillis();
		String result = path + File.separator + ms + "_"
				+ event.getFile().getFileName();
		try {

			FileOutputStream fileOutputStream = new FileOutputStream(result);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bulk;
			InputStream inputStream = event.getFile().getInputstream();

			while (true) {
				bulk = inputStream.read(buffer);
				if (bulk < 0) {
					break;
				}
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}
			fileOutputStream.close();
			inputStream.close();

			/* 1.add url of photo */
			String URL_FILE = reques.getScheme() + "://"
					+ reques.getServerName() + ":" + reques.getServerPort()
					+ StorageHelper.getBaseUrl(StorageHelper.DIR) + "/" + ms
					+ "_" + event.getFile().getFileName();

			System.out.println("url photo >>" + URL_FILE);

			tempTrainLocal.add(result);
			tempTrainUrl.add(URL_FILE);

			size = size + 1;

			FacesMessage msg = new FacesMessage("Succesful", event.getFile()
					.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchImage() throws Exception {
		System.out.println("studentId>>" + studentId);
		if (studentId != null && !studentId.equals(""))
			imageList = trainHistoryService.getListImage(studentId);
		else
			imageList = new ArrayList<String>();
		System.out.println("imageList>>" + imageList + select);
	}

	public void selectDelete(long selectState) throws Exception {
		List<String> servers = CommunicationHelper
				.getALLServer(CommunicationHelper.TRAIN);
		System.out.println(selectState);
		String[] parts = this.studentId.split("-");
		String group = parts[0];
		if (selectState == 1) {
			ReturnObject returnObject = null;
			try {
				for (int i = 0; i < servers.size(); i++) {
					System.out.println("---for loop----"+i);
					if (CommunicationHelper.checkAvailableTrain(servers.get(i))) {
						System.out.println("This server availilable (check for delete id) >> "+ servers.get(i));
						 String OutPutJson = CommunicationHelper
								.getResultDelete(servers.get(i) +"/"+ group + "/"
										+ studentId);
						JSONObject outJson = new JSONObject(OutPutJson);
						System.out.println("Body >> "+OutPutJson);
						System.out.println("return code >> " + outJson.getInt("code"));
						if (outJson.getInt("code") == 1000) {
							boolean deleteIdIs = trainHistoryService.deleteStudent(studentId);
							studentList = trainHistoryService
									.getListStudentId();
							System.out.println("delete "+studentId+"is >>"+deleteIdIs);
						}
						message = outJson.getString("message");
					}
				}
				System.out.println("---end loop----");

			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else {
			TrainObject trainObject = new TrainObject();
			trainObject.setId(studentId);
			trainObject.setGroup(group);
			HttpServletRequest reques = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String deleteList[] = reques.getParameterValues("deleteList");
			List<TrainHistory> rs_list;
			rs_list = trainHistoryService.getListTrainHistory(this.studentId);
			List<String> image = new ArrayList<String>();
			for (int i = 0; i < rs_list.size(); i++) {
				image.add(rs_list.get(i).getFileImageURL());
			}

			for (int i = 0; i < deleteList.length; i++) {
				image.remove(image.indexOf(deleteList[i]));
			}
			System.out.println("set of exist image(list)" + image);
			String OutPutJson = null;
			if (image.size() > 0) {
				String face[] = new String[image.size()];
				for (int i = 0; i < image.size(); i++) {
					face[i] = image.get(i);
				}
				System.out.println("set of exist image(array)"+ Arrays.toString(face));
				trainObject.setFaceset(face);
				for (int i = 0; i < servers.size(); i++) {
					System.out.println("---for loop----"+i);
					if (CommunicationHelper.checkAvailableTrain(servers.get(i))) {
						System.out
								.println("This server availilable (check for delete some images) >> "
										+ servers.get(i));
						 OutPutJson = CommunicationHelper.getResultPut(servers
								.get(i), CommunicationHelper
								.getPraseDataToJSONForMat(trainObject));
						 calcuSucessPic(OutPutJson);
						 codeMap.put(String.valueOf(returnObject.getCode()),
									String.valueOf(returnObject.getCode()));
						System.out.println("Body >> "+OutPutJson);
						System.out.println("return code >> " + returnObject.getCode());
					}
				}

			} else {
				for (int i = 0; i < servers.size(); i++) {
					System.out.println("---for loop----"+i);
					if (CommunicationHelper.checkAvailableTrain(servers.get(i))) {
						System.out
								.println("This server availilable (check for delete id) >> "
										+ servers.get(i));
						OutPutJson = CommunicationHelper
								.getResultDelete(servers.get(i) + group + "/"
										+ studentId);
						JSONObject outJson = new JSONObject(OutPutJson);
						System.out.println("Body >> "+OutPutJson);
						System.out.println("return code >> " + outJson.getInt("code"));
						if (outJson.getInt("code") == 1000) {
							boolean deleteIdIs = trainHistoryService.deleteStudent(studentId);
							studentList = trainHistoryService
									.getListStudentId();
							System.out.println("delete "+studentId+"is >>"+deleteIdIs);
						}
						message = outJson.getString("message");
					}
				}
			}
			int code1 = 2000;
			if (codeMap.get("1001") != null) {
				code1 = 1001;
			}
			if (codeMap.get("1000") != null) {
				code1 = 1000;
			}
			System.out.println("-----end loop-------");
			System.out.println("result code >> "+code1);
			if (code1 == 1000) {
				System.out.println("result set image for delete >> "+deleteList.toString());
				for (int i = 0; i < deleteList.length; i++) {
					boolean deleteImageIs = trainHistoryService.deleteStudentImage(deleteList[i]);
					System.out.println("delete image is >> "+ deleteImageIs);
				}
				System.out.println("-----end delete loop-------");
			} else if (code1 == 1001) {
				System.out.println("result set image for update >> "+mapdata.toString());
				trainHistoryService.deleteStudent(studentId);
				Set<String> success = mapdata.get("success");
				for (String s : success) {
					int index = tempTrainUrl.indexOf(s);
					boolean updateIs = trainHistoryService.updateStudentImage(s);
					System.out.println("update is >> "+updateIs);
				}
				System.out.println("-----end update loop-------");
			}

		}
		imageList.clear();
		clearData();
		studentList = trainHistoryService.getListStudentId();
	}

	public String pageDelete() throws Exception {
		message = null;
		clearData();
		studentList = trainHistoryService.getListStudentId();
		System.out.println(studentList);
		studentId = "nn";
		return "delete";
	}

	public String pageCreate() throws Exception {
		message = null;
		clearData();
		studentList = trainHistoryService.getListStudentId();
		System.out.println(studentList);
		return "create";
	}

	public void calcuSucessPic(String OutPutJson) throws Exception {
		/* by mim */
		// Json convert to object
		JSONObject outJson = new JSONObject(OutPutJson);
		int code = outJson.getInt("code");
		returnObject.setMessage(outJson.getString("message"));
		returnObject.setCode(code);
		if (code == 1000) {

		} else if (code == 1001) {
			returnObject.setData(outJson.getJSONObject("data"));
			// data Json convert to array
			JSONArray temp = new JSONArray(returnObject.getData().getString(
					"error"));
			int length = temp.length();
			if (length > 0) {
				HashSet<String> set = new HashSet<String>();
				for (int i = 0; i < length; i++) {
					set.add(temp.getString(i));
				}
				mapdata.put("error", set);
			}
			temp = new JSONArray(returnObject.getData().getString("success"));

			length = temp.length();
			if (length > 0) {
				HashSet<String> set = new HashSet<String>();
				for (int i = 0; i < length; i++) {
					set.add(temp.getString(i));
				}
				mapdata.put("success", set);
			}
		} else if (code == 2000) {
			returnObject.setData(outJson.getJSONObject("data"));
			JSONArray temp = new JSONArray(returnObject.getData().getString(
					"error"));
			int length = temp.length();
			if (length > 0) {
				HashSet<String> set = new HashSet<String>();
				for (int i = 0; i < length; i++) {
					set.add(temp.getString(i));
				}
				mapdata.put("error", set);
			}

		}
	
		message = returnObject.getMessage();

	}

	public void trainImage() throws Exception {
		List<String> servers = CommunicationHelper
				.getALLServer(CommunicationHelper.TRAIN);
		HttpServletRequest reques = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		ResourceBundle resource = ResourceBundle.getBundle("applicationConfig");

		if (select == 1) {
			this.studentId = reques
					.getParameterValues("mainFrm:uploadForm:j_username")[0];
		}

		int chkStudent = 0;
		try {
			chkStudent = trainHistoryService.chkStudentId(this.studentId);
			String[] parts = this.studentId.split("-");
			String group = parts[0];

			/* STEP 9 set trainObject */

			TrainObject trainObject = new TrainObject();
			trainObject.setId(studentId);
			trainObject.setGroup(group);

			/* STEP 10 send Json to server */

			if (select == 1 && chkStudent <= 0) {
				System.out
						.println("2.ckeck exist id to database >> Id not exist in database");
				String face[] = new String[tempTrainUrl.size()];

				for (int i = 0; i < tempTrainUrl.size(); i++) {
					face[i] = tempTrainUrl.get(i);
				}

				trainObject.setFaceset(face);
				System.out.println(new JSONObject(trainObject).toString());
			
			
		
				for (int i = 0; i < servers.size(); i++) {
					System.out.println("---for loop----"+i);
					if (CommunicationHelper.checkAvailableTrain(servers.get(i))) {
						System.out
								.println("This server availilable (check for add) >> "
										+ servers.get(i));
						String OutPutJson1 = CommunicationHelper.getResultPost(
								servers.get(i), CommunicationHelper
										.getPraseDataToJSONForMat(trainObject));

						calcuSucessPic(OutPutJson1);
						codeMap.put(String.valueOf(returnObject.getCode()),
								String.valueOf(returnObject.getCode()));
						System.out.println("Body >> " + OutPutJson1);
						System.out.println("return code >> " + returnObject.getCode());
					}
				}

			} else if (select == 2) {
				System.out
						.println("2.ckeck exist id to database >>Id exist in database");
				for (int i = 0; i < imageList.size(); i++) {
					tempTrainUrl.add(imageList.get(i));
				}
				String face[] = new String[tempTrainUrl.size()];

				for (int i = 0; i < tempTrainUrl.size(); i++) {
					face[i] = tempTrainUrl.get(i);
				}
				trainObject.setFaceset(face);
				for (int i = 0; i < servers.size(); i++) {
					System.out.println("---for loop----"+i);
					if (CommunicationHelper.checkAvailableTrain(servers.get(i))) {
						System.out
								.println("This server availilable (check for add) >> "
										+ servers.get(i));
						String OutPutJson = CommunicationHelper.getResultPut(
								servers.get(i), CommunicationHelper
										.getPraseDataToJSONForMat(trainObject));
						calcuSucessPic(OutPutJson);
						codeMap.put(String.valueOf(returnObject.getCode()),
								String.valueOf(returnObject.getCode()));
						System.out.println("Body >> " + OutPutJson);
						System.out.println("return code >> " + returnObject.getCode());
					}
				}

			}
			int code1 = 2000;
			if (codeMap.get("1001") != null) {
				code1 = 1001;
			}
			if (codeMap.get("1000") != null) {
				code1 = 1000;
			}
			System.out.println("-----end loop-------");
			System.out.println("result code >> "+code1);
			if (code1 == 1000) {
				System.out.println("result set image for add >> "+tempTrainLocal.toString());
				for (int i = 0; i < tempTrainLocal.size(); i++) {
					trainHistoryService.addTrainData(this.studentId,
							tempTrainUrl.get(i), tempTrainLocal.get(i), group);
				}

			} else if (code1 == 1001) {
				System.out.println("result set image for add >> "+mapdata.toString());
				Set<String> success = mapdata.get("success");
				for (String s : success) {
					int index = tempTrainUrl.indexOf(s);
					trainHistoryService.addTrainData(this.studentId, s,
							tempTrainLocal.get(index), group);
				}
			} else {

			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (tempTrainLocal != null) {
			tempTrainLocal.clear();
		}

		if (tempTrainUrl != null) {
			tempTrainUrl.clear();
		}

		if (imageList != null) {
			imageList = null;
		}

		clearData();
	}
}
