package com.coingazua.zotminer.api.bithumb.client;

import com.coingazua.zotminer.domain.common.model.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BithumbApiTests {

	@Autowired
	private BithumbApi bithumbApi;

	@Test
	public void testRecentTransactionJob() throws Exception {
		bithumbApi.recentTransaction(Currency.BTC);
		//assertTrue(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED));
	}
}
