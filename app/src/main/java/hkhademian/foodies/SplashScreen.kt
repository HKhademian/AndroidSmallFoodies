package hkhademian.foodies

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class SplashScreen : Fragment() {
	val pages = listOf(
		Page(
			"Quick Search",
			"See your location to start exploring restaurant around you",
			R.drawable.splash_1,
		),
		Page(
			"Variety of foods",
			"See your location to start exploring restaurant around you",
			R.drawable.splash_2,
		),
		Page(
			"Search for a place",
			"See your location to start exploring restaurant around you",
			R.drawable.splash_3,
		),
		Page(
			"Fast shipping",
			"See your location to start exploring restaurant around you",
			R.drawable.splash_4,
		),
	)

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.screen_splash, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val pager = view.findViewById<ViewPager2>(R.id.pager)
		pager.adapter = Adapter()
	}

	data class Page(
		val title: String,
		val subtitle: String,
		@DrawableRes val image: Int,
	)

	private inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
		inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
			val title get() = root.findViewById<TextView>(R.id.title)
			val subtitle get() = root.findViewById<TextView>(R.id.subtitle)
			val image get() = root.findViewById<ImageView>(R.id.image)
			private val progress get() = root.findViewById<ViewGroup>(R.id.progress)

			fun setIndex(index: Int) {
				progress.forEachIndexed { it, view ->
					view.setBackgroundColor(
						if (it == index) Color.WHITE
						else Color.parseColor("#66FFFFFF")
					)
				}
			}
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
			ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_splash, parent, false))

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			val item = pages[position]
			holder.title.text = item.title
			holder.subtitle.text = item.subtitle
			holder.image.setImageResource(item.image)
			holder.setIndex(position)
		}

		override fun getItemCount() = pages.size


	}
}
