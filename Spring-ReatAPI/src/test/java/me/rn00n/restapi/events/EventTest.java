package me.rn00n.restapi.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventTest {

    @DisplayName("Builder 설정")
    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertNotNull(event);
    }

    @DisplayName("Bean 생성")
    @Test
    public void javaBean() {
        Event event = new Event();
        event.setName("Event");
        event.setDescription("Spring");

        assertEquals(event.getName(), "Event");
        assertEquals(event.getDescription(), "Spring");
    }

}