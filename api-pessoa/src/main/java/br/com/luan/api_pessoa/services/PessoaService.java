package br.com.luan.api_pessoa.services;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luan.api_pessoa.model.Pessoa;
import br.com.luan.api_pessoa.model.Usuario;
import br.com.luan.api_pessoa.model.dto.PessoaDTO;
import br.com.luan.api_pessoa.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> buscarTodasPessoas() {
        return repository.buscarTodosAtivo();
    }

    public PessoaDTO buscarPorId(Long id) throws Exception {
        PessoaDTO retorno = (PessoaDTO) PessoaDTO.buildFromEntity(repository.buscarPorId(id), PessoaDTO.class);
        if (Objects.isNull(retorno)) {
            throw new Exception(String.format("N\u00E3o encontrato pessoa com id %d", id));
        }
        return retorno;
    }

    public PessoaDTO salvarPessoa(PessoaDTO dto, Long userId) throws Exception {

        Pessoa pessoa = PessoaDTO.buildEntity(dto);
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        if (Objects.isNull(pessoa.getId())) {
            pessoa.setDataCriacao(new Date());
            pessoa.setUsuarioCriacao(usuario);
        } else {
            pessoa.setDataAlteracao(new Date());
            pessoa.setUsuarioAlteracao(usuario);
        }
        return (PessoaDTO) PessoaDTO.buildFromEntity(repository.save(pessoa), PessoaDTO.class);
    }

    public void deletarPessoa(Long pessoaId, Long userId) throws Exception {
        Pessoa pessoa = new Pessoa();
        Usuario usuarioAlteracao = new Usuario();
        usuarioAlteracao.setId(userId);
        pessoa.setAtivo(false);
        pessoa.setDataAlteracao(new Date());
        pessoa.setUsuarioAlteracao(usuarioAlteracao);
        repository.save(pessoa);
    }

}
