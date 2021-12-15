package adventofcode2021.day9;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeBasinTest {

    @Test
    public void basinSize_puzzleInput_SameCharactersAdjacent() {
        // given
        List<String> input = List.of(
                "99999",
                "91239",
                "94459",
                "99999"
        );

        // when
        int result = SmokeBasin.basinSize(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void basinSize_puzzleInputSnippet() {
        // given
        List<String> input = List.of(
                "5796798621237995498765434567987542999765679987545679109878999877899789876532123456998999876887899921",
                "4645976434456789349654321298997679898654698987635678998767897656789698765432012347897899865676798799",
                "3234987545978993298795410989998989789543256897646789498756789546896579877842123456976789954345985678",
                "4356798679989999019987329878999798679765345689856991296545890134789467998956899967895698643239874579",
                "6467999789999898934976598967987676568996457999967999987636789345691346789769987898934987651098763456",
                "7598997999987796899989987959876543456789569898998998765125678956910298899898776799123998862987652345",
                "8789876789865685578999876645987665567998689656789876554034899999891989998987564679099789879876543456",
                "9898765498974324456799865534598786788998798645889985432126954987789878987654323567988698989987664678",
                "9989997987543212367987654323459897899549895434569876556437899876599967996543212459876587898998798789",
                "9877989997662101456798765434567998967932987624778989787548999989679459987687301349875456967899899893"
        );

        // when
        int result = SmokeBasin.basinSize(input);

        // then
        assertThat(result).isEqualTo(1134);
    }

    @Test
    public void basinSize() {
        // given
        List<String> input = List.of(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
        );

        // when
        int result = SmokeBasin.basinSize(input);

        // then
        assertThat(result).isEqualTo(1134);
    }

    @Test
    public void lowPointRiskLevel() {
        // given
        List<String> input = List.of(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
        );

        // when
        int result = SmokeBasin.lowPointRiskLevel(input);

        // then
        assertThat(result).isEqualTo(15);
    }
}