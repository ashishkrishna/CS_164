	.data
	.align	2
	.globl	class_nameTab
	.globl	Main_protObj
	.globl	Int_protObj
	.globl	String_protObj
	.globl	bool_const0
	.globl	bool_const1
	.globl	_int_tag
	.globl	_bool_tag
	.globl	_string_tag
_int_tag:
	.word	3
_bool_tag:
	.word	4
_string_tag:
	.word	5
	.globl	_MemMgr_INITIALIZER
_MemMgr_INITIALIZER:
	.word	_NoGC_Init
	.globl	_MemMgr_COLLECTOR
_MemMgr_COLLECTOR:
	.word	_NoGC_Collect
	.globl	_MemMgr_TEST
_MemMgr_TEST:
	.word	0
	.word	-1
str_const17:
	.word	5
	.word	5
	.word	String_dispTab
	.word	int_const5
	.byte	0	
	.align	2
	.word	-1
str_const16:
	.word	5
	.word	6
	.word	String_dispTab
	.word	int_const1
	.ascii	"Main"
	.byte	0	
	.align	2
	.word	-1
str_const15:
	.word	5
	.word	5
	.word	String_dispTab
	.word	int_const2
	.ascii	"Baz"
	.byte	0	
	.align	2
	.word	-1
str_const14:
	.word	5
	.word	5
	.word	String_dispTab
	.word	int_const2
	.ascii	"Foo"
	.byte	0	
	.align	2
	.word	-1
str_const13:
	.word	5
	.word	6
	.word	String_dispTab
	.word	int_const3
	.ascii	"String"
	.byte	0	
	.align	2
	.word	-1
str_const12:
	.word	5
	.word	6
	.word	String_dispTab
	.word	int_const1
	.ascii	"Bool"
	.byte	0	
	.align	2
	.word	-1
str_const11:
	.word	5
	.word	5
	.word	String_dispTab
	.word	int_const2
	.ascii	"Int"
	.byte	0	
	.align	2
	.word	-1
str_const10:
	.word	5
	.word	5
	.word	String_dispTab
	.word	int_const4
	.ascii	"IO"
	.byte	0	
	.align	2
	.word	-1
str_const9:
	.word	5
	.word	6
	.word	String_dispTab
	.word	int_const3
	.ascii	"Object"
	.byte	0	
	.align	2
	.word	-1
str_const8:
	.word	5
	.word	7
	.word	String_dispTab
	.word	int_const8
	.ascii	"_prim_slot"
	.byte	0	
	.align	2
	.word	-1
str_const7:
	.word	5
	.word	7
	.word	String_dispTab
	.word	int_const9
	.ascii	"SELF_TYPE"
	.byte	0	
	.align	2
	.word	-1
str_const6:
	.word	5
	.word	7
	.word	String_dispTab
	.word	int_const9
	.ascii	"_no_class"
	.byte	0	
	.align	2
	.word	-1
str_const5:
	.word	5
	.word	8
	.word	String_dispTab
	.word	int_const10
	.ascii	"<basic class>"
	.byte	0	
	.align	2
	.word	-1
str_const4:
	.word	5
	.word	7
	.word	String_dispTab
	.word	int_const11
	.ascii	"continue"
	.byte	0	
	.align	2
	.word	-1
str_const3:
	.word	5
	.word	6
	.word	String_dispTab
	.word	int_const1
	.ascii	"halt"
	.byte	0	
	.align	2
	.word	-1
str_const2:
	.word	5
	.word	7
	.word	String_dispTab
	.word	int_const12
	.ascii	" is prime.\n"
	.byte	0	
	.align	2
	.word	-1
str_const1:
	.word	5
	.word	10
	.word	String_dispTab
	.word	int_const13
	.ascii	"2 is trivially prime.\n"
	.byte	0	
	.align	2
	.word	-1
str_const0:
	.word	5
	.word	7
	.word	String_dispTab
	.word	int_const8
	.ascii	"tests/6.cl"
	.byte	0	
	.align	2
	.word	-1
int_const13:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	22
	.word	-1
int_const12:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	11
	.word	-1
int_const11:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	8
	.word	-1
