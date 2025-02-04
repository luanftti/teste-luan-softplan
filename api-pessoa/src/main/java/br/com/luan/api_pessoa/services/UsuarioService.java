package br.com.luan.api_pessoa.services;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luan.api_pessoa.model.Usuario;
import br.com.luan.api_pessoa.model.dto.UsuarioDTO;
import br.com.luan.api_pessoa.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDTO salvarUsuario(UsuarioDTO dto, Long userId) throws Exception {
        Usuario novoUsuario = UsuarioDTO.buildEntity(dto);
        Usuario usuarioCriacaoAlteracao = new Usuario();
        usuarioCriacaoAlteracao.setId(userId);
        if (Objects.isNull(novoUsuario.getId())) {
            novoUsuario.setUsuarioCriacao(usuarioCriacaoAlteracao);
            novoUsuario.setDataCriacao(new Date());
        } else {
            novoUsuario.setUsuarioAlteracao(usuarioCriacaoAlteracao);
            novoUsuario.setDataAlteracao(new Date());
        }
        return (UsuarioDTO) UsuarioDTO.buildFromEntity(repository.save(novoUsuario), UsuarioDTO.class);

    }

    public void deletarUsuario(Long pessoaId, Long userId) throws Exception {
        Usuario usuario = new Usuario();
        Usuario usuarioAlteracao = new Usuario();
        usuarioAlteracao.setId(userId);
        usuario.setAtivo(false);
        usuario.setDataAlteracao(new Date());
        usuario.setUsuarioAlteracao(usuarioAlteracao);
        repository.save(usuario);
    }
}
