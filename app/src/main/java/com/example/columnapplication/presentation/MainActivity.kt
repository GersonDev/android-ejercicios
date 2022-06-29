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
import com.example.columnapplication.domain.models.Card
import com.example.columnapplication.presentation.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {
    val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nombre by menuViewModel.messageNombre.observeAsState("")
            val apellido by menuViewModel.messageApellido.observeAsState("")
            val dni by menuViewModel.messageDni.observeAsState("")
            val monto by menuViewModel.messageMonto.observeAsState("")
            val listaDeTarjetas by menuViewModel.listaDeRegistro.observeAsState(listOf())
            val navController = rememberNavController()
            NavHost(navController, startDestination = Screen.MenuPrincipal.route) {
                composable(Screen.MenuPrincipal.route) {
                    MenuPrincipalPantalla(
                        {
                            navController.navigate(Screen.Registrar.route)
                        },
                        {
                            menuViewModel.actualizarTarjetas()
                            navController.navigate(Screen.EstadoDeCuenta.route)
                        }
                    )
                }

                composable(Screen.Registrar.route) {
                    RegistroDeDatosPantalla(
                        onClickButtonRegistrar = {
                            menuViewModel.registerPerson()
                            navController.navigate(Screen.MenuPrincipal.route)
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

                composable(Screen.EstadoDeCuenta.route) {
                    VerEstadoDeCuentaPantalla(tarjetas = listaDeTarjetas)
                }
            }
        }
    }
}

@Composable
private fun MenuPrincipalPantalla(onClickButtonOne: () -> Unit, onClickButtonTwo: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.metro1),
            contentDescription = "Logo Linea1"
        )
        Button(onClick = onClickButtonOne, content = {
            Text("Venta de Tarjetas")
        })
        Button(onClick = onClickButtonTwo, content = {
            Text("Ver estado de Cuentas ")
        })
        Button(onClick = {}, content = {
            Text("Escaner tarjetas para ingresar al tren ")
        })
        Button(onClick = {}, content = {
            Text("Recargar Tarjeta ")
        })
    }

}

@Composable
private fun RegistroDeDatosPantalla(
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
                Text("Ingrese Nombre:")
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

@Composable
private fun VerEstadoDeCuentaPantalla(tarjetas: List<Card>) {
    Column {
        tarjetas.forEach { tarjeta ->
            Text("${tarjeta.code} - ${tarjeta.balance}", color = Color.Red)

        }
    }
}

@Preview
@Composable
fun MenuPrincipalPreview() {
    MenuPrincipalPantalla(onClickButtonOne = { /*TODO*/ }, onClickButtonTwo = {})
}

@Preview
@Composable
fun RegistarPreview() {
    RegistroDeDatosPantalla(
        onClickButtonRegistrar = { /*TODO*/ },
        nombre = "",
        apellido = "",
        dni = "",
        monto = "",
        onValueChangeNombre = {},
        onValueChangeApellido = {},
        onValueChangeDni = {},
        onValueChangeMonto = {}
    )
}

@Preview
@Composable
fun EstadoDeCuenta() {
    VerEstadoDeCuentaPantalla(
        tarjetas = listOf(
            Card("A001", 20.00),
            Card("A002", 20.50)

        )
    )
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