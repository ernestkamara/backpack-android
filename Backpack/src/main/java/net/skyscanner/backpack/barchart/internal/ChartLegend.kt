package net.skyscanner.backpack.barchart.internal

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import net.skyscanner.backpack.R
import net.skyscanner.backpack.badge.BpkBadge
import net.skyscanner.backpack.barchart.BpkBarChart
import net.skyscanner.backpack.util.Consumer

@SuppressLint("ViewConstructor")
internal class ChartLegend constructor(
  context: Context,
  colors: BpkBarChart.Colors
) : LinearLayout(context), Consumer<BpkBarChart.Legend?> {

  init {
    orientation = HORIZONTAL
    LayoutInflater.from(context).inflate(R.layout.view_bpk_barchart_legend, this, true)
  }

  private val activated = findViewById<BpkBadge>(R.id.bpk_barchart_legend_activated).apply {
    isActivated = true
    setBackground(colors.chartForeground)
  }
  private val inactivated = findViewById<BpkBadge>(R.id.bpk_barchart_legend_inactivated).apply {
    isActivated = false
    setBackground(colors.chartForeground)
  }

  override fun invoke(legend: BpkBarChart.Legend?) {
    if (legend == null) {
      activated.visibility = View.GONE
      inactivated.visibility = View.GONE
    } else {
      activated.visibility = View.VISIBLE
      inactivated.visibility = View.VISIBLE
      activated.text = legend.activeTitle
      inactivated.text = legend.inactiveTitle
    }
  }
}