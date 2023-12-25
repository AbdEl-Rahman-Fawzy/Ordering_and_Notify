package com.example.demo.service;


import java.util.Set;
import com.example.demo.Database;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl {

    public Boolean addPerson(Customer p) {
        try {
            if(Database.getCustomer(p.getId()) != null){
                return false;
            }
            Database.addCustomer(p.getId(), p);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean deletePerson(int id) {
        try {
            if(Database.getCustomer(id) == null){
                return false;
            }
            Database.removeCustomer(id);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Customer getPerson(int id) {
        try {
            return Database.getCustomer(id);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }

    public Customer[] getAllPersons() {
        try {
            Set<Integer> ids = Database.customers.keySet();
		    Customer[] p = new Customer[ids.size()];
		int i=0;
		for(Integer id : ids){
			p[i] = Database.getCustomer(id);
			i++;
		}
		return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }


}
