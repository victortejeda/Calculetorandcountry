package com.example.myapplication15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication15Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(navController)
        }
        composable("calculator") {
            CalculatorScreen(navController)
        }
        composable("countries") {
            CountriesScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    var animationTrigger by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(300)
        animationTrigger = true
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "🧮 Calculadora & Países",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = animationTrigger,
                enter = fadeIn(animationSpec = tween(800)) + slideInVertically(
                    initialOffsetY = { -100 },
                    animationSpec = tween(800)
                ),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(
                    text = "¡Bienvenido!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            AnimatedVisibility(
                visible = animationTrigger,
                enter = fadeIn(animationSpec = tween(800, delayMillis = 200)) + slideInVertically(
                    initialOffsetY = { 100 },
                    animationSpec = tween(800, delayMillis = 200)
                ),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Button(
                    onClick = { navController.navigate("calculator") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "🧮 Calculadora",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AnimatedVisibility(
                visible = animationTrigger,
                enter = fadeIn(animationSpec = tween(800, delayMillis = 400)) + slideInVertically(
                    initialOffsetY = { 100 },
                    animationSpec = tween(800, delayMillis = 400)
                ),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Button(
                    onClick = { navController.navigate("countries") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "🌎 Países",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(navController: NavController) {
    var display by remember { mutableStateOf("0") }
    var operation by remember { mutableStateOf("") }
    var firstNumber by remember { mutableStateOf(0.0) }
    var newNumber by remember { mutableStateOf(true) }
    var history by remember { mutableStateOf(listOf<String>()) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🧮 Calculadora") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Display
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = display,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            // History
            if (history.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(history.takeLast(3)) { item ->
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }
            
            // Buttons
            val buttonSpacing = 8.dp
            val buttonSize = 70.dp
            
            // Row 1: Clear, +/-, %, ÷
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    onClick = {
                        display = "0"
                        operation = ""
                        firstNumber = 0.0
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    shape = CircleShape
                ) {
                    Text("C", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (display != "0") {
                            display = if (display.startsWith("-")) display.substring(1) else "-$display"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = CircleShape
                ) {
                    Text("+/-", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        val number = display.toDoubleOrNull() ?: 0.0
                        display = (number / 100).toString()
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = CircleShape
                ) {
                    Text("%", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        operation = "÷"
                        firstNumber = display.toDoubleOrNull() ?: 0.0
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = CircleShape
                ) {
                    Text("÷", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(buttonSpacing))
            
            // Row 2: 7, 8, 9, ×
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "7"
                            newNumber = false
                        } else {
                            display += "7"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("7", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "8"
                            newNumber = false
                        } else {
                            display += "8"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("8", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "9"
                            newNumber = false
                        } else {
                            display += "9"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("9", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        operation = "×"
                        firstNumber = display.toDoubleOrNull() ?: 0.0
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = CircleShape
                ) {
                    Text("×", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(buttonSpacing))
            
            // Row 3: 4, 5, 6, -
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "4"
                            newNumber = false
                        } else {
                            display += "4"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("4", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "5"
                            newNumber = false
                        } else {
                            display += "5"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("5", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "6"
                            newNumber = false
                        } else {
                            display += "6"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("6", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        operation = "-"
                        firstNumber = display.toDoubleOrNull() ?: 0.0
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = CircleShape
                ) {
                    Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(buttonSpacing))
            
            // Row 4: 1, 2, 3, +
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "1"
                            newNumber = false
                        } else {
                            display += "1"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "2"
                            newNumber = false
                        } else {
                            display += "2"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("2", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "3"
                            newNumber = false
                        } else {
                            display += "3"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text("3", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        operation = "+"
                        firstNumber = display.toDoubleOrNull() ?: 0.0
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = CircleShape
                ) {
                    Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(buttonSpacing))
            
            // Row 5: 0, ., =
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    onClick = {
                        if (newNumber) {
                            display = "0"
                            newNumber = false
                        } else {
                            display += "0"
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(2f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(35.dp)
                ) {
                    Text("0", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        if (!display.contains(".")) {
                            display += "."
                            newNumber = false
                        }
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = CircleShape
                ) {
                    Text(".", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = {
                        val secondNumber = display.toDoubleOrNull() ?: 0.0
                        val result = when (operation) {
                            "+" -> firstNumber + secondNumber
                            "-" -> firstNumber - secondNumber
                            "×" -> firstNumber * secondNumber
                            "÷" -> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
                            else -> secondNumber
                        }
                        
                        val operationSymbol = when (operation) {
                            "+" -> "+"
                            "-" -> "-"
                            "×" -> "×"
                            "÷" -> "÷"
                            else -> ""
                        }
                        
                        if (operation.isNotEmpty()) {
                            val historyEntry = "${firstNumber} $operationSymbol $secondNumber = $result"
                            history = history + historyEntry
                        }
                        
                        display = result.toString()
                        operation = ""
                        firstNumber = result
                        newNumber = true
                    },
                    modifier = Modifier
                        .size(buttonSize)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = CircleShape
                ) {
                    Text("=", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
    val favorites = remember { mutableStateOf(mutableSetOf<String>()) }
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
        compareByDescending<Country> { if (favorites.value.contains(it.name)) 1 else 0 }
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
                                    if (isFavorite) favorites.value.remove(country.name)
                                    else favorites.value.add(country.name)
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