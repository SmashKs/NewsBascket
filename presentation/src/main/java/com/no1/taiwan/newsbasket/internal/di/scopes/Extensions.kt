import androidx.fragment.app.FragmentActivity
import org.kodein.di.android.AndroidComponentsWeakScope

val fragmentScope get() = AndroidComponentsWeakScope<FragmentActivity>()
