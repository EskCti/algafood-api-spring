package com.eskcti.algafoodapi.infrastruct.services.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.eskcti.algafoodapi.core.storage.StorageProperties;
import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void storage(NewPhoto newPhoto) {
        try {
            String pathFile = getPathFile(newPhoto.getNameFile());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    pathFile,
                    newPhoto.getInputStream(),
                    objectMetadata
            );

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to send file to Amazon S3.", e);
        }
    }

    private String getPathFile(String nameFile) {
        return String.format("%s/%s", storageProperties.getS3().getDirectory(), nameFile);
    }

    @Override
    public void remove(String nameFile) {

    }

    @Override
    public InputStream toRecover(String nameFile) {
        return null;
    }
}
