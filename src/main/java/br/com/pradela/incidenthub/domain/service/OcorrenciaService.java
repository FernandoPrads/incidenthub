package br.com.pradela.incidenthub.domain.service;

import br.com.pradela.incidenthub.domain.model.*;
import br.com.pradela.incidenthub.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static br.com.pradela.incidenthub.domain.specification.OcorrenciaSpecification.filtro;


import java.time.LocalDateTime;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final PessoaGateway pessoaGateway;
    private final EnderecoRepository enderecoRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
                             PessoaGateway pessoaGateway,
                             EnderecoRepository enderecoRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.pessoaGateway = pessoaGateway;
        this.enderecoRepository = enderecoRepository;
    }

    public Ocorrencia registrar(
            String nome,
            String cpf,
            String logradouro,
            String bairro,
            String cep,
            String cidade,
            String estado,
            String urlImagem
    ) {

        Pessoa pessoa = pessoaGateway.findByDocumentoCpf(cpf)
                .orElseGet(() -> {
                    Pessoa nova = new Pessoa();
                    nova.setNomeCompleto(nome);
                    nova.setDocumentoCpf(cpf);
                    return pessoaGateway.save(nova);
                });

        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);

        endereco = enderecoRepository.save(endereco);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setPessoa(pessoa);
        ocorrencia.setEndereco(endereco);
        ocorrencia.setDataOcorrencia(LocalDateTime.now());
        ocorrencia.setStatus("ATIVA");
        ocorrencia.setUrlImagem(urlImagem);

        return ocorrenciaRepository.save(ocorrencia);
    }
    
    public Page<Ocorrencia> listar(
            String nome,
            String cpf,
            String cidade,
            Pageable pageable
    ) {
        return ocorrenciaRepository.findAll(filtro(nome, cpf, cidade), pageable);
    }
    
    public Ocorrencia finalizar(Long id) {

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        if ("FINALIZADA".equals(ocorrencia.getStatus())) {
            throw new RuntimeException("Ocorrência já está finalizada");
        }

        ocorrencia.setStatus("FINALIZADA");

        return ocorrenciaRepository.save(ocorrencia);
    }
}