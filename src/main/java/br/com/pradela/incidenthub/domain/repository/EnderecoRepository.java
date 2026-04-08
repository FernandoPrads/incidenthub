package br.com.pradela.incidenthub.domain.repository;

import br.com.pradela.incidenthub.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}