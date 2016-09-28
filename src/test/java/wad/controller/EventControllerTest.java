package wad.controller;

import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.repository.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @LocalServerPort
    private Integer port;
    
    @Autowired
    EventRepository eventRepo;

    
    @Test
    public void noTestYet() {
        
    }
    
//    @Test
//    public void oneEventTest() {
//        goTo("http://localhost:" + port + "/events");
//
//        String kuri = "Kurinpalautus";
//        
//        assertFalse(pageSource().contains(kuri));
//
//        fill(find("#name")).with(kuri);
//        fill(find("#date")).with("10/13/2016");
//        submit(find("#addEvent").first());
//
//        assertTrue(pageSource().contains(kuri));
//                
//        click(find("#remove-" + eventRepo.findByName(kuri).getId().toString()));
//        assertFalse(pageSource().contains("Van Damme"));
//    }
//    
}
