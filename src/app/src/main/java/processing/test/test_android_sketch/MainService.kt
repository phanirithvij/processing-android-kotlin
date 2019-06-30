package processing.test.test_android_sketch

import processing.android.PWallpaper
import processing.core.PApplet

class MainService : PWallpaper() {
    override fun createSketch(): PApplet {
        return SketchAndroid()
    }
}
