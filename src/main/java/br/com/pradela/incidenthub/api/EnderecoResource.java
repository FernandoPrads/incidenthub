package br.com.pradela.incidenthub.api;

import br.com.pradela.incidenthub.domain.model.Endereco;
import br.com.pradela.incidenthub.domain.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

    private final EnderecoService enderecoService;

    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public Endereco criar(@RequestBody Endereco endereco) {
        return enderecoService.salvar(endereco);
    }

    @GetMapping
    public List<Endereco> listar() {
        return enderecoService.listar();
    }
}