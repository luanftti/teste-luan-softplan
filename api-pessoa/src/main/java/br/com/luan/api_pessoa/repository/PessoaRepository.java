package br.com.luan.api_pessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.luan.api_pessoa.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.ativo = true")
    public List<Pessoa> buscarTodosAtivo();

    @Query("SELECT p FROM Pessoa p WHERE p.id = :id AND p.ativo = true")
    public Pessoa buscarPorId(@Param("id") Long id);

}
