package com.test.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.crud.bean.Department;
import com.test.crud.bean.Employee;
import com.test.crud.dao.DepartmentMapper;
import com.test.crud.dao.EmployeeMapper;
/*
 * ʹ��ע��ָ�������ļ�λ��
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testCRUD() {
//		ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
//		departmentMapper.insertSelective(new Department(null,"������"));
//		departmentMapper.insertSelective(new Department(null,"���Բ�"));
//		departmentMapper.deleteByPrimaryKey(1);
	employeeMapper.insertSelective(new Employee(null, "Tom", "M", "Tom@qq.com", 8));
		//����������Ա�� ������sqlsession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for(int i=0;i<=1000;i++) {
//			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//			mapper.insertSelective(new Employee(null, uid, "M", uid+"@qq.com", 7));
//		}
//		System.out.println("�������");

		
	}
}
