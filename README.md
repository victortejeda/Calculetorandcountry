# 📱 Calculadora Android

¡Bienvenido! Este proyecto es una aplicación de calculadora para Android desarrollada en **Kotlin** usando **Jetpack Compose** para la interfaz de usuario. 🚀

---

## 🏆 Nivel y propósito

> 🧑‍💻 **Práctica de nivel junior**
>
> Ideal para quienes están aprendiendo desarrollo Android moderno. Permite familiarizarse con conceptos básicos de UI, navegación y manejo de estado en Compose.

---

## ✨ Detalles del proyecto

- 🧮 **Calculadora:** Realiza operaciones básicas (➕ suma, ➖ resta, ✖️ multiplicación, ➗ división) entre dos números, seleccionando la operación mediante un menú desplegable.
- 🌎 **Lista de países:** Muestra una lista de países sudamericanos y su población. Al seleccionar un país, se muestra su población.
- 🧭 **Navegación:** Menú principal para navegar entre la calculadora y la lista de países.
- 🎨 **Interfaz:** Sencilla, intuitiva y adaptada a Material 3.

---

## 🏗️ Patrón de arquitectura

El proyecto utiliza **Jetpack Compose** para construir la interfaz de usuario de forma declarativa. No implementa un patrón arquitectónico complejo como MVVM o MVC. Toda la lógica y el estado se gestionan dentro de los mismos componentes `@Composable`, lo cual es común en proyectos pequeños o de práctica.

- ✅ **Ventajas:**
  - Fácil de entender para principiantes.
  - Menos archivos y clases, ideal para prácticas rápidas.
- ⚠️ **Limitaciones:**
  - No es escalable para proyectos grandes.
  - La lógica y la UI están acopladas en los mismos archivos.

---

## 📂 Estructura del Proyecto

```
app/
  └── src/
      └── main/
          ├── java/com/example/myapplication15/  # Código fuente principal
          └── res/                              # Recursos de la app
```
- `build.gradle.kts`, `settings.gradle.kts`: Archivos de configuración de Gradle.

---

## ⚙️ Cómo compilar y ejecutar

1. 🌀 Clona este repositorio.
2. 🛠️ Abre el proyecto en Android Studio.
3. 🔄 Sincroniza el proyecto con Gradle.
4. ▶️ Ejecuta la aplicación en un emulador o dispositivo físico.

---

## 👥 Créditos

Desarrollado por Victor Tejeda.

Práctica para estudiantes de desarrollo Android junior. 