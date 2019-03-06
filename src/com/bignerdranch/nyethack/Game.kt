package com.bignerdranch.nyethack

import java.lang.Exception
import kotlin.system.exitProcess

object Game {
    private var gameInPlay:Boolean=true;
    private var currentRoom: Room = TownSquare()
    private val player = Player("Madrigal")
    private var worldMap= listOf(
        listOf(currentRoom,Room("Tavern"),Room("Back Room")),
        listOf(Room("Long Corridor"),Room("Generic Room"))

    )

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")
        if(player.healthPoints<=0){
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if(monster.healthPoints<=0){
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster=null
        }
    }

    private fun fight()= currentRoom.monster?.let{
        while (player.healthPoints>0 && it.healthPoints>0){
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete"
    }?:"There's nothing here to fight"


    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }

    private fun move(directionInput: String)=try {
        val direction = Direction.valueOf(directionInput.toUpperCase())
        val newPosition=direction.updateCoordinate(player.currentPosition)
        if(!newPosition.isInBounds){
            throw IllegalStateException("$direction is out of of bounds.")
        }
            val newRoom= worldMap[newPosition.y][newPosition.x]
        player.currentPosition=newPosition
        currentRoom=newRoom
        "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
    }catch (e:Exception){
        "Invalid direction: $directionInput"
    }

    private class GameInput(arg: String?){
        private val input=arg?:""
        val command =input.split(" ")[0]
        val argument =input.split(" ").getOrElse(1,{""})

        fun processCommand()= when (command.toLowerCase()){
            "move"-> move(argument)
            "quit"->quitGame()
            "map"->map()
            "fight"-> fight()
            else->commandNotFound()
        }

        private fun map(){

            worldMap.forEach {
                it.forEach {  if(it== currentRoom)
                    print("X")
                else print("O") }
                print("\n")

            }
        }
        private fun quitGame(){ println("Fare thee well\n");gameInPlay=false}
        private fun commandNotFound()="I'm not quite sure what you're trying to do!"
    }
    fun play() {
        while (gameInPlay) {
            //play nyethack
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus(player)
            print("> Enter your command \n")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(
        player: Player
    ) {
        //com.bignerdranch.nyethack.getPlayer status
        println(
            "(Aura: {${player.auraColor()}}) " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
        )

        println("$player ${player.formatHealthStatus()}")

    }
}

fun main(args: Array<String>) {


    Game.play()
}







