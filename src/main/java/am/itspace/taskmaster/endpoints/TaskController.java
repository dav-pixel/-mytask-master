package am.itspace.taskmaster.endpoints;

import am.itspace.taskmaster.dtos.TaskDto;
import am.itspace.taskmaster.entities.Status;
import am.itspace.taskmaster.entities.Task;
import am.itspace.taskmaster.mappers.MyMapper;
import am.itspace.taskmaster.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final MyMapper mapper;

    @GetMapping("/all/{sortBy}/{pageNo}")
    public List<TaskDto> getAllTasks(@PathVariable("pageNo") int pageNo, @PathVariable("sortBy") String sortBy){
        int size = 20;
        Page<Task> page = taskService.getAllTasks(pageNo, size, sortBy);
        List<Task> list = page.getContent();
        return mapper.tasksToTaskDtos(list);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<TaskDto>> getByStatus(@PathVariable("status") Status status){
        return new ResponseEntity<>(taskService.getTasksByStatus(status), HttpStatus.OK);
    }

    @PostMapping
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable("id") int id, @RequestBody Task task){
        taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") int id){
        taskService.deleteTask(id);
    }

}
