package br.com.luan.api_pessoa.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.luan.api_pessoa.model.CustomUserDetails;
import br.com.luan.api_pessoa.model.dto.UsuarioDTO;
import br.com.luan.api_pessoa.services.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/criarUsuario")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
                String json = mapper.writeValueAsString(service.salvarUsuario(dto, user.getId()));
                return ResponseEntity.ok(json);
            } else {
                return ResponseEntity.internalServerError().body("Erro ao autenticar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/editarUsuario")
    public ResponseEntity<String> editarPessoa(@RequestBody UsuarioDTO dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
                String json = mapper.writeValueAsString(service.salvarUsuario(dto, user.getId()));
                return ResponseEntity.ok(json);
            } else {
                return ResponseEntity.internalServerError().body("Erro ao autenticar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
