package br.com.pradela.incidenthub.api;

import br.com.pradela.incidenthub.domain.model.Pessoa;
import br.com.pradela.incidenthub.domain.service.PessoaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;

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
    
    @GetMapping
    public Page<PessoaResponse> listar(Pageable pageable) {

        return pessoaService.listar(pageable)
                .map(pessoa -> new PessoaResponse(
                        pessoa.getIdPessoa(),
                        pessoa.getNomeCompleto(),
                        pessoa.getDocumentoCpf()
                ));
    }
    
    
    @GetMapping("/{id}")
    public PessoaResponse buscarPorId(@PathVariable Long id) {

        Pessoa pessoa = pessoaService.buscarPorId(id);

        return new PessoaResponse(
                pessoa.getIdPessoa(),
                pessoa.getNomeCompleto(),
                pessoa.getDocumentoCpf()
        );
    }
    
    
    @PutMapping("/{id}")
    public PessoaResponse atualizar(@PathVariable Long id, @RequestBody PessoaRequest request) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNomeCompleto(request.getNomeCompleto());
        pessoa.setDocumentoCpf(request.getDocumentoCpf());

        Pessoa atualizada = pessoaService.atualizar(id, pessoa);

        return new PessoaResponse(
                atualizada.getIdPessoa(),
                atualizada.getNomeCompleto(),
                atualizada.getDocumentoCpf()
        );
    }
    
    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
    }
}