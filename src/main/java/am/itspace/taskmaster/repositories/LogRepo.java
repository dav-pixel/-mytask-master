package am.itspace.taskmaster.repositories;

import am.itspace.taskmaster.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogRepo extends JpaRepository<Log, Integer> {

    @Override
    Optional<Log> findById(Integer integer);
}
