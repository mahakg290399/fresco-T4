package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

public class ProductController {

	/*
	This controller would be responsible for the ProductController endpoints
	Add the required annotations and create the endpoints
	*/
	
	@Autowired
	
	private ProductService productService;
	
	//to add the Product details using Product object
	
	@PostMapping("/product/add")
	
	private Object postProduct(@RequestBody Product product){
		Object pr = productService.postProduct(product);
		return pr;
		}
	
	
	//to get all the Product details
	
	@GetMapping("/product/get")
	
	private Object getProduct(){
		Object products = productService.getProduct();
		return products;
		}

	
	//to update the product with id as pathVariable and product as object in RequestBody	
	
	@PutMapping("/product/{id}")
	
	public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody Product product){
		ResponseEntity<Object> updatedpr = productService.updateProduct(id, product);
		return updatedpr;
	}
	
	
	// to delete the product by using id as PathVariable
	
	@DeleteMapping("/product/{id}")
	
	public ResponseEntity<Object> deleteProductById(@PathVariable int id){
		ResponseEntity<Object> res = productService.deleteProductById(id);
		return res;
	}
	
	
	
	//to get the product items by vendor
	
	@GetMapping("/product/vendor")
	
	public ResponseEntity<Object> getSimilarVendor(@RequestParam String value){
		ResponseEntity<Object> pr = productService.getSimilarVendor(value);
		return pr;
	}
}