import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import org.kodein.di.bindings.WeakContextScope

val fragmentScope get() = WeakContextScope.of<Fragment>()  // Kodein 6.0.0
val activityScope get() = WeakContextScope.of<ActivityCompat>()  // Kodein 6.0.0
