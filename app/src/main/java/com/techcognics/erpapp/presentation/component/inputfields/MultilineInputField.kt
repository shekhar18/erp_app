package com.techcognics.erpapp.presentation.component.inputfields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techcognics.erpapp.presentation.component.text.LabelText

@Composable
fun MultilineInputField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    validation: Boolean = false,errorMessage:String=""
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LabelText(label)
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()

                .border(
                    width = 1.dp,
                    color = if(validation) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(start = 10.dp, top = 10.dp), contentAlignment = Alignment.TopStart
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                maxLines = 5,
                singleLine = false,
                textStyle = TextStyle(
                    fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            )
        }
        if (validation) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}