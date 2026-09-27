-- Sistema de Aerolineas - Script de creacion de la base de datos 

create database if not exists sistema_aerolineas
	character set utf8mb4
    collate utf8mb4_spanish_ci;
    
use sistema_aerolineas;

-- Tabla: aerolineas
create table aerolineas(
	id_aerolinea int auto_increment primary key,
    nombre varchar(100) not null unique
);

-- Tabla: aviones 
create table aviones(
	id_avion int auto_increment primary key,
    id_aerolinea int not null,
    modelo varchar(80) not null,
    capacidad int not null,
    estado varchar(10) not null default 'Activo',
    constraint fk_avion_aerolinea foreign key (id_aerolinea)
		references aerolineas(id_aerolinea) on delete restrict,
    constraint chk_capacidad check (capacidad>0),
    constraint chk_estado_avion check (estado in('Activo','Inactivo'))
);

-- Tabla: pilotos
create table pilotos(
	id_piloto int auto_increment primary key,
    cedula varchar(20) not null unique,
    nombre varchar(60) not null,
    apellidos varchar(60) not null,
    fecha_nacimiento date not null,
    estado_civil varchar(20) not null,
    numero_licencia varchar(30) not null unique,
    fecha_vencimiento_licencia date not null
);

-- Tabla: vuelos
create table vuelos(
	id_vuelo int auto_increment primary key,
	id_avion int not null,
    id_piloto int not null,
    pais_destino varchar(60) not null,
    ciudad_destino varchar(60) not null,
    fecha_hora_salida datetime not null,
    estado varchar(15) not null default 'Programado',
    constraint fk_vuelo_avion foreign key (id_avion)
		references aviones(id_avion) on delete restrict,
	constraint fk_vuelo_piloto foreign key (id_piloto)
		references pilotos(id_piloto) on delete restrict,
	constraint chk_estado_vuelo check (estado in ('Programado','Cancelado','Finalizado'))
);

-- Tabla: pasajeros
create table pasajeros(
	id_pasajero int auto_increment primary key,
    cedula varchar(20) not null unique,
    nombre_completo varchar(120) not null,
    pasaporte varchar(30) not null,
    fecha_vencimiento_pasaporte date not null
);

-- Tabla: tiquetes
create table tiquetes(
	id_ticket int auto_increment primary key,
    id_vuelo int not null,
    id_pasajero int not null,
    fecha_hora_compra datetime not null,
    numero_asiento varchar(10) not null,
    constraint fk_ticket_vuelo foreign key (id_vuelo)
		references vuelos(id_vuelo) on delete restrict,
	constraint fk_ticket_pasajero foreign key (id_pasajero)
		references pasajeros(id_pasajero) on delete restrict,
	constraint uq_asiento_vuelo unique (id_vuelo, numero_asiento)
);

SHOW TABLES;

use sistema_aerolineas;
insert into aerolineas (nombre) values ('Avianca'), ('LATAM'),('Copa Airlines');
select * from aviones;
