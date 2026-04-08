package br.com.pradela.incidenthub.domain.service;

import io.minio.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class StorageService {

    private final MinioClient minioClient;

    public StorageService() {
        this.minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("admin", "admin123")
                .build();
    }

    public String uploadArquivo(MultipartFile arquivo) {

        try {
        	String extensao = "";

        	if (arquivo.getOriginalFilename() != null &&
        	    arquivo.getOriginalFilename().contains(".")) {

        	    extensao = arquivo.getOriginalFilename()
        	            .substring(arquivo.getOriginalFilename().lastIndexOf("."));
        	}

        	String nomeArquivo = UUID.randomUUID() + extensao;
            String contentType = arquivo.getContentType();
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("ocorrencias")
                            .object(nomeArquivo)
                            .stream(arquivo.getInputStream(), arquivo.getSize(), -1)
                            .contentType(contentType)
                            .build()
            );

            return "http://localhost:9000/ocorrencias/" + nomeArquivo;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo", e);
        }
    }
}