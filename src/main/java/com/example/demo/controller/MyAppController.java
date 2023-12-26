package com.example.demo.controller;


import com.example.demo.Database;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerServiceImpl;
import com.example.demo.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyAppController {
    @Autowired
    CustomerServiceImpl personService;

    @PostMapping("/add")
    public Response addPerson(@RequestBody Customer p) {
        System.out.println("in add person"+p);
        boolean res = personService.addPerson(p);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("com.example.demo.Person Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("com.example.demo.Person created successfully");
        return response;
    }
    @GetMapping("/habal")
    public String habal() {

        return "habal";
    }

    @DeleteMapping("/delete/{id}")
    public Response deletePerson(@PathVariable("id") int id) {
        System.out.println("in delete with id:"+id);
        boolean res = personService.deletePerson(id);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("com.example.demo.Person Doesn't Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("com.example.demo.Person deleted successfully");
        return response;
    }
    @GetMapping("/get/{id}")
	public Customer getPerson(@PathVariable("id") int id) {
            System.out.println("in get with id:"+id);
		return personService.getPerson(id);
	}
        @GetMapping("/get")
	public Customer[] getAll() {
            System.out.println("in getAll");
		return personService.getAllPersons();
	}

}
