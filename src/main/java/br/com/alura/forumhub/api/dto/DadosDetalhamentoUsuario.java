package br.com.alura.forumhub.api.dto;

import br.com.alura.forumhub.api.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}