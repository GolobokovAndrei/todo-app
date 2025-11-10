package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList {
    private final List<Task> items = new ArrayList<>();
    
    public static class Task {
        private String text;
        private boolean done;
        
        public Task(String text) {
            this.text = text;
            this.done = false;
        }
        
        public String getText() {
            return text;
        }
        
        public boolean isDone() {
            return done;
        }
        
        public void setDone(boolean done) {
            this.done = done;
        }
        
        @Override
        public String toString() {
            return (done ? "[✓] " : "[ ] ") + text;
        }
    }
    
    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(new Task(item));
            }
        }
    }
    
    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }
    
    public List<String> getAll() {
        return items.stream()
                .map(Task::toString)
                .collect(Collectors.toList());
    }
    
    public int size() {
        return items.size();
    }
    
    public void clear() {
        items.clear();
    }
    
    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).setDone(true);
            return true;
        }
        return false;
    }
    
    public List<String> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String lowerQuery = query.toLowerCase();
        List<String> results = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Task task = items.get(i);
            if (task.getText().toLowerCase().contains(lowerQuery)) {
                results.add(i + ": " + task.toString());
            }
        }
        return results;
    }
    
    public Task getTask(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }
}