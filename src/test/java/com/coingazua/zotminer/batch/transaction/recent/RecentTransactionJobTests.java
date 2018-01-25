package com.coingazua.zotminer.batch.transaction.recent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecentTransactionJobTests {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job recentTransactionJob;

    @Test
    public void testRecentTransactionJob() throws Exception {
        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("parameter", "test")
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(recentTransactionJob, jobParameter);
        assertTrue(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED));
    }
}
