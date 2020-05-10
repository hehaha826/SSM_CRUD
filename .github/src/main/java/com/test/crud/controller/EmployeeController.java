package com.test.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.crud.bean.Employee;
import com.test.crud.bean.EmployeeExample;
import com.test.crud.bean.Msg;
import com.test.crud.service.EmployeeService;

/**
 * 
 * ����CRUD����
 * 
 * @author HJX
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	/**
	 * ��ҳ��ѯ
	 * 
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ��ѯ֮ǰֻ��Ҫ���ô���ҳ���Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// ���������ѯ����һ����ҳ

		List<Employee> emps = employeeService.getAll();
		PageInfo page = new PageInfo(emps, 5);// ������ʾ5����Ϣ
		return Msg.success().add("pageInfo", page);

	}

	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ��ѯ֮ǰֻ��Ҫ���ô���ҳ���Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// ���������ѯ����һ����ҳ
		List<Employee> emps = employeeService.getAll();
		PageInfo page = new PageInfo(emps, 5);// ������ʾ5����Ϣ
		model.addAttribute("pageInfo", page);

		return "list";
	}

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError error: errors) {
				System.out.println("������ֶ�����"+error.getField());
				System.out.println("�������Ϣ��"+error.getDefaultMessage());
			}
			
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
		    return Msg.success();
		}
	}

	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName") String empName) {
		boolean b = employeeService.checkuser(empName);
		if (b) {
			return Msg.success();
		} else {
			return Msg.fail();
		}
	}
	@RequestMapping(value="/emp/{id}",method =RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		System.out.println("��Ҫ���µ�Ա������"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	@RequestMapping(value="/demp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("id")Integer id) {
		employeeService.deleteEmp(id);
		return Msg.success();
	}
}
