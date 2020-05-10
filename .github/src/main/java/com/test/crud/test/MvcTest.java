package com.test.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.test.crud.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
		"file:WebContent/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	// ����springMvc��ioc container
	@Autowired
	WebApplicationContext context;
	// ����mvc���󣬻�ȡ������
	MockMvc mocMvc;

	@Before
	public void initMoKcMvc() {
		mocMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testPage() throws Exception {
		// ģ�������õ�����ֵ
		MvcResult result = mocMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNumber", "1")).andReturn();
		// ����ɹ��Ժ��������л���pageInfo
		MockHttpServletRequest request = result.getRequest();
		PageInfo<Employee> page = (PageInfo<Employee>) request.getAttribute("pageInfo");
		System.out.println("��ǰҳ��" + page.getPageNum());
		System.out.println("��ҳ��" + page.getPages());
		System.out.println("�ܼ�¼��" + page.getTotal());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		for (int i : page.getNavigatepageNums()) {
			System.out.println(" " + i);
		}
		
// 		get employee info
		List<Employee> emps = page.getList();
		for(Employee emp : emps) {
			System.out.println("id:"+emp.getEmpId()+"==>name:" +emp.getEmpName());
		}
	}
}