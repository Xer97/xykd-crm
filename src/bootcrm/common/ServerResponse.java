package bootcrm.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author xer97
 * @date 2019/3/31 12:27 可复用的服务响应对象
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9183508140619754957L;

	private int status;
	private String msg;
	private T data;

	private ServerResponse(int status) {
		this.status = status;
	}

	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> ServerResponse<T> createByError() {
		return new ServerResponse<>(ResponseCode.ERROR.getCode());
	}
//
//	public static <T> ServerResponse<T> createByError(T data) {
//		return new ServerResponse<>(ResponseCode.ERROR.getCode(), data);
//	}
//
//	public static <T> ServerResponse<T> createByError(String msg, T data) {
//		return new ServerResponse<>(ResponseCode.ERROR.getCode(), msg, data);
//	}

	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		return new ServerResponse<>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new ServerResponse<>(errorCode, errorMessage);
	}
}
