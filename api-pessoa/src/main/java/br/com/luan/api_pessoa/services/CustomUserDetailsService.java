package br.com.luan.api_pessoa.services;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.luan.api_pessoa.model.CustomUserDetails;
import br.com.luan.api_pessoa.model.Usuario;
import br.com.luan.api_pessoa.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.repository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = repository.buscarUsuarioLogin(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Usu\u00E1rio n\u00E3o encontrado");
        }

        return CustomUserDetails.builder().id(user.getId()).password(passwordEncoder.encode(user.getSenha()))
                .username(user.getLogin())
                .nome(user.getNome()).build();
    }

}
