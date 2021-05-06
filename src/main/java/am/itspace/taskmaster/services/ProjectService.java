package am.itspace.taskmaster.services;

import am.itspace.taskmaster.entities.Project;
import am.itspace.taskmaster.repositories.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;

    public void addProject(Project project){
        if (project != null){
            projectRepo.save(project);
        }
    }

    public void deleteProject(Integer id){
        if (id != null){
            projectRepo.delete(projectRepo.getOne(id));
        }
    }

    public void updateProject(Integer id, Project project){
        if (id != null && projectRepo.findById(id).isPresent()){
            if (projectRepo.getOne(id).getId() == id){
                project.setId(id);
                projectRepo.save(project);
            }
        }
    }




}
