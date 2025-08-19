package br.com.alura.forumhub.api.dto;

import br.com.alura.forumhub.api.model.StatusTopico;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        StatusTopico status
) {}
