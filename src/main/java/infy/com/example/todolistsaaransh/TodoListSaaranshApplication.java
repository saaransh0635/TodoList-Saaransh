package infy.com.example.todolistsaaransh;

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import infy.com.example.todolistsaaransh.Repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoListSaaranshApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListSaaranshApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(TaskRepository taskRepository){
//        return args -> {
//            taskRepository.save(new TaskEntity(null,"abc",1,2021-10-05T10:14:14.188Z));
//
//
//        };
//    }

}
