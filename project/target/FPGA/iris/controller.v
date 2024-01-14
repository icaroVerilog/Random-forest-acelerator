`include "tree0.v"
`include "tree1.v"
`include "tree2.v"
`include "tree3.v"
`include "tree4.v"
`include "tree5.v"
`include "tree6.v"
`include "tree7.v"
`include "tree8.v"
`include "tree9.v"
`include "tree10.v"
`include "tree11.v"
`include "tree12.v"
`include "tree13.v"
`include "tree14.v"
`include "tree15.v"
`include "tree16.v"
`include "tree17.v"
`include "tree18.v"
`include "tree19.v"
`include "tree20.v"
`include "tree21.v"
`include "tree22.v"
`include "tree23.v"
`include "tree24.v"
`include "tree25.v"
`include "tree26.v"
`include "tree27.v"
`include "tree28.v"
`include "tree29.v"
`include "tree30.v"
`include "tree31.v"
`include "tree32.v"
`include "tree33.v"
`include "tree34.v"
`include "tree35.v"
`include "tree36.v"
`include "tree37.v"
`include "tree38.v"
`include "tree39.v"
`include "tree40.v"
`include "tree41.v"
`include "tree42.v"
`include "tree43.v"
`include "tree44.v"
`include "tree45.v"
`include "tree46.v"
`include "tree47.v"
`include "tree48.v"
`include "tree49.v"
`include "tree50.v"
`include "tree51.v"
`include "tree52.v"
`include "tree53.v"
`include "tree54.v"
`include "tree55.v"
`include "tree56.v"
`include "tree57.v"
`include "tree58.v"
`include "tree59.v"
`include "tree60.v"
`include "tree61.v"
`include "tree62.v"
`include "tree63.v"
`include "tree64.v"
`include "tree65.v"
`include "tree66.v"
`include "tree67.v"
`include "tree68.v"
`include "tree69.v"
`include "tree70.v"
`include "tree71.v"
`include "tree72.v"
`include "tree73.v"
`include "tree74.v"
`include "tree75.v"
`include "tree76.v"
`include "tree77.v"
`include "tree78.v"
`include "tree79.v"
`include "tree80.v"
`include "tree81.v"
`include "tree82.v"
`include "tree83.v"
`include "tree84.v"
`include "tree85.v"
`include "tree86.v"
`include "tree87.v"
`include "tree88.v"
`include "tree89.v"
`include "tree90.v"
`include "tree91.v"
`include "tree92.v"
`include "tree93.v"
`include "tree94.v"
`include "tree95.v"
`include "tree96.v"
`include "tree97.v"
`include "tree98.v"
`include "tree99.v"

