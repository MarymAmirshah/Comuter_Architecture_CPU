`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   17:32:53 06/09/2023
// Design Name:   CPU
// Module Name:   C:/Users/puyapardaz/Desktop/Phase 1/ISE/Project_Phase2/testbench.v
// Project Name:  Project_Phase2
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: CPU
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module testbench;
	reg clk;

	initial begin
		clk = 0;
		repeat (200) 
			#20 clk = ~clk;
		$finish;
	end

	CPU cpu(.clk(clk));

	initial begin

		$dumpfile("wave.vcd");
		$dumpvars(0, cpu);
	end

endmodule

