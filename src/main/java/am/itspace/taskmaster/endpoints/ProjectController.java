package am.itspace.taskmaster.endpoints;

import am.itspace.taskmaster.entities.Project;
import am.itspace.taskmaster.repositories.ProjectRepo;
import am.itspace.taskmaster.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepo projectRepo;

    @PostMapping
    public void addProject(@RequestBody Project project){
        projectService.addProject(project);
    }

    @PutMapping("{id}")
    public void updateProject(@PathVariable("id") int id, @RequestBody Project project){
        projectService.updateProject(id, project);
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable("id") int id){
        projectService.deleteProject(id);
    }

}
