package br.com.luan.api_pessoa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.luan.api_pessoa.model.CustomUserDetails;
import br.com.luan.api_pessoa.model.Pessoa;
import br.com.luan.api_pessoa.model.dto.PessoaDTO;
import br.com.luan.api_pessoa.services.PessoaService;

@RestController
@RequestMapping("pessoa")
@CrossOrigin
public class PessoaController {

    @Autowired
    private PessoaService service;

    private static ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/buscarTodos")
    public ResponseEntity<String> buscarTodasPessoas() {
        try {
            List<Pessoa> retornoBanco = service.buscarTodasPessoas();
            List<PessoaDTO> retorno = new ArrayList<>();
            if (Objects.nonNull(retornoBanco)) {
                for (Pessoa p : retornoBanco) {
                    retorno.add((PessoaDTO) PessoaDTO.buildFromEntity(p, PessoaDTO.class));
                }
            }
            String json = mapper.writeValueAsString(retorno);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/criarPessoa")
    public ResponseEntity<String> criaPessoa(@RequestBody PessoaDTO dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
                String json = mapper.writeValueAsString(service.salvarPessoa(dto, user.getId()));
                return ResponseEntity.ok(json);
            } else {
                return ResponseEntity.internalServerError().body("Erro ao autenticar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/editarPessoa")
    public ResponseEntity<String> editarPessoa(@RequestBody PessoaDTO dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
                String json = mapper.writeValueAsString(service.salvarPessoa(dto, user.getId()));
                return ResponseEntity.ok(json);
            } else {
                return ResponseEntity.internalServerError().body("Erro ao autenticar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletarPessoa")
    public ResponseEntity<String> deletarPessoa(@RequestParam Long pessoaId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
                service.deletarPessoa(pessoaId, user.getId());
                return ResponseEntity.ok("Pessoa excluída com sucesso!");
            } else {
                return ResponseEntity.internalServerError().body("Erro ao autenticar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
