package com.techcognics.erpapp.presentation.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techcognics.erpapp.presentation.component.button.PrimaryButton
import kotlinx.coroutines.launch


@Composable
fun MultiStepFormPager(
    modifier: Modifier = Modifier,
    homeNavHostController: NavHostController,
    pages: List<@Composable () -> Unit>,
    onSubmit: (Int) -> Unit,
    pageNumber: (Int) -> Unit
) {


    val totalSteps = pages.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column {
        StepperIndicator(
            currentStep = pagerState.currentPage, totalSteps = totalSteps
        )
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) { page ->
            pages[page]()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (pagerState.currentPage > 0) {
                PrimaryButton(
                    modifier = Modifier.weight(1f), buttonText = "Previous", onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            pageNumber(pagerState.currentPage)
                        }
                    })

            }
            if (pagerState.currentPage > 0) Spacer(modifier = Modifier.width(8.dp))
            PrimaryButton(
                modifier = Modifier.weight(1f),
                buttonText = if (pagerState.currentPage == totalSteps - 1) "Submit" else "Next"
            ) {
                scope.launch {

                    if (pagerState.currentPage == totalSteps - 1) {
                        // Handle Submit
                        Toast.makeText(context, "Form Submitted", Toast.LENGTH_SHORT).show()
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                    pageNumber(pagerState.currentPage)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            PrimaryButton(
                modifier = Modifier.weight(1f), buttonText = "Cancel", onClick = {
                    scope.launch {
                        homeNavHostController.popBackStack()
                    }
                })

        }
    }
}

@Composable
fun StepperIndicator(currentStep: Int, totalSteps: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(totalSteps) { index ->
            val color = if (index <= currentStep) MaterialTheme.colorScheme.primary else Color.Gray
            val textColor =
                if (index <= currentStep) Color.White else MaterialTheme.colorScheme.onBackground
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (index + 1).toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}
