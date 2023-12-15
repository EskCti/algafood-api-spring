package com.eskcti.algafoodapi.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {
    private Local local = new Local();
    private S3 s3 = new S3();
    private TypeStorage type = TypeStorage.LOCAL;

    public enum TypeStorage {
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {
        private Path directoryPhotos;
    }

    @Getter
    @Setter
    public class S3 {
        private String idKeyAccess;
        private String keySecret;
        private String bucket;
        private Regions region;
        private String directory;
    }
}
