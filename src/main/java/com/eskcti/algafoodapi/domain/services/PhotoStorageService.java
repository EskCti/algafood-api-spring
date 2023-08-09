package com.eskcti.algafoodapi.domain.services;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {
    void storage(NewPhoto newPhoto);

    void remove(String nameFile);

    InputStream toRecover(String nameFile);

    default String generateFileName(String nameOriginal) {
        return UUID.randomUUID().toString() + "_" + nameOriginal;
    }

    @Builder
    @Getter
    class NewPhoto {
        private String nameFile;
        private InputStream inputStream;
    }
}
