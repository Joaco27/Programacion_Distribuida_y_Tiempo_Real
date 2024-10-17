#!/bin/bash

# Definir IPs
server_ip="192.168.56.100"
client_ip="192.168.56.101"

# vagrant halt
# sleep 10
vagrant up
sleep 10

vagrant ssh servidor -c "cd /vagrant && javac ServidorModificado.java"
vagrant ssh cliente -c "cd /vagrant && javac ClienteModificado.java"

sleep 10

port=8080
length=10
for i in {1..6}
do
    totalTime=0
    echo "Tamaño de Mensaje: $length bytes"
    for j in {1..10}
    do
        vagrant ssh servidor -c "cd /vagrant && java ServidorModificado $port $length" &
        sleep 3
        time=$(vagrant ssh cliente -c "cd /vagrant&& java ClienteModificado $server_ip $port $length")
        sleep 3
        echo "El mensaje $j tardo $time ms"
        totalTime=$((totalTime+time))
        port=$((port+1))
    done
    length=$((length*10))
    echo "El promedio de tiempo es $(awk "BEGIN {printf \"%.2f\", $totalTime/10}") ms"
done
