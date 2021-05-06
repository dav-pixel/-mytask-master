package am.itspace.taskmaster.repositories;

import am.itspace.taskmaster.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

    @Override
    Optional<Project> findById(Integer integer);
}
