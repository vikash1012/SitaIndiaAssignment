package com.sitaindia.backend.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class GetAllEmployeeResponse {
    List<CreateEmployeeResponse> data;
}