int_const10:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	13
	.word	-1
int_const9:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	9
	.word	-1
int_const8:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	10
	.word	-1
int_const7:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	1
	.word	-1
int_const6:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	500
	.word	-1
int_const5:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	0
	.word	-1
int_const4:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	2
	.word	-1
int_const3:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	6
	.word	-1
int_const2:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	3
	.word	-1
int_const1:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	4
	.word	-1
int_const0:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	5
	.word	-1
bool_const0:
	.word	4
	.word	4
	.word	Bool_dispTab
	.word	0
	.word	-1
bool_const1:
	.word	4
	.word	4
	.word	Bool_dispTab
	.word	1
class_nameTab:
	.word	str_const9
	.word	str_const10
	.word	str_const16
	.word	str_const11
	.word	str_const12
	.word	str_const13
	.word	str_const14
	.word	str_const15
class_objTab:
	.word	Object_protObj
	.word	Object_init
	.word	IO_protObj
	.word	IO_init
	.word	Main_protObj
	.word	Main_init
	.word	Int_protObj
	.word	Int_init
	.word	Bool_protObj
	.word	Bool_init
	.word	String_protObj
	.word	String_init
	.word	Foo_protObj
	.word	Foo_init
	.word	Baz_protObj
	.word	Baz_init
Object_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
IO_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
	.word	IO.in_int
	.word	IO.in_string
	.word	IO.out_int
	.word	IO.out_string
Main_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
	.word	IO.in_int
	.word	IO.in_string
	.word	IO.out_int
	.word	IO.out_string
	.word	Main.main
Int_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
Bool_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
String_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
	.word	String.substr
	.word	String.concat
	.word	String.length
Foo_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
	.word	Foo.bars
	.word	Foo.badz
	.word	Foo.bar
Baz_dispTab:
	.word	Object.copy
	.word	Object.type_name
	.word	Object.abort
	.word	Baz.bars
	.word	Foo.badz
	.word	Baz.bar
	.word	-1
Object_protObj:
	.word	0
	.word	3
	.word	Object_dispTab
	.word	-1
IO_protObj:
	.word	1
	.word	3
	.word	IO_dispTab
	.word	-1
Main_protObj:
	.word	2
	.word	9
	.word	Main_dispTab
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	-1
Int_protObj:
	.word	3
	.word	4
	.word	Int_dispTab
	.word	0
	.word	-1
Bool_protObj:
	.word	4
	.word	4
	.word	Bool_dispTab
	.word	0
	.word	-1
String_protObj:
	.word	5
	.word	5
	.word	String_dispTab
	.word	0
	.word	0
	.word	-1
Foo_protObj:
	.word	6
	.word	4
	.word	Foo_dispTab
	.word	0
	.word	-1
Baz_protObj:
	.word	7
	.word	5
	.word	Baz_dispTab
	.word	0
	.word	0
	.globl	heap_start
heap_start:
	.word	0
	.text
	.globl	Main_init
	.globl	Int_init
	.globl	String_init
	.globl	Bool_init
	.globl	Main.main
Object_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
IO_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Main_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	IO_init
	la	$a0 str_const1
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label0
	la	$a0 str_const0
	li	$t1 4
	jal	_dispatch_abort
label0:
	lw	$t1 8($a0)
	lw	$t1 24($t1)
	jalr	$t1
	la	$a0 int_const4
	sw	$a0 32($s0)
	la	$a0 int_const0
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 int_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 -16($fp)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 -20($fp)
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	add	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label1
	la	$a0 str_const0
	li	$t1 4
	jal	_dispatch_abort
label1:
	lw	$t1 8($a0)
	lw	$t1 20($t1)
	jalr	$t1
	la	$a0 int_const4
	addiu	$sp $sp 4
	addiu	$sp $sp 4
	sw	$a0 28($s0)
	la	$a0 int_const5
	jal	Object.copy
	li	$t1 0
	sw	$t1 12($a0)
	sw	$a0 24($s0)
	la	$a0 int_const5
	jal	Object.copy
	li	$t1 0
	sw	$t1 12($a0)
	sw	$a0 20($s0)
	la	$a0 int_const6
	sw	$a0 16($s0)
