package br.com.luan.api_pessoa.model.dto;

import br.com.luan.api_pessoa.abstracts.BaseDTO;
import br.com.luan.api_pessoa.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO extends BaseDTO<Usuario> {
    private Long id;
    private String login;
    private String nome;

    @Override
    protected Usuario toEntity() {
        return Usuario.builder().id(this.id).login(this.login).nome(this.nome).build();
    }

    @Override
    protected BaseDTO<Usuario> fromEntity(Usuario entity) {
        try {
            return UsuarioDTO.builder().id(entity.getId()).login(entity.getLogin()).nome(entity.getNome()).build();
        } catch (Exception e) {
            return null;
        }
    }

}
