package test;

        import com.google.gson.Gson;
        import com.hxsg.Dao.FuJiangMapper;
        import com.hxsg.pk.websoket.WebSocketPkServer;
        import com.hxsg.po.FuJiang;
        import com.hxsg.po.RoleFujiang;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mybatis.xml"})
public class WebSocketPkServerTest {
    @Autowired
    FuJiangMapper  fujiangmapper;
    @Test
    public void testCreateRoleFujiang() throws Exception {
//        FuJiang fg=fujiangmapper.selectByPrimaryKey(13);
//        RoleFujiang rf= WebSocketPkServer.CreateRoleFujiang(fg, 100, 1000, true);
//        System.out.println("--------");
//        Gson gn=new Gson();
//        System.out.println(gn.toJson(rf));

    }
}