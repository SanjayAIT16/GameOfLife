package com.jobik.gameoflife.gameOfLife

import androidx.annotation.Keep
import com.jobik.gameoflife.helpers.ArrayHelper
import com.jobik.gameoflife.gameOfLife.GameOfLife.Companion.GameOfLifeUnitState
import kotlin.random.Random


class GameOfLife {
    companion object {
        @Keep
        enum class GameOfLifeUnitState() {
            Alive,
            Dead,
            Empty
        }

        fun getNumberOfNeighbors(row: Int, col: Int, list: List<List<GameOfLifeUnitState>>): Int {
            val rowStart = maxOf(0, row - 1)
            val rowEnd = minOf(list.size - 1, row + 1)
            val colStart = maxOf(0, col - 1)
            val colEnd = minOf(list[0].size - 1, col + 1)

            var neighbors = 0
            for (i in rowStart..rowEnd) {
                for (j in colStart..colEnd) {
                    if (i != row || j != col) {
                        if (list[i][j] == GameOfLifeUnitState.Alive) neighbors++
                    }
                }
            }

            return neighbors
        }

        data class GameOfLifeStepSettings(
            val neighborsForReviving: Int = 3,
            val minimumNeighborsForAlive: Int = 2,
            val maximumNeighborsForAlive: Int = 3,
        )

        fun makeOneStepGameOfLife(
            currentState: List<List<GameOfLifeUnitState>>,
            settings: GameOfLifeStepSettings = GameOfLifeStepSettings()
        ): List<List<GameOfLifeUnitState>> {
            val newState = cloneGameState(currentState)
            for (row in currentState.indices) {
                for (col in currentState[row].indices) {
                    val numberOfNeighbors = getNumberOfNeighbors(list = currentState, row = row, col = col)
                    if (numberOfNeighbors > settings.maximumNeighborsForAlive || numberOfNeighbors < settings.minimumNeighborsForAlive) {
                        if (newState[row][col] == GameOfLifeUnitState.Alive)
                            newState[row][col] = GameOfLifeUnitState.Dead
                    } else if (numberOfNeighbors == settings.neighborsForReviving) {
                        newState[row][col] =
                            GameOfLifeUnitState.Alive
                    }
                }
            }
            return newState
        }

        fun countAlive(newStateOfGame: List<List<GameOfLifeUnitState>>): Int {
            var aliveCount = 0
            for (row in newStateOfGame.indices) {
                for (col in newStateOfGame.first().indices) {
                    if (newStateOfGame[row][col] == GameOfLifeUnitState.Alive) aliveCount++
                }
            }
            return aliveCount
        }

        fun cloneGameState(list: List<List<GameOfLifeUnitState>>): MutableList<MutableList<GameOfLifeUnitState>> {
            val newArray = ArrayHelper.generateTwoDimList(
                rows = list.size,
                cols = list[list.size - 1].size,
                initialValue = GameOfLifeUnitState.Empty
            )
            for (row in list.indices) {
                for (col in list[row].indices) {
                    newArray[row][col] = list[row][col]
                }
            }
            return newArray
        }

        fun makeRandomStartStates(state: List<List<GameOfLifeUnitState>>): List<List<GameOfLifeUnitState>> {
            val newState = cloneGameState(state)

            for (row in state.indices) {
                for (col in state[row].indices) {
                    newState[row][col] =
                        if (Random.nextBoolean()) GameOfLifeUnitState.Alive else continue
                }
            }
            return newState
        }
    }
}