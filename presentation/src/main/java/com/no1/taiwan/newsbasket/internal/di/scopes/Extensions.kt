import androidx.fragment.app.FragmentActivity
import org.kodein.di.android.AndroidComponentsWeakScope

val fragmentScope get() = AndroidComponentsWeakScope<FragmentActivity>()
//val fragmentScope get() = WeakContextScope.of<FragmentActivity>()  // Kodein 6.0.0
