package br.com.pradela.incidenthub.api;

import br.com.pradela.incidenthub.domain.model.Pessoa;
import br.com.pradela.incidenthub.domain.service.PessoaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public PessoaResponse criar(@RequestBody PessoaRequest request) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNomeCompleto(request.getNomeCompleto());
        pessoa.setDocumentoCpf(request.getDocumentoCpf());

        Pessoa salva = pessoaService.criar(pessoa);

        return new PessoaResponse(
                salva.getIdPessoa(),
                salva.getNomeCompleto(),
                salva.getDocumentoCpf()
        );
    }
}