package com.eskcti.algafoodapi.domain.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {
    void storage(NewPhoto newPhoto);

    void remove(String nameFile);

    RecoveredPhoto toRecover(String nameFile);

    default String generateFileName(String nameOriginal) {
        return UUID.randomUUID().toString() + "_" + nameOriginal;
    }

    @Builder
    @Getter
    class NewPhoto {
        private String nameFile;
        private String contentType;
        private InputStream inputStream;
    }

    @Builder
    @Getter
    class RecoveredPhoto {
        private InputStream inputStream;
        private String url;

        public boolean haveUrl() {
            return url != null;
        }

        public boolean haveInputStream() {
            return inputStream != null;
        }
    }
}
