import { Component, ViewChild, AfterViewInit, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})

export class LoginComponent {
  
  userName: string = '';
  password: string = '';

  @ViewChild('usernameInput') usernameInput!: ElementRef;
  @ViewChild('passwordIpunt') passwordIpunt!: ElementRef;

  constructor(
    private router: Router, 
    private messageService: MessageService,
    private service: AuthService) {
      
  }

  onLoginClick() {
    try {
      if(this.userName == ""){
        this.messageService.add({severity: "warn", summary: "Aviso", detail: "O usuário deve ser informado"});
        this.usernameInput.nativeElement.focus();
        return;
      }  
      if(this.password == "") {
        this.messageService.add({severity: "warn", summary: "Aviso", detail: "A senha deve ser informada"});
        this.passwordIpunt.nativeElement.focus();
        return;
      }

      this.service.login(this.userName, this.password).subscribe({
        next: (data) => {
          if(data !== undefined && data.login !== undefined && data.login !== ''){
            this.service.updateSession();
            this.service.setUser(data);
            this.router.navigate(['/pessoa'])
          }
        },
        error: (err) => {
          this.messageService.add({severity: "error", summary: "Erro", detail: "Erro na requisição de login"});
        }
      });

    } catch (error) {
      this.messageService.add({severity: "error", summary: "Erro", detail: 'Ocorreu um erro ao processar o login'});
    }
  }

}