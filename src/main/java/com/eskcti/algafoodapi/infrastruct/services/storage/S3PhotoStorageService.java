package com.eskcti.algafoodapi.infrastruct.services.storage;

import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {
    @Override
    public void storage(NewPhoto newPhoto) {

    }

    @Override
    public void remove(String nameFile) {

    }

    @Override
    public InputStream toRecover(String nameFile) {
        return null;
    }
}
