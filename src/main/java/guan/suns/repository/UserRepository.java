package guan.suns.repository;

import guan.suns.model.StudentPDM;
import guan.suns.model.UserPDM;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2016/5/5.
 */
public interface UserRepository extends JpaRepository<UserPDM, String> {
}
