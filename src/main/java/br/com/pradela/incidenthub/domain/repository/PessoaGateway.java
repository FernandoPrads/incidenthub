package br.com.pradela.incidenthub.domain.repository;

import br.com.pradela.incidenthub.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaGateway extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByDocumentoCpf(String documentoCpf);
}