package com.example.foodmart.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.foodmart.ui.theme.FoodmartTheme
import com.example.foodmart.view.LoginActivity

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodmartTheme {
                RegistrationBody()
            }
        }
    }
}

@Composable
fun RegistrationBody() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var agreeToTerms by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val activity = context as? Activity
    val scrollState = rememberScrollState()

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
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Header
                Text(
                    text = "🛒 Join FoodMart Family 🛒",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = textColor
                )
                Text(
                    text = "🥬 Create account for fresh groceries 🍎",
                    fontSize = 16.sp,
                    color = placeholderColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Logo placeholder
                Card(
                    modifier = Modifier.size(120.dp),
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
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryGreen,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Registration Form Card
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
                            text = "Create Account",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )

                        // Full Name Field
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            ),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            placeholder = {
                                Text("Full Name", color = placeholderColor)
                            },
                            value = fullName,
                            onValueChange = { input ->
                                fullName = input
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        // Email Field
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            ),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Email,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            placeholder = {
                                Text("your.email@gmail.com", color = placeholderColor)
                            },
                            value = email,
                            onValueChange = { input ->
                                email = input
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        // Password Field
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            visualTransformation = if (passwordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
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
                            placeholder = {
                                Text("Create Password", color = placeholderColor)
                            },
                            value = password,
                            onValueChange = { input ->
                                password = input
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        // Confirm Password Field
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = { confirmPasswordVisibility = !confirmPasswordVisibility }
                                ) {
                                    Icon(
                                        imageVector = if (confirmPasswordVisibility)
                                            Icons.Default.Visibility
                                        else
                                            Icons.Default.VisibilityOff,
                                        contentDescription = if (confirmPasswordVisibility) "Hide password" else "Show password",
                                        tint = primaryGreen
                                    )
                                }
                            },
                            placeholder = {
                                Text("Confirm Password", color = placeholderColor)
                            },
                            value = confirmPassword,
                            onValueChange = { input ->
                                confirmPassword = input
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        // Phone Number Field
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone
                            ),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Phone,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            placeholder = {
                                Text("Phone Number", color = placeholderColor)
                            },
                            value = phoneNumber,
                            onValueChange = { input ->
                                phoneNumber = input
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        // Address Field
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            keyboardOptions = KeyboardOptions(),
                            leadingIcon = {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = primaryGreen
                                )
                            },
                            placeholder = {
                                Text("Delivery Address", color = placeholderColor)
                            },
                            value = address,
                            onValueChange = { input ->
                                address = input
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryGreen,
                                unfocusedBorderColor = lightGreen.copy(alpha = 0.6f),
                                focusedLabelColor = primaryGreen
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Terms and Conditions Checkbox
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = agreeToTerms,
                                onCheckedChange = { checked ->
                                    agreeToTerms = checked
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = primaryGreen,
                                    checkmarkColor = Color.White,
                                    uncheckedColor = lightGreen.copy(alpha = 0.6f)
                                )
                            )
                            Text(
                                text = "I agree to FoodMart's Terms & Conditions",
                                modifier = Modifier.padding(start = 8.dp),
                                color = textColor,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Register Button
                        Button(
                            onClick = {
                                // Validation
                                if (fullName.isBlank() || email.isBlank() || phoneNumber.isBlank() ||
                                    password.isBlank() || confirmPassword.isBlank() || address.isBlank()) {
                                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (!email.contains("@")) {
                                    Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (password.length < 6) {
                                    Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (password != confirmPassword) {
                                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (phoneNumber.length < 10) {
                                    Toast.makeText(context, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (!agreeToTerms) {
                                    Toast.makeText(context, "Please agree to terms and conditions", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                // Simulate registration process
                                coroutineScope.launch {
                                    try {
                                        // Show loading state
                                        Toast.makeText(context, "Creating your FoodMart account...", Toast.LENGTH_SHORT).show()

                                        // Simulate network delay
                                        delay(1500)

                                        // Here you would typically call your registration API
                                        // For now, we'll simulate success
                                        Toast.makeText(context, "🎉 Account created successfully!\nWelcome to FoodMart!", Toast.LENGTH_LONG).show()

                                        // Navigate to LoginActivity
                                        delay(500)
                                        val intent = Intent(context, LoginActivity::class.java)
                                        context.startActivity(intent)
                                        activity?.finish()

                                    } catch (e: Exception) {
                                        Toast.makeText(context, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
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
                                "Join FoodMart",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Login Link
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Already have an account? ",
                                color = placeholderColor,
                                fontSize = 14.sp
                            )
                            Text(
                                "Sign In",
                                color = primaryGreen,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    val intent = Intent(context, LoginActivity::class.java)
                                    context.startActivity(intent)
                                    activity?.finish()
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Quick Registration Options
                Text(
                    text = "Or register with",
                    color = placeholderColor,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(15.dp))

                // Social Registration Options
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, "Google registration coming soon!", Toast.LENGTH_SHORT).show()
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
                            Toast.makeText(context, "Facebook registration coming soon!", Toast.LENGTH_SHORT).show()
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

                Spacer(modifier = Modifier.height(20.dp))

                // Footer
                Text(
                    text = "Start your fresh grocery journey today! 🥕🍞🥛",
                    color = placeholderColor.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreviewBody() {
    FoodmartTheme {
        RegistrationBody()
    }
}
