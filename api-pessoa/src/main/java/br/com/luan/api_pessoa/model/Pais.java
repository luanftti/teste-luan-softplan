package br.com.luan.api_pessoa.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "api", name = "pais")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "sigla", length = 2)
    private String sigla;

    @Column(name = "regiao", nullable = false, length = 50)
    private String regiao;

    @Column(name = "subregiao", length = 50)
    private String subregiao;

    @Column(name = "regiao_intermediaria", length = 50)
    private String regiaoIntermediaria;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_criacao", updatable = false)
    private Usuario usuarioCriacao;

    @Builder.Default
    @Column(name = "data_criacao", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    @ManyToOne
    @JoinColumn(name = "fk_usuario_alteracao")
    private Usuario usuarioAlteracao;

    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @Builder.Default
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}
