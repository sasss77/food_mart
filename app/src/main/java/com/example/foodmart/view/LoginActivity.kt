package com.example.foodmart.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.foodmart.ui.theme.FoodmartTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodmartTheme {
                Scaffold { innerPadding ->
                    LoginBody(innerPadding)
                }
            }
        }
    }
}

@Composable
fun LoginBody(innerPaddingValues: PaddingValues) {
    val context = LocalContext.current
    val activity = context as Activity

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // SharedPreferences for Remember Me functionality
    val sharedPreferences = context.getSharedPreferences("FoodMart_User", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Load saved credentials if available
    LaunchedEffect(Unit) {
        val savedEmail: String = sharedPreferences.getString("email", "") ?: ""
        val savedPassword: String = sharedPreferences.getString("password", "") ?: ""

        if (savedEmail.isNotEmpty()) {
            email = savedEmail
            password = savedPassword
            rememberMe = true
        }
    }

    // FoodMart theme colors
    val primaryGreen = Color(0xFF4CAF50) // Fresh green
    val darkGreen = Color(0xFF2E7D32) // Dark green
    val lightGreen = Color(0xFF81C784) // Light green
    val backgroundColor = Color(0xFFF1F8E9) // Very light green
    val cardColor = Color.White
    val textColor = Color(0xFF1B5E20) // Dark green text
    val placeholderColor = Color(0xFF66BB6A) // Medium green

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            backgroundColor,
                            Color(0xFFE8F5E8) // Light green gradient
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPaddingValues)
                    .padding(24.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // App Header
                Text(
                    text = "🛒 Welcome to FoodMart 🛒",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "🥬 Fresh • Fast • Convenient 🍎",
                    fontSize = 16.sp,
                    color = placeholderColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Logo placeholder - you can replace with your actual logo
                Card(
                    modifier = Modifier
                        .size(120.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🛒\nFOOD\nMART",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryGreen,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Login Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = Color.Black.copy(alpha = 0.1f)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardColor)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Sign In",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        // Email Field
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("your.email@gmail.com", color = placeholderColor) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Password Field
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text("Enter your password", color = placeholderColor) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = { passwordVisibility = !passwordVisibility }
                                ) {
                                    Icon(
                                        imageVector = if (passwordVisibility)
                                            Icons.Default.Visibility
                                        else
                                            Icons.Default.VisibilityOff,
                                        contentDescription = if (passwordVisibility) "Hide password" else "Show password",
                                        tint = primaryGreen
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisibility)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Remember Me and Forgot Password Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = rememberMe,
                                    onCheckedChange = { rememberMe = it },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = primaryGreen,
                                        checkmarkColor = Color.White,
                                        uncheckedColor = lightGreen.copy(alpha = 0.6f)
                                    )
                                )
                                Text(
                                    "Remember me",
                                    color = textColor,
                                    fontSize = 14.sp
                                )
                            }

                            Text(
                                "Forgot Password?",
                                color = primaryGreen,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    // Handle forgot password navigation
                                    Toast.makeText(context, "Forgot password feature coming soon", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Login Button
                        Button(
                            onClick = {
                                // Basic validation
                                if (email.isBlank() || password.isBlank()) {
                                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (rememberMe) {
                                    editor.putString("email", email)
                                    editor.putString("password", password)
                                    editor.apply()
                                } else {
                                    editor.clear().apply()
                                }

                                // Simulate login process
                                coroutineScope.launch {
                                    // Here you would typically call your authentication API
                                    if (email.contains("@") && password.length >= 4) {
                                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()

                                        // Navigate to main app screen (you'll need to create this)
                                        // val intent = Intent(context, DashboardActivity::class.java)
                                        // context.startActivity(intent)
                                        // activity.finish()

                                        // For now, just show success message
                                        Toast.makeText(context, "Welcome to FoodMart! Dashboard coming soon...", Toast.LENGTH_LONG).show()

                                    } else {
                                        Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryGreen
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 4.dp,
                                pressedElevation = 8.dp
                            )
                        ) {
                            Text(
                                "Sign In to FoodMart",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Sign Up Link
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "New to FoodMart? ",
                                color = placeholderColor,
                                fontSize = 14.sp
                            )
                            Text(
                                "Create Account",
                                color = primaryGreen,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    // Handle registration navigation
                                    Toast.makeText(context, "Registration feature coming soon", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Quick Login Options
                Text(
                    text = "or continue with",
                    color = placeholderColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Social Login Placeholder Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, "Google login coming soon", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDB4437)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Google", color = Color.White, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            Toast.makeText(context, "Facebook login coming soon", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5998)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Facebook", color = Color.White, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Footer
                Text(
                    text = "Your trusted grocery companion 🥕🍞🥛",
                    color = placeholderColor.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginBodyPreview() {
    FoodmartTheme {
        LoginBody(innerPaddingValues = PaddingValues(0.dp))
    }
}
