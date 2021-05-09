package am.itspace.taskmaster.mappers;


import am.itspace.taskmaster.dtos.ProjectDto;
import am.itspace.taskmaster.dtos.TaskDto;
import am.itspace.taskmaster.dtos.UserGetDto;
import am.itspace.taskmaster.entities.Project;
import am.itspace.taskmaster.entities.Task;
import am.itspace.taskmaster.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultMyMapper implements MyMapper {


    @Override
    public UserGetDto userToUserDto(User user) {
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(user.getId());
        userGetDto.setName(user.getName());
        userGetDto.setSurname(user.getSurname());
        userGetDto.setEmail(user.getEmail());
        return userGetDto;
    }

    @Override
    public List<UserGetDto> usersToUserDto(List<User> list) {
        List<UserGetDto> userGetDtoList = new ArrayList<>();
        for (User user: list) {
            UserGetDto userGetDto = userToUserDto(user);
            userGetDtoList.add(userGetDto);

        }
        return userGetDtoList;
    }

    @Override
    public TaskDto taskToTaskDto(Task task) {
        TaskDto taskGetDto = new TaskDto();
        taskGetDto.setId(task.getId());
        taskGetDto.setTitle(task.getTitle());
        taskGetDto.setDeadline(task.getDeadline());
        taskGetDto.setLogTime(task.getLogTime());
        taskGetDto.setStatus(task.getStatus());
        taskGetDto.setDate(task.getDate());
        return taskGetDto;
    }

    @Override
    public ProjectDto projectToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDeadline(project.getDeadline());
        projectDto.setUser(project.getUser());
        return projectDto;
    }

    @Override
    public List<TaskDto> tasksToTaskDtos(List<Task> list) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task : list) {
            TaskDto taskGetDto = taskToTaskDto(task);
            taskDtoList.add(taskGetDto);

        }
        return taskDtoList;
    }
}
