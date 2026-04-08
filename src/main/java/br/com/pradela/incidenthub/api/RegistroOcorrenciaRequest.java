package br.com.pradela.incidenthub.api;

public class RegistroOcorrenciaRequest {

    private String nomeCompleto;
    private String documentoCpf;

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    // getters e setters

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getDocumentoCpf() { return documentoCpf; }
    public void setDocumentoCpf(String documentoCpf) { this.documentoCpf = documentoCpf; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}