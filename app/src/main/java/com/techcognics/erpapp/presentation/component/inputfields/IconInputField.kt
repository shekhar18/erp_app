package com.techcognics.erpapp.presentation.component.inputfields

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun IconInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    icon: Int,
    contentDescription: String = "",
    hintText: String,
    isPasswordField: Boolean = false,
    keypadType: KeyboardType
) {
    var isFocused by remember { mutableStateOf(false) }

    val labelOffsetY by animateFloatAsState(
        targetValue = if (isFocused) -14f else 2f, label = "LabelOffset"
    )

    Box(
        modifier = modifier
            .height(28.dp)
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp), contentAlignment = Alignment.CenterStart
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isFocused) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = contentDescription,
                    tint = Color.Black,
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))


            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { state ->
                        isFocused = state.isFocused
                    },
                value = value,

                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keypadType // Opens number keypad
                ),

                singleLine = true,
                visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
                textStyle = MaterialTheme.typography.labelMedium,
                decorationBox = { innerTextField ->
                    if (value.isEmpty() && isFocused != true) {
                        Row {
                            Text(
                                text = hintText,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Text(

                                text = "*",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    } else {
                        Row {
                            Text(
                                modifier = modifier
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(start = if (icon != null) 15.dp else 0.dp)
                                    .offset(y = if (isFocused) labelOffsetY.dp else 0.dp),
                                text = hintText,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                modifier = modifier
                                    .background(Color.White)
                                    .offset(y = if (isFocused) labelOffsetY.dp else 0.dp),
                                text = "*",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    innerTextField()
                })


        }/*   if (isFocused) {
            Row {
                Text(
                    modifier = modifier
                        .background(Color.White)
                        .padding(start = if (icon != null) 15.dp else 0.dp)
                        .offset(y = labelOffsetY.dp),
                    text = hintText,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,

                    )
                Text(
                    modifier = modifier
                        .background(Color.White)
                        .offset(y = labelOffsetY.dp),
                    text = "*",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }*/

    }

}