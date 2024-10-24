#!/bin/bash

PUERTO=8080
LENGTH=10
javac ServidorModificado.java

for i in {1..6}
do
    for j in {1..10}
    do
        # echo "Abriendo puerto $PUERTO..."

        # Abrir puerto en el firewall de Windows usando PowerShell
        # powershell.exe -Command "New-NetFirewallRule -DisplayName 'Abrir Puerto $PUERTO' -Direction Inbound -LocalPort $PUERTO -Protocol TCP -Action Allow"

        # Compilar y ejecutar el servidor Java
        echo "Ejecutando el servidor en el puerto $PUERTO..."
        java ServidorModificado $PUERTO $LENGTH

        # Cerrar el puerto después de que el servidor haya terminado
        # echo "Cerrando el puerto $PUERTO..."
        # powershell.exe -Command "Remove-NetFirewallRule -DisplayName 'Abrir Puerto $PUERTO'"


        # Incrementar el puerto
        PUERTO=$((PUERTO+1))
        
    done
    LENGTH=$((LENGTH*10))
done

