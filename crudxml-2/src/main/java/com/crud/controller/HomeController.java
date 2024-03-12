package com.crud.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Customer;

@Controller
public class HomeController {

	@PostMapping("/process")
	public String createCustomer(@ModelAttribute Customer customer,Model model) throws JAXBException, IOException {
		
		JAXBContext context=JAXBContext.newInstance(Customer.class);
		Marshaller marshaller=context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(customer, new FileWriter("C:\\Users\\Development\\Desktop\\xmldata\\customer.xml"));
		System.out.println("marshalling done");
		model.addAttribute("customer",customer);		
		return"Success";
		
	}
	
	@GetMapping("/getCustomer")
	public String readCustomer(Model model) throws JAXBException, FileNotFoundException {
		JAXBContext context=JAXBContext.newInstance(Customer.class);
		Unmarshaller unmarshaller=context.createUnmarshaller();
		Customer object = (Customer) unmarshaller.unmarshal(new FileReader("C:\\Users\\Development\\Desktop\\xmldata\\customer.xml"));
		System.out.println(object);
		model.addAttribute("customer",object);
		return "customer";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/updateCustomer")
	public String update() {
		return "updateForm";
	}
	
	@PostMapping("/update")
	public String updateCustomer(@ModelAttribute Customer customer,Model model) throws JAXBException, IOException {
		JAXBContext context=JAXBContext.newInstance(Customer.class);
		Unmarshaller unmarshaller=context.createUnmarshaller();
		Customer object = (Customer) unmarshaller.unmarshal(new FileReader("C:\\Users\\Development\\Desktop\\xmldata\\customer.xml"));
		System.out.println("before updation"+object);
		object.setCustomerId(customer.getCustomerId());
		object.setCustomerName(customer.getCustomerName());
		object.setCustomerAddress(customer.getCustomerAddress());
		
		Marshaller marshaller=context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(object, new FileWriter("C:\\Users\\Development\\Desktop\\xmldata\\customer.xml"));
		System.out.println("after updation"+object);
		model.addAttribute("customer",object);
		return "success";
	}
}
