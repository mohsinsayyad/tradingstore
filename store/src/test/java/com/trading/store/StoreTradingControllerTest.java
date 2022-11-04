package com.trading.store;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.store.controller.TradingStoreController;
import com.trading.store.model.TradingStore;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreTradingControllerTest.class)
public class StoreTradingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private TradingStoreController tradingStoreController;

	@Test
	public void addTradingStore() throws Exception {
		TradingStore store = new TradingStore("T6", 1, "CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N");

		given(tradingStoreController.addTradingStore(any(TradingStore.class)))
		.willReturn(new ResponseEntity<String>("Trade added successfully!.", HttpStatus.OK));
        

		ResultActions response = mockMvc
				.perform(post("/addtrade").contentType(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(store)));
	            

		response.andExpect(status().isOk()).andDo(print());
	}
}
