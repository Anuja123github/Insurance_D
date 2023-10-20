package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.ApiResponseDto;
import com.insurance.model.PolicySchedule;
import com.insurance.service.PolicyScheduleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PolicyScheduleController {

	@Autowired
	private PolicyScheduleService policyScheduleService;

	
	@PostMapping("/policySchedule")
	@ApiOperation("Request to add the policy schedule details to database")
	@ApiResponses(value = {@ApiResponse (code = 201 , message = "added policy schedule  details "),@ApiResponse(code =  404 ,message = "Not_FOund"),@ApiResponse(code = 500, message ="Internal server error")})
	public ResponseEntity<PolicySchedule> addPolicySchedule(@RequestBody PolicySchedule policySchedule) {
		PolicySchedule policySchedules = policyScheduleService.addPolicySchedule(policySchedule);
		return ResponseEntity.status(HttpStatus.CREATED).body(policySchedules);
	}

	@PutMapping("/policySchedule")
	@ApiOperation("Request to add the policy schedule details to database")
	@ApiResponses(value = {@ApiResponse (code = 200 , message = "updated policy schedule  details "),@ApiResponse(code =  404 ,message = "Not_FOund"),@ApiResponse(code = 500, message ="Internal server error")})
	public ResponseEntity<PolicySchedule> updatePolicy(@RequestBody PolicySchedule policySchedule) {
		PolicySchedule policySchedules = policyScheduleService.updatePolicySchedule(policySchedule);
		return ResponseEntity.status(HttpStatus.OK).body(policySchedules);
	}

	@GetMapping("policySchedule/{id}")
	@ApiOperation("Request to add the policy schedule details to database")
	@ApiResponses(value = {@ApiResponse (code = 200 , message = "fetch  policy schedule  details by using id "),@ApiResponse(code =  404 ,message = "Not_FOund"),@ApiResponse(code = 500, message ="Internal server error")})
	public ResponseEntity<PolicySchedule> getPolicyById(@PathVariable("id") Integer id) {
		PolicySchedule policySchedules = policyScheduleService.getPolicyScheduleById(id);
		return ResponseEntity.status(HttpStatus.OK).body(policySchedules);
	}

	@DeleteMapping("policySchedule/{id}")
	@ApiOperation("Request to add the policy schedule details to database")
	@ApiResponses(value = {@ApiResponse (code = 200 , message = "deleted policy schedule  details by using id "),@ApiResponse(code =  404 ,message = "Not_FOund"),@ApiResponse(code = 500, message ="Internal server error")})
	public ResponseEntity<ApiResponseDto> deletePolicyById(@PathVariable("id") Integer id) {
		policyScheduleService.deletePolicyScheduleById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record Deleted Successfully"));
	}
}
