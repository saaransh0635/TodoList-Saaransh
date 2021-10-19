package infy.com.example.todolistsaaransh.Model;

/**
 * This class is for creating task table as an entity
 * Model Layer
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;


@Entity
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Data
    @Table(name="task")
    public class TaskEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotNull
        private String task_name;

        @NotNull
        int priority;
        private Date date;

        /**
         * This method is for displaying the task name
         * @return Name of the task
         */
        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }
        /**
         * This method is for displaying the prioritee of the task
         * @return prioritee of the task
         */
        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        /**
         * This method is for displaying the last date of the task to be completed
         * @return Deadline of the task
         */
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
