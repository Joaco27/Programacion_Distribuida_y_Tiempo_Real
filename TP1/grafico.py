import matplotlib.pyplot as plt
import numpy as np

# Datos
x = [10, 100, 1000, 10000, 100000]
y = [1.00, 1.40, 1.00, 1.00, 3.00]

# Desviación estándar (asumiendo valores ficticios para las barras de error)
std_dev = [0.00, 0.09, 0.00, 0.00, 0.84]  # Estos valores deben ajustarse según tus datos reales

# Crear el gráfico
plt.figure(figsize=(10, 6))
plt.errorbar(x, y, yerr=std_dev, fmt='-o', capsize=5, capthick=1, ecolor='red', label='Valores con desviación estándar')

# Configuraciones del gráfico
plt.xscale('log')  # Escala logarítmica en el eje X si es necesario
plt.xlabel('Tamaño de Mensaje')
plt.ylabel('Tiempo (ms)')
plt.title('Gráfico de Líneas con Desviación Estándar')
plt.grid(True, linestyle='--', alpha=0.6)
plt.legend()

# Mostrar el gráfico
plt.show()
