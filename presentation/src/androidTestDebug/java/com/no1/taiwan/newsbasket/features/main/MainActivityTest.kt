package com.no1.taiwan.newsbasket.features.main

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.KButton
import com.agoda.kakao.KEditText
import com.agoda.kakao.KTextView
import com.agoda.kakao.Screen
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val screen = MainActivityScreen()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testButtonClickPerform() {
        screen {
            button {
                isDisplayed()
                isEnabled()
            }

            input1 {
                typeText("hello")
                hasText("hello")
            }
            input2 {
                typeText("world")
                hasText("world")
            }

            button.click()

            text.hasText("hello + world")
        }
    }

    class MainActivityScreen : Screen<MainActivityScreen>() {
        val button = KButton { withId(R.id.btn_click) }
        val input1 = KEditText { withId(R.id.et_input1) }
        val input2 = KEditText { withId(R.id.et_input2) }
        val text = KTextView { withId(R.id.tv_res) }
    }
}
