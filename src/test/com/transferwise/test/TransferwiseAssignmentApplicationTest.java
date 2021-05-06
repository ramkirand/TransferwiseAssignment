package com.transferwise.test;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.base.Verify;
import com.transferwise.service.DataLoadService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest
public class TransferwiseAssignmentApplicationTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DataLoadService dataLoadService;
	

	@Test
	public void contextLoads() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/load/transactions")).andReturn();
		log.info("Pass " + mvcResult.getResponse().getContentType());
		when(dataLoadService.loadData()).thenReturn(BatchStatus.STARTED);
		Verify.verifyNotNull(dataLoadService.loadData());
	}
	
	@Test
	public void testPostService() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/load/payment")).andReturn();
		log.info("Pass " + mvcResult.getResponse().getContentType());
		//when(postMessageService.postScreeningResponse("msg")).thenReturn("Hello");
		//Verify.verifyNotNull(dataLoadService.loadData());
	}

}
