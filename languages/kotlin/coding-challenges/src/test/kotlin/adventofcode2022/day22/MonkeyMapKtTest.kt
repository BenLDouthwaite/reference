package adventofcode2022.day22

import adventofcode2022.day22.Direction.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

private const val DAY = "day22"

class MonkeyMapKtTest {

    companion object {
        
        @JvmStatic
        fun wrappedPositions(): List<Arguments> {
            
            val a = 0
            val b = 49
            val c = 50
            val d = 99
            val e = 100
            val f = 149
            
            val g = 0
            val h = 49
            val i = 50
            val j = 99
            val k = 100
            val l = 149
            val m = 150
            val n = 199

            return listOf(
                // Block 2,0 = 1
                Arguments.of(Pos(e, g), NORTH, Pos(a, n), NORTH),
                Arguments.of(Pos(f, g), NORTH, Pos(b, n), NORTH),

                Arguments.of(Pos(f, g), EAST, Pos(d, l), WEST),
                Arguments.of(Pos(f, h), EAST, Pos(d, k), WEST),

                Arguments.of(Pos(e, h), SOUTH, Pos(d, i), WEST),
                Arguments.of(Pos(f, h), SOUTH, Pos(d, j), WEST),
                
                // In Block 1,0 = 2
                Arguments.of(Pos(c, g), NORTH, Pos(a, m), EAST),
                Arguments.of(Pos(d, g), NORTH, Pos(a, n), EAST),

                Arguments.of(Pos(c, g), WEST, Pos(a, l), EAST),
                Arguments.of(Pos(c, h), WEST, Pos(a, k), EAST),

                // Block 1, 1 = 3
                Arguments.of(Pos(d, i), EAST, Pos(e, h), NORTH),
                Arguments.of(Pos(d, j), EAST, Pos(f, h), NORTH),

                Arguments.of(Pos(c, i), WEST, Pos(a, k), SOUTH),
                Arguments.of(Pos(c, j), WEST, Pos(b, k), SOUTH),

                // Block 1, 2 = 4
                Arguments.of(Pos(d, k), EAST, Pos(f, h), WEST),
                Arguments.of(Pos(d, l), EAST, Pos(f, g), WEST),

                Arguments.of(Pos(c, l), SOUTH, Pos(b, m), WEST),
                Arguments.of(Pos(d, l), SOUTH, Pos(b, n), WEST),

                // Block 0, 2 = 5
                Arguments.of(Pos(a, k), NORTH, Pos(c, i), WEST),
                Arguments.of(Pos(b, k), NORTH, Pos(c, j), WEST),

                Arguments.of(Pos(a, k), WEST, Pos(c, h), EAST),
                Arguments.of(Pos(a, l), WEST, Pos(c, g), EAST),

                // Block 0, 2 = 6
                Arguments.of(Pos(b, m), EAST, Pos(c, l), NORTH),
                Arguments.of(Pos(b, n), EAST, Pos(d, l), NORTH),

                Arguments.of(Pos(a, m), WEST, Pos(c, g), SOUTH),
                Arguments.of(Pos(a, n), WEST, Pos(d, g), SOUTH),

                Arguments.of(Pos(a, n), SOUTH, Pos(e, g), SOUTH),
                Arguments.of(Pos(b, n), SOUTH, Pos(f, g), SOUTH),
            )
        }

        @JvmStatic
        fun wrappedPositionsExample() = listOf(
            // Block 2,0 = 1
            Arguments.of(Pos(8, 0), NORTH, Pos(3, 4), SOUTH),
            Arguments.of(Pos(11, 0), NORTH, Pos(0, 4), SOUTH),

            Arguments.of(Pos(11, 0), EAST, Pos(15, 11), WEST),
            Arguments.of(Pos(11, 3), EAST, Pos(15, 8), WEST),

            Arguments.of(Pos(8, 0), WEST, Pos(4, 4), SOUTH),
            Arguments.of(Pos(8, 3), WEST, Pos(7, 4), SOUTH),

            // Block 1,0 = 2
            Arguments.of(Pos(0, 4), NORTH, Pos(11, 0), SOUTH),
            Arguments.of(Pos(3, 4), NORTH, Pos(8, 0), SOUTH),
            
            Arguments.of(Pos(0, 7), SOUTH, Pos(11, 11), NORTH),
            Arguments.of(Pos(3, 7), SOUTH, Pos(8, 11), NORTH),

            Arguments.of(Pos(0, 4), WEST, Pos(15, 11), NORTH),
            Arguments.of(Pos(0, 7), WEST, Pos(12, 11), NORTH),
            
            // Block 1,1 = 3
            Arguments.of(Pos(4, 4), NORTH, Pos(8, 0), EAST),
            Arguments.of(Pos(7, 4), NORTH, Pos(8, 3), EAST),

            Arguments.of(Pos(4, 7), SOUTH, Pos(8, 11), EAST),
            Arguments.of(Pos(7, 7), SOUTH, Pos(8, 8), EAST),
            
            // Block 2,1 = 4
            Arguments.of(Pos(11, 4), EAST, Pos(15, 8), SOUTH),
            Arguments.of(Pos(11, 7), EAST, Pos(12, 8), SOUTH),

            // Block 2,2 = 5
            Arguments.of(Pos(8, 8), WEST, Pos(7, 7), NORTH),
            Arguments.of(Pos(8, 11), WEST, Pos(4, 7), NORTH),

            Arguments.of(Pos(8, 11), SOUTH, Pos(3, 7), NORTH),
            Arguments.of(Pos(11, 11), SOUTH, Pos(0, 7), NORTH),
            
            // Block 3,2 = 6
            Arguments.of(Pos(12, 8), NORTH, Pos(11, 7), WEST),
            Arguments.of(Pos(15, 8), NORTH, Pos(11, 4), WEST),

            Arguments.of(Pos(15, 8), EAST, Pos(11, 3), WEST),
            Arguments.of(Pos(15, 11), EAST, Pos(11, 0), WEST),

            Arguments.of(Pos(12, 11), SOUTH, Pos(0, 7), EAST),
            Arguments.of(Pos(15, 11), SOUTH, Pos(0, 4), EAST),
        )
    }
    
    @ParameterizedTest
    @MethodSource("wrappedPositions")
    fun getWrappedPosAndDirectionTest(
        startPos: Pos,
        startDir: Direction,
        endPos: Pos, 
        endDir: Direction
    ) {

        // when
        val (wrappedPos, newDirection) = getWrappedPosAndDirection(
            startPos,
            startDir
        )
        
        // then
        assertEquals(endPos, wrappedPos)
        assertEquals(endDir, newDirection)
    }

    @ParameterizedTest
    @MethodSource("wrappedPositionsExample")
    fun getWrappedPosAndDirectionExampleTest(
        startPos: Pos,
        startDir: Direction,
        endPos: Pos,
        endDir: Direction
    ) {

        // when
        val (wrappedPos, newDirection) = getWrappedPosAndDirectionExample(
            startPos,
            startDir,
        )

        // then
        assertEquals(endPos, wrappedPos)
        assertEquals(endDir, newDirection)
    }
        
}