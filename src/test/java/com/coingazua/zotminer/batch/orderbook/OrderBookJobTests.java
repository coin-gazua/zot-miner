package com.coingazua.zotminer.batch.orderbook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderBookJobTests {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value = "orderBookJob")
	private Job orderBookJob;

	@Test
	public void testPersonJob() throws Exception {
		JobParameters jobParameter = new JobParametersBuilder()
				.addLong("time",System.currentTimeMillis())
				.addString("parameter", "test")
				.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(orderBookJob, jobParameter);
		assertTrue(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED));
	}
}
