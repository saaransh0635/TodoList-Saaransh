package infy.com.example.todolistsaaransh.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import infy.com.example.todolistsaaransh.Model.TaskEntity;
import infy.com.example.todolistsaaransh.Repository.TaskRepository;
import infy.com.example.todolistsaaransh.Service.TaskServiceImpl;
import infy.com.example.todolistsaaransh.util.JsonUtil;
import net.minidev.json.JSONUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskServiceImpl service;

    @MockBean
    TaskRepository taskRepository;

    @InjectMocks
    TaskController taskController;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TaskEntity taskEntity;

    private final static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");



    TaskEntity task1 = new TaskEntity(1l, "Task 1", 23, dateFormat.parse("01-01-1994"));
    TaskEntity task_2 = new TaskEntity(2l, "Task 2", 27, dateFormat.parse("01-01-1994"));
    TaskEntity task_3 = new TaskEntity(3l, "Task 3", 31, dateFormat.parse("01-01-1994"));

    TaskControllerTest() throws ParseException {
    }


    @Test
        public void saveTaskEntity() throws Exception {
            TaskEntity task =  getValidTask();
            String taskJson = mapper.writeValueAsString(task);

            mockMvc.perform(post("/api/tasks/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(taskJson))
                    .andExpect(status().isCreated());
    }

    TaskEntity getValidTask() throws ParseException {
        return TaskEntity.builder()
                .task_name("New Task")
                .priority(5)
                .date(dateFormat.parse("01-05-2021"))
                .build();

    }
        @Test
    public void getAllTaskEntity() throws Exception {
                List<TaskEntity> records = new ArrayList<>(Arrays.asList(task1, task_2, task_3));
                Mockito.when(service.getAllTasks()).thenReturn(records);
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/tasks")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
                       // .andExpect(jsonPath("$[2].name", is("Jane Doe")));
            }
    @Test
    void getTaskEntityById() throws Exception {
        Mockito.when(service.getTaskById(task1.getId())).thenReturn(task1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priority", is(23)));
    }

    @Test
    void updateTaskEntity() throws Exception {
            TaskEntity task =  getValidTask();
            String taskJson = mapper.writeValueAsString(task);

            mockMvc.perform(put("/api/tasks/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(taskJson))
                    .andExpect(status().isOk());
        }

    @Test
    void deleteTaskEntity()throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}