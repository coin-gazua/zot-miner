package com.coingazua.zotminer.home.repository;

import com.coingazua.zotminer.home.entity.MinerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinerTestRepositoryTests {

	@Autowired
	private MinerTestRepository minerTestRepository;

	@Test
	public void testFindOne() {
		MinerTest minerTest = minerTestRepository.findOne(1L);
		System.out.printf(minerTest.toString());
		assertNotNull(minerTest);
	}
}
