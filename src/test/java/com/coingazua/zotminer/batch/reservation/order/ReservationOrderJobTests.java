package com.coingazua.zotminer.batch.reservation.order;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationOrderJobTests {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job reservationOrderJob;

    @Test
    public void testReservationOrderJob() throws Exception {
        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("parameter", "test")
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(reservationOrderJob, jobParameter);
        assertTrue(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED));
    }
}
