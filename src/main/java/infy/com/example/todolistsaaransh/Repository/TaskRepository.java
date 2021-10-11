package infy.com.example.todolistsaaransh.Repository;

import infy.com.example.todolistsaaransh.Model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {


    @Query("select t FROM TaskEntity t where t.task_name like %?1%")
    List<TaskEntity> findByTask_name(String name);


}
