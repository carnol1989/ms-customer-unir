package com.unir.net.service;

import java.util.List;

public interface CrudService<T> {

    T saveService(T obj);
    T updateService(T obj);
    T findByIdService(Long id);
    boolean deleteByIdService(Long id);
    List<T> findAllService();

}
