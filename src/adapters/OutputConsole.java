package adapters;

import interfaces.OutputInterface;

public class OutputConsole implements OutputInterface{
	@Override
	public void printLine(String message) {
		System.out.println(message);
	}
	
	@Override
	public void print(String message) {
		System.out.print(message);
	}
	
	@Override
	public void clear() {
		for (int i = 0; i < 50; ++i)
			System.out.println(" ");
	}
}
