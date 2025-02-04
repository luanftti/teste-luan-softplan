package br.com.luan.api_pessoa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.luan.api_pessoa.model.Cidade;
import br.com.luan.api_pessoa.model.Estado;
import br.com.luan.api_pessoa.model.Pais;
import br.com.luan.api_pessoa.model.dto.CidadeDTO;
import br.com.luan.api_pessoa.model.dto.EstadoDTO;
import br.com.luan.api_pessoa.model.dto.PaisDTO;
import br.com.luan.api_pessoa.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/buscarPaises")
    private ResponseEntity<String> buscarTodosPaises() {
        try {
            List<Pais> retornoBanco = service.buscarTodosPaises();
            List<PaisDTO> retorno = new ArrayList<>();
            if (Objects.nonNull(retornoBanco)) {
                for (Pais p : retornoBanco) {
                    retorno.add((PaisDTO) PaisDTO.buildFromEntity(p, PaisDTO.class));
                }
            }
            String json = mapper.writeValueAsString(retorno);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/buscarEstados")
    private ResponseEntity<String> buscarTodosEstados() {
        try {
            List<Estado> retornoBanco = service.buscarTodosEstados();
            List<EstadoDTO> retorno = new ArrayList<>();
            if (Objects.nonNull(retornoBanco)) {
                for (Estado p : retornoBanco) {
                    retorno.add((EstadoDTO) EstadoDTO.buildFromEntity(p, EstadoDTO.class));
                }
            }
            String json = mapper.writeValueAsString(retorno);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/buscarCidades")
    private ResponseEntity<String> buscarTodasCidades() {
        try {
            List<Cidade> retornoBanco = service.buscarTodasCidades();
            List<CidadeDTO> retorno = new ArrayList<>();
            if (Objects.nonNull(retornoBanco)) {
                for (Cidade p : retornoBanco) {
                    retorno.add((CidadeDTO) CidadeDTO.buildFromEntity(p, CidadeDTO.class));
                }
            }
            String json = mapper.writeValueAsString(retorno);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
