# 📱 Calculadora & Países - Android Jetpack Compose

¡Bienvenido a la **Calculadora & Países**! Esta app es una práctica de nivel junior desarrollada en **Kotlin** usando **Jetpack Compose** y Material 3. Ideal para aprender los fundamentos del desarrollo Android moderno con una interfaz atractiva, animaciones y manejo de estado.

---

## ✨ Características principales

- 🧮 **Calculadora con historial:**
  - Suma, resta, multiplicación y división.
  - Menú desplegable para elegir la operación.
  - Historial visual y borrado individual o total.
- 🌎 **Lista interactiva de países:**
  - Países de Sudamérica y República Dominicana con bandera y población.
  - Favoritos con icono de estrella y ordenamiento dinámico.
  - Hora local en tiempo real al seleccionar un país.
- 🎨 **UI moderna y animada:**
  - Animaciones de entrada, escala y pulso.
  - Gradientes y tarjetas decorativas.
  - Compatible con modo claro y oscuro.
- 🧭 **Navegación intuitiva:**
  - Menú principal animado.
  - Navegación entre calculadora y países.

---

## 🖼️ Iconos y visuales

- **Iconos Material:**
  - Se utilizan iconos de Material Design (`material-icons-extended`) para navegación (flecha de volver), favoritos (estrella), historial (borrar), menús desplegables, etc.
  - Los iconos se integran en botones, menús y acciones para mejorar la experiencia visual y la usabilidad.
- **Emojis:**
  - Se usan emojis en títulos, botones y tarjetas para dar un toque amigable y visualmente atractivo.
- **Personalización:**
  - Los colores y estilos de los iconos se adaptan automáticamente al modo claro/oscuro.

---

## 🏗️ Arquitectura y decisiones técnicas

- **Jetpack Compose:** UI declarativa, todo el estado y lógica en composables.
- **Material 3:** Colores adaptativos, tipografía y componentes modernos.
- **Sin MVVM/MVC:** Ideal para prácticas y proyectos pequeños.
- **Animaciones:** Uso de `animateFloatAsState`, `rememberInfiniteTransition`, `AnimatedVisibility` y más.
- **Gestión de favoritos:** Con `mutableStateOf(mutableSetOf())` para máxima compatibilidad.

---

## 📂 Estructura del Proyecto

```
app/
  └── src/
      └── main/
          ├── java/com/example/myapplication15/  # Código fuente principal
          └── res/                              # Recursos de la app
```
- `build.gradle.kts`, `settings.gradle.kts`: Configuración de dependencias y plugins.

---

## ⚙️ Cómo compilar y ejecutar

1. 🌀 Clona este repositorio:
   ```bash
   git clone https://github.com/victortejeda/Calculetorandcountry.git
   ```
2. 🛠️ Abre el proyecto en **Android Studio** (recomendado versión Hedgehog o superior).
3. 🔄 Sincroniza el proyecto con Gradle.
4. ▶️ Ejecuta la app en un emulador o dispositivo físico con Android 12+.

---

## 🖼️ Capturas de pantalla

> Puedes agregar aquí imágenes de la app en modo claro y oscuro, mostrando la calculadora, el menú y la lista de países.

---

## 👥 Créditos y autores

**Desarrollado por:**
- 👨‍💻 Victor Tejeda (owner del repo)

**Práctica sustentada por:**
- 👨‍💻 Henry Castro — 1-21-4112
- 👩‍💻 Lissette Rodríguez — 1-19-3824
- 👨‍💻 Miguel Berroa — 2-16-3694

---

## 💡 Sugerencias para contribuir

¿Quieres mejorar la app? ¡Pull requests y sugerencias son bienvenidas!
- Haz un fork del repo
- Crea una rama con tu mejora
- Haz un PR explicando tu cambio

---

## 📝 Licencia

Este proyecto es solo para fines educativos y de práctica. Puedes usarlo, modificarlo y compartirlo libremente citando a los autores. 