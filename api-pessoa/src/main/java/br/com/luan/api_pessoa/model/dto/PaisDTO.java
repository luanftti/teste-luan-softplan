package br.com.luan.api_pessoa.model.dto;

import br.com.luan.api_pessoa.abstracts.BaseDTO;
import br.com.luan.api_pessoa.model.Pais;
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
public class PaisDTO extends BaseDTO<Pais> {
    
    private Long id;
    private String nome;

    @Override
    public Pais toEntity() {
        return Pais.builder().id(this.id).nome(this.nome).build();
    }

    @Override
    public BaseDTO<Pais> fromEntity(Pais entity) {
        try {
            return PaisDTO.builder().id(entity.getId()).nome(entity.getNome()).build();

        } catch (Exception e) {
            return null;
        }
    }

}
