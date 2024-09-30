#!/bin/bash

# IP del servidor
IP_SERVIDOR="163.10.141.215"
PUERTO="8080"

# Compilar el cliente Java
javac Ej3_Cliente.java

# Archivo de salida
ARCHIVO_SALIDA="TP2ejercicio2"
echo "" > $ARCHIVO_SALIDA  # Limpiar el contenido previo del archivo

# Rango de tamaños de mensaje
for (( i=10; i<=100000; i*=10 ))
do
    total_tiempo=0

    echo "Tamaño de mensaje: $i bytes" >> $ARCHIVO_SALIDA
    
    # Ejecutar 10 veces para calcular el promedio
    for (( j=1; j<=10; j++ ))
    do
        tiempo=$(java Ej3_Cliente $IP_SERVIDOR $PUERTO $i)
        echo "Iteración $j: $tiempo ms" >> $ARCHIVO_SALIDA
        total_tiempo=$(awk "BEGIN {print $total_tiempo+$tiempo}")
        sleep 3
    done

    # Calcular el promedio
    promedio=$(awk "BEGIN {printf \"%.2f\", $total_tiempo/10}")
    echo "Promedio de tiempo para $i bytes: $promedio ms" >> $ARCHIVO_SALIDA
    echo "------------------------------------------" >> $ARCHIVO_SALIDA
done
