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

@Composable
fun MenuScreen(navController: NavController) {
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
            Text(
                text = "🚀 Práctica Android Studio",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.scale(pulseScale)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "🎯 Selecciona una opción:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("calculator") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "🧮 Calculadora con Historial", fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { navController.navigate("countries") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "🌎 Lista Interactiva de Países", fontWeight = FontWeight.Medium)
            }
        }
    }
}

// Data class para el historial para asegurar una clave única
data class HistoryEntry(val id: UUID = UUID.randomUUID(), val calculation: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(navController: NavController) {
    var valor1 by remember { mutableStateOf("") }
    var valor2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var operacionSeleccionada by remember { mutableStateOf("Sumar") }
    var showMenu by remember { mutableStateOf(false) }
    val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")
    // El historial ahora es una lista de HistoryEntry
    val history = remember { mutableStateListOf<HistoryEntry>() }

    fun getOperatorSymbol(op: String): String = when (op) {
        "Sumar" -> "+"
        "Restar" -> "-"
        "Multiplicar" -> "×"
        "Dividir" -> "÷"
        else -> ""
    }

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
        // Se añade un nuevo objeto HistoryEntry al historial
        history.add(0, HistoryEntry(calculation = historyText))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🧮 Calculadora") },
                navigationIcon = {
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
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = valor1,
                    onValueChange = { valor1 = it.replace(',', '.') },
                    label = { Text("Valor 1") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = valor2,
                    onValueChange = { valor2 = it.replace(',', '.') },
                    label = { Text("Valor 2") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            Box {
                OutlinedTextField(
                    value = operacionSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Operación") },
                    trailingIcon = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(Icons.Default.KeyboardArrowDown, "Abrir menú")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    operaciones.forEach { op ->
                        DropdownMenuItem(
                            text = { Text(op) },
                            onClick = {
                                operacionSeleccionada = op
                                showMenu = false
                            }
                        )
                    }
                }
            }
            Button(
                onClick = { calcular() },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text(text = "🧮 OPERAR", fontWeight = FontWeight.Bold)
            }
            resultado?.let {
                val isError = it.startsWith("Error:")
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isError) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Historial", style = MaterialTheme.typography.titleMedium)
                if (history.isNotEmpty()) {
                    TextButton(onClick = { history.clear() }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Limpiar historial",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text("Limpiar Todo")
                    }
                }
            }
            if (history.isEmpty()) {
                Text(
                    "Aún no hay operaciones.",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    // La clave ahora es el ID único del HistoryEntry, solucionando el crash
                    items(items = history, key = { it.id }) { entry ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(entry.calculation, modifier = Modifier.weight(1f))
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

data class Country(
    val name: String,
    val population: Long,
    val flagEmoji: String,
    val timeZoneId: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(navController: NavController) {
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
    val favorites = remember { mutableStateSetOf<String>() }
    var currentTime by remember { mutableStateOf("") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("hh:mm:ss a") }

    LaunchedEffect(seleccion) {
        if (seleccion != null) {
            val zoneId = ZoneId.of(seleccion!!.timeZoneId)
            while (isActive) {
                currentTime = LocalTime.now(zoneId).format(timeFormatter)
                delay(1000)
            }
        }
    }

    val sortedCountries = countries.sortedWith(
        compareByDescending<Country> { it.name in favorites }
            .thenBy { it.name }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌎 Lista de Países") },
                navigationIcon = {
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
                            Text(
                                text = "${country.flagEmoji} ${country.name}",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Población: ${NumberFormat.getInstance(Locale.getDefault()).format(country.population)}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Hora Local: $currentTime",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = sortedCountries, key = { it.name }) { country ->
                    val isSelected = seleccion?.name == country.name
                    val isFavorite = country.name in favorites
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
                            Text(text = country.flagEmoji, fontSize = 24.sp)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = country.name,
                                modifier = Modifier.weight(1f).padding(vertical = 16.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                            IconButton(
                                onClick = {
                                    if (isFavorite) favorites.remove(country.name) else favorites.add(country.name)
                                }
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                                    contentDescription = "Marcar como favorito",
                                    tint = if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

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