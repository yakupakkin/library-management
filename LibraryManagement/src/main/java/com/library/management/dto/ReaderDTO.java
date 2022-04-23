package com.library.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDTO {
	private Integer id;
	private String firstName;
	private String lastName;
	private int reservedCount;
}
