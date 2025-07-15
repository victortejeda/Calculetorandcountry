// MainActivity.kt
// Calculadora & Países - App de práctica Android Jetpack Compose
// Documentación completa en cada función y bloque importante

package com.example.myapplication15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication15.ui.theme.MyApplication15Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.text.NumberFormat
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

/**
 * Actividad principal de la app. Inicializa el tema y la navegación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication15Theme {
                AppNavigation()
            }
        }
    }
}

/**
 * Controlador de navegación principal de la app.
 * Define las rutas: menú, calculadora y países.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") { MenuScreen(navController) }
        composable("calculator") { CalculatorScreen(navController) }
        composable("countries") { CountriesScreen(navController) }
    }
}

/**
 * Pantalla de menú principal con animaciones, créditos y navegación.
 * Incluye animación de pulso, gradiente de fondo y botones con iconos/emojis.
 */
@Composable
fun MenuScreen(navController: NavController) {
    // Animación de entrada y pulso
    var startAnimation by remember { mutableStateOf(false) }
    val scaleIn by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(durationMillis = 500),
        label = "scaleIn"
    )
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(animation = tween(1000), repeatMode = RepeatMode.Reverse),
        label = "pulse"
    )
    LaunchedEffect(Unit) { startAnimation = true }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
                .padding(24.dp)
                .scale(scaleIn)
                .alpha(scaleIn),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título animado
            Text(
                text = "🚀 Práctica Android Studio",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.scale(pulseScale)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            // Tarjeta de créditos animada
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scaleIn),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "👥 Sustentado por:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "👨‍💻 Henry Castro\n1-21-4112",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "👩‍💻 Lissette Rodríguez\n1-19-3824",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "👨‍💻 Miguel Berroa\n2-16-3694",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            // Subtítulo
            Text(
                text = "🎯 Selecciona una opción:",
                style = MaterialTheme.typography.titleMedium,
                alpha = 0.8f
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            // Botón para calculadora
            Button(
                onClick = { navController.navigate("calculator") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .scale(scaleIn),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "🧮 Calculadora con Historial", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            // Botón para países
            Button(
                onClick = { navController.navigate("countries") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .scale(scaleIn),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "🌎 Lista Interactiva de Países", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            
            // Elementos decorativos sutiles (puntos animados)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .scale(pulseScale * (0.8f + index * 0.1f))
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                    )
                }
            }
        }
    }
}

/**
 * Data class para el historial de la calculadora.
 * Se usa un UUID para evitar problemas de claves duplicadas en listas.
 */
data class HistoryEntry(val id: UUID = UUID.randomUUID(), val calculation: String)

/**
 * Pantalla de calculadora con historial, operaciones y manejo de errores.
 * Incluye iconos para borrar historial y operaciones, y animaciones de Material 3.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(navController: NavController) {
    // Estado de los campos y el historial
    var valor1 by remember { mutableStateOf("") }
    var valor2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var operacionSeleccionada by remember { mutableStateOf("Sumar") }
    var showMenu by remember { mutableStateOf(false) }
    val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")
    val history = remember { mutableStateListOf<HistoryEntry>() }

    // Devuelve el símbolo de la operación
    fun getOperatorSymbol(op: String): String = when (op) {
        "Sumar" -> "+"
        "Restar" -> "-"
        "Multiplicar" -> "×"
        "Dividir" -> "÷"
        else -> ""
    }

    // Realiza el cálculo y actualiza el historial
    fun calcular() {
        val num1 = valor1.toDoubleOrNull()
        val num2 = valor2.toDoubleOrNull()
        if (num1 == null || num2 == null) {
            resultado = "Error: Ingrese valores numéricos válidos."
            return
        }
        val res = when (operacionSeleccionada) {
            "Sumar" -> num1 + num2
            "Restar" -> num1 - num2
            "Multiplicar" -> num1 * num2
            "Dividir" -> {
                if (num2 == 0.0) {
                    resultado = "Error: No se puede dividir por cero."
                    return
                }
                num1 / num2
            }
            else -> 0.0
        }
        val formattedResult = NumberFormat.getInstance().format(res)
        resultado = "Resultado: $formattedResult"
        val historyText =
            "${NumberFormat.getInstance().format(num1)} ${getOperatorSymbol(operacionSeleccionada)} ${
                NumberFormat.getInstance().format(num2)
            } = $formattedResult"
        history.add(0, HistoryEntry(calculation = historyText))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🧮 Calculadora") },
                navigationIcon = {
                    // Icono de volver (Material Icon)
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Campos de entrada
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = valor1, onValueChange = { valor1 = it.replace(',', '.') }, label = { Text("Valor 1") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f), singleLine = true)
                OutlinedTextField(value = valor2, onValueChange = { valor2 = it.replace(',', '.') }, label = { Text("Valor 2") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f), singleLine = true)
            }
            // Menú desplegable para seleccionar operación
            Box {
                OutlinedTextField(value = operacionSeleccionada, onValueChange = {}, readOnly = true, label = { Text("Operación") }, trailingIcon = { IconButton(onClick = { showMenu = !showMenu }) { Icon(Icons.Default.KeyboardArrowDown, "Abrir menú") } }, modifier = Modifier.fillMaxWidth())
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }, modifier = Modifier.fillMaxWidth()) {
                    operaciones.forEach { op -> DropdownMenuItem(text = { Text(op) }, onClick = { operacionSeleccionada = op; showMenu = false }) }
                }
            }
            // Botón de operar
            Button(onClick = { calcular() }, modifier = Modifier.fillMaxWidth().height(50.dp)) { Text(text = "🧮 OPERAR", fontWeight = FontWeight.Bold) }
            // Resultado
            resultado?.let {
                val isError = it.startsWith("Error:")
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer)) {
                    Text(text = it, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium, color = if (isError) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Historial de operaciones
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Historial", style = MaterialTheme.typography.titleMedium)
                if (history.isNotEmpty()) {
                    // Botón para limpiar historial (icono de borrar)
                    TextButton(onClick = { history.clear() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Limpiar historial", modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.size(4.dp))
                        Text("Limpiar Todo")
                    }
                }
            }
            if (history.isEmpty()) {
                Text("Aún no hay operaciones.", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium)
            } else {
                // Card con historial y scroll
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(items = history, key = { it.id }) { entry ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    entry.calculation, 
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                // Botón para borrar entrada individual (icono de borrar)
                                IconButton(onClick = { history.remove(entry) }) {
                                    Icon(
                                        Icons.Default.Delete, 
                                        contentDescription = "Borrar entrada", 
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Data class para países, usada en la pantalla de países.
 */
