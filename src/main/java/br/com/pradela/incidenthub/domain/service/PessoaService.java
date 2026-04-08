package br.com.pradela.incidenthub.domain.service;

import br.com.pradela.incidenthub.domain.exception.RecursoNaoEncontradoException;
import br.com.pradela.incidenthub.domain.model.Pessoa;
import br.com.pradela.incidenthub.domain.repository.PessoaGateway;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class PessoaService {

    private final PessoaGateway pessoaGateway;

    // Injeção via construtor (boa prática)
    public PessoaService(PessoaGateway pessoaGateway) {
        this.pessoaGateway = pessoaGateway;
    }

    public Pessoa criar(Pessoa pessoa) {

        // regra de negócio simples
        pessoa.setCriadoEm(LocalDateTime.now());

        return pessoaGateway.save(pessoa);
    }
    
    
    public Page<Pessoa> listar(Pageable pageable) {
        return pessoaGateway.findAll(pageable);
    }
    
    public Pessoa buscarPorId(Long id) {
        return pessoaGateway.findById(id)
        		.orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada"));  
    }
    public Pessoa atualizar(Long id, Pessoa dados) {

        Pessoa existente = buscarPorId(id);

        existente.setNomeCompleto(dados.getNomeCompleto());
        existente.setDocumentoCpf(dados.getDocumentoCpf());

        return pessoaGateway.save(existente);
    }
    
    public void deletar(Long id) {

        Pessoa pessoa = buscarPorId(id);

        pessoaGateway.delete(pessoa);
    }
    
}

