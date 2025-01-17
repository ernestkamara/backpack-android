/**
 * Backpack for Android - Skyscanner's Design System
 *
 * Copyright 2018 Skyscanner Ltd
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

package net.skyscanner.backpack.compose.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

internal fun Modifier.hideContentIf(hide: Boolean): Modifier =
  layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
      if (!hide) {
        placeable.place(0, 0)
      }
    }
  }


@OptIn(ExperimentalContracts::class)
internal inline fun Modifier.applyIf(predicate: Boolean, block: Modifier.() -> Modifier): Modifier {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }
  return if (predicate) block() else this
}
