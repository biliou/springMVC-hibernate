package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.core.responseFormat.Response;
import com.example.dao.impl.AccountDao2;
import com.example.service.AccountService;

@Controller
public class AccountController2 {
	@Autowired
	private AccountDao2 accountDao2;
	@Autowired
	private AccountService accountService;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController2.class.getName());

	@RequestMapping(value = "/account/test", method = RequestMethod.GET)
	@ResponseBody
	public Response testFunc() {
		//测试Log4j
		logger.debug("This is a debug message");  
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		
		return new Response().success("i'm AccountController2");
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	@ResponseBody
	public Response createAccount(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		boolean result = accountDao2.createAnAccount(name);
		Response r = null;
		if (result) {
			r = new Response().success();
		} else {
			r = new Response().failure();
		}
		return r;
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Response daleteAccount(@PathVariable("id") Integer id) {
		boolean result = accountDao2.deleteAnAccount(id);
		Response r = null;
		if (result) {
			r = new Response().success();
		} else {
			r = new Response().failure("删除失败");
		}
		return r;
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Response getAccount(@PathVariable("id") Integer id) {
		return accountService.getAccountInfoById(id);
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	@ResponseBody
	public Response getAccountsInfo() {
		return accountService.getAccountsInfo();
	}
	
	@RequestMapping(value = "/account/{id}/{name}", method = RequestMethod.PUT)
	@ResponseBody
	public Response setAccount(@PathVariable("id") Integer id,
			@PathVariable("name") String name) {
		return accountService.setAccountInfo(id, name);
	}

}
