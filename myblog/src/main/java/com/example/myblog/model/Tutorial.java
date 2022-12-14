package com.example.myblog.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Tutorial {

	private Long id;

	private String title;

	private String description;

	private String published;

	private String deleteYn;

	private String insertTime;

	private String updateTime;

	private String deleteTime;

}
