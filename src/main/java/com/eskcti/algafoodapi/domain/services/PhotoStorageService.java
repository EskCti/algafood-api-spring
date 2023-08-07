package com.eskcti.algafoodapi.domain.services;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface PhotoStorageService {
    void storage(NewPhoto newPhoto);

    @Builder
    @Getter
    class NewPhoto {
        private String nameFile;
        private InputStream inputStream;
    }
}
