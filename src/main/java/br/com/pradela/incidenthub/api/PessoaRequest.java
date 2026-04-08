package br.com.pradela.incidenthub.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PessoaRequest {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String documentoCpf;

    private String dataNascimento;

    // getters e setters
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getDocumentoCpf() { return documentoCpf; }
    public void setDocumentoCpf(String documentoCpf) { this.documentoCpf = documentoCpf; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}