package com.example.wiremock_stages_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wiremock_stages_compose.ui.viewmodel.StageViewModel
import com.example.wiremock_stages_compose.ui.widget.ProgressWidget
import org.koin.androidx.compose.get
import com.example.wiremock_stages_compose.ui.model.StepState
import com.example.wiremock_stages_compose.ui.model.UiStatus

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StageScreen(navController: NavController, paymentCode: String) {
    val viewModel = get<StageViewModel>()
    Scaffold(topBar = {
        AppBar(navController = navController, viewModel)
    }) {
        Body(paymentCode = paymentCode, viewModel)
    }
}

@Composable
private fun AppBar(navController: NavController, viewModel: StageViewModel) {
    TopAppBar(
        title = { Text(text = "Stage Screen") },
        navigationIcon = {
            IconButton(onClick = {
                viewModel.resetScenario()
                navController.navigateUp()
            }) {
                Icon(Icons.Rounded.ArrowBack, "")
            }
        }
    )
}

@Composable
private fun Body(paymentCode: String, viewModel: StageViewModel) {
    val states = StepState.values()

    LaunchedEffect(true) { }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProgressWidget(
                size = states.size,
                currentIndex = viewModel.state.index,
                states = states.map { it.toString() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (viewModel.state.uiStatus) {
                UiStatus.INIT -> {
                    Text(text = "Payment Code")
                    Text(text = paymentCode)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Press Start Pull Button",
                        fontSize = 24.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                UiStatus.LOADING -> {
                    CircularProgressIndicator(modifier = Modifier.padding(vertical = 16.dp))
                    Text(text = "Loading")
                }
                UiStatus.SUCCESS -> {
                    Text(text = "INDEX: ${viewModel.state.index}", fontSize = 24.sp)
                    Text(text = "STATE: ${viewModel.state.pullData?.status}", fontSize = 30.sp)
                    if (viewModel.state.pullData?.status == "AUTHORIZED") {
                        Text(
                            text = "Transaction Successful!",
                            color = Color.Blue,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                UiStatus.ERROR -> {
                    viewModel.state.error?.let { error ->
                        Text(text = error, color = Color.Red)
                    }
                }
            }
        }
        Buttons(paymentCode, viewModel)
    }
}

@Composable
private fun Buttons(paymentCode: String, viewModel: StageViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ),
            onClick = { viewModel.resetScenario() }) {
            Text(text = "RESET SCENARIO")
        }
        Button(
            onClick = { viewModel.pullData(paymentCode) }) {
            Text(text = "START PULL")
        }
    }
}