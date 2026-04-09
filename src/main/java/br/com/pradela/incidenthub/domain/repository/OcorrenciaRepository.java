package br.com.pradela.incidenthub.domain.repository;

import br.com.pradela.incidenthub.domain.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>,
        JpaSpecificationExecutor<Ocorrencia> {
}