package com.example.myapplication15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication15Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "menu",
        modifier = modifier
    ) {
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

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Práctica Android Studio",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Selecciona una opción:",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { navController.navigate("calculator") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Calculadora con Spinner",
                fontSize = 16.sp
            )
        }

        Button(
            onClick = { navController.navigate("countries") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Lista de Países",
                fontSize = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(navController: NavController) {
    var valor1 by remember { mutableStateOf("") }
    var valor2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operacionSeleccionada by remember { mutableStateOf("Sumar") }
    var showMenu by remember { mutableStateOf(false) }

    val operaciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")

    fun calcular() {
        val num1 = valor1.toDoubleOrNull()
        val num2 = valor2.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            resultado = "Por favor ingrese valores válidos."
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
        resultado = "Resultado: $res"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { navController.navigateUp() }) {
                Text("← Volver")
            }
            Text(
                text = "Calculadora",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(48.dp)) // Espaciador para centrar el título
        }

        OutlinedTextField(
            value = valor1,
            onValueChange = { valor1 = it },
            label = { Text("Ingrese primer valor") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = valor2,
            onValueChange = { valor2 = it },
            label = { Text("Ingrese segundo valor") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Componente Spinner (Dropdown)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = operacionSeleccionada,
                onValueChange = { },
                readOnly = true,
                label = { Text("Operación") },
                trailingIcon = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Text("▼")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                operaciones.forEach { operacion ->
                    DropdownMenuItem(
                        text = { Text(operacion) },
                        onClick = {
                            operacionSeleccionada = operacion
                            showMenu = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = { calcular() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "OPERAR", fontSize = 16.sp)
        }

        if (resultado.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = resultado,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(navController: NavController) {
    // Se recomienda usar un Pair o una clase de datos para asociar país y población
    val paisesYHabitantes = listOf(
        Pair("Argentina", 45_000_000L),
        Pair("Chile", 19_000_000L),
        Pair("Paraguay", 7_000_000L),
        Pair("Bolivia", 12_000_000L),
        Pair("Perú", 33_000_000L),
        Pair("Ecuador", 18_000_000L),
        Pair("Brasil", 215_000_000L),
        Pair("Colombia", 51_000_000L),
        Pair("Venezuela", 28_000_000L),
        Pair("Uruguay", 3_500_000L)
    )

    var seleccion by remember { mutableStateOf<Pair<String, Long>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { navController.navigateUp() }) {
                Text("← Volver")
            }
            Text(
                text = "Lista de Países",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(48.dp)) // Espaciador
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la información del país seleccionado
        seleccion?.let { (pais, poblacion) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                val poblacionFormateada = NumberFormat.getInstance(Locale.getDefault()).format(poblacion)
                Text(
                    text = "La población de $pais es $poblacionFormateada habitantes.",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Lista de países
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(paisesYHabitantes.size) { index ->
                val (pais, _) = paisesYHabitantes[index]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { seleccion = paisesYHabitantes[index] }
                ) {
                    Text(
                        text = pais,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

// --- Vistas Previas ---

@Preview(showBackground = true)
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