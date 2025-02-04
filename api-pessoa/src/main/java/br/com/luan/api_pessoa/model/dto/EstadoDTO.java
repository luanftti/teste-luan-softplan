package br.com.luan.api_pessoa.model.dto;

import br.com.luan.api_pessoa.abstracts.BaseDTO;
import br.com.luan.api_pessoa.model.Estado;
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
public class EstadoDTO extends BaseDTO<Estado> {

    private Long id;
    private String nome;

    @Override
    protected Estado toEntity() {
        return Estado.builder().id(this.id).nome(this.nome).build();
    }

    @Override
    protected BaseDTO<Estado> fromEntity(Estado entity) {
        return EstadoDTO.builder().id(entity.getId()).nome(entity.getNome()).build();

    }

}
