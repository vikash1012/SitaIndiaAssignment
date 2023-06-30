package com.sitaindia.backend.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class GetAllEmployeeResponse {
    List<CreateEmployeeResponse> data;
}
