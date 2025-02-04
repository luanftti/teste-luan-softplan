import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { AuthGuard } from "./auth/auth.guard";
import { LoginComponent } from "./screens/login/login.component";
import { PessoaComponent } from "./screens/pessoa/pessoa.component";


@NgModule({
    imports: [RouterModule.forRoot([
        {
            path:'',
            redirectTo: '/pessoa',
            pathMatch: 'full',
        },
        {
            path: 'login',
            component: LoginComponent
        },
        {
            path: 'pessoa',
            component: PessoaComponent,
            canActivate:[AuthGuard]
        }
    ])],
    exports: [RouterModule]})
export class AppRoutingModule {}