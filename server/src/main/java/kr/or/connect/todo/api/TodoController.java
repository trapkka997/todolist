package kr.or.connect.todo.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.TodoList;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todolists")
public class TodoController {
	private final TodoService service;
	private final Logger log = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	public TodoController(TodoService service){
		this.service = service;
	}
	
	@GetMapping
	Collection<TodoList> readList(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	TodoList read(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	TodoList create(@RequestBody TodoList todolist){
		TodoList newTodo = service.create(todolist);
		return newTodo;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody TodoList todolist) {
		todolist.setId(id);
		service.update(todolist);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void clear() {
		service.clear();
	}
	
	@GetMapping("/count")
	Integer countList() {
		return service.countTodo();
	}
	@GetMapping("/activecount")
	Integer countActiveList() {
		return service.countActiveTodo();
	}
	@GetMapping("/completedcount")
	Integer countCompletedList() {
		return service.countCompletedTodo();
	}
	
	@GetMapping("/active")
	Collection<TodoList> activeList(){
		return service.findActive();
	}
	@GetMapping("/completed")
	Collection<TodoList> completedList(){
		return service.findCompleted();
	}


}
