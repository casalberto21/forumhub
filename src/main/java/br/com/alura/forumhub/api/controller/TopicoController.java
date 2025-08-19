package br.com.alura.forumhub.api.controller;

import br.com.alura.forumhub.api.dto.*;
import br.com.alura.forumhub.api.model.Topico;
import br.com.alura.forumhub.api.model.Usuario;
import br.com.alura.forumhub.api.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    // Criar tópico
    @PostMapping
    @Transactional
    public DadosDetalhamentoTopico cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        var topico = new Topico(dados, usuarioLogado);
        repository.save(topico);
        return new DadosDetalhamentoTopico(topico);
    }

    // Listar tópicos (paginação)
    @GetMapping
    public Page<DadosListagemTopico> listar(Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(DadosListagemTopico::new);
    }

    // Detalhar tópico por ID
    @GetMapping("/{id}")
    public DadosDetalhamentoTopico detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return new DadosDetalhamentoTopico(topico);
    }

    // Atualizar tópico (só o autor pode atualizar)
    @PutMapping("/{id}")
    @Transactional
    public DadosDetalhamentoTopico atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        var topico = repository.getReferenceById(id);

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Acesso negado: você não é o autor deste tópico.");
        }

        topico.atualizarInformacoes(dados);
        return new DadosDetalhamentoTopico(topico);
    }

    // Excluir tópico (só o autor pode excluir)
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        var topico = repository.getReferenceById(id);

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Acesso negado: você não é o autor deste tópico.");
        }

        topico.excluir();
    }
}
