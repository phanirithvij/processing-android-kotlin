package processing.test.test_android_sketch.services

import processing.android.PWallpaper
import processing.core.PApplet
import processing.test.test_android_sketch.ui.SketchAndroid

class MainService : PWallpaper() {
    override fun createSketch(): PApplet {
        return SketchAndroid()
    }
}
