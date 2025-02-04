do
$$
declare 
	idusuario bigint;
	datahora timestamp;
begin

	create schema if not exists api;

	datahora := now();

	create table if not exists api.usuario(
		id bigserial primary key,
		login varchar(100) not null,
		senha varchar(200) not null,
		nome varchar(500) not null,	
		fk_usuario_criacao bigint null,
		data_criacao timestamp not null default now(),
		fk_usuario_alteracao bigint null,
		data_alteracao timestamp null,
		ativo boolean not null default true,
		
		constraint fk_usuario_usuario_criacao foreign key (fk_usuario_criacao) references api.usuario(id),
		constraint fk_usuario_usuario_alteracao foreign key (fk_usuario_alteracao) references api.usuario(id)
	);

	if not exists (select 1 from pg_indexes where indexname = 'idx_usuario_login') then
		create index idx_usuario_login on api.usuario (login); 
	end if;
 
	if not exists (select 1 from api.usuario) then
		insert into api.usuario(login, senha, nome, fk_usuario_criacao, data_criacao, fk_usuario_alteracao, data_alteracao, ativo)
		values('admin', '$2a$12$hjqVK5ImAcCGKHY8bFQGUeRwYm3U1lUJ6AS.5Of.D7Ew4Q4fWNt3W', 'Usuario Admin', null, datahora, null, null, true);
	end if;

	select id 
	into idusuario
	from api.usuario u 
	where u.login = 'admin';

	update api.usuario
	set fk_usuario_criacao = idusuario
	where login = 'admin';

	alter table api.usuario alter column fk_usuario_criacao set not null;


	create table if not exists api.pais (
		id serial primary key,
		nome varchar(100) not null,
		sigla varchar(2),
		regiao varchar(50) not null,		
		subregiao varchar(50) null,
		regiao_intermediaria varchar(50) null,
		fk_usuario_criacao bigint null,
		data_criacao timestamp not null default now(),
		fk_usuario_alteracao bigint null,
		data_alteracao timestamp null,
		ativo boolean not null default true,

		constraint fk_pais_usuario_criacao foreign key(fk_usuario_criacao) references api.usuario(id),
		constraint fk_pais_usuario_alteracao foreign key(fk_usuario_alteracao) references api.usuario(id)		
	);

	if not exists (select 1 from pg_indexes where indexname = 'idx_pais_nome') then
		create index idx_pais_nome on api.pais(nome);
	end if;

	if not exists (select 1 from pg_indexes where indexname = 'idx_pais_sigla') then
		create index idx_pais_sigla on api.pais(sigla);
	end if;

	create table if not exists api.estado (
		id serial primary key,
		nome varchar(100) not null,
		sigla varchar(2) not null,
		fk_pais int not null,
		fk_usuario_criacao bigint null,
		data_criacao timestamp not null default now(),
		fk_usuario_alteracao bigint null,
		data_alteracao timestamp null,
		ativo boolean not null default true,

		constraint fk_estado_usuario_criacao foreign key(fk_usuario_criacao) references api.usuario(id),
		constraint fk_estado_usuario_alteracao foreign key(fk_usuario_alteracao) references api.usuario(id),
		constraint fk_estado_pais foreign key(fk_pais) references api.pais(id)
		
	);

	
	if not exists (select 1 from pg_indexes where indexname = 'idx_estado_pais_nome') then
		create index idx_estado_pais_nome on api.estado(fk_pais, nome) ;
	end if;

	if not exists (select 1 from pg_indexes where indexname = 'idx_estado_pais_sigla') then
		create index idx_estado_pais_sigla on api.estado(fk_pais, sigla);
 	end if;

	if not exists (select 1 from pg_indexes where indexname = 'idx_estado_nome') then
		create index idx_estado_nome on api.estado(nome);
	end if;


	create table if not exists api.cidade (
		id bigserial primary key,
		nome varchar(250) not null,
		fk_estado int null,
		fk_pais int null,
		fk_usuario_criacao bigint null,
		data_criacao timestamp not null default now(),
		fk_usuario_alteracao bigint null,
		data_alteracao timestamp null,
		ativo boolean not null default true,

		constraint fk_cidade_usuario_criacao foreign key(fk_usuario_criacao) references api.usuario(id),
		constraint fk_cidade_usuario_alteracao foreign key(fk_usuario_alteracao) references api.usuario(id)
	);

	if not exists (select 1 from pg_indexes where indexname = 'idx_cidade_nome') then
		create index idx_cidade_nome on api.cidade(nome);
	end if;

	if not exists (select 1 from pg_indexes where indexname = 'idx_estado_nome') then
		create index idx_estado_nome on api.cidade(fk_estado, nome);
	end if;
	
	if not exists (select 1 from pg_indexes where indexname = 'idx_pais_nome') then
		create index idx_pais_nome on api.cidade(fk_pais,nome);
	end if;


	create table if not exists api.pessoa (
		id bigserial primary key,
		nome text not null,
		sexo int null,
		email varchar(100) null,
		data_nascimento timestamp not null,
		fk_pais_nascimento int null,		
		fk_estado_nascimento int null,
		fk_cidade_nascimento int null,
		cpf varchar(11) not null,
		fk_usuario_criacao bigint null,
		data_criacao timestamp not null default now(),
		fk_usuario_alteracao bigint null,
		data_alteracao timestamp null,
		ativo boolean not null default true,

		constraint fk_tabela_usuario_criacao foreign key(fk_usuario_criacao) references api.usuario(id),
		constraint fk_tabela_usuario_alteracao foreign key(fk_usuario_alteracao) references api.usuario(id),
		constraint fk_pessoa_pais foreign key (fk_pais_nascimento) references api.pais(id),
		constraint fk_pessoa_estado foreign key (fK_estado_nascimento) references api.estado(id),
		constraint fk_pessoa_cidade foreign key (fk_cidade_nascimento) references api.cidade(id),
		constraint un_cpf_pessoa unique (cpf)
		
	);

	if not exists (select 1 from pg_indexes where indexname = 'idx_pessoa_cpf') then
		create index idx_pessoa_cpf on api.pessoa(cpf);
	end if;
	
	if not exists (select 1 from pg_indexes where indexname = 'idx_pessoa_nome') then
		create index idx_pessoa_nome on api.pessoa(nome);
	end if;
	

	if not exists (select 1 from pg_roles where rolname = 'api_user') then
		create role api_user with login password 'api@2025$UU';
	end if;
	
	grant connect on database postgres to api_user;

	grant usage on schema api to api_user;

	grant usage, select, update on all sequences in schema api TO api_user;

	grant select, insert, update, delete on all tables in schema api to api_user;

	alter default privileges in schema api grant select, insert, update, delete on tables to api_user;

end;
$$;