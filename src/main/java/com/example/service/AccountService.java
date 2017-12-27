package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.core.responseFormat.Response;
import com.example.dao.impl.AccountDao2;
import com.example.model.hibernate.Account;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountDao2 accountDao2;

	/**
	 * 通过id获取用户信息
	 * 
	 * @param id
	 *            用户id
	 * @return {@link Response} 返回数据格式对象
	 */
	public Response getAccountInfoById(Integer id) {
		Account account = accountDao2.getAnAccount(id);

		if (account != null) {
			return new Response().success(account);
		} else {
			return new Response().failure();
		}
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @return {@link Response} 返回数据格式对象
	 */
	public Response getAccountsInfo() {
		List<Account> accounts = accountDao2.getAccountList();

		if (accounts != null) {
			return new Response().success(accounts);
		} else {
			return new Response().failure();
		}
	}

	/**
	 * 更新一个用户信息的名字
	 * 
	 * @return {@link Response} 返回数据格式对象
	 */
	public Response setAccountInfo(Integer id, String name) {
		Account account = accountDao2.updateAnAccount(id, name);

		if (account != null) {
			return new Response().success(account);
		} else {
			return new Response().failure();
		}
	}

}
