package adventofcode2021.day1;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SonarSweepTest {

    @Test
    public void sweepSliding_puzzleExample() {
        // given
        List<Integer> depthReadings = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        // when
        int largerMeasurements = SonarSweep.sweepSliding(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(5);
    }

    @Test
    public void sweepSliding_allDecreasing() {
        // given
        List<Integer> depthReadings = List.of(100, 90, 80, 70, 60, 50, 40, 30);

        // when
        int largerMeasurements = SonarSweep.sweepSliding(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(0);
    }

    @Test
    public void sweepSliding_allIncreasing() {
        // given
        List<Integer> depthReadings = List.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);

        // when
        int largerMeasurements = SonarSweep.sweepSliding(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(8);
    }

    @Test
    public void sweep_puzzleInput_mixedSteps() {
        // given
        List<Integer> depthReadings = List.of(10, 50, 20, 60, 30, 70);

        // when
        int largerMeasurements = SonarSweep.sweep(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(3);
    }

    @Test
    public void sweep_puzzleInput_allDecrements() {
        // given
        List<Integer> depthReadings = List.of(4, 3, 2, 1);

        // when
        int largerMeasurements = SonarSweep.sweep(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(0);
    }

    @Test
    public void sweep_puzzleInput_allIncrements() {
        // given
        List<Integer> depthReadings = List.of(1, 2, 3, 4);

        // when
        int largerMeasurements = SonarSweep.sweep(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(3);
    }

    @Test
    public void sweep_puzzleInput_singleValue() {
        // given
        List<Integer> depthReadings = List.of(1);

        // when
        int largerMeasurements = SonarSweep.sweep(depthReadings);

        // then
        assertThat(largerMeasurements).isEqualTo(0);
    }
}