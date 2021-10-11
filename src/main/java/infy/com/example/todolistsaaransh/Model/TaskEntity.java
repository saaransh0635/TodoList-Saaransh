package infy.com.example.todolistsaaransh.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


    @Entity
    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @Table(name="task")
    public class TaskEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String task_name;
        private int priority;

        private Date date;

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
