package infy.com.example.todolistsaaransh.Controller;

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import infy.com.example.todolistsaaransh.Repository.TaskRepository;
import infy.com.example.todolistsaaransh.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }


    // build create TaskEntity REST API
    @PostMapping()
    public ResponseEntity<TaskEntity> saveTaskEntity(@RequestBody TaskEntity task) {
        return new ResponseEntity<TaskEntity>(taskService.saveTask(task), HttpStatus.CREATED);
    }


    // build get all TaskEntitys REST API
    @GetMapping
    public List<TaskEntity> getAllTaskEntity() {
        return taskService.getAllTasks();
    }

    // build get TaskEntity by id REST API
    // http://localhost:8080/api/TaskEntitys/1
    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskEntityById(@PathVariable("id")long TaskEntityId) {
        return new ResponseEntity<TaskEntity>(taskService.getTaskById(TaskEntityId), HttpStatus.OK);
    }

    // build update TaskEntity REST API
    // http://localhost:8080/api/TaskEntitys/1
    @PutMapping("{id}")
    public ResponseEntity<TaskEntity> updateTaskEntity(@PathVariable("id") long id
            , @RequestBody TaskEntity task) {
        return new ResponseEntity<TaskEntity>(taskService.updateTask(task, id), HttpStatus.OK);
    }

    // build delete TaskEntity REST API
    // http://localhost:8080/api/TaskEntitys/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTaskEntity(@PathVariable("id") long id) {

        // delete TaskEntity from DB
        taskService.deleteTask(id);
        return new ResponseEntity<String>("Task deleted successfully!.", HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<TaskEntity> findAll(@RequestParam Optional<String> name){
        return taskService.findAll(name.orElse("_"));
    }

}


