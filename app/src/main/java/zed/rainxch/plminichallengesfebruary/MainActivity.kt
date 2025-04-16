package zed.rainxch.plminichallengesfebruary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zed.rainxch.plminichallengesfebruary.domain.ThousandModel
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThousandContainer()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThousandContainer() {
    val dataSet = listOf<ThousandModel>(
        ThousandModel("1,000", true),
        ThousandModel("1.000", true),
        ThousandModel("1 000", false)
    )

    Column(modifier = Modifier.padding(4.dp)) {
        Text(stringResource(R.string.thousands_separator))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Purple80,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            items(dataSet) { item ->
                ThousandSeparator(data = item, onClick = { isEnabled -> !isEnabled })
            }
        }
    }
}


@Composable
fun ThousandSeparator(data: ThousandModel, onClick: (Boolean) -> Unit) {
    val isEnabledState by remember { mutableStateOf(data.isEnabled) }
    Button(
        colors = ButtonColors(
            containerColor = White,
            disabledContainerColor = Purple80,
            contentColor = Black,
            disabledContentColor = Gray,
        ),
        enabled = isEnabledState,
        onClick = { onClick(isEnabledState) },
    ) {
        Text(text = data.value)
    }
}