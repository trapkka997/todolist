package kr.or.connect.todo.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.domain.TodoList;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoDaoTest {
	@Autowired
	private TodoDao dao;
	

	@Test
	public void shouldInsertSelect(){
		Date date = new Date();
		TodoList todolist = new TodoList("자바 공부하기",0,new Timestamp(date.getTime()));
		Integer id = dao.insert(todolist);
		
		TodoList selected = dao.selectById(id);
		assertThat(selected.getTodo(),is("자바 공부하기"));
	}
	
	@Test
	public void shouldSelectAll() {
		List<TodoList> allTodo = dao.selectAll();
		assertThat(allTodo, is(notNullValue()));
	}
	
	@Test
	public void shouldDelete(){
		Date date = new Date();
		TodoList todo = new TodoList("자바 공부하기",0,new Timestamp(date.getTime()));
		Integer id = dao.insert(todo);
		
		int affected = dao.deleteById(id);
		assertThat(affected, is(1));
	}
}