module controller(
	clock,
	voted,
	ft0,
	ft1,
	ft2,
	ft3
);

	integer class00;
	integer class01;
	integer class10;
	input wire clock;
	output reg [1:0] voted;

	input wire [31:0] ft0;
	input wire [31:0] ft1;
	input wire [31:0] ft2;
	input wire [31:0] ft3;

	wire [2:0] voted_class0;
	wire [2:0] voted_class1;
	wire [2:0] voted_class2;
	wire [2:0] voted_class3;
	wire [2:0] voted_class4;
	wire [2:0] voted_class5;
	wire [2:0] voted_class6;
	wire [2:0] voted_class7;
	wire [2:0] voted_class8;
	wire [2:0] voted_class9;
	wire [2:0] voted_class10;
	wire [2:0] voted_class11;
	wire [2:0] voted_class12;
	wire [2:0] voted_class13;
	wire [2:0] voted_class14;
	wire [2:0] voted_class15;
	wire [2:0] voted_class16;
	wire [2:0] voted_class17;
	wire [2:0] voted_class18;
	wire [2:0] voted_class19;
	wire [2:0] voted_class20;
	wire [2:0] voted_class21;
	wire [2:0] voted_class22;
	wire [2:0] voted_class23;
	wire [2:0] voted_class24;
	wire [2:0] voted_class25;
	wire [2:0] voted_class26;
	wire [2:0] voted_class27;
	wire [2:0] voted_class28;
	wire [2:0] voted_class29;
	wire [2:0] voted_class30;
	wire [2:0] voted_class31;
	wire [2:0] voted_class32;
	wire [2:0] voted_class33;
	wire [2:0] voted_class34;
	wire [2:0] voted_class35;
	wire [2:0] voted_class36;
	wire [2:0] voted_class37;
	wire [2:0] voted_class38;
	wire [2:0] voted_class39;
	wire [2:0] voted_class40;
	wire [2:0] voted_class41;
	wire [2:0] voted_class42;
	wire [2:0] voted_class43;
	wire [2:0] voted_class44;
	wire [2:0] voted_class45;
	wire [2:0] voted_class46;
	wire [2:0] voted_class47;
	wire [2:0] voted_class48;
	wire [2:0] voted_class49;
	wire [2:0] voted_class50;
	wire [2:0] voted_class51;
	wire [2:0] voted_class52;
	wire [2:0] voted_class53;
	wire [2:0] voted_class54;
	wire [2:0] voted_class55;
	wire [2:0] voted_class56;
	wire [2:0] voted_class57;
	wire [2:0] voted_class58;
	wire [2:0] voted_class59;
	wire [2:0] voted_class60;
	wire [2:0] voted_class61;
	wire [2:0] voted_class62;
	wire [2:0] voted_class63;
	wire [2:0] voted_class64;
	wire [2:0] voted_class65;
	wire [2:0] voted_class66;
	wire [2:0] voted_class67;
	wire [2:0] voted_class68;
	wire [2:0] voted_class69;
	wire [2:0] voted_class70;
	wire [2:0] voted_class71;
	wire [2:0] voted_class72;
	wire [2:0] voted_class73;
	wire [2:0] voted_class74;
	wire [2:0] voted_class75;
	wire [2:0] voted_class76;
	wire [2:0] voted_class77;
	wire [2:0] voted_class78;
	wire [2:0] voted_class79;
	wire [2:0] voted_class80;
	wire [2:0] voted_class81;
	wire [2:0] voted_class82;
	wire [2:0] voted_class83;
	wire [2:0] voted_class84;
	wire [2:0] voted_class85;
	wire [2:0] voted_class86;
	wire [2:0] voted_class87;
	wire [2:0] voted_class88;
	wire [2:0] voted_class89;
	wire [2:0] voted_class90;
	wire [2:0] voted_class91;
	wire [2:0] voted_class92;
	wire [2:0] voted_class93;
	wire [2:0] voted_class94;
	wire [2:0] voted_class95;
	wire [2:0] voted_class96;
	wire [2:0] voted_class97;
	wire [2:0] voted_class98;
	wire [2:0] voted_class99;


	tree0 tree0(
		.clock(clock),
		.voted_class(voted_class0),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree1 tree1(
		.clock(clock),
		.voted_class(voted_class1),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree2 tree2(
		.clock(clock),
		.voted_class(voted_class2),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree3 tree3(
		.clock(clock),
		.voted_class(voted_class3),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree4 tree4(
		.clock(clock),
		.voted_class(voted_class4),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree5 tree5(
		.clock(clock),
		.voted_class(voted_class5),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree6 tree6(
		.clock(clock),
		.voted_class(voted_class6),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree7 tree7(
		.clock(clock),
		.voted_class(voted_class7),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree8 tree8(
		.clock(clock),
		.voted_class(voted_class8),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree9 tree9(
		.clock(clock),
		.voted_class(voted_class9),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree10 tree10(
		.clock(clock),
		.voted_class(voted_class10),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree11 tree11(
		.clock(clock),
		.voted_class(voted_class11),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree12 tree12(
		.clock(clock),
		.voted_class(voted_class12),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree13 tree13(
		.clock(clock),
		.voted_class(voted_class13),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree14 tree14(
		.clock(clock),
		.voted_class(voted_class14),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree15 tree15(
		.clock(clock),
		.voted_class(voted_class15),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree16 tree16(
		.clock(clock),
		.voted_class(voted_class16),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree17 tree17(
		.clock(clock),
		.voted_class(voted_class17),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree18 tree18(
		.clock(clock),
		.voted_class(voted_class18),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree19 tree19(
		.clock(clock),
		.voted_class(voted_class19),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree20 tree20(
		.clock(clock),
		.voted_class(voted_class20),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree21 tree21(
		.clock(clock),
		.voted_class(voted_class21),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree22 tree22(
		.clock(clock),
		.voted_class(voted_class22),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree23 tree23(
		.clock(clock),
		.voted_class(voted_class23),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree24 tree24(
		.clock(clock),
		.voted_class(voted_class24),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree25 tree25(
		.clock(clock),
		.voted_class(voted_class25),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree26 tree26(
		.clock(clock),
		.voted_class(voted_class26),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree27 tree27(
		.clock(clock),
		.voted_class(voted_class27),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree28 tree28(
		.clock(clock),
		.voted_class(voted_class28),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree29 tree29(
		.clock(clock),
		.voted_class(voted_class29),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree30 tree30(
		.clock(clock),
		.voted_class(voted_class30),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree31 tree31(
		.clock(clock),
		.voted_class(voted_class31),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree32 tree32(
		.clock(clock),
		.voted_class(voted_class32),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree33 tree33(
		.clock(clock),
		.voted_class(voted_class33),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree34 tree34(
		.clock(clock),
		.voted_class(voted_class34),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree35 tree35(
		.clock(clock),
		.voted_class(voted_class35),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree36 tree36(
		.clock(clock),
		.voted_class(voted_class36),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree37 tree37(
		.clock(clock),
		.voted_class(voted_class37),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree38 tree38(
		.clock(clock),
		.voted_class(voted_class38),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree39 tree39(
		.clock(clock),
		.voted_class(voted_class39),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree40 tree40(
		.clock(clock),
		.voted_class(voted_class40),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree41 tree41(
		.clock(clock),
		.voted_class(voted_class41),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree42 tree42(
		.clock(clock),
		.voted_class(voted_class42),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree43 tree43(
		.clock(clock),
		.voted_class(voted_class43),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree44 tree44(
		.clock(clock),
		.voted_class(voted_class44),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree45 tree45(
		.clock(clock),
		.voted_class(voted_class45),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree46 tree46(
		.clock(clock),
		.voted_class(voted_class46),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree47 tree47(
		.clock(clock),
		.voted_class(voted_class47),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree48 tree48(
		.clock(clock),
		.voted_class(voted_class48),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree49 tree49(
		.clock(clock),
		.voted_class(voted_class49),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree50 tree50(
		.clock(clock),
		.voted_class(voted_class50),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree51 tree51(
		.clock(clock),
		.voted_class(voted_class51),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree52 tree52(
		.clock(clock),
		.voted_class(voted_class52),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree53 tree53(
		.clock(clock),
		.voted_class(voted_class53),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree54 tree54(
		.clock(clock),
		.voted_class(voted_class54),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree55 tree55(
		.clock(clock),
		.voted_class(voted_class55),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree56 tree56(
		.clock(clock),
		.voted_class(voted_class56),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree57 tree57(
		.clock(clock),
		.voted_class(voted_class57),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree58 tree58(
		.clock(clock),
		.voted_class(voted_class58),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree59 tree59(
		.clock(clock),
		.voted_class(voted_class59),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree60 tree60(
		.clock(clock),
		.voted_class(voted_class60),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree61 tree61(
		.clock(clock),
		.voted_class(voted_class61),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree62 tree62(
		.clock(clock),
		.voted_class(voted_class62),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree63 tree63(
		.clock(clock),
		.voted_class(voted_class63),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree64 tree64(
		.clock(clock),
		.voted_class(voted_class64),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree65 tree65(
		.clock(clock),
		.voted_class(voted_class65),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree66 tree66(
		.clock(clock),
		.voted_class(voted_class66),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree67 tree67(
		.clock(clock),
		.voted_class(voted_class67),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree68 tree68(
		.clock(clock),
		.voted_class(voted_class68),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree69 tree69(
		.clock(clock),
		.voted_class(voted_class69),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree70 tree70(
		.clock(clock),
		.voted_class(voted_class70),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree71 tree71(
		.clock(clock),
		.voted_class(voted_class71),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree72 tree72(
		.clock(clock),
		.voted_class(voted_class72),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree73 tree73(
		.clock(clock),
		.voted_class(voted_class73),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree74 tree74(
		.clock(clock),
		.voted_class(voted_class74),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree75 tree75(
		.clock(clock),
		.voted_class(voted_class75),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree76 tree76(
		.clock(clock),
		.voted_class(voted_class76),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree77 tree77(
		.clock(clock),
		.voted_class(voted_class77),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree78 tree78(
		.clock(clock),
		.voted_class(voted_class78),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree79 tree79(
		.clock(clock),
		.voted_class(voted_class79),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree80 tree80(
		.clock(clock),
		.voted_class(voted_class80),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree81 tree81(
		.clock(clock),
		.voted_class(voted_class81),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree82 tree82(
		.clock(clock),
		.voted_class(voted_class82),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree83 tree83(
		.clock(clock),
		.voted_class(voted_class83),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree84 tree84(
		.clock(clock),
		.voted_class(voted_class84),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree85 tree85(
		.clock(clock),
		.voted_class(voted_class85),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree86 tree86(
		.clock(clock),
		.voted_class(voted_class86),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree87 tree87(
		.clock(clock),
		.voted_class(voted_class87),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree88 tree88(
		.clock(clock),
		.voted_class(voted_class88),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree89 tree89(
		.clock(clock),
		.voted_class(voted_class89),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree90 tree90(
		.clock(clock),
		.voted_class(voted_class90),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree91 tree91(
		.clock(clock),
		.voted_class(voted_class91),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree92 tree92(
		.clock(clock),
		.voted_class(voted_class92),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree93 tree93(
		.clock(clock),
		.voted_class(voted_class93),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree94 tree94(
		.clock(clock),
		.voted_class(voted_class94),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree95 tree95(
		.clock(clock),
		.voted_class(voted_class95),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree96 tree96(
		.clock(clock),
		.voted_class(voted_class96),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree97 tree97(
		.clock(clock),
		.voted_class(voted_class97),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree98 tree98(
		.clock(clock),
		.voted_class(voted_class98),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	tree99 tree99(
		.clock(clock),
		.voted_class(voted_class99),
		.ft0(ft0),
		.ft1(ft1),
		.ft2(ft2),
		.ft3(ft3), 
	);

	always @(posedge clock) begin 
		class00 <= voted_class0[0] + voted_class1[0] + voted_class2[0] + voted_class3[0] + voted_class4[0] + voted_class5[0] + voted_class6[0] + voted_class7[0] + voted_class8[0] + voted_class9[0] + voted_class10[0] + voted_class11[0] + voted_class12[0] + voted_class13[0] + voted_class14[0] + voted_class15[0] + voted_class16[0] + voted_class17[0] + voted_class18[0] + voted_class19[0] + voted_class20[0] + voted_class21[0] + voted_class22[0] + voted_class23[0] + voted_class24[0] + voted_class25[0] + voted_class26[0] + voted_class27[0] + voted_class28[0] + voted_class29[0] + voted_class30[0] + voted_class31[0] + voted_class32[0] + voted_class33[0] + voted_class34[0] + voted_class35[0] + voted_class36[0] + voted_class37[0] + voted_class38[0] + voted_class39[0] + voted_class40[0] + voted_class41[0] + voted_class42[0] + voted_class43[0] + voted_class44[0] + voted_class45[0] + voted_class46[0] + voted_class47[0] + voted_class48[0] + voted_class49[0] + voted_class50[0] + voted_class51[0] + voted_class52[0] + voted_class53[0] + voted_class54[0] + voted_class55[0] + voted_class56[0] + voted_class57[0] + voted_class58[0] + voted_class59[0] + voted_class60[0] + voted_class61[0] + voted_class62[0] + voted_class63[0] + voted_class64[0] + voted_class65[0] + voted_class66[0] + voted_class67[0] + voted_class68[0] + voted_class69[0] + voted_class70[0] + voted_class71[0] + voted_class72[0] + voted_class73[0] + voted_class74[0] + voted_class75[0] + voted_class76[0] + voted_class77[0] + voted_class78[0] + voted_class79[0] + voted_class80[0] + voted_class81[0] + voted_class82[0] + voted_class83[0] + voted_class84[0] + voted_class85[0] + voted_class86[0] + voted_class87[0] + voted_class88[0] + voted_class89[0] + voted_class90[0] + voted_class91[0] + voted_class92[0] + voted_class93[0] + voted_class94[0] + voted_class95[0] + voted_class96[0] + voted_class97[0] + voted_class98[0] + voted_class99[0];
		class01 <= voted_class0[1] + voted_class1[1] + voted_class2[1] + voted_class3[1] + voted_class4[1] + voted_class5[1] + voted_class6[1] + voted_class7[1] + voted_class8[1] + voted_class9[1] + voted_class10[1] + voted_class11[1] + voted_class12[1] + voted_class13[1] + voted_class14[1] + voted_class15[1] + voted_class16[1] + voted_class17[1] + voted_class18[1] + voted_class19[1] + voted_class20[1] + voted_class21[1] + voted_class22[1] + voted_class23[1] + voted_class24[1] + voted_class25[1] + voted_class26[1] + voted_class27[1] + voted_class28[1] + voted_class29[1] + voted_class30[1] + voted_class31[1] + voted_class32[1] + voted_class33[1] + voted_class34[1] + voted_class35[1] + voted_class36[1] + voted_class37[1] + voted_class38[1] + voted_class39[1] + voted_class40[1] + voted_class41[1] + voted_class42[1] + voted_class43[1] + voted_class44[1] + voted_class45[1] + voted_class46[1] + voted_class47[1] + voted_class48[1] + voted_class49[1] + voted_class50[1] + voted_class51[1] + voted_class52[1] + voted_class53[1] + voted_class54[1] + voted_class55[1] + voted_class56[1] + voted_class57[1] + voted_class58[1] + voted_class59[1] + voted_class60[1] + voted_class61[1] + voted_class62[1] + voted_class63[1] + voted_class64[1] + voted_class65[1] + voted_class66[1] + voted_class67[1] + voted_class68[1] + voted_class69[1] + voted_class70[1] + voted_class71[1] + voted_class72[1] + voted_class73[1] + voted_class74[1] + voted_class75[1] + voted_class76[1] + voted_class77[1] + voted_class78[1] + voted_class79[1] + voted_class80[1] + voted_class81[1] + voted_class82[1] + voted_class83[1] + voted_class84[1] + voted_class85[1] + voted_class86[1] + voted_class87[1] + voted_class88[1] + voted_class89[1] + voted_class90[1] + voted_class91[1] + voted_class92[1] + voted_class93[1] + voted_class94[1] + voted_class95[1] + voted_class96[1] + voted_class97[1] + voted_class98[1] + voted_class99[1];
		class10 <= voted_class0[2] + voted_class1[2] + voted_class2[2] + voted_class3[2] + voted_class4[2] + voted_class5[2] + voted_class6[2] + voted_class7[2] + voted_class8[2] + voted_class9[2] + voted_class10[2] + voted_class11[2] + voted_class12[2] + voted_class13[2] + voted_class14[2] + voted_class15[2] + voted_class16[2] + voted_class17[2] + voted_class18[2] + voted_class19[2] + voted_class20[2] + voted_class21[2] + voted_class22[2] + voted_class23[2] + voted_class24[2] + voted_class25[2] + voted_class26[2] + voted_class27[2] + voted_class28[2] + voted_class29[2] + voted_class30[2] + voted_class31[2] + voted_class32[2] + voted_class33[2] + voted_class34[2] + voted_class35[2] + voted_class36[2] + voted_class37[2] + voted_class38[2] + voted_class39[2] + voted_class40[2] + voted_class41[2] + voted_class42[2] + voted_class43[2] + voted_class44[2] + voted_class45[2] + voted_class46[2] + voted_class47[2] + voted_class48[2] + voted_class49[2] + voted_class50[2] + voted_class51[2] + voted_class52[2] + voted_class53[2] + voted_class54[2] + voted_class55[2] + voted_class56[2] + voted_class57[2] + voted_class58[2] + voted_class59[2] + voted_class60[2] + voted_class61[2] + voted_class62[2] + voted_class63[2] + voted_class64[2] + voted_class65[2] + voted_class66[2] + voted_class67[2] + voted_class68[2] + voted_class69[2] + voted_class70[2] + voted_class71[2] + voted_class72[2] + voted_class73[2] + voted_class74[2] + voted_class75[2] + voted_class76[2] + voted_class77[2] + voted_class78[2] + voted_class79[2] + voted_class80[2] + voted_class81[2] + voted_class82[2] + voted_class83[2] + voted_class84[2] + voted_class85[2] + voted_class86[2] + voted_class87[2] + voted_class88[2] + voted_class89[2] + voted_class90[2] + voted_class91[2] + voted_class92[2] + voted_class93[2] + voted_class94[2] + voted_class95[2] + voted_class96[2] + voted_class97[2] + voted_class98[2] + voted_class99[2];

		if ((class00 > class01) & (class00 > class10)) begin
			voted <= 2'b00; 
 		end
		if ((class01 > class00) & (class01 > class10)) begin
			voted <= 2'b01; 
 		end
		if ((class10 > class00) & (class10 > class01)) begin
			voted <= 2'b10; 
 		end 
	end
endmodule