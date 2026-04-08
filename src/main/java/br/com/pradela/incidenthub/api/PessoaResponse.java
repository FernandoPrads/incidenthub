package br.com.pradela.incidenthub.api;

public class PessoaResponse {

    private Long idPessoa;
    private String nomeCompleto;
    private String documentoCpf;

    public PessoaResponse(Long idPessoa, String nomeCompleto, String documentoCpf) {
        this.idPessoa = idPessoa;
        this.nomeCompleto = nomeCompleto;
        this.documentoCpf = documentoCpf;
    }

    public Long getIdPessoa() { return idPessoa; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getDocumentoCpf() { return documentoCpf; }
}