#!/bin/bash

# Compilar los archivos Java si es necesario (opcional, solo si no están ya compilados)
javac Ej2_Servidor.java
javac Ej2_Cliente.java

# Valor inicial del parámetro
param=10

# Iterar 7 veces
for i in {1..6}
do
  echo "Iteración 10^$i"

  port=$((8080 + i))
  java Ej2_Servidor $port $param &   # El ampersand (&) permite ejecutar el servidor en segundo plano

  # Esperar unos segundos para asegurarse de que el servidor esté listo
  sleep 2

  java Ej2_Cliente 127.0.0.1 $port $param

  # Esperar a que el servidor termine
  #wait
  #sleep 2
  param=$((param * 10))
  echo ""
done
