package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.TodoList;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private TodoDao dao;
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public TodoList findById(Integer id){
		return dao.selectById(id);
	}
	
	public Collection<TodoList> findAll(){
		return dao.selectAll();
	}
	public Collection<TodoList> findActive(){
		return dao.selectActive();
	}
	public Collection<TodoList> findCompleted(){
		return dao.selectCompleted();
	}
	
	public TodoList create(TodoList todolist){
		Integer id = dao.insert(todolist);
		todolist.setId(id);
		return todolist;
	}
	
	public boolean update(TodoList todolist){
		int affected = dao.update(todolist);
		return affected == 1;
	}
	
	public boolean delete(Integer id){
		int affected = dao.deleteById(id);
		return affected == 1;
	}
	public boolean clear(){
		int affected = dao.deleteByCompleted();
		return affected == 1;
	}
	public Integer countTodo() {
		return dao.countTodos();
	}
	public Integer countActiveTodo() {
		return dao.countActiveTodos();
	}
	public Integer countCompletedTodo() {
		return dao.countCompletedTodos();
	}

}
