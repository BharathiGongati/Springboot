package org.jsp.springDataJpa.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springDataJpa.dao.UserDao;
import org.jsp.springDataJpa.dto.Product;
import org.jsp.springDataJpa.dto.ResponseStructure;
import org.jsp.springDataJpa.dto.User;
import org.jsp.springDataJpa.exception.IdNotFoundExcepton;
import org.jsp.springDataJpa.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User u){
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(dao.saveUser(u));
		structure.setMessage("User saved with Id:"+u.getId());
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User u){
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(dao.updateUser(u));
		structure.setMessage("User updated");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
		
	}
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.findById(id);
		if(recUser.isPresent()) {
			structure.setMessage("User found");
			structure.setData(recUser.get());
		
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK); 
		}
		throw new IdNotFoundExcepton();
	}
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.findById(id);
		if (recUser.isPresent()) {
			structure.setMessage("user deleted");
			structure.setData("user found");
			structure.setStatuscode(HttpStatus.OK.value());
			dao.deleteUser(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);

		}
		structure.setMessage("user Not deleted");
		structure.setData("User not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}
		
		
		
	
	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
		ResponseStructure<List<User>> structure=new ResponseStructure<>();
		structure.setData(dao.findAll());
		structure.setMessage("List of all users");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.OK);
		
		
		
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone,String password){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.verifyUser(phone,password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user verified succesfully");
			structure.setStatuscode(HttpStatus.OK.value());
			return  new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
			
		}
		throw new InvalidCredentialsException();
		
	}
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email,String password){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.verifyUser(email,password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user verified succesfully");
			structure.setStatuscode(HttpStatus.OK.value());
			return  new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
			
		}
		throw new InvalidCredentialsException();
		
		
	}

}
	
	
	
	
	


