package infy.com.example.todolistsaaransh.Service;

/**
 * This class is for creating a service layer for the functions required in our application
 */

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import infy.com.example.todolistsaaransh.Repository.TaskRepository;
import infy.com.example.todolistsaaransh.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private final TaskRepository taskRepository;

    /**
     * This method is a Constructor injection
     *
     */
    public TaskServiceImpl(TaskRepository taskRepository){
        super();
        this.taskRepository = taskRepository;
    }


    /**
     * This method is for saving a task
     * @return task
     */
    @Override
    public TaskEntity saveTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    /**
     * This method is for displaying all the tasks from our memory
     * uses findall method from JpaRepository
     * @return Name of the task
     */
    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * This method is for displaying the task details by its id, if id is not found in the database hrn throws error
     * @param id
     * @return task
     */
    @Override
    public TaskEntity getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    /**
     * This method is for updating the task details stored in the memory
     * @param id
     * @return task
     */
    @Override
    public TaskEntity updateTask(TaskEntity task, long id) {
       TaskEntity existingTask = taskRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException("Task","Id",id));
       existingTask.setTask_name(task.getTask_name());
       existingTask.setPriority(task.getPriority());
       existingTask.setDate(task.getDate());
        taskRepository.save(existingTask);
        return existingTask;
    }

    /**
     * This method is for deleting a task by its id
     *
     *
     */
    @Override
    public void deleteTask(long id) {
        taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task", "Id", id));
        taskRepository.deleteById(id);

    }

    /**
     * This method is for displaying a task by its name
     *
     *
     */
    @Override
    public List<TaskEntity> findAll(String name) {
        return taskRepository.findByTask_name(name);
    }


}


