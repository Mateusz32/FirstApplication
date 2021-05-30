package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldSaveTaskTest() {
        //GIVEN
        Task task = new Task(1L, "title", "content");

        //WHEN
        when(taskRepository.save(any())).thenReturn(task);

        Task saveTask = service.saveTask(task);
        Long id = saveTask.getId();

        //THEN
        assertEquals(1L, id);
    }

    @Test
    public void shouldGetTaskTest() {
        //GIVEN
        Task task = new Task(1L, "title", "content");

        //WHEN
        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(task));
        Long id = task.getId();

        Optional<Task> getTask = service.getTask(id);
        Long idTask = getTask.get().getId();

        //THEN
        assertEquals(id, idTask);
    }

    @Test
    public void shouldGetTasksListTest() {
        //GIVEN
        Task task = new Task(1L, "title", "content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        //WHEN
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> listTasks = service.getAllTasks();

        //THEN
        assertEquals(1, listTasks.size());
    }

    @Test
    public void shouldDeleteTaskTest() {
        //GIVEN
        Task task = new Task(1L, "title", "content");
        Long id = task.getId();

        //WHEN
        service.deleteTask(id);

        //THEN
        assertFalse(taskRepository.existsById(id));
        verify(taskRepository, times(1)).deleteById(id);
    }
}