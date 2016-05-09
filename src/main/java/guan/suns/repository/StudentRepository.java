package guan.suns.repository;

import guan.suns.model.StudentPDM;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2016/5/5.
 */
public interface StudentRepository extends JpaRepository<StudentPDM, String> {
}
