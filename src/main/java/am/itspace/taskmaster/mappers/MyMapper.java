package am.itspace.taskmaster.mappers;

import am.itspace.taskmaster.dtos.ProjectDto;
import am.itspace.taskmaster.dtos.TaskDto;
import am.itspace.taskmaster.dtos.UserGetDto;
import am.itspace.taskmaster.entities.Project;
import am.itspace.taskmaster.entities.Task;
import am.itspace.taskmaster.entities.User;


import java.util.List;


public interface MyMapper {


    UserGetDto userToUserDto(User user);

    List<UserGetDto> usersToUserDto(List<User> list);



    TaskDto taskToTaskDto(Task task);

    ProjectDto projectToProjectDto(Project project);

    List<TaskDto> tasksToTaskDtos(List<Task> list);
}
