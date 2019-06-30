package processing.test.test_android_sketch

import processing.core.*

class SketchAndroid : PApplet() {

    private var circles = arrayOfNulls<Circle>(1000)
    private var count = 0
    private var maxDiameter = 100
    private var minDiameter = 10
    private var lastAdded = 0
    private var lastAddedTimeout = 100

    override fun setup() {
        println("Setting UP Baby")
        background(255)
        noFill()
    }

    override fun draw() {
        //background(255);
        if (count < circles.size) {
            circles[count] = Circle(5f, maxDiameter.toFloat())
            for (i in 0 until count) {
                if (circles[i]?.let { circles[count]!!.intersects(it) }!!) {
                    circles[count] = null
                    break
                }
            }

            if (circles[count] != null) {
                circles[count]?.draw()

                if (count > 1) {
                    var nearest = 100000f
                    var current = 0f
                    var nearestIndex = -1
                    for (i in 0 until count) {
                        current = dist(circles[i]!!.x, circles[i]!!.y, circles[count]!!.x, circles[count]!!.y)
                        if (current < nearest) {
                            nearest = current
                            nearestIndex = i
                        }
                    }

                    stroke(255f, 255f, 0f)
                    line(circles[nearestIndex]!!.x, circles[nearestIndex]!!.y, circles[count]!!.x, circles[count]!!.y)
                    stroke(0)
                }

                count++
                lastAdded = 0
            } else {
                if (lastAdded > lastAddedTimeout && maxDiameter > minDiameter) {
                    maxDiameter--
                    lastAdded = 0
                }
                lastAdded++
            }
        }
    }


    internal inner class Circle(minDiameter: Float, maxDiameter: Float) {
        var x: Float = 0.toFloat()
        var y: Float = 0.toFloat()
        private var radius: Float = 0.toFloat()

        init {
            radius = random(minDiameter, maxDiameter) / 2.0f
            x = random(radius, width - radius)
            y = random(radius, height - radius)
        }

        fun intersects(c: Circle): Boolean {
            return dist(c.x, c.y, x, y) < c.radius + radius
        }

        fun draw() {
            val c = lerpColor(color(255), color(0), radius / 50.0f)
            fill(c)
            ellipse(x, y, radius * 2, radius * 2)
        }

    }

    override fun settings() {
        size(500, 500)
        smooth()
    }
}
