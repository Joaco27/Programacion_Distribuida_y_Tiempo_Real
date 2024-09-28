#!/bin/bash
javac Ej3_Servidor.java
javac Ej3_Cliente.java

param=10
port=8080
tiempos=(0 0 0 0 0)

for i in {1..5}
do
  desviaciones=()
  echo "Iteración 10^$i"
  for j in {1..20}
  do
    java Ej3_Servidor $port $param &   # El ampersand (&) permite ejecutar el servidor en segundo plano

    # Esperar unos segundos para asegurarse de que el servidor esté listo
    sleep 3
    
    tiempoMsj=$(java Ej3_Cliente 127.0.0.1 $port $param)
    echo "Tiempo en ms: $tiempoMsj"
    tiempos[$((i-1))]=$((tiempos[i-1] + tiempoMsj))
    desviaciones+=($tiempoMsj)
    # Esperar a que el servidor termine
    #wait
    #sleep 2
    port=$((port + 1))
  done
  param=$((param * 10))
  

echo "Promedio de tiempo para 10^$i: $(awk "BEGIN {printf \"%.2f\", ${tiempos[i-1]} / 20}") ms"

  desv=0
  for k in "${desviaciones[@]}"; do
    desv=$((desv + ((k - tiempos[i-1]/20)*(k - tiempos[i-1]/20))))
  done
  echo | awk -v n="$desv" '{ print "La desviación estándar es: " sqrt(n/19) }'
  echo ""
done

it=1
for t in "${tiempos[@]}"; do
echo "Promedio de tiempo para 10^$it: $(awk "BEGIN {printf \"%.2f\", $t / 20}") ms"
  it=$((it + 1))
done 
