#!/bin/bash

# IP del servidor
IP_SERVIDOR="163.10.141.215"
PUERTO=8080
LENGTH=10
# Compilar el cliente Java
javac ClienteModificado.java

# Archivo de salida
ARCHIVO_SALIDA="Resultados.txt"
echo "" > $ARCHIVO_SALIDA  # Limpiar el contenido previo del archivo

# Rango de tamaños de mensaje
for i in {1..6}
do
    total_tiempo=0

    echo "Tamaño de mensaje: $LENGTH bytes" >> $ARCHIVO_SALIDA
    
    # Ejecutar 10 veces para calcular el promedio
    for j in {1..10}
    do
        tiempo=$(java ClienteModificado $IP_SERVIDOR $PUERTO $LENGTH)
        echo "Iteración $j: $tiempo ms" >> $ARCHIVO_SALIDA
        total_tiempo=$(awk "BEGIN {print $total_tiempo+$tiempo}")
        PUERTO=$((PUERTO+1))
        sleep 3
    done

    # Calcular el promedio
    promedio=$(awk "BEGIN {printf \"%.2f\", $total_tiempo/10}")
    echo "Promedio de tiempo para $LENGTH bytes: $promedio ms" >> $ARCHIVO_SALIDA
    echo "------------------------------------------" >> $ARCHIVO_SALIDA
    LENGTH=$((LENGTH*10))
done
