package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@GetMapping(value="")
	public String index() {
		return "login"; //不指向外界，因为添加了@RestController注解
	}
	
	@GetMapping(value="index.do")
	public ModelAndView index2() {
		return new ModelAndView("login"); //指向外界
	}
	
	@GetMapping(value="login.do") 
	public Object login(String name, String password) {
		System.out.println("传入参数name=" + name + ", password=" + password);
		if (StringUtils.isEmpty(name)) {
			throw new RuntimeException("name is null");
		}
		if (StringUtils.isEmpty(password)) {
			throw new RuntimeException("password is null");
		}
		User user = userDao.find(name, password);
		if (user == null) {
			throw new RuntimeException("name or password is error"); 
		} 
		return user;
	}
}
