import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { Routes } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { MessagesModule } from 'primeng/messages';
import { PasswordModule } from 'primeng/password';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './screens/login/login.component';
import { PessoaComponent } from './screens/pessoa/pessoa.component';
import {ToolbarModule} from 'primeng/toolbar';
import { AuthInterceptor } from './auth/auth.interceptor';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'pessoa', component: PessoaComponent },
  // outras rotas
];

@NgModule({
  declarations: [AppComponent, LoginComponent, PessoaComponent],
  imports: [
    AppRoutingModule, 
    CommonModule, 
    HttpClientModule,
    FormsModule,
    PasswordModule,
    MessagesModule,
    BrowserModule,
    CardModule,
    ToastModule,
    FormsModule,
    ButtonModule,
    TableModule,
    DialogModule,
    DropdownModule,
    ToolbarModule,
    HttpClientModule
  ],
  providers: [
    MessageService,
    provideAnimationsAsync(),
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }