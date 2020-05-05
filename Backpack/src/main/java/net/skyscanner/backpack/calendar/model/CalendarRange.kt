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

package net.skyscanner.backpack.calendar.model

import org.threeten.bp.LocalDate
import java.io.Serializable

open class CalendarSelection : Serializable

data class SingleDay(val selectedDay: LocalDate) : CalendarSelection()

data class CalendarRange(var start: LocalDate? = null, var end: LocalDate? = null) : CalendarSelection() {
    internal val isOnTheSameDate: Boolean
        get() = isRange && start == end

    internal val isRange: Boolean
        get() = start != null && end != null

    enum class DrawType {
        NONE, RANGE, SELECTED
    }

    internal fun getDrawType(calendarDay: LocalDate): DrawType {
        return if (isRange) {
            when {
                isSelected(calendarDay) -> DrawType.SELECTED
                isBetweenRange(calendarDay) -> DrawType.RANGE
                else -> DrawType.NONE
            }
        } else {
            if (isStartIsInSelectedMonth(calendarDay.year, calendarDay.month.value) &&
                isSelected(start, calendarDay) ||
                isEndIsInSelectedMonth(calendarDay.year, calendarDay.month.value) &&
                isSelected(end, calendarDay)
            ) {
              DrawType.SELECTED
            } else {
              DrawType.NONE
            }
        }
    }

    private fun isSelected(currentDayTime: LocalDate) =
        currentDayTime == start || currentDayTime == end

    private fun isBetweenRange(currentDayTime: LocalDate) =
        currentDayTime.isAfter(start) && currentDayTime.isBefore(end)

    private fun isSelected(day: LocalDate?, currentDayTime: LocalDate) =
        if (day != null) currentDayTime == day else false

    private fun isStartIsInSelectedMonth(year: Int, month: Int) =
        start != null && start?.year == year && start?.month?.value == month

    private fun isEndIsInSelectedMonth(year: Int, month: Int) =
        end != null && end?.year == year && end?.month?.value == month
}
