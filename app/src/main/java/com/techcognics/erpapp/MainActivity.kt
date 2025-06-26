package com.techcognics.erpapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.techcognics.erpapp.presentation.base.ErpAppNavHost
import com.techcognics.erpapp.presentation.ui.theme.ERPAPPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ERPAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ErpAppNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

