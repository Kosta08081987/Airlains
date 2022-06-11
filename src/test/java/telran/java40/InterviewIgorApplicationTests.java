package telran.java40;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import telran.java40.controller.AirlainsController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InterviewIgorApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AirlainsController controller;

	@Test
	public void allDestinationsLoads() throws Exception{
		this.mockMvc.perform(get("/airline/allDestinations"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("[\"Adis Abeba\",\"Berlin\",\"Madrid\",\"Paris\",\"Tel Aviv\"]"));
	}

	@Test
	public void addDestinationLoads() throws Exception{
		this.mockMvc.perform(put("/airline/addDestination").content("Rome"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("true")));
		this.mockMvc.perform(get("/airline/allDestinations"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("[\"Rome\",\"Adis Abeba\",\"Berlin\",\"Madrid\",\"Paris\",\"Tel Aviv\"]"));
		this.mockMvc.perform(delete("/airline/deleteDestination").content("Rome"))
				.andDo(print())
				.andExpect(status().isOk());

	}

//	@Test
//	public void deletDestinationLoads() throws Exception {
//		this.mockMvc.perform(delete("/airline/deleteDestination").content("Rome"))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//		this.mockMvc.perform(get("/airline/allDestinations"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().json("[\"Adis Abeba\",\"Berlin\",\"Madrid\",\"Paris\",\"Tel Aviv\"]"));
//	}
//	string(containsString("Hello, World"))
// json("[\"Adis Abeba\",\"Berlin\",\"Madrid\",\"Paris\",\"Tel Aviv\"]"))
}