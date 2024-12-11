package br.com.gabrielsilva.todolist.task;

import br.com.gabrielsilva.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks/")
public class TaskController {
    @Autowired
    private ITaskRepository iTaskRepository;
    @PostMapping
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As datas estão erradas");
        }
        var task = this.iTaskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        System.out.println(request.toString());
        var idUser = request.getAttribute("idUser");
        System.out.println("/tasks/"+idUser);
        var tasks = this.iTaskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        var idUser = request.getAttribute("idUser");
        var task  = this.iTaskRepository.findById(id).orElse(null);
        if(task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não existe");
        }
        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não tem permissão para alterar essa tarefa");
        }
        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.iTaskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }
}
