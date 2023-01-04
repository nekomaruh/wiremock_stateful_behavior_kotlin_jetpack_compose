package com.example.wiremock_stages_compose.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle.Event.ON_ANY
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.navigation.NavController
import com.example.wiremock_stages_compose.data.model.Result
import com.example.wiremock_stages_compose.data.model.Result.OnError
import com.example.wiremock_stages_compose.data.model.Result.OnLoading
import com.example.wiremock_stages_compose.data.model.Result.OnSuccess
import com.example.wiremock_stages_compose.domain.model.PullResponse
import com.example.wiremock_stages_compose.ui.extension.rememberFlow
import com.example.wiremock_stages_compose.ui.extension.rememberLifecycleEvent
import com.example.wiremock_stages_compose.ui.viewmodel.StageViewModel
import com.example.wiremock_stages_compose.ui.widget.ProgressWidget
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StageScreen(navController: NavController, paymentCode: String) {
    Scaffold(topBar = {
        AppBar(navController = navController)
    }) {
        Body(paymentCode = paymentCode)
    }
}

@Composable
private fun AppBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "Stage Screen") },
        navigationIcon = {
            IconButton(onClick = navController::navigateUp) {
                Icon(Icons.Rounded.ArrowBack, "")
            }
        }
    )
}

private enum class NodeState { INIT, CREATE, VALIDATE, AUTHORIZE }

private fun onCallPull(result: Result<PullResponse>) {
    when (result) {
        is OnLoading -> {
            Log.i("CALL PULL", "LOADING")
        }
        is OnError -> {
            Log.e("CALL PULL", result.throwable.toString())
        }
        is OnSuccess -> {
            Log.i("CALL PULL", "NEW_STATE ${result.data}")
            onNewPullState(result.data.status)
        }
    }
}

private fun onNewPullState(status: String) {
    when (status) {
        "INIT" -> {
            Log.i("PULL", "INIT")
        }
        "CREATE" -> {
            Log.i("PULL", "CREATE")
        }
        "VALIDATE" -> {
            Log.i("PULL", "VALIDATE")
        }
        "AUTHORIZE" -> {
            Log.i("PULL", "AUTHORIZE")
        }
    }
}

@Composable
private fun Body(paymentCode: String) {
    val index = remember { mutableStateOf(1) }
    val states = NodeState.values()

    val viewModel = get<StageViewModel>()
    val lifecycleEvent = rememberLifecycleEvent()
    val pullState by viewModel.pullFlow.collectAsState()
    onCallPull(pullState)

    LaunchedEffect(lifecycleEvent) {
        when (lifecycleEvent) {
            ON_CREATE -> Log.i("EVENT", "ON_CREATE")
            ON_ANY -> Log.i("EVENT", "ON_ANY")
            ON_START -> Log.i("EVENT", "ON_START")
            ON_RESUME -> {
                Log.i("EVENT", "ON_RESUME")
                viewModel.getStatus(paymentCode)
            }
            ON_STOP -> Log.i("EVENT", "ON_STOP")
            ON_DESTROY -> Log.i("EVENT", "ON_DESTROY")
            ON_PAUSE -> Log.i("EVENT", "ON_PAUSE")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ProgressWidget(
                size = states.size,
                currentIndex = index.value,
                states = states.map { it.toString() }
            )
            Text(text = "STATE: ${states[index.value - 1]}", fontSize = 30.sp)
            Text(text = "PULL COUNT: 5", fontSize = 30.sp)
        }

        Buttons(index = index)

    }
}

@Composable
private fun Buttons(index: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = androidx.compose.ui.graphics.Color.Red,
                contentColor = androidx.compose.ui.graphics.Color.White
            ),
            onClick = { index.value = 1 }) {
            Text(text = "RESET SCENARIO")
        }
        Button(
            onClick = {
                if (index.value < NodeState.values().size) index.value++
            }) {
            Text(text = "PULL")
        }
    }
}