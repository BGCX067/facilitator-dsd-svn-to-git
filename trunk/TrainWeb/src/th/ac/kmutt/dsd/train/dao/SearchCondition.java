package th.ac.kmutt.dsd.train.dao;


import java.io.Serializable;

public class SearchCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	String fieldName;

	String operation;

	Object fieldValue;

	public SearchCondition() {

	}

	public SearchCondition(String fieldName, Object fieldValue) {
		setFieldName(fieldName);
		setFieldValue(fieldValue);
	}

	public SearchCondition(String fieldName, Object fieldValue, String operation) {
		setFieldName(fieldName);
		setFieldValue(fieldValue);
		setOperation(operation);
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

}
