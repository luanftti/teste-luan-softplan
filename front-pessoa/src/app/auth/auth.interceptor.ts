import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "../services/auth-service";
import { Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    
    constructor (private service: AuthService) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authHeader = this.service.getAuthHeaders();
        if(authHeader) {
            const cloned = req.clone({
                headers:authHeader
            });            
            return next.handle(cloned)
        }
        return next.handle(req);
    }
}