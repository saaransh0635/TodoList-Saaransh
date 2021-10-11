package infy.com.example.todolistsaaransh;

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import infy.com.example.todolistsaaransh.Service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoListSaaranshApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    public TaskServiceImpl taskService;

//    @Test
//    public void TestsaveTask(){
//        TaskEntity task = TaskEntity.builder()
//                .task_name("India wins")
//                .priority(5)
//                .date('2021-07-04T14:44:01.884+00:00')
//                .build();
//    }

}
