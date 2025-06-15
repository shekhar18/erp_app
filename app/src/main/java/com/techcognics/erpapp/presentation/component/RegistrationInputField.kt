package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techcognics.erpapp.R

@Composable
fun RegistrationInputField(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String = "",
    hintText: String,
    isPasswordField: Boolean = false,
    keypadType: KeyboardType
) {
    var email by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .height(28.dp)
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colorResource(R.color.forget_text_color),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp), contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.Black,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keypadType // Opens number keypad
                ),
                singleLine = true,
                visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
                textStyle = TextStyle(fontSize = 12.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    if (email.isEmpty()) {
                        Row {
                            Text(
                                text = hintText,
                                fontSize = 9.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "*",
                                fontSize = 9.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    innerTextField()
                })
        }


    }

    /*  Box(
          modifier = Modifier
              .fillMaxWidth()
              .padding(start = 8.dp, end = 8.dp)
              .border(
                  width = 1.dp, color = Color(0xFF3B82F6), // Blue border
                  shape = RoundedCornerShape(4.dp)
              )
              .padding(horizontal = 12.dp, vertical = 8.dp)
      ) {
          Row(
              verticalAlignment = Alignment.CenterVertically
          ) {
              Icon(
                  imageVector = icon,
                  contentDescription = contentDescription,
                  tint = Color.Black,
                  modifier = Modifier.size(15.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Column {
                  BasicTextField(
                      value = email,
                      onValueChange = { email = it },
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(1.dp),
                      textStyle = TextStyle(
                          color = Color.Black, fontSize = 16.sp
                      ),
                      visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
                      singleLine = true,
                      decorationBox = { innerTextField ->
                          if (email.isEmpty()) {
                              Row {
                                  Text(
                                      text = hintText,
                                      fontSize = 12.sp,
                                      color = Color.Black,
                                      fontWeight = FontWeight.Medium
                                  )
                                  Text(
                                      text = "*",
                                      fontSize = 12.sp,
                                      color = Color.Red,
                                      fontWeight = FontWeight.Bold
                                  )
                              }
                          }
                          innerTextField()
                      })
              }
          }
      }*/

}