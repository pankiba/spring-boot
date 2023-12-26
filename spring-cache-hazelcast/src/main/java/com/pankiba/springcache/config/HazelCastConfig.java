package com.pankiba.springcache.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelCastConfig {

	public static final String EMPLOYYES = "employees";
	private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());


	public Config createConfig() {
		Config config = new Config();
		config.addMapConfig(mapConfig());
		config.getSerializationConfig().addSerializerConfig(serializerConfig());
		return config;
	}

	private SerializerConfig serializerConfig() {
		return null;
	}

	private MapConfig mapConfig() {
		MapConfig mapConfig = new MapConfig(EMPLOYYES);
		mapConfig.setTimeToLiveSeconds(360);
		mapConfig.setMaxIdleSeconds(400);
		return mapConfig;
	}
}
