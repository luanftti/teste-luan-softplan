package br.com.luan.api_pessoa.controller;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.luan.api_pessoa.model.CustomUserDetails;
import br.com.luan.api_pessoa.model.dto.UsuarioDTO;

@RestController
@RequestMapping("login")
public class LoginController {

    private static ObjectMapper mapper = new ObjectMapper();

    @PostMapping()
    public ResponseEntity<String> login() {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(auth) && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
                UsuarioDTO retorno = UsuarioDTO.builder().id(user.getId()).login(user.getUsername())
                        .nome(user.getNome()).build();
                String json = mapper.writeValueAsString(retorno);

                return ResponseEntity.ok(json);
            } else {
                return ResponseEntity.internalServerError().body("Erro ao autenticar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
