package br.com.pradela.incidenthub.domain.service;

import br.com.pradela.incidenthub.domain.model.Pessoa;
import br.com.pradela.incidenthub.domain.repository.PessoaGateway;
import org.springframework.stereotype.Service;

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
}