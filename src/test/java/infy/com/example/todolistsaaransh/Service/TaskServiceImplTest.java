package infy.com.example.todolistsaaransh.Service;

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import infy.com.example.todolistsaaransh.Repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    TaskEntity task;

    private final static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void saveTask() throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setTask_name("Task 1");
        task.setPriority(4);
        task.setDate(dateFormat.parse("01-01-1994"));


        when(taskRepository.save(ArgumentMatchers.any(TaskEntity.class))).thenReturn(task);

        TaskEntity created = taskService.saveTask(task);

        assertThat(created.getTask_name()).isSameAs(task.getTask_name());
        assertThat(created.getPriority()).isSameAs(task.getPriority());
        verify(taskRepository).save(task);
    }

    @Test
    public void deleteTask() {

        TaskEntity task = new TaskEntity();
        task.setTask_name("Task 1");
        task.setId(1L);

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        taskService.deleteTask(task.getId());
        verify(taskRepository).deleteById(task.getId());
    }


    @Test
    public void updateTasktest() throws ParseException {

        TaskEntity task = new TaskEntity();
        task.setId(12L);
        task.setTask_name("Task 1");
        task.setPriority(5);
        task.setDate(dateFormat.parse("01-01-1994"));

        TaskEntity newTask = new TaskEntity();
        newTask.setTask_name("Updated task");
        newTask.setId(12l);
        newTask.setPriority(4);
        newTask.setDate(dateFormat.parse("01-01-1994"));

        given(taskRepository.findById(task.getId())).willReturn(Optional.of(task));
        taskService.updateTask(newTask, task.getId());

        verify(taskRepository).save(newTask);
        verify(taskRepository).findById(task.getId());

    }


    @Test
    public void getAllTaskTest(){

        List<TaskEntity> tasks = new ArrayList<>();
        tasks.add(new TaskEntity());

        given(taskRepository.findAll()).willReturn(tasks);

        List<TaskEntity> expected = taskService.getAllTasks();

        assertEquals(expected, tasks);
        verify(taskRepository).findAll();


}

    @Test
    public void getTaskByIdTest() throws ParseException {

        final Long id = 1L;

        final TaskEntity task = new TaskEntity(1L,"Task 1",5,dateFormat.parse("01-01-1994"));
        given(taskRepository.findById(id)).willReturn(Optional.of(task));
        final Optional <TaskEntity> expected = Optional.ofNullable(taskService.getTaskById(id));
        assertThat(expected).isNotNull();
    }

    @Test
    public void findAllTest(){
        List<TaskEntity> tasks = new ArrayList<>();
        tasks.add(new TaskEntity());

        given(taskRepository.findByTask_name(task.getTask_name())).willReturn(tasks);

       taskService.findAll(task.getTask_name());

        verify(taskRepository).findByTask_name(task.getTask_name());

    }

        }


