package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void listTasksDtoMapToTasksTest() {
        //GIVEN
        Task task1 = new Task(1L, "title1", "content1");
        Task task2 = new Task(2L, "title2", "content2");
        List<Task> taskList = new ArrayList<>();

        taskList.add(task1);
        taskList.add(task2);

        //WHEN
        List<TaskDto> mappedTaskListDto = taskMapper.mapToTaskDtoList(taskList);

        Long id1 = mappedTaskListDto.get(0).getId();
        String title1 = mappedTaskListDto.get(0).getTitle();
        String content1 = mappedTaskListDto.get(0).getContent();

        Long id2 = mappedTaskListDto.get(1).getId();
        String title2 = mappedTaskListDto.get(1).getTitle();
        String content2 = mappedTaskListDto.get(1).getContent();

        //THEN
        assertEquals(1L, id1);
        assertEquals("title1", title1);
        assertEquals("content1", content1);

        assertEquals(2L, id2);
        assertEquals("title2", title2);
        assertEquals("content2", content2);
    }

    @Test
    public void tasksDtoMapToTaskTest() {
        //GIVEN
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");

        //WHEN
        Task mappedTaskDto = taskMapper.mapToTask(taskDto);

        Long id1 = mappedTaskDto.getId();
        String title1 = mappedTaskDto.getTitle();
        String content1 = mappedTaskDto.getContent();

        //THEN
        assertEquals(1L, id1);
        assertEquals("title1", title1);
        assertEquals("content1", content1);
    }

    @Test
    public void tasksMapToTaskDtoTest() {
        //GIVEN
        Task task = new Task(1L, "title1", "content1");

        //WHEN
        TaskDto mappedTask = taskMapper.mapToTaskDto(task);

        Long id1 = mappedTask.getId();
        String title1 = mappedTask.getTitle();
        String content1 = mappedTask.getContent();

        //THEN
        assertEquals(1L, id1);
        assertEquals("title1", title1);
        assertEquals("content1", content1);
    }
}