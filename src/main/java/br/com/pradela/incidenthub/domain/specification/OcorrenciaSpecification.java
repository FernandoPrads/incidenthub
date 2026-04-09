package br.com.pradela.incidenthub.domain.specification;

import br.com.pradela.incidenthub.domain.model.Ocorrencia;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class OcorrenciaSpecification {

    public static Specification<Ocorrencia> filtro(
            String nome,
            String cpf,
            String cidade
    ) {

        return (root, query, cb) -> {

            var predicates = cb.conjunction();

            if (nome != null && !nome.isEmpty()) {
                Join<Object, Object> pessoa = root.join("pessoa");
                predicates = cb.and(predicates,
                        cb.like(cb.lower(pessoa.get("nomeCompleto")),
                                "%" + nome.toLowerCase() + "%"));
            }

            if (cpf != null && !cpf.isEmpty()) {
                Join<Object, Object> pessoa = root.join("pessoa");
                predicates = cb.and(predicates,
                        cb.equal(pessoa.get("documentoCpf"), cpf));
            }

            if (cidade != null && !cidade.isEmpty()) {
                Join<Object, Object> endereco = root.join("endereco");
                predicates = cb.and(predicates,
                        cb.like(cb.lower(endereco.get("cidade")),
                                "%" + cidade.toLowerCase() + "%"));
            }

            return predicates;
        };
    }
}