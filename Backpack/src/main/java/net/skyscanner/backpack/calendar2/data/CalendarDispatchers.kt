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

package net.skyscanner.backpack.calendar2.data

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.skyscanner.backpack.util.InternalBackpackApi

@InternalBackpackApi
object CalendarDispatchers {

  private var main: CoroutineDispatcher = Dispatchers.Main
  private var background: CoroutineDispatcher = Dispatchers.Default

  val Main: CoroutineDispatcher
    get() = main

  val Background: CoroutineDispatcher
    get() = background

  @VisibleForTesting
  fun setMain(dispatcher: CoroutineDispatcher) {
    this.main = dispatcher
  }

  @VisibleForTesting
  fun setBackground(dispatcher: CoroutineDispatcher) {
    this.background = dispatcher
  }
}
