#!/bin/sh
cd iw2016-uJob
sudo chmod 777 ./config.sh
sudo chmod 777 ./inicio.sh
echo Iniciando configuracion de la aplicacion web
read -p "Nombre de usuario con permisos de administrador de la base de datos:" usuario
read -p "Contrasena del usuario:" contrasenna
mysql -u $usuario -p $contrasenna < iw2016ujob.sql
echo Gracias por utilizar la herramienta
./inicio.sh
