import androidx.fragment.app.FragmentActivity
import org.kodein.di.bindings.WeakContextScope

val fragmentScope get() = WeakContextScope.of<FragmentActivity>()  // Kodein 6.0.0
