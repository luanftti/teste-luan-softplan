package br.com.luan.api_pessoa.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("source")
public class SourceController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getSource() {
        return """
                   <!DOCTYPE html>
                   <html lang="pt-BR">
                   <head>
                       <meta charset="UTF-8">
                   </head>
                   <body>
                         <a href=https://github.com/luanftti/teste-luan-softplan.git>https://github.com/luanftti/teste-luan-softplan.git</a>
                    </body>
                    </html>
                """;
    }
}