data class Country(
    val name: String,
    val population: Long,
    val flagEmoji: String,
    val timeZoneId: String
)

/**
 * Pantalla de países con favoritos, animaciones y hora local.
 * Incluye iconos de estrella para favoritos y navegación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(navController: NavController) {
    // Lista de países hardcodeada
    val countries = remember {
        listOf(
            Country("Argentina", 45_810_000L, "🇦🇷", "America/Argentina/Buenos_Aires"),
            Country("Bolivia", 12_080_000L, "🇧🇴", "America/La_Paz"),
            Country("Brasil", 215_300_000L, "🇧🇷", "America/Sao_Paulo"),
            Country("Chile", 19_490_000L, "🇨🇱", "America/Santiago"),
            Country("Colombia", 51_520_000L, "🇨🇴", "America/Bogota"),
            Country("Ecuador", 17_800_000L, "🇪🇨", "America/Guayaquil"),
            Country("Paraguay", 7_359_000L, "🇵🇾", "America/Asuncion"),
            Country("Perú", 33_720_000L, "🇵🇪", "America/Lima"),
            Country("Uruguay", 3_426_000L, "🇺🇾", "America/Montevideo"),
            Country("Venezuela", 28_200_000L, "🇻🇪", "America/Caracas"),
            Country("Rep. Dominicana", 11_120_000L, "🇩🇴", "America/Santo_Domingo")
        )
    }

    var seleccion by remember { mutableStateOf<Country?>(null) }
    // Favoritos gestionados con mutableStateOf(mutableSetOf())
    val favorites = remember { mutableStateOf(mutableSetOf<String>()) }
    var currentTime by remember { mutableStateOf("") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("hh:mm:ss a") }

    // Efecto para actualizar la hora local del país seleccionado
    LaunchedEffect(seleccion) {
        if (seleccion != null) {
            val zoneId = ZoneId.of(seleccion!!.timeZoneId)
            while (isActive) {
                currentTime = LocalTime.now(zoneId).format(timeFormatter)
                delay(1000)
            }
        }
    }

    // Ordena países: favoritos primero, luego alfabético
    val sortedCountries = countries.sortedWith(
        compareByDescending<Country> { if (favorites.value.contains(it.name)) 1 else 0 }
            .thenBy { it.name }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌎 Lista de Países") },
                navigationIcon = {
                    // Icono de volver (Material Icon)
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Card animada con detalles del país seleccionado
            AnimatedVisibility(
                visible = seleccion != null,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
            ) {
                seleccion?.let { country ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${country.flagEmoji} ${country.name}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onTertiaryContainer)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Población: ${NumberFormat.getInstance(Locale.getDefault()).format(country.population)}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onTertiaryContainer)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Hora Local: $currentTime", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onTertiaryContainer)
                        }
                    }
                }
            }
            // Lista de países con favoritos
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = sortedCountries, key = { it.name }) { country ->
                    val isSelected = seleccion?.name == country.name
                    val isFavorite = favorites.value.contains(country.name)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { seleccion = if (isSelected) null else country },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Emoji de bandera
                            Text(text = country.flagEmoji, fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(16.dp))
                            // Nombre del país
                            Text(text = country.name, modifier = Modifier.weight(1f).padding(vertical = 16.dp), style = MaterialTheme.typography.bodyLarge, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                            // Icono de favorito (estrella)
                            IconButton(onClick = { if (isFavorite) favorites.value.remove(country.name) else favorites.value.add(country.name) }) {
                                Icon(imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder, contentDescription = "Marcar como favorito", tint = if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Previews para desarrollo visual
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    MyApplication15Theme {
        MenuScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    MyApplication15Theme {
        CalculatorScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun CountriesScreenPreview() {
    MyApplication15Theme {
        CountriesScreen(rememberNavController())
    }
}