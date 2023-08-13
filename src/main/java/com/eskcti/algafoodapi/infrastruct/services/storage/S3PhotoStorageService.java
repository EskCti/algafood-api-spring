package com.eskcti.algafoodapi.infrastruct.services.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

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
