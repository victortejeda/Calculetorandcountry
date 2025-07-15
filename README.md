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

> Puedes agr<img width="455" height="774" alt="Captura de pantalla 2025-07-15 a las 12 14 32 a  m" src="https://github.com/user-attachments/assets/dd04c917-a811-458c-8c9e-760196cdfa4f" />
egar aquí imágenes de la app en modo claro y oscuro, mostrando la calculadora, el menú y la lista de países.
<img width="393" height="779" alt="Captura de pantalla 2025-07-15 a las 12 14 48 a  m" src="https://github.com/user-attachments/assets/dc0cd7e2-2e7a-49d3-a896-e4132f367f52" />
<img width="396" height="795" alt="Captura de pantalla 2025-07-15 a las 12 15 05 a  m" src="https://githu<img width="429" height="782" alt="Captura de pantalla 2025-07-15 a las 12 15 48 a  m" src="https://github.com/user-attachments/assets/c5b4d600-5e4b-4e4e-a3fd-2897dacb1092" /><img width="471" height="793" alt="Captura de pantalla 2025-07-15 a las 12 15 58 a  m" src="https://github.com/user-attachments/assets/2ed5307e-4f5e-440d-b840-20177d0fdba7" /><img width="432" height="792" alt="Captura de pantalla 2025-07-15 a las 12 16 10 a  m" src="https://github.com/user-attachments/assets/9a64d0fe-c2d0-4308-8bec-0aa5641731ea" />


b.com/user-attachments/assets/6abd24a0-60e8-4448-a230-53fd36dc8a20" />

---

## 👥 Créditos y autores

**Desarrollado por:**
- 👨‍💻 Victor Tejeda (owner del repo)



## 💡 Sugerencias para contribuir

¿Quieres mejorar la app? ¡Pull requests y sugerencias son bienvenidas!
- Haz un fork del repo
- Crea una rama con tu mejora
- Haz un PR explicando tu cambio

---

## 📝 Licencia

Este proyecto es solo para fines educativos y de práctica. Puedes usarlo, modificarlo y compartirlo libremente citando a los autores. 
