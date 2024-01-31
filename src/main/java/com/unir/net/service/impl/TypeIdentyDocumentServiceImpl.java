package com.unir.net.service.impl;

import com.unir.net.dao.TypeIdentityDocumentDao;
import com.unir.net.dto.PatchDto;
import com.unir.net.entity.TypeIdentityDocument;
import com.unir.net.service.TypeIdentityDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeIdentyDocumentServiceImpl implements TypeIdentityDocumentService {

    @Autowired
    private TypeIdentityDocumentDao identityDocumentDao;

    @Override
    public TypeIdentityDocument saveService(TypeIdentityDocument obj) {
        return identityDocumentDao.save(obj);
    }

    @Override
    public TypeIdentityDocument updateService(TypeIdentityDocument obj) {
        return identityDocumentDao.save(obj);
    }

    @Override
    public boolean patchService(Long id, PatchDto dto) {
        return false;
    }

    @Override
    public TypeIdentityDocument findByIdService(Long id) {
        Optional<TypeIdentityDocument> optionalTypeIdentityDocument = identityDocumentDao.findById(id);
        return optionalTypeIdentityDocument.isPresent() ? optionalTypeIdentityDocument.get() : new TypeIdentityDocument();
    }

    @Override
    public boolean deleteByIdService(Long id) {
        identityDocumentDao.deleteById(id);
        return true;
    }

    @Override
    public List<TypeIdentityDocument> findAllService() {
        return identityDocumentDao.findAll();
    }
}
