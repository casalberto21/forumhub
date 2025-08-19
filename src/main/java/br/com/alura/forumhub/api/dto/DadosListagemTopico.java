package br.com.alura.forumhub.api.dto;

import br.com.alura.forumhub.api.model.Topico;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor().getLogin(), // agora usa o login do Usuario
                topico.getCurso()
        );
    }
}
