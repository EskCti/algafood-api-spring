package com.eskcti.algafoodapi.infrastruct.services.storage;

import com.eskcti.algafoodapi.domain.services.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//@Service
public class LocalPhotoStorageService implements PhotoStorageService {
    @Value("${algafood.storage.local.directory-photos}")
    private Path directoryPhotos;
    @Override
    public void storage(NewPhoto newPhoto) {
        try {
            Path filePath = getFilePath(newPhoto.getNameFile());

            FileCopyUtils.copy(newPhoto.getInputStream(),
                    Files.newOutputStream(filePath));
        } catch (IOException e) {
            throw new StorageException("Unable to store file", e);
        }
    }

    @Override
    public void remove(String nameFile) {
        try {
            Path filePath = getFilePath(nameFile);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new StorageException("Unable to remove file", e);
        }
    }

    @Override
    public InputStream toRecover(String nameFile) {
        Path filePath = getFilePath(nameFile);
        if (Files.exists(filePath)) {
            try {
                return Files.newInputStream(filePath);
            } catch (IOException e) {
                throw new StorageException("Unable to recover file", e);
            }
        }
        return null;
    }

    private Path getFilePath(String nameFile) {
        return directoryPhotos.resolve(Path.of(nameFile));
    }
}
