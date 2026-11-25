package cl.ucm.villagermanagement.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.ucm.villagermanagement.models.Mundo

@Composable
fun AuthDialogScreen(
    servidor: Mundo,
    onDismiss: () -> Unit,
    onAuthSuccess: () -> Unit
) {
    // Alterna entre vista de Login (true) y Register (false)
    var esLogin by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (esLogin) "Iniciar Sesión (${servidor.ip})" else "Registrarse (${servidor.ip})"
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                if (esLogin) {
                    LoginScreen(onLoginClick = onAuthSuccess)
                } else {
                    RegisterScreen(onRegisterClick = onAuthSuccess)
                }

                Spacer(modifier = Modifier.height(4.dp))

                TextButton(
                    onClick = { esLogin = !esLogin },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (esLogin) "¿No tienes cuenta? Regístrate aquí"
                        else "¿Ya tienes cuenta? Inicia sesión"
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorCampo by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = nickname,
            onValueChange = {
                nickname = it
                errorCampo = false
            },
            label = { Text("Nickname") },
            isError = errorCampo,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorCampo = false
            },
            label = { Text("Contraseña") },
            isError = errorCampo,
            supportingText = {
                if (errorCampo) {
                    Text("Completa ambos campos para continuar")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nickname.isBlank() || password.isBlank()) {
                    errorCampo = true
                } else {
                    onLoginClick()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar al servidor")
        }
    }
}

@Composable
fun RegisterScreen(onRegisterClick: () -> Unit) {
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var errorRegistro by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = nickname,
            onValueChange = {
                nickname = it
                errorRegistro = false
            },
            label = { Text("Nuevo Nickname") },
            isError = errorRegistro,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorRegistro = false
            },
            label = { Text("Contraseña") },
            isError = errorRegistro,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmarPassword,
            onValueChange = {
                confirmarPassword = it
                errorRegistro = false
            },
            label = { Text("Confirmar Contraseña") },
            isError = errorRegistro,
            supportingText = {
                if (errorRegistro) {
                    Text("Verifica que los campos no estén vacíos y coincidan")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nickname.isBlank() || password.isBlank() || password != confirmarPassword) {
                    errorRegistro = true
                } else {
                    onRegisterClick()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta y entrar")
        }
    }
}