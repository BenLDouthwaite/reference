package ctci;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListsTest {

    @Test
    public void removeDuplicates() {
        // given
        Node root = new Node(1);
        root.appendToTail(2, 10, 20, 1, 2, 1, 2, 3);

        // when
        Node result = LinkedLists.removeDuplicates(root);

        // then
        List<Integer> dataList = LinkedLists.getAllValues(result);
        assertThat(dataList).isEqualTo(List.of(1, 2, 10, 20, 3));
    }

    @Test
    public void removeDuplicates_allDuplicates() {
        // given
        Node root = new Node(1);
        root.appendToTail(1,1,1,1,1,1);

        // when
        Node result = LinkedLists.removeDuplicates(root);

        // then
        List<Integer> dataList = LinkedLists.getAllValues(result);
        assertThat(dataList).isEqualTo(List.of(1));
    }

    @Test
    public void removeDuplicatesNoBuffer_allDuplicates() {
        // given
        Node root = new Node(1);
        root.appendToTail(2, 10, 20, 1, 2, 1, 2, 3);

        // when
        Node result = LinkedLists.removeDuplicatesNoBuffer(root);

        // then
        List<Integer> dataList = LinkedLists.getAllValues(result);
        assertThat(dataList).isEqualTo(List.of(10, 20, 1, 2, 3));
    }
}