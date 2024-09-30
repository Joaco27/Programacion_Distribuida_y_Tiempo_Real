#!/bin/bash

#Definir el puerto
PUERTO=8080

#Abrir el puerto en iptables (asegúrate de tener permisos para ejecutar iptables)
if ! iptables -L INPUT -n | grep -q "$PUERTO"; then
    echo "Abriendo el puerto $PUERTO..."
    sudo iptables -A INPUT -p tcp --dport $PUERTO -j ACCEPT
    echo "Puerto $PUERTO abierto."
else
    echo "El puerto $PUERTO ya está abierto."
fi

#Compilar el servidor Java
javac Ej3_Servidor.java

#Ejecutar el servidor Java en un bucle
while true; do
    echo "Esperando conexiones en el puerto $PUERTO..."
    java Ej3_Servidor
done