package com.codespark.springbootbasics.hibernatemapping.bootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_bi.Asset;
import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_bi.Manager;
import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_uni.Broker;
import com.codespark.springbootbasics.hibernatemapping.entity.one_to_many_uni.Fund;
import com.codespark.springbootbasics.hibernatemapping.repository.AssetRepository;
import com.codespark.springbootbasics.hibernatemapping.repository.BrokerRepository;
import com.codespark.springbootbasics.hibernatemapping.repository.ManagerRepository;

@Component
public class OneToManyBootstrap {

	private static Logger logger = LoggerFactory.getLogger(OneToManyBootstrap.class);

	@Autowired
	BrokerRepository brokerRepository;

	@Autowired
	ManagerRepository managerRepository;

	@Autowired
	AssetRepository assetRepository;

	public void run() {
		saveOneToManyUnidirectional();
		getOneToManyUnidirectional();

		saveOneToManyBidirectional();
		getOneToManyBidirectional();
	}

	public void saveOneToManyUnidirectional() {
		List<Fund> funds = new ArrayList<>();
		funds.add(new Fund(0, "FND123", 10000));
		funds.add(new Fund(0, "FND456", 34000));
		funds.add(new Fund(0, "FND789", 7000));

		Broker broker = new Broker(0, "Sayan Das", "sayan@test.com", funds);

		brokerRepository.save(broker);
		logger.info("BROKER: " + broker);
	}

	public void getOneToManyUnidirectional() {
		Optional<Broker> data = brokerRepository.findById(1);

		// BROKER --> FUNDS
		if (data.isPresent()) {
			Broker broker = data.get();
			logger.info("BROKER: " + broker);
		}
	}

	public void saveOneToManyBidirectional() {
		List<Asset> assets = new ArrayList<>();
		Manager manager = new Manager(0, "Deepayan Nayak", (short) 3);

		Asset asset1 = new Asset(0, "AST123", 12000, manager);
		Asset asset2 = new Asset(0, "AST456", 7800, manager);

		assets.add(asset1);
		assets.add(asset2);

		manager.setAssets(assets);

		managerRepository.save(manager);
		logger.info("MANAGER: " + manager);
		logger.info("ASSETS: " + manager.getAssets());
	}

	public void getOneToManyBidirectional() {
		Optional<Manager> data1 = managerRepository.findById(1);

		// MANAGER <--> ASSETS
		if (data1.isPresent()) {
			Manager manager = data1.get();

			logger.info("MANAGER: " + manager);
			logger.info("ASSETS: " + manager.getAssets());
		}

		Optional<Asset> data2 = assetRepository.findById(2);

		// ASSETS <--> MANAGER
		if (data2.isPresent()) {
			Asset asset = data2.get();

			logger.info("ASSETS2: " + asset);
			logger.info("MANAGER: " + asset.getManager());
		}
	}
}
