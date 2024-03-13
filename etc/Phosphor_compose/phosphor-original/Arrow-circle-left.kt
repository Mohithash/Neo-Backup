import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.machiav3lli.backup.ui.compose.icons.phosphor.Phosphor

@Composable
fun ArrowCircleLeftIcon() {
    Image(
        painter = painterResource(id = Phosphor.ArrowCircleLeft),
        contentDescription = "Arrow circle left icon"
    )
}
