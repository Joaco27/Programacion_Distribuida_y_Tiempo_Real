import numpy as np

# Datos simulados (ejemplo)
# Cada sublista representa diferentes mediciones para un valor de X
muestras = {
    10: [1.00, 1.00, 1.00, 1.00, 1.00],
    100: [1.00, 1.00, 1.00, 1.20, 1.00],
    1000: [1.00, 1.00, 1.00, 1.00, 1.00],
    10000: [1.00, 1.00, 1.00, 1.00, 1.00],
    100000: [4.00, 2.00, 4.00, 3.00, 3.00]
}

# Calcular la desviación estándar para cada conjunto de muestras
desviaciones = {key: np.std(val, ddof=1) for key, val in muestras.items()}

# Mostrar los resultados
for key, std in desviaciones.items():
    print(f"Valor: {key}, Desviación Estándar: {std:.2f}")
