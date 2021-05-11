package weatherApp;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import weather.app.Application;
import weather.app.entity.WeatherCalender;
import weather.app.entity.WeatherType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RunWeatherAppTests  {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
	}
	
	@Test
	public void testSaveWeatherCalenderCorrect() throws Exception {
		//Ok Correct save object
		WeatherType weatherType = new WeatherType("SUNNY", 0, 0, "/sunny.png");
			 
		WeatherCalender weatherCalender = new WeatherCalender(LocalDate.of(2020, 2, 2), weatherType);
		
		this.mockMvc.perform(	MockMvcRequestBuilders.post("/Administration/save").flashAttr("weatherCalender", weatherCalender)).
				andExpect(MockMvcResultMatchers.redirectedUrl("/Administration/"));
	
	}
	
	@Test
	public void testGetAllWeatherPaginCorrect() throws Exception {
		//Ok Correct get all weather with pagination
		this.mockMvc.perform(	MockMvcRequestBuilders.get("/Administration/autoCheckPagin")
								.param("pagin_no","1")
								.param("page_size", "1"))
							.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetAllWeatherPaginIncorrectValue() throws Exception {
		//pagin_no <1 or page_size < 1 the throw error
		this.mockMvc.perform(	MockMvcRequestBuilders.get("/Administration/autoCheckPagin")
								.param("pagin_no","0")
								.param("page_size", "0"))
							.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void testGetWeatherPaginBtDatesIncorrectPageNum() throws Exception {
		//pagin_no <1 or page_size < 1 the throw error
		this.mockMvc.perform(	MockMvcRequestBuilders.get("/Administration/checkPage")
								.param("pagin_no","0")
								.param("start_date", "2020-10-10")
								.param("end_date", "2020-10-11")
								.param("page_size", "0"))
							.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
	@Test
	public void testGetWeatherPaginBtDatesCorrect() throws Exception {
		//Ok get all weather bewteen start and end dates with pagination
		this.mockMvc.perform(	MockMvcRequestBuilders.get("/Administration/checkPage")
								.param("pagin_no","1")
								.param("start_date", "2020-10-10")
								.param("end_date", "2020-12-11")
								.param("page_size", "5"))
							.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetWeatherPaginBtDatesStartDtGtEndDt() throws Exception {
		//If start_date > end_date then error
		this.mockMvc.perform(	MockMvcRequestBuilders.get("/Administration/checkPage")
								.param("pagin_no","1")
								.param("start_date", "2020-10-10")
								.param("end_date", "2019-10-11")
								.param("page_size", "5"))
							.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
