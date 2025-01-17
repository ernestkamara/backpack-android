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

package net.skyscanner.backpack.demo.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import net.skyscanner.backpack.compose.card.BpkCard
import net.skyscanner.backpack.compose.card.BpkCardCorner
import net.skyscanner.backpack.compose.card.BpkCardElevation
import net.skyscanner.backpack.compose.card.BpkCardPadding
import net.skyscanner.backpack.compose.text.BpkText
import net.skyscanner.backpack.compose.tokens.BpkSpacing

@Composable
fun CardStory() {
  Column(
    modifier = Modifier.padding(horizontal = BpkSpacing.Base, vertical = BpkSpacing.Xxl),
    verticalArrangement = Arrangement.spacedBy(BpkSpacing.Base),
  ) {

    val cardModifier = Modifier
      .fillMaxWidth()
      .weight(1f)

    SmallCornersCardExample(cardModifier)

    LargeCornersCardExample(cardModifier)

    NoPaddingCardExample(Modifier.fillMaxWidth())

    NonClickableCardExample(cardModifier)

    FocusableCardExample(cardModifier)
  }
}

@Composable
fun SmallCornersCardExample(
  modifier: Modifier = Modifier,
) {
  BpkCard(
    modifier = modifier,
    onClick = {},
    contentAlignment = Alignment.Center,
  ) {
    BpkText("Small corners")
  }
}

@Composable
fun LargeCornersCardExample(
  modifier: Modifier = Modifier,
) {
  BpkCard(
    modifier = modifier,
    onClick = {},
    corner = BpkCardCorner.Large,
    contentAlignment = Alignment.Center,
  ) {
    BpkText("Large corners")
  }
}

@Composable
fun NoPaddingCardExample(
  modifier: Modifier = Modifier,
) {
  BpkCard(
    modifier = modifier,
    onClick = {},
    padding = BpkCardPadding.None,
    contentAlignment = Alignment.Center,
  ) {
    BpkText("No padding")
  }
}

@Composable
fun NonClickableCardExample(
  modifier: Modifier = Modifier,
) {
  BpkCard(modifier, contentAlignment = Alignment.Center) {
    BpkText("Non clickable")
  }
}

@Composable
fun FocusableCardExample(
  modifier: Modifier = Modifier,
) {
  var elevation by remember { mutableStateOf(BpkCardElevation.Focus) }

  BpkCard(
    modifier = modifier,
    elevation = elevation,
    contentAlignment = Alignment.Center,
    onClick = {
      elevation = when (elevation) {
        BpkCardElevation.Default -> BpkCardElevation.Focus
        BpkCardElevation.Focus -> BpkCardElevation.Default
      }
    },
  ) {
    BpkText(if (elevation == BpkCardElevation.Focus) "Tap to unfocus" else "Tap to focus")
  }
}
