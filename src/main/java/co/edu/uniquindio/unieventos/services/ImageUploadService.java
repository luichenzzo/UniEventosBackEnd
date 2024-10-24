package co.edu.uniquindio.unieventos.services;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageUploadService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    @Value("${gcp.bucket.name}")
    private String bucketName;

    public String uploadImage(MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, imageName).build();

        storage.create(blobInfo, file.getBytes());

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, imageName);
    }
}
