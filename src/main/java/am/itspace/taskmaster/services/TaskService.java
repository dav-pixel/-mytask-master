package am.itspace.taskmaster.services;

import am.itspace.taskmaster.dtos.TaskDto;
import am.itspace.taskmaster.entities.Status;
import am.itspace.taskmaster.entities.Task;
import am.itspace.taskmaster.mappers.MyMapper;
import am.itspace.taskmaster.repositories.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final MyMapper myMapper;

    
    public void addTask(Task task){
        if(task != null && taskRepo.findAll().isEmpty()){
            taskRepo.save(task);
        }
        if (task != null){
            for (Task task1 : taskRepo.findAll()) {
                if ((!task.getTitle().equals(task1.getTitle()) && task.getProject().getId() == task1.getProject().getId())
                || ((!task.getTitle().equals(task1.getTitle())) && task.getProject().getId() != task1.getProject().getId())
                || ((task.getTitle().equals(task1.getTitle())) && task.getProject().getId()!=task1.getProject().getId())){
                    taskRepo.save(task);
                }
            }
        }
    }

    public Page<Task> getAllTasks(int pageNumber, int pageSize, String sortBy) {
        if (sortBy.equals("title")) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
            return taskRepo.findAll(pageable);
        }
        if (sortBy.equals("date")) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
                return taskRepo.findAll(pageable);
        }
        if (sortBy.equals("deadline")) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
                return taskRepo.findAll(pageable);
        }
        return null;
    }

   
    public void updateTask(Integer id, Task task){
        if (id != null && taskRepo.findById(id).isPresent()){
            Task existTask = taskRepo.getOne(id);
            if(existTask.getId() == id){
                task.setId(id);
                taskRepo.save(task);
            }
        }
    }

    public List<TaskDto> getTasksByStatus(Status status){
        if (status != null && status.name().length() != 0){
            List<TaskDto> list = myMapper.tasksToTaskDtos(taskRepo.findByStatus(status));
            if (!list.isEmpty()){
                return list;
            }
        }
        return null;
    }

    public void deleteTask(Integer id){
        if (id != null){
            taskRepo.delete(taskRepo.getOne(id));
        }
    }

}
