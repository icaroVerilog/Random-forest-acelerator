module tree50(
	ft0, ft1, ft2, ft3,
	clock, voted_class
);
	input wire clock;

	input wire [31:0] ft0;
	input wire [31:0] ft1;
	input wire [31:0] ft2;
	input wire [31:0] ft3;

	output reg [2:0] voted_class;

	parameter class0 = 3'b100;
	parameter class1 = 3'b010;
	parameter class2 = 3'b001;

	always @(posedge clock) begin
		if (ft3 <= 32'b00000000000000000000000000000000) begin
			voted_class <= class0;
		end 
		else begin
			if (ft3 <= 32'b00000000000000000000000000000001) begin
				if (ft1 <= 32'b00000000000000000000000000000010) begin
					if (ft0 <= 32'b00000000000000000000000000000110) begin
						if (ft3 <= 32'b00000000000000000000000000000001) begin
							voted_class <= class1;
						end 
						else begin
							voted_class <= class2;
						end
					end 
					else begin
						voted_class <= class1;
					end
				end 
				else begin
					voted_class <= class1;
				end
			end 
			else begin
				if (ft3 <= 32'b00000000000000000000000000000001) begin
					if (ft2 <= 32'b00000000000000000000000000000100) begin
						voted_class <= class2;
					end 
					else begin
						voted_class <= class1;
					end
				end 
				else begin
					voted_class <= class2;
				end
			end
		end
	end
endmodule