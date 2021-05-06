package am.itspace.taskmaster.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne (cascade = CascadeType.MERGE)
    private am.itspace.taskmaster.entities.Task task;
    @Column(name = "start_date_time")
    private long startDateTime;
    @Column(name = "end_date_time")
    private long endDateTime;
    @OneToOne (cascade = CascadeType.MERGE)
    private User user;



}