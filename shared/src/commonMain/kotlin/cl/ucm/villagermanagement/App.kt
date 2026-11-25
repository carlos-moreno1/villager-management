package cl.ucm.villagermanagement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.ucm.villagermanagement.models.Multiplayer
import cl.ucm.villagermanagement.models.Mundo
import cl.ucm.villagermanagement.models.Singleplayer
import cl.ucm.villagermanagement.models.Usuario
import cl.ucm.villagermanagement.screens.AldeanosScreen
import cl.ucm.villagermanagement.screens.ConfigScreen
import cl.ucm.villagermanagement.screens.HomeScreen
import cl.ucm.villagermanagement.screens.InicioScreen
import cl.ucm.villagermanagement.screens.UserScreen

@Composable
fun App() {
    val navController = rememberNavController()

    // Lista de servidores/mundos de ejemplo basados en tus modelos
    val servidores by remember {
        mutableStateOf(
            listOf<Mundo>(
                Multiplayer(ip = "play.ucmcraft.cl", owner = Usuario("AdminUCM", "1234")),
                Multiplayer(ip = "mc.survival.net", owner = Usuario("Steve", "1234")),
                Singleplayer(owner = Usuario("JugadorLocal", "1234"))
            )
        )
    }

    var servidorSeleccionado by remember { mutableStateOf<Mundo?>(null) }

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        // 1. Pantalla inicial con lista de servidores y modal de Login/Register
        composable("inicio") {
            InicioScreen(
                servidores = servidores,
                onIngresoExitoso = { mundo ->
                    servidorSeleccionado = mundo
                    navController.navigate("panel_principal")
                }
            )
        }

        // 2. Contenedor con barra lateral para Home, Aldeanos, Usuarios y Configuración
        composable("panel_principal") {
            PanelNavegacionLateral(
                servidor = servidorSeleccionado,
                onSalirServidor = {
                    servidorSeleccionado = null
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun PanelNavegacionLateral(
    servidor: Mundo?,
    onSalirServidor: () -> Unit
) {
    val innerNavController = rememberNavController()
    var rutaActual by remember { mutableStateOf("home") }

    Scaffold { innerPadding ->
        Row(modifier = Modifier.fillMaxSize()) {
            // Barra lateral (Rail, como se muestra en la estructura de Scaffold del PDF)
            NavigationRail(modifier = Modifier.fillMaxHeight()) {
                NavigationRailItem(
                    selected = rutaActual == "home",
                    onClick = {
                        rutaActual = "home"
                        innerNavController.navigate("home")
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationRailItem(
                    selected = rutaActual == "aldeanos",
                    onClick = {
                        rutaActual = "aldeanos"
                        innerNavController.navigate("aldeanos")
                    },
                    icon = { Icon(Icons.Default.Face, contentDescription = "Aldeanos") },
                    label = { Text("Aldeanos") }
                )
                NavigationRailItem(
                    selected = rutaActual == "usuarios",
                    onClick = {
                        rutaActual = "usuarios"
                        innerNavController.navigate("usuarios")
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuarios") },
                    label = { Text("Usuarios") }
                )
                NavigationRailItem(
                    selected = rutaActual == "config",
                    onClick = {
                        rutaActual = "config"
                        innerNavController.navigate("config")
                    },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Configuración") },
                    label = { Text("Config") }
                )
            }

            // Grafo de navegación interior para las 4 pantallas
            NavHost(
                navController = innerNavController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("home") {
                    HomeScreen(
                        innerPadding = innerPadding,
                        servidor = servidor,
                        onSalir = onSalirServidor
                    )
                }
                composable("aldeanos") {
                    AldeanosScreen(innerPadding = innerPadding)
                }
                composable("usuarios") {
                    UserScreen(innerPadding = innerPadding)
                }
                composable("config") {
                    ConfigScreen(innerPadding = innerPadding)
                }
            }
        }
    }
}