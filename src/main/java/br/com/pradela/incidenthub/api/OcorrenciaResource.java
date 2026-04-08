package br.com.pradela.incidenthub.api;

import br.com.pradela.incidenthub.domain.model.Ocorrencia;
import br.com.pradela.incidenthub.domain.service.OcorrenciaService;
import br.com.pradela.incidenthub.domain.service.StorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaResource {

    private final OcorrenciaService ocorrenciaService;
    private final StorageService storageService;

    public OcorrenciaResource(OcorrenciaService ocorrenciaService,
                              StorageService storageService) {
        this.ocorrenciaService = ocorrenciaService;
        this.storageService = storageService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public Ocorrencia criar(
            @RequestParam String nomeCompleto,
            @RequestParam String documentoCpf,
            @RequestParam String logradouro,
            @RequestParam String bairro,
            @RequestParam String cep,
            @RequestParam String cidade,
            @RequestParam String estado,
            @RequestParam MultipartFile imagem
    ) {

        String urlImagem = storageService.uploadArquivo(imagem);

        return ocorrenciaService.registrar(
                nomeCompleto,
                documentoCpf,
                logradouro,
                bairro,
                cep,
                cidade,
                estado,
                urlImagem
        );
    }
}