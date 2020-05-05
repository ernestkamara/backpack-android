/**
 * Backpack for Android - Skyscanner's Design System
 *
 * Copyright 2018-2020 Skyscanner Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.skyscanner.backpack.text

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.skyscanner.backpack.BpkSnapshotTest
import net.skyscanner.backpack.createThemedContext
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BpkLinksSpanTests : BpkSnapshotTest() {

  private val link = "https://backpack.github.io/"
  private val handler = { _: String ->
  }

  private val textView = TextView(testContext).apply { setBackgroundColor(Color.WHITE) }

  @Before
  fun setup() {
    setDimensions(20, 50)
  }

  @Test
  fun screenshotTestLinkSpan_Custom() {
    val span = BpkLinkSpan(testContext, link, handler)
    textView.text = SpannableStringBuilder().append("Test", span, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    snap(textView)
  }

  @Test
  fun screenshotTestLinkSpan_withTheme() {
    val span = BpkLinkSpan(createThemedContext(testContext), link, handler)
    textView.text = SpannableStringBuilder().append("Test", span, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    snap(textView)
  }
}
