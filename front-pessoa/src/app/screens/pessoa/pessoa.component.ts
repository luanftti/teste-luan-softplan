import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Cidade } from "../../models/cidade";
import { Pessoa } from "../../models/pessoa";
import { Usuario } from "../../models/usuario";
import { AuthService } from "../../services/auth-service";
import { PessoaService } from "../../services/pessoa-service";
import { Pais } from "../../models/pais";
import { MessageService } from "primeng/api";

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrl: './pessoa.component.scss'
})

export class PessoaComponent implements OnInit{

  private usuarioLogado?: Usuario;

  pessoa:Pessoa = new Pessoa();
  pessoas:Pessoa[] = [];
  displayDialog:boolean = false;
  cidades: Cidade[] = []
  selectedCidade?: Cidade;
  paises: Pais[] = [];
  loading: boolean = false;
  

  constructor (
    private router: Router,
    private authService: AuthService,
    private service: PessoaService, 
    private messageService: MessageService) {
  
  }

  ngOnInit(): void {
    if(this.authService.isLoggedIn()) {
      let user = this.authService.getUser();
      if(user !== null) {        
        this.usuarioLogado = user;
      } else {
        this.authService.logout();
        this.router.navigate(['/login']);
      }     
    }
    this.loadPessoas();
  }
     
  novaPessoa() {
    this.displayDialog = true;
  }

  loadPessoas(){
    this.service.loadPessoas().subscribe({
      next:(data) => {
        this.pessoas = data;
      },
      error:(err) =>{
        this.messageService.add({severity: "error", summary: "Erro", detail: "Erro ao buscar pessoas"});
      }
    })    
  }

  editarPessoa(selectedPessoa: Pessoa) {

  }

  deletarPessoa(selectedPessoa: Pessoa){

  }

  salvarPessoa() {

  }
}