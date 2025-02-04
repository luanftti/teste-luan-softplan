package br.com.luan.api_pessoa.model.dto;

import java.util.Date;
import java.util.Objects;

import br.com.luan.api_pessoa.abstracts.BaseDTO;
import br.com.luan.api_pessoa.model.Cidade;
import br.com.luan.api_pessoa.model.Pais;
import br.com.luan.api_pessoa.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO extends BaseDTO<Pessoa> {
        private Long id;
        private String nome;
        private String cpf;
        private String email;
        private CidadeDTO cidadeNascimento;
        private PaisDTO paisNascimento;

        private Date dataNascimento;

        @Override
        protected Pessoa toEntity() {
                return Pessoa.builder().id(this.id).nome(this.nome).cpf(this.cpf).email(this.email)
                                .dataNascimento(this.dataNascimento)
                                .cidade(Objects.isNull(this.cidadeNascimento) ? null
                                                : Cidade.builder().id(this.cidadeNascimento.getId())
                                                                .pais(Objects.isNull(this.paisNascimento) ? null
                                                                                : Pais.builder().id(this.paisNascimento
                                                                                                .getId()).build())
                                                                .build())
                                .build();
        }

        @Override
        protected BaseDTO<Pessoa> fromEntity(Pessoa entity) {
                try {
                        return PessoaDTO.builder().id(entity.getId()).nome(entity.getNome()).cpf(entity.getCpf())
                                        .email(entity.getEmail()).dataNascimento(entity.getDataNascimento())
                                        .cidadeNascimento(Objects.isNull(entity.getCidade()) ? null
                                                        : (CidadeDTO) CidadeDTO.buildFromEntity(entity.getCidade(),
                                                                        CidadeDTO.class))
                                        .paisNascimento(
                                                        Objects.isNull(entity.getCidade())
                                                                        || Objects.isNull(entity.getCidade().getPais())
                                                                                        ? null
                                                                                        : (PaisDTO) PaisDTO
                                                                                                        .buildFromEntity(
                                                                                                                        entity.getCidade()
                                                                                                                                        .getPais(),
                                                                                                                        PaisDTO.class))
                                        .build();
                } catch (Exception e) {
                        return null;
                }
        }

}
