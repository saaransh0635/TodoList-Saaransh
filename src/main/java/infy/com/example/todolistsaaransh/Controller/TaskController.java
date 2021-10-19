package infy.com.example.todolistsaaransh.Controller;

/***
 * This class is for creating A Controller layer to handle api's that are referred.
 *  Controller class.
 * @author Saaransh Shaw
 */



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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {


    private TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    /**
     * This method is for saving a task details in memory
     * @param task
     * @return task and Http status
     */
    @PostMapping()
    public ResponseEntity<TaskEntity> saveTaskEntity(@RequestBody @Validated TaskEntity task) {
        return new ResponseEntity<TaskEntity>(taskService.saveTask(task), HttpStatus.CREATED);
    }


    /**
     * This method is for displaying all tasks from the memory
     * @return all the tasks
     */
    @GetMapping
    public List<TaskEntity> getAllTaskEntity() {
        return taskService.getAllTasks();
    }

    /**
     * This method is for displaying a specific task by its id.
     * @param taskEntityId
     * @return task and Http status
     */
    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskEntityById(@PathVariable("id")long taskEntityId) {
        return new ResponseEntity<TaskEntity>(taskService.getTaskById(taskEntityId), HttpStatus.OK);
    }

    /**
     * This method is for updating a task details in memory
     * @param id
     * @return task and Http status
     */
    @PutMapping("{id}")
    public ResponseEntity<TaskEntity> updateTaskEntity(@PathVariable("id") long id
            , @RequestBody TaskEntity task) {
        return new ResponseEntity<TaskEntity>(taskService.updateTask(task, id), HttpStatus.OK);
    }

    /**
     * This method is for deleting a task from memory
     * @param id
     * @return message and Http status
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTaskEntity(@PathVariable("id") long id) {

        // delete TaskEntity from DB
        taskService.deleteTask(id);
        return new ResponseEntity<String>("Task deleted successfully!.", HttpStatus.OK);
    }

    /**
     * This method is for searching a task from its name
     * @param name
     * @return task details
     */
    @GetMapping("/search")
    public List<TaskEntity> findAll(@RequestParam Optional<String> name){
        return taskService.findAll(name.orElse("_"));
    }

}


