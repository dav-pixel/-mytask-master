package am.itspace.taskmaster.repositories;

import am.itspace.taskmaster.entities.Status;
import am.itspace.taskmaster.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(Status status);
    @Override
    Optional<Task> findById(Integer integer);
}
