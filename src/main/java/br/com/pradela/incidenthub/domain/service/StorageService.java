package br.com.pradela.incidenthub.domain.service;

import io.minio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Service
public class StorageService {

    private final MinioClient minioClient;

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    public StorageService(@Value("${minio.url}") String url,
                          @Value("${minio.access-key}") String accessKey,
                          @Value("${minio.secret-key}") String secretKey) {

        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

    // Garante que o bucket existe
    @PostConstruct
    public void criarBucketSeNaoExistir() {
        try {
            boolean existe = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket("ocorrencias").build()
            );

            if (!existe) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket("ocorrencias").build()
                );
            }

            System.out.println("✅ Bucket verificado/criado com sucesso");

        } catch (Exception e) {
            System.out.println("⚠️ MinIO não está rodando. Aplicação continuará sem storage.");
        }
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
                            .bucket(bucket)
                            .object(nomeArquivo)
                            .stream(arquivo.getInputStream(), arquivo.getSize(), -1)
                            .contentType(contentType)
                            .build()
            );

            return url + "/" + bucket + "/" + nomeArquivo;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo", e);
        }
    }
}