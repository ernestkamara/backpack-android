package net.skyscanner.backpack.pagetitle

import android.app.Activity
import android.view.View
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import net.skyscanner.backpack.BpkSnapshotTest
import net.skyscanner.backpack.demo.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BpkPageTitleTest : BpkSnapshotTest() {

  private lateinit var activity: AppCompatActivity

  @get:Rule
  var activityRule: ActivityTestRule<AppCompatActivity> =
    ActivityTestRule(AppCompatActivity::class.java)

  @Before
  fun setup() {
    activity = activityRule.activity
    setDimensions(400, 400)
  }

  @Test
  fun screenshotPageTitle_default() {
    activity.init()
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_collapsed() {
    activity.init()
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .perform(ViewActions.swipeUp())
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_expanded() {
    activity.init()
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .perform(ViewActions.swipeDown())
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_default_rtl() {
    activity.init(rtl = true)
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_collapsed_rtl() {
    activity.init(rtl = true)
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .perform(ViewActions.swipeUp())
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_expanded_rtl() {
    activity.init(rtl = true)
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .perform(ViewActions.swipeDown())
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_default_themed() {
    activity.init(theme = R.style.LondonTheme)
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_collapsed_themed() {
    activity.init(theme = R.style.LondonTheme)
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .perform(ViewActions.swipeUp())
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  @Test
  fun screenshotPageTitle_expanded_themed() {
    activity.init(theme = R.style.LondonTheme)
    val asyncSnapshot = prepareForAsyncTest()
    onView(ViewMatchers.withId(R.id.appBar))
      .perform(ViewActions.swipeDown())
      .check { v, _ ->
        asyncSnapshot.record(v)
      }
  }

  private fun Activity.init(
    @StyleRes theme: Int = 0,
    rtl: Boolean = false
  ) {
    runOnUiThread {
      if (theme != 0) {
        setTheme(theme)
      }
      setContentView(R.layout.fragment_page_title)
      if (!rtl) {
        findViewById<BpkPageTitle>(R.id.appBar).title = "Page Title"
      } else {
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        findViewById<BpkPageTitle>(R.id.appBar).title = "عنوان الصفحة"
      }
    }
  }
}