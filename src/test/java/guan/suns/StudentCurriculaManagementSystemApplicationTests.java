package guan.suns;

import guan.suns.basicClass.Department;
import guan.suns.basicClass.Gender;
import guan.suns.model.StudentPDM;
import guan.suns.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StudentCurriculaManagementSystemApplication.class)
@WebAppConfiguration
public class StudentCurriculaManagementSystemApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void contextLoads() {

		StudentPDM student = new StudentPDM("1436540041","guanlin","GL", Gender.male,"Innovation", Department.School_of_Computer_Science_and_Engineering,20,null);
		studentRepository.save(student);
	}

}
