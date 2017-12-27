package com.example.service;

import com.example.core.responseFormat.Response;

public interface IAccountService {
	
	public Response getAccountInfoById(Integer id);
	
	public Response getAccountsInfo();
	
	public Response setAccountInfo(Integer id,String name);

}
