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

package net.skyscanner.backpack.calendar2.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.skyscanner.backpack.calendar2.data.CalendarCell
import net.skyscanner.backpack.calendar2.data.CalendarCells
import net.skyscanner.backpack.calendar2.data.CalendarDispatchers
import net.skyscanner.backpack.util.Consumer
import net.skyscanner.backpack.util.InternalBackpackApi
import net.skyscanner.backpack.util.ItemHolder

internal class CalendarAdapter(
  private val scope: CoroutineScope,
  private val output: Consumer<CalendarCell.Day>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Consumer<CalendarCells> {

  private var data: CalendarCells = CalendarCells(emptyList())

  @OptIn(InternalBackpackApi::class)
  override fun invoke(data: CalendarCells) {
    val calculator = CalendarDiffCalculator(this.data, data)
    scope.launch(CalendarDispatchers.Background) {
      val diff = DiffUtil.calculateDiff(calculator, false)
      withContext(CalendarDispatchers.Main) {
        this@CalendarAdapter.data = data
        diff.dispatchUpdatesTo(this@CalendarAdapter)
      }
    }
  }

  operator fun get(position: Int): CalendarCell =
    data[position]

  override fun getItemCount(): Int =
    data.size

  override fun getItemViewType(position: Int): Int = when (data[position]) {
    is CalendarCell.Day -> TYPE_DAY
    is CalendarCell.Header -> TYPE_HEADER
    is CalendarCell.Space -> TYPE_SPACE
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
    TYPE_HEADER -> CalendarCellHeaderHolder(parent)
    TYPE_DAY -> CalendarCellDayHolder(parent, output)
    else -> CalendarCellSpaceHolder(parent)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
    (holder as ItemHolder<CalendarCell>).invoke(data[position])

  companion object {
    private const val TYPE_SPACE = 0
    private const val TYPE_HEADER = 1
    private const val TYPE_DAY = 2
  }
}
