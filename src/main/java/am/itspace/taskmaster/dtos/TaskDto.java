package am.itspace.taskmaster.dtos;

import am.itspace.taskmaster.entities.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("deadline")
    private Date deadline;
    @JsonProperty("project")
    private ProjectDto projectDto;
    @JsonProperty("log_time")
    private Double logTime;
    @JsonProperty("status")
    private Status status;


}
