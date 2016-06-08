#!/bin/sh
sudo chmod 777 ./config.sh
sudo chmod 777 ./inicio.sh
echo Iniciando configuracion de la aplicacion web
read -p "Nombre de usuario con permisos de administrador de la base de datos:" usuario
mysql -u $usuario -p < iw2016ujob.sql
echo Gracias por utilizar la herramienta
