import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { Usuario } from "../models/usuario";
import { envrioment } from "../envrioment";

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    private authHeader: string | null = null;
    private urlLogin: string = envrioment.URL_API + '/login';
    private tempo_expiracao = 30 * 60 * 1000;
    private user: Usuario | null = null;
    

    constructor(private http: HttpClient, router: Router) {

    }

    login(userName: string, password: string) : Observable<any> {
        const credential = btoa(`${userName}:${password}`);
        this.authHeader = 'Basic ' + credential;
        sessionStorage.setItem('auth', credential);              

        return this.http.post(this.urlLogin, {});
    }

    logout() {        
        sessionStorage.clear();

    }

    getAuthHeaders() {
     
        return new HttpHeaders(
            {
                "Authorization" :  this.authHeader || '',
                "Content-Type": "application/json",
                'Accept': 'application/json',
            });
      
    }
    isLoggedIn(){
        const timeExpiration = sessionStorage.getItem('expires');
        if(timeExpiration !== null){
            return Date.now() < Number(timeExpiration);
        }
        return false;
    }

    updateSession() {
        if (sessionStorage.getItem('auth')) {
            sessionStorage.setItem('expires', (Date.now() + this.tempo_expiracao).toString());
        }        
    }

    setUser(user: Usuario) {
        this.user = user;
    }

    getUser(): Usuario {
        if(this.user !== null)
            return this.user;
        else 
            return new Usuario();
    }
   
}