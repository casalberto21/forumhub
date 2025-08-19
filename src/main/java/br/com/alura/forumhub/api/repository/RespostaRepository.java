package br.com.alura.forumhub.api.repository;

import br.com.alura.forumhub.api.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    List<Resposta> findByTopicoId(Long idTopico);
}