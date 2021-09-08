package com.hossein.linefollower.core.provider

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.setMargins
import androidx.core.view.updateMarginsRelative
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class ParamsProvider {

   companion object {
      const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT
      const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT
   }

   class Linear : LinearLayout.LayoutParams {
      constructor(width: Int, height: Int) : super(width, height)
      constructor(width: Int, height: Int, weight: Float) : super(width, height, weight)

      fun gravity(gravity: Int): Linear {
         this.gravity = gravity
         return this
      }
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Linear {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Linear {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      fun weight(w: Float): Linear{
         weight = w
         return this
      }
      companion object {
         fun defaultParams() = Linear(MATCH, WRAP)
         fun wrapContent() = Linear(WRAP, WRAP)
         fun fullScreen() = Linear(MATCH, MATCH)
         fun availableHeightParams(weight: Float = 1F) = Linear(MATCH, 0, weight)
         fun availableWidthParams(weight: Float = 1F) = Linear(0, WRAP, weight)
         fun justOccupyWidth() = Linear(0, 1, 1F)
         fun justOccupyHeight() = Linear(1, 0, 1F)
         fun justOccupyScreen() = Linear(MATCH, MATCH, 1F)

         fun get(w: Int, h: Int) = Linear(w, h)
      }
   }

   class Frame(width: Int, height: Int) : FrameLayout.LayoutParams(width, height) {

      fun gravity(gravity: Int): Frame {
         this.gravity = gravity
         return this
      }
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Frame {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Frame {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = Frame(MATCH, WRAP)
         fun wrapContent() = Frame(WRAP, WRAP)
         fun fullScreen() = Frame(MATCH, MATCH)
         fun get(w: Int, h: Int) = Frame(w, h)
      }
   }

   class Relative(width: Int, height: Int) : RelativeLayout.LayoutParams(width, height) {
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Relative {
         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Relative {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      fun above(viewId: Int) = apply { addRule(RelativeLayout.ABOVE, viewId) }
      fun below(viewId: Int) = apply { addRule(RelativeLayout.BELOW, viewId) }
      fun alignParentBottom() = apply { addRule(RelativeLayout.ALIGN_PARENT_BOTTOM) }
      fun alignParentTop() = apply { addRule(RelativeLayout.ALIGN_PARENT_TOP) }
      fun alignParentStart() = apply { addRule(RelativeLayout.ALIGN_PARENT_START) }
      fun alignParentEnd() = apply { addRule(RelativeLayout.ALIGN_PARENT_END) }
      fun centerInParent() = apply { addRule(RelativeLayout.CENTER_IN_PARENT) }
      fun centerVertical() = apply { addRule(RelativeLayout.CENTER_VERTICAL) }
      fun centerHorizontal() = apply { addRule(RelativeLayout.CENTER_HORIZONTAL) }
      fun toStartOf(id : Int) = apply { addRule(RelativeLayout.START_OF , id) }
      fun toEndOf(id : Int) = apply { addRule(RelativeLayout.END_OF , id) }
      fun aboveOf(id : Int) = apply { addRule(RelativeLayout.ABOVE , id) }

      companion object {
         fun defaultParams() = Relative(MATCH, WRAP)
         fun wrapContent() = Relative(WRAP, WRAP)
         fun fullSize() = Relative(MATCH, MATCH)
         fun get(w: Int, h: Int) = Relative(w, h)
      }
   }

   class Constraint(width: Int, height: Int) : ConstraintLayout.LayoutParams(width, height) {
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Constraint {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Constraint {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = Constraint(0, WRAP)
         fun wrapContent() = Constraint(WRAP, WRAP)
         fun fullScreen() = Constraint(0, 0)
         fun get(w: Int, h: Int) = Constraint(w, h)
      }
   }

   class Scroll(width: Int, height: Int) : FrameLayout.LayoutParams(width, height) {
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Scroll {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Scroll {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = Scroll(MATCH, WRAP)
         fun wrapContent() = Scroll(WRAP, WRAP)
         fun fullScreen() = Scroll(MATCH, MATCH)
         fun get(w: Int, h: Int) = Scroll(w, h)
      }
   }

   class Card(width: Int, height: Int) : FrameLayout.LayoutParams(width, height) {

      fun gravity(gravity: Int): Card {
         this.gravity = gravity
         return this
      }

      fun margins(left: Int, top: Int, right: Int, bottom: Int): Card {
         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Card {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = Card(MATCH, WRAP)
         fun wrapContent() = Card(WRAP, WRAP)
         fun fullScreen() = Card(MATCH, MATCH)
         fun get(w: Int, h: Int) = Card(w, h)
      }
   }

   class Drawer(width: Int, height: Int) : DrawerLayout.LayoutParams(width, height) {
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Drawer {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Drawer {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      fun gravity(gravity: Int): Drawer {
         this.gravity = gravity
         return this
      }

      companion object {
         fun fullScreen() = Drawer(MATCH, MATCH)
         fun get(w: Int, h: Int) = Drawer(w, h)
      }
   }

   class Coordinator(width: Int, height: Int) : CoordinatorLayout.LayoutParams(width, height) {
      fun margins(left: Int, top: Int, right: Int, bottom: Int): Coordinator {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Coordinator {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = Coordinator(MATCH, WRAP)
         fun scroller(): Coordinator {
            val params = Coordinator(MATCH, MATCH)
            params.behavior = AppBarLayout.ScrollingViewBehavior()
            return params
         }

         fun get(w: Int, h: Int) = Coordinator(w, h)
      }
   }

   class AppBar(width: Int, height: Int) : AppBarLayout.LayoutParams(width, height) {
      fun margins(left: Int, top: Int, right: Int, bottom: Int): AppBar {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): AppBar {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = AppBar(MATCH, WRAP)

         fun scrollFlags(flags: Int): AppBar {
            val params = defaultParams()
            params.scrollFlags = flags
            return params
         }

         fun stay(): AppBar {
            return scrollFlags(SCROLL_FLAG_ENTER_ALWAYS)
         }

         fun scroll(): AppBar {
            return scrollFlags(SCROLL_FLAG_SCROLL)
         }
      }
   }

   class NestedScroll(width: Int, height: Int) : FrameLayout.LayoutParams(width, height) {

      fun margins(left: Int, top: Int, right: Int, bottom: Int): NestedScroll {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): NestedScroll {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun fullScreen() = NestedScroll(MATCH, MATCH)
         fun defaultParams() = Recycler(MATCH, WRAP)
      }
   }

   class Recycler(width: Int, height: Int) : RecyclerView.LayoutParams(width, height) {

      fun margins(left: Int, top: Int, right: Int, bottom: Int): Recycler {

         marginStart=left
         marginEnd=right
         topMargin=top
         bottomMargin=bottom
         return this
      }

      fun margins(margin: Int): Recycler {
         marginStart=margin
         marginEnd=margin
         topMargin=margin
         bottomMargin=margin
         return this
      }
      companion object {
         fun defaultParams() = Recycler(MATCH, WRAP)
         fun wrapContent() = Recycler(WRAP, WRAP)
         fun fullSize() = Recycler(MATCH, MATCH)
         fun get(w: Int, h: Int) = Recycler(w, h)
      }
   }
}

fun ViewGroup.MarginLayoutParams.margin(value: Int) = apply {
   updateMarginsRelative(value, value, value, value)
}

fun ViewGroup.MarginLayoutParams.margin(start: Int, top: Int, end: Int, bottom: Int) = apply {
   updateMarginsRelative(start, top, end, bottom)
}