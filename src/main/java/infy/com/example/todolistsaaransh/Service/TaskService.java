package infy.com.example.todolistsaaransh.Service;

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.web.PagedResourcesAssembler;

import java.util.List;


public interface TaskService {

        TaskEntity saveTask(TaskEntity task);
        List<TaskEntity> getAllTasks();
        TaskEntity getTaskById(long id);
        TaskEntity updateTask(TaskEntity task, long id);
        void deleteTask(long id);
        List<TaskEntity>findAll(String name);


}
