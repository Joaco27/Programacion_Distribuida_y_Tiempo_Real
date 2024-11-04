# Abre el archivo y lee sus líneas
with open("Resultados.txt", "r") as file:
    lines = file.readlines()

# Variables para almacenar los datos
clientes_actual = None
tiempos = {}
for line in lines:
    line = line.strip()
    if line.startswith("Cantidad de Clientes:"):
        # Si es una línea de cantidad de clientes, extrae el número
        clientes_actual = int(line.split(": ")[1])
        tiempos[clientes_actual] = []
    elif line.startswith("Tiempo de respuesta es:"):
        # Si es una línea de tiempo de respuesta, extrae el valor
        tiempo = int(line.split(": ")[1].replace(" ms", ""))
        tiempos[clientes_actual].append(tiempo)

# Calcula el promedio de tiempo para cada cantidad de clientes
for clientes, tiempos_list in tiempos.items():
    if tiempos_list:  # Asegurarse de que hay tiempos en la lista
        promedio = sum(tiempos_list) / len(tiempos_list)
        print(f"Cantidad de Clientes: {clientes}, Promedio de Tiempo de Respuesta: {promedio:.2f} ms")
