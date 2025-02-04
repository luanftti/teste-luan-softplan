package br.com.luan.api_pessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.luan.api_pessoa.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    @Query("SELECT p FROM Pais p WHERE p.ativo = true")
    public List<Pais> buscarTodosAtivo();

}
