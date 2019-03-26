package com.test.pingpong;

public class TestPingPong {
	public static void main(String[] args) throws Exception {
		Monitor oneTwo = new Monitor();
		Monitor twoOne = new Monitor();

		Thread playerOne = new Thread(new Player("Player1", true, oneTwo, twoOne));
		Thread playerTwo = new Thread(new Player("Player2", false, twoOne, oneTwo));
		playerOne.start();
		playerTwo.start();

		playerOne.join();
		System.exit(0);
	}
}
