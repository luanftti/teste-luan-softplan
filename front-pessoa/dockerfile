# Etapa 1: Construção da aplicação Angular
FROM node:18.13.0  AS build

# Define o diretório de trabalho dentro do container
RUN mkdir /app
WORKDIR /app

# Instala dependências do sistema necessárias para o Git
RUN apt-get update && apt-get install -y git

# Clona o repositório (substitua pelo link correto)
RUN git clone https://github.com/luanftti/front-api-pessoa.git

# Define o diretório do projeto Angular
WORKDIR /app/front-api-pessoa
RUN git pull;

# Instala as dependências e faz o build
RUN npm install && npm run build


# Etapa 2: Servindo a aplicação com Nginx
FROM nginx:latest

# Copia os arquivos do build Angular para o diretório de publicação do Nginx
COPY --from=build /app/front-api-pessoa/dist/front-pessoa /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf


# Expõe a porta correta (Nginx usa a 80)
EXPOSE 80


# Inicia o Nginx
CMD ["nginx", "-g", "daemon off;"]
