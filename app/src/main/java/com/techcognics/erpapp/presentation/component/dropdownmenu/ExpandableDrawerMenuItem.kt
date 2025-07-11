package com.techcognics.erpapp.presentation.component.dropdownmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.data.user_roles.Children

@Composable
fun ExpandableDrawerMenuItem(
    title: String?,
    subTitle: String?,
    icon: Int,
    children: List<Children>? = emptyList(),
    onChildClick: (String) -> Unit = {},

    ) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { expanded = !expanded }
        .padding(horizontal = 10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "text",
                tint = MaterialTheme.colorScheme.primary,

                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp),
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,

                    )
                if (expanded) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = subTitle ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }


            }

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        AnimatedVisibility(visible = expanded) {

            Column(modifier = Modifier.padding(start = 32.dp, top = 8.dp)) {
                children?.forEach { child ->
                    val iconId = context.resources.getIdentifier(
                        (child.icon ?: "dashboard") as String?,
                        "drawable",
                        context.packageName
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(25.dp)
                            .clickable { onChildClick(child.name ?: "") }) {
                        Icon(
                            painter = painterResource(
                                (if (iconId != 0) {
                                    iconId
                                } else {
                                    context.resources.getIdentifier(
                                        ("dashboard") as String?,
                                        "drawable",
                                        context.packageName
                                    )
                                }).toInt()

                            ),
                            contentDescription = "text",
                            tint = contentColor,
                            modifier = Modifier
                                .height(16.dp)
                                .width(16.dp),
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        child.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}