label2:
	la	$a0 bool_const1
	lw	$a0 12($a0)
	beqz	$a0 label3
	lw	$a0 28($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 int_const7
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	add	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	sw	$a0 28($s0)
	la	$a0 int_const4
	sw	$a0 24($s0)
label4:
	lw	$a0 28($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 24($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 24($s0)
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	mul	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	move	$t2 $a0
	lw	$t2 12($t2)
	lw	$t1 4($sp)
	lw	$t1 12($t1)
	addiu	$sp $sp 4
	la	$a0 bool_const1
	blt	$t1 $t2 label5
	la	$a0 bool_const0
label5:
	lw	$a0 12($a0)
	beqz	$a0 label6
	la	$a0 bool_const0
	b	label7
label6:
	lw	$a0 28($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 24($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 28($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 24($s0)
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	div	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	mul	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	sub	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 int_const5
	move	$t2 $a0
	lw	$t1 4($sp)
	addiu	$sp $sp 4
	la	$a0 bool_const1
	beq	$t1 $t2 label8
	la	$a1 bool_const0
	jal	equality_test
label8:
	lw	$a0 12($a0)
	beqz	$a0 label9
	la	$a0 bool_const0
	b	label10
label9:
	la	$a0 bool_const1
label10:
label7:
	lw	$a0 12($a0)
	beqz	$a0 label11
	lw	$a0 24($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 int_const7
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	add	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	sw	$a0 24($s0)
	b	label4
label11:
	lw	$a0 28($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 24($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 24($s0)
	jal	Object.copy
	lw	$t3 4($sp)
	lw	$t2 12($a0)
	lw	$t1 12($t3)
	mul	$t1 $t1 $t2
	sw	$t1 12($a0)
	addiu	$sp $sp 4
	move	$t2 $a0
	lw	$t2 12($t2)
	lw	$t1 4($sp)
	lw	$t1 12($t1)
	addiu	$sp $sp 4
	la	$a0 bool_const1
	blt	$t1 $t2 label13
	la	$a0 bool_const0
label13:
	lw	$a0 12($a0)
	beqz	$a0 label14
	lw	$a0 28($s0)
	sw	$a0 32($s0)
	lw	$a0 32($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label16
	la	$a0 str_const0
	li	$t1 4
	jal	_dispatch_abort
label16:
	lw	$t1 8($a0)
	lw	$t1 20($t1)
	jalr	$t1
	la	$a0 str_const2
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label17
	la	$a0 str_const0
	li	$t1 4
	jal	_dispatch_abort
label17:
	lw	$t1 8($a0)
	lw	$t1 24($t1)
	jalr	$t1
	b	label15
label14:
	la	$a0 int_const5
label15:
	lw	$a0 16($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 28($s0)
	move	$t2 $a0
	lw	$t2 12($t2)
	lw	$t1 4($sp)
	lw	$t1 12($t1)
	addiu	$sp $sp 4
	la	$a0 bool_const1
	ble	$t1 $t2 label18
	la	$a0 bool_const0
label18:
	lw	$a0 12($a0)
	beqz	$a0 label19
	la	$a0 str_const3
	bne	$a0 $zero label21
	la	$a0 str_const0
	li	$t1 4
	jal	_dispatch_abort
label21:
	lw	$t1 8($a0)
	lw	$t1 8($t1)
	jalr	$t1
	b	label20
label19:
	la	$a0 str_const4
label20:
	b	label2
label3:
	sw	$a0 12($s0)
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Main.main:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	la	$a0 int_const5
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Int_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Bool_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
String_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Foo_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	Object_init
	la	$a0 int_const0
	sw	$a0 12($s0)
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Foo.bar:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	lw	$a0 12($s0)
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Foo.badz:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	la	$a0 int_const1
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Foo.bars:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	la	$a0 int_const2
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Baz_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	jal	Foo_init
	la	$a0 int_const3
	sw	$a0 12($s0)
	la	$a0 int_const0
	sw	$a0 8($s0)
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Baz.bars:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	lw	$a0 8($s0)
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Baz.bar:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 16
	move	$s0 $a0
	la	$a0 int_const4
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
