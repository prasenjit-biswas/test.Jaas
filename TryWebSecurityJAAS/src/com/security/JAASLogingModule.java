package com.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class JAASLogingModule implements LoginModule{
	
	private String TEST_USER = "testUser";
	private String TEST_PASSWORD = "testUser";
	private boolean loginSuccess = false;
	private boolean commitSuccess = false;
	private CallbackHandler callbackHandler = null;
	@Override
	public boolean abort() throws LoginException {
		System.out.println(" I m in JAASLogingModule abort()");
		if(commitSuccess){
			return true;
		}else{
			logout();
		}
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		System.out.println(" I m in JAASLogingModule commit()");
		if(loginSuccess){
			commitSuccess = true;
			return true;
		}
		return false;
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		if(this.callbackHandler == null){
			System.out.println(" Problem in initializing callbackHandler ");
		}
		Callback[] callbaks = new Callback[2];
		callbaks[0] = new NameCallback("username");
		callbaks[1] = new PasswordCallback("password: ", false);
		try{
			this.callbackHandler.handle(callbaks);
			String userName = ((NameCallback)callbaks[0]).getName();
			String password = new String(((PasswordCallback)callbaks[1]).getPassword());
			System.out.println(" Given userName : "+userName+", password : "+password);
			if(TEST_USER.equals(userName) && TEST_PASSWORD.equals(password)){
				loginSuccess = true;
				return true;
			}else{
				loginSuccess = false;
				return false;
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		System.out.println(" I m in JAASLogingModule logout()");
		return true;
	}

}
