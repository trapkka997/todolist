package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.domain.TodoList;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<TodoList> rowMapper = BeanPropertyRowMapper.newInstance(TodoList.class);
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
	}
	
	public TodoList selectById(Integer id){
		RowMapper<TodoList> rowMapper = BeanPropertyRowMapper.newInstance(TodoList.class);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(TodoSqls.SELECT_BY_ID, params, rowMapper);
	}
	
	
	
	public Integer insert(TodoList todolist){
		SqlParameterSource params = new BeanPropertySqlParameterSource(todolist);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	
	
	public Integer deleteById(Integer id){
		Map<String, ?>params = Collections.singletonMap("id", id);
		return jdbc.update(TodoSqls.DELETE_BY_ID, params);
	}
	public Integer deleteByCompleted(){
		Map<String, ?>params = Collections.emptyMap();
		return jdbc.update(TodoSqls.DELETE_BY_COMPLETED, params);
	}
	
	
	
	public Integer update(TodoList todolist){
		SqlParameterSource params = new BeanPropertySqlParameterSource(todolist);
		return jdbc.update(TodoSqls.UPDATE, params);
	}
	
	
	
	public List<TodoList> selectAll(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_ALL, params, rowMapper);
	}
	public List<TodoList> selectActive(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_ACTIVE, params, rowMapper);
	}
	public List<TodoList> selectCompleted(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_COMPLETED, params, rowMapper);
	}
	
	
	
	public int countTodos(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.COUNT_TODO, params, Integer.class);
	}
	public int countActiveTodos(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.ACTIVE_COUNT_TODO, params, Integer.class);
	}
	public int countCompletedTodos(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.COMPLETED_COUNT_TODO, params, Integer.class);
	}

	
}
