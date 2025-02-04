package br.com.luan.api_pessoa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("source")
public class SourceController {

    @GetMapping
    public String getSource() {
        return "https://github.com/luanftti/teste-luan-softplan.git";
    }
}
