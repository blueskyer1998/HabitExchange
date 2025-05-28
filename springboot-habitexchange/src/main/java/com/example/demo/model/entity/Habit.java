package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "habit") // 若資料表名稱與實體類一致可以不用設定此行
public class Habit {
	@Id
	@Column(name = "habit_id")
	private Integer habitId;
	
	private String habitName;
	
}
