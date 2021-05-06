package am.itspace.taskmaster.endpoints;

import am.itspace.taskmaster.entities.Log;
import am.itspace.taskmaster.services.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    @PostMapping()
    public String addLog(@RequestBody Log log){
        logService.addLog(log);
        return "log was added!";
    }

    @PutMapping("/{id}")
    public String updateLog(@PathVariable("id") int id, @RequestBody Log log){
        logService.updateLog(id, log);
        return "log was updated successfully!";
    }
}
