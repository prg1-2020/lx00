package lx03

import scala.math.{max, min}

import sgeometry.Pos
import sdraw.{World, Blue, White, Green}

case class PotonWorld(centerX: Int, centerY: Int) extends World() {
  val A = PotonApp

  val BallRadius = A.BallRadius
  val MinX       = BallRadius + 10
  lazy val MaxX  = A.WorldWidth - BallRadius - 10
  lazy val MaxY  = A.WorldHeight - BallRadius * 2
  val HallX      = 200

  def draw(): Boolean = {
    // Clear screen
    canvas.drawRect(Pos(0, 0), canvas.width, canvas.height, White) &&
    // Grasses
    canvas.drawRect(Pos(10, MaxY - 10), HallX - 10, 20, Green) &&
    canvas.drawRect(Pos(HallX + (BallRadius * 2 + 10), MaxY - 10),
      canvas.width - (HallX + (BallRadius * 2 + 10)), 20, Green) &&
    // The ball
    canvas.drawDisk(Pos(centerX, centerY), 50, Blue)
  }

  def click(p: Pos): World = this

  def keyEvent(key: String): World = {
    println(key)
    key match {
      case "SPACE" => PotonWorld(centerX, MaxY)
      case "LEFT" | "h" => PotonWorld(max(MinX, centerX - 5), centerY)
      case "RIGHT" | "l" => PotonWorld(min(centerX + 5, MaxX), centerY)
      case _ => this
    }
  }

  def tick(): World = {
    PotonWorld(centerX, min(centerY + 10, MaxY))
  }
}

// Run this app from sbt: [project lxz; runMain poton.A]
object PotonApp extends App {
  val BallRadius = 50
  val WorldWidth = 800
  val WorldHeight = 600

  val world = PotonWorld(WorldWidth / 2, BallRadius * 2)
  world.bigBang(WorldWidth, WorldHeight, 0.1)
}
