#!/bin/bash

# Compilar los archivos Java si es necesario (opcional, solo si no están ya compilados)
javac Ej3_Servidor.java
javac Ej3_Cliente.java

# Valor inicial del parámetro
param=10

# Iterar 7 veces
for i in {1..6}
do
  echo "Iteración $i con parámetro: $param"

for i in {1..10}
do
port=$((8080 + i))
  # Ejecutar el servidor con el puerto 8080 y el valor actual de 'param'
  # echo "Iniciando el servidor..."
  java Ej3_Servidor $port $param &   # El ampersand (&) permite ejecutar el servidor en segundo plano

  # Esperar unos segundos para asegurarse de que el servidor esté listo
  #sleep 2

  # Ejecutar el cliente con la dirección IP 127.0.0.1, puerto 8080, y el valor actual de 'param'
  # echo "Iniciando el cliente..."
  java Ej3_Cliente 127.0.0.1 $port $param

  # Esperar a que el servidor termine
  #wait
  #sleep 2
  done
  # Multiplicar el parámetro por 10 para la siguiente iteración
  param=$((param * 10))
  echo ""
done
