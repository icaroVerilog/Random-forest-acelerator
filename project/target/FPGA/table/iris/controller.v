module controller (
	clock,
	reset,
	features,
	voted,
	read_new_sample,
	compute_vote_flag
);

	input wire clock;
	input wire reset;

	input wire [95:0] features;

	output wire [12:0] voted;
	output wire compute_vote_flag;
	output wire read_new_sample;

	validation_table validation_table (
		.clock(clock),
		.reset(reset),
		.forest_vote(voted),
		.compute_vote_flag(compute_vote_flag),
		.feature_integer(features[95:48]),
		.feature_decimal(features[47:0])
	);
endmodule