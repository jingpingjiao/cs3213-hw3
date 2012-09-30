package controller;


import io.InputHandler;
import pipe.MyPipe;
import pipe.Pipe;

import filter.Filter;
import filter.InputFilter;
import filter.OutputFilter;

public class Controller {
	public static void main(String args[]) {
		Pipe pipe1 = new MyPipe();
		Pipe pipe2 = new MyPipe();
		Pipe pipe3 = new MyPipe();

		
		InputHandler inputHandler = new InputHandler(pipe1);

		Filter inputFilter = new InputFilter(pipe1, pipe2);
		Filter outputFilter = new OutputFilter(pipe2, pipe3);
		inputFilter.run();
		outputFilter.run();
	}
}
