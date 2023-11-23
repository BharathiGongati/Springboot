package org.jsp.springDataJpa.service;

import java.util.List;

import java.util.Optional;

import org.jsp.springDataJpa.dao.ProductDao;
import org.jsp.springDataJpa.dao.UserDao;
import org.jsp.springDataJpa.dto.Product;
import org.jsp.springDataJpa.dto.ResponseStructure;
import org.jsp.springDataJpa.dto.User;
import org.jsp.springDataJpa.exception.IdNotFoundExcepton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User u = recUser.get();
			u.getProducts().add(product);

			product.setUser(recUser.get());
			userDao.updateUser(recUser.get());
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product added successfully");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);

		}
		throw new IdNotFoundExcepton();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			product.setUser(recUser.get());
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product updated successfully");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);

		}
		throw new IdNotFoundExcepton();
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("product found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}

		throw new IdNotFoundExcepton();

	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if (recProduct.isPresent()) {
			dao.deleteProduct(id);
			structure.setData("product deleted");
			structure.setMessage("Product Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundExcepton();
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByUserId(int user_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(dao.findProductsByUserId(user_id));
		structure.setMessage("products found for User Id");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	
	
	
	

}
