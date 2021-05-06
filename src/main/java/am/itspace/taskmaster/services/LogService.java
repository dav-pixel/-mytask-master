package am.itspace.taskmaster.services;

import am.itspace.taskmaster.entities.Log;
import am.itspace.taskmaster.repositories.LogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepo logRepo;

    public void addLog(Log log){
        if (log != null){
            logRepo.save(log);
        }
    }

    public void updateLog(Integer id, Log log){
        if (id != null && logRepo.findById(id).isPresent()){
            Log existLog = logRepo.getOne(id);
            if (existLog.getId() == id){
                log.setId(id);
                logRepo.save(log);
            }
        }
    }




}
