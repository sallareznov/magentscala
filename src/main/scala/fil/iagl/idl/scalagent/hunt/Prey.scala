package fil.iagl.idl.scalagent.hunt

import fil.iagl.idl.scalagent.core._

class Prey(environment: Environment) extends Agent {

  agentType = AgentType.PREY
  var direction = Direction.EAST
  var stepX = 0
  var stepY = 0

  override def doIt(agentsShapes: AgentsShapes): Unit = {
    getDirection()
    position = getNextPosition(environment)
    agentsShapes.relocateShape(this, position.x, position.y)
  }

  def getDirection(): Unit = {
    direction match {
      case Direction.NORTH =>
        stepX = 0
        stepY = -1
      case Direction.EAST =>
        stepX = 1
        stepY = 0
      case Direction.SOUTH =>
        stepX = 0
        stepY = 1
      case Direction.WEST =>
        stepX = -1
        stepY = 0
      case _ => ()
    }
  }

  def getNextPosition(environment: Environment): Position = {
    val newX = if ((position.x + stepX) >= 0) position.x + stepX else (position.x + stepX) + environment.width
    val newY = if ((position.y + stepY) >= 0) position.y + stepY else (position.y + stepY) + environment.height
    val nextPosition = Position(newX % environment.width, newY % environment.height)
    if (positionIsEmpty(nextPosition, environment))
      nextPosition
    else
      position
  }

  def positionIsEmpty(newPosition: Position, environment: Environment): Boolean = environment.getAgent(newPosition.x, newPosition.y).isEmpty

}

object Prey {

  def apply(environment: Environment) = new Prey(environment)

}