package com.example.columnapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.columnapplication.R
import com.example.columnapplication.presentation.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {
    val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nombre by menuViewModel.messageNombre.observeAsState("")
            var apellido by remember { mutableStateOf("") }
            var dni by remember { mutableStateOf("") }
            var monto by remember { mutableStateOf("") }
            val navController = rememberNavController()
            NavHost(navController, startDestination = "menuPrincipal") {
                composable("menuPrincipal") {
                    MenuPrincipal({
                        navController.navigate("registroDeDatos")
                    }, "")
                }

                composable("registroDeDatos") {
                    RegistroDeDatos(
                        onClickButtonRegistrar = {
                            menuViewModel.registerPerson()
                            menuViewModel.printStackAndQueue()
                            navController.navigate("menuPrincipal")
                        },
                        nombre = nombre,
                        apellido = apellido,
                        dni = dni,
                        monto = monto,
                        onValueChangeNombre = {
                            menuViewModel.enviarNombre(it)
                        },
                        onValueChangeApellido = {
                            menuViewModel.enviarApellido(it)
                        },
                        onValueChangeDni = {
                            menuViewModel.enviarDni(it)
                        },
                        onValueChangeMonto = {
                            menuViewModel.enviarMonto(it)
                        }
                    )
                }

                composable("three") {
                    Three {
                        navController.navigate("one")
                    }
                }
            }


        }
    }
}

@Composable
private fun MenuPrincipal(onClickButtonOne: () -> Unit, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
         Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.metro1),
            contentDescription = "Logo Linea1"
        )
        Button(onClick = onClickButtonOne, content = {
            Text("Venta de Tarjetas $text")
        })
        Button(onClick = {}, content = {
            Text("Ver estado de Cuentas $text")
        })
        Button(onClick = {}, content = {
            Text("Escaner tarjetas para ingresar al tren $text")
        })
        Button(onClick = {}, content = {
            Text("Recargar Tarjeta $text")
        })
    }

}

@Composable
private fun RegistroDeDatos(
    onClickButtonRegistrar: () -> Unit,
    nombre: String,
    apellido: String,
    dni: String,
    monto: String,
    onValueChangeNombre: (String) -> Unit,
    onValueChangeApellido: (String) -> Unit,
    onValueChangeDni: (String) -> Unit,
    onValueChangeMonto: (String) -> Unit,

    ) {


    Column() {
        TextField(
            value = nombre,
            onValueChange = onValueChangeNombre,
            label = {
                Text( "Ingrese Nombre:")
            }
        )
        TextField(
            value = apellido,
            onValueChange = onValueChangeApellido,
            label = {
                Text("Ingrese Apellido:")
            }
        )
        TextField(
            value = dni,
            onValueChange = onValueChangeDni,
            label = {
                Text("Ingrese Dni:")
            }
        )
        TextField(
            value = monto,
            onValueChange = onValueChangeMonto,
            label = {
                Text("Ingrese Monto:")
            }
        )
        Button(
            onClick = onClickButtonRegistrar,
            content = {
                Text(
                    "Registrar",
                    color = Color.Green
                )
            })
    }
}

fun registrarDatos(nombre: String, apellido: String, dni: String, monto: String) {

}

@Composable
private fun Three(onClickButtonThree: () -> Unit) {
    Button(onClick = onClickButtonThree, content = {
        Text("Carloss Threee", color = Color.Red)
    })
}

@Preview
@Composable
fun MenuPrincipalPreview() {
    MenuPrincipal(onClickButtonOne = { /*TODO*/ }, text = "")
}

@Preview
@Composable
fun RegistarPreview() {
    //RegistroDeDatos {}
}
/*
@Preview
@Composable
fun app() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                painter = painterResource(id = R.drawable.android4),
                contentDescription = "Logo Android"
            )
            Text(
                text = "Gerson",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center

            )
            Text(text = "Cornelio", color = Color.White)
            Text(text = "Vargas", color = Color.White)
        }
    }
}

 */