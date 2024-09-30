#!/bin/bash

# Puerto en el que escuchará el servidor
PUERTO=8080

# Abrir el puerto en iptables (requiere permisos de superusuario)
if ! sudo iptables -L INPUT -n | grep -q ":$PUERTO"; then
    echo "Abriendo el puerto $PUERTO..."
    sudo iptables -A INPUT -p tcp --dport $PUERTO -j ACCEPT
    echo "Puerto $PUERTO abierto."
else
    echo "El puerto $PUERTO ya está abierto."
fi

# Compilar el servidor Java
javac Ej3_Servidor.java

# Ejecutar el servidor en un bucle para aceptar múltiples conexiones
while true; do
    echo "Esperando conexiones en el puerto $PUERTO..."
    java Ej3_Servidor
done
