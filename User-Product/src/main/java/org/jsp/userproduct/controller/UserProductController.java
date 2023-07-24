package org.jsp.userproduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userproduct.dao.ProductDao;
import org.jsp.userproduct.dao.UserDao;
import org.jsp.userproduct.dto.Product;
import org.jsp.userproduct.dto.User;

public class UserProductController {

	static UserDao uDao = new UserDao();
	static ProductDao pDao = new ProductDao();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1.Register the user");
		System.out.println("2.Verify the user by phone and password");
		System.out.println("3.Verify the user by email and password");
		System.out.println("4.update user");
		System.out.println("5.Add product");
		System.out.println("6.View products by user id");
		System.out.println("7.View products by category");
		System.out.println("enter your choice:");
		int choice = sc.nextInt();

		switch (choice) {
		case 1: {
			saveUser();
			break;
		}
		case 2: {
			verifyByphpwd();
			break;
		}
		case 3: {
			verifyByEmailpwd();
			break;
		}
		case 4: {
			updateUser();
			break;
		}
		case 5: {
			addProduct();
			break;
		}
		case 6: {
			viewProduct();
			break;
		}
		case 7: {
			viewProductByCategory();
			break;
		}

		}

	}

	public static void saveUser() {
		System.out.println("enter the name,phone,email and password");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String pwd = sc.next();
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(pwd);
		u = uDao.saveUser(u);
		System.out.println("user save with id:" + u.getId());
	}

	public static void verifyByphpwd() {
		System.out.println("enter the user phone number and password to verify");
		long ph = sc.nextLong();
		String pwd = sc.next();
		User u = uDao.verifyUser(ph, pwd);
		if (u != null) {
			System.out.println("The name is:" + u.getName());
			System.out.println("The email is:" + u.getEmail());
		} else
			System.out.println("no user available with entered details");
	}

	public static void verifyByEmailpwd() {
		System.out.println("enter the user email and password to verify");
		String email = sc.next();
		String pwd = sc.next();
		User u = uDao.verifyUser(email, pwd);
		if (u != null) {
			System.out.println("The name is:" + u.getName());
			System.out.println("The phone number is:" + u.getPhone());
		} else
			System.out.println("no user available with entered details");
	}

	public static void updateUser() {
		System.out.println("enter the id,name,phone,email and password");
		int id = sc.nextInt();
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String pwd = sc.next();
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(pwd);
		u = uDao.updateUser(u);
		System.out.println("user updated");

	}

	public static void addProduct() {
		System.out.println("enter the id for which you want to add product:");
		int u_id = sc.nextInt();
		System.out.println("Enter the name,description,brand,category and price");
		String name = sc.next();
		String desciption = sc.next();
		String brand = sc.next();
		String category = sc.next();
		double price = sc.nextDouble();

		Product p = new Product();
		p.setName(name);
		p.setDescription(desciption);
		p.setBrand(brand);
		p.setCategory(category);
		p.setPrice(price);
		p = pDao.saveProduct(p, u_id);
		if (p != null)
			System.out.println("product added with id:" + p.getId());
		else
			System.out.println("no user found to add the product");
	}

	public static void viewProduct() {
		System.out.println("enter the user id you want to view th product details:");
		int u_id = sc.nextInt();
		List<Product> ps = pDao.fetchProducts(u_id);
		if (ps.size() > 0) {
			for (Product pds : ps) {
				System.out.println("The name of the product is:" + pds.getName());
				System.out.println("the brand of the product is:" + pds.getBrand());
				System.out.println("the category of the product is:" + pds.getCategory());
				System.out.println("The price of the product is:" + pds.getPrice());
			}
		} else {
			System.out.println("no products found for givn user id");
		}
	}

	public static void viewProductByCategory() {
		System.out.println("enter the category you want to view th product details:");
		String category = sc.next();
		List<Product> ps = pDao.fetchProducts(category);
		if (ps.size() > 0) {
			for (Product pds : ps) {
				System.out.println("The name of the product is:" + pds.getName());
				System.out.println("the brand of the product is:" + pds.getBrand());
				System.out.println("the category of the product is:" + pds.getCategory());
				System.out.println("The price of the product is:" + pds.getPrice());
			}
		} else {
			System.out.println("no products found for given category");
		}

	}

}
