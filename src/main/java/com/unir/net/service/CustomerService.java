package com.unir.net.service;

import com.unir.net.dto.PatchDto;
import com.unir.net.entity.Customer;

public interface CustomerService extends CrudService<Customer> {

    boolean pathService(Long id, PatchDto dto);

}
