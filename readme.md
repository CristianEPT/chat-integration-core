# Chat Integration Core (Telegram Bot)

## Descripción

Este repositorio contiene la prueba tecnica para Hunty desarrollada
por Cristian Piña. Acá se explicará como configurara y ejecuta
el artefacto.
Para el desarrollo de este artefacto utilicé las siguientes tecnologias
Java 17, Spring Boot 3, MongoDb, Gradle; Telegram Bot. Por otro lado, a nivel
de arquitectura de aplicación hice uso de arquitectura hexagonal
o también conocida como arquitectura de puertos y adaptadores.
Hice uso de una base de datos no relación como MongoDb, ya que
por su flexibilidad, gestión, velocidad, soporte y disponibilidad se
alineaba a los requerimientos solicitados.

## Uso

###  1. Requisitos:

Se requiere instalar o configurar las siguientes tecnologías.
* Docker
* JDK 17
* Gradle
* Postman

### 2. Estructura de carpetas

La siguiente es la estructura de carpetas utilizada en este artefacto:
```
chat-integration-core/     					# Directorio raiz.
|- build/ 							# Carpeta con el proyecto compilado.
|- src/          						# Codigo productivo de la aplicación.
	|- main
		|- resources
			|- application.yml 			# Configuraciones de la aplicación.
|- scripts/      						# Script de  base de datos.
|- dockercompose.yml  						# Configuración de la base de datos.
|- build.grtadle      						# Archivo de dependencias.
```
### 3. Ejecutar base de datos

* desde un terminal ubicarse en el directorio raíz del proyecto
```
cd ~/Documentos/chat-integration-core
```
* Ejecutar el docker-compose
```
docker-compose up -d
```
### 4. Compilar y ejecutar el proyecto

* desde un terminal ubicarse en el directorio raíz del proyecto
 ```
cd ~/Documentos/chat-integration-core
```
* ejecutar el limpiador y compilador del proyecto; esto generará un archivo
  .jar que será el proyecto compilado
```
gradle clean build
```
* ejecutar el proyecto
```
java -jar chat-integration-core.jar
```

### 5. Probar (Enviar mensajes a Telegram Bot)

* Buscar en telegram el bot llamado "HuntyBot"
* Dar click en el boton "start"
* Enviar mensajes

Nota: Todos los mensajes que se envíen desde el chat hacia el bot
quedaran almacenados en base de datos.

### 6. Probar API's
* Se desarrollaron 4 endpoint
    * Primero: consultar todos los chats
    * Segundo: consultar los mensajes por cada chat
    * Tercero: enviar mensajes al chat
    * Cuarto: enviar documentos
* Se desarrollo un swagger para ver la documentación de las API's. [Click Aqui](./swagger.yml)