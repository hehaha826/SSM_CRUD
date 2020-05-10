package com.test.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.crud.bean.Department;
import com.test.crud.dao.DepartmentMapper;
@Service
public class DepartmentService {
	
	@Autowired
	DepartmentMapper departmentMapper;
	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		List<Department> list = departmentMapper.selectByExample(null);
		return list;//查所有直接null 返回部门信息
	}
	
}
