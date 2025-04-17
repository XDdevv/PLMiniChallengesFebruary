package zed.rainxch.plminichallengesfebruary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zed.rainxch.plminichallengesfebruary.ui.theme.Black
import zed.rainxch.plminichallengesfebruary.ui.theme.Gray
import zed.rainxch.plminichallengesfebruary.ui.theme.PLMiniChallengesFebruaryTheme
import zed.rainxch.plminichallengesfebruary.ui.theme.Purple80
import zed.rainxch.plminichallengesfebruary.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLMiniChallengesFebruaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ThousandContainer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThousandContainer(
    modifier: Modifier = Modifier
) {
    val dataSet = listOf("1,000", "1.000", "1 000")

    var activeItem by remember { mutableIntStateOf(0) }
    Column(modifier = modifier.padding(4.dp)) {
        Text(stringResource(R.string.thousands_separator))
        Row (
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .background(
                    color = Purple80,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 4.dp)
                .horizontalScroll(ScrollState(0)),
            horizontalArrangement = Arrangement.spacedBy(4.dp),

        ) {
            dataSet.forEachIndexed { index, item ->
                ThousandSeparator(
                    data = item,
                    onClick = {
                        if (activeItem != index) {
                            activeItem = index
                        }
                    },
                    isActive = activeItem == index
                )
            }
        }
    }
}


@Composable
fun ThousandSeparator(
    data: String,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    var colorBackground = if (isActive) White else Purple80
    var colorText = if (isActive) Black else Gray

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorBackground,
            contentColor = colorText
        ),
        shape = RoundedCornerShape(14.dp),
        onClick = { onClick(data) },
        modifier = modifier
    ) {
        Text(text = data)
    }
}