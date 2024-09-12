package com.vigacat.catalogue.web;

import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.web.controller.GameController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class VigacatApplicationTest {

    @Autowired
    private GameController gameController;

    @Test
    @WithMockUser(username = "testuser", authorities = {"permission::CAT_QUERY_GAMES"})
    public void shouldGetGame() {
        GameDto gameDto = gameController.getGame(1L);
        System.out.println();
    }

}