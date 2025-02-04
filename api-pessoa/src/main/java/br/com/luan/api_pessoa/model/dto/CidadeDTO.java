package br.com.luan.api_pessoa.model.dto;

import br.com.luan.api_pessoa.abstracts.BaseDTO;
import br.com.luan.api_pessoa.model.Cidade;
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
public class CidadeDTO extends BaseDTO<Cidade> {

    private Long id;
    private String nome;

    @Override
    public Cidade toEntity() {
        return Cidade.builder().id(this.id).nome(this.nome).build();
    }

    @Override
    public BaseDTO<Cidade> fromEntity(Cidade entity) {
        try {
            return CidadeDTO.builder().id(entity.getId()).nome(entity.getNome()).build();
        } catch (Exception e) {
            return null;
        }
    }

}
