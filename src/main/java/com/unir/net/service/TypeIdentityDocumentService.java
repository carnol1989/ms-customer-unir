package com.unir.net.service;

import com.unir.net.dto.PatchDto;
import com.unir.net.entity.TypeIdentityDocument;

public interface TypeIdentityDocumentService extends CrudService<TypeIdentityDocument> {

    boolean patchService(Long id, PatchDto dto);

}
