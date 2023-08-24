package com.quantumsoft.recycleapp.ui.theme.Login.ui

import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.quantumsoft.recycleapp.ui.theme.DarkGreen
import com.quantumsoft.recycleapp.ui.theme.Purple40
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape


@ExperimentalMaterial3Api
@Composable

fun vision() {
    LoginScreen(viewModel = LoginViewModel())
}

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    Box(
        Modifier
            .fillMaxSize()
            .background(DarkGreen)
            .padding(40.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@ExperimentalMaterial3Api
@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        TitleLogin("Inicio de Sesión")
        HighlightedText("Inicia sesión con tu cuenta de ", "BioTec")
        SubtitleLogin("Email")
        EmailField(email) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(4.dp))
        SubtitleLogin("Contraseña")
        PasswordField(password) { viewModel.onLoginChanged(email, it) }
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(loginEnable) {
            coroutineScope.launch {
                viewModel.onLoginSelected()
            }
        }
        HighlightedText("Si no tienes una cuenta ", "Registrate")
    }
}

@Composable
fun TitleLogin(title: String) {
    Text(
        text = title,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFFFFFF)
    )
}

@ExperimentalMaterial3Api
@Composable
fun HighlightedText(normal: String, resaltado: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append(normal)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            )
        ) {
            append(resaltado)
        }
    }
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.White,
    )

}

@ExperimentalMaterial3Api
@Composable
fun SubtitleLogin(str: String) {
    Text(
        text = str,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.White,
    )

}


@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF4303),
            disabledContainerColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF9BFBFB)
    )
}

@ExperimentalMaterial3Api
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            containerColor = Color(0xFFEEEEEE),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .border(
                width = 2.dp,            // Grosor del borde
                color = Color(0xFF000000), // Color del borde
                shape = RoundedCornerShape(10.dp)  // Esquinas redondeadas
            )
            .fillMaxWidth()
    )
}

@ExperimentalMaterial3Api
@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = {Text(text = "tu_email@gmail.com")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            containerColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .border(
                width = 2.dp,            // Grosor del borde
                color = Color(0xFF000000), // Color del borde
                shape = RoundedCornerShape(10.dp)  // Esquinas redondeadas
            )
            .fillMaxWidth()
    )

}


