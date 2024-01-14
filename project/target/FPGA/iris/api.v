`include "controller.v"

module api(
	clock,
	start,
	reset,
	done,
	data_read_done,
	data_write_done,
	available_write,
	data_read_valid,
	data_read_request,
	data_read,
	data_write
);
	input wire clock;
	input wire start;
	input wire reset;
	output reg done;

	input wire [3:0] data_read_done;
	input wire [3:0] data_write_done;
	input wire [3:0] available_write;
	input wire [3:0] data_read_valid;
	output reg [3:0] data_read_request;
	output reg [3:0] data_write_request;

	input wire [127:0] data_read;
	output reg [127:0] data_write;

	wire [1:0] voted;
	reg [1:0] read_request;
	reg [1:0] write_request;
	reg [127:0] features;

	controller controller(
		.clock(clock),
		.voted(voted),
		.ft3(features[31:0]),
		.ft3(features[63:32]),
		.ft2(features[95:64]),
		.ft2(features[127:96]), 
	);

	always @(posedge clock) begin 

		if (reset) begin
			data_read_request  <= 4'b0000;
			data_write_request <= 4'b0000;
			done               <= 1'b0;
			data_write         <= 128'b0; 
 		end
		if (start) begin
			data_read_request  <= 4'b0000;
			data_write_request <= 4'b0000;

			if (data_read_valid == 4'b1111 && available_write == 4'b1111) begin
				data_read_request <= 2'b11; 
 			end
			if (data_read_request == 4'b1111) begin
				features <= data_read;
				data_write <= voted;
				write_request[0] <= 1'b1; 
 			end
			if (data_write_done[0] == 1'b1) begin
				done <= 1'b1; 
 			end 
 		end 
	end
endmodule