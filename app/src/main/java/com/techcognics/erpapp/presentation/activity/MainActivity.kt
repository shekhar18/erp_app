package com.techcognics.erpapp.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.techcognics.erpapp.presentation.base.ErpAppNavHost
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.ui.theme.ERPAPPTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val _isLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            lifecycleScope.launch {
                viewModel.isLogin.collect { login ->
                    _isLogin.value = login
                }
            }
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }

        enableEdgeToEdge()
        setContent {
            ERPAPPTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    ErpAppNavHost(
                        modifier = Modifier.Companion.padding(innerPadding),
                        isLogin = _isLogin.observeAsState().value == true
                    )
                }
            }
        }


    }
}

@Preview
@Composable
private fun ShowErrorScreen() {
    ErrorDialog {  }

}