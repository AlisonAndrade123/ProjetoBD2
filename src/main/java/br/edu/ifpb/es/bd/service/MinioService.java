// Local do arquivo: src/main/java/br/edu/ifpb/es/bd/service/MinioService.java
package br.edu.ifpb.es.bd.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.url}")
    private String minioUrl;

    public String uploadFile(MultipartFile file) {
        try {
            // Gera um nome de arquivo único para evitar colisões
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Faz o upload do arquivo para o MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            // Retorna a URL completa do arquivo salvo
            return minioUrl + "/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload do arquivo para o MinIO", e);
        }
    }
}