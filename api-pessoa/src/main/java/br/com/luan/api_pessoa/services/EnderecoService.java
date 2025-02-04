package br.com.luan.api_pessoa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luan.api_pessoa.model.Cidade;
import br.com.luan.api_pessoa.model.Estado;
import br.com.luan.api_pessoa.model.Pais;
import br.com.luan.api_pessoa.repository.CidadeRepository;
import br.com.luan.api_pessoa.repository.EstadoRepository;
import br.com.luan.api_pessoa.repository.PaisRepository;

@Service
public class EnderecoService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> buscarTodosPaises() {
        return paisRepository.buscarTodosAtivo();
    }

    public List<Estado> buscarTodosEstados() {
        return estadoRepository.buscarTodosAtivo();
    }

    public List<Cidade> buscarTodasCidades() {
        return cidadeRepository.buscarTodasAtivo();
    }
}
