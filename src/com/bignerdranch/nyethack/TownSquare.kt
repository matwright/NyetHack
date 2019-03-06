package com.bignerdranch.nyethack

class TownSquare : Room("Town Square") {
    final override  fun load() = "The villagers rally and cheer as you enter!"
    override val dangerLevel = super.dangerLevel - 3
    private fun ringBell() = "The bell tower announced your arrival. $bellSound"
    private var bellSound = "GWONG"
}