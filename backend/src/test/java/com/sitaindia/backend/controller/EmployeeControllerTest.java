package com.sitaindia.backend.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitaindia.backend.controller.dto.CreateEmployeeRequest;
import com.sitaindia.backend.controller.dto.CreateEmployeeResponse;
import com.sitaindia.backend.controller.dto.EmployeeRequest;
import com.sitaindia.backend.controller.dto.EmployeeResponse;
import com.sitaindia.backend.controller.dto.GetAllEmployeeResponse;
import com.sitaindia.backend.service.EmployeeService;

@MockBean({EmployeeService.class})
@WebMvcTest(controllers=EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    EmployeeService employeeService;

    @BeforeEach
     void init(){
        EmployeeController employeeController=new EmployeeController(employeeService);
        this.mockMvc= MockMvcBuilders.standaloneSetup(employeeController)
        .setControllerAdvice().build();
     }

     ObjectMapper objectMapper=new ObjectMapper();
    @Test
    void shouldCreateEmployee() throws Exception{
       
        CreateEmployeeRequest createEmployeeRequest = createEmployeeRequest();
        MockHttpServletRequestBuilder builder = createBuilderForPostMethod(createEmployeeRequest);
        doNothing().when(employeeService).create(createEmployeeRequest);

        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(employeeService,times(1)).create(createEmployeeRequest);
    }

    @Test
    void shouldReturnAllEmployee() throws Exception{

        List<EmployeeResponse> employeeResponses= List.of(new EmployeeResponse("vikash", 10000));
         List<CreateEmployeeResponse> createEmployeeResponses=List.of(new CreateEmployeeResponse("INR", employeeResponses));
         GetAllEmployeeResponse getAllEmployeeResponse=new GetAllEmployeeResponse(createEmployeeResponses);
        when(employeeService.getEmployee("jun-23-2000")).thenReturn(getAllEmployeeResponse);
        MockHttpServletRequestBuilder builder = createBuilderforGetMethod();
    
         this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json( objectMapper.writeValueAsString(getAllEmployeeResponse)));

        verify(employeeService,times(1)).getEmployee("jun-23-2000");
    }

    private MockHttpServletRequestBuilder createBuilderforGetMethod() {
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders
        .get("http://localhost:8080/tci/employee-bonus")
        .param("date","jun-23-2000")
        .contentType(MediaType.APPLICATION_JSON);
        return builder;
    }

    private MockHttpServletRequestBuilder createBuilderForPostMethod(
            CreateEmployeeRequest createEmployeeRequest) {
        MockHttpServletRequestBuilder builder=MockMvcRequestBuilders
        .post("http://localhost:8080/tci/employee-bonus")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.valueToTree(createEmployeeRequest).toString());
        return builder;
    }

    private CreateEmployeeRequest createEmployeeRequest() {
        CreateEmployeeRequest createEmployeeRequest=new CreateEmployeeRequest(List.of(new EmployeeRequest("Vikash",5000,"IR","June-23-2000","may-12-2090","it")));
        return createEmployeeRequest;
    }
    
}
