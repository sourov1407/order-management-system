package mlab.order.management;

import mlab.order.management.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class IntegrationTest {

	private MockMvc mockMvc;
	private MockHttpServletRequestBuilder requestBuilder;
	@Autowired
	private UserController userController;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	@WithMockUser
	void createReviewTest() throws Exception {
		String request = "{\n" +
				"    \"user_id\": 1,\n" +
				"    \"product_id\": 1,\n" +
				"    \"rating\": 7.9,\n" +
				"    \"review\": \"Good :)\"\n" +
				"}";

		String response =
				"{\"response_code\":\"OK\",\"response_body\":{\"product_id\":1,\"rating\":7.9,\"review\":\"Good :)\"}}";



		requestBuilder = MockMvcRequestBuilders.post("/users/reviews")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request);

		MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(requestBuilder)
				.andReturn().getResponse();

		assertEquals(mockHttpServletResponse.getStatus(), 200);
		assertEquals(mockHttpServletResponse.getContentAsString(), response);

	}


}
