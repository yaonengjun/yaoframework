package org.oursight.framework.yao.base.exception;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;
	protected String errorCode;

	public CommonException() {
		errorCode = "DefaultError";
		init();
	}

	public CommonException(String s) {
		super(s);
		errorCode = "DefaultError";
		init();
	}

	public CommonException(String s, Throwable throwable) {
		super(s, throwable);
		errorCode = "efaultError";
		init();
	}

	public CommonException(Throwable throwable) {
		super(throwable);
		errorCode = "DefaultError";
		init();
	}

	protected void init() {
		setErrorCode("DefaultError");
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String s) {
		errorCode = s;
	}

}
