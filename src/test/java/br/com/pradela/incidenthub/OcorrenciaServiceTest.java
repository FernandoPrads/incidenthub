package br.com.pradela.incidenthub;

import br.com.pradela.incidenthub.domain.model.Ocorrencia;
import br.com.pradela.incidenthub.domain.repository.EnderecoRepository;
import br.com.pradela.incidenthub.domain.repository.OcorrenciaRepository;
import br.com.pradela.incidenthub.domain.repository.PessoaGateway;
import br.com.pradela.incidenthub.domain.service.OcorrenciaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OcorrenciaServiceTest {

    private OcorrenciaRepository ocorrenciaRepository;
    private PessoaGateway pessoaGateway;
    private EnderecoRepository enderecoRepository;

    private OcorrenciaService ocorrenciaService;

    @BeforeEach
    void setup() {
        ocorrenciaRepository = mock(OcorrenciaRepository.class);
        pessoaGateway = mock(PessoaGateway.class);
        enderecoRepository = mock(EnderecoRepository.class);

        ocorrenciaService = new OcorrenciaService(
                ocorrenciaRepository,
                pessoaGateway,
                enderecoRepository
        );
    }

    @Test
    void deveFinalizarOcorrencia() {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setIdOcorrencia(1L);
        ocorrencia.setStatus("ATIVA");

        when(ocorrenciaRepository.findById(1L))
                .thenReturn(Optional.of(ocorrencia));

        when(ocorrenciaRepository.save(any()))
                .thenReturn(ocorrencia);

        Ocorrencia resultado = ocorrenciaService.finalizar(1L);

        assertEquals("FINALIZADA", resultado.getStatus());
    }
}