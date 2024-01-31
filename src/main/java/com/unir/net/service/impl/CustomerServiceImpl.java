package com.unir.net.service.impl;

import com.unir.net.dao.CustomerDao;
import com.unir.net.dto.PatchDto;
import com.unir.net.entity.Customer;
import com.unir.net.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer saveService(Customer obj) {
        return customerDao.save(obj);
    }

    @Override
    public Customer updateService(Customer obj) {
        return customerDao.save(obj);
    }

    @Override
    public Customer findByIdService(Long id) {
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        return optionalCustomer.isPresent() ? optionalCustomer.get() : new Customer();
    }

    @Override
    public boolean deleteByIdService(Long id) {
        customerDao.deleteById(id);
        return true;
    }

    @Override
    public List<Customer> findAllService() {
        return customerDao.findAll();
    }

    @Override
    public boolean pathService(Long id, PatchDto dto) {
        boolean result = false;
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (dto.getKey().equalsIgnoreCase("firstName")) {
                customer.setFirstName(dto.getValue());
            }
            if (dto.getKey().equalsIgnoreCase("lastName")) {
                customer.setLastName(dto.getValue());
            }
            customerDao.save(customer);
            result = true;
        }
        return result;
    }
}
