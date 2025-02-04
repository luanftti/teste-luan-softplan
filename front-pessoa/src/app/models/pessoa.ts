import { Cidade } from "./cidade";
import { Pais } from "./pais";

export class Pessoa {
    id?: number;
    nome?: string;
    dataNascimento?: Date;
    email?: string;
    cpf?:string;
    cidadeNascimento?:Cidade;
    paisNascimento?:Pais;

}