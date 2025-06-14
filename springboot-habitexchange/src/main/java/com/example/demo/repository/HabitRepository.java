package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Habit;
import java.util.List;


//Spring JPA
@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> { // Habit: entity, Integer: @Id 的型別
	
	// 查詢未完成習慣(自行撰寫 T-SQL, 注意:欄位名要符合資料表中的設定)
	@Query(value = "select id, name from habit where is_archived = 0", nativeQuery = true)
	List<Habit> findAllUnarchivedHabits();
}
