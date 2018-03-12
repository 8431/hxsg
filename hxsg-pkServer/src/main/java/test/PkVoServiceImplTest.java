package test;

import com.hxsg.dao.PkVoService;
import com.hxsg.vo.PkRoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by dlf on 2017/11/15 0015.
 * Email 1429264916@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-*.xml"})
public class PkVoServiceImplTest {
    @Autowired
    PkVoService pkvoservice;
    @Test
    public void getPkRoleVo() throws Exception {
        List<PkRoleVo> pvLi=pkvoservice.getPkRoleVo("1000","休息");


    }

}