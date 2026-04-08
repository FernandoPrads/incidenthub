package br.com.pradela.incidenthub.api.exception;

import java.time.LocalDateTime;

public class ErroResponse {

    private int status;
    private String erro;
    private String mensagem;
    private LocalDateTime dataHora;

    public ErroResponse(int status, String erro, String mensagem, LocalDateTime dataHora) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public int getStatus() { return status; }
    public String getErro() { return erro; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataHora() { return dataHora; }
}