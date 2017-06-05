package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String DELETE_BY_COMPLETED=
			"DELETE FROM todo WHERE completed= 1";
	
	static final String UPDATE =
			"UPDATE todo SET\n"
			+ "completed = :completed\n"
			+ "WHERE id = :id";
	
	static final String SELECT_BY_ID =
			"SELECT id, todo, completed, date FROM todo where id = :id";
	
	static final String SELECT_ALL =
			"SELECT id, todo, completed, date FROM todo";
	
	static final String SELECT_ACTIVE =
			"SELECT id, todo, completed, date FROM todo where completed = 0";
	
	static final String SELECT_COMPLETED =
			"SELECT id, todo, completed, date FROM todo where completed = 1";
	
	static final String COUNT_TODO =
			"SELECT COUNT(*) FROM todo";
	static final String ACTIVE_COUNT_TODO =
			"SELECT COUNT(*) FROM todo WHERE completed = 0";
	static final String COMPLETED_COUNT_TODO =
			"SELECT COUNT(*) FROM todo WHERE completed = 1";
	
}

