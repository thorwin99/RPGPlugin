package com.mcoldlife.ReturnBundle;

public class ReturnBundle<T>{

	T returnVal;
	String message;
	int messageCode;
	
	public ReturnBundle(T returnValue){
		
		this.returnVal = returnValue;
		
	}
	
	public T getReturnValue(){
		
		return returnVal;
	}
	public ReturnBundle<T> setMessage(String msg){
		
		this.message = msg;
		return this;
		
	}
	public ReturnBundle<T> setMessageCode(int code){
		this.messageCode = code;
		return this;
	}
	public String getMessage(){
		return this.message;
	}
	public int getMessageCode(){
		return this.messageCode;
	}
}
