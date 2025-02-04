import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Pessoa } from "../models/pessoa";
import { envrioment } from "../envrioment";
import { AuthService } from "./auth-service";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root',
})
export class PessoaService {

    private urlBase = envrioment.URL_API + '/pessoa';

    constructor(private auth: AuthService, private http: HttpClient) {

    }

    loadPessoas():Observable<Pessoa[]> {
        const headers = this.auth.getAuthHeaders();
        return this.http.get<Pessoa[]>(this.urlBase + '/buscarTodos', {headers});
    }
    
}