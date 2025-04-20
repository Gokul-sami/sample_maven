package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.Login;
import com.example.demo.services.LoginService;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
public class LogTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginService ls;

    @Test
	void testLogin() throws Exception {
		Login newUser = new Login();
		newUser.setUsername("testUser");
		when(ls.log(anyString(), anyString())).thenReturn(true);
		mockMvc.perform(post("/login")
		.param("username", "testUser")
		.param("password", "testPassword"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/home"));
	}

	@Test
   public void testFailedLogin() throws Exception {
       when(ls.log(anyString(), anyString())).thenReturn(false);
       mockMvc.perform(post("/login")
               .param("username", "invalidUser")
               .param("password", "wrongPass"))
               .andExpect(status().isOk());
            //    .andExpect(view().name("login"))
            //    .andExpect(model().attributeExists("error"));
   }
}
