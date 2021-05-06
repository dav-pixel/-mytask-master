package am.itspace.taskmaster.mappers;

import am.itspace.taskmaster.dtos.ProjectDto;
import am.itspace.taskmaster.dtos.TaskDto;
import am.itspace.taskmaster.dtos.UserGetDto;
import am.itspace.taskmaster.entities.Project;
import am.itspace.taskmaster.entities.Task;
import am.itspace.taskmaster.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {User.class, Task.class, Project.class})
public interface MyMapper {

    UserGetDto userToUserDto(User user);
    List<UserGetDto> usersToUserDto(List<User> list);
    @Mapping(target = "projectDto", source = "task.project")
    TaskDto TaskToTaskDto(Task task);
    ProjectDto projectToProjectDto(Project project);
    List<TaskDto> tasksToTaskDtos(List<Task> list);
}
