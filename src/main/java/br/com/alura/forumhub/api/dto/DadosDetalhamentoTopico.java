package br.com.alura.forumhub.api.dto;

import br.com.alura.forumhub.api.model.Topico;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor().getLogin(), // idem, login como autor
                topico.getCurso()
        );
    }
}
