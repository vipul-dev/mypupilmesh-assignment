package com.vipul.dev.mypupilmesh.presentation.ui.screens.signIn

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vipul.dev.mypupilmesh.R
import com.vipul.dev.mypupilmesh.presentation.navigation.AuthDest
import com.vipul.dev.mypupilmesh.presentation.navigation.DashboardDest

@Composable
fun SignInScreen(navController: NavController?, viewModel: SignInViewModel = hiltViewModel()) {

    val state = viewModel.uiState
    val context = LocalContext.current

    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))

        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Zenithra",
                    fontSize = 20.sp,
                    color = Color.White,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Welcome back",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text("Please enter your details to sign in", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    IconButton(onClick = {
                        Toast.makeText(context, "Work on progress", Toast.LENGTH_SHORT).show()
                    }) {

                        Icon(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "Google",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    IconButton(onClick = {
                        Toast.makeText(context, "Work on progress", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.apple),
                            contentDescription = "Apple",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(12.dp))

                Text("OR", color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { newTxt ->
                        viewModel.onEmailChange(newTxt)
                        viewModel.resetError()
                    },
                    label = { Text("Email") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.LightGray,
                        disabledLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = { newTxt ->
                        viewModel.onPasswordChange(newTxt)
                        viewModel.resetError()
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (passwordVisible) painterResource(R.drawable.ic_visibility) else painterResource(
                                R.drawable.ic_visibility_off
                            )
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = icon, contentDescription = "Toggle button"
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                    ),
                    label = { Text("Password") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.LightGray,
                        disabledLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (state.error != null) {
                    Text(text = state.error ?: "", color = Color.Red)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    "Forgot password?",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = viewModel::signIn,
                    enabled = !state.isLoading
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp), color = Color.LightGray
                        )
                    } else {
                        Text("Sign In")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                val annotatedText = buildAnnotatedString {
                    append("Don't have an account? ")

                    //Adding clickable span
                    pushStringAnnotation(tag = "SIGN_UP", annotation = "sign_up")

                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Sign Up")
                    }
                    pop()
                }

                var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

                Text(
                    text = annotatedText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    onTextLayout = { result -> textLayoutResult = result },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable(false) {}
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                textLayoutResult?.let { layout ->
                                    val position = layout.getOffsetForPosition(offset)
                                    annotatedText.getStringAnnotations(
                                        start = position, end = position
                                    ).firstOrNull()?.let { annotation ->
                                        if (annotation.tag == "SIGN_UP") {
                                            Toast.makeText(
                                                context, "Sign Up clicked", Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        })

            }
        }
    }

    if (state.isSignedIn){
        navController?.navigate(DashboardDest.DashboardScreen){
            popUpTo(AuthDest.SignInScreen){
                inclusive=true
            }
        }
    }

    if (state.showUserExistDialog) {
        /*AlertDialog(
            onDismissRequest = { viewModel.dismissUserExistDialog() },
            title = { Text("User Already Exists") },
            text = { Text("User with this email already exists. Please sign in.") },
            confirmButton = {
                Button(onClick = { viewModel.dismissUserExistDialog() }) {
                    Text("OK")
                }

            })*/

        navController?.navigate(DashboardDest.DashboardScreen){
            popUpTo(AuthDest.SignInScreen){
                inclusive=true
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewUI() {
    SignInScreen(null)
}