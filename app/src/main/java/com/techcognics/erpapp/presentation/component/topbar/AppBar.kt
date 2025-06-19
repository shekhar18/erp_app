package com.techcognics.erpapp.presentation.component.topbar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.techcognics.erpapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val context = LocalContext.current
    val profileBorderColor = MaterialTheme.colorScheme.primary
    TopAppBar(
        navigationIcon = {
        IconButton(
            onClick = {
                scope.launch { if (drawerState.isOpen == true) drawerState.close() else drawerState.open() }
            },
        ) {
            Icon(
                painter = painterResource(R.drawable.menu),
                contentDescription = "Menu",
                modifier = modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
    }, title = {
        Text(
            "Enterprise Resource Planning System".uppercase(),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
    ), actions = {
        IconButton(
            onClick = {
                Toast.makeText(context, "Grid Menu", Toast.LENGTH_SHORT).show()
            }, modifier = modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.menu_grid),
                contentDescription = "Menu",
                modifier = modifier.size(16.dp),
                tint = Color.Unspecified
            )

        }
        IconButton(
            onClick = {
                Toast.makeText(context, "Notification Menu", Toast.LENGTH_SHORT).show()
            }, modifier = modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.notification),
                contentDescription = "Menu",
                modifier = modifier.size(16.dp),
                tint = Color.Unspecified
            )
        }
        Box(modifier = Modifier
            .size(24.dp)
            .drawBehind {

                val strokeWidth = 3.dp.toPx()
                val radius = size.minDimension / 2
                drawCircle(
                    color = profileBorderColor, radius = radius - strokeWidth / 2, style = Stroke(
                        width = strokeWidth,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
                    ), center = Offset(size.width / 2, size.height / 2)
                )
            }
            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
            contentAlignment = Alignment.Center) {
            Text(
                text = "SK",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp
            )
        }
    })

}