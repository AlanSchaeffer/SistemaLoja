package br.unisinos.desenvsoft3.controller.frontend.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
	private static final String HTML_FILE = "/index.html";
	
	@RequestMapping("/")
	public String siteHome() {
		return HTML_FILE;
	}
	
	@RequestMapping("/login")
	public String siteLogin() {
		return HTML_FILE;
	}
	
	@RequestMapping("/customer")
	public String siteIndex() {
		return HTML_FILE;
	}
	
	@RequestMapping("/admin")
	public String siteAdmin() {
		return HTML_FILE;
	}
	
	@RequestMapping("/admin/login")
	public String siteAdminLogin() {
		return HTML_FILE;
	}

	@RequestMapping("/admin/products")
	public String siteAdminProducts() {
		return HTML_FILE;
	}
	
	@RequestMapping("/admin/products/new")
	public String siteAdminNewProduct() {
		return HTML_FILE;
	}
	
	@RequestMapping("/admin/products/{id}")
	public String siteAdminEditProduct(@PathVariable("id") Integer id) {
		return HTML_FILE;
	}
	
	@RequestMapping("/users")
	public String siteUsers() {
		return HTML_FILE;
	}
}
