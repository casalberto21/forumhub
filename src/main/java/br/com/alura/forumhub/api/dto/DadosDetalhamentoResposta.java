package br.com.alura.forumhub.api.dto;

import br.com.alura.forumhub.api.model.Resposta;
import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id,
        String mensagem,
        String nomeTopico,
        LocalDateTime dataCriacao,
        String nomeAutor,
        Boolean solucao
) {
    public DadosDetalhamentoResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getTitulo(),
                resposta.getDataCriacao(),
                resposta.getAutor().getUsername(),
                resposta.getSolucao()
        );
    }
}