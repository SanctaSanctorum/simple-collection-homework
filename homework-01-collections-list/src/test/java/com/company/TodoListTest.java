package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Программа для хранения списка дел")
public class TodoListTest {

    private TodoList todoList;

    private final List<String> testTodos = List.of("buy milk", "learn java", "create app", "send email", "write issue");

    @BeforeEach
    public void setUp() {
        todoList = new TodoList();
    }

    @Test
    @DisplayName("Добавление одного дела")
    void addSingleTodo() {
        final String todo = "first added todo";
        List<String> expected = List.of(todo);

        todoList.add(todo);
        assertEquals(expected, todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление трех дел -> проверка порядка добавления дел")
    void addThreeTodos() {
        List<String> todos = getRandomTodos(3);
        todos.forEach(todoList::add);
        assertEquals(todos, todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление трех дел -> добавление дела на существующий индекс")
    void addTodoToExistIndex() {
        String addedTodo = "new todo";
        List<String> todos = getRandomTodos(3);
        todos.forEach(todoList::add);
        todoList.add(1, addedTodo);
        assertEquals(List.of(todos.get(0), addedTodo, todos.get(1), todos.get(2)), todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление трех дел -> добавление дела на НЕ существующий индекс")
    void addTodoToNotExistIndex() {
        String addedTodo = "new todo";
        List<String> todos = getRandomTodos(3);
        todos.forEach(todoList::add);
        todoList.add(4, addedTodo);
        assertEquals(List.of(todos.get(0), todos.get(1), todos.get(2), addedTodo), todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление трех дел -> удаление дела с существующим индексом")
    void deleteExistTodo() {
        List<String> todos = getRandomTodos(3);
        todos.forEach(todoList::add);
        todoList.delete(1);
        assertEquals(List.of(todos.get(0), todos.get(2)), todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление пяти дел -> удаление дела с НЕ существующим индексом")
    void deleteNotExistTodo() {
        List<String> todos = getRandomTodos(5);
        todos.forEach(todoList::add);
        todoList.delete(5);
        assertEquals(todos, todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление двух дел -> изменение существующего дела")
    void editExistTodo() {
        String editedTodo = "new todo";
        List<String> todos = getRandomTodos(2);
        todos.forEach(todoList::add);
        todoList.edit(1, editedTodo);
        assertEquals(List.of(todos.get(0), editedTodo), todoList.getTodos());
    }

    @Test
    @DisplayName("Добавление двух дел -> изменение НЕ существующего дела")
    void editNotExistTodo() {
        List<String> todos = getRandomTodos(2);
        todos.forEach(todoList::add);
        todoList.edit(3, "");
        assertEquals(todos, todoList.getTodos());
    }

    private List<String> getRandomTodos(int amount) {
        return Stream.generate(() -> Math.random() * testTodos.size())
                .limit(amount)
                .map(Double::intValue)
                .map(testTodos::get)
                .collect(Collectors.toList());
    }
}