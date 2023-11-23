package org.jsp.springbootdemo.controller;

import org.jsp.springbootdemo.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping(value="/home")
	public String home() {
		return "welcome to the world of spring boot";
	}
	@GetMapping(value="/getsum")
	public String getsum(@RequestParam int n1,@RequestParam int n2) {
		return "sum:"+(n1+n2);
	}
	@GetMapping(value="/getsub")
	public String getsub(@RequestParam int n1,@RequestParam int n2) {
		return "sub:"+(n1-n2);
	}
	@GetMapping(value="/getmul")
	public String getmul(@RequestParam int n1,@RequestParam int n2) {
		return "mul:"+(n1*n2);
	}
	@GetMapping(value="/getdiv")
	public String getdiv(@RequestParam int n1,@RequestParam int n2) {
		return "div:"+(n1/n2);
	}
	@GetMapping("/get/{n}")
	public String getTable(@PathVariable int n) {
		String table="";
		for(int i=1;i<=10;i++) {
			table=table+n+"*"+i+"="+(n*i)+"\n";
		}
		return table;
		
	}
	@GetMapping("/users")
	public User getuser() {
		return new User(1,"ABC",8877,"ABC@123");
	}
	@PostMapping("/users")
	public String printUser(@RequestBody User u) {
		System.out.println("Id:"+u.getId());
		System.out.println("Name"+u.getName());
		System.out.println("Phone Number:"+u.getPhone());
		System.out.println("password:"+u.getPassword());
		return "user details printed";
		
	}
	

}
