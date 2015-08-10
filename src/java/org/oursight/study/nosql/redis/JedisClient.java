package org.oursight.study.nosql.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {

	public static void main(String[] args) {
		init();

	}

	public static void init() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
		Jedis jedis = pool.getResource();
		jedis.set("test", "123456");
		jedis.set("test", "000000");
		System.out.println(jedis.get("test"));

	}
}
