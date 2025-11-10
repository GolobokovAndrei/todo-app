import org.example.TodoList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TodoListTest {
    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add(" task1 ");
        assertEquals(1, t.size());
        assertTrue(t.getAll().getFirst().contains("task1"));
    }
    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }
    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add(" ");
        assertEquals(0, t.size());
    }
    
    @Test
    void clear() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        t.add("task3");
        assertEquals(3, t.size());
        t.clear();
        assertEquals(0, t.size());
        assertTrue(t.getAll().isEmpty());
    }
    
    @Test
    void clearEmptyList() {
        TodoList t = new TodoList();
        t.clear();
        assertEquals(0, t.size());
    }
    
    @Test
    void markDone() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        assertFalse(t.getTask(0).isDone());
        assertTrue(t.markDone(0));
        assertTrue(t.getTask(0).isDone());
        assertFalse(t.getTask(1).isDone());
    }
    
    @Test
    void markDoneInvalidIndex() {
        TodoList t = new TodoList();
        t.add("task1");
        assertFalse(t.markDone(-1));
        assertFalse(t.markDone(10));
    }
    
    @Test
    void markDoneDisplaysCorrectly() {
        TodoList t = new TodoList();
        t.add("task1");
        t.markDone(0);
        String display = t.getAll().get(0);
        assertTrue(display.contains("✓"));
        assertTrue(display.contains("task1"));
    }
    
    @Test
    void search() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("Buy bread");
        t.add("Read book");
        var results = t.search("buy");
        assertEquals(2, results.size());
        assertTrue(results.get(0).contains("milk"));
        assertTrue(results.get(1).contains("bread"));
    }
    
    @Test
    void searchCaseSensitive() {
        TodoList t = new TodoList();
        t.add("Buy MILK");
        t.add("buy bread");
        var results = t.search("BUY");
        assertEquals(2, results.size());
    }
    
    @Test
    void searchNoResults() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        var results = t.search("nonexistent");
        assertTrue(results.isEmpty());
    }
    
    @Test
    void searchEmptyQuery() {
        TodoList t = new TodoList();
        t.add("task1");
        var results = t.search("");
        assertTrue(results.isEmpty());
    }
    
    @Test
    void searchNullQuery() {
        TodoList t = new TodoList();
        t.add("task1");
        var results = t.search(null);
        assertTrue(results.isEmpty());
    }
    
    @Test
    void searchReturnsIndexes() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        t.add("task3");
        var results = t.search("task");
        assertEquals(3, results.size());
        assertTrue(results.get(0).startsWith("0:"));
        assertTrue(results.get(1).startsWith("1:"));
        assertTrue(results.get(2).startsWith("2:"));
    }
    
    @Test
    void searchFindsDoneTasks() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("Buy bread");
        t.markDone(0);
        var results = t.search("buy");
        assertEquals(2, results.size());
        assertTrue(results.get(0).contains("✓"));
        assertTrue(results.get(1).contains("[ ]"));
    }
    
    @Test
    void markDoneTwice() {
        TodoList t = new TodoList();
        t.add("task1");
        assertTrue(t.markDone(0));
        assertTrue(t.getTask(0).isDone());
        assertTrue(t.markDone(0)); // можно пометить дважды
        assertTrue(t.getTask(0).isDone());
    }
    
    @Test
    void clearAfterMarkDone() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");
        t.markDone(0);
        t.clear();
        assertEquals(0, t.size());
        assertTrue(t.getAll().isEmpty());
    }
    
    @Test
    void searchPartialMatch() {
        TodoList t = new TodoList();
        t.add("Buy groceries");
        t.add("Read book");
        var results = t.search("gro");
        assertEquals(1, results.size());
        assertTrue(results.get(0).contains("groceries"));
    }
}