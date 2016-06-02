#!/bin/bash

echo Iniciando configuración de la aplicación web.

read -p "Nombre de usuario con permisos de administrador de la base de datos:" usuario
read -s -p "Contraseña del usuario:" contrasenna

mysql -u $usuario -p $contrasenna < iw2016ujob.sql

echo Gracias por utilizar la herramienta de configuración