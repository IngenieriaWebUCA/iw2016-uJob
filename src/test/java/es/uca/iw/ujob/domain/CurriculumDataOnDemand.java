package es.uca.iw.ujob.domain;
import org.junit.runner.RunWith;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RooDataOnDemand(entity = Curriculum.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config.xml")
@ActiveProfiles("test")
public class CurriculumDataOnDemand {
}
