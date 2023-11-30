 The Geometry Data Base  at level (1)
***************************************
midpoint[N,AD]
midpoint[M,CD]
midpoint[L,CB]
midpoint[K,AB]

The configuration contains the following lines
line [1]A C P 
line [0]B P 
line [1]B P D 
line [0]A D N 
line [0]C D M 
line [0]B C L 
line [0]A B K 
line [0]C D G 
line [1]C D M G 
line [0]P G 
line [0]B C F 
line [1]B C L F 
line [0]P F 
line [0]A B E 
line [1]A B K E 
line [0]P E 
line [0]A D H 
line [1]A D N H 
line [0]P H 
line [1]B O 
line [1]A O 
line [1]O K 
line [1]C O 
line [1]O D 
line [1]O N 
line [1]O L 
line [1]O M 
line [1]N M 
line [1]N K 
line [1]M L 
line [1]L K 
line [0]P N 
line [0]P M 
line [0]P L 
line [0]P K 
line [1]O P 
line [1]O E 
line [1]O H 
line [1]O F 
line [1]O G 
line [1]B M 
line [1]D L 
line [1]M K 
line [1]N L 
line [1]B N 
line [1]D K 
line [1]A L 
line [1]C K 
line [1]A M 
line [1]C N 
line [1]E H 
line [1]F E 
line [1]G H 
line [1]G F 
line [1]P K G        because para[$PK,PG$].
line [1]D F 
line [1]B G 
line [1]P M E        because para[$PM,PE$].
line [1]C E 
line [1]A F 
line [1]C H 
line [1]A G 
line [1]P L H        because para[$PH,PL$].
line [1]P N F        because para[$PN,PF$].
line [1]B H 
line [1]D E 
line [1]N G 
line [1]M F 
line [1]N E 
line [1]M H 
line [1]L G 
line [1]L E 
line [1]K F 
line [1]K H 
line [1]G E 
line [1]F H 

The configuration contains the following parallel lines
pline [0]: line [1]B P D ; 
pline [0]: line [1]A C P ; 
pline [0]: line [0]P G ; 
pline [1]: line [1]C D M G ; 
pline [0]: line [0]P F ; 
pline [1]: line [1]B C L F ; 
pline [0]: line [0]P E ; 
pline [1]: line [1]A B K E ; 
pline [0]: line [0]P H ; 
pline [1]: line [1]A D N H ; 
pline [0]: line [1]O K ; 
pline [0]: line [1]O K ; line [0]P E ; 
       because tline [0]line [1]A B K E  perp line [1]O K  and tline [0]line [0]P E  perp line [1]A B K E .
pline [0]: line [1]O N ; 
pline [0]: line [1]O N ; line [0]P H ; 
       because tline [0]line [1]A D N H  perp line [1]O N  and tline [0]line [0]P H  perp line [1]A D N H .
pline [0]: line [1]O L ; 
pline [0]: line [1]O L ; line [0]P F ; 
       because tline [0]line [1]B C L F  perp line [1]O L  and tline [0]line [0]P F  perp line [1]B C L F .
pline [0]: line [1]O M ; 
pline [0]: line [1]O M ; line [0]P G ; 
       because tline [0]line [1]C D M G  perp line [1]O M  and tline [0]line [0]P G  perp line [1]C D M G .
pline [0]: line [1]N M ; line [1]A C P ; 
       because midpoint[$M,CD$] and midpoint[$N,AD$].
pline [0]: line [1]N K ; line [1]B P D ; 
       because midpoint[$K,AB$] and midpoint[$N,AD$].
pline [0]: line [1]M L ; line [1]B P D ; 
       because midpoint[$L,CB$] and midpoint[$M,CD$].
pline [1]: line [1]M L ; line [1]B P D ; line [1]N K ; 
       because pline [0]: line [1]N K ; line [1]B P D ;  and pline [0]: line [1]M L ; line [1]B P D ; .
pline [0]: line [1]L K ; line [1]A C P ; 
       because midpoint[$K,AB$] and midpoint[$L,CB$].
pline [1]: line [1]L K ; line [1]A C P ; line [1]N M ; 
       because pline [0]: line [1]N M ; line [1]A C P ;  and pline [0]: line [1]L K ; line [1]A C P ; .
pline [1]: line [1]G H ; 
pline [1]: line [1]O D ; 
pline [0]: line [0]P K ; 
pline [1]: line [1]P K G ; line [1]O M ; line [1]P K G ; 
       because tline [0]line [1]C D M G  perp line [0]P K  and tline [0]line [1]C D M G  perp line [1]O M .
pline [1]: line [1]E H ; 
pline [1]: line [1]A O ; 
pline [0]: line [0]P M ; 
pline [1]: line [1]P M E ; line [1]O K ; line [1]P M E ; 
       because tline [0]line [1]A B K E  perp line [0]P M  and tline [0]line [1]A B K E  perp line [1]O K .
pline [1]: line [1]B O ; 
pline [1]: line [1]F E ; 
pline [1]: line [1]C O ; 
pline [1]: line [1]G F ; 
pline [0]: line [0]P H ; line [0]P L ; 
       because para[$BP,ML$] and $[PH,BP]=[PL,ML]$.
pline [1]: line [1]P L H ; line [1]P L H ; line [1]O N ; 
       because pline [0]: line [1]O N ; line [0]P H ;  and pline [0]: line [0]P H ; line [0]P L ; .
pline [0]: line [0]P N ; line [0]P F ; 
       because $[NM,PN]=[NM,PF]$ and $[PH,BP]=[PL,ML]$.
pline [1]: line [1]P N F ; line [1]P N F ; line [1]O L ; 
       because pline [0]: line [1]O L ; line [0]P F ;  and pline [0]: line [0]P N ; line [0]P F ; .
pline [1]: line [1]N G ; 
pline [1]: line [1]L G ; 
pline [1]: line [1]N E ; 
pline [1]: line [1]L E ; 
pline [1]: line [1]K F ; 
pline [1]: line [1]M F ; 
pline [1]: line [1]N L ; 
pline [1]: line [1]G E ; 
pline [1]: line [1]K H ; 
pline [1]: line [1]M H ; 
pline [1]: line [1]M K ; 
pline [1]: line [1]F H ; 

The configuration contains the following perp lines
tline [1]line [1]M L  perp line [1]L K 
tline [0]line [0]P G  perp line [1]C D M G 
tline [0]line [0]P F  perp line [1]B C L F 
tline [0]line [0]P E  perp line [1]A B K E 
tline [0]line [0]P H  perp line [1]A D N H 
tline [0]line [1]A B K E  perp line [1]O K 
       because midpoint[$K,AB$] and circle[$(O)AB$].
tline [0]line [1]A B K E  perp line [1]O K 
       because tline [0]line [0]P E  perp line [1]A B K E  and tline [0]line [1]A B K E  perp line [1]O K .
tline [0]line [1]A D N H  perp line [1]O N 
       because midpoint[$N,AD$] and circle[$(O)AD$].
tline [1]line [1]A D N H  perp line [1]P L H 
       because tline [0]line [0]P H  perp line [1]A D N H  and tline [0]line [1]A D N H  perp line [1]O N .
tline [0]line [1]B C L F  perp line [1]O L 
       because midpoint[$L,BC$] and circle[$(O)BC$].
tline [1]line [1]B C L F  perp line [1]P N F 
       because tline [0]line [0]P F  perp line [1]B C L F  and tline [0]line [1]B C L F  perp line [1]O L .
tline [0]line [1]C D M G  perp line [1]O M 
       because midpoint[$M,CD$] and circle[$(O)CD$].
tline [0]line [1]C D M G  perp line [1]O M 
       because tline [0]line [0]P G  perp line [1]C D M G  and tline [0]line [1]C D M G  perp line [1]O M .
tline [1]line [1]G H  perp line [1]O D 
       because perp[$PH,AD$] and $[GH,PH]=[OD,AD]$.
tline [0]line [1]C D M G  perp line [0]P K 
       because perp[$BP,AC$] and $[CD,BP]=[PK,AC]$.
tline [1]line [1]C D M G  perp line [1]P K G 
       because tline [0]line [1]C D M G  perp line [1]O M  and tline [0]line [1]C D M G  perp line [0]P K .
tline [1]line [1]E H  perp line [1]A O 
       because perp[$PH,AD$] and $[PH,EH]=[AD,AO]$.
tline [0]line [1]A B K E  perp line [0]P M 
       because perp[$AC,ML$] and $[AC,AB]=[ML,PM]$.
tline [1]line [1]A B K E  perp line [1]P M E 
       because tline [0]line [1]A B K E  perp line [1]O K  and tline [0]line [1]A B K E  perp line [0]P M .
tline [1]line [1]B O  perp line [1]F E 
       because perp[$OL,BC$] and $[OL,BO]=[BC,FE]$.
tline [1]line [1]C O  perp line [1]G F 
       because perp[$OL,BC$] and $[CO,OL]=[GF,BC]$.
tline [1]line [1]N G  perp line [1]L G 
       because $[NG,LG]=[NG,LG]$ and perp[$NM,ML$].
tline [1]line [1]N E  perp line [1]L E 
       because $[NG,LG]=[NE,LE]$ and perp[$NM,ML$].
tline [1]line [1]K F  perp line [1]M F 
       because perp[$NK,NM$] and $[NK,KF]=[NM,MF]$.
tline [1]line [1]N L  perp line [1]G E 
       because perp[$LE,NE$] and $[LE,NL]=[NE,GE]$.
tline [1]line [1]K H  perp line [1]M H 
       because perp[$NK,NM$] and $[NK,KH]=[NM,MH]$.
tline [1]line [1]M K  perp line [1]F H 
       because perp[$KH,MH$] and $[KH,MK]=[MH,FH]$.

The configuration contains the following circles
cir [0]circle[(O)ABC]
cir [0]circle[(O)BD]
cir [1]circle[(O)ABCD]
       because cir [0]circle[(O)ABC] and cir [0]circle[(O)BD].
cir [1]circle[(N)APD]
       because midpoint[$N,AD$] and perp[$PA,PD$].
cir [1]circle[(M)CPD]
       because midpoint[$M,CD$] and perp[$PC,PD$].
cir [1]circle[(L)BCP]
       because midpoint[$L,CB$] and perp[$PC,PB$].
cir [1]circle[(K)ABP]
       because midpoint[$K,AB$] and perp[$PA,PB$].
cir [1]circle[AONK]
       because perp[$NA,NO$] and perp[$KA,KO$].
cir [1]circle[BOLK]
       because perp[$LB,LO$] and perp[$KB,KO$].
cir [1]circle[APEH]
       because perp[$HA,HP$] and perp[$EA,EP$].
cir [1]circle[BPFE]
       because perp[$FB,FP$] and perp[$EB,EP$].
cir [1]circle[ODNM]
       because perp[$MD,MO$] and perp[$ND,NO$].
cir [1]circle[PDGH]
       because perp[$GD,GP$] and perp[$HD,HP$].
cir [1]circle[COML]
       because perp[$MC,MO$] and perp[$LC,LO$].
cir [1]circle[CPGF]
       because perp[$GC,GP$] and perp[$FC,FP$].
cir [1]circle[BDGF]
       because $[DG,DB]=[FG,FB]$.
cir [1]circle[ACFE]
       because $[AC,AE]=[FC,FE]$.
cir [0]circle[MLGF]
       because $[MG,ML]=[FG,FL]$.
cir [0]circle[LKFE]
       because $[KL,KE]=[FL,FE]$.
cir [1]circle[ACGH]
       because $[GH,GC]=[AH,AC]$.
cir [0]circle[NMGH]
       because $[GH,GM]=[NH,NM]$.
cir [0]circle[NKEH]
       because $[HE,HN]=[KE,KN]$.
cir [1]circle[BDEH]
       because $[HE,HD]=[BE,BD]$.
cir [0]circle[MLKG]
       because perp[$LM,LK$] and perp[$GM,GK$].
cir [0]circle[MLKGF]
       because cir [0]circle[MLGF] and cir [0]circle[MLKG].
cir [0]circle[MLKGFE]
       because cir [0]circle[LKFE] and cir [0]circle[MLKGF].
cir [0]circle[NMKG]
       because perp[$NM,NK$] and perp[$GM,GK$].
cir [0]circle[NMKGH]
       because cir [0]circle[NMGH] and cir [0]circle[NMKG].
cir [0]circle[NMKGEH]
       because cir [0]circle[NKEH] and cir [0]circle[NMKGH].
cir [1]circle[NMLKGFEH]
       because cir [0]circle[MLKGFE] and cir [0]circle[NMKGEH].
cir [1]circle[(N)GE]
       because $[NE,EG]=[EG,GN]$.
cir [1]circle[(M)FH]
       because $[MH,HF]=[HF,FM]$.
cir [1]circle[(L)GE]
       because $[LE,EG]=[EG,GL]$.
cir [1]circle[(K)FH]
       because $[KH,HF]=[HF,FK]$.


The configuration contains the following eqangles
eqang [0][CA,CD] = [PB,PG]
       because perp[$PG,CD$] and perp[$BP,AC$].
eqang [0][PA,PG] = [DB,DC]
       because perp[$PG,CD$] and perp[$BP,AC$].
eqang [0][CA,CB] = [PB,PF]
       because perp[$PF,BC$] and perp[$BP,AC$].
eqang [0][PA,PF] = [BP,BC]
       because perp[$PF,BC$] and perp[$BP,AC$].
eqang [0][AC,AB] = [PB,PE]
       because perp[$PE,AB$] and perp[$BP,AC$].
eqang [0][PA,PE] = [BP,BA]
       because perp[$PE,AB$] and perp[$BP,AC$].
eqang [0][AC,AD] = [PB,PH]
       because perp[$PH,AD$] and perp[$BP,AC$].
eqang [0][PA,PH] = [DB,DA]
       because perp[$PH,AD$] and perp[$BP,AC$].
eqang [0][BA,BO] = [AO,AB]
       because circle[$(O)AB$].
eqang [0][OK,OB] = [OA,OK]
       because midpoint[$K,AB$] and circle[$(O)AB$].
eqang [0][CA,CB] = [OA,OK]
       because circle[$(O)ABC$], midpoint[$K,AB$], circle[$(O)AB$].
eqang [0][PB,PF] = [OA,OK] = [CA,CB]
       because eqang [0][CA,CB] = [PB,PF] and eqang [0][CA,CB] = [OA,OK].
eqang [0][OK,OB] = [CA,CB] = [OA,OK] = [PB,PF]
       because eqang [0][OK,OB] = [OA,OK] and eqang [0][PB,PF] = [OA,OK] = [CA,CB].
eqang [0][DA,DB] = [CA,CB]
       because circle[$ABCD$].
eqang [0][PA,PH] = [CB,CA] = [DB,DA]
       because eqang [0][PA,PH] = [DB,DA] and eqang [0][DA,DB] = [CA,CB].
eqang [0][PB,PF] = [OA,OK] = [OK,OB] = [DA,DB] = [CA,CB] = [PH,PA]
       because eqang [0][OK,OB] = [CA,CB] = [OA,OK] = [PB,PF] and eqang [0][PA,PH] = [CB,CA] = [DB,DA].
eqang [0][CA,CO] = [AO,AC]
       because circle[$(O)AC$].
eqang [0][DA,DC] = [BA,BC]
       because circle[$ACBD$].
eqang [0][DA,DO] = [AO,AD]
       because circle[$(O)AD$].
eqang [0][ON,OD] = [OA,ON]
       because midpoint[$N,AD$] and circle[$(O)AD$].
eqang [0][BA,BP] = [OA,ON]
       because circle[$(O)ADB$], midpoint[$N,AD$], circle[$(O)AD$].
eqang [0][PA,PE] = [ON,OA] = [BP,BA]
       because eqang [0][PA,PE] = [BP,BA] and eqang [0][BA,BP] = [OA,ON].
eqang [0][ON,OD] = [BA,BP] = [OA,ON] = [PE,PA]
       because eqang [0][ON,OD] = [OA,ON] and eqang [0][PA,PE] = [ON,OA] = [BP,BA].
eqang [0][CA,CD] = [BA,BP]
       because circle[$ADBC$].
eqang [0][PB,PG] = [BA,BP] = [CA,CD]
       because eqang [0][CA,CD] = [PB,PG] and eqang [0][CA,CD] = [BA,BP].
eqang [0][PE,PA] = [OA,ON] = [ON,OD] = [CA,CD] = [BA,BP] = [PB,PG]
       because eqang [0][ON,OD] = [BA,BP] = [OA,ON] = [PE,PA] and eqang [0][PB,PG] = [BA,BP] = [CA,CD].
eqang [0][CB,CO] = [BO,BC]
       because circle[$(O)BC$].
eqang [0][OL,OC] = [OB,OL]
       because midpoint[$L,BC$] and circle[$(O)BC$].
eqang [0][AB,AC] = [OB,OL]
       because circle[$(O)BCA$], midpoint[$L,BC$], circle[$(O)BC$].
eqang [0][PB,PE] = [OL,OB] = [AC,AB]
       because eqang [0][AC,AB] = [PB,PE] and eqang [0][AB,AC] = [OB,OL].
eqang [0][OL,OC] = [AB,AC] = [OB,OL] = [PE,PB]
       because eqang [0][OL,OC] = [OB,OL] and eqang [0][PB,PE] = [OL,OB] = [AC,AB].
eqang [0][DB,DC] = [AB,AC]
       because circle[$BCAD$].
eqang [0][PA,PG] = [AB,AC] = [DB,DC]
       because eqang [0][PA,PG] = [DB,DC] and eqang [0][DB,DC] = [AB,AC].
eqang [0][PE,PB] = [OB,OL] = [OL,OC] = [DB,DC] = [AB,AC] = [PA,PG]
       because eqang [0][OL,OC] = [AB,AC] = [OB,OL] = [PE,PB] and eqang [0][PA,PG] = [AB,AC] = [DB,DC].
eqang [0][DB,DO] = [BO,BP]
       because circle[$(O)BD$].
eqang [0][CB,CD] = [AB,AD]
       because circle[$BDAC$].
eqang [0][DC,DO] = [CO,CD]
       because circle[$(O)CD$].
eqang [0][OM,OD] = [OC,OM]
       because midpoint[$M,CD$] and circle[$(O)CD$].
eqang [0][AC,AD] = [OC,OM]
       because circle[$(O)CDA$], midpoint[$M,CD$], circle[$(O)CD$].
eqang [0][PB,PH] = [OC,OM] = [AC,AD]
       because eqang [0][AC,AD] = [PB,PH] and eqang [0][AC,AD] = [OC,OM].
eqang [0][OM,OD] = [AC,AD] = [OC,OM] = [PB,PH]
       because eqang [0][OM,OD] = [OC,OM] and eqang [0][PB,PH] = [OC,OM] = [AC,AD].
eqang [0][BC,BP] = [AC,AD]
       because circle[$CDAB$].
eqang [0][PA,PF] = [AD,AC] = [BP,BC]
       because eqang [0][PA,PF] = [BP,BC] and eqang [0][BC,BP] = [AC,AD].
eqang [0][PB,PH] = [OC,OM] = [OM,OD] = [BC,BP] = [AC,AD] = [PF,PA]
       because eqang [0][OM,OD] = [AC,AD] = [OC,OM] = [PB,PH] and eqang [0][PA,PF] = [AD,AC] = [BP,BC].
eqang [0][PA,PN] = [AD,AC]
       because circle[$(N)AP$].
eqang [0][PF,PA] = [BC,BP] = [OM,OD] = [OC,OM] = [PB,PH] = [AC,AD] = [PN,PA]
       because eqang [0][PB,PH] = [OC,OM] = [OM,OD] = [BC,BP] = [AC,AD] = [PF,PA] and eqang [0][PA,PN] = [AD,AC].
eqang [0][DB,DA] = [PN,PB]
       because circle[$(N)PD$].
eqang [0][PH,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB]
       because eqang [0][PB,PF] = [OA,OK] = [OK,OB] = [DA,DB] = [CA,CB] = [PH,PA] and eqang [0][DB,DA] = [PN,PB].
eqang [0][PA,PM] = [CD,CA]
       because circle[$(M)CP$].
eqang [0][PB,PG] = [BA,BP] = [ON,OD] = [OA,ON] = [PE,PA] = [CA,CD] = [PM,PA]
       because eqang [0][PE,PA] = [OA,ON] = [ON,OD] = [CA,CD] = [BA,BP] = [PB,PG] and eqang [0][PA,PM] = [CD,CA].
eqang [0][DB,DC] = [PM,PB]
       because circle[$(M)PD$].
eqang [0][PA,PG] = [AB,AC] = [OL,OC] = [OB,OL] = [PE,PB] = [PM,PB] = [DB,DC]
       because eqang [0][PE,PB] = [OB,OL] = [OL,OC] = [DB,DC] = [AB,AC] = [PA,PG] and eqang [0][DB,DC] = [PM,PB].
eqang [0][PB,PL] = [BC,BP]
       because circle[$(L)BP$].
eqang [0][PN,PA] = [AC,AD] = [PB,PH] = [OC,OM] = [OM,OD] = [PF,PA] = [BC,BP] = [PB,PL]
       because eqang [0][PF,PA] = [BC,BP] = [OM,OD] = [OC,OM] = [PB,PH] = [AC,AD] = [PN,PA] and eqang [0][PB,PL] = [BC,BP].
eqang [0][PA,PL] = [CB,CA]
       because circle[$(L)CP$].
eqang [0][DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [PH,PA] = [CA,CB] = [PL,PA]
       because eqang [0][PH,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] and eqang [0][PA,PL] = [CB,CA].
eqang [0][PA,PK] = [AB,AC]
       because circle[$(K)AP$].
eqang [0][DB,DC] = [PM,PB] = [PE,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [AB,AC] = [PA,PK]
       because eqang [0][PA,PG] = [AB,AC] = [OL,OC] = [OB,OL] = [PE,PB] = [PM,PB] = [DB,DC] and eqang [0][PA,PK] = [AB,AC].
eqang [0][PB,PK] = [BA,BP]
       because circle[$(K)BP$].
eqang [0][PM,PA] = [CA,CD] = [PE,PA] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK]
       because eqang [0][PB,PG] = [BA,BP] = [ON,OD] = [OA,ON] = [PE,PA] = [CA,CD] = [PM,PA] and eqang [0][PB,PK] = [BA,BP].
eqang [0][PO,PE] = [OP,OK]
       because para[$OK,PE$].
eqang [0][PH,PE] = [ON,OK]
       because para[$NO,HP$].
eqang [0][PG,PE] = [OM,OK]
       because para[$MO,GP$].
eqang [0][PF,PE] = [OL,OK]
       because para[$LO,FP$].
eqang [1][EO,EP] = [OE,OK]
       because para[$OK,EP$].
eqang [0][PK,PE] = [KP,KO]
       because para[$KO,PE$].
eqang [0][PB,PE] = [KN,KO]
       because para[$NK,BP$].
eqang [0][PA,PK] = [AB,AC] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB]
       because eqang [0][DB,DC] = [PM,PB] = [PE,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [AB,AC] = [PA,PK] and eqang [0][PB,PE] = [KN,KO].
eqang [0][PA,PE] = [KL,KO]
       because para[$LK,AP$].
eqang [0][PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [KO,KL] = [PE,PA]
       because eqang [0][PM,PA] = [CA,CD] = [PE,PA] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] and eqang [0][PA,PE] = [KL,KO].
eqang [0][KO,KL] = [AB,ML]
       because perp[$ML,LK$] and perp[$AB,OK$].
eqang [0][PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] = [AB,ML] = [KO,KL]
       because eqang [0][PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [KO,KL] = [PE,PA] and eqang [0][KO,KL] = [AB,ML].
eqang [0][OK,ML] = [KA,KL]
       because perp[$ML,LK$] and perp[$AB,OK$].
eqang [0][OK,AC] = [AB,ML]
       because perp[$ML,AC$] and perp[$AB,OK$].
eqang [0][KO,KL] = [PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [AB,ML] = [OK,AC]
       because eqang [0][PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] = [AB,ML] = [KO,KL] and eqang [0][OK,AC] = [AB,ML].
eqang [0][OK,ML] = [AB,AC]
       because perp[$ML,AC$] and perp[$AB,OK$].
eqang [0][PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [PA,PK] = [AB,AC] = [OK,ML]
       because eqang [0][PA,PK] = [AB,AC] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] and eqang [0][OK,ML] = [AB,AC].
eqang [0][KA,KL] = [OK,ML] = [AB,AC] = [PA,PK] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB]
       because eqang [0][OK,ML] = [KA,KL] and eqang [0][PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [PA,PK] = [AB,AC] = [OK,ML].
eqang [0][OK,NM] = [AB,ML]
       because perp[$ML,NM$] and perp[$AB,OK$].
eqang [0][OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] = [KO,KL] = [AB,ML] = [OK,NM]
       because eqang [0][KO,KL] = [PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [AB,ML] = [OK,AC] and eqang [0][OK,NM] = [AB,ML].
eqang [0][OK,ML] = [AB,NM]
       because perp[$ML,NM$] and perp[$AB,OK$].
eqang [0][PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [PA,PK] = [AB,AC] = [KA,KL] = [AB,NM] = [OK,ML]
       because eqang [0][KA,KL] = [OK,ML] = [AB,AC] = [PA,PK] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] and eqang [0][OK,ML] = [AB,NM].
eqang [0][OK,BP] = [KA,KL]
       because perp[$BP,LK$] and perp[$AB,OK$].
eqang [0][OK,ML] = [AB,NM] = [AB,AC] = [PA,PK] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] = [KA,KL] = [OK,BP]
       because eqang [0][PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [PA,PK] = [AB,AC] = [KA,KL] = [AB,NM] = [OK,ML] and eqang [0][OK,BP] = [KA,KL].
eqang [0][KO,KL] = [KA,KN]
       because perp[$NK,LK$] and perp[$AB,OK$].
eqang [0][OK,NM] = [AB,ML] = [PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL]
       because eqang [0][OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] = [KO,KL] = [AB,ML] = [OK,NM] and eqang [0][KO,KL] = [KA,KN].
eqang [0][OK,AD] = [AB,ON]
       because perp[$AD,ON$] and perp[$AB,OK$].
eqang [0][OK,PH] = [AB,AD]
       because perp[$AD,PH$] and perp[$AB,OK$].
eqang [0][CB,CD] = [AB,AD] = [OK,PH]
       because eqang [0][CB,CD] = [AB,AD] and eqang [0][OK,PH] = [AB,AD].
eqang [0][OK,AD] = [AB,PH]
       because perp[$AD,PH$] and perp[$AB,OK$].
eqang [0][AB,ON] = [AB,PH] = [OK,AD]
       because eqang [0][OK,AD] = [AB,ON] and eqang [0][OK,AD] = [AB,PH].
eqang [0][OK,BC] = [AB,OL]
       because perp[$BC,OL$] and perp[$AB,OK$].
eqang [0][OK,PF] = [BA,BC]
       because perp[$BC,PF$] and perp[$AB,OK$].
eqang [0][DA,DC] = [BA,BC] = [OK,PF]
       because eqang [0][DA,DC] = [BA,BC] and eqang [0][OK,PF] = [BA,BC].
eqang [0][OK,BC] = [AB,PF]
       because perp[$BC,PF$] and perp[$AB,OK$].
eqang [0][AB,OL] = [AB,PF] = [OK,BC]
       because eqang [0][OK,BC] = [AB,OL] and eqang [0][OK,BC] = [AB,PF].
eqang [0][OK,OM] = [AB,CD]
       because perp[$CD,OM$] and perp[$AB,OK$].
eqang [0][PG,PE] = [CD,AB] = [OM,OK]
       because eqang [0][PG,PE] = [OM,OK] and eqang [0][OK,OM] = [AB,CD].
eqang [0][OK,CD] = [AB,OM]
       because perp[$CD,OM$] and perp[$AB,OK$].
eqang [0][KO,KP] = [AB,CD]
       because perp[$CD,PG$] and perp[$AB,OK$].
eqang [0][OM,OK] = [PG,PE] = [CD,AB] = [KP,KO]
       because eqang [0][PG,PE] = [CD,AB] = [OM,OK] and eqang [0][KO,KP] = [AB,CD].
eqang [0][OK,CD] = [KA,KP]
       because perp[$CD,PG$] and perp[$AB,OK$].
eqang [0][AB,OM] = [KA,KP] = [OK,CD]
       because eqang [0][OK,CD] = [AB,OM] and eqang [0][OK,CD] = [KA,KP].
eqang [0][PE,LK] = [AB,ML]
       because perp[$ML,LK$] and perp[$AB,PE$].
eqang [0][KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] = [OK,NM] = [AB,ML] = [PE,LK]
       because eqang [0][OK,NM] = [AB,ML] = [PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] and eqang [0][PE,LK] = [AB,ML].
eqang [0][MP,ML] = [KA,KL]
       because perp[$ML,LK$] and perp[$AB,PE$].
eqang [0][OK,BP] = [PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [PA,PK] = [AB,AC] = [AB,NM] = [OK,ML] = [KA,KL] = [MP,ML]
       because eqang [0][OK,ML] = [AB,NM] = [AB,AC] = [PA,PK] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] = [KA,KL] = [OK,BP] and eqang [0][MP,ML] = [KA,KL].
eqang [0][MP,MN] = [AB,ML]
       because perp[$ML,NM$] and perp[$AB,PE$].
eqang [0][PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN]
       because eqang [0][KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [PB,PG] = [BA,BP] = [PB,PK] = [OK,NM] = [AB,ML] = [PE,LK] and eqang [0][MP,MN] = [AB,ML].
eqang [0][PE,NK] = [KA,KL]
       because perp[$NK,LK$] and perp[$AB,PE$].
eqang [0][MP,ML] = [OK,ML] = [AB,NM] = [AB,AC] = [PA,PK] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] = [OK,BP] = [KA,KL] = [PE,NK]
       because eqang [0][OK,BP] = [PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PG] = [PA,PK] = [AB,AC] = [AB,NM] = [OK,ML] = [KA,KL] = [MP,ML] and eqang [0][PE,NK] = [KA,KL].
eqang [0][PE,ON] = [AB,AD]
       because perp[$AD,ON$] and perp[$AB,PE$].
eqang [0][OK,PH] = [CB,CD] = [AB,AD] = [PE,ON]
       because eqang [0][CB,CD] = [AB,AD] = [OK,PH] and eqang [0][PE,ON] = [AB,AD].
eqang [0][PE,AD] = [AB,ON]
       because perp[$AD,ON$] and perp[$AB,PE$].
eqang [0][OK,AD] = [AB,PH] = [AB,ON] = [PM,AD]
       because eqang [0][AB,ON] = [AB,PH] = [OK,AD] and eqang [0][PE,AD] = [AB,ON].
eqang [0][PE,OL] = [BA,BC]
       because perp[$BC,OL$] and perp[$AB,PE$].
eqang [0][OK,PF] = [DA,DC] = [BA,BC] = [PE,OL]
       because eqang [0][DA,DC] = [BA,BC] = [OK,PF] and eqang [0][PE,OL] = [BA,BC].
eqang [0][PE,BC] = [AB,OL]
       because perp[$BC,OL$] and perp[$AB,PE$].
eqang [0][OK,BC] = [AB,PF] = [AB,OL] = [PM,BC]
       because eqang [0][AB,OL] = [AB,PF] = [OK,BC] and eqang [0][PE,BC] = [AB,OL].
eqang [0][MP,MO] = [AB,CD]
       because perp[$CD,OM$] and perp[$AB,PE$].
eqang [0][KP,KO] = [PG,PE] = [OM,OK] = [CD,AB] = [MO,MP]
       because eqang [0][OM,OK] = [PG,PE] = [CD,AB] = [KP,KO] and eqang [0][MP,MO] = [AB,CD].
eqang [0][MP,MC] = [AB,OM]
       because perp[$CD,OM$] and perp[$AB,PE$].
eqang [0][OK,CD] = [KA,KP] = [AB,OM] = [MP,MC]
       because eqang [0][AB,OM] = [KA,KP] = [OK,CD] and eqang [0][MP,MC] = [AB,OM].
eqang [0][PO,PL] = [OP,ON]
       because para[$ON,PH$].
eqang [0][PG,PH] = [OM,ON]
       because para[$MO,GP$].
eqang [0][PF,PH] = [OL,ON]
       because para[$LO,FP$].
eqang [0][HO,HP] = [OH,ON]
       because para[$ON,HP$].
eqang [0][PN,PH] = [NP,NO]
       because para[$NO,PH$].
eqang [0][PA,PH] = [NM,NO]
       because para[$MN,AP$].
eqang [0][PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] = [NO,NM] = [PH,PA]
       because eqang [0][DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [PH,PA] = [CA,CB] = [PL,PA] and eqang [0][PA,PH] = [NM,NO].
eqang [0][PB,PH] = [NK,NO]
       because para[$KN,BP$].
eqang [0][PB,PL] = [BC,BP] = [PF,PA] = [OM,OD] = [OC,OM] = [AC,AD] = [PN,PA] = [NK,NO] = [PB,PH]
       because eqang [0][PN,PA] = [AC,AD] = [PB,PH] = [OC,OM] = [OM,OD] = [PF,PA] = [BC,BP] = [PB,PL] and eqang [0][PB,PH] = [NK,NO].
eqang [0][ON,LK] = [AD,ML]
       because perp[$ML,LK$] and perp[$AD,ON$].
eqang [0][ON,ML] = [AD,LK]
       because perp[$ML,LK$] and perp[$AD,ON$].
eqang [0][ON,AC] = [AD,ML]
       because perp[$ML,AC$] and perp[$AD,ON$].
eqang [0][ON,LK] = [AD,ML] = [ON,AC]
       because eqang [0][ON,LK] = [AD,ML] and eqang [0][ON,AC] = [AD,ML].
eqang [0][ON,ML] = [AD,AC]
       because perp[$ML,AC$] and perp[$AD,ON$].
eqang [0][PB,PH] = [NK,NO] = [PN,PA] = [OC,OM] = [OM,OD] = [PF,PA] = [BC,BP] = [PB,PL] = [AC,AD] = [ML,ON]
       because eqang [0][PB,PL] = [BC,BP] = [PF,PA] = [OM,OD] = [OC,OM] = [AC,AD] = [PN,PA] = [NK,NO] = [PB,PH] and eqang [0][ON,ML] = [AD,AC].
eqang [0][AD,LK] = [ON,ML] = [AD,AC] = [PL,PB] = [BP,BC] = [PA,PF] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB]
       because eqang [0][ON,ML] = [AD,LK] and eqang [0][PB,PH] = [NK,NO] = [PN,PA] = [OC,OM] = [OM,OD] = [PF,PA] = [BC,BP] = [PB,PL] = [AC,AD] = [ML,ON].
eqang [0][NO,NM] = [AD,ML]
       because perp[$ML,NM$] and perp[$AD,ON$].
eqang [0][PH,PA] = [DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [AD,ML] = [NO,NM]
       because eqang [0][PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] = [NO,NM] = [PH,PA] and eqang [0][NO,NM] = [AD,ML].
eqang [0][ON,AC] = [ON,LK] = [NO,NM] = [AD,ML] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] = [PH,PA]
       because eqang [0][ON,LK] = [AD,ML] = [ON,AC] and eqang [0][PH,PA] = [DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [AD,ML] = [NO,NM].
eqang [0][ON,ML] = [NA,NM]
       because perp[$ML,NM$] and perp[$AD,ON$].
eqang [0][PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [PA,PF] = [BP,BC] = [PL,PB] = [AD,AC] = [AD,LK] = [NA,NM] = [ON,ML]
       because eqang [0][AD,LK] = [ON,ML] = [AD,AC] = [PL,PB] = [BP,BC] = [PA,PF] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] and eqang [0][ON,ML] = [NA,NM].
eqang [0][ON,BP] = [AD,LK]
       because perp[$BP,LK$] and perp[$AD,ON$].
eqang [0][ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [PA,PF] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [AD,LK] = [ON,BP]
       because eqang [0][PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [PA,PF] = [BP,BC] = [PL,PB] = [AD,AC] = [AD,LK] = [NA,NM] = [ON,ML] and eqang [0][ON,BP] = [AD,LK].
eqang [0][ON,LK] = [NA,NK]
       because perp[$NK,LK$] and perp[$AD,ON$].
eqang [0][PH,PA] = [DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [AD,ML] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK]
       because eqang [0][ON,AC] = [ON,LK] = [NO,NM] = [AD,ML] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] = [PH,PA] and eqang [0][ON,LK] = [NA,NK].
eqang [0][ON,OL] = [AD,BC]
       because perp[$BC,OL$] and perp[$AD,ON$].
eqang [0][PF,PH] = [BC,AD] = [OL,ON]
       because eqang [0][PF,PH] = [OL,ON] and eqang [0][ON,OL] = [AD,BC].
eqang [0][ON,BC] = [AD,OL]
       because perp[$BC,OL$] and perp[$AD,ON$].
eqang [0][NO,NP] = [AD,BC]
       because perp[$BC,PF$] and perp[$AD,ON$].
eqang [0][OL,ON] = [PF,PH] = [BC,AD] = [NP,NO]
       because eqang [0][PF,PH] = [BC,AD] = [OL,ON] and eqang [0][NO,NP] = [AD,BC].
eqang [0][ON,BC] = [NA,NP]
       because perp[$BC,PF$] and perp[$AD,ON$].
eqang [0][AD,OL] = [NA,NP] = [ON,BC]
       because eqang [0][ON,BC] = [AD,OL] and eqang [0][ON,BC] = [NA,NP].
eqang [0][ON,CD] = [AD,OM]
       because perp[$CD,OM$] and perp[$AD,ON$].
eqang [0][ON,PG] = [DA,DC]
       because perp[$CD,PG$] and perp[$AD,ON$].
eqang [0][PE,OL] = [BA,BC] = [OK,PF] = [DA,DC] = [ON,PG]
       because eqang [0][OK,PF] = [DA,DC] = [BA,BC] = [PE,OL] and eqang [0][ON,PG] = [DA,DC].
eqang [0][ON,CD] = [AD,PG]
       because perp[$CD,PG$] and perp[$AD,ON$].
eqang [0][AD,OM] = [AD,PG] = [ON,CD]
       because eqang [0][ON,CD] = [AD,OM] and eqang [0][ON,CD] = [AD,PG].
eqang [0][LP,LK] = [AD,ML]
       because perp[$ML,LK$] and perp[$AD,PH$].
eqang [0][ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] = [PH,PA] = [AD,ML] = [LP,LK]
       because eqang [0][PH,PA] = [DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [AD,ML] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] and eqang [0][LP,LK] = [AD,ML].
eqang [0][LP,LM] = [AD,LK]
       because perp[$ML,LK$] and perp[$AD,PH$].
eqang [0][ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [PA,PF] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [AD,LK] = [LP,LM]
       because eqang [0][ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [PA,PF] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [AD,LK] = [ON,BP] and eqang [0][LP,LM] = [AD,LK].
eqang [0][PH,NM] = [AD,ML]
       because perp[$ML,NM$] and perp[$AD,PH$].
eqang [0][LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM]
       because eqang [0][ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PF] = [PB,PN] = [DA,DB] = [PH,PA] = [AD,ML] = [LP,LK] and eqang [0][PH,NM] = [AD,ML].
eqang [0][PH,NK] = [AD,LK]
       because perp[$NK,LK$] and perp[$AD,PH$].
eqang [0][LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [PA,PF] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK]
       because eqang [0][ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [PA,PF] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [AD,LK] = [LP,LM] and eqang [0][PH,NK] = [AD,LK].
eqang [0][LP,LO] = [AD,BC]
       because perp[$BC,OL$] and perp[$AD,PH$].
eqang [0][NP,NO] = [PF,PH] = [OL,ON] = [BC,AD] = [LO,LP]
       because eqang [0][OL,ON] = [PF,PH] = [BC,AD] = [NP,NO] and eqang [0][LP,LO] = [AD,BC].
eqang [0][LP,LB] = [AD,OL]
       because perp[$BC,OL$] and perp[$AD,PH$].
eqang [0][ON,BC] = [NA,NP] = [AD,OL] = [LP,LB]
       because eqang [0][AD,OL] = [NA,NP] = [ON,BC] and eqang [0][LP,LB] = [AD,OL].
eqang [0][PH,OM] = [DA,DC]
       because perp[$CD,OM$] and perp[$AD,PH$].
eqang [0][ON,PG] = [OK,PF] = [BA,BC] = [PE,OL] = [DA,DC] = [PH,OM]
       because eqang [0][PE,OL] = [BA,BC] = [OK,PF] = [DA,DC] = [ON,PG] and eqang [0][PH,OM] = [DA,DC].
eqang [0][PH,CD] = [AD,OM]
       because perp[$CD,OM$] and perp[$AD,PH$].
eqang [0][ON,CD] = [AD,PK] = [AD,OM] = [PH,CD]
       because eqang [0][AD,OM] = [AD,PG] = [ON,CD] and eqang [0][PH,CD] = [AD,OM].
eqang [0][PO,PN] = [OP,OL]
       because para[$OL,PF$].
eqang [0][PG,PF] = [OM,OL]
       because para[$MO,GP$].
eqang [1][FO,FP] = [OF,OL]
       because para[$OL,FP$].
eqang [0][PL,PF] = [LP,LO]
       because para[$LO,PF$].
eqang [0][PB,PF] = [LM,LO]
       because para[$ML,BP$].
eqang [0][PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [LM,LO] = [PB,PF]
       because eqang [0][LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [PB,PF] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] and eqang [0][PB,PF] = [LM,LO].
eqang [0][PA,PF] = [LK,LO]
       because para[$KL,AP$].
eqang [0][PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [LK,LO] = [PA,PF]
       because eqang [0][LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [PA,PF] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] and eqang [0][PA,PF] = [LK,LO].
eqang [0][LO,LK] = [LB,LM]
       because perp[$ML,LK$] and perp[$BC,OL$].
eqang [0][PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [LM,LB] = [LK,LO]
       because eqang [0][PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [LK,LO] = [PA,PF] and eqang [0][LO,LK] = [LB,LM].
eqang [0][LO,LM] = [LB,LK]
       because perp[$ML,LK$] and perp[$BC,OL$].
eqang [0][PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [LK,LB] = [LM,LO]
       because eqang [0][PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [LM,LO] = [PB,PF] and eqang [0][LO,LM] = [LB,LK].
eqang [0][OL,AC] = [LB,LM]
       because perp[$ML,AC$] and perp[$BC,OL$].
eqang [0][LK,LO] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [LM,LB] = [AC,OL]
       because eqang [0][PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [LM,LB] = [LK,LO] and eqang [0][OL,AC] = [LB,LM].
eqang [0][OL,NM] = [LB,LM]
       because perp[$ML,NM$] and perp[$BC,OL$].
eqang [0][AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [LK,LO] = [LM,LB] = [NM,OL]
       because eqang [0][LK,LO] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [LM,LB] = [AC,OL] and eqang [0][OL,NM] = [LB,LM].
eqang [0][LO,LM] = [BC,NM]
       because perp[$ML,NM$] and perp[$BC,OL$].
eqang [0][LK,LB] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO]
       because eqang [0][PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [LK,LB] = [LM,LO] and eqang [0][LO,LM] = [BC,NM].
eqang [0][OL,BP] = [LB,LK]
       because perp[$BP,LK$] and perp[$BC,OL$].
eqang [0][LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [LK,LB] = [BP,OL]
       because eqang [0][LK,LB] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] and eqang [0][OL,BP] = [LB,LK].
eqang [0][LO,LK] = [BC,NK]
       because perp[$NK,LK$] and perp[$BC,OL$].
eqang [0][NM,OL] = [LM,LB] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO]
       because eqang [0][AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [LK,LO] = [LM,LB] = [NM,OL] and eqang [0][LO,LK] = [BC,NK].
eqang [0][OL,NK] = [LB,LK]
       because perp[$NK,LK$] and perp[$BC,OL$].
eqang [0][BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] = [LK,LB] = [NK,OL]
       because eqang [0][LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [LK,LB] = [BP,OL] and eqang [0][OL,NK] = [LB,LK].
eqang [0][OL,CD] = [BC,OM]
       because perp[$CD,OM$] and perp[$BC,OL$].
eqang [0][OL,PG] = [CB,CD]
       because perp[$CD,PG$] and perp[$BC,OL$].
eqang [0][PE,ON] = [AB,AD] = [OK,PH] = [CB,CD] = [OL,PG]
       because eqang [0][OK,PH] = [CB,CD] = [AB,AD] = [PE,ON] and eqang [0][OL,PG] = [CB,CD].
eqang [0][OL,CD] = [BC,PG]
       because perp[$CD,PG$] and perp[$BC,OL$].
eqang [0][BC,OM] = [BC,PG] = [OL,CD]
       because eqang [0][OL,CD] = [BC,OM] and eqang [0][OL,CD] = [BC,PG].
eqang [0][PF,LK] = [LB,LM]
       because perp[$ML,LK$] and perp[$BC,PF$].
eqang [0][LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LM,LB] = [LK,PF]
       because eqang [0][NM,OL] = [LM,LB] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] and eqang [0][PF,LK] = [LB,LM].
eqang [0][PF,ML] = [LB,LK]
       because perp[$ML,LK$] and perp[$BC,PF$].
eqang [0][NK,OL] = [LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [LK,LB] = [ML,PF]
       because eqang [0][BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] = [LK,LB] = [NK,OL] and eqang [0][PF,ML] = [LB,LK].
eqang [0][NP,NM] = [LB,LM]
       because perp[$ML,NM$] and perp[$BC,PF$].
eqang [0][LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP]
       because eqang [0][LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [PL,PB] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LM,LB] = [LK,PF] and eqang [0][NP,NM] = [LB,LM].
eqang [0][NP,NK] = [LB,LK]
       because perp[$NK,LK$] and perp[$BC,PF$].
eqang [0][ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP]
       because eqang [0][NK,OL] = [LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [PB,PN] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [LK,LB] = [ML,PF] and eqang [0][NP,NK] = [LB,LK].
eqang [0][PF,OM] = [CB,CD]
       because perp[$CD,OM$] and perp[$BC,PF$].
eqang [0][OL,PG] = [OK,PH] = [AB,AD] = [PE,ON] = [CB,CD] = [PF,OM]
       because eqang [0][PE,ON] = [AB,AD] = [OK,PH] = [CB,CD] = [OL,PG] and eqang [0][PF,OM] = [CB,CD].
eqang [0][PF,CD] = [BC,OM]
       because perp[$CD,OM$] and perp[$BC,PF$].
eqang [0][OL,CD] = [BC,PK] = [BC,OM] = [PF,CD]
       because eqang [0][BC,OM] = [BC,PG] = [OL,CD] and eqang [0][PF,CD] = [BC,OM].
eqang [0][PO,PG] = [OP,OM]
       because para[$OM,PG$].
eqang [1][GO,GP] = [OG,OM]
       because para[$OM,GP$].
eqang [0][PM,PG] = [MP,MO]
       because para[$MO,PG$].
eqang [0][PA,PG] = [MN,MO]
       because para[$NM,AP$].
eqang [0][PE,NK] = [KA,KL] = [OK,BP] = [PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PK] = [AB,AC] = [AB,NM] = [OK,ML] = [MP,ML] = [MN,MO] = [PA,PG]
       because eqang [0][MP,ML] = [OK,ML] = [AB,NM] = [AB,AC] = [PA,PK] = [PA,PG] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] = [OK,BP] = [KA,KL] = [PE,NK] and eqang [0][PA,PG] = [MN,MO].
eqang [0][PB,PG] = [ML,MO]
       because para[$LM,BP$].
eqang [0][MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [ML,MO] = [PB,PG]
       because eqang [0][PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [PB,PG] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] and eqang [0][PB,PG] = [ML,MO].
eqang [0][OM,LK] = [MC,ML]
       because perp[$ML,LK$] and perp[$CD,OM$].
eqang [0][MO,ML] = [CD,LK]
       because perp[$ML,LK$] and perp[$CD,OM$].
eqang [0][PB,PG] = [PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [LK,CD] = [ML,MO]
       because eqang [0][MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [ML,MO] = [PB,PG] and eqang [0][MO,ML] = [CD,LK].
eqang [0][OM,AC] = [MC,ML]
       because perp[$ML,AC$] and perp[$CD,OM$].
eqang [0][OM,LK] = [MC,ML] = [OM,AC]
       because eqang [0][OM,LK] = [MC,ML] and eqang [0][OM,AC] = [MC,ML].
eqang [0][MO,MN] = [MC,ML]
       because perp[$ML,NM$] and perp[$CD,OM$].
eqang [0][PA,PG] = [MP,ML] = [OK,ML] = [AB,NM] = [AB,AC] = [PA,PK] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] = [OK,BP] = [KA,KL] = [PE,NK] = [ML,MC] = [MN,MO]
       because eqang [0][PE,NK] = [KA,KL] = [OK,BP] = [PE,PB] = [KO,KN] = [DB,DC] = [PM,PB] = [OB,OL] = [OL,OC] = [PA,PK] = [AB,AC] = [AB,NM] = [OK,ML] = [MP,ML] = [MN,MO] = [PA,PG] and eqang [0][MO,MN] = [MC,ML].
eqang [0][OM,AC] = [OM,LK] = [MO,MN] = [MC,ML] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [KN,KO] = [DC,DB] = [PB,PM] = [OL,OB] = [OC,OL] = [PK,PA] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA]
       because eqang [0][OM,LK] = [MC,ML] = [OM,AC] and eqang [0][PA,PG] = [MP,ML] = [OK,ML] = [AB,NM] = [AB,AC] = [PA,PK] = [OL,OC] = [OB,OL] = [PM,PB] = [DB,DC] = [KO,KN] = [PE,PB] = [OK,BP] = [KA,KL] = [PE,NK] = [ML,MC] = [MN,MO].
eqang [0][MO,ML] = [MC,MN]
       because perp[$ML,NM$] and perp[$CD,OM$].
eqang [0][LK,CD] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO]
       because eqang [0][PB,PG] = [PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [LK,CD] = [ML,MO] and eqang [0][MO,ML] = [MC,MN].
eqang [0][OM,BP] = [CD,LK]
       because perp[$BP,LK$] and perp[$CD,OM$].
eqang [0][ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [LK,CD] = [BP,OM]
       because eqang [0][LK,CD] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] and eqang [0][OM,BP] = [CD,LK].
eqang [0][OM,LK] = [CD,NK]
       because perp[$NK,LK$] and perp[$CD,OM$].
eqang [0][PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [AC,AB] = [PK,PA] = [OC,OL] = [OL,OB] = [PB,PM] = [DC,DB] = [KN,KO] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MC,ML] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK]
       because eqang [0][OM,AC] = [OM,LK] = [MO,MN] = [MC,ML] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [KN,KO] = [DC,DB] = [PB,PM] = [OL,OB] = [OC,OL] = [PK,PA] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] and eqang [0][OM,LK] = [CD,NK].
eqang [0][OM,NK] = [CD,LK]
       because perp[$NK,LK$] and perp[$CD,OM$].
eqang [0][BP,OM] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [LK,CD] = [NK,OM]
       because eqang [0][ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [LK,CD] = [BP,OM] and eqang [0][OM,NK] = [CD,LK].
eqang [0][KP,KL] = [MC,ML]
       because perp[$ML,LK$] and perp[$CD,PG$].
eqang [0][OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [KN,KO] = [DC,DB] = [PB,PM] = [OL,OB] = [OC,OL] = [PK,PA] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [MC,ML] = [KP,KL]
       because eqang [0][PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [AC,AB] = [PK,PA] = [OC,OL] = [OL,OB] = [PB,PM] = [DC,DB] = [KN,KO] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MC,ML] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] and eqang [0][KP,KL] = [MC,ML].
eqang [0][PG,ML] = [CD,LK]
       because perp[$ML,LK$] and perp[$CD,PG$].
eqang [0][NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [BP,OM] = [LK,CD] = [ML,PG]
       because eqang [0][BP,OM] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [LK,CD] = [NK,OM] and eqang [0][PG,ML] = [CD,LK].
eqang [0][PG,NM] = [MC,ML]
       because perp[$ML,NM$] and perp[$CD,PG$].
eqang [0][KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [AC,AB] = [PK,PA] = [OC,OL] = [OL,OB] = [PB,PM] = [DC,DB] = [KN,KO] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM]
       because eqang [0][OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [KN,KO] = [DC,DB] = [PB,PM] = [OL,OB] = [OC,OL] = [PK,PA] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [MC,ML] = [KP,KL] and eqang [0][PG,NM] = [MC,ML].
eqang [0][KP,KN] = [CD,LK]
       because perp[$NK,LK$] and perp[$CD,PG$].
eqang [0][ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP]
       because eqang [0][NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [PB,PK] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [BP,OM] = [LK,CD] = [ML,PG] and eqang [0][KP,KN] = [CD,LK].
eqang [1][BM,BP] = [MB,ML]
       because para[$ML,BP$].
eqang [0][PM,PB] = [MP,ML]
       because para[$ML,PB$].
eqang [0][PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [KN,KO] = [DC,DB] = [OL,OB] = [OC,OL] = [PK,PA] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [KP,KL] = [ML,MP] = [PB,PM]
       because eqang [0][KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [AC,AB] = [PK,PA] = [OC,OL] = [OL,OB] = [PB,PM] = [DC,DB] = [KN,KO] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] and eqang [0][PM,PB] = [MP,ML].
eqang [0][PL,PB] = [LP,LM]
       because para[$LM,PB$].
eqang [0][NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB]
       because eqang [0][LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [PA,PN] = [OM,OC] = [OD,OM] = [BP,BC] = [PL,PB] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] and eqang [0][PL,PB] = [LP,LM].
eqang [1][DL,DB] = [LD,LM]
       because para[$LM,DB$].
eqang [0][KM,KN] = [MK,ML]
       because para[$ML,KN$].
eqang [0][NL,NK] = [LN,LM]
       because para[$LM,NK$].
eqang [1][NB,NK] = [BN,BP]
       because para[$BP,NK$].
eqang [0][NP,NK] = [PN,PB]
       because para[$PB,NK$].
eqang [0][NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP]
       because eqang [0][ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [PL,PA] = [CA,CB] = [OK,OB] = [OA,OK] = [PB,PN] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] and eqang [0][NP,NK] = [PN,PB].
eqang [0][KP,KN] = [PK,PB]
       because para[$PB,KN$].
eqang [0][KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP]
       because eqang [0][ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [PM,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [PB,PK] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] and eqang [0][KP,KN] = [PK,PB].
eqang [1][KD,KN] = [DK,DB]
       because para[$DB,KN$].
eqang [1][AL,AC] = [LA,LK]
       because para[$LK,AC$].
eqang [0][PL,PA] = [LP,LK]
       because para[$LK,PA$].
eqang [0][NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [CA,CB] = [OK,OB] = [OA,OK] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA]
       because eqang [0][NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [PH,PA] = [DA,DB] = [OA,OK] = [OK,OB] = [CA,CB] = [PL,PA] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] and eqang [0][PL,PA] = [LP,LK].
eqang [1][CK,CA] = [KC,KL]
       because para[$KL,CA$].
eqang [0][PK,PA] = [KP,KL]
       because para[$KL,PA$].
eqang [0][PB,PM] = [ML,MP] = [KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [AC,AB] = [OC,OL] = [OL,OB] = [DC,DB] = [KN,KO] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA]
       because eqang [0][PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [KN,KO] = [DC,DB] = [OL,OB] = [OC,OL] = [PK,PA] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [KP,KL] = [ML,MP] = [PB,PM] and eqang [0][PK,PA] = [KP,KL].
eqang [0][NL,NM] = [LN,LK]
       because para[$LK,NM$].
eqang [0][MK,MN] = [KM,KL]
       because para[$KL,MN$].
eqang [1][MA,MN] = [AM,AC]
       because para[$AC,MN$].
eqang [1][NC,NM] = [CN,CA]
       because para[$CA,NM$].
eqang [0][NP,NM] = [PN,PA]
       because para[$PA,NM$].
eqang [0][PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [OM,OC] = [OD,OM] = [BP,BC] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP]
       because eqang [0][NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [BP,BC] = [OD,OM] = [OM,OC] = [PA,PN] = [NO,NK] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] and eqang [0][NP,NM] = [PN,PA].
eqang [0][MP,MN] = [PM,PA]
       because para[$PA,MN$].
eqang [0][KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN]
       because eqang [0][KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PM,PA] = [PE,PA] = [OK,AC] = [KA,KN] = [KO,KL] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] and eqang [0][MP,MN] = [PM,PA].
eqang [0][KO,KN] = [AO,AD]
       because circle[$ONAK$].
eqang [0][DA,DO] = [AO,AD] = [KO,KN]
       because eqang [0][DA,DO] = [AO,AD] and eqang [0][KO,KN] = [AO,AD].
eqang [0][PK,PA] = [KP,KL] = [PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [DC,DB] = [OL,OB] = [OC,OL] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [DO,DA]
       because eqang [0][PB,PM] = [ML,MP] = [KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [AC,AB] = [OC,OL] = [OL,OB] = [DC,DB] = [KN,KO] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] and eqang [0][DA,DO] = [AO,AD] = [KO,KN].
eqang [0][NO,NK] = [AO,AB]
       because circle[$OKAN$].
eqang [0][BA,BO] = [AO,AB] = [NO,NK]
       because eqang [0][BA,BO] = [AO,AB] and eqang [0][NO,NK] = [AO,AB].
eqang [0][NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [BP,BC] = [OD,OM] = [OM,OC] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO]
       because eqang [0][PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [NO,NK] = [OM,OC] = [OD,OM] = [BP,BC] = [AD,AC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] and eqang [0][BA,BO] = [AO,AB] = [NO,NK].
eqang [0][ON,OK] = [AD,AB]
       because circle[$NKAO$].
eqang [0][PH,PE] = [AD,AB] = [ON,OK]
       because eqang [0][PH,PE] = [ON,OK] and eqang [0][ON,OK] = [AD,AB].
eqang [0][PF,OM] = [CB,CD] = [PE,ON] = [OK,PH] = [OL,PG] = [OK,ON] = [AB,AD] = [PE,PH]
       because eqang [0][OL,PG] = [OK,PH] = [AB,AD] = [PE,ON] = [CB,CD] = [PF,OM] and eqang [0][PH,PE] = [AD,AB] = [ON,OK].
eqang [0][KO,KL] = [BO,BC]
       because circle[$OLBK$].
eqang [0][CB,CO] = [BO,BC] = [KO,KL]
       because eqang [0][CB,CO] = [BO,BC] and eqang [0][KO,KL] = [BO,BC].
eqang [0][MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PE,PA] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO]
       because eqang [0][KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KO,KL] = [KA,KN] = [OK,AC] = [PE,PA] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] and eqang [0][CB,CO] = [BO,BC] = [KO,KL].
eqang [0][OL,OK] = [BC,BA]
       because circle[$LKBO$].
eqang [0][PF,PE] = [BC,BA] = [OL,OK]
       because eqang [0][PF,PE] = [OL,OK] and eqang [0][OL,OK] = [BC,BA].
eqang [0][PH,OM] = [DA,DC] = [PE,OL] = [OK,PF] = [ON,PG] = [OK,OL] = [BA,BC] = [PE,PF]
       because eqang [0][ON,PG] = [OK,PF] = [BA,BC] = [PE,OL] = [DA,DC] = [PH,OM] and eqang [0][PF,PE] = [BC,BA] = [OL,OK].
eqang [0][HA,HE] = [PA,PE]
       because circle[$AEPH$].
eqang [0][CB,CO] = [BO,BC] = [KO,KL] = [KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [HE,HA]
       because eqang [0][MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [BA,BP] = [ON,OD] = [OA,ON] = [CA,CD] = [PE,PA] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] and eqang [0][HA,HE] = [PA,PE].
eqang [0][EA,EH] = [PA,PH]
       because circle[$AHPE$].
eqang [0][PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [DA,DB] = [OA,OK] = [OK,OB] = [CA,CB] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA]
       because eqang [0][NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [CA,CB] = [OK,OB] = [OA,OK] = [DA,DB] = [PH,PA] = [LP,LK] = [PB,PF] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] and eqang [0][EA,EH] = [PA,PH].
eqang [0][HP,HE] = [AC,AB]
       because circle[$PEAH$].
eqang [0][DO,DA] = [AD,AO] = [KN,KO] = [PB,PM] = [ML,MP] = [KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [DC,DB] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE]
       because eqang [0][PK,PA] = [KP,KL] = [PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [PB,PE] = [DC,DB] = [OL,OB] = [OC,OL] = [AC,AB] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [DO,DA] and eqang [0][HP,HE] = [AC,AB].
eqang [0][EP,EH] = [AC,AD]
       because circle[$PHAE$].
eqang [0][BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [OM,OC] = [OD,OM] = [BP,BC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP]
       because eqang [0][NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [AD,AC] = [BP,BC] = [OD,OM] = [OM,OC] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] and eqang [0][EP,EH] = [AC,AD].
eqang [0][EA,EF] = [PB,PF]
       because circle[$BFPE$].
eqang [0][EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [CA,CB] = [OK,OB] = [OA,OK] = [DA,DB] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF]
       because eqang [0][PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [PB,PF] = [LP,LK] = [DA,DB] = [OA,OK] = [OK,OB] = [CA,CB] = [NO,NM] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] and eqang [0][EA,EF] = [PB,PF].
eqang [0][FB,FE] = [PB,PE]
       because circle[$BEPF$].
eqang [0][HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [DC,DB] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [DO,DA] = [PB,PE] = [FB,FE]
       because eqang [0][DO,DA] = [AD,AO] = [KN,KO] = [PB,PM] = [ML,MP] = [KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [DC,DB] = [PB,PE] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] and eqang [0][FB,FE] = [PB,PE].
eqang [0][EP,EF] = [BP,BC]
       because circle[$PFBE$].
eqang [0][EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] = [BP,BC] = [EP,EF]
       because eqang [0][BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [PH,PB] = [OM,OC] = [OD,OM] = [BP,BC] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] and eqang [0][EP,EF] = [BP,BC].
eqang [0][FP,FE] = [BP,BA]
       because circle[$PEBF$].
eqang [0][HE,HA] = [PE,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [CA,CD] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP]
       because eqang [0][CB,CO] = [BO,BC] = [KO,KL] = [KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [CA,CD] = [OA,ON] = [ON,OD] = [BA,BP] = [OK,NM] = [PE,LK] = [PB,PG] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [HE,HA] and eqang [0][FP,FE] = [BP,BA].
eqang [0][NO,NM] = [DO,DC]
       because circle[$OMDN$].
eqang [0][CO,CD] = [DC,DO] = [NM,NO]
       because eqang [0][DC,DO] = [CO,CD] and eqang [0][NO,NM] = [DO,DC].
eqang [0][EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [DA,DB] = [OA,OK] = [OK,OB] = [CA,CB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] = [NO,NM] = [DO,DC] = [CD,CO]
       because eqang [0][EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [NO,NM] = [CA,CB] = [OK,OB] = [OA,OK] = [DA,DB] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] and eqang [0][CO,CD] = [DC,DO] = [NM,NO].
eqang [0][DA,DC] = [ON,OM]
       because circle[$NMOD$].
eqang [0][PG,PH] = [OM,ON] = [DC,DA]
       because eqang [0][PG,PH] = [OM,ON] and eqang [0][DA,DC] = [ON,OM].
eqang [0][PE,PF] = [BA,BC] = [OK,OL] = [ON,PG] = [OK,PF] = [PE,OL] = [PH,OM] = [DA,DC] = [ON,OM] = [PH,PG]
       because eqang [0][PH,OM] = [DA,DC] = [PE,OL] = [OK,PF] = [ON,PG] = [OK,OL] = [BA,BC] = [PE,PF] and eqang [0][PG,PH] = [OM,ON] = [DC,DA].
eqang [0][HP,HG] = [DB,DC]
       because circle[$PGDH$].
eqang [0][FB,FE] = [PB,PE] = [DO,DA] = [AD,AO] = [KN,KO] = [PB,PM] = [ML,MP] = [KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP]
       because eqang [0][HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [DC,DB] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PG,PA] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [DO,DA] = [PB,PE] = [FB,FE] and eqang [0][HP,HG] = [DB,DC].
eqang [0][GP,GH] = [DB,DA]
       because circle[$PHDG$].
eqang [0][CD,CO] = [DO,DC] = [NO,NM] = [EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [CA,CB] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP]
       because eqang [0][EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [DA,DB] = [OA,OK] = [OK,OB] = [CA,CB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] = [NO,NM] = [DO,DC] = [CD,CO] and eqang [0][GP,GH] = [DB,DA].
eqang [0][HA,HG] = [PB,PG]
       because circle[$DGPH$].
eqang [0][FE,FP] = [BA,BP] = [CB,CO] = [BO,BC] = [KO,KL] = [KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [CA,CD] = [OA,ON] = [ON,OD] = [OK,NM] = [PE,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [HE,HA] = [PB,PG] = [HA,HG]
       because eqang [0][HE,HA] = [PE,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PB,PG] = [PE,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [CA,CD] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] and eqang [0][HA,HG] = [PB,PG].
eqang [0][GC,GH] = [PB,PH]
       because circle[$DHPG$].
eqang [0][EP,EF] = [BP,BC] = [BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [GH,GC]
       because eqang [0][EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [PA,PF] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [PH,PB] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] = [BP,BC] = [EP,EF] and eqang [0][GC,GH] = [PB,PH].
eqang [0][OM,OL] = [CD,CB]
       because circle[$MLCO$].
eqang [0][PG,PF] = [CD,CB] = [OM,OL]
       because eqang [0][PG,PF] = [OM,OL] and eqang [0][OM,OL] = [CD,CB].
eqang [0][PE,PH] = [AB,AD] = [OK,ON] = [OL,PG] = [OK,PH] = [PE,ON] = [PF,OM] = [OL,OM] = [CB,CD] = [PF,PG]
       because eqang [0][PF,OM] = [CB,CD] = [PE,ON] = [OK,PH] = [OL,PG] = [OK,ON] = [AB,AD] = [PE,PH] and eqang [0][PG,PF] = [CD,CB] = [OM,OL].
eqang [0][FB,FG] = [PA,PG]
       because circle[$CGPF$].
eqang [0][HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [DO,DA] = [PB,PE] = [FB,FE] = [PG,PA] = [FG,FB]
       because eqang [0][FB,FE] = [PB,PE] = [DO,DA] = [AD,AO] = [KN,KO] = [PB,PM] = [ML,MP] = [KP,KL] = [PG,PA] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] and eqang [0][FB,FG] = [PA,PG].
eqang [0][GC,GF] = [PA,PF]
       because circle[$CFPG$].
eqang [0][GH,GC] = [PH,PB] = [EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] = [BP,BC] = [EP,EF] = [PA,PF] = [GC,GF]
       because eqang [0][EP,EF] = [BP,BC] = [BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [PA,PF] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [GH,GC] and eqang [0][GC,GF] = [PA,PF].
eqang [0][FP,FG] = [CA,CD]
       because circle[$PGCF$].
eqang [0][HA,HG] = [PB,PG] = [HE,HA] = [PE,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PE,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG]
       because eqang [0][FE,FP] = [BA,BP] = [CB,CO] = [BO,BC] = [KO,KL] = [KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [CA,CD] = [OA,ON] = [ON,OD] = [OK,NM] = [PE,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [HE,HA] = [PB,PG] = [HA,HG] and eqang [0][FP,FG] = [CA,CD].
eqang [0][GP,GF] = [CA,CB]
       because circle[$PFCG$].
eqang [0][GH,GP] = [DA,DB] = [EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] = [NO,NM] = [DO,DC] = [CD,CO] = [CA,CB] = [GP,GF]
       because eqang [0][CD,CO] = [DO,DC] = [NO,NM] = [EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [CA,CB] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] and eqang [0][GP,GF] = [CA,CB].
eqang [0][PL,PK] = [PH,PG]
       because $[GH,HP]=[PK,KL]$ and $[HG,GP]=[PL,LK]$.
eqang [0][ON,OM] = [DA,DC] = [PH,OM] = [PE,OL] = [OK,PF] = [ON,PG] = [OK,OL] = [BA,BC] = [PE,PF] = [PH,PG] = [PL,PK]
       because eqang [0][PE,PF] = [BA,BC] = [OK,OL] = [ON,PG] = [OK,PF] = [PE,OL] = [PH,OM] = [DA,DC] = [ON,OM] = [PH,PG] and eqang [0][PL,PK] = [PH,PG].
eqang [0][KP,KA] = [MC,MP]
       because $[MD,DP]=[PA,AK]$ and $[DP,PM]=[KP,PA]$.
eqang [0][PL,PM] = [CD,CB]
       because $[CD,DB]=[LM,MP]$ and $[DB,BC]=[PL,LM]$.
eqang [0][PF,PG] = [OL,OM] = [PF,OM] = [PE,ON] = [OK,PH] = [OL,PG] = [OK,ON] = [AB,AD] = [PE,PH] = [CB,CD] = [PM,PL]
       because eqang [0][PE,PH] = [AB,AD] = [OK,ON] = [OL,PG] = [OK,PH] = [PE,ON] = [PF,OM] = [OL,OM] = [CB,CD] = [PF,PG] and eqang [0][PL,PM] = [CD,CB].
eqang [0][OD,OA] = [MC,MP]
       because $[MD,DP]=[DA,AO]$ and $[DP,PM]=[OD,DA]$.
eqang [0][KP,KA] = [MC,MP] = [OD,OA]
       because eqang [0][KP,KA] = [MC,MP] and eqang [0][OD,OA] = [MC,MP].
eqang [0][PN,PM] = [DC,DA]
       because $[HG,GD]=[MN,NP]$ and $[GH,HD]=[NM,MP]$.
eqang [0][PL,PK] = [PH,PG] = [PE,PF] = [BA,BC] = [OK,OL] = [ON,PG] = [OK,PF] = [PE,OL] = [PH,OM] = [ON,OM] = [DA,DC] = [PM,PN]
       because eqang [0][ON,OM] = [DA,DC] = [PH,OM] = [PE,OL] = [OK,PF] = [ON,PG] = [OK,OL] = [BA,BC] = [PE,PF] = [PH,PG] = [PL,PK] and eqang [0][PN,PM] = [DC,DA].
eqang [0][LP,LB] = [NA,NP]
       because $[NA,AP]=[LP,PB]$, $[AP,PN]=[PB,BL]$, $[PH,BP]=[PL,ML]$.
eqang [0][OA,OB] = [NA,NP]
       because $[NA,AP]=[OA,AB]$, $[AP,PN]=[AB,BO]$, $[PH,BP]=[PL,ML]$.
eqang [0][LP,LB] = [NA,NP] = [OA,OB]
       because eqang [0][LP,LB] = [NA,NP] and eqang [0][OA,OB] = [NA,NP].
eqang [0][PK,PN] = [AD,AB]
       because $[EH,HA]=[NK,KP]$ and $[HE,EA]=[KN,NP]$.
eqang [0][PM,PL] = [CB,CD] = [PE,PH] = [OK,ON] = [OL,PG] = [OK,PH] = [PE,ON] = [PF,OM] = [OL,OM] = [PF,PG] = [AB,AD] = [PN,PK]
       because eqang [0][PF,PG] = [OL,OM] = [PF,OM] = [PE,ON] = [OK,PH] = [OL,PG] = [OK,ON] = [AB,AD] = [PE,PH] = [CB,CD] = [PM,PL] and eqang [0][PK,PN] = [AD,AB].
eqang [0][OB,OC] = [MP,MC]
       because $[MP,PC]=[OB,BC]$ and $[PC,CM]=[BC,CO]$.
eqang [0][OD,OA] = [KP,KA] = [MC,MP] = [OC,OB]
       because eqang [0][KP,KA] = [MC,MP] = [OD,OA] and eqang [0][OB,OC] = [MP,MC].
eqang [0][OD,OC] = [NA,NP]
       because $[ND,DP]=[OD,DC]$ and $[DP,PN]=[DC,CO]$.
eqang [0][OA,OB] = [LP,LB] = [NA,NP] = [OD,OC]
       because eqang [0][LP,LB] = [NA,NP] = [OA,OB] and eqang [0][OD,OC] = [NA,NP].
eqang [0][OD,LK] = [GH,ML]
       because perp[$ML,LK$] and perp[$GH,OD$].
eqang [0][OD,ML] = [GH,LK]
       because perp[$ML,LK$] and perp[$GH,OD$].
eqang [0][OD,AC] = [GH,ML]
       because perp[$ML,AC$] and perp[$GH,OD$].
eqang [0][OD,LK] = [GH,ML] = [OD,AC]
       because eqang [0][OD,LK] = [GH,ML] and eqang [0][OD,AC] = [GH,ML].
eqang [0][OD,ML] = [GH,AC]
       because perp[$ML,AC$] and perp[$GH,OD$].
eqang [0][GH,LK] = [GH,AC] = [OD,ML]
       because eqang [0][OD,ML] = [GH,LK] and eqang [0][OD,ML] = [GH,AC].
eqang [0][OD,NM] = [GH,ML]
       because perp[$ML,NM$] and perp[$GH,OD$].
eqang [0][OD,AC] = [OD,LK] = [GH,ML] = [OD,NM]
       because eqang [0][OD,LK] = [GH,ML] = [OD,AC] and eqang [0][OD,NM] = [GH,ML].
eqang [0][OD,ML] = [GH,NM]
       because perp[$ML,NM$] and perp[$GH,OD$].
eqang [0][GH,AC] = [GH,LK] = [GH,NM] = [OD,ML]
       because eqang [0][GH,LK] = [GH,AC] = [OD,ML] and eqang [0][OD,ML] = [GH,NM].
eqang [0][OD,LK] = [GH,BP]
       because perp[$BP,LK$] and perp[$GH,OD$].
eqang [0][OD,NM] = [GH,ML] = [OD,AC] = [GH,BP] = [OD,LK]
       because eqang [0][OD,AC] = [OD,LK] = [GH,ML] = [OD,NM] and eqang [0][OD,LK] = [GH,BP].
eqang [0][DO,DB] = [GH,LK]
       because perp[$BP,LK$] and perp[$GH,OD$].
eqang [0][BO,BP] = [LK,GH] = [DB,DO]
       because eqang [0][DB,DO] = [BO,BP] and eqang [0][DO,DB] = [GH,LK].
eqang [0][OD,ML] = [GH,NM] = [GH,AC] = [DO,DB] = [GH,LK] = [BP,BO]
       because eqang [0][GH,AC] = [GH,LK] = [GH,NM] = [OD,ML] and eqang [0][BO,BP] = [LK,GH] = [DB,DO].
eqang [0][OD,LK] = [GH,NK]
       because perp[$NK,LK$] and perp[$GH,OD$].
eqang [1][GH,BP] = [OD,AC] = [GH,ML] = [OD,NM] = [GH,NK] = [OD,LK]
       because eqang [0][OD,NM] = [GH,ML] = [OD,AC] = [GH,BP] = [OD,LK] and eqang [0][OD,LK] = [GH,NK].
eqang [0][OD,NK] = [GH,LK]
       because perp[$NK,LK$] and perp[$GH,OD$].
eqang [0][BP,BO] = [DO,DB] = [GH,AC] = [GH,NM] = [OD,ML] = [GH,LK] = [OD,NK]
       because eqang [0][OD,ML] = [GH,NM] = [GH,AC] = [DO,DB] = [GH,LK] = [BP,BO] and eqang [0][OD,NK] = [GH,LK].
eqang [0][OD,PH] = [HG,HA]
       because perp[$AD,PH$] and perp[$GH,OD$].
eqang [0][FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [CB,CO] = [BO,BC] = [KO,KL] = [KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PE,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [HE,HA] = [PB,PG] = [HA,HG] = [PH,OD]
       because eqang [0][HA,HG] = [PB,PG] = [HE,HA] = [PE,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PE,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PG] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] and eqang [0][OD,PH] = [HG,HA].
eqang [0][OD,PL] = [HG,HA]
       because perp[$AD,PL$] and perp[$GH,OD$].
eqang [0][PH,OD] = [PB,PK] = [HE,HA] = [PE,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PE,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PK] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD]
       because eqang [0][FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [CB,CO] = [BO,BC] = [KO,KL] = [KN,KP] = [PB,PK] = [ML,PG] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PE,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [HE,HA] = [PB,PG] = [HA,HG] = [PH,OD] and eqang [0][OD,PL] = [HG,HA].
eqang [0][DO,DA] = [HG,HP]
       because perp[$AD,PL$] and perp[$GH,OD$].
eqang [0][FG,FB] = [PG,PA] = [FB,FE] = [PB,PE] = [AD,AO] = [KN,KO] = [PB,PM] = [ML,MP] = [KP,KL] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [DO,DA]
       because eqang [0][HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PG,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [DO,DA] = [PB,PE] = [FB,FE] = [PG,PA] = [FG,FB] and eqang [0][DO,DA] = [HG,HP].
eqang [0][DO,DA] = [GH,ON]
       because perp[$AD,ON$] and perp[$GH,OD$].
eqang [0][HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [PB,PE] = [FB,FE] = [PK,PA] = [FG,FB] = [GH,ON] = [DO,DA]
       because eqang [0][FG,FB] = [PG,PA] = [FB,FE] = [PB,PE] = [AD,AO] = [KN,KO] = [PB,PM] = [ML,MP] = [KP,KL] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PG,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [DO,DA] and eqang [0][DO,DA] = [GH,ON].
eqang [0][OD,PN] = [GH,BC]
       because perp[$BC,PN$] and perp[$GH,OD$].
eqang [0][OD,BC] = [GH,PN]
       because perp[$BC,PN$] and perp[$GH,OD$].
eqang [0][OD,PF] = [GH,BC]
       because perp[$BC,PF$] and perp[$GH,OD$].
eqang [0][OD,PN] = [GH,BC] = [OD,PF]
       because eqang [0][OD,PN] = [GH,BC] and eqang [0][OD,PF] = [GH,BC].
eqang [0][OD,BC] = [GH,PF]
       because perp[$BC,PF$] and perp[$GH,OD$].
eqang [0][GH,PN] = [GH,PF] = [OD,BC]
       because eqang [0][OD,BC] = [GH,PN] and eqang [0][OD,BC] = [GH,PF].
eqang [0][OD,OL] = [GH,BC]
       because perp[$BC,OL$] and perp[$GH,OD$].
eqang [1][OD,PN] = [OD,PN] = [GH,BC] = [OD,OL]
       because eqang [0][OD,PN] = [GH,BC] = [OD,PF] and eqang [0][OD,OL] = [GH,BC].
eqang [0][OD,BC] = [GH,OL]
       because perp[$BC,OL$] and perp[$GH,OD$].
eqang [1][GH,PN] = [GH,PN] = [GH,OL] = [OD,BC]
       because eqang [0][GH,PN] = [GH,PF] = [OD,BC] and eqang [0][OD,BC] = [GH,OL].
eqang [0][OD,PK] = [GH,GC]
       because perp[$CD,PK$] and perp[$GH,OD$].
eqang [0][GC,GF] = [PA,PF] = [EP,EF] = [BP,BC] = [BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [GH,GC] = [OD,PK]
       because eqang [0][GH,GC] = [PH,PB] = [EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] = [BP,BC] = [EP,EF] = [PA,PF] = [GC,GF] and eqang [0][OD,PK] = [GH,GC].
eqang [0][DO,DC] = [GH,GP]
       because perp[$CD,PK$] and perp[$GH,OD$].
eqang [0][GP,GF] = [CA,CB] = [CD,CO] = [NO,NM] = [EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] = [GH,GP] = [DO,DC]
       because eqang [0][GH,GP] = [DA,DB] = [EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] = [NO,NM] = [DO,DC] = [CD,CO] = [CA,CB] = [GP,GF] and eqang [0][DO,DC] = [GH,GP].
eqang [0][DO,DC] = [GH,OM]
       because perp[$CD,OM$] and perp[$GH,OD$].
eqang [0][GH,GP] = [GH,GP] = [DA,DB] = [EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] = [NO,NM] = [CD,CO] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC]
       because eqang [0][GP,GF] = [CA,CB] = [CD,CO] = [NO,NM] = [EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] = [GH,GP] = [DO,DC] and eqang [0][DO,DC] = [GH,OM].
eqang [0][OD,PG] = [GH,GC]
       because perp[$CD,PG$] and perp[$GH,OD$].
eqang [0][OD,PK] = [PH,PB] = [EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] = [BP,BC] = [EP,EF] = [PA,PF] = [GC,GF] = [GH,GC] = [OD,PK]
       because eqang [0][GC,GF] = [PA,PF] = [EP,EF] = [BP,BC] = [BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [GH,GC] = [OD,PK] and eqang [0][OD,PG] = [GH,GC].
eqang [0][OD,OA] = [HG,HE]
       because perp[$EH,AO$] and perp[$GH,OD$].
eqang [0][OC,OB] = [MC,MP] = [KP,KA] = [HG,HE] = [OD,OA]
       because eqang [0][OD,OA] = [KP,KA] = [MC,MP] = [OC,OB] and eqang [0][OD,OA] = [HG,HE].
eqang [1][OD,EH] = [GH,AO]
       because perp[$EH,AO$] and perp[$GH,OD$].
eqang [0][OD,PM] = [GH,AB]
       because perp[$AB,PM$] and perp[$GH,OD$].
eqang [0][OD,AB] = [GH,PM]
       because perp[$AB,PM$] and perp[$GH,OD$].
eqang [0][OD,OK] = [GH,AB]
       because perp[$AB,OK$] and perp[$GH,OD$].
eqang [0][OD,PM] = [GH,AB] = [OD,OK]
       because eqang [0][OD,PM] = [GH,AB] and eqang [0][OD,OK] = [GH,AB].
eqang [0][OD,AB] = [GH,OK]
       because perp[$AB,OK$] and perp[$GH,OD$].
eqang [0][GH,PM] = [GH,OK] = [OD,AB]
       because eqang [0][OD,AB] = [GH,PM] and eqang [0][OD,AB] = [GH,OK].
eqang [0][OD,PE] = [GH,AB]
       because perp[$AB,PE$] and perp[$GH,OD$].
eqang [1][OD,OK] = [OD,PM] = [GH,AB] = [OD,PM]
       because eqang [0][OD,PM] = [GH,AB] = [OD,OK] and eqang [0][OD,PE] = [GH,AB].
eqang [0][OD,AB] = [GH,PE]
       because perp[$AB,PE$] and perp[$GH,OD$].
eqang [1][GH,OK] = [GH,PM] = [GH,PM] = [OD,AB]
       because eqang [0][GH,PM] = [GH,OK] = [OD,AB] and eqang [0][OD,AB] = [GH,PE].
eqang [1][OD,FE] = [GH,BO]
       because perp[$BO,FE$] and perp[$GH,OD$].
eqang [1][OD,OB] = [GH,FE]
       because perp[$BO,FE$] and perp[$GH,OD$].
eqang [1][OD,GF] = [GH,CO]
       because perp[$CO,GF$] and perp[$GH,OD$].
eqang [0][OD,OC] = [GH,GF]
       because perp[$CO,GF$] and perp[$GH,OD$].
eqang [0][NA,NP] = [LP,LB] = [OA,OB] = [GH,GF] = [OD,OC]
       because eqang [0][OA,OB] = [LP,LB] = [NA,NP] = [OD,OC] and eqang [0][OD,OC] = [GH,GF].
eqang [0][OP,OM] = [PO,PK]
       because para[$PK,OM$].
eqang [1][PO,PK] = [PO,PK] = [OP,OM]
       because eqang [0][PO,PG] = [OP,OM] and eqang [0][OP,OM] = [PO,PK].
eqang [0][OK,OM] = [PM,PK]
       because para[$MP,KO$].
eqang [0][MO,MP] = [CD,AB] = [PG,PE] = [KP,KO] = [PK,PM] = [OM,OK]
       because eqang [0][KP,KO] = [PG,PE] = [OM,OK] = [CD,AB] = [MO,MP] and eqang [0][OK,OM] = [PM,PK].
eqang [0][OL,OM] = [PF,PK]
       because para[$FP,LO$].
eqang [0][PN,PK] = [AB,AD] = [PF,PK] = [PF,OM] = [PE,ON] = [OK,PH] = [OL,PK] = [OK,ON] = [PE,PH] = [CB,CD] = [PM,PL] = [PF,PK] = [OL,OM]
       because eqang [0][PM,PL] = [CB,CD] = [PE,PH] = [OK,ON] = [OL,PG] = [OK,PH] = [PE,ON] = [PF,OM] = [OL,OM] = [PF,PG] = [AB,AD] = [PN,PK] and eqang [0][OL,OM] = [PF,PK].
eqang [0][OK,OM] = [PE,PK]
       because para[$EP,KO$].
eqang [0][KP,KO] = [PK,PE] = [OM,OK]
       because eqang [0][PK,PE] = [KP,KO] and eqang [0][OK,OM] = [PE,PK].
eqang [0][PK,PM] = [KP,KO] = [PG,PE] = [CD,AB] = [MO,MP] = [OM,OK] = [PK,PE] = [KP,KO]
       because eqang [0][MO,MP] = [CD,AB] = [PG,PE] = [KP,KO] = [PK,PM] = [OM,OK] and eqang [0][KP,KO] = [PK,PE] = [OM,OK].
eqang [0][ON,OM] = [PH,PK]
       because para[$HP,NO$].
eqang [0][PM,PN] = [DA,DC] = [PH,OM] = [PE,OL] = [OK,PF] = [ON,PK] = [OK,OL] = [BA,BC] = [PE,PF] = [PH,PK] = [PL,PK] = [PH,PK] = [ON,OM]
       because eqang [0][PL,PK] = [PH,PG] = [PE,PF] = [BA,BC] = [OK,OL] = [ON,PG] = [OK,PF] = [PE,OL] = [PH,OM] = [ON,OM] = [DA,DC] = [PM,PN] and eqang [0][ON,OM] = [PH,PK].
eqang [0][MP,MO] = [PM,PK]
       because para[$PK,MO$].
eqang [0][PM,PG] = [PM,PK] = [MP,MO]
       because eqang [0][PM,PG] = [MP,MO] and eqang [0][MP,MO] = [PM,PK].
eqang [1][KP,KO] = [PK,PM] = [OM,OK] = [MO,MP] = [CD,AB] = [PK,PM] = [KP,KO] = [MO,MP] = [PK,PM] = [PK,PM]
       because eqang [0][PK,PM] = [KP,KO] = [PG,PE] = [CD,AB] = [MO,MP] = [OM,OK] = [PK,PE] = [KP,KO] and eqang [0][PM,PG] = [PM,PK] = [MP,MO].
eqang [0][MK,MO] = [KM,KP]
       because para[$KP,MO$].
eqang [0][PK,AD] = [CD,PL]
       because perp[$AD,PL$] and perp[$CD,PK$].
eqang [1][PL,CD] = [AD,OM] = [ON,CD] = [PL,CD] = [AD,PK]
       because eqang [0][ON,CD] = [AD,PK] = [AD,OM] = [PH,CD] and eqang [0][PK,AD] = [CD,PL].
eqang [0][PK,BC] = [CD,PN]
       because perp[$BC,PN$] and perp[$CD,PK$].
eqang [1][PN,CD] = [BC,OM] = [OL,CD] = [PN,CD] = [BC,PK]
       because eqang [0][OL,CD] = [BC,PK] = [BC,OM] = [PF,CD] and eqang [0][PK,BC] = [CD,PN].
eqang [0][PK,AO] = [CD,EH]
       because perp[$EH,AO$] and perp[$CD,PK$].
eqang [0][PK,EH] = [CD,AO]
       because perp[$EH,AO$] and perp[$CD,PK$].
eqang [0][PK,FE] = [CD,BO]
       because perp[$BO,FE$] and perp[$CD,PK$].
eqang [0][PK,BO] = [CD,FE]
       because perp[$BO,FE$] and perp[$CD,PK$].
eqang [0][PK,CO] = [GC,GF]
       because perp[$CO,GF$] and perp[$CD,PK$].
eqang [0][GH,GC] = [PA,PF] = [EP,EF] = [BP,BC] = [BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [OD,PK] = [GC,GF] = [PK,CO]
       because eqang [0][OD,PK] = [PH,PB] = [EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [AO,AB] = [BA,BO] = [BP,BC] = [EP,EF] = [PA,PF] = [GC,GF] = [GH,GC] = [OD,PK] and eqang [0][PK,CO] = [GC,GF].
eqang [0][OM,PL] = [DC,DA]
       because perp[$AD,PL$] and perp[$CD,OM$].
eqang [0][ON,OM] = [PL,PK] = [PH,PK] = [PE,PF] = [BA,BC] = [OK,OL] = [ON,PK] = [OK,PF] = [PE,OL] = [PH,OM] = [PM,PN] = [DA,DC] = [PL,OM]
       because eqang [0][PM,PN] = [DA,DC] = [PH,OM] = [PE,OL] = [OK,PF] = [ON,PK] = [OK,OL] = [BA,BC] = [PE,PF] = [PH,PK] = [PL,PK] = [PH,PK] = [ON,OM] and eqang [0][OM,PL] = [DC,DA].
eqang [0][OM,PN] = [CD,CB]
       because perp[$BC,PN$] and perp[$CD,OM$].
eqang [0][OL,OM] = [PM,PL] = [PE,PH] = [OK,ON] = [OL,PK] = [OK,PH] = [PE,ON] = [PF,OM] = [PF,PK] = [AB,AD] = [PN,PK] = [CB,CD] = [PN,OM]
       because eqang [0][PN,PK] = [AB,AD] = [PF,PK] = [PF,OM] = [PE,ON] = [OK,PH] = [OL,PK] = [OK,ON] = [PE,PH] = [CB,CD] = [PM,PL] = [PF,PK] = [OL,OM] and eqang [0][OM,PN] = [CD,CB].
eqang [0][OM,OA] = [CD,EH]
       because perp[$EH,AO$] and perp[$CD,OM$].
eqang [1][PK,AO] = [CD,EH] = [OM,OA]
       because eqang [0][PK,AO] = [CD,EH] and eqang [0][OM,OA] = [CD,EH].
eqang [0][OM,EH] = [CD,AO]
       because perp[$EH,AO$] and perp[$CD,OM$].
eqang [1][PK,EH] = [CD,AO] = [OM,EH]
       because eqang [0][PK,EH] = [CD,AO] and eqang [0][OM,EH] = [CD,AO].
eqang [0][OM,AB] = [MC,MP]
       because perp[$AB,PM$] and perp[$CD,OM$].
eqang [0][MP,MC] = [KA,KP] = [OK,CD] = [MP,MC] = [AB,OM]
       because eqang [0][OK,CD] = [KA,KP] = [AB,OM] = [MP,MC] and eqang [0][OM,AB] = [MC,MP].
eqang [0][OD,OA] = [HG,HE] = [OC,OB] = [OM,AB] = [MC,MP] = [CD,OK] = [KP,KA] = [MC,MP]
       because eqang [0][OC,OB] = [MC,MP] = [KP,KA] = [HG,HE] = [OD,OA] and eqang [0][MP,MC] = [KA,KP] = [OK,CD] = [MP,MC] = [AB,OM].
eqang [0][OM,FE] = [CD,BO]
       because perp[$BO,FE$] and perp[$CD,OM$].
eqang [1][PK,FE] = [CD,BO] = [OM,FE]
       because eqang [0][PK,FE] = [CD,BO] and eqang [0][OM,FE] = [CD,BO].
eqang [0][OM,OB] = [CD,FE]
       because perp[$BO,FE$] and perp[$CD,OM$].
eqang [1][PK,BO] = [CD,FE] = [OM,OB]
       because eqang [0][PK,BO] = [CD,FE] and eqang [0][OM,OB] = [CD,FE].
eqang [0][OM,GF] = [CD,CO]
       because perp[$CO,GF$] and perp[$CD,OM$].
eqang [0][DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF]
       because eqang [0][GH,GP] = [GH,GP] = [DA,DB] = [EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [EH,EA] = [NO,NM] = [CD,CO] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] and eqang [0][OM,GF] = [CD,CO].
eqang [1][FB,FD] = [GB,GC]
       because circle[$BDGF$].
eqang [1][GB,GF] = [DB,DF]
       because circle[$BFDG$].
eqang [1][FD,FG] = [BP,BG]
       because circle[$DGBF$].
eqang [1][DC,DF] = [BG,BC]
       because circle[$GFBD$].
eqang [0][AO,LK] = [EH,ML]
       because perp[$ML,LK$] and perp[$EH,AO$].
eqang [0][AO,ML] = [EH,LK]
       because perp[$ML,LK$] and perp[$EH,AO$].
eqang [0][AO,AC] = [EH,ML]
       because perp[$ML,AC$] and perp[$EH,AO$].
eqang [0][CA,CO] = [EH,ML] = [AO,AC]
       because eqang [0][CA,CO] = [AO,AC] and eqang [0][AO,AC] = [EH,ML].
eqang [0][AO,LK] = [AO,AC] = [EH,ML] = [CA,CO]
       because eqang [0][AO,LK] = [EH,ML] and eqang [0][CA,CO] = [EH,ML] = [AO,AC].
eqang [0][AO,ML] = [EH,AC]
       because perp[$ML,AC$] and perp[$EH,AO$].
eqang [0][EH,LK] = [EH,AC] = [AO,ML]
       because eqang [0][AO,ML] = [EH,LK] and eqang [0][AO,ML] = [EH,AC].
eqang [0][AO,NM] = [EH,ML]
       because perp[$ML,NM$] and perp[$EH,AO$].
eqang [0][CA,CO] = [AO,AC] = [AO,LK] = [EH,ML] = [AO,NM]
       because eqang [0][AO,LK] = [AO,AC] = [EH,ML] = [CA,CO] and eqang [0][AO,NM] = [EH,ML].
eqang [0][AO,ML] = [EH,NM]
       because perp[$ML,NM$] and perp[$EH,AO$].
eqang [0][EH,AC] = [EH,LK] = [EH,NM] = [AO,ML]
       because eqang [0][EH,LK] = [EH,AC] = [AO,ML] and eqang [0][AO,ML] = [EH,NM].
eqang [0][AO,LK] = [EH,BP]
       because perp[$BP,LK$] and perp[$EH,AO$].
eqang [0][AO,NM] = [EH,ML] = [AO,AC] = [CA,CO] = [EH,BP] = [AO,LK]
       because eqang [0][CA,CO] = [AO,AC] = [AO,LK] = [EH,ML] = [AO,NM] and eqang [0][AO,LK] = [EH,BP].
eqang [0][AO,BP] = [EH,LK]
       because perp[$BP,LK$] and perp[$EH,AO$].
eqang [0][AO,ML] = [EH,NM] = [EH,AC] = [EH,LK] = [AO,BP]
       because eqang [0][EH,AC] = [EH,LK] = [EH,NM] = [AO,ML] and eqang [0][AO,BP] = [EH,LK].
eqang [0][AO,LK] = [EH,NK]
       because perp[$NK,LK$] and perp[$EH,AO$].
eqang [0][EH,BP] = [CA,CO] = [AO,AC] = [EH,ML] = [AO,NM] = [EH,NK] = [AO,LK]
       because eqang [0][AO,NM] = [EH,ML] = [AO,AC] = [CA,CO] = [EH,BP] = [AO,LK] and eqang [0][AO,LK] = [EH,NK].
eqang [0][AO,NK] = [EH,LK]
       because perp[$NK,LK$] and perp[$EH,AO$].
eqang [1][AO,BP] = [EH,AC] = [EH,NM] = [AO,ML] = [EH,LK] = [AO,NK]
       because eqang [0][AO,ML] = [EH,NM] = [EH,AC] = [EH,LK] = [AO,BP] and eqang [0][AO,NK] = [EH,LK].
eqang [0][AO,PH] = [HE,HA]
       because perp[$AD,PH$] and perp[$EH,AO$].
eqang [0][PL,OD] = [HA,HG] = [FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [CB,CO] = [BO,BC] = [KO,KL] = [ML,PK] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PE,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [PB,PK] = [PH,OD] = [HE,HA] = [AO,PH]
       because eqang [0][PH,OD] = [PB,PK] = [HE,HA] = [PE,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PE,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PK] = [PB,PK] = [KN,KP] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] and eqang [0][AO,PH] = [HE,HA].
eqang [0][AO,PL] = [HE,HA]
       because perp[$AD,PL$] and perp[$EH,AO$].
eqang [0][AO,PH] = [PH,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PK] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] = [HE,HA] = [AO,PL]
       because eqang [0][PL,OD] = [HA,HG] = [FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [CB,CO] = [BO,BC] = [KO,KL] = [ML,PK] = [BP,OM] = [MP,MN] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PE,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [MP,MN] = [PE,PA] = [PB,PK] = [PH,OD] = [HE,HA] = [AO,PH] and eqang [0][AO,PL] = [HE,HA].
eqang [0][AO,AD] = [HE,HP]
       because perp[$AD,PL$] and perp[$EH,AO$].
eqang [0][DO,DA] = [GH,ON] = [FG,FB] = [FB,FE] = [PB,PE] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [HP,HE] = [AD,AO]
       because eqang [0][HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PE] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [KP,KL] = [ML,MP] = [PB,PM] = [KN,KO] = [AD,AO] = [PB,PE] = [FB,FE] = [PK,PA] = [FG,FB] = [GH,ON] = [DO,DA] and eqang [0][AO,AD] = [HE,HP].
eqang [0][AO,AD] = [EH,ON]
       because perp[$AD,ON$] and perp[$EH,AO$].
eqang [0][HP,HE] = [HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [ML,MP] = [PB,PM] = [KN,KO] = [PB,PM] = [FB,FE] = [FG,FB] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO]
       because eqang [0][DO,DA] = [GH,ON] = [FG,FB] = [FB,FE] = [PB,PE] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PE] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [HP,HE] = [AD,AO] and eqang [0][AO,AD] = [EH,ON].
eqang [0][AO,PN] = [EH,BC]
       because perp[$BC,PN$] and perp[$EH,AO$].
eqang [0][AO,BC] = [EH,PN]
       because perp[$BC,PN$] and perp[$EH,AO$].
eqang [0][AO,PF] = [EH,BC]
       because perp[$BC,PF$] and perp[$EH,AO$].
eqang [0][AO,PN] = [EH,BC] = [AO,PF]
       because eqang [0][AO,PN] = [EH,BC] and eqang [0][AO,PF] = [EH,BC].
eqang [0][AO,BC] = [EH,PF]
       because perp[$BC,PF$] and perp[$EH,AO$].
eqang [0][EH,PN] = [EH,PF] = [AO,BC]
       because eqang [0][AO,BC] = [EH,PN] and eqang [0][AO,BC] = [EH,PF].
eqang [0][OA,OL] = [EH,BC]
       because perp[$BC,OL$] and perp[$EH,AO$].
eqang [1][AO,PN] = [AO,PN] = [EH,BC] = [OA,OL]
       because eqang [0][AO,PN] = [EH,BC] = [AO,PF] and eqang [0][OA,OL] = [EH,BC].
eqang [0][AO,BC] = [EH,OL]
       because perp[$BC,OL$] and perp[$EH,AO$].
eqang [1][EH,PN] = [EH,PN] = [EH,OL] = [AO,BC]
       because eqang [0][EH,PN] = [EH,PF] = [AO,BC] and eqang [0][AO,BC] = [EH,OL].
eqang [0][AO,PM] = [EH,EA]
       because perp[$AB,PM$] and perp[$EH,AO$].
eqang [0][OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [EH,EA] = [AO,PM]
       because eqang [0][DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [EH,EA] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] and eqang [0][AO,PM] = [EH,EA].
eqang [0][AO,AB] = [EH,EP]
       because perp[$AB,PM$] and perp[$EH,AO$].
eqang [0][PK,CO] = [GC,GF] = [OD,PK] = [PH,PB] = [EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [BA,BO] = [BP,BC] = [EP,EF] = [PA,PF] = [GH,GC] = [EH,EP] = [AO,AB]
       because eqang [0][GH,GC] = [PA,PF] = [EP,EF] = [BP,BC] = [BA,BO] = [AO,AB] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [OD,PK] = [GC,GF] = [PK,CO] and eqang [0][AO,AB] = [EH,EP].
eqang [0][AO,AB] = [EH,OK]
       because perp[$AB,OK$] and perp[$EH,AO$].
eqang [0][EH,EP] = [GH,GC] = [PA,PF] = [EP,EF] = [BP,BC] = [BA,BO] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB]
       because eqang [0][PK,CO] = [GC,GF] = [OD,PK] = [PH,PB] = [EH,EP] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [BA,BO] = [BP,BC] = [EP,EF] = [PA,PF] = [GH,GC] = [EH,EP] = [AO,AB] and eqang [0][AO,AB] = [EH,OK].
eqang [0][AO,PE] = [EH,EA]
       because perp[$AB,PE$] and perp[$EH,AO$].
eqang [0][AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AO,PM]
       because eqang [0][OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [EA,EF] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [EH,EA] = [AO,PM] and eqang [0][AO,PE] = [EH,EA].
eqang [1][AO,FE] = [EH,BO]
       because perp[$BO,FE$] and perp[$EH,AO$].
eqang [0][OA,OB] = [EH,EF]
       because perp[$BO,FE$] and perp[$EH,AO$].
eqang [0][OD,OC] = [GH,GF] = [LP,LB] = [NA,NP] = [EH,EF] = [OA,OB]
       because eqang [0][NA,NP] = [LP,LB] = [OA,OB] = [GH,GF] = [OD,OC] and eqang [0][OA,OB] = [EH,EF].
eqang [1][AO,GF] = [EH,CO]
       because perp[$CO,GF$] and perp[$EH,AO$].
eqang [1][OA,OC] = [EH,GF]
       because perp[$CO,GF$] and perp[$EH,AO$].
eqang [0][OP,OK] = [PO,PM]
       because para[$PM,OK$].
eqang [1][PO,PM] = [PO,PM] = [OP,OK]
       because eqang [0][PO,PE] = [OP,OK] and eqang [0][OP,OK] = [PO,PM].
eqang [0][OL,OK] = [PF,PM]
       because para[$FP,LO$].
eqang [0][PL,OM] = [DA,DC] = [PM,PN] = [PH,OM] = [PM,OL] = [OK,PF] = [ON,PK] = [BA,BC] = [PM,PF] = [PH,PK] = [PL,PK] = [ON,OM] = [PM,PF] = [OK,OL]
       because eqang [0][ON,OM] = [PL,PK] = [PH,PK] = [PE,PF] = [BA,BC] = [OK,OL] = [ON,PK] = [OK,PF] = [PE,OL] = [PH,OM] = [PM,PN] = [DA,DC] = [PL,OM] and eqang [0][OL,OK] = [PF,PM].
eqang [0][ON,OK] = [PH,PM]
       because para[$HP,NO$].
eqang [0][PN,OM] = [CB,CD] = [PN,PK] = [AB,AD] = [PF,PK] = [PF,OM] = [PM,ON] = [OK,PH] = [OL,PK] = [PM,PH] = [PM,PL] = [OL,OM] = [PM,PH] = [OK,ON]
       because eqang [0][OL,OM] = [PM,PL] = [PE,PH] = [OK,ON] = [OL,PK] = [OK,PH] = [PE,ON] = [PF,OM] = [PF,PK] = [AB,AD] = [PN,PK] = [CB,CD] = [PN,OM] and eqang [0][ON,OK] = [PH,PM].
eqang [0][KM,KO] = [MK,MP]
       because para[$MP,KO$].
eqang [0][PM,AD] = [AB,PL]
       because perp[$AD,PL$] and perp[$AB,PM$].
eqang [1][AB,ON] = [AB,PL] = [OK,AD] = [AB,PL] = [PM,AD]
       because eqang [0][OK,AD] = [AB,PH] = [AB,ON] = [PM,AD] and eqang [0][PM,AD] = [AB,PL].
eqang [0][PM,BC] = [AB,PN]
       because perp[$BC,PN$] and perp[$AB,PM$].
eqang [1][AB,OL] = [AB,PN] = [OK,BC] = [AB,PN] = [PM,BC]
       because eqang [0][OK,BC] = [AB,PF] = [AB,OL] = [PM,BC] and eqang [0][PM,BC] = [AB,PN].
eqang [0][PM,BO] = [EA,EF]
       because perp[$BO,FE$] and perp[$AB,PM$].
eqang [0][EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO]
       because eqang [0][AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PH,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PH,NM] = [AD,ML] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [EA,EF] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AO,PM] and eqang [0][PM,BO] = [EA,EF].
eqang [0][PM,GF] = [AB,CO]
       because perp[$CO,GF$] and perp[$AB,PM$].
eqang [0][PM,CO] = [AB,GF]
       because perp[$CO,GF$] and perp[$AB,PM$].
eqang [0][OK,PL] = [AB,AD]
       because perp[$AD,PL$] and perp[$AB,OK$].
eqang [1][OK,ON] = [OL,OM] = [PM,PL] = [PM,PL] = [OL,PK] = [OK,PL] = [PM,ON] = [PN,OM] = [PN,PK] = [PN,PK] = [CB,CD] = [PN,OM] = [AB,AD] = [OK,PL]
       because eqang [0][PN,OM] = [CB,CD] = [PN,PK] = [AB,AD] = [PF,PK] = [PF,OM] = [PM,ON] = [OK,PH] = [OL,PK] = [PM,PH] = [PM,PL] = [OL,OM] = [PM,PH] = [OK,ON] and eqang [0][OK,PL] = [AB,AD].
eqang [0][OK,PN] = [BA,BC]
       because perp[$BC,PN$] and perp[$AB,OK$].
eqang [1][OK,OL] = [ON,OM] = [PL,PK] = [PL,PK] = [PM,PN] = [ON,PK] = [OK,PN] = [PM,OL] = [PL,OM] = [PM,PN] = [DA,DC] = [PL,OM] = [BA,BC] = [OK,PN]
       because eqang [0][PL,OM] = [DA,DC] = [PM,PN] = [PH,OM] = [PM,OL] = [OK,PF] = [ON,PK] = [BA,BC] = [PM,PF] = [PH,PK] = [PL,PK] = [ON,OM] = [PM,PF] = [OK,OL] and eqang [0][OK,PN] = [BA,BC].
eqang [0][OK,FE] = [BA,BO]
       because perp[$BO,FE$] and perp[$AB,OK$].
eqang [0][AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [PH,PB] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [PA,PF] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE]
       because eqang [0][EH,EP] = [GH,GC] = [PA,PF] = [EP,EF] = [BP,BC] = [BA,BO] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PH,NK] = [AD,LK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [EH,EP] = [PH,PB] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] and eqang [0][OK,FE] = [BA,BO].
eqang [0][OK,GF] = [AB,CO]
       because perp[$CO,GF$] and perp[$AB,OK$].
eqang [1][PM,GF] = [AB,CO] = [OK,GF]
       because eqang [0][PM,GF] = [AB,CO] and eqang [0][OK,GF] = [AB,CO].
eqang [0][OK,OC] = [AB,GF]
       because perp[$CO,GF$] and perp[$AB,OK$].
eqang [1][PM,CO] = [AB,GF] = [OK,OC]
       because eqang [0][PM,CO] = [AB,GF] and eqang [0][OK,OC] = [AB,GF].
eqang [1][EA,EC] = [FA,FB]
       because circle[$ACFE$].
eqang [1][FA,FE] = [CA,CE]
       because circle[$AECF$].
eqang [1][EC,EF] = [AC,AF]
       because circle[$CFAE$].
eqang [1][CB,CE] = [AF,AB]
       because circle[$FEAC$].
eqang [0][FE,LK] = [BO,ML]
       because perp[$ML,LK$] and perp[$BO,FE$].
eqang [0][FE,ML] = [BO,LK]
       because perp[$ML,LK$] and perp[$BO,FE$].
eqang [0][FE,AC] = [BO,ML]
       because perp[$ML,AC$] and perp[$BO,FE$].
eqang [0][FE,LK] = [BO,ML] = [FE,AC]
       because eqang [0][FE,LK] = [BO,ML] and eqang [0][FE,AC] = [BO,ML].
eqang [0][FE,ML] = [BO,AC]
       because perp[$ML,AC$] and perp[$BO,FE$].
eqang [0][BO,LK] = [BO,AC] = [FE,ML]
       because eqang [0][FE,ML] = [BO,LK] and eqang [0][FE,ML] = [BO,AC].
eqang [0][FE,NM] = [BO,ML]
       because perp[$ML,NM$] and perp[$BO,FE$].
eqang [0][FE,AC] = [FE,LK] = [BO,ML] = [FE,NM]
       because eqang [0][FE,LK] = [BO,ML] = [FE,AC] and eqang [0][FE,NM] = [BO,ML].
eqang [0][FE,ML] = [BO,NM]
       because perp[$ML,NM$] and perp[$BO,FE$].
eqang [0][BO,AC] = [BO,LK] = [BO,NM] = [FE,ML]
       because eqang [0][BO,LK] = [BO,AC] = [FE,ML] and eqang [0][FE,ML] = [BO,NM].
eqang [0][FE,LK] = [BO,BP]
       because perp[$BP,LK$] and perp[$BO,FE$].
eqang [0][OD,NK] = [GH,LK] = [OD,ML] = [GH,NM] = [GH,AC] = [DO,DB] = [BP,BO] = [LK,FE]
       because eqang [0][BP,BO] = [DO,DB] = [GH,AC] = [GH,NM] = [OD,ML] = [GH,LK] = [OD,NK] and eqang [0][FE,LK] = [BO,BP].
eqang [0][FE,NM] = [BO,ML] = [FE,AC] = [FE,LK] = [BO,BP] = [DB,DO] = [AC,GH] = [NM,GH] = [ML,OD] = [LK,GH] = [NK,OD]
       because eqang [0][FE,AC] = [FE,LK] = [BO,ML] = [FE,NM] and eqang [0][OD,NK] = [GH,LK] = [OD,ML] = [GH,NM] = [GH,AC] = [DO,DB] = [BP,BO] = [LK,FE].
eqang [0][FE,BP] = [BO,LK]
       because perp[$BP,LK$] and perp[$BO,FE$].
eqang [0][FE,ML] = [BO,NM] = [BO,AC] = [BO,LK] = [FE,BP]
       because eqang [0][BO,AC] = [BO,LK] = [BO,NM] = [FE,ML] and eqang [0][FE,BP] = [BO,LK].
eqang [0][FE,LK] = [BO,NK]
       because perp[$NK,LK$] and perp[$BO,FE$].
eqang [1][NK,OD] = [LK,GH] = [ML,OD] = [NM,GH] = [AC,GH] = [DB,DO] = [BO,BP] = [FE,AC] = [BO,ML] = [FE,NM] = [BO,NK] = [FE,LK]
       because eqang [0][FE,NM] = [BO,ML] = [FE,AC] = [FE,LK] = [BO,BP] = [DB,DO] = [AC,GH] = [NM,GH] = [ML,OD] = [LK,GH] = [NK,OD] and eqang [0][FE,LK] = [BO,NK].
eqang [0][FE,NK] = [BO,LK]
       because perp[$NK,LK$] and perp[$BO,FE$].
eqang [1][FE,BP] = [BO,AC] = [BO,NM] = [FE,ML] = [BO,LK] = [FE,NK]
       because eqang [0][FE,ML] = [BO,NM] = [BO,AC] = [BO,LK] = [FE,BP] and eqang [0][FE,NK] = [BO,LK].
eqang [0][FE,PH] = [BO,AD]
       because perp[$AD,PH$] and perp[$BO,FE$].
eqang [0][FE,AD] = [BO,PH]
       because perp[$AD,PH$] and perp[$BO,FE$].
eqang [0][FE,PL] = [BO,AD]
       because perp[$AD,PL$] and perp[$BO,FE$].
eqang [0][FE,PH] = [BO,AD] = [FE,PL]
       because eqang [0][FE,PH] = [BO,AD] and eqang [0][FE,PL] = [BO,AD].
eqang [0][FE,AD] = [BO,PL]
       because perp[$AD,PL$] and perp[$BO,FE$].
eqang [0][BO,PH] = [BO,PL] = [FE,AD]
       because eqang [0][FE,AD] = [BO,PH] and eqang [0][FE,AD] = [BO,PL].
eqang [0][FE,ON] = [BO,AD]
       because perp[$AD,ON$] and perp[$BO,FE$].
eqang [1][FE,PL] = [FE,PL] = [BO,AD] = [FE,ON]
       because eqang [0][FE,PH] = [BO,AD] = [FE,PL] and eqang [0][FE,ON] = [BO,AD].
eqang [0][FE,AD] = [OB,ON]
       because perp[$AD,ON$] and perp[$BO,FE$].
eqang [1][BO,PL] = [BO,PL] = [OB,ON] = [FE,AD]
       because eqang [0][BO,PH] = [BO,PL] = [FE,AD] and eqang [0][FE,AD] = [OB,ON].
eqang [0][FE,FP] = [BO,BC]
       because perp[$BC,PN$] and perp[$BO,FE$].
eqang [0][AO,PL] = [HE,HA] = [PL,OD] = [HA,HG] = [FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [CB,CO] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [MP,MN] = [PM,PA] = [PB,PK] = [PH,OD] = [AO,PH] = [BO,BC] = [FE,FP]
       because eqang [0][AO,PH] = [PH,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [MP,MN] = [BP,OM] = [ML,PK] = [KO,KL] = [BO,BC] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] = [HE,HA] = [AO,PL] and eqang [0][FE,FP] = [BO,BC].
eqang [0][FE,FB] = [BO,PN]
       because perp[$BC,PN$] and perp[$BO,FE$].
eqang [0][AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [FG,FB] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [HP,HE] = [PN,BO] = [FB,FE]
       because eqang [0][HP,HE] = [HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [ML,MP] = [PB,PM] = [KN,KO] = [PB,PM] = [FB,FE] = [FG,FB] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] and eqang [0][FE,FB] = [BO,PN].
eqang [0][FE,FB] = [BO,PF]
       because perp[$BC,PF$] and perp[$BO,FE$].
eqang [0][PN,BO] = [HP,HE] = [HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PB,PM] = [KN,KO] = [FG,FB] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [PF,BO] = [FB,FE]
       because eqang [0][AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [FG,FB] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [HP,HE] = [PN,BO] = [FB,FE] and eqang [0][FE,FB] = [BO,PF].
eqang [0][FE,OL] = [BO,BC]
       because perp[$BC,OL$] and perp[$BO,FE$].
eqang [0][FE,FP] = [AO,PH] = [PH,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] = [HE,HA] = [AO,PL] = [BO,BC] = [FE,OL]
       because eqang [0][AO,PL] = [HE,HA] = [PL,OD] = [HA,HG] = [FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [CB,CO] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [MP,MN] = [PM,PA] = [PB,PK] = [PH,OD] = [AO,PH] = [BO,BC] = [FE,FP] and eqang [0][FE,OL] = [BO,BC].
eqang [0][FE,FG] = [OB,OC]
       because perp[$CO,GF$] and perp[$BO,FE$].
eqang [0][KP,KA] = [CD,OK] = [MC,MP] = [OM,AB] = [HG,HE] = [OD,OA] = [OC,OB] = [FG,FE]
       because eqang [0][OD,OA] = [HG,HE] = [OC,OB] = [OM,AB] = [MC,MP] = [CD,OK] = [KP,KA] = [MC,MP] and eqang [0][FE,FG] = [OB,OC].
eqang [1][FE,CO] = [BO,GF]
       because perp[$CO,GF$] and perp[$BO,FE$].
eqang [0][GF,LK] = [CO,ML]
       because perp[$ML,LK$] and perp[$CO,GF$].
eqang [0][GF,ML] = [CO,LK]
       because perp[$ML,LK$] and perp[$CO,GF$].
eqang [0][GF,AC] = [CO,ML]
       because perp[$ML,AC$] and perp[$CO,GF$].
eqang [0][GF,LK] = [CO,ML] = [GF,AC]
       because eqang [0][GF,LK] = [CO,ML] and eqang [0][GF,AC] = [CO,ML].
eqang [0][GF,ML] = [CO,CA]
       because perp[$ML,AC$] and perp[$CO,GF$].
eqang [0][AO,LK] = [EH,NK] = [AO,NM] = [EH,ML] = [AO,AC] = [EH,BP] = [CA,CO] = [ML,GF]
       because eqang [0][EH,BP] = [CA,CO] = [AO,AC] = [EH,ML] = [AO,NM] = [EH,NK] = [AO,LK] and eqang [0][GF,ML] = [CO,CA].
eqang [0][CO,LK] = [GF,ML] = [CO,CA] = [BP,EH] = [AC,AO] = [ML,EH] = [NM,AO] = [NK,EH] = [LK,AO]
       because eqang [0][GF,ML] = [CO,LK] and eqang [0][AO,LK] = [EH,NK] = [AO,NM] = [EH,ML] = [AO,AC] = [EH,BP] = [CA,CO] = [ML,GF].
eqang [0][GF,NM] = [CO,ML]
       because perp[$ML,NM$] and perp[$CO,GF$].
eqang [0][GF,AC] = [GF,LK] = [CO,ML] = [GF,NM]
       because eqang [0][GF,LK] = [CO,ML] = [GF,AC] and eqang [0][GF,NM] = [CO,ML].
eqang [0][GF,ML] = [CO,NM]
       because perp[$ML,NM$] and perp[$CO,GF$].
eqang [0][LK,AO] = [NK,EH] = [NM,AO] = [ML,EH] = [AC,AO] = [BP,EH] = [CO,CA] = [CO,LK] = [CO,NM] = [GF,ML]
       because eqang [0][CO,LK] = [GF,ML] = [CO,CA] = [BP,EH] = [AC,AO] = [ML,EH] = [NM,AO] = [NK,EH] = [LK,AO] and eqang [0][GF,ML] = [CO,NM].
eqang [0][GF,LK] = [CO,BP]
       because perp[$BP,LK$] and perp[$CO,GF$].
eqang [0][GF,NM] = [CO,ML] = [GF,AC] = [CO,BP] = [GF,LK]
       because eqang [0][GF,AC] = [GF,LK] = [CO,ML] = [GF,NM] and eqang [0][GF,LK] = [CO,BP].
eqang [0][GF,BP] = [CO,LK]
       because perp[$BP,LK$] and perp[$CO,GF$].
eqang [0][GF,ML] = [CO,NM] = [CO,CA] = [BP,EH] = [AC,AO] = [ML,EH] = [NM,AO] = [NK,EH] = [LK,AO] = [CO,LK] = [GF,BP]
       because eqang [0][LK,AO] = [NK,EH] = [NM,AO] = [ML,EH] = [AC,AO] = [BP,EH] = [CO,CA] = [CO,LK] = [CO,NM] = [GF,ML] and eqang [0][GF,BP] = [CO,LK].
eqang [0][GF,LK] = [CO,NK]
       because perp[$NK,LK$] and perp[$CO,GF$].
eqang [1][CO,BP] = [GF,AC] = [CO,ML] = [GF,NM] = [CO,NK] = [GF,LK]
       because eqang [0][GF,NM] = [CO,ML] = [GF,AC] = [CO,BP] = [GF,LK] and eqang [0][GF,LK] = [CO,NK].
eqang [0][GF,NK] = [CO,LK]
       because perp[$NK,LK$] and perp[$CO,GF$].
eqang [1][GF,BP] = [LK,AO] = [NK,EH] = [NM,AO] = [ML,EH] = [AC,AO] = [BP,EH] = [CO,CA] = [CO,NM] = [GF,ML] = [CO,LK] = [GF,NK]
       because eqang [0][GF,ML] = [CO,NM] = [CO,CA] = [BP,EH] = [AC,AO] = [ML,EH] = [NM,AO] = [NK,EH] = [LK,AO] = [CO,LK] = [GF,BP] and eqang [0][GF,NK] = [CO,LK].
eqang [0][GF,PH] = [CO,AD]
       because perp[$AD,PH$] and perp[$CO,GF$].
eqang [0][GF,AD] = [CO,PH]
       because perp[$AD,PH$] and perp[$CO,GF$].
eqang [0][GF,PL] = [CO,AD]
       because perp[$AD,PL$] and perp[$CO,GF$].
eqang [0][GF,PH] = [CO,AD] = [GF,PL]
       because eqang [0][GF,PH] = [CO,AD] and eqang [0][GF,PL] = [CO,AD].
eqang [0][GF,AD] = [CO,PL]
       because perp[$AD,PL$] and perp[$CO,GF$].
eqang [0][CO,PH] = [CO,PL] = [GF,AD]
       because eqang [0][GF,AD] = [CO,PH] and eqang [0][GF,AD] = [CO,PL].
eqang [0][GF,ON] = [CO,AD]
       because perp[$AD,ON$] and perp[$CO,GF$].
eqang [1][GF,PL] = [GF,PL] = [CO,AD] = [GF,ON]
       because eqang [0][GF,PH] = [CO,AD] = [GF,PL] and eqang [0][GF,ON] = [CO,AD].
eqang [0][GF,AD] = [OC,ON]
       because perp[$AD,ON$] and perp[$CO,GF$].
eqang [1][CO,PL] = [CO,PL] = [OC,ON] = [GF,AD]
       because eqang [0][CO,PH] = [CO,PL] = [GF,AD] and eqang [0][GF,AD] = [OC,ON].
eqang [0][FG,FP] = [CO,CB]
       because perp[$BC,PN$] and perp[$CO,GF$].
eqang [0][FE,OL] = [BO,BC] = [AO,PL] = [HE,HA] = [PL,OD] = [HA,HG] = [FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [MP,MN] = [PM,PA] = [PB,PK] = [PH,OD] = [AO,PH] = [FE,FP] = [CB,CO] = [FP,FG]
       because eqang [0][FE,FP] = [AO,PH] = [PH,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [CB,CO] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] = [HE,HA] = [AO,PL] = [BO,BC] = [FE,OL] and eqang [0][FG,FP] = [CO,CB].
eqang [0][FG,FB] = [CO,PN]
       because perp[$BC,PN$] and perp[$CO,GF$].
eqang [0][FB,FE] = [PF,BO] = [AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [HP,HE] = [PN,BO] = [CO,PN] = [FG,FB]
       because eqang [0][PN,BO] = [HP,HE] = [HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PB,PM] = [KN,KO] = [FG,FB] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [PF,BO] = [FB,FE] and eqang [0][FG,FB] = [CO,PN].
eqang [0][FG,FB] = [CO,PF]
       because perp[$BC,PF$] and perp[$CO,GF$].
eqang [0][CO,PN] = [PN,BO] = [HP,HE] = [HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PB,PM] = [KN,KO] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [PN,BO] = [FB,FE] = [CO,PN] = [FG,FB]
       because eqang [0][FB,FE] = [PF,BO] = [AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [MC,ML] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [HP,HE] = [DC,DB] = [HG,HP] = [HG,HP] = [HP,HE] = [PN,BO] = [CO,PN] = [FG,FB] and eqang [0][FG,FB] = [CO,PF].
eqang [0][GF,OL] = [CO,CB]
       because perp[$BC,OL$] and perp[$CO,GF$].
eqang [0][FP,FG] = [FE,FP] = [AO,PL] = [PL,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] = [HE,HA] = [AO,PL] = [BO,BC] = [FE,OL] = [CB,CO] = [OL,GF]
       because eqang [0][FE,OL] = [BO,BC] = [AO,PL] = [HE,HA] = [PL,OD] = [HA,HG] = [FP,FG] = [CA,CD] = [FE,FP] = [BA,BP] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [MN,MC] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [MP,MN] = [PM,PA] = [PB,PK] = [PH,OD] = [AO,PH] = [FE,FP] = [CB,CO] = [FP,FG] and eqang [0][GF,OL] = [CO,CB].
eqang [1][HA,HC] = [GA,GC]
       because circle[$ACGH$].
eqang [1][GA,GH] = [CA,CH]
       because circle[$AHCG$].
eqang [1][HC,HG] = [AC,AG]
       because circle[$CGAH$].
eqang [1][CD,CH] = [AG,AD]
       because circle[$GHAC$].
eqang [0][PH,PN] = [AD,BC]
       because perp[$BC,PN$] and perp[$AD,PH$].
eqang [0][NP,NO] = [BC,AD] = [PN,PH]
       because eqang [0][PN,PH] = [NP,NO] and eqang [0][PH,PN] = [AD,BC].
eqang [0][LO,LP] = [OL,ON] = [PF,PH] = [NP,NO] = [PN,PH] = [BC,AD] = [NP,NO]
       because eqang [0][NP,NO] = [PF,PH] = [OL,ON] = [BC,AD] = [LO,LP] and eqang [0][NP,NO] = [BC,AD] = [PN,PH].
eqang [0][LP,LB] = [NA,NP]
       because perp[$BC,PN$] and perp[$AD,PH$].
eqang [0][AD,OL] = [NA,NP] = [ON,BC] = [NA,NP] = [LP,LB]
       because eqang [0][ON,BC] = [NA,NP] = [AD,OL] = [LP,LB] and eqang [0][LP,LB] = [NA,NP].
eqang [0][OA,OB] = [EH,EF] = [LP,LB] = [GH,GF] = [OD,OC] = [LP,LB] = [NA,NP] = [ON,BC] = [NA,NP] = [AD,OL]
       because eqang [0][OD,OC] = [GH,GF] = [LP,LB] = [NA,NP] = [EH,EF] = [OA,OB] and eqang [0][AD,OL] = [NA,NP] = [ON,BC] = [NA,NP] = [LP,LB].
eqang [0][PL,NM] = [AD,ML]
       because perp[$ML,NM$] and perp[$AD,PL$].
eqang [0][PM,BO] = [EA,EF] = [AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PL,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PL,NM] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AD,ML] = [PL,NM]
       because eqang [0][EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PF] = [PL,PA] = [LP,LK] = [NK,NP] = [LK,LB] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [AD,ML] = [PH,NM] = [BP,OL] = [ML,PF] = [PB,PN] = [NK,NP] = [PH,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO] and eqang [0][PL,NM] = [AD,ML].
eqang [0][PL,NK] = [AD,LK]
       because perp[$NK,LK$] and perp[$AD,PL$].
eqang [0][OK,FE] = [BA,BO] = [EH,EP] = [GH,GC] = [PA,PF] = [EP,EF] = [BP,BC] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PL,NK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [PL,PB] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] = [AD,LK] = [PL,NK]
       because eqang [0][AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [PH,PB] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LM,LB] = [LK,LO] = [NK,BC] = [AC,OL] = [LP,LM] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [AD,LK] = [PH,NK] = [NM,OL] = [LK,PF] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [PA,PF] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE] and eqang [0][PL,NK] = [AD,LK].
eqang [0][PL,PN] = [AD,BC]
       because perp[$BC,PN$] and perp[$AD,PL$].
eqang [0][NP,NO] = [PN,PH] = [NP,NO] = [PF,PH] = [OL,ON] = [LO,LP] = [BC,AD] = [PN,PL]
       because eqang [0][LO,LP] = [OL,ON] = [PF,PH] = [NP,NO] = [PN,PH] = [BC,AD] = [NP,NO] and eqang [0][PL,PN] = [AD,BC].
eqang [0][PL,PF] = [AD,BC]
       because perp[$BC,PF$] and perp[$AD,PL$].
eqang [0][LP,LO] = [AD,BC] = [PL,PF]
       because eqang [0][PL,PF] = [LP,LO] and eqang [0][PL,PF] = [AD,BC].
eqang [0][PN,PL] = [LO,LP] = [OL,ON] = [PF,PL] = [NP,NO] = [PN,PL] = [NP,NO] = [PF,PL] = [BC,AD] = [LO,LP]
       because eqang [0][NP,NO] = [PN,PH] = [NP,NO] = [PF,PH] = [OL,ON] = [LO,LP] = [BC,AD] = [PN,PL] and eqang [0][LP,LO] = [AD,BC] = [PL,PF].
eqang [0][OP,ON] = [PO,PH]
       because para[$PH,ON$].
eqang [1][PO,PL] = [PO,PH] = [OP,ON]
       because eqang [0][PO,PL] = [OP,ON] and eqang [0][OP,ON] = [PO,PH].
eqang [0][NP,NO] = [PN,PH]
       because para[$PH,NO$].
eqang [0][BC,AD] = [NP,NO] = [PN,PL] = [OL,ON] = [LO,LP] = [PN,PL] = [PN,PH] = [NP,NO]
       because eqang [0][PN,PL] = [LO,LP] = [OL,ON] = [PF,PL] = [NP,NO] = [PN,PL] = [NP,NO] = [PF,PL] = [BC,AD] = [LO,LP] and eqang [0][NP,NO] = [PN,PH].
eqang [0][OH,ON] = [HO,HP]
       because para[$HP,ON$].
eqang [1][HO,HP] = [HO,HP] = [OH,ON]
       because eqang [0][HO,HP] = [OH,ON] and eqang [0][OH,ON] = [HO,HP].
eqang [0][NA,NO] = [HA,HP]
       because para[$HP,NO$].
eqang [0][NL,NO] = [LN,LP]
       because para[$LP,NO$].
eqang [0][PN,LK] = [LB,LM]
       because perp[$ML,LK$] and perp[$BC,PN$].
eqang [0][AD,LK] = [AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LK,LO] = [NK,BC] = [AC,OL] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [PL,NK] = [NM,OL] = [LK,PN] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [PA,PN] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE] = [LM,LB] = [LK,PN]
       because eqang [0][OK,FE] = [BA,BO] = [EH,EP] = [GH,GC] = [PA,PF] = [EP,EF] = [BP,BC] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PF] = [NM,OL] = [PL,NK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [LP,LM] = [AC,OL] = [NK,BC] = [LK,LO] = [LM,LB] = [NM,NP] = [PA,PN] = [NM,NP] = [AD,AC] = [PL,PB] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] = [AD,LK] = [PL,NK] and eqang [0][PN,LK] = [LB,LM].
eqang [0][PN,ML] = [LB,LK]
       because perp[$ML,LK$] and perp[$BC,PN$].
eqang [0][AD,ML] = [EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PN] = [NK,NP] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [PL,NM] = [BP,OL] = [ML,PN] = [PB,PN] = [NK,NP] = [PL,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO] = [LK,LB] = [ML,PN]
       because eqang [0][PM,BO] = [EA,EF] = [AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PL,PA] = [NK,NP] = [PB,PN] = [ML,PF] = [BP,OL] = [PL,NM] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [LK,LB] = [NK,NP] = [LP,LK] = [PL,PA] = [PB,PF] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AD,ML] = [PL,NM] and eqang [0][PN,ML] = [LB,LK].
eqang [0][OP,OL] = [PO,PN]
       because para[$PN,OL$].
eqang [1][PO,PN] = [PO,PN] = [OP,OL]
       because eqang [0][PO,PN] = [OP,OL] and eqang [0][OP,OL] = [PO,PN].
eqang [0][LP,LO] = [PL,PN]
       because para[$PN,LO$].
eqang [0][PN,PH] = [OL,ON] = [PN,PL] = [NP,NO] = [BC,AD] = [PN,PL] = [LO,LP]
       because eqang [0][BC,AD] = [NP,NO] = [PN,PL] = [OL,ON] = [LO,LP] = [PN,PL] = [PN,PH] = [NP,NO] and eqang [0][LP,LO] = [PL,PN].
eqang [0][ON,OL] = [NO,NP]
       because para[$NP,OL$].
eqang [1][LO,LP] = [PN,PL] = [BC,AD] = [NP,NO] = [PN,PL] = [PN,PH] = [NP,NO] = [OL,ON]
       because eqang [0][PN,PH] = [OL,ON] = [PN,PL] = [NP,NO] = [BC,AD] = [PN,PL] = [LO,LP] and eqang [0][ON,OL] = [NO,NP].
eqang [0][LN,LO] = [NL,NP]
       because para[$NP,LO$].
eqang [0][LN,LO] = [NL,NP]
       because para[$NP,LO$].
eqang [0][NL,NP] = [NL,NP] = [LN,LO]
       because eqang [0][LN,LO] = [NL,NP] and eqang [0][LN,LO] = [NL,NP].
eqang [1][HB,HA] = [EA,ED]
       because circle[$BDEH$].
eqang [1][HB,HE] = [DB,DE]
       because circle[$BEDH$].
eqang [1][ED,EH] = [BP,BH]
       because circle[$DHBE$].
eqang [1][DE,DA] = [BA,BH]
       because circle[$EHBD$].
eqang [0][KL,KM] = [LN,LK]
       because circle[$NMLK$] and para[$NM,LK$].
eqang [0][NL,NM] = [LN,LK] = [KL,KM]
       because eqang [0][NL,NM] = [LN,LK] and eqang [0][KL,KM] = [LN,LK].
eqang [0][MK,MN] = [KM,KL] = [LK,LN] = [NM,NL]
       because eqang [0][MK,MN] = [KM,KL] and eqang [0][NL,NM] = [LN,LK] = [KL,KM].
eqang [0][KN,KM] = [LN,LM]
       because circle[$NMLK$].
eqang [0][MK,ML] = [LM,LN] = [KM,KN]
       because eqang [0][KM,KN] = [MK,ML] and eqang [0][KN,KM] = [LN,LM].
eqang [0][NL,NK] = [KN,KM] = [LN,LM] = [ML,MK]
       because eqang [0][NL,NK] = [LN,LM] and eqang [0][MK,ML] = [LM,LN] = [KM,KN].
eqang [0][GN,GC] = [LN,LM]
       because circle[$NMLG$].
eqang [0][ML,MK] = [KN,KM] = [NL,NK] = [LN,LM] = [GN,GC]
       because eqang [0][NL,NK] = [KN,KM] = [LN,LM] = [ML,MK] and eqang [0][GN,GC] = [LN,LM].
eqang [0][FP,FM] = [LN,LM]
       because circle[$NMLF$].
eqang [0][GN,GC] = [NL,NK] = [KN,KM] = [ML,MK] = [LN,LM] = [FP,FM]
       because eqang [0][ML,MK] = [KN,KM] = [NL,NK] = [LN,LM] = [GN,GC] and eqang [0][FP,FM] = [LN,LM].
eqang [0][EN,EP] = [LN,LM]
       because circle[$NMLE$].
eqang [0][FP,FM] = [ML,MK] = [KN,KM] = [NL,NK] = [GN,GC] = [LN,LM] = [EN,EP]
       because eqang [0][GN,GC] = [NL,NK] = [KN,KM] = [ML,MK] = [LN,LM] = [FP,FM] and eqang [0][EN,EP] = [LN,LM].
eqang [0][HA,HM] = [LN,LM]
       because circle[$NMLH$].
eqang [0][EN,EP] = [GN,GC] = [NL,NK] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM]
       because eqang [0][FP,FM] = [ML,MK] = [KN,KM] = [NL,NK] = [GN,GC] = [LN,LM] = [EN,EP] and eqang [0][HA,HM] = [LN,LM].
eqang [0][GN,GL] = [MN,ML]
       because circle[$NLMG$].
eqang [0][EN,EL] = [MN,ML]
       because circle[$NLME$].
eqang [0][GN,GL] = [MN,ML] = [EN,EL]
       because eqang [0][GN,GL] = [MN,ML] and eqang [0][EN,EL] = [MN,ML].
eqang [0][GN,GP] = [MN,MK]
       because circle[$NKMG$].
eqang [0][NM,NL] = [LK,LN] = [KM,KL] = [MK,MN] = [GP,GN]
       because eqang [0][MK,MN] = [KM,KL] = [LK,LN] = [NM,NL] and eqang [0][GN,GP] = [MN,MK].
eqang [0][FP,FK] = [MN,MK]
       because circle[$NKMF$].
eqang [0][GP,GN] = [KM,KL] = [LK,LN] = [NM,NL] = [MK,MN] = [FK,FP]
       because eqang [0][NM,NL] = [LK,LN] = [KM,KL] = [MK,MN] = [GP,GN] and eqang [0][FP,FK] = [MN,MK].
eqang [0][EN,EA] = [MN,MK]
       because circle[$NKME$].
eqang [0][FK,FP] = [NM,NL] = [LK,LN] = [KM,KL] = [GP,GN] = [MK,MN] = [EA,EN]
       because eqang [0][GP,GN] = [KM,KL] = [LK,LN] = [NM,NL] = [MK,MN] = [FK,FP] and eqang [0][EN,EA] = [MN,MK].
eqang [0][HA,HK] = [MN,MK]
       because circle[$NKMH$].
eqang [0][EA,EN] = [GP,GN] = [KM,KL] = [LK,LN] = [NM,NL] = [FK,FP] = [MK,MN] = [HK,HA]
       because eqang [0][FK,FP] = [NM,NL] = [LK,LN] = [KM,KL] = [GP,GN] = [MK,MN] = [EA,EN] and eqang [0][HA,HK] = [MN,MK].
eqang [0][LN,LG] = [MN,MC]
       because circle[$NGML$].
eqang [0][OL,GF] = [CB,CO] = [FE,OL] = [BO,BC] = [HE,HA] = [HA,HG] = [CA,CD] = [BA,BP] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [MP,MN] = [PM,PA] = [PB,PK] = [PL,OD] = [AO,PL] = [FE,FP] = [FP,FG] = [MN,MC] = [LN,LG]
       because eqang [0][FP,FG] = [FE,FP] = [AO,PL] = [PL,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [MN,MC] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [BA,BP] = [FE,FP] = [CA,CD] = [FP,FG] = [HA,HG] = [PL,OD] = [HE,HA] = [AO,PL] = [BO,BC] = [FE,OL] = [CB,CO] = [OL,GF] and eqang [0][LN,LG] = [MN,MC].
eqang [0][EN,EG] = [MN,MC]
       because circle[$NGME$].
eqang [0][LN,LG] = [FP,FG] = [FE,FP] = [AO,PL] = [PL,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [BA,BP] = [CA,CD] = [HA,HG] = [HE,HA] = [BO,BC] = [FE,OL] = [CB,CO] = [OL,GF] = [MN,MC] = [EN,EG]
       because eqang [0][OL,GF] = [CB,CO] = [FE,OL] = [BO,BC] = [HE,HA] = [HA,HG] = [CA,CD] = [BA,BP] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [MP,MN] = [PM,PA] = [PB,PK] = [PL,OD] = [AO,PL] = [FE,FP] = [FP,FG] = [MN,MC] = [LN,LG] and eqang [0][EN,EG] = [MN,MC].
eqang [0][LN,LB] = [MN,MF]
       because circle[$NFML$].
eqang [0][KN,KF] = [MN,MF]
       because circle[$NFMK$].
eqang [0][LN,LB] = [MN,MF] = [KN,KF]
       because eqang [0][LN,LB] = [MN,MF] and eqang [0][KN,KF] = [MN,MF].
eqang [0][GN,GF] = [MN,MF]
       because circle[$NFMG$].
eqang [0][KN,KF] = [LN,LB] = [MN,MF] = [GN,GF]
       because eqang [0][LN,LB] = [MN,MF] = [KN,KF] and eqang [0][GN,GF] = [MN,MF].
eqang [0][EN,EF] = [MN,MF]
       because circle[$NFME$].
eqang [0][GN,GF] = [LN,LB] = [KN,KF] = [MN,MF] = [EN,EF]
       because eqang [0][KN,KF] = [LN,LB] = [MN,MF] = [GN,GF] and eqang [0][EN,EF] = [MN,MF].
eqang [0][HA,HF] = [MN,MF]
       because circle[$NFMH$].
eqang [0][EN,EF] = [KN,KF] = [LN,LB] = [GN,GF] = [MN,MF] = [HA,HF]
       because eqang [0][GN,GF] = [LN,LB] = [KN,KF] = [MN,MF] = [EN,EF] and eqang [0][HA,HF] = [MN,MF].
eqang [0][LN,LE] = [MN,MP]
       because circle[$NEML$].
eqang [0][EN,EG] = [MN,MC] = [OL,GF] = [CB,CO] = [FE,OL] = [BO,BC] = [HE,HA] = [HA,HG] = [CA,CD] = [BA,BP] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [PB,PK] = [PL,OD] = [AO,PL] = [FE,FP] = [FP,FG] = [LN,LG] = [MP,MN] = [LE,LN]
       because eqang [0][LN,LG] = [FP,FG] = [FE,FP] = [AO,PL] = [PL,OD] = [PB,PK] = [PM,PA] = [MP,MN] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [BA,BP] = [CA,CD] = [HA,HG] = [HE,HA] = [BO,BC] = [FE,OL] = [CB,CO] = [OL,GF] = [MN,MC] = [EN,EG] and eqang [0][LN,LE] = [MN,MP].
eqang [0][GN,GE] = [MN,MP]
       because circle[$NEMG$].
eqang [1][LE,LN] = [LN,LG] = [FP,FG] = [FE,FP] = [AO,PL] = [PL,OD] = [PB,PK] = [PM,PA] = [KN,KP] = [LK,CD] = [NK,OM] = [ML,MO] = [PM,LK] = [OK,NM] = [ON,OD] = [OA,ON] = [OK,AC] = [KA,KN] = [AB,ML] = [BP,OM] = [ML,PK] = [KO,KL] = [BA,BP] = [CA,CD] = [HA,HG] = [HE,HA] = [BO,BC] = [FE,OL] = [CB,CO] = [OL,GF] = [MN,MC] = [EN,EG] = [MP,MN] = [GE,GN]
       because eqang [0][EN,EG] = [MN,MC] = [OL,GF] = [CB,CO] = [FE,OL] = [BO,BC] = [HE,HA] = [HA,HG] = [CA,CD] = [BA,BP] = [KO,KL] = [ML,PK] = [BP,OM] = [AB,ML] = [KA,KN] = [OK,AC] = [OA,ON] = [ON,OD] = [OK,NM] = [PM,LK] = [ML,MO] = [NK,OM] = [LK,CD] = [KN,KP] = [PM,PA] = [PB,PK] = [PL,OD] = [AO,PL] = [FE,FP] = [FP,FG] = [LN,LG] = [MP,MN] = [LE,LN] and eqang [0][GN,GE] = [MN,MP].
eqang [0][LN,LP] = [MN,MH]
       because circle[$NHML$].
eqang [0][NL,NO] = [MN,MH] = [LN,LP]
       because eqang [0][NL,NO] = [LN,LP] and eqang [0][LN,LP] = [MN,MH].
eqang [0][KN,KH] = [MN,MH]
       because circle[$NHMK$].
eqang [0][LN,LP] = [NL,NO] = [MN,MH] = [KN,KH]
       because eqang [0][NL,NO] = [MN,MH] = [LN,LP] and eqang [0][KN,KH] = [MN,MH].
eqang [0][GN,GH] = [MN,MH]
       because circle[$NHMG$].
eqang [0][KN,KH] = [NL,NO] = [LN,LP] = [MN,MH] = [GN,GH]
       because eqang [0][LN,LP] = [NL,NO] = [MN,MH] = [KN,KH] and eqang [0][GN,GH] = [MN,MH].
eqang [0][FP,FH] = [MN,MH]
       because circle[$NHMF$].
eqang [0][GN,GH] = [LN,LP] = [NL,NO] = [KN,KH] = [MN,MH] = [FP,FH]
       because eqang [0][KN,KH] = [NL,NO] = [LN,LP] = [MN,MH] = [GN,GH] and eqang [0][FP,FH] = [MN,MH].
eqang [0][EN,EH] = [MN,MH]
       because circle[$NHME$].
eqang [0][FP,FH] = [KN,KH] = [NL,NO] = [LN,LP] = [GN,GH] = [MN,MH] = [EN,EH]
       because eqang [0][GN,GH] = [LN,LP] = [NL,NO] = [KN,KH] = [MN,MH] = [FP,FH] and eqang [0][EN,EH] = [MN,MH].
eqang [0][GC,GL] = [NM,NL]
       because circle[$MLNG$].
eqang [0][HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EA,EN] = [NM,NL] = [GC,GL]
       because eqang [0][EA,EN] = [GP,GN] = [KM,KL] = [LK,LN] = [NM,NL] = [FK,FP] = [MK,MN] = [HK,HA] and eqang [0][GC,GL] = [NM,NL].
eqang [0][FM,FB] = [NM,NL]
       because circle[$MLNF$].
eqang [0][GC,GL] = [EA,EN] = [GP,GN] = [KM,KL] = [LK,LN] = [FK,FP] = [MK,MN] = [HK,HA] = [NM,NL] = [FM,FB]
       because eqang [0][HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EA,EN] = [NM,NL] = [GC,GL] and eqang [0][FM,FB] = [NM,NL].
eqang [0][EP,EL] = [NM,NL]
       because circle[$MLNE$].
eqang [0][FM,FB] = [HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EA,EN] = [GC,GL] = [NM,NL] = [EP,EL]
       because eqang [0][GC,GL] = [EA,EN] = [GP,GN] = [KM,KL] = [LK,LN] = [FK,FP] = [MK,MN] = [HK,HA] = [NM,NL] = [FM,FB] and eqang [0][EP,EL] = [NM,NL].
eqang [0][HM,HP] = [NM,NL]
       because circle[$MLNH$].
eqang [0][EP,EL] = [GC,GL] = [EA,EN] = [GP,GN] = [KM,KL] = [LK,LN] = [FK,FP] = [MK,MN] = [HK,HA] = [FM,FB] = [NM,NL] = [HM,HP]
       because eqang [0][FM,FB] = [HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EA,EN] = [GC,GL] = [NM,NL] = [EP,EL] and eqang [0][HM,HP] = [NM,NL].
eqang [0][FM,FK] = [NM,NK]
       because circle[$MKNF$].
eqang [0][HM,HK] = [NM,NK]
       because circle[$MKNH$].
eqang [0][FM,FK] = [NM,NK] = [HM,HK]
       because eqang [0][FM,FK] = [NM,NK] and eqang [0][HM,HK] = [NM,NK].
eqang [0][LM,LG] = [NM,NG]
       because circle[$MGNL$].
eqang [0][KM,KP] = [NM,NG]
       because circle[$MGNK$].
eqang [0][MK,MO] = [NM,NG] = [KM,KP]
       because eqang [0][MK,MO] = [KM,KP] and eqang [0][KM,KP] = [NM,NG].
eqang [0][LM,LG] = [KM,KP] = [NM,NG] = [MK,MO]
       because eqang [0][LM,LG] = [NM,NG] and eqang [0][MK,MO] = [NM,NG] = [KM,KP].
eqang [0][FM,FG] = [NM,NG]
       because circle[$MGNF$].
eqang [0][MK,MO] = [KM,KP] = [LM,LG] = [NM,NG] = [FM,FG]
       because eqang [0][LM,LG] = [KM,KP] = [NM,NG] = [MK,MO] and eqang [0][FM,FG] = [NM,NG].
eqang [0][EP,EG] = [NM,NG]
       because circle[$MGNE$].
eqang [0][FM,FG] = [LM,LG] = [KM,KP] = [MK,MO] = [NM,NG] = [EP,EG]
       because eqang [0][MK,MO] = [KM,KP] = [LM,LG] = [NM,NG] = [FM,FG] and eqang [0][EP,EG] = [NM,NG].
eqang [0][HM,HG] = [NM,NG]
       because circle[$MGNH$].
eqang [0][EP,EG] = [MK,MO] = [KM,KP] = [LM,LG] = [FM,FG] = [NM,NG] = [HM,HG]
       because eqang [0][FM,FG] = [LM,LG] = [KM,KP] = [MK,MO] = [NM,NG] = [EP,EG] and eqang [0][HM,HG] = [NM,NG].
eqang [0][KM,KF] = [NM,NP]
       because circle[$MFNK$].
eqang [0][LM,LB] = [OK,FE] = [BA,BO] = [EH,EP] = [GH,GC] = [EP,EF] = [BP,BC] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PN] = [NM,OL] = [PL,NK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [AC,OL] = [NK,BC] = [LK,LO] = [PA,PN] = [AD,AC] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] = [AD,LK] = [NM,NP] = [KM,KF]
       because eqang [0][AD,LK] = [AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [AD,AC] = [NM,NP] = [PA,PN] = [NM,NP] = [LK,LO] = [NK,BC] = [AC,OL] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [PL,NK] = [NM,OL] = [LK,PN] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [PA,PN] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE] = [LM,LB] = [LK,PN] and eqang [0][KM,KF] = [NM,NP].
eqang [0][HM,HF] = [NM,NP]
       because circle[$MFNH$].
eqang [0][KM,KF] = [AD,LK] = [AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [AD,AC] = [PA,PN] = [LK,LO] = [NK,BC] = [AC,OL] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [PL,NK] = [NM,OL] = [LK,PN] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE] = [LM,LB] = [NM,NP] = [HM,HF]
       because eqang [0][LM,LB] = [OK,FE] = [BA,BO] = [EH,EP] = [GH,GC] = [EP,EF] = [BP,BC] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PN] = [NM,OL] = [PL,NK] = [ON,BP] = [OM,OC] = [OD,OM] = [NA,NM] = [ON,ML] = [AC,OL] = [NK,BC] = [LK,LO] = [PA,PN] = [AD,AC] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] = [AD,LK] = [NM,NP] = [KM,KF] and eqang [0][HM,HF] = [NM,NP].
eqang [0][LM,LE] = [NM,NE]
       because circle[$MENL$].
eqang [0][KM,KA] = [NM,NE]
       because circle[$MENK$].
eqang [0][LM,LE] = [NM,NE] = [KM,KA]
       because eqang [0][LM,LE] = [NM,NE] and eqang [0][KM,KA] = [NM,NE].
eqang [0][GC,GE] = [NM,NE]
       because circle[$MENG$].
eqang [0][KM,KA] = [LM,LE] = [NM,NE] = [GC,GE]
       because eqang [0][LM,LE] = [NM,NE] = [KM,KA] and eqang [0][GC,GE] = [NM,NE].
eqang [0][FM,FE] = [NM,NE]
       because circle[$MENF$].
eqang [0][GC,GE] = [LM,LE] = [KM,KA] = [NM,NE] = [FM,FE]
       because eqang [0][KM,KA] = [LM,LE] = [NM,NE] = [GC,GE] and eqang [0][FM,FE] = [NM,NE].
eqang [0][HM,HE] = [NM,NE]
       because circle[$MENH$].
eqang [0][FM,FE] = [KM,KA] = [LM,LE] = [GC,GE] = [NM,NE] = [HM,HE]
       because eqang [0][GC,GE] = [LM,LE] = [KM,KA] = [NM,NE] = [FM,FE] and eqang [0][HM,HE] = [NM,NE].
eqang [0][KM,KH] = [NM,NA]
       because circle[$MHNK$].
eqang [0][HM,HF] = [NM,NP] = [LM,LB] = [OK,FE] = [BA,BO] = [EH,EP] = [GH,GC] = [EP,EF] = [BP,BC] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PN] = [NM,OL] = [PL,NK] = [ON,BP] = [OM,OC] = [OD,OM] = [ON,ML] = [AC,OL] = [NK,BC] = [LK,LO] = [PA,PN] = [AD,AC] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] = [AD,LK] = [KM,KF] = [NA,NM] = [KH,KM]
       because eqang [0][KM,KF] = [AD,LK] = [AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [AD,AC] = [PA,PN] = [LK,LO] = [NK,BC] = [AC,OL] = [ON,ML] = [NA,NM] = [OD,OM] = [OM,OC] = [ON,BP] = [PL,NK] = [NM,OL] = [LK,PN] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE] = [LM,LB] = [NM,NP] = [HM,HF] and eqang [0][KM,KH] = [NM,NA].
eqang [0][FM,FH] = [NM,NA]
       because circle[$MHNF$].
eqang [1][KH,KM] = [KM,KF] = [AD,LK] = [AO,AB] = [EH,OK] = [PK,CO] = [GC,GF] = [OD,PK] = [AD,AC] = [PA,PN] = [LK,LO] = [NK,BC] = [AC,OL] = [ON,ML] = [OD,OM] = [OM,OC] = [ON,BP] = [PL,NK] = [NM,OL] = [LK,PN] = [LP,LM] = [PL,PB] = [NO,NK] = [BP,BC] = [EP,EF] = [GH,GC] = [EH,EP] = [BA,BO] = [OK,FE] = [LM,LB] = [NM,NP] = [HM,HF] = [NA,NM] = [FH,FM]
       because eqang [0][HM,HF] = [NM,NP] = [LM,LB] = [OK,FE] = [BA,BO] = [EH,EP] = [GH,GC] = [EP,EF] = [BP,BC] = [NO,NK] = [PL,PB] = [LP,LM] = [LK,PN] = [NM,OL] = [PL,NK] = [ON,BP] = [OM,OC] = [OD,OM] = [ON,ML] = [AC,OL] = [NK,BC] = [LK,LO] = [PA,PN] = [AD,AC] = [OD,PK] = [GC,GF] = [PK,CO] = [EH,OK] = [AO,AB] = [AD,LK] = [KM,KF] = [NA,NM] = [KH,KM] and eqang [0][FM,FH] = [NM,NA].
eqang [0][GL,GP] = [NL,NK]
       because circle[$LKNG$].
eqang [0][HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [GN,GC] = [EN,EP] = [NL,NK] = [GL,GP]
       because eqang [0][EN,EP] = [GN,GC] = [NL,NK] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] and eqang [0][GL,GP] = [NL,NK].
eqang [0][FB,FK] = [NL,NK]
       because circle[$LKNF$].
eqang [0][GL,GP] = [EN,EP] = [GN,GC] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] = [NL,NK] = [FB,FK]
       because eqang [0][HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [GN,GC] = [EN,EP] = [NL,NK] = [GL,GP] and eqang [0][FB,FK] = [NL,NK].
eqang [0][EL,EA] = [NL,NK]
       because circle[$LKNE$].
eqang [0][FB,FK] = [HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [GN,GC] = [EN,EP] = [GL,GP] = [NL,NK] = [EL,EA]
       because eqang [0][GL,GP] = [EN,EP] = [GN,GC] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] = [NL,NK] = [FB,FK] and eqang [0][EL,EA] = [NL,NK].
eqang [0][HP,HK] = [NL,NK]
       because circle[$LKNH$].
eqang [0][EL,EA] = [GL,GP] = [EN,EP] = [GN,GC] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] = [FB,FK] = [NL,NK] = [HP,HK]
       because eqang [0][FB,FK] = [HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [GN,GC] = [EN,EP] = [GL,GP] = [NL,NK] = [EL,EA] and eqang [0][HP,HK] = [NL,NK].
eqang [0][ML,MC] = [NL,NG]
       because circle[$LGNM$].
eqang [0][FG,FB] = [FB,FE] = [AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [DC,DB] = [HG,HP] = [HP,HE] = [PN,BO] = [CO,PN] = [NG,NL] = [MC,ML]
       because eqang [0][CO,PN] = [PN,BO] = [HP,HE] = [HG,HP] = [HG,HP] = [DC,DB] = [HP,HE] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [MC,ML] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PB,PM] = [KN,KO] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [PN,BO] = [FB,FE] = [CO,PN] = [FG,FB] and eqang [0][ML,MC] = [NL,NG].
eqang [0][EL,EG] = [NL,NG]
       because circle[$LGNE$].
eqang [0][MC,ML] = [CO,PN] = [PN,BO] = [HP,HE] = [HG,HP] = [DC,DB] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PB,PM] = [KN,KO] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [FB,FE] = [FG,FB] = [NG,NL] = [EG,EL]
       because eqang [0][FG,FB] = [FB,FE] = [AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [KN,KO] = [PB,PM] = [ML,MP] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [DC,DB] = [HG,HP] = [HP,HE] = [PN,BO] = [CO,PN] = [NG,NL] = [MC,ML] and eqang [0][EL,EG] = [NL,NG].
eqang [0][ML,MF] = [NL,NP]
       because circle[$LFNM$].
eqang [0][LN,LO] = [NL,NP] = [NL,NP] = [ML,MF]
       because eqang [0][NL,NP] = [NL,NP] = [LN,LO] and eqang [0][ML,MF] = [NL,NP].
eqang [0][KL,KF] = [NL,NP]
       because circle[$LFNK$].
eqang [0][ML,MF] = [NL,NP] = [LN,LO] = [NL,NP] = [KL,KF]
       because eqang [0][LN,LO] = [NL,NP] = [NL,NP] = [ML,MF] and eqang [0][KL,KF] = [NL,NP].
eqang [0][GL,GF] = [NL,NP]
       because circle[$LFNG$].
eqang [0][KL,KF] = [LN,LO] = [NL,NP] = [ML,MF] = [NL,NP] = [GL,GF]
       because eqang [0][ML,MF] = [NL,NP] = [LN,LO] = [NL,NP] = [KL,KF] and eqang [0][GL,GF] = [NL,NP].
eqang [0][EL,EF] = [NL,NP]
       because circle[$LFNE$].
eqang [0][GL,GF] = [ML,MF] = [NL,NP] = [LN,LO] = [KL,KF] = [NL,NP] = [EL,EF]
       because eqang [0][KL,KF] = [LN,LO] = [NL,NP] = [ML,MF] = [NL,NP] = [GL,GF] and eqang [0][EL,EF] = [NL,NP].
eqang [0][HP,HF] = [NL,NP]
       because circle[$LFNH$].
eqang [0][EL,EF] = [KL,KF] = [LN,LO] = [NL,NP] = [ML,MF] = [GL,GF] = [NL,NP] = [HP,HF]
       because eqang [0][GL,GF] = [ML,MF] = [NL,NP] = [LN,LO] = [KL,KF] = [NL,NP] = [EL,EF] and eqang [0][HP,HF] = [NL,NP].
eqang [0][ML,MP] = [NL,NE]
       because circle[$LENM$].
eqang [0][EG,EL] = [NG,NL] = [FG,FB] = [FB,FE] = [AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [KN,KO] = [PB,PM] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [DC,DB] = [HG,HP] = [HP,HE] = [PN,BO] = [CO,PN] = [MC,ML] = [NL,NE] = [ML,MP]
       because eqang [0][MC,ML] = [CO,PN] = [PN,BO] = [HP,HE] = [HG,HP] = [DC,DB] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [ML,MP] = [PB,PM] = [KN,KO] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [FB,FE] = [FG,FB] = [NG,NL] = [EG,EL] and eqang [0][ML,MP] = [NL,NE].
eqang [0][GL,GE] = [NL,NE]
       because circle[$LENG$].
eqang [1][ML,MP] = [MC,ML] = [CO,PN] = [PN,BO] = [HP,HE] = [HG,HP] = [DC,DB] = [AC,AB] = [PK,PA] = [KP,KL] = [PK,NM] = [OM,LK] = [CD,NK] = [OM,AC] = [MO,MN] = [NK,PM] = [KL,KA] = [BP,OK] = [OL,OB] = [OC,OL] = [NM,AB] = [ML,OK] = [PB,PM] = [KN,KO] = [GH,ON] = [DO,DA] = [ON,EH] = [AD,AO] = [FB,FE] = [FG,FB] = [NG,NL] = [EG,EL] = [NL,NE] = [GL,GE]
       because eqang [0][EG,EL] = [NG,NL] = [FG,FB] = [FB,FE] = [AD,AO] = [ON,EH] = [DO,DA] = [GH,ON] = [KN,KO] = [PB,PM] = [ML,OK] = [NM,AB] = [OC,OL] = [OL,OB] = [BP,OK] = [KL,KA] = [NK,PM] = [MO,MN] = [OM,AC] = [CD,NK] = [OM,LK] = [PK,NM] = [KP,KL] = [PK,PA] = [AC,AB] = [DC,DB] = [HG,HP] = [HP,HE] = [PN,BO] = [CO,PN] = [MC,ML] = [NL,NE] = [ML,MP] and eqang [0][GL,GE] = [NL,NE].
eqang [0][ML,MH] = [NL,NA]
       because circle[$LHNM$].
eqang [0][KL,KH] = [NL,NA]
       because circle[$LHNK$].
eqang [0][ML,MH] = [NL,NA] = [KL,KH]
       because eqang [0][ML,MH] = [NL,NA] and eqang [0][KL,KH] = [NL,NA].
eqang [0][GL,GH] = [NL,NA]
       because circle[$LHNG$].
eqang [0][KL,KH] = [ML,MH] = [NL,NA] = [GL,GH]
       because eqang [0][ML,MH] = [NL,NA] = [KL,KH] and eqang [0][GL,GH] = [NL,NA].
eqang [0][FB,FH] = [NL,NA]
       because circle[$LHNF$].
eqang [0][GL,GH] = [ML,MH] = [KL,KH] = [NL,NA] = [FB,FH]
       because eqang [0][KL,KH] = [ML,MH] = [NL,NA] = [GL,GH] and eqang [0][FB,FH] = [NL,NA].
eqang [0][EL,EH] = [NL,NA]
       because circle[$LHNE$].
eqang [0][FB,FH] = [KL,KH] = [ML,MH] = [GL,GH] = [NL,NA] = [EL,EH]
       because eqang [0][GL,GH] = [ML,MH] = [KL,KH] = [NL,NA] = [FB,FH] and eqang [0][EL,EH] = [NL,NA].
eqang [0][MK,MC] = [NK,NG]
       because circle[$KGNM$].
eqang [0][LK,LG] = [NK,NG]
       because circle[$KGNL$].
eqang [0][MK,MC] = [NK,NG] = [LK,LG]
       because eqang [0][MK,MC] = [NK,NG] and eqang [0][LK,LG] = [NK,NG].
eqang [0][FK,FG] = [NK,NG]
       because circle[$KGNF$].
eqang [0][LK,LG] = [MK,MC] = [NK,NG] = [FK,FG]
       because eqang [0][MK,MC] = [NK,NG] = [LK,LG] and eqang [0][FK,FG] = [NK,NG].
eqang [0][EA,EG] = [NK,NG]
       because circle[$KGNE$].
eqang [0][FK,FG] = [MK,MC] = [LK,LG] = [NK,NG] = [EA,EG]
       because eqang [0][LK,LG] = [MK,MC] = [NK,NG] = [FK,FG] and eqang [0][EA,EG] = [NK,NG].
eqang [0][HK,HG] = [NK,NG]
       because circle[$KGNH$].
eqang [0][EA,EG] = [LK,LG] = [MK,MC] = [FK,FG] = [NK,NG] = [HK,HG]
       because eqang [0][FK,FG] = [MK,MC] = [LK,LG] = [NK,NG] = [EA,EG] and eqang [0][HK,HG] = [NK,NG].
eqang [0][MK,MF] = [NK,NP]
       because circle[$KFNM$].
eqang [0][LK,LB] = [PM,BO] = [EA,EF] = [AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PL,PA] = [ML,PN] = [BP,OL] = [PL,NM] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [PB,PN] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AD,ML] = [NK,NP] = [MK,MF]
       because eqang [0][AD,ML] = [EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PN] = [NK,NP] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [PL,NM] = [BP,OL] = [ML,PN] = [PB,PN] = [NK,NP] = [PL,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO] = [LK,LB] = [ML,PN] and eqang [0][MK,MF] = [NK,NP].
eqang [0][HK,HF] = [NK,NP]
       because circle[$KFNH$].
eqang [0][MK,MF] = [AD,ML] = [EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PN] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [PL,NM] = [BP,OL] = [ML,PN] = [PL,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO] = [LK,LB] = [NK,NP] = [HK,HF]
       because eqang [0][LK,LB] = [PM,BO] = [EA,EF] = [AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PL,PA] = [ML,PN] = [BP,OL] = [PL,NM] = [ON,LK] = [NA,NK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [PB,PN] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AD,ML] = [NK,NP] = [MK,MF] and eqang [0][HK,HF] = [NK,NP].
eqang [0][MK,MP] = [NK,NE]
       because circle[$KENM$].
eqang [0][KM,KO] = [NK,NE] = [MK,MP]
       because eqang [0][KM,KO] = [MK,MP] and eqang [0][MK,MP] = [NK,NE].
eqang [0][LK,LE] = [NK,NE]
       because circle[$KENL$].
eqang [0][MK,MP] = [KM,KO] = [NK,NE] = [LK,LE]
       because eqang [0][KM,KO] = [NK,NE] = [MK,MP] and eqang [0][LK,LE] = [NK,NE].
eqang [0][GP,GE] = [NK,NE]
       because circle[$KENG$].
eqang [0][LK,LE] = [KM,KO] = [MK,MP] = [NK,NE] = [GP,GE]
       because eqang [0][MK,MP] = [KM,KO] = [NK,NE] = [LK,LE] and eqang [0][GP,GE] = [NK,NE].
eqang [0][FK,FE] = [NK,NE]
       because circle[$KENF$].
eqang [0][GP,GE] = [MK,MP] = [KM,KO] = [LK,LE] = [NK,NE] = [FK,FE]
       because eqang [0][LK,LE] = [KM,KO] = [MK,MP] = [NK,NE] = [GP,GE] and eqang [0][FK,FE] = [NK,NE].
eqang [0][HK,HE] = [NK,NE]
       because circle[$KENH$].
eqang [0][FK,FE] = [LK,LE] = [KM,KO] = [MK,MP] = [GP,GE] = [NK,NE] = [HK,HE]
       because eqang [0][GP,GE] = [MK,MP] = [KM,KO] = [LK,LE] = [NK,NE] = [FK,FE] and eqang [0][HK,HE] = [NK,NE].
eqang [0][MK,MH] = [NK,NA]
       because circle[$KHNM$].
eqang [0][HK,HF] = [NK,NP] = [LK,LB] = [PM,BO] = [EA,EF] = [AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PL,PA] = [ML,PN] = [BP,OL] = [PL,NM] = [ON,LK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [PB,PN] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AD,ML] = [MK,MF] = [NA,NK] = [MH,MK]
       because eqang [0][MK,MF] = [AD,ML] = [EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PN] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [NA,NK] = [ON,LK] = [PL,NM] = [BP,OL] = [ML,PN] = [PL,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO] = [LK,LB] = [NK,NP] = [HK,HF] and eqang [0][MK,MH] = [NK,NA].
eqang [0][FK,FH] = [NK,NA]
       because circle[$KHNF$].
eqang [1][MH,MK] = [MK,MF] = [AD,ML] = [EH,EA] = [OM,GF] = [CD,CO] = [GH,GP] = [DA,DB] = [PB,PN] = [NK,OL] = [LM,LO] = [NM,BC] = [LP,LK] = [OA,OK] = [OK,OB] = [ON,AC] = [ON,LK] = [PL,NM] = [BP,OL] = [ML,PN] = [PL,PA] = [NO,NM] = [CA,CB] = [GP,GF] = [GH,OM] = [DO,DC] = [AO,PM] = [EA,EF] = [PM,BO] = [LK,LB] = [NK,NP] = [HK,HF] = [NA,NK] = [FH,FK]
       because eqang [0][HK,HF] = [NK,NP] = [LK,LB] = [PM,BO] = [EA,EF] = [AO,PM] = [DO,DC] = [GH,OM] = [GP,GF] = [CA,CB] = [NO,NM] = [PL,PA] = [ML,PN] = [BP,OL] = [PL,NM] = [ON,LK] = [ON,AC] = [OK,OB] = [OA,OK] = [LP,LK] = [NM,BC] = [LM,LO] = [NK,OL] = [PB,PN] = [DA,DB] = [GH,GP] = [CD,CO] = [OM,GF] = [EH,EA] = [AD,ML] = [MK,MF] = [NA,NK] = [MH,MK] and eqang [0][FK,FH] = [NK,NA].
eqang [0][MC,MF] = [NG,NP]
       because circle[$GFNM$].
eqang [0][LG,LB] = [NG,NP]
       because circle[$GFNL$].
eqang [0][MC,MF] = [NG,NP] = [LG,LB]
       because eqang [0][MC,MF] = [NG,NP] and eqang [0][LG,LB] = [NG,NP].
eqang [0][KP,KF] = [NG,NP]
       because circle[$GFNK$].
eqang [0][LG,LB] = [MC,MF] = [NG,NP] = [KP,KF]
       because eqang [0][MC,MF] = [NG,NP] = [LG,LB] and eqang [0][KP,KF] = [NG,NP].
eqang [0][EG,EF] = [NG,NP]
       because circle[$GFNE$].
eqang [0][KP,KF] = [MC,MF] = [LG,LB] = [NG,NP] = [EG,EF]
       because eqang [0][LG,LB] = [MC,MF] = [NG,NP] = [KP,KF] and eqang [0][EG,EF] = [NG,NP].
eqang [0][HG,HF] = [NG,NP]
       because circle[$GFNH$].
eqang [0][EG,EF] = [LG,LB] = [MC,MF] = [KP,KF] = [NG,NP] = [HG,HF]
       because eqang [0][KP,KF] = [MC,MF] = [LG,LB] = [NG,NP] = [EG,EF] and eqang [0][HG,HF] = [NG,NP].
eqang [0][MC,MP] = [NG,NE]
       because circle[$GENM$].
eqang [0][FG,FE] = [OC,OB] = [OD,OA] = [HG,HE] = [OM,AB] = [CD,OK] = [KP,KA] = [NG,NE] = [MC,MP]
       because eqang [0][KP,KA] = [CD,OK] = [MC,MP] = [OM,AB] = [HG,HE] = [OD,OA] = [OC,OB] = [FG,FE] and eqang [0][MC,MP] = [NG,NE].
eqang [0][LG,LE] = [NG,NE]
       because circle[$GENL$].
eqang [1][MC,MP] = [KP,KA] = [CD,OK] = [OM,AB] = [HG,HE] = [OD,OA] = [OC,OB] = [FG,FE] = [NG,NE] = [LG,LE]
       because eqang [0][FG,FE] = [OC,OB] = [OD,OA] = [HG,HE] = [OM,AB] = [CD,OK] = [KP,KA] = [NG,NE] = [MC,MP] and eqang [0][LG,LE] = [NG,NE].
eqang [0][MC,MH] = [NG,NA]
       because circle[$GHNM$].
eqang [0][LG,LP] = [NG,NA]
       because circle[$GHNL$].
eqang [0][MC,MH] = [NG,NA] = [LG,LP]
       because eqang [0][MC,MH] = [NG,NA] and eqang [0][LG,LP] = [NG,NA].
eqang [0][KP,KH] = [NG,NA]
       because circle[$GHNK$].
eqang [0][LG,LP] = [MC,MH] = [NG,NA] = [KP,KH]
       because eqang [0][MC,MH] = [NG,NA] = [LG,LP] and eqang [0][KP,KH] = [NG,NA].
eqang [0][FG,FH] = [NG,NA]
       because circle[$GHNF$].
eqang [0][KP,KH] = [MC,MH] = [LG,LP] = [NG,NA] = [FG,FH]
       because eqang [0][LG,LP] = [MC,MH] = [NG,NA] = [KP,KH] and eqang [0][FG,FH] = [NG,NA].
eqang [0][EG,EH] = [NG,NA]
       because circle[$GHNE$].
eqang [0][FG,FH] = [LG,LP] = [MC,MH] = [KP,KH] = [NG,NA] = [EG,EH]
       because eqang [0][KP,KH] = [MC,MH] = [LG,LP] = [NG,NA] = [FG,FH] and eqang [0][EG,EH] = [NG,NA].
eqang [0][MF,MP] = [NP,NE]
       because circle[$FENM$].
eqang [0][LB,LE] = [NP,NE]
       because circle[$FENL$].
eqang [0][MF,MP] = [NP,NE] = [LB,LE]
       because eqang [0][MF,MP] = [NP,NE] and eqang [0][LB,LE] = [NP,NE].
eqang [0][KF,KA] = [NP,NE]
       because circle[$FENK$].
eqang [0][LB,LE] = [MF,MP] = [NP,NE] = [KF,KA]
       because eqang [0][MF,MP] = [NP,NE] = [LB,LE] and eqang [0][KF,KA] = [NP,NE].
eqang [0][GF,GE] = [NP,NE]
       because circle[$FENG$].
eqang [0][KF,KA] = [MF,MP] = [LB,LE] = [NP,NE] = [GF,GE]
       because eqang [0][LB,LE] = [MF,MP] = [NP,NE] = [KF,KA] and eqang [0][GF,GE] = [NP,NE].
eqang [0][HF,HE] = [NP,NE]
       because circle[$FENH$].
eqang [0][GF,GE] = [LB,LE] = [MF,MP] = [KF,KA] = [NP,NE] = [HF,HE]
       because eqang [0][KF,KA] = [MF,MP] = [LB,LE] = [NP,NE] = [GF,GE] and eqang [0][HF,HE] = [NP,NE].
eqang [0][MF,MH] = [NP,NA]
       because circle[$FHNM$].
eqang [0][AD,OL] = [ON,BC] = [OD,OC] = [GH,GF] = [LP,LB] = [EH,EF] = [OA,OB] = [NA,NP] = [MH,MF]
       because eqang [0][OA,OB] = [EH,EF] = [LP,LB] = [GH,GF] = [OD,OC] = [LP,LB] = [NA,NP] = [ON,BC] = [NA,NP] = [AD,OL] and eqang [0][MF,MH] = [NP,NA].
eqang [0][KF,KH] = [NP,NA]
       because circle[$FHNK$].
eqang [1][MH,MF] = [OA,OB] = [EH,EF] = [LP,LB] = [GH,GF] = [OD,OC] = [ON,BC] = [AD,OL] = [NA,NP] = [KH,KF]
       because eqang [0][AD,OL] = [ON,BC] = [OD,OC] = [GH,GF] = [LP,LB] = [EH,EF] = [OA,OB] = [NA,NP] = [MH,MF] and eqang [0][KF,KH] = [NP,NA].
eqang [0][MP,MH] = [NE,NA]
       because circle[$EHNM$].
eqang [0][LE,LP] = [NE,NA]
       because circle[$EHNL$].
eqang [0][MP,MH] = [NE,NA] = [LE,LP]
       because eqang [0][MP,MH] = [NE,NA] and eqang [0][LE,LP] = [NE,NA].
eqang [0][KA,KH] = [NE,NA]
       because circle[$EHNK$].
eqang [0][LE,LP] = [MP,MH] = [NE,NA] = [KA,KH]
       because eqang [0][MP,MH] = [NE,NA] = [LE,LP] and eqang [0][KA,KH] = [NE,NA].
eqang [0][GE,GH] = [NE,NA]
       because circle[$EHNG$].
eqang [0][KA,KH] = [MP,MH] = [LE,LP] = [NE,NA] = [GE,GH]
       because eqang [0][LE,LP] = [MP,MH] = [NE,NA] = [KA,KH] and eqang [0][GE,GH] = [NE,NA].
eqang [0][FE,FH] = [NE,NA]
       because circle[$EHNF$].
eqang [0][GE,GH] = [LE,LP] = [MP,MH] = [KA,KH] = [NE,NA] = [FE,FH]
       because eqang [0][KA,KH] = [MP,MH] = [LE,LP] = [NE,NA] = [GE,GH] and eqang [0][FE,FH] = [NE,NA].
eqang [1][GL,GB] = [FD,FM]
       because $[MD,DF]=[GB,BL]$ and $[DM,MF]=[GL,LB]$.
eqang [1][FK,FA] = [EC,EL]
       because $[LC,CE]=[FA,AK]$ and $[CL,LE]=[FK,KA]$.
eqang [1][GN,GA] = [HC,HM]
       because $[MC,CH]=[GA,AN]$ and $[CM,MH]=[GN,NA]$.
eqang [1][HK,HB] = [ED,EN]
       because $[ED,DN]=[KB,BH]$ and $[DN,NE]=[HK,KB]$.
eqang [0][LG,LK] = [NG,ML]
       because perp[$ML,LK$] and perp[$NG,LG$].
eqang [0][HK,HG] = [NK,NG] = [FK,FG] = [MK,MC] = [EA,EG] = [ML,NG] = [LK,LG]
       because eqang [0][EA,EG] = [LK,LG] = [MK,MC] = [FK,FG] = [NK,NG] = [HK,HG] and eqang [0][LG,LK] = [NG,ML].
eqang [0][LG,LM] = [NG,LK]
       because perp[$ML,LK$] and perp[$NG,LG$].
eqang [0][HM,HG] = [NM,NG] = [FM,FG] = [KM,KP] = [MK,MO] = [EP,EG] = [LK,NG] = [LM,LG]
       because eqang [0][EP,EG] = [MK,MO] = [KM,KP] = [LM,LG] = [FM,FG] = [NM,NG] = [HM,HG] and eqang [0][LG,LM] = [NG,LK].
eqang [0][LG,AC] = [NG,ML]
       because perp[$ML,AC$] and perp[$NG,LG$].
eqang [0][LK,LG] = [EA,EG] = [MK,MC] = [FK,FG] = [NK,NG] = [HK,HG] = [ML,NG] = [AC,LG]
       because eqang [0][HK,HG] = [NK,NG] = [FK,FG] = [MK,MC] = [EA,EG] = [ML,NG] = [LK,LG] and eqang [0][LG,AC] = [NG,ML].
eqang [0][LG,LM] = [NG,AC]
       because perp[$ML,AC$] and perp[$NG,LG$].
eqang [0][LK,NG] = [EP,EG] = [MK,MO] = [KM,KP] = [FM,FG] = [NM,NG] = [HM,HG] = [AC,NG] = [LM,LG]
       because eqang [0][HM,HG] = [NM,NG] = [FM,FG] = [KM,KP] = [MK,MO] = [EP,EG] = [LK,NG] = [LM,LG] and eqang [0][LG,LM] = [NG,AC].
eqang [0][LG,NM] = [NG,ML]
       because perp[$ML,NM$] and perp[$NG,LG$].
eqang [0][AC,LG] = [HK,HG] = [NK,NG] = [FK,FG] = [MK,MC] = [EA,EG] = [LK,LG] = [ML,NG] = [NM,LG]
       because eqang [0][LK,LG] = [EA,EG] = [MK,MC] = [FK,FG] = [NK,NG] = [HK,HG] = [ML,NG] = [AC,LG] and eqang [0][LG,NM] = [NG,ML].
eqang [0][LG,LK] = [NG,BP]
       because perp[$BP,LK$] and perp[$NG,LG$].
eqang [0][NM,LG] = [ML,NG] = [EA,EG] = [MK,MC] = [FK,FG] = [NK,NG] = [HK,HG] = [AC,LG] = [BP,NG] = [LK,LG]
       because eqang [0][AC,LG] = [HK,HG] = [NK,NG] = [FK,FG] = [MK,MC] = [EA,EG] = [LK,LG] = [ML,NG] = [NM,LG] and eqang [0][LG,LK] = [NG,BP].
eqang [0][LG,BP] = [NG,LK]
       because perp[$BP,LK$] and perp[$NG,LG$].
eqang [0][LM,LG] = [AC,NG] = [HM,HG] = [NM,NG] = [FM,FG] = [KM,KP] = [MK,MO] = [EP,EG] = [LK,NG] = [BP,LG]
       because eqang [0][LK,NG] = [EP,EG] = [MK,MO] = [KM,KP] = [FM,FG] = [NM,NG] = [HM,HG] = [AC,NG] = [LM,LG] and eqang [0][LG,BP] = [NG,LK].
eqang [0][LG,NK] = [NG,LK]
       because perp[$NK,LK$] and perp[$NG,LG$].
eqang [0][BP,LG] = [EP,EG] = [MK,MO] = [KM,KP] = [FM,FG] = [NM,NG] = [HM,HG] = [AC,NG] = [LM,LG] = [LK,NG] = [NK,LG]
       because eqang [0][LM,LG] = [AC,NG] = [HM,HG] = [NM,NG] = [FM,FG] = [KM,KP] = [MK,MO] = [EP,EG] = [LK,NG] = [BP,LG] and eqang [0][LG,NK] = [NG,LK].
eqang [0][LG,AD] = [NG,PL]
       because perp[$AD,PL$] and perp[$NG,LG$].
eqang [0][LG,ON] = [NG,NA]
       because perp[$AD,ON$] and perp[$NG,LG$].
eqang [0][EG,EH] = [KP,KH] = [MC,MH] = [LG,LP] = [FG,FH] = [NG,NA] = [LG,ON]
       because eqang [0][FG,FH] = [LG,LP] = [MC,MH] = [KP,KH] = [NG,NA] = [EG,EH] and eqang [0][LG,ON] = [NG,NA].
eqang [0][LG,AD] = [NG,NO]
       because perp[$AD,ON$] and perp[$NG,LG$].
eqang [1][NG,PL] = [NG,NO] = [LG,AD]
       because eqang [0][LG,AD] = [NG,PL] and eqang [0][LG,AD] = [NG,NO].
eqang [0][LG,PN] = [NG,BC]
       because perp[$BC,PN$] and perp[$NG,LG$].
eqang [0][LG,LO] = [NG,BC]
       because perp[$BC,OL$] and perp[$NG,LG$].
eqang [1][LG,PN] = [NG,BC] = [LG,LO]
       because eqang [0][LG,PN] = [NG,BC] and eqang [0][LG,LO] = [NG,BC].
eqang [0][LG,LB] = [NG,OL]
       because perp[$BC,OL$] and perp[$NG,LG$].
eqang [0][HG,HF] = [NG,NP] = [KP,KF] = [MC,MF] = [EG,EF] = [NG,OL] = [LG,LB]
       because eqang [0][EG,EF] = [LG,LB] = [MC,MF] = [KP,KF] = [NG,NP] = [HG,HF] and eqang [0][LG,LB] = [NG,OL].
eqang [0][LG,OD] = [GN,GH]
       because perp[$GH,OD$] and perp[$NG,LG$].
eqang [0][EN,EH] = [MN,MH] = [LN,LP] = [NL,NO] = [KN,KH] = [FP,FH] = [GN,GH] = [LG,OD]
       because eqang [0][FP,FH] = [KN,KH] = [NL,NO] = [LN,LP] = [GN,GH] = [MN,MH] = [EN,EH] and eqang [0][LG,OD] = [GN,GH].
eqang [0][GL,GH] = [NG,OD]
       because perp[$GH,OD$] and perp[$NG,LG$].
eqang [0][EL,EH] = [NL,NA] = [ML,MH] = [KL,KH] = [FB,FH] = [NG,OD] = [GL,GH]
       because eqang [0][FB,FH] = [KL,KH] = [ML,MH] = [GL,GH] = [NL,NA] = [EL,EH] and eqang [0][GL,GH] = [NG,OD].
eqang [0][LG,OM] = [GN,GC]
       because perp[$CD,OM$] and perp[$NG,LG$].
eqang [0][HP,HK] = [NL,NK] = [FB,FK] = [HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [EL,EA] = [GN,GC] = [LG,OM]
       because eqang [0][EL,EA] = [GL,GP] = [EN,EP] = [GN,GC] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] = [FB,FK] = [NL,NK] = [HP,HK] and eqang [0][LG,OM] = [GN,GC].
eqang [0][GL,GC] = [NG,OM]
       because perp[$CD,OM$] and perp[$NG,LG$].
eqang [0][HM,HP] = [NM,NL] = [FM,FB] = [HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EA,EN] = [EP,EL] = [OM,NG] = [GC,GL]
       because eqang [0][EP,EL] = [GC,GL] = [EA,EN] = [GP,GN] = [KM,KL] = [LK,LN] = [FK,FP] = [MK,MN] = [HK,HA] = [FM,FB] = [NM,NL] = [HM,HP] and eqang [0][GL,GC] = [NG,OM].
eqang [1][LG,AO] = [NG,EH]
       because perp[$EH,AO$] and perp[$NG,LG$].
eqang [1][LG,EH] = [NG,AO]
       because perp[$EH,AO$] and perp[$NG,LG$].
eqang [0][LG,PM] = [NG,AB]
       because perp[$AB,PM$] and perp[$NG,LG$].
eqang [0][LG,AB] = [NG,PM]
       because perp[$AB,PM$] and perp[$NG,LG$].
eqang [0][LG,OK] = [NG,AB]
       because perp[$AB,OK$] and perp[$NG,LG$].
eqang [1][LG,PM] = [NG,AB] = [LG,OK]
       because eqang [0][LG,PM] = [NG,AB] and eqang [0][LG,OK] = [NG,AB].
eqang [0][LG,AB] = [NG,OK]
       because perp[$AB,OK$] and perp[$NG,LG$].
eqang [1][NG,PM] = [NG,OK] = [LG,AB]
       because eqang [0][LG,AB] = [NG,PM] and eqang [0][LG,AB] = [NG,OK].
eqang [1][LG,FE] = [NG,BO]
       because perp[$BO,FE$] and perp[$NG,LG$].
eqang [1][LG,BO] = [NG,FE]
       because perp[$BO,FE$] and perp[$NG,LG$].
eqang [0][GL,GF] = [NG,CO]
       because perp[$CO,GF$] and perp[$NG,LG$].
eqang [0][HP,HF] = [NL,NP] = [ML,MF] = [NL,NP] = [LN,LO] = [KL,KF] = [EL,EF] = [NG,CO] = [GL,GF]
       because eqang [0][EL,EF] = [KL,KF] = [LN,LO] = [NL,NP] = [ML,MF] = [GL,GF] = [NL,NP] = [HP,HF] and eqang [0][GL,GF] = [NG,CO].
eqang [0][LG,CO] = [GN,GF]
       because perp[$CO,GF$] and perp[$NG,LG$].
eqang [0][HA,HF] = [MN,MF] = [LN,LB] = [KN,KF] = [EN,EF] = [GN,GF] = [LG,CO]
       because eqang [0][EN,EF] = [KN,KF] = [LN,LB] = [GN,GF] = [MN,MF] = [HA,HF] and eqang [0][LG,CO] = [GN,GF].
eqang [1][LG,NE] = [NG,LE]
       because perp[$NE,LE$] and perp[$NG,LG$].
eqang [1][LG,MF] = [NG,KF]
       because perp[$KF,MF$] and perp[$NG,LG$].
eqang [1][LG,KF] = [NG,MF]
       because perp[$KF,MF$] and perp[$NG,LG$].
eqang [1][LG,MH] = [NG,KH]
       because perp[$KH,MH$] and perp[$NG,LG$].
eqang [1][LG,KH] = [NG,MH]
       because perp[$KH,MH$] and perp[$NG,LG$].
eqang [1][LG,FH] = [NG,MK]
       because perp[$MK,FH$] and perp[$NG,LG$].
eqang [1][LG,MK] = [NG,FH]
       because perp[$MK,FH$] and perp[$NG,LG$].
eqang [0][LE,LK] = [NE,ML]
       because perp[$ML,LK$] and perp[$NE,LE$].
eqang [0][HK,HE] = [NK,NE] = [GP,GE] = [MK,MP] = [KM,KO] = [FK,FE] = [ML,NE] = [LK,LE]
       because eqang [0][FK,FE] = [LK,LE] = [KM,KO] = [MK,MP] = [GP,GE] = [NK,NE] = [HK,HE] and eqang [0][LE,LK] = [NE,ML].
eqang [0][LE,LM] = [NE,LK]
       because perp[$ML,LK$] and perp[$NE,LE$].
eqang [0][HM,HE] = [NM,NE] = [GC,GE] = [KM,KA] = [FM,FE] = [LK,NE] = [LM,LE]
       because eqang [0][FM,FE] = [KM,KA] = [LM,LE] = [GC,GE] = [NM,NE] = [HM,HE] and eqang [0][LE,LM] = [NE,LK].
eqang [0][LE,AC] = [NE,ML]
       because perp[$ML,AC$] and perp[$NE,LE$].
eqang [0][LK,LE] = [FK,FE] = [KM,KO] = [MK,MP] = [GP,GE] = [NK,NE] = [HK,HE] = [ML,NE] = [AC,LE]
       because eqang [0][HK,HE] = [NK,NE] = [GP,GE] = [MK,MP] = [KM,KO] = [FK,FE] = [ML,NE] = [LK,LE] and eqang [0][LE,AC] = [NE,ML].
eqang [0][LE,LM] = [NE,AC]
       because perp[$ML,AC$] and perp[$NE,LE$].
eqang [0][LK,NE] = [FM,FE] = [KM,KA] = [GC,GE] = [NM,NE] = [HM,HE] = [AC,NE] = [LM,LE]
       because eqang [0][HM,HE] = [NM,NE] = [GC,GE] = [KM,KA] = [FM,FE] = [LK,NE] = [LM,LE] and eqang [0][LE,LM] = [NE,AC].
eqang [0][LE,NM] = [NE,ML]
       because perp[$ML,NM$] and perp[$NE,LE$].
eqang [0][AC,LE] = [HK,HE] = [NK,NE] = [GP,GE] = [MK,MP] = [KM,KO] = [FK,FE] = [LK,LE] = [ML,NE] = [NM,LE]
       because eqang [0][LK,LE] = [FK,FE] = [KM,KO] = [MK,MP] = [GP,GE] = [NK,NE] = [HK,HE] = [ML,NE] = [AC,LE] and eqang [0][LE,NM] = [NE,ML].
eqang [0][LE,LK] = [NE,BP]
       because perp[$BP,LK$] and perp[$NE,LE$].
eqang [0][NM,LE] = [ML,NE] = [FK,FE] = [KM,KO] = [MK,MP] = [GP,GE] = [NK,NE] = [HK,HE] = [AC,LE] = [BP,NE] = [LK,LE]
       because eqang [0][AC,LE] = [HK,HE] = [NK,NE] = [GP,GE] = [MK,MP] = [KM,KO] = [FK,FE] = [LK,LE] = [ML,NE] = [NM,LE] and eqang [0][LE,LK] = [NE,BP].
eqang [0][LE,BP] = [NE,LK]
       because perp[$BP,LK$] and perp[$NE,LE$].
eqang [0][LM,LE] = [AC,NE] = [HM,HE] = [NM,NE] = [GC,GE] = [KM,KA] = [FM,FE] = [LK,NE] = [BP,LE]
       because eqang [0][LK,NE] = [FM,FE] = [KM,KA] = [GC,GE] = [NM,NE] = [HM,HE] = [AC,NE] = [LM,LE] and eqang [0][LE,BP] = [NE,LK].
eqang [0][LE,NK] = [NE,LK]
       because perp[$NK,LK$] and perp[$NE,LE$].
eqang [0][BP,LE] = [FM,FE] = [KM,KA] = [GC,GE] = [NM,NE] = [HM,HE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE]
       because eqang [0][LM,LE] = [AC,NE] = [HM,HE] = [NM,NE] = [GC,GE] = [KM,KA] = [FM,FE] = [LK,NE] = [BP,LE] and eqang [0][LE,NK] = [NE,LK].
eqang [0][LE,AD] = [NE,PL]
       because perp[$AD,PL$] and perp[$NE,LE$].
eqang [0][LE,ON] = [NE,NA]
       because perp[$AD,ON$] and perp[$NE,LE$].
eqang [0][FE,FH] = [KA,KH] = [MP,MH] = [LE,LP] = [GE,GH] = [NE,NA] = [LE,ON]
       because eqang [0][GE,GH] = [LE,LP] = [MP,MH] = [KA,KH] = [NE,NA] = [FE,FH] and eqang [0][LE,ON] = [NE,NA].
eqang [0][LE,AD] = [NE,NO]
       because perp[$AD,ON$] and perp[$NE,LE$].
eqang [1][NE,PL] = [NE,NO] = [LE,AD]
       because eqang [0][LE,AD] = [NE,PL] and eqang [0][LE,AD] = [NE,NO].
eqang [0][LE,PN] = [NE,BC]
       because perp[$BC,PN$] and perp[$NE,LE$].
eqang [0][LE,LO] = [NE,BC]
       because perp[$BC,OL$] and perp[$NE,LE$].
eqang [1][LE,PN] = [NE,BC] = [LE,LO]
       because eqang [0][LE,PN] = [NE,BC] and eqang [0][LE,LO] = [NE,BC].
eqang [0][LE,LB] = [NE,OL]
       because perp[$BC,OL$] and perp[$NE,LE$].
eqang [0][HF,HE] = [NP,NE] = [KF,KA] = [MF,MP] = [GF,GE] = [OL,NE] = [LB,LE]
       because eqang [0][GF,GE] = [LB,LE] = [MF,MP] = [KF,KA] = [NP,NE] = [HF,HE] and eqang [0][LE,LB] = [NE,OL].
eqang [1][LE,OD] = [NE,GH]
       because perp[$GH,OD$] and perp[$NE,LE$].
eqang [1][LE,GH] = [NE,OD]
       because perp[$GH,OD$] and perp[$NE,LE$].
eqang [0][LE,PK] = [NE,CD]
       because perp[$CD,PK$] and perp[$NE,LE$].
eqang [0][LE,CD] = [NE,PK]
       because perp[$CD,PK$] and perp[$NE,LE$].
eqang [0][LE,OM] = [NE,CD]
       because perp[$CD,OM$] and perp[$NE,LE$].
eqang [1][LE,PK] = [NE,CD] = [LE,OM]
       because eqang [0][LE,PK] = [NE,CD] and eqang [0][LE,OM] = [NE,CD].
eqang [0][LE,CD] = [NE,OM]
       because perp[$CD,OM$] and perp[$NE,LE$].
eqang [1][NE,PK] = [NE,OM] = [LE,CD]
       because eqang [0][LE,CD] = [NE,PK] and eqang [0][LE,CD] = [NE,OM].
eqang [0][LE,AO] = [EN,EH]
       because perp[$EH,AO$] and perp[$NE,LE$].
eqang [0][LG,OD] = [GN,GH] = [FP,FH] = [KN,KH] = [NL,NO] = [LN,LP] = [MN,MH] = [EN,EH] = [LE,AO]
       because eqang [0][EN,EH] = [MN,MH] = [LN,LP] = [NL,NO] = [KN,KH] = [FP,FH] = [GN,GH] = [LG,OD] and eqang [0][LE,AO] = [EN,EH].
eqang [0][EL,EH] = [NE,AO]
       because perp[$EH,AO$] and perp[$NE,LE$].
eqang [0][GL,GH] = [NG,OD] = [FB,FH] = [KL,KH] = [ML,MH] = [NL,NA] = [NE,AO] = [EL,EH]
       because eqang [0][EL,EH] = [NL,NA] = [ML,MH] = [KL,KH] = [FB,FH] = [NG,OD] = [GL,GH] and eqang [0][EL,EH] = [NE,AO].
eqang [0][LE,OK] = [EN,EA]
       because perp[$AB,OK$] and perp[$NE,LE$].
eqang [0][GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [LK,LN] = [FK,FP] = [MK,MN] = [HK,HA] = [FM,FB] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE]
       because eqang [0][HM,HP] = [NM,NL] = [FM,FB] = [HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EA,EN] = [EP,EL] = [OM,NG] = [GC,GL] and eqang [0][LE,OK] = [EN,EA].
eqang [0][EL,EA] = [NE,OK]
       because perp[$AB,OK$] and perp[$NE,LE$].
eqang [0][LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] = [FB,FK] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA]
       because eqang [0][HP,HK] = [NL,NK] = [FB,FK] = [HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [EL,EA] = [GN,GC] = [LG,OM] and eqang [0][EL,EA] = [NE,OK].
eqang [0][EL,EF] = [NE,BO]
       because perp[$BO,FE$] and perp[$NE,LE$].
eqang [0][GL,GF] = [NG,CO] = [KL,KF] = [LN,LO] = [NL,NP] = [ML,MF] = [NL,NP] = [HP,HF] = [NE,BO] = [EL,EF]
       because eqang [0][HP,HF] = [NL,NP] = [ML,MF] = [NL,NP] = [LN,LO] = [KL,KF] = [EL,EF] = [NG,CO] = [GL,GF] and eqang [0][EL,EF] = [NE,BO].
eqang [0][LE,BO] = [EN,EF]
       because perp[$BO,FE$] and perp[$NE,LE$].
eqang [0][LG,CO] = [GN,GF] = [KN,KF] = [LN,LB] = [MN,MF] = [HA,HF] = [EN,EF] = [LE,BO]
       because eqang [0][HA,HF] = [MN,MF] = [LN,LB] = [KN,KF] = [EN,EF] = [GN,GF] = [LG,CO] and eqang [0][LE,BO] = [EN,EF].
eqang [1][LE,GF] = [NE,CO]
       because perp[$CO,GF$] and perp[$NE,LE$].
eqang [1][LE,CO] = [NE,GF]
       because perp[$CO,GF$] and perp[$NE,LE$].
eqang [1][LE,MF] = [NE,KF]
       because perp[$KF,MF$] and perp[$NE,LE$].
eqang [1][LE,KF] = [NE,MF]
       because perp[$KF,MF$] and perp[$NE,LE$].
eqang [1][LE,MH] = [NE,KH]
       because perp[$KH,MH$] and perp[$NE,LE$].
eqang [1][LE,KH] = [NE,MH]
       because perp[$KH,MH$] and perp[$NE,LE$].
eqang [1][LE,FH] = [NE,MK]
       because perp[$MK,FH$] and perp[$NE,LE$].
eqang [1][LE,MK] = [NE,FH]
       because perp[$MK,FH$] and perp[$NE,LE$].
eqang [0][MF,LK] = [KF,ML]
       because perp[$ML,LK$] and perp[$KF,MF$].
eqang [0][MF,AC] = [KF,ML]
       because perp[$ML,AC$] and perp[$KF,MF$].
eqang [0][MF,LK] = [KF,ML] = [MF,AC]
       because eqang [0][MF,LK] = [KF,ML] and eqang [0][MF,AC] = [KF,ML].
eqang [0][MF,ML] = [KF,AC]
       because perp[$ML,AC$] and perp[$KF,MF$].
eqang [0][EL,EF] = [NE,BO] = [HP,HF] = [NL,NP] = [NL,NP] = [LN,LO] = [KL,KF] = [NG,CO] = [GL,GF] = [AC,KF] = [ML,MF]
       because eqang [0][GL,GF] = [NG,CO] = [KL,KF] = [LN,LO] = [NL,NP] = [ML,MF] = [NL,NP] = [HP,HF] = [NE,BO] = [EL,EF] and eqang [0][MF,ML] = [KF,AC].
eqang [0][MF,MN] = [KF,ML]
       because perp[$ML,NM$] and perp[$KF,MF$].
eqang [0][LE,BO] = [EN,EF] = [HA,HF] = [LN,LB] = [KN,KF] = [GN,GF] = [LG,CO] = [ML,KF] = [MN,MF]
       because eqang [0][LG,CO] = [GN,GF] = [KN,KF] = [LN,LB] = [MN,MF] = [HA,HF] = [EN,EF] = [LE,BO] and eqang [0][MF,MN] = [KF,ML].
eqang [0][MF,AC] = [MF,LK] = [MF,MN] = [KF,ML] = [CO,LG] = [GF,GN] = [KF,KN] = [LB,LN] = [HF,HA] = [EF,EN] = [BO,LE]
       because eqang [0][MF,LK] = [KF,ML] = [MF,AC] and eqang [0][LE,BO] = [EN,EF] = [HA,HF] = [LN,LB] = [KN,KF] = [GN,GF] = [LG,CO] = [ML,KF] = [MN,MF].
eqang [0][MF,ML] = [KF,NM]
       because perp[$ML,NM$] and perp[$KF,MF$].
eqang [0][AC,KF] = [GL,GF] = [NG,CO] = [KL,KF] = [LN,LO] = [NL,NP] = [NL,NP] = [HP,HF] = [NE,BO] = [EL,EF] = [NM,KF] = [ML,MF]
       because eqang [0][EL,EF] = [NE,BO] = [HP,HF] = [NL,NP] = [NL,NP] = [LN,LO] = [KL,KF] = [NG,CO] = [GL,GF] = [AC,KF] = [ML,MF] and eqang [0][MF,ML] = [KF,NM].
eqang [0][MF,LK] = [KF,BP]
       because perp[$BP,LK$] and perp[$KF,MF$].
eqang [0][BO,LE] = [EF,EN] = [HF,HA] = [LB,LN] = [KF,KN] = [GF,GN] = [CO,LG] = [KF,ML] = [MF,MN] = [MF,AC] = [KF,BP] = [MF,LK]
       because eqang [0][MF,AC] = [MF,LK] = [MF,MN] = [KF,ML] = [CO,LG] = [GF,GN] = [KF,KN] = [LB,LN] = [HF,HA] = [EF,EN] = [BO,LE] and eqang [0][MF,LK] = [KF,BP].
eqang [0][MF,BP] = [KF,KL]
       because perp[$BP,LK$] and perp[$KF,MF$].
eqang [0][ML,MF] = [NM,KF] = [EL,EF] = [NE,BO] = [HP,HF] = [NL,NP] = [NL,NP] = [LN,LO] = [NG,CO] = [GL,GF] = [AC,KF] = [KL,KF] = [BP,MF]
       because eqang [0][AC,KF] = [GL,GF] = [NG,CO] = [KL,KF] = [LN,LO] = [NL,NP] = [NL,NP] = [HP,HF] = [NE,BO] = [EL,EF] = [NM,KF] = [ML,MF] and eqang [0][MF,BP] = [KF,KL].
eqang [0][MF,NK] = [KF,KL]
       because perp[$NK,LK$] and perp[$KF,MF$].
eqang [0][BP,MF] = [AC,KF] = [GL,GF] = [NG,CO] = [LN,LO] = [NL,NP] = [NL,NP] = [HP,HF] = [NE,BO] = [EL,EF] = [NM,KF] = [ML,MF] = [KL,KF] = [NK,MF]
       because eqang [0][ML,MF] = [NM,KF] = [EL,EF] = [NE,BO] = [HP,HF] = [NL,NP] = [NL,NP] = [LN,LO] = [NG,CO] = [GL,GF] = [AC,KF] = [KL,KF] = [BP,MF] and eqang [0][MF,NK] = [KF,KL].
eqang [0][MF,PL] = [KF,AD]
       because perp[$AD,PL$] and perp[$KF,MF$].
eqang [0][MF,AD] = [KF,PL]
       because perp[$AD,PL$] and perp[$KF,MF$].
eqang [0][MF,ON] = [KF,AD]
       because perp[$AD,ON$] and perp[$KF,MF$].
eqang [1][MF,PL] = [KF,AD] = [MF,ON]
       because eqang [0][MF,PL] = [KF,AD] and eqang [0][MF,ON] = [KF,AD].
eqang [0][MF,AD] = [KF,ON]
       because perp[$AD,ON$] and perp[$KF,MF$].
eqang [1][KF,PL] = [KF,ON] = [MF,AD]
       because eqang [0][MF,AD] = [KF,PL] and eqang [0][MF,AD] = [KF,ON].
eqang [0][MF,OL] = [FK,FB]
       because perp[$BC,OL$] and perp[$KF,MF$].
eqang [0][EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF]
       because eqang [0][LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [LN,LM] = [HA,HM] = [FB,FK] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] and eqang [0][MF,OL] = [FK,FB].
eqang [0][FM,FB] = [KF,OL]
       because perp[$BC,OL$] and perp[$KF,MF$].
eqang [0][OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB]
       because eqang [0][GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [LK,LN] = [FK,FP] = [MK,MN] = [HK,HA] = [FM,FB] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] and eqang [0][FM,FB] = [KF,OL].
eqang [1][MF,OD] = [KF,GH]
       because perp[$GH,OD$] and perp[$KF,MF$].
eqang [1][MF,GH] = [KF,OD]
       because perp[$GH,OD$] and perp[$KF,MF$].
eqang [0][MF,PK] = [KF,CD]
       because perp[$CD,PK$] and perp[$KF,MF$].
eqang [0][MF,MO] = [KF,CD]
       because perp[$CD,OM$] and perp[$KF,MF$].
eqang [1][MF,PK] = [KF,CD] = [MF,MO]
       because eqang [0][MF,PK] = [KF,CD] and eqang [0][MF,MO] = [KF,CD].
eqang [0][MF,MC] = [KF,OM]
       because perp[$CD,OM$] and perp[$KF,MF$].
eqang [0][LG,LB] = [NG,OL] = [EG,EF] = [KP,KF] = [NG,NP] = [HG,HF] = [OM,KF] = [MC,MF]
       because eqang [0][HG,HF] = [NG,NP] = [KP,KF] = [MC,MF] = [EG,EF] = [NG,OL] = [LG,LB] and eqang [0][MF,MC] = [KF,OM].
eqang [1][MF,AO] = [KF,EH]
       because perp[$EH,AO$] and perp[$KF,MF$].
eqang [1][MF,EH] = [KF,AO]
       because perp[$EH,AO$] and perp[$KF,MF$].
eqang [0][MF,AB] = [KF,PM]
       because perp[$AB,PM$] and perp[$KF,MF$].
eqang [0][MF,OK] = [KF,KA]
       because perp[$AB,OK$] and perp[$KF,MF$].
eqang [0][LB,LE] = [OL,NE] = [GF,GE] = [MF,MP] = [NP,NE] = [HF,HE] = [KF,KA] = [MF,OK]
       because eqang [0][HF,HE] = [NP,NE] = [KF,KA] = [MF,MP] = [GF,GE] = [OL,NE] = [LB,LE] and eqang [0][MF,OK] = [KF,KA].
eqang [0][MF,AB] = [KF,KO]
       because perp[$AB,OK$] and perp[$KF,MF$].
eqang [1][KF,PM] = [KF,KO] = [MF,AB]
       because eqang [0][MF,AB] = [KF,PM] and eqang [0][MF,AB] = [KF,KO].
eqang [0][FM,FE] = [KF,BO]
       because perp[$BO,FE$] and perp[$KF,MF$].
eqang [0][NK,LE] = [LK,NE] = [LM,LE] = [AC,NE] = [HM,HE] = [NM,NE] = [GC,GE] = [KM,KA] = [BP,LE] = [KF,BO] = [FM,FE]
       because eqang [0][BP,LE] = [FM,FE] = [KM,KA] = [GC,GE] = [NM,NE] = [HM,HE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE] and eqang [0][FM,FE] = [KF,BO].
eqang [0][MF,BO] = [FK,FE]
       because perp[$BO,FE$] and perp[$KF,MF$].
eqang [0][LK,LE] = [BP,NE] = [AC,LE] = [HK,HE] = [NK,NE] = [GP,GE] = [MK,MP] = [KM,KO] = [ML,NE] = [NM,LE] = [FK,FE] = [MF,BO]
       because eqang [0][NM,LE] = [ML,NE] = [FK,FE] = [KM,KO] = [MK,MP] = [GP,GE] = [NK,NE] = [HK,HE] = [AC,LE] = [BP,NE] = [LK,LE] and eqang [0][MF,BO] = [FK,FE].
eqang [0][FM,FG] = [KF,CO]
       because perp[$CO,GF$] and perp[$KF,MF$].
eqang [0][NK,LG] = [LK,NG] = [LM,LG] = [AC,NG] = [HM,HG] = [NM,NG] = [KM,KP] = [MK,MO] = [EP,EG] = [BP,LG] = [KF,CO] = [FM,FG]
       because eqang [0][BP,LG] = [EP,EG] = [MK,MO] = [KM,KP] = [FM,FG] = [NM,NG] = [HM,HG] = [AC,NG] = [LM,LG] = [LK,NG] = [NK,LG] and eqang [0][FM,FG] = [KF,CO].
eqang [0][MF,CO] = [FK,FG]
       because perp[$CO,GF$] and perp[$KF,MF$].
eqang [0][LK,LG] = [BP,NG] = [AC,LG] = [HK,HG] = [NK,NG] = [MK,MC] = [EA,EG] = [ML,NG] = [NM,LG] = [FK,FG] = [MF,CO]
       because eqang [0][NM,LG] = [ML,NG] = [EA,EG] = [MK,MC] = [FK,FG] = [NK,NG] = [HK,HG] = [AC,LG] = [BP,NG] = [LK,LG] and eqang [0][MF,CO] = [FK,FG].
eqang [1][MF,GE] = [KF,NL]
       because perp[$NL,GE$] and perp[$KF,MF$].
eqang [1][MF,NL] = [KF,GE]
       because perp[$NL,GE$] and perp[$KF,MF$].
eqang [1][MF,KH] = [KF,MH]
       because perp[$KH,MH$] and perp[$KF,MF$].
eqang [0][GE,LK] = [LN,LM]
       because perp[$ML,LK$] and perp[$NL,GE$].
eqang [0][OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [HA,HM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,LK]
       because eqang [0][EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [HA,HM] = [LN,LM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] and eqang [0][GE,LK] = [LN,LM].
eqang [0][GE,ML] = [LN,LK]
       because perp[$ML,LK$] and perp[$NL,GE$].
eqang [0][FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [FK,FP] = [MK,MN] = [HK,HA] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [LK,LN] = [ML,GE]
       because eqang [0][OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [HK,HA] = [MK,MN] = [FK,FP] = [LK,LN] = [KM,KL] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] and eqang [0][GE,ML] = [LN,LK].
eqang [0][GE,AC] = [LN,LM]
       because perp[$ML,AC$] and perp[$NL,GE$].
eqang [0][GE,LK] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [HA,HM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [LN,LM] = [GE,AC]
       because eqang [0][OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [HA,HM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,LK] and eqang [0][GE,AC] = [LN,LM].
eqang [0][GE,ML] = [NL,AC]
       because perp[$ML,AC$] and perp[$NL,GE$].
eqang [0][LK,LN] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [HK,HA] = [MK,MN] = [FK,FP] = [KM,KL] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE]
       because eqang [0][FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [FK,FP] = [MK,MN] = [HK,HA] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [LK,LN] = [ML,GE] and eqang [0][GE,ML] = [NL,AC].
eqang [0][GE,NM] = [LN,LM]
       because perp[$ML,NM$] and perp[$NL,GE$].
eqang [0][GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [HA,HM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [GE,LK] = [LN,LM] = [GE,NM]
       because eqang [0][GE,LK] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [HA,HM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [LN,LM] = [GE,AC] and eqang [0][GE,NM] = [LN,LM].
eqang [0][GE,LK] = [NL,BP]
       because perp[$BP,LK$] and perp[$NL,GE$].
eqang [0][GE,NM] = [LN,LM] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [HA,HM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [GE,AC] = [NL,BP] = [GE,LK]
       because eqang [0][GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [HA,HM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [GE,LK] = [LN,LM] = [GE,NM] and eqang [0][GE,LK] = [NL,BP].
eqang [0][GE,BP] = [LN,LK]
       because perp[$BP,LK$] and perp[$NL,GE$].
eqang [0][ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [FK,FP] = [MK,MN] = [HK,HA] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [LK,LN] = [BP,GE]
       because eqang [0][LK,LN] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [HK,HA] = [MK,MN] = [FK,FP] = [KM,KL] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] and eqang [0][GE,BP] = [LN,LK].
eqang [0][GE,NK] = [LN,LK]
       because perp[$NK,LK$] and perp[$NL,GE$].
eqang [0][BP,GE] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [HK,HA] = [MK,MN] = [FK,FP] = [KM,KL] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] = [LK,LN] = [NK,GE]
       because eqang [0][ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [FK,FP] = [MK,MN] = [HK,HA] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [LK,LN] = [BP,GE] and eqang [0][GE,NK] = [LN,LK].
eqang [0][GE,PL] = [NL,NA]
       because perp[$AD,PL$] and perp[$NL,GE$].
eqang [0][EL,EH] = [NE,AO] = [ML,MH] = [KL,KH] = [FB,FH] = [NG,OD] = [GL,GH] = [NL,NA] = [GE,PL]
       because eqang [0][GL,GH] = [NG,OD] = [FB,FH] = [KL,KH] = [ML,MH] = [NL,NA] = [NE,AO] = [EL,EH] and eqang [0][GE,PL] = [NL,NA].
eqang [0][GE,AD] = [LN,LP]
       because perp[$AD,PL$] and perp[$NL,GE$].
eqang [0][LE,AO] = [EN,EH] = [MN,MH] = [NL,NO] = [KN,KH] = [FP,FH] = [GN,GH] = [LG,OD] = [LN,LP] = [GE,AD]
       because eqang [0][LG,OD] = [GN,GH] = [FP,FH] = [KN,KH] = [NL,NO] = [LN,LP] = [MN,MH] = [EN,EH] = [LE,AO] and eqang [0][GE,AD] = [LN,LP].
eqang [0][GE,ON] = [NL,NA]
       because perp[$AD,ON$] and perp[$NL,GE$].
eqang [0][GE,PL] = [GL,GH] = [NG,OD] = [FB,FH] = [KL,KH] = [ML,MH] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON]
       because eqang [0][EL,EH] = [NE,AO] = [ML,MH] = [KL,KH] = [FB,FH] = [NG,OD] = [GL,GH] = [NL,NA] = [GE,PL] and eqang [0][GE,ON] = [NL,NA].
eqang [0][GE,PN] = [LN,LB]
       because perp[$BC,PN$] and perp[$NL,GE$].
eqang [0][MF,LK] = [KF,BP] = [MF,AC] = [MF,MN] = [KF,ML] = [CO,LG] = [GF,GN] = [KF,KN] = [HF,HA] = [EF,EN] = [BO,LE] = [LB,LN] = [PN,GE]
       because eqang [0][BO,LE] = [EF,EN] = [HF,HA] = [LB,LN] = [KF,KN] = [GF,GN] = [CO,LG] = [KF,ML] = [MF,MN] = [MF,AC] = [KF,BP] = [MF,LK] and eqang [0][GE,PN] = [LN,LB].
eqang [0][GE,BC] = [NL,NP]
       because perp[$BC,PN$] and perp[$NL,GE$].
eqang [0][NK,MF] = [KL,KF] = [ML,MF] = [NM,KF] = [EL,EF] = [NE,BO] = [HP,HF] = [NL,NP] = [LN,LO] = [NG,CO] = [GL,GF] = [AC,KF] = [BP,MF] = [NL,NP] = [GE,BC]
       because eqang [0][BP,MF] = [AC,KF] = [GL,GF] = [NG,CO] = [LN,LO] = [NL,NP] = [NL,NP] = [HP,HF] = [NE,BO] = [EL,EF] = [NM,KF] = [ML,MF] = [KL,KF] = [NK,MF] and eqang [0][GE,BC] = [NL,NP].
eqang [0][GE,OL] = [LN,LB]
       because perp[$BC,OL$] and perp[$NL,GE$].
eqang [0][PN,GE] = [BO,LE] = [EF,EN] = [HF,HA] = [KF,KN] = [GF,GN] = [CO,LG] = [KF,ML] = [MF,MN] = [MF,AC] = [KF,BP] = [MF,LK] = [LB,LN] = [OL,GE]
       because eqang [0][MF,LK] = [KF,BP] = [MF,AC] = [MF,MN] = [KF,ML] = [CO,LG] = [GF,GN] = [KF,KN] = [HF,HA] = [EF,EN] = [BO,LE] = [LB,LN] = [PN,GE] and eqang [0][GE,OL] = [LN,LB].
eqang [1][GE,OD] = [NL,GH]
       because perp[$GH,OD$] and perp[$NL,GE$].
eqang [0][GE,GH] = [NL,OD]
       because perp[$GH,OD$] and perp[$NL,GE$].
eqang [0][LE,ON] = [NE,NA] = [LE,LP] = [MP,MH] = [KA,KH] = [FE,FH] = [NL,OD] = [GE,GH]
       because eqang [0][FE,FH] = [KA,KH] = [MP,MH] = [LE,LP] = [GE,GH] = [NE,NA] = [LE,ON] and eqang [0][GE,GH] = [NL,OD].
eqang [0][GE,GP] = [NL,CD]
       because perp[$CD,PK$] and perp[$NL,GE$].
eqang [0][MF,BO] = [FK,FE] = [NM,LE] = [ML,NE] = [KM,KO] = [MK,MP] = [NK,NE] = [HK,HE] = [AC,LE] = [BP,NE] = [LK,LE] = [CD,NL] = [GP,GE]
       because eqang [0][LK,LE] = [BP,NE] = [AC,LE] = [HK,HE] = [NK,NE] = [GP,GE] = [MK,MP] = [KM,KO] = [ML,NE] = [NM,LE] = [FK,FE] = [MF,BO] and eqang [0][GE,GP] = [NL,CD].
eqang [0][GE,GC] = [NL,PK]
       because perp[$CD,PK$] and perp[$NL,GE$].
eqang [0][FM,FE] = [KF,BO] = [BP,LE] = [KM,KA] = [NM,NE] = [HM,HE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE] = [PK,NL] = [GC,GE]
       because eqang [0][NK,LE] = [LK,NE] = [LM,LE] = [AC,NE] = [HM,HE] = [NM,NE] = [GC,GE] = [KM,KA] = [BP,LE] = [KF,BO] = [FM,FE] and eqang [0][GE,GC] = [NL,PK].
eqang [0][GE,OM] = [NL,CD]
       because perp[$CD,OM$] and perp[$NL,GE$].
eqang [0][GP,GE] = [LK,LE] = [BP,NE] = [AC,LE] = [HK,HE] = [NK,NE] = [MK,MP] = [KM,KO] = [ML,NE] = [NM,LE] = [FK,FE] = [MF,BO] = [CD,NL] = [OM,GE]
       because eqang [0][MF,BO] = [FK,FE] = [NM,LE] = [ML,NE] = [KM,KO] = [MK,MP] = [NK,NE] = [HK,HE] = [AC,LE] = [BP,NE] = [LK,LE] = [CD,NL] = [GP,GE] and eqang [0][GE,OM] = [NL,CD].
eqang [0][GE,GC] = [NL,OM]
       because perp[$CD,OM$] and perp[$NL,GE$].
eqang [0][PK,NL] = [NK,LE] = [LK,NE] = [LM,LE] = [AC,NE] = [HM,HE] = [NM,NE] = [KM,KA] = [BP,LE] = [KF,BO] = [FM,FE] = [OM,NL] = [GC,GE]
       because eqang [0][FM,FE] = [KF,BO] = [BP,LE] = [KM,KA] = [NM,NE] = [HM,HE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE] = [PK,NL] = [GC,GE] and eqang [0][GE,GC] = [NL,OM].
eqang [1][GE,AO] = [NL,EH]
       because perp[$EH,AO$] and perp[$NL,GE$].
eqang [0][EG,EH] = [NL,AO]
       because perp[$EH,AO$] and perp[$NL,GE$].
eqang [0][LG,ON] = [NG,NA] = [FG,FH] = [LG,LP] = [MC,MH] = [KP,KH] = [NL,AO] = [EG,EH]
       because eqang [0][EG,EH] = [KP,KH] = [MC,MH] = [LG,LP] = [FG,FH] = [NG,NA] = [LG,ON] and eqang [0][EG,EH] = [NL,AO].
eqang [0][EG,EP] = [NL,AB]
       because perp[$AB,PM$] and perp[$NL,GE$].
eqang [0][FM,FG] = [KF,CO] = [BP,LG] = [MK,MO] = [KM,KP] = [NM,NG] = [HM,HG] = [AC,NG] = [LM,LG] = [LK,NG] = [NK,LG] = [AB,NL] = [EP,EG]
       because eqang [0][NK,LG] = [LK,NG] = [LM,LG] = [AC,NG] = [HM,HG] = [NM,NG] = [KM,KP] = [MK,MO] = [EP,EG] = [BP,LG] = [KF,CO] = [FM,FG] and eqang [0][EG,EP] = [NL,AB].
eqang [0][EG,EA] = [NL,PM]
       because perp[$AB,PM$] and perp[$NL,GE$].
eqang [0][MF,CO] = [FK,FG] = [NM,LG] = [ML,NG] = [MK,MC] = [NK,NG] = [HK,HG] = [AC,LG] = [BP,NG] = [LK,LG] = [PM,NL] = [EA,EG]
       because eqang [0][LK,LG] = [BP,NG] = [AC,LG] = [HK,HG] = [NK,NG] = [MK,MC] = [EA,EG] = [ML,NG] = [NM,LG] = [FK,FG] = [MF,CO] and eqang [0][EG,EA] = [NL,PM].
eqang [0][GE,OK] = [NL,AB]
       because perp[$AB,OK$] and perp[$NL,GE$].
eqang [0][EP,EG] = [NK,LG] = [LK,NG] = [LM,LG] = [AC,NG] = [HM,HG] = [NM,NG] = [KM,KP] = [MK,MO] = [BP,LG] = [KF,CO] = [FM,FG] = [AB,NL] = [OK,GE]
       because eqang [0][FM,FG] = [KF,CO] = [BP,LG] = [MK,MO] = [KM,KP] = [NM,NG] = [HM,HG] = [AC,NG] = [LM,LG] = [LK,NG] = [NK,LG] = [AB,NL] = [EP,EG] and eqang [0][GE,OK] = [NL,AB].
eqang [0][EG,EA] = [NL,OK]
       because perp[$AB,OK$] and perp[$NL,GE$].
eqang [0][PM,NL] = [LK,LG] = [BP,NG] = [AC,LG] = [HK,HG] = [NK,NG] = [MK,MC] = [ML,NG] = [NM,LG] = [FK,FG] = [MF,CO] = [OK,NL] = [EA,EG]
       because eqang [0][MF,CO] = [FK,FG] = [NM,LG] = [ML,NG] = [MK,MC] = [NK,NG] = [HK,HG] = [AC,LG] = [BP,NG] = [LK,LG] = [PM,NL] = [EA,EG] and eqang [0][EG,EA] = [NL,OK].
eqang [0][EG,EF] = [NL,BO]
       because perp[$BO,FE$] and perp[$NL,GE$].
eqang [0][MC,MF] = [OM,KF] = [HG,HF] = [NG,NP] = [KP,KF] = [NG,OL] = [LG,LB] = [NL,BO] = [EG,EF]
       because eqang [0][LG,LB] = [NG,OL] = [EG,EF] = [KP,KF] = [NG,NP] = [HG,HF] = [OM,KF] = [MC,MF] and eqang [0][EG,EF] = [NL,BO].
eqang [1][GE,BO] = [NL,FE]
       because perp[$BO,FE$] and perp[$NL,GE$].
eqang [0][GE,GF] = [NL,CO]
       because perp[$CO,GF$] and perp[$NL,GE$].
eqang [0][MF,OK] = [KF,KA] = [HF,HE] = [NP,NE] = [MF,MP] = [OL,NE] = [LB,LE] = [CO,NL] = [GF,GE]
       because eqang [0][LB,LE] = [OL,NE] = [GF,GE] = [MF,MP] = [NP,NE] = [HF,HE] = [KF,KA] = [MF,OK] and eqang [0][GE,GF] = [NL,CO].
eqang [1][GE,CO] = [NL,GF]
       because perp[$CO,GF$] and perp[$NL,GE$].
eqang [1][GE,MH] = [NL,KH]
       because perp[$KH,MH$] and perp[$NL,GE$].
eqang [1][GE,KH] = [NL,MH]
       because perp[$KH,MH$] and perp[$NL,GE$].
eqang [1][GE,FH] = [NL,MK]
       because perp[$MK,FH$] and perp[$NL,GE$].
eqang [1][GE,MK] = [NL,FH]
       because perp[$MK,FH$] and perp[$NL,GE$].
eqang [0][MH,LK] = [KH,ML]
       because perp[$ML,LK$] and perp[$KH,MH$].
eqang [0][MH,AC] = [KH,ML]
       because perp[$ML,AC$] and perp[$KH,MH$].
eqang [0][MH,LK] = [KH,ML] = [MH,AC]
       because eqang [0][MH,LK] = [KH,ML] and eqang [0][MH,AC] = [KH,ML].
eqang [0][MH,ML] = [KH,AC]
       because perp[$ML,AC$] and perp[$KH,MH$].
eqang [0][GE,ON] = [NL,NA] = [EL,EH] = [NE,AO] = [KL,KH] = [FB,FH] = [NG,OD] = [GL,GH] = [GE,PL] = [AC,KH] = [ML,MH]
       because eqang [0][GE,PL] = [GL,GH] = [NG,OD] = [FB,FH] = [KL,KH] = [ML,MH] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON] and eqang [0][MH,ML] = [KH,AC].
eqang [0][MH,MN] = [KH,ML]
       because perp[$ML,NM$] and perp[$KH,MH$].
eqang [0][GE,AD] = [LN,LP] = [LG,OD] = [GN,GH] = [FP,FH] = [KN,KH] = [NL,NO] = [EN,EH] = [LE,AO] = [ML,KH] = [MN,MH]
       because eqang [0][LE,AO] = [EN,EH] = [MN,MH] = [NL,NO] = [KN,KH] = [FP,FH] = [GN,GH] = [LG,OD] = [LN,LP] = [GE,AD] and eqang [0][MH,MN] = [KH,ML].
eqang [0][MH,AC] = [MH,LK] = [MH,MN] = [KH,ML] = [AO,LE] = [EH,EN] = [NO,NL] = [KH,KN] = [FH,FP] = [GH,GN] = [OD,LG] = [LP,LN] = [AD,GE]
       because eqang [0][MH,LK] = [KH,ML] = [MH,AC] and eqang [0][GE,AD] = [LN,LP] = [LG,OD] = [GN,GH] = [FP,FH] = [KN,KH] = [NL,NO] = [EN,EH] = [LE,AO] = [ML,KH] = [MN,MH].
eqang [0][MH,ML] = [KH,NM]
       because perp[$ML,NM$] and perp[$KH,MH$].
eqang [0][AC,KH] = [GE,PL] = [GL,GH] = [NG,OD] = [FB,FH] = [KL,KH] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON] = [NM,KH] = [ML,MH]
       because eqang [0][GE,ON] = [NL,NA] = [EL,EH] = [NE,AO] = [KL,KH] = [FB,FH] = [NG,OD] = [GL,GH] = [GE,PL] = [AC,KH] = [ML,MH] and eqang [0][MH,ML] = [KH,NM].
eqang [0][MH,LK] = [KH,BP]
       because perp[$BP,LK$] and perp[$KH,MH$].
eqang [0][AD,GE] = [LP,LN] = [OD,LG] = [GH,GN] = [FH,FP] = [KH,KN] = [NO,NL] = [EH,EN] = [AO,LE] = [KH,ML] = [MH,MN] = [MH,AC] = [KH,BP] = [MH,LK]
       because eqang [0][MH,AC] = [MH,LK] = [MH,MN] = [KH,ML] = [AO,LE] = [EH,EN] = [NO,NL] = [KH,KN] = [FH,FP] = [GH,GN] = [OD,LG] = [LP,LN] = [AD,GE] and eqang [0][MH,LK] = [KH,BP].
eqang [0][MH,BP] = [KH,KL]
       because perp[$BP,LK$] and perp[$KH,MH$].
eqang [0][ML,MH] = [NM,KH] = [GE,ON] = [NL,NA] = [EL,EH] = [NE,AO] = [FB,FH] = [NG,OD] = [GL,GH] = [GE,PL] = [AC,KH] = [KL,KH] = [BP,MH]
       because eqang [0][AC,KH] = [GE,PL] = [GL,GH] = [NG,OD] = [FB,FH] = [KL,KH] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON] = [NM,KH] = [ML,MH] and eqang [0][MH,BP] = [KH,KL].
eqang [0][MH,NK] = [KH,KL]
       because perp[$NK,LK$] and perp[$KH,MH$].
eqang [0][BP,MH] = [AC,KH] = [GE,PL] = [GL,GH] = [NG,OD] = [FB,FH] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON] = [NM,KH] = [ML,MH] = [KL,KH] = [NK,MH]
       because eqang [0][ML,MH] = [NM,KH] = [GE,ON] = [NL,NA] = [EL,EH] = [NE,AO] = [FB,FH] = [NG,OD] = [GL,GH] = [GE,PL] = [AC,KH] = [KL,KH] = [BP,MH] and eqang [0][MH,NK] = [KH,KL].
eqang [0][MH,ON] = [HK,HA]
       because perp[$AD,ON$] and perp[$KH,MH$].
eqang [0][NK,GE] = [LK,LN] = [ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [FK,FP] = [MK,MN] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [BP,GE] = [HK,HA] = [MH,ON]
       because eqang [0][BP,GE] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [HK,HA] = [MK,MN] = [FK,FP] = [KM,KL] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] = [LK,LN] = [NK,GE] and eqang [0][MH,ON] = [HK,HA].
eqang [0][HM,HA] = [KH,ON]
       because perp[$AD,ON$] and perp[$KH,MH$].
eqang [0][GE,LK] = [NL,BP] = [GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,NM] = [ON,KH] = [HA,HM]
       because eqang [0][GE,NM] = [LN,LM] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [HA,HM] = [FP,FM] = [ML,MK] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [GE,AC] = [NL,BP] = [GE,LK] and eqang [0][HM,HA] = [KH,ON].
eqang [0][MH,PN] = [KH,BC]
       because perp[$BC,PN$] and perp[$KH,MH$].
eqang [0][MH,BC] = [KH,PN]
       because perp[$BC,PN$] and perp[$KH,MH$].
eqang [0][MH,OL] = [KH,BC]
       because perp[$BC,OL$] and perp[$KH,MH$].
eqang [1][MH,PN] = [KH,BC] = [MH,OL]
       because eqang [0][MH,PN] = [KH,BC] and eqang [0][MH,OL] = [KH,BC].
eqang [0][MH,BC] = [KH,OL]
       because perp[$BC,OL$] and perp[$KH,MH$].
eqang [1][KH,PN] = [KH,OL] = [MH,BC]
       because eqang [0][MH,BC] = [KH,PN] and eqang [0][MH,BC] = [KH,OL].
eqang [0][MH,OD] = [HK,HG]
       because perp[$GH,OD$] and perp[$KH,MH$].
eqang [0][EA,EG] = [OK,NL] = [MF,CO] = [FK,FG] = [NM,LG] = [ML,NG] = [MK,MC] = [NK,NG] = [AC,LG] = [BP,NG] = [LK,LG] = [PM,NL] = [HK,HG] = [MH,OD]
       because eqang [0][PM,NL] = [LK,LG] = [BP,NG] = [AC,LG] = [HK,HG] = [NK,NG] = [MK,MC] = [ML,NG] = [NM,LG] = [FK,FG] = [MF,CO] = [OK,NL] = [EA,EG] and eqang [0][MH,OD] = [HK,HG].
eqang [0][HM,HG] = [KH,OD]
       because perp[$GH,OD$] and perp[$KH,MH$].
eqang [0][OK,GE] = [AB,NL] = [FM,FG] = [KF,CO] = [BP,LG] = [MK,MO] = [KM,KP] = [NM,NG] = [AC,NG] = [LM,LG] = [LK,NG] = [NK,LG] = [EP,EG] = [KH,OD] = [HM,HG]
       because eqang [0][EP,EG] = [NK,LG] = [LK,NG] = [LM,LG] = [AC,NG] = [HM,HG] = [NM,NG] = [KM,KP] = [MK,MO] = [BP,LG] = [KF,CO] = [FM,FG] = [AB,NL] = [OK,GE] and eqang [0][HM,HG] = [KH,OD].
eqang [0][MH,PK] = [KH,CD]
       because perp[$CD,PK$] and perp[$KH,MH$].
eqang [0][MH,MO] = [KH,CD]
       because perp[$CD,OM$] and perp[$KH,MH$].
eqang [1][MH,PK] = [KH,CD] = [MH,MO]
       because eqang [0][MH,PK] = [KH,CD] and eqang [0][MH,MO] = [KH,CD].
eqang [0][MH,MC] = [KH,OM]
       because perp[$CD,OM$] and perp[$KH,MH$].
eqang [0][EG,EH] = [NL,AO] = [KP,KH] = [LG,LP] = [FG,FH] = [NG,NA] = [LG,ON] = [OM,KH] = [MC,MH]
       because eqang [0][LG,ON] = [NG,NA] = [FG,FH] = [LG,LP] = [MC,MH] = [KP,KH] = [NL,AO] = [EG,EH] and eqang [0][MH,MC] = [KH,OM].
eqang [0][MH,AO] = [HK,HE]
       because perp[$EH,AO$] and perp[$KH,MH$].
eqang [0][OM,GE] = [CD,NL] = [MF,BO] = [FK,FE] = [NM,LE] = [ML,NE] = [KM,KO] = [MK,MP] = [NK,NE] = [AC,LE] = [BP,NE] = [LK,LE] = [GP,GE] = [HK,HE] = [MH,AO]
       because eqang [0][GP,GE] = [LK,LE] = [BP,NE] = [AC,LE] = [HK,HE] = [NK,NE] = [MK,MP] = [KM,KO] = [ML,NE] = [NM,LE] = [FK,FE] = [MF,BO] = [CD,NL] = [OM,GE] and eqang [0][MH,AO] = [HK,HE].
eqang [0][HM,HE] = [KH,AO]
       because perp[$EH,AO$] and perp[$KH,MH$].
eqang [0][GC,GE] = [OM,NL] = [FM,FE] = [KF,BO] = [BP,LE] = [KM,KA] = [NM,NE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE] = [PK,NL] = [KH,AO] = [HM,HE]
       because eqang [0][PK,NL] = [NK,LE] = [LK,NE] = [LM,LE] = [AC,NE] = [HM,HE] = [NM,NE] = [KM,KA] = [BP,LE] = [KF,BO] = [FM,FE] = [OM,NL] = [GC,GE] and eqang [0][HM,HE] = [KH,AO].
eqang [0][MH,AB] = [KH,PM]
       because perp[$AB,PM$] and perp[$KH,MH$].
eqang [0][MH,OK] = [KH,KA]
       because perp[$AB,OK$] and perp[$KH,MH$].
eqang [0][GE,GH] = [NL,OD] = [FE,FH] = [MP,MH] = [LE,LP] = [NE,NA] = [LE,ON] = [KA,KH] = [OK,MH]
       because eqang [0][LE,ON] = [NE,NA] = [LE,LP] = [MP,MH] = [KA,KH] = [FE,FH] = [NL,OD] = [GE,GH] and eqang [0][MH,OK] = [KH,KA].
eqang [0][MH,AB] = [KH,KO]
       because perp[$AB,OK$] and perp[$KH,MH$].
eqang [1][KH,PM] = [KH,KO] = [MH,AB]
       because eqang [0][MH,AB] = [KH,PM] and eqang [0][MH,AB] = [KH,KO].
eqang [1][MH,FE] = [KH,BO]
       because perp[$BO,FE$] and perp[$KH,MH$].
eqang [1][MH,BO] = [KH,FE]
       because perp[$BO,FE$] and perp[$KH,MH$].
eqang [1][MH,GF] = [KH,CO]
       because perp[$CO,GF$] and perp[$KH,MH$].
eqang [1][MH,CO] = [KH,GF]
       because perp[$CO,GF$] and perp[$KH,MH$].
eqang [0][FH,LK] = [MK,ML]
       because perp[$ML,LK$] and perp[$MK,FH$].
eqang [0][HA,HM] = [ON,KH] = [GE,NM] = [LN,LM] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [FP,FM] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [GE,AC] = [NL,BP] = [GE,LK] = [ML,MK] = [LK,FH]
       because eqang [0][GE,LK] = [NL,BP] = [GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [ML,MK] = [FP,FM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,NM] = [ON,KH] = [HA,HM] and eqang [0][FH,LK] = [MK,ML].
eqang [0][FH,ML] = [KM,KL]
       because perp[$ML,LK$] and perp[$MK,FH$].
eqang [0][MH,ON] = [HK,HA] = [BP,GE] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [MK,MN] = [FK,FP] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] = [LK,LN] = [NK,GE] = [KM,KL] = [FH,ML]
       because eqang [0][NK,GE] = [LK,LN] = [ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [KM,KL] = [FK,FP] = [MK,MN] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [BP,GE] = [HK,HA] = [MH,ON] and eqang [0][FH,ML] = [KM,KL].
eqang [0][FH,AC] = [MK,ML]
       because perp[$ML,AC$] and perp[$MK,FH$].
eqang [0][LK,FH] = [GE,LK] = [NL,BP] = [GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [FP,FM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,NM] = [ON,KH] = [HA,HM] = [ML,MK] = [AC,FH]
       because eqang [0][HA,HM] = [ON,KH] = [GE,NM] = [LN,LM] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [FP,FM] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [GE,AC] = [NL,BP] = [GE,LK] = [ML,MK] = [LK,FH] and eqang [0][FH,AC] = [MK,ML].
eqang [0][FH,ML] = [MK,AC]
       because perp[$ML,AC$] and perp[$MK,FH$].
eqang [0][KM,KL] = [NK,GE] = [LK,LN] = [ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [FK,FP] = [MK,MN] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [BP,GE] = [HK,HA] = [MH,ON] = [MK,AC] = [FH,ML]
       because eqang [0][MH,ON] = [HK,HA] = [BP,GE] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [MK,MN] = [FK,FP] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] = [LK,LN] = [NK,GE] = [KM,KL] = [FH,ML] and eqang [0][FH,ML] = [MK,AC].
eqang [0][FH,NM] = [MK,ML]
       because perp[$ML,NM$] and perp[$MK,FH$].
eqang [0][AC,FH] = [HA,HM] = [ON,KH] = [GE,NM] = [LN,LM] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [FP,FM] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [GE,AC] = [NL,BP] = [GE,LK] = [LK,FH] = [ML,MK] = [NM,FH]
       because eqang [0][LK,FH] = [GE,LK] = [NL,BP] = [GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [FP,FM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,NM] = [ON,KH] = [HA,HM] = [ML,MK] = [AC,FH] and eqang [0][FH,NM] = [MK,ML].
eqang [0][FH,LK] = [MK,BP]
       because perp[$BP,LK$] and perp[$MK,FH$].
eqang [1][NM,FH] = [ML,MK] = [GE,LK] = [NL,BP] = [GE,AC] = [OL,MF] = [FB,FK] = [LG,OM] = [GN,GC] = [GL,GP] = [EN,EP] = [KN,KM] = [FP,FM] = [NL,NK] = [HP,HK] = [NE,OK] = [EL,EA] = [LN,LM] = [GE,NM] = [ON,KH] = [HA,HM] = [AC,FH] = [BP,MK] = [LK,FH]
       because eqang [0][AC,FH] = [HA,HM] = [ON,KH] = [GE,NM] = [LN,LM] = [EL,EA] = [NE,OK] = [HP,HK] = [NL,NK] = [FP,FM] = [KN,KM] = [EN,EP] = [GL,GP] = [GN,GC] = [LG,OM] = [FB,FK] = [OL,MF] = [GE,AC] = [NL,BP] = [GE,LK] = [LK,FH] = [ML,MK] = [NM,FH] and eqang [0][FH,LK] = [MK,BP].
eqang [0][FH,BP] = [KM,KL]
       because perp[$BP,LK$] and perp[$MK,FH$].
eqang [0][FH,ML] = [MK,AC] = [MH,ON] = [HK,HA] = [BP,GE] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [MK,MN] = [FK,FP] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] = [LK,LN] = [NK,GE] = [KM,KL] = [FH,BP]
       because eqang [0][KM,KL] = [NK,GE] = [LK,LN] = [ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [FK,FP] = [MK,MN] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [BP,GE] = [HK,HA] = [MH,ON] = [MK,AC] = [FH,ML] and eqang [0][FH,BP] = [KM,KL].
eqang [0][FH,NK] = [KM,KL]
       because perp[$NK,LK$] and perp[$MK,FH$].
eqang [1][FH,BP] = [NK,GE] = [LK,LN] = [ML,GE] = [AC,NL] = [FM,FB] = [KF,OL] = [GC,GL] = [OM,NG] = [EP,EL] = [GP,GN] = [FK,FP] = [MK,MN] = [NM,NL] = [HM,HP] = [EA,EN] = [OK,LE] = [BP,GE] = [HK,HA] = [MH,ON] = [MK,AC] = [FH,ML] = [KM,KL] = [FH,NK]
       because eqang [0][FH,ML] = [MK,AC] = [MH,ON] = [HK,HA] = [BP,GE] = [OK,LE] = [EA,EN] = [HM,HP] = [NM,NL] = [MK,MN] = [FK,FP] = [GP,GN] = [EP,EL] = [OM,NG] = [GC,GL] = [KF,OL] = [FM,FB] = [AC,NL] = [ML,GE] = [LK,LN] = [NK,GE] = [KM,KL] = [FH,BP] and eqang [0][FH,NK] = [KM,KL].
eqang [0][HF,HP] = [MK,AD]
       because perp[$AD,PL$] and perp[$MK,FH$].
eqang [0][GE,BC] = [NL,NP] = [BP,MF] = [AC,KF] = [GL,GF] = [NG,CO] = [LN,LO] = [NL,NP] = [NE,BO] = [EL,EF] = [NM,KF] = [ML,MF] = [KL,KF] = [NK,MF] = [AD,MK] = [HP,HF]
       because eqang [0][NK,MF] = [KL,KF] = [ML,MF] = [NM,KF] = [EL,EF] = [NE,BO] = [HP,HF] = [NL,NP] = [LN,LO] = [NG,CO] = [GL,GF] = [AC,KF] = [BP,MF] = [NL,NP] = [GE,BC] and eqang [0][HF,HP] = [MK,AD].
eqang [0][HF,HA] = [MK,PL]
       because perp[$AD,PL$] and perp[$MK,FH$].
eqang [0][OL,GE] = [LB,LN] = [MF,LK] = [KF,BP] = [MF,AC] = [MF,MN] = [KF,ML] = [CO,LG] = [GF,GN] = [KF,KN] = [EF,EN] = [BO,LE] = [PN,GE] = [MK,PL] = [HF,HA]
       because eqang [0][PN,GE] = [BO,LE] = [EF,EN] = [HF,HA] = [KF,KN] = [GF,GN] = [CO,LG] = [KF,ML] = [MF,MN] = [MF,AC] = [KF,BP] = [MF,LK] = [LB,LN] = [OL,GE] and eqang [0][HF,HA] = [MK,PL].
eqang [0][FH,ON] = [MK,AD]
       because perp[$AD,ON$] and perp[$MK,FH$].
eqang [1][HP,HF] = [NK,MF] = [KL,KF] = [ML,MF] = [NM,KF] = [EL,EF] = [NE,BO] = [NL,NP] = [LN,LO] = [NG,CO] = [GL,GF] = [AC,KF] = [BP,MF] = [NL,NP] = [GE,BC] = [AD,MK] = [ON,FH]
       because eqang [0][GE,BC] = [NL,NP] = [BP,MF] = [AC,KF] = [GL,GF] = [NG,CO] = [LN,LO] = [NL,NP] = [NE,BO] = [EL,EF] = [NM,KF] = [ML,MF] = [KL,KF] = [NK,MF] = [AD,MK] = [HP,HF] and eqang [0][FH,ON] = [MK,AD].
eqang [0][HF,HA] = [MK,ON]
       because perp[$AD,ON$] and perp[$MK,FH$].
eqang [1][MK,PL] = [PN,GE] = [BO,LE] = [EF,EN] = [KF,KN] = [GF,GN] = [CO,LG] = [KF,ML] = [MF,MN] = [MF,AC] = [KF,BP] = [MF,LK] = [LB,LN] = [OL,GE] = [MK,ON] = [HF,HA]
       because eqang [0][OL,GE] = [LB,LN] = [MF,LK] = [KF,BP] = [MF,AC] = [MF,MN] = [KF,ML] = [CO,LG] = [GF,GN] = [KF,KN] = [EF,EN] = [BO,LE] = [PN,GE] = [MK,PL] = [HF,HA] and eqang [0][HF,HA] = [MK,ON].
eqang [0][FH,FP] = [MK,BC]
       because perp[$BC,PN$] and perp[$MK,FH$].
eqang [0][MH,LK] = [KH,BP] = [MH,AC] = [MH,MN] = [KH,ML] = [AO,LE] = [EH,EN] = [NO,NL] = [KH,KN] = [GH,GN] = [OD,LG] = [LP,LN] = [AD,GE] = [MK,BC] = [FH,FP]
       because eqang [0][AD,GE] = [LP,LN] = [OD,LG] = [GH,GN] = [FH,FP] = [KH,KN] = [NO,NL] = [EH,EN] = [AO,LE] = [KH,ML] = [MH,MN] = [MH,AC] = [KH,BP] = [MH,LK] and eqang [0][FH,FP] = [MK,BC].
eqang [0][FH,FB] = [MK,PN]
       because perp[$BC,PN$] and perp[$MK,FH$].
eqang [0][NK,MH] = [KL,KH] = [ML,MH] = [NM,KH] = [GE,ON] = [NL,NA] = [EL,EH] = [NE,AO] = [NG,OD] = [GL,GH] = [GE,PL] = [AC,KH] = [BP,MH] = [PN,MK] = [FB,FH]
       because eqang [0][BP,MH] = [AC,KH] = [GE,PL] = [GL,GH] = [NG,OD] = [FB,FH] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON] = [NM,KH] = [ML,MH] = [KL,KH] = [NK,MH] and eqang [0][FH,FB] = [MK,PN].
eqang [0][FH,OL] = [MK,BC]
       because perp[$BC,OL$] and perp[$MK,FH$].
eqang [1][FH,FP] = [AD,GE] = [LP,LN] = [OD,LG] = [GH,GN] = [KH,KN] = [NO,NL] = [EH,EN] = [AO,LE] = [KH,ML] = [MH,MN] = [MH,AC] = [KH,BP] = [MH,LK] = [MK,BC] = [FH,OL]
       because eqang [0][MH,LK] = [KH,BP] = [MH,AC] = [MH,MN] = [KH,ML] = [AO,LE] = [EH,EN] = [NO,NL] = [KH,KN] = [GH,GN] = [OD,LG] = [LP,LN] = [AD,GE] = [MK,BC] = [FH,FP] and eqang [0][FH,OL] = [MK,BC].
eqang [0][FH,FB] = [MK,OL]
       because perp[$BC,OL$] and perp[$MK,FH$].
eqang [1][PN,MK] = [BP,MH] = [AC,KH] = [GE,PL] = [GL,GH] = [NG,OD] = [NE,AO] = [EL,EH] = [NL,NA] = [GE,ON] = [NM,KH] = [ML,MH] = [KL,KH] = [NK,MH] = [OL,MK] = [FB,FH]
       because eqang [0][NK,MH] = [KL,KH] = [ML,MH] = [NM,KH] = [GE,ON] = [NL,NA] = [EL,EH] = [NE,AO] = [NG,OD] = [GL,GH] = [GE,PL] = [AC,KH] = [BP,MH] = [PN,MK] = [FB,FH] and eqang [0][FH,FB] = [MK,OL].
eqang [1][FH,OD] = [MK,GH]
       because perp[$GH,OD$] and perp[$MK,FH$].
eqang [0][HF,HG] = [MK,OD]
       because perp[$GH,OD$] and perp[$MK,FH$].
eqang [1][EG,EF] = [NL,BO] = [LG,LB] = [NG,OL] = [KP,KF] = [NG,NP] = [OM,KF] = [MC,MF] = [OD,MK] = [HG,HF]
       because eqang [0][MC,MF] = [OM,KF] = [HG,HF] = [NG,NP] = [KP,KF] = [NG,OL] = [LG,LB] = [NL,BO] = [EG,EF] and eqang [0][HF,HG] = [MK,OD].
eqang [0][FH,PK] = [MK,MC]
       because perp[$CD,PK$] and perp[$MK,FH$].
eqang [0][MH,OD] = [HK,HG] = [PM,NL] = [LK,LG] = [BP,NG] = [AC,LG] = [NK,NG] = [ML,NG] = [NM,LG] = [FK,FG] = [MF,CO] = [OK,NL] = [EA,EG] = [MK,MC] = [FH,PK]
       because eqang [0][EA,EG] = [OK,NL] = [MF,CO] = [FK,FG] = [NM,LG] = [ML,NG] = [MK,MC] = [NK,NG] = [AC,LG] = [BP,NG] = [LK,LG] = [PM,NL] = [HK,HG] = [MH,OD] and eqang [0][FH,PK] = [MK,MC].
eqang [0][FH,CD] = [KM,KP]
       because perp[$CD,PK$] and perp[$MK,FH$].
eqang [1][HM,HG] = [KH,OD] = [EP,EG] = [NK,LG] = [LK,NG] = [LM,LG] = [AC,NG] = [NM,NG] = [MK,MO] = [BP,LG] = [KF,CO] = [FM,FG] = [AB,NL] = [OK,GE] = [KM,KP] = [FH,CD]
       because eqang [0][OK,GE] = [AB,NL] = [FM,FG] = [KF,CO] = [BP,LG] = [MK,MO] = [KM,KP] = [NM,NG] = [AC,NG] = [LM,LG] = [LK,NG] = [NK,LG] = [EP,EG] = [KH,OD] = [HM,HG] and eqang [0][FH,CD] = [KM,KP].
eqang [0][FH,OM] = [MK,MC]
       because perp[$CD,OM$] and perp[$MK,FH$].
eqang [1][FH,PK] = [EA,EG] = [OK,NL] = [MF,CO] = [FK,FG] = [NM,LG] = [ML,NG] = [NK,NG] = [AC,LG] = [BP,NG] = [LK,LG] = [PM,NL] = [HK,HG] = [MH,OD] = [MK,MC] = [FH,OM]
       because eqang [0][MH,OD] = [HK,HG] = [PM,NL] = [LK,LG] = [BP,NG] = [AC,LG] = [NK,NG] = [ML,NG] = [NM,LG] = [FK,FG] = [MF,CO] = [OK,NL] = [EA,EG] = [MK,MC] = [FH,PK] and eqang [0][FH,OM] = [MK,MC].
eqang [1][FH,AO] = [MK,EH]
       because perp[$EH,AO$] and perp[$MK,FH$].
eqang [0][HF,HE] = [MK,AO]
       because perp[$EH,AO$] and perp[$MK,FH$].
eqang [1][GF,GE] = [CO,NL] = [LB,LE] = [OL,NE] = [MF,MP] = [NP,NE] = [KF,KA] = [MF,OK] = [MK,AO] = [HF,HE]
       because eqang [0][MF,OK] = [KF,KA] = [HF,HE] = [NP,NE] = [MF,MP] = [OL,NE] = [LB,LE] = [CO,NL] = [GF,GE] and eqang [0][HF,HE] = [MK,AO].
eqang [0][FH,PM] = [KM,KA]
       because perp[$AB,PM$] and perp[$MK,FH$].
eqang [0][HM,HE] = [KH,AO] = [PK,NL] = [NK,LE] = [LK,NE] = [LM,LE] = [AC,NE] = [NM,NE] = [BP,LE] = [KF,BO] = [FM,FE] = [OM,NL] = [GC,GE] = [KM,KA] = [FH,PM]
       because eqang [0][GC,GE] = [OM,NL] = [FM,FE] = [KF,BO] = [BP,LE] = [KM,KA] = [NM,NE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE] = [PK,NL] = [KH,AO] = [HM,HE] and eqang [0][FH,PM] = [KM,KA].
eqang [0][FH,AB] = [MK,MP]
       because perp[$AB,PM$] and perp[$MK,FH$].
eqang [1][MH,AO] = [HK,HE] = [GP,GE] = [LK,LE] = [BP,NE] = [AC,LE] = [NK,NE] = [KM,KO] = [ML,NE] = [NM,LE] = [FK,FE] = [MF,BO] = [CD,NL] = [OM,GE] = [MK,MP] = [FH,AB]
       because eqang [0][OM,GE] = [CD,NL] = [MF,BO] = [FK,FE] = [NM,LE] = [ML,NE] = [KM,KO] = [MK,MP] = [NK,NE] = [AC,LE] = [BP,NE] = [LK,LE] = [GP,GE] = [HK,HE] = [MH,AO] and eqang [0][FH,AB] = [MK,MP].
eqang [0][FH,OK] = [KM,KA]
       because perp[$AB,OK$] and perp[$MK,FH$].
eqang [1][FH,PM] = [GC,GE] = [OM,NL] = [FM,FE] = [KF,BO] = [BP,LE] = [NM,NE] = [AC,NE] = [LM,LE] = [LK,NE] = [NK,LE] = [PK,NL] = [KH,AO] = [HM,HE] = [KM,KA] = [FH,OK]
       because eqang [0][HM,HE] = [KH,AO] = [PK,NL] = [NK,LE] = [LK,NE] = [LM,LE] = [AC,NE] = [NM,NE] = [BP,LE] = [KF,BO] = [FM,FE] = [OM,NL] = [GC,GE] = [KM,KA] = [FH,PM] and eqang [0][FH,OK] = [KM,KA].
eqang [0][FH,FE] = [MK,BO]
       because perp[$BO,FE$] and perp[$MK,FH$].
eqang [1][OK,MH] = [KA,KH] = [LE,ON] = [NE,NA] = [LE,LP] = [MP,MH] = [NL,OD] = [GE,GH] = [BO,MK] = [FE,FH]
       because eqang [0][GE,GH] = [NL,OD] = [FE,FH] = [MP,MH] = [LE,LP] = [NE,NA] = [LE,ON] = [KA,KH] = [OK,MH] and eqang [0][FH,FE] = [MK,BO].
eqang [1][FH,BO] = [MK,FE]
       because perp[$BO,FE$] and perp[$MK,FH$].
eqang [0][FH,FG] = [MK,CO]
       because perp[$CO,GF$] and perp[$MK,FH$].
eqang [1][MC,MH] = [OM,KH] = [LG,ON] = [NG,NA] = [LG,LP] = [KP,KH] = [NL,AO] = [EG,EH] = [CO,MK] = [FG,FH]
       because eqang [0][EG,EH] = [NL,AO] = [KP,KH] = [LG,LP] = [FG,FH] = [NG,NA] = [LG,ON] = [OM,KH] = [MC,MH] and eqang [0][FH,FG] = [MK,CO].
eqang [1][FH,CO] = [MK,GF]
       because perp[$CO,GF$] and perp[$MK,FH$].


The configuration contains the following congruent segments
con-seg[1]:AO = BO = CO = OD 
       because cir [1]circle[(O)ABCD].
con-seg[0]:AN = DN 
       because midpoint[$N,AD$].
con-seg[0]:CM = DM 
       because midpoint[$M,CD$].
con-seg[0]:CL = BL 
       because midpoint[$L,CB$].
con-seg[0]:AK = BK 
       because midpoint[$K,AB$].
con-seg[0]:AN = PN = DN 
       because cir [1]circle[(N)APD].
con-seg[0]:CM = PM = DM 
       because cir [1]circle[(M)CPD].
con-seg[0]:BL = CL = PL 
       because cir [1]circle[(L)BCP].
con-seg[0]:AK = BK = PK 
       because cir [1]circle[(K)ABP].
con-seg[1]:NK = ML 
       because $KM = MK$ and $[KMN] sim [MKL]$.
con-seg[1]:NM = LK 
       because $KM = MK$ and $[KMN] sim [MKL]$.
con-seg[0]:OM = PK 
       because [0]:OM*PH = PK*PH .
con-seg[1]:OM = PK = AK = BK 
       because con-seg[0]:AK = BK = PK  and con-seg[0]:OM = PK .
con-seg[0]:ON = PL 
       because [0]:ON*PG = PL*PG .
con-seg[1]:ON = PL = BL = CL 
       because con-seg[0]:BL = CL = PL  and con-seg[0]:ON = PL .
con-seg[0]:OK = PM 
       because [0]:OK*ML = PM*NK .
con-seg[1]:OK = PM = CM = DM 
       because con-seg[0]:CM = PM = DM  and con-seg[0]:OK = PM .
con-seg[0]:OL = DN 
       because [0]:OL*PD = PD*DN .
con-seg[1]:OL = DN = AN = PN 
       because con-seg[0]:AN = PN = DN  and con-seg[0]:OL = DN .
con-seg[1]:NL = MK 
       because circle[$NMLK$] and para[$NM,LK$].
con-seg[1]:MF = MH 
       because [0]:MF*LG = MH*LG .
con-seg[1]:LE = LG 
       because [0]:PL*LG = PL*LE .
con-seg[1]:KH = KF 
       because [0]:AK*KF = PK*KH .
con-seg[1]:NE = NG 
       because [0]:AN*NG = PN*NE .

The configuration contains the following similar triangles
sim-tri[0]:[1,MKN] [1,KML] 
       because $[MK,KN]=[KM,ML]$ and $[KM,MN]=[MK,KL]$.
sim-tri[0]:[-1,LNK] [-1,NLM] 
       because $[LN,NK]=[NL,LM]$ and $[NL,LK]=[LN,NM]$.
sim-tri[0]:[1,PEF] [-1,OLK] 
       because $[EP,PF]=[KO,OL]$ and $[PE,EF]=[KL,LO]$.
sim-tri[0]:[1,PEF] [1,DAC] 
       because $[EP,PF]=[AD,DC]$ and $[PE,EF]=[DA,AC]$.
sim-tri[0]:[1,PEF] [1,DAC] [-1,OLK] 
       because sim-tri[0]:[1,PEF] [-1,OLK]  and sim-tri[0]:[1,PEF] [1,DAC] .
sim-tri[0]:[1,PEF] [1,DNM] 
       because $[EP,PF]=[ND,DM]$ and $[PE,EF]=[DN,NM]$.
sim-tri[0]:[1,PEF] [1,DNM] [1,DAC] [-1,OLK] 
       because sim-tri[0]:[1,PEF] [1,DAC] [-1,OLK]  and sim-tri[0]:[1,PEF] [1,DNM] .
sim-tri[0]:[1,PEF] [-1,DGH] 
       because $[EP,PF]=[HD,DG]$ and $[PE,EF]=[HG,GD]$.
sim-tri[0]:[-1,EFP] [1,GHD] [-1,NMD] [-1,ACD] [1,LKO] 
       because sim-tri[0]:[1,PEF] [1,DNM] [1,DAC] [-1,OLK]  and sim-tri[0]:[1,PEF] [-1,DGH] .
sim-tri[0]:[1,BAC] [-1,OMN] 
       because $[AB,BC]=[NO,OM]$ and $[BA,AC]=[NM,MO]$.
sim-tri[0]:[1,BKL] [-1,OMN] 
       because $[KB,BL]=[NO,OM]$ and $[BK,KL]=[NM,MO]$.
sim-tri[0]:[-1,BLK] [1,ONM] [-1,BCA] 
       because sim-tri[0]:[1,BAC] [-1,OMN]  and sim-tri[0]:[1,BKL] [-1,OMN] .
sim-tri[0]:[1,BEF] [1,ONM] 
       because $[EB,BF]=[NO,OM]$ and $[BE,EF]=[ON,NM]$.
sim-tri[0]:[-1,BFE] [-1,OMN] [1,BKL] [1,BAC] 
       because sim-tri[0]:[-1,BLK] [1,ONM] [-1,BCA]  and sim-tri[0]:[1,BEF] [1,ONM] .
sim-tri[0]:[1,BAC] [1,PHG] 
       because $[AB,BC]=[HP,PG]$ and $[BA,AC]=[PH,HG]$.
sim-tri[0]:[1,ACB] [1,HGP] [-1,FEB] [-1,MNO] [1,KLB] 
       because sim-tri[0]:[-1,BFE] [-1,OMN] [1,BKL] [1,BAC]  and sim-tri[0]:[1,BAC] [1,PHG] .
sim-tri[0]:[1,PEH] [-1,ONK] 
       because $[EP,PH]=[KO,ON]$ and $[PE,EH]=[KN,NO]$.
sim-tri[0]:[1,PEH] [1,CBD] 
       because $[EP,PH]=[BC,CD]$ and $[PE,EH]=[CB,BD]$.
sim-tri[0]:[1,PEH] [1,CBD] [-1,ONK] 
       because sim-tri[0]:[1,PEH] [-1,ONK]  and sim-tri[0]:[1,PEH] [1,CBD] .
sim-tri[0]:[1,PEH] [1,CLM] 
       because $[EP,PH]=[LC,CM]$ and $[PE,EH]=[CL,LM]$.
sim-tri[0]:[1,PEH] [1,CLM] [1,CBD] [-1,ONK] 
       because sim-tri[0]:[1,PEH] [1,CBD] [-1,ONK]  and sim-tri[0]:[1,PEH] [1,CLM] .
sim-tri[0]:[1,PEH] [-1,CGF] 
       because $[EP,PH]=[FC,CG]$ and $[PE,EH]=[FG,GC]$.
sim-tri[0]:[1,HPE] [-1,FCG] [1,MCL] [1,DCB] [-1,KON] 
       because sim-tri[0]:[1,PEH] [1,CLM] [1,CBD] [-1,ONK]  and sim-tri[0]:[1,PEH] [-1,CGF] .
sim-tri[0]:[1,ABD] [-1,OML] 
       because $[BA,AD]=[LO,OM]$ and $[AB,BD]=[LM,MO]$.
sim-tri[0]:[1,AKN] [-1,OML] 
       because $[KA,AN]=[LO,OM]$ and $[AK,KN]=[LM,MO]$.
sim-tri[0]:[-1,ANK] [1,OLM] [-1,ADB] 
       because sim-tri[0]:[1,ABD] [-1,OML]  and sim-tri[0]:[1,AKN] [-1,OML] .
sim-tri[0]:[1,AEH] [1,OLM] 
       because $[EA,AH]=[LO,OM]$ and $[AE,EH]=[OL,LM]$.
sim-tri[0]:[-1,AHE] [-1,OML] [1,AKN] [1,ABD] 
       because sim-tri[0]:[-1,ANK] [1,OLM] [-1,ADB]  and sim-tri[0]:[1,AEH] [1,OLM] .
sim-tri[0]:[1,ABD] [1,PFG] 
       because $[BA,AD]=[FP,PG]$ and $[AB,BD]=[PF,FG]$.
sim-tri[0]:[-1,BDA] [-1,FGP] [1,HEA] [1,MLO] [-1,KNA] 
       because sim-tri[0]:[-1,AHE] [-1,OML] [1,AKN] [1,ABD]  and sim-tri[0]:[1,ABD] [1,PFG] .
sim-tri[0]:[1,HGP] [-1,KLP] 
       because $[GH,HP]=[PK,KL]$ and $[HG,GP]=[PL,LK]$.
sim-tri[1]:[1,HGP] [-1,KLP] [1,ACB] [-1,FEB] [-1,MNO] [1,KLB] 
       because sim-tri[0]:[1,ACB] [1,HGP] [-1,FEB] [-1,MNO] [1,KLB]  and sim-tri[0]:[1,HGP] [-1,KLP] .
sim-tri[0]:[1,DCP] [-1,ABP] 
       because $[CD,DP]=[PA,AB]$ and $[DC,CP]=[PB,BA]$.
sim-tri[0]:[1,DCP] [1,APE] 
       because $[CD,DP]=[PA,AE]$ and $[DC,CP]=[AP,PE]$.
sim-tri[0]:[-1,DPC] [-1,AEP] [1,APB] 
       because sim-tri[0]:[1,DCP] [-1,ABP]  and sim-tri[0]:[1,DCP] [1,APE] .
sim-tri[0]:[1,DGP] [1,APB] 
       because $[GD,DP]=[PA,AB]$ and $[DG,GP]=[AP,PB]$.
sim-tri[0]:[-1,DPG] [-1,ABP] [1,DCP] [1,APE] 
       because sim-tri[0]:[-1,DPC] [-1,AEP] [1,APB]  and sim-tri[0]:[1,DGP] [1,APB] .
sim-tri[0]:[1,DMP] [-1,AKP] 
       because $[MD,DP]=[PA,AK]$ and $[DP,PM]=[KP,PA]$.
sim-tri[0]:[1,DCP] [-1,OBL] 
       because $[CD,DP]=[LO,OB]$ and $[DC,CP]=[LB,BO]$.
sim-tri[0]:[1,DCP] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE] 
       because sim-tri[0]:[-1,DPG] [-1,ABP] [1,DCP] [1,APE]  and sim-tri[0]:[1,DCP] [-1,OBL] .
sim-tri[0]:[1,DCP] [1,OCL] 
       because $[CD,DP]=[CO,OL]$ and $[DC,CP]=[OC,CL]$.
sim-tri[0]:[1,DCP] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE] 
       because sim-tri[0]:[1,DCP] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE]  and sim-tri[0]:[1,DCP] [1,OCL] .
sim-tri[0]:[1,DCB] [-1,MPL] 
       because $[CD,DB]=[LM,MP]$ and $[DB,BC]=[PL,LM]$.
sim-tri[1]:[1,DCB] [-1,MPL] [1,HPE] [-1,FCG] [1,MCL] [-1,KON] 
       because sim-tri[0]:[1,HPE] [-1,FCG] [1,MCL] [1,DCB] [-1,KON]  and sim-tri[0]:[1,DCB] [-1,MPL] .
sim-tri[0]:[1,DCP] [-1,AON] 
       because $[CD,DP]=[NA,AO]$ and $[DC,CP]=[NO,OA]$.
sim-tri[0]:[1,DCP] [-1,AON] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE] 
       because sim-tri[0]:[1,DCP] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE]  and sim-tri[0]:[1,DCP] [-1,AON] .
sim-tri[0]:[1,DMP] [-1,AOD] 
       because $[MD,DP]=[DA,AO]$ and $[DP,PM]=[OD,DA]$.
sim-tri[0]:[1,DMP] [-1,AOD] [-1,AKP] 
       because sim-tri[0]:[1,DMP] [-1,AKP]  and sim-tri[0]:[1,DMP] [-1,AOD] .
sim-tri[0]:[1,DCP] [1,DON] 
       because $[CD,DP]=[OD,DN]$ and $[DC,CP]=[DO,ON]$.
sim-tri[0]:[1,DCP] [1,DON] [-1,AON] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE] 
       because sim-tri[0]:[1,DCP] [-1,AON] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE]  and sim-tri[0]:[1,DCP] [1,DON] .
sim-tri[0]:[1,DCP] [1,PBE] 
       because $[CD,DP]=[BP,PE]$ and $[DC,CP]=[PB,BE]$.
sim-tri[0]:[1,DCP] [1,PBE] [1,DON] [-1,AON] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE] 
       because sim-tri[0]:[1,DCP] [1,DON] [-1,AON] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE]  and sim-tri[0]:[1,DCP] [1,PBE] .
sim-tri[0]:[1,DCP] [-1,PCG] 
       because $[CD,DP]=[GP,PC]$ and $[DC,CP]=[GC,CP]$.
sim-tri[0]:[1,CPD] [-1,CGP] [1,BEP] [1,OND] [-1,ONA] [1,CLO] [-1,BLO] [-1,PGD] [-1,BPA] [1,PEA] 
       because sim-tri[0]:[1,DCP] [1,PBE] [1,DON] [-1,AON] [1,OCL] [-1,OBL] [-1,DPG] [-1,ABP] [1,APE]  and sim-tri[0]:[1,DCP] [-1,PCG] .
sim-tri[0]:[1,GHD] [1,NMP] 
       because $[HG,GD]=[MN,NP]$ and $[GH,HD]=[NM,MP]$.
sim-tri[1]:[1,GHD] [1,NMP] [-1,EFP] [-1,NMD] [-1,ACD] [1,LKO] 
       because sim-tri[0]:[-1,EFP] [1,GHD] [-1,NMD] [-1,ACD] [1,LKO]  and sim-tri[0]:[1,GHD] [1,NMP] .
sim-tri[0]:[1,PHD] [-1,APD] 
       because $[HP,PD]=[DA,AP]$ and $[PH,HD]=[DP,PA]$.
sim-tri[0]:[1,PHD] [1,AHP] 
       because $[HP,PD]=[HA,AP]$ and $[PH,HD]=[AH,HP]$.
sim-tri[0]:[1,PHD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [-1,APD]  and sim-tri[0]:[1,PHD] [1,AHP] .
sim-tri[0]:[1,PHD] [-1,OMD] 
       because $[HP,PD]=[DO,OM]$ and $[PH,HD]=[DM,MO]$.
sim-tri[0]:[1,PHD] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [-1,OMD] .
sim-tri[0]:[1,PHD] [1,OMC] 
       because $[HP,PD]=[MO,OC]$ and $[PH,HD]=[OM,MC]$.
sim-tri[0]:[1,PHD] [1,OMC] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [-1,OMD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [1,OMC] .
sim-tri[0]:[1,PHD] [-1,AKO] 
       because $[HP,PD]=[OA,AK]$, $[PH,HD]=[OK,KA]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [1,OMC] [-1,OMD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [-1,AKO] .
sim-tri[0]:[1,PHD] [1,BKO] 
       because $[HP,PD]=[KB,BO]$, $[PH,HD]=[BK,KO]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,PHD] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [1,BKO] .
sim-tri[0]:[1,PHD] [1,BPC] 
       because $[HP,PD]=[PB,BC]$, $[PH,HD]=[BP,PC]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,PHD] [1,BPC] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [1,BPC] .
sim-tri[0]:[1,PHD] [-1,BFP] 
       because $[HP,PD]=[PB,BF]$, $[PH,HD]=[PF,FB]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-1,BFP] [1,BPC] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [1,BPC] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [-1,BFP] .
sim-tri[0]:[1,PHD] [-1,PFC] 
       because $[HP,PD]=[CP,PF]$, $[PH,HD]=[CF,FP]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-1,PFC] [-1,BFP] [1,BPC] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD] 
       because sim-tri[0]:[1,PHD] [-1,BFP] [1,BPC] [1,BKO] [-1,AKO] [1,OMC] [-1,OMD] [1,AHP] [-1,APD]  and sim-tri[0]:[1,PHD] [-1,PFC] .
sim-tri[0]:[1,ANP] [1,PLB] 
       because $[NA,AP]=[LP,PB]$, $[AP,PN]=[PB,BL]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,ANP] [1,AOB] 
       because $[NA,AP]=[OA,AB]$, $[AP,PN]=[AB,BO]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,ANP] [1,AOB] [1,PLB] 
       because sim-tri[0]:[1,ANP] [1,PLB]  and sim-tri[0]:[1,ANP] [1,AOB] .
sim-tri[0]:[1,HEA] [1,KNP] 
       because $[EH,HA]=[NK,KP]$ and $[HE,EA]=[KN,NP]$.
sim-tri[1]:[1,HEA] [1,KNP] [-1,BDA] [-1,FGP] [1,MLO] [-1,KNA] 
       because sim-tri[0]:[-1,BDA] [-1,FGP] [1,HEA] [1,MLO] [-1,KNA]  and sim-tri[0]:[1,HEA] [1,KNP] .
sim-tri[0]:[1,PMC] [-1,PKB] 
       because $[MP,PC]=[BP,PK]$ and $[PM,MC]=[BK,KP]$.
sim-tri[0]:[1,PMC] [1,BOC] 
       because $[MP,PC]=[OB,BC]$ and $[PC,CM]=[BC,CO]$.
sim-tri[0]:[-1,PCM] [-1,BCO] [1,PBK] 
       because sim-tri[0]:[1,PMC] [-1,PKB]  and sim-tri[0]:[1,PMC] [1,BOC] .
sim-tri[0]:[1,DNP] [1,PLC] 
       because $[ND,DP]=[LP,PC]$ and $[DN,NP]=[PL,LC]$.
sim-tri[0]:[1,DNP] [1,DOC] 
       because $[ND,DP]=[OD,DC]$ and $[DP,PN]=[DC,CO]$.
sim-tri[0]:[1,PDN] [1,CDO] [1,CPL] 
       because sim-tri[0]:[1,DNP] [1,PLC]  and sim-tri[0]:[1,DNP] [1,DOC] .
sim-tri[1]:[1,POK] [1,OPM] 
       because $[OP,PK]=[PO,OM]$ and $[PO,OK]=[OP,PM]$.
sim-tri[0]:[-1,KPM] [-1,MOK] 
       because $[KP,PM]=[MO,OK]$ and $[PK,KM]=[OM,MK]$.
sim-tri[1]:[1,PGM] [-1,PEK] 
       because $[GP,PM]=[KP,PE]$ and $[PG,GM]=[KE,EP]$.
sim-tri[1]:[1,FCD] [-1,GCB] 
       because $[CF,FD]=[BG,GC]$ and $[FC,CD]=[BC,CG]$.
sim-tri[1]:[1,DMF] [-1,BLG] 
       because $[MD,DF]=[GB,BL]$ and $[DM,MF]=[GL,LB]$.
sim-tri[0]:[1,PMH] [-1,PLE] 
       because $[MP,PH]=[EP,PL]$ and $[PM,MH]=[EL,LP]$.
sim-tri[0]:[1,PMH] [-1,ANE] 
       because $[MP,PH]=[EA,AN]$ and $[PM,MH]=[EN,NA]$.
sim-tri[0]:[1,PMH] [-1,ANE] [-1,PLE] 
       because sim-tri[0]:[1,PMH] [-1,PLE]  and sim-tri[0]:[1,PMH] [-1,ANE] .
sim-tri[0]:[1,PMH] [1,AKH] 
       because $[MP,PH]=[KA,AH]$ and $[PM,MH]=[AK,KH]$.
sim-tri[1]:[1,PMH] [1,AKH] [-1,ANE] [-1,PLE] 
       because sim-tri[0]:[1,PMH] [-1,ANE] [-1,PLE]  and sim-tri[0]:[1,PMH] [1,AKH] .
sim-tri[0]:[1,PNG] [-1,PKF] 
       because $[NP,PG]=[FP,PK]$ and $[PN,NG]=[FK,KP]$.
sim-tri[0]:[1,PNG] [-1,CMF] 
       because $[NP,PG]=[FC,CM]$ and $[PN,NG]=[FM,MC]$.
sim-tri[0]:[1,PNG] [-1,CMF] [-1,PKF] 
       because sim-tri[0]:[1,PNG] [-1,PKF]  and sim-tri[0]:[1,PNG] [-1,CMF] .
sim-tri[0]:[1,PNG] [1,CLG] 
       because $[NP,PG]=[LC,CG]$ and $[PN,NG]=[CL,LG]$.
sim-tri[1]:[1,PNG] [1,CLG] [-1,CMF] [-1,PKF] 
       because sim-tri[0]:[1,PNG] [-1,CMF] [-1,PKF]  and sim-tri[0]:[1,PNG] [1,CLG] .
sim-tri[0]:[1,PLG] [-1,PKH] 
       because $[LP,PG]=[HP,PK]$ and $[PL,LG]=[HK,KP]$.
sim-tri[0]:[1,PLG] [1,DNG] 
       because $[LP,PG]=[ND,DG]$ and $[PL,LG]=[DN,NG]$.
sim-tri[0]:[1,PLG] [1,DNG] [-1,PKH] 
       because sim-tri[0]:[1,PLG] [-1,PKH]  and sim-tri[0]:[1,PLG] [1,DNG] .
sim-tri[0]:[1,PLG] [-1,DMH] 
       because $[LP,PG]=[HD,DM]$ and $[PL,LG]=[HM,MD]$.
sim-tri[1]:[1,PLG] [-1,DMH] [1,DNG] [-1,PKH] 
       because sim-tri[0]:[1,PLG] [1,DNG] [-1,PKH]  and sim-tri[0]:[1,PLG] [-1,DMH] .
sim-tri[0]:[1,PMF] [-1,PNE] 
       because $[MP,PF]=[EP,PN]$ and $[PM,MF]=[EN,NP]$.
sim-tri[0]:[1,PMF] [-1,BLE] 
       because $[MP,PF]=[EB,BL]$ and $[PM,MF]=[EL,LB]$.
sim-tri[0]:[1,PMF] [-1,BLE] [-1,PNE] 
       because sim-tri[0]:[1,PMF] [-1,PNE]  and sim-tri[0]:[1,PMF] [-1,BLE] .
sim-tri[0]:[1,PMF] [1,BKF] 
       because $[MP,PF]=[KB,BF]$ and $[PM,MF]=[BK,KF]$.
sim-tri[1]:[1,PMF] [1,BKF] [-1,BLE] [-1,PNE] 
       because sim-tri[0]:[1,PMF] [-1,BLE] [-1,PNE]  and sim-tri[0]:[1,PMF] [1,BKF] .
sim-tri[1]:[1,EBC] [-1,FBA] 
       because $[BE,EC]=[AF,FB]$ and $[EB,BC]=[AB,BF]$.
sim-tri[1]:[1,CLE] [-1,AKF] 
       because $[LC,CE]=[FA,AK]$ and $[CL,LE]=[FK,KA]$.
sim-tri[1]:[1,HDC] [-1,GDA] 
       because $[DH,HC]=[AG,GD]$ and $[HD,DC]=[AD,DG]$.
sim-tri[1]:[1,CMH] [-1,ANG] 
       because $[MC,CH]=[GA,AN]$ and $[CM,MH]=[GN,NA]$.
sim-tri[1]:[1,POL] [1,OPN] 
       because $[OP,PL]=[PO,ON]$ and $[PO,OL]=[OP,PN]$.
sim-tri[1]:[1,PFL] [-1,PHN] 
       because $[FP,PL]=[NP,PH]$, $[PL,LF]=[HN,NP]$, $[PN,PL]=[PN,ON]$, $[OL,PL]=[PN,ON]$, $[OL,PL]=[PN,PH]$.
sim-tri[0]:[1,PNL] [-1,PHF] 
       because $[NP,PL]=[FP,PH]$, $[PL,LN]=[HF,FP]$, $[PN,PL]=[PN,ON]$, $[OL,PL]=[PN,ON]$, $[OL,PL]=[PN,PH]$.
sim-tri[0]:[1,PNL] [1,OLN] 
       because $[NP,PL]=[LO,ON]$, $[PN,NL]=[OL,LN]$, $[PN,PL]=[PN,ON]$, $[PN,PL]=[OL,ON]$, $[PN,PL]=[PN,ON]$, $[OL,PL]=[PN,ON]$, $[OL,PL]=[PN,PH]$.
sim-tri[1]:[1,PNL] [1,OLN] [-1,PHF] 
       because sim-tri[0]:[1,PNL] [-1,PHF]  and sim-tri[0]:[1,PNL] [1,OLN] .
sim-tri[1]:[1,HBA] [-1,EDA] 
       because $[BH,HA]=[AE,ED]$ and $[HB,BA]=[AD,DE]$.
sim-tri[1]:[1,DEN] [-1,BHK] 
       because $[ED,DN]=[KB,BH]$ and $[DN,NE]=[HK,KB]$.
sim-tri[0]:[1,LEN] [-1,LGN] 
       because $[EL,LN]=[NL,LG]$ and $[LE,EN]=[NG,GL]$.
sim-tri[0]:[1,LEN] [-1,PGD] 
       because $[EL,LN]=[DP,PG]$ and $[LE,EN]=[DG,GP]e eqang [0][GP,GE] = [LK,LE] = [Nbecause $[BH,HA,BFP] 
       CG] 
       because $[CD,DP]=[GP,PC]$ and $[DC,CP]=[GC,CP]$.
sim-tri[0]:[1,CPD] [-ri[1]:[1,PNL] [1,OLGD] 
       be1]:[1,HBA] [-1,EGD] 
       because $[EL,LN]=[DP,PG]$ and $[LE,EN]=[DG,GP]e eqang [0][GP,GE] = [LK,LE] = [       because $[ED,DN]=[:[1,DEN] [-1,BHK] 
       beri[1]:[1,PNL] [1,OLGD] 
       because $[EL,LN]=[DP,PG]$ and $[LE,EN]=[DG,GP]e eqang [0][GP,GE] = [LK,LE] = [ $[PL,LN]=[HF,FP]B 
     E  because $[ED,DN]=[KBBsim-triNE= [AM,MH]=[GN $[K.
sim-tri[1]:[1,HBA] [-1,E]B 
     E  be1,BOC] 
       becA]=[KN,NP]$.
sim-tri[1]:[1,HEA]C] 
       because $[Mri[1]:[1,PNL] [1,OLNB 
     E  be $[PL,LN]=[HF,FPnfigu      [1,BKL] [1,BAC]  and MMO]$.
sK.
sim-ilar triangL .
sim-ri[0]:[1,LEN] [-1,Lnfigu      [1,[ .

Thecause $[ED,DN]=[:[1,DEN] [-1,B

The configuri[1]:[1,PNL] [1,OLnfigu      [1,1]:[1,HBA] [-1,Enfigu      [1,[ .

The      because $[MK,KN]=[K-tri[1]:[1,HEA]L] 
       becri[1]:[1,PNL] [1,OLnfigu      [1,[ .

The0]:[1,LEN] [-1,LE because  because $[OP,PK]=[PO,PE= [Aowing ,DCP] [-1,Eim-tri[K0]:[1,cause sim-tri[0]: because  bec1,OPM] 
 ause $[MK,KN]=[K-tri[1]:[1,HEA]M] 
       becri[1]:[1,PNL] [1,OL: because  bec0]:[1,LEN] [-1,LnH 
     KF:[1,DNP] [1,PLC]  andHM]$.
siMim-triilar triH[HA,= = [FKi[0]:[1,LEN] [-1,LnH 
      [1,AHP] [-1,APD]  and sM]$.
sim $[EB,BC]=[ABiH[HA,= Om-tri[1]:[1,CLE] [-1,LnH 
      [1,A[cause $[HP,PD]=[CP,PF]$, $[PH,HD]=[CF,FP]$]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-1,PFC] [-1,BFP] [1,BPC] [1,BKO] [-1,AKOPD]=[CP,PF]$, $[PH,HD]=[CF,FP]$, $[PH,BP]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-ri[1]:[1,PNL] [1,OLnH 
      [1,A1]:[1,HBA] [-1,EnH 
      [1,A[cause $[HP,PD]=[CP,PF]$, $[PH,HD]=[CF,FP]$]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-    KF:[1,DNP] [1,PLC]  a:[1,LEN] [-1,LnH 
     KF:[1ri[1]:[1,PNL] [1,OLnH 
      [1,A[cause $[HP,PD]=[CP,PF]$, $[PH,HD]=[CF,FP]$]=[PL,ML]$.
sim-tri[0]:[1,PHD] [-OB] 
       becauAP]=[OAH  because $[NP,PG]=[FP$, $[P=O] =Htriilar trA $[DP
sim-tri[1]:[1,EBC] [-1,FuAP]=[OAH  be[] [-1,OMD] [1,AHP] [-1,APD]  an] 
       because $[NA,AP]=[OA,AB]$-tri[0]:[1,ANP] [1,AAP]=[OAH  be0]:[1,DMP] [-1,AOD] 
    E  because $[CF,FD]=[BG,GC] .
siE= [O,CD]=[BC,CG]P.
sim-tri[1]:[1,HBA] [-1,EDD] 
    E  be1,PHD  because $[MD,DP]=[DA,AO]$ and $[DP,PM]=[OD,DA]$.
si  because $[Mi[0]:[1,DMP] [-1,AOD] [-1,AKE  because sim-tri[0]    be,AKHFbecause $[OP,PK]=[PO,sim-tr=BiH[Htriilar trsim-tr=BiF[FG,[1]:[1,HDC] [-1,GD   be,AKHFbec[,DOC] 
    se $[ND,DP]=[OD,DC]$ and $[DP,PN]=OC] 
       because $i[0]:[1,DMP] [-1,AOD   be,AKHFbece [0]:AK*KF = PK*KH .
con-seg[1]:NE = NG 
  erp[$CO,GFtuse [0]:A[0]:DEN] [-1,B

The configuause $[OP,PK]=[POcause $KM = MK$ and $[KMN] sim [MKL][1]:[1,HEA]L] 
       because $[OP,PK]=[PO     LNMN]=[MK,KLKd $[KMNL-tri[0[0]:DEN] [-1,B   becau] [-1ause $[OP,PK]=[POc     NMN]=[MK,   be$[KMN]tri[0][0]:DEN] [-1,B
N[1,MLO] [1,AHP] [-1,APD]  andMPD =KLMN]=[MK,
N[1,$[KMNKtri[0][0]:DEN]1[-1,B
N[1,MLO] [1,A1,B   beHP] [-1,APD]  an[0]:DEN] [-1,B   becau] [-1N]=[M[0]:DEN] [-1,B
N[1,MLO] [1,A [MKL][1]:[1,HEA]   bec- $[DB,BC]=[PL,LM]$.
sim$MPD =KOMN]=[MK,
  be$[KMNK-tri[0[0]:DEN] [-1,B
]$ and $[DB,BC]=[PL,LM]$.
sim$MCD =KOMN]=[MK,
C be$[KMNK-tri[0[0]:DEN]1[-1,B
]$ and $[DB,BHEA]   beHP] [-1,APD]  an[0]:DEN] [-1EA]   bec- $[DB,BN]=[M[0]:DEN] [-1,B
]$ and $[DB,B[0[0]:DEN] [-1,BD] [-1,BPA] [C]=[PL,LM]$.
sim$DfigurC,CD]=[BC,Ond $[KMNOCsim [MKL][1]:[1,HBLO] [-1,PG   because $[MD,DP]=[PDfigurB,CD]=[BC,Ond $[KMNOBsim [MKL][1]:[1,HEA]D] [-1,BP] 
     PA] [C]=[PL,LM]$.
sim[0]:DEN] [-1,BD] [-1,BPA] [N]=[M[0]:DEN] [-1,BO] [-1,PG   be [MKL][1]:[1,HBLA] [-1,PG A] [C]=[PL,LM]$.
sim$AfigurC,CD]=[BCAOnd $[KMNOCsim [MKL][1]:11,HBLA] [-1,PG A] [HEA]D] [-1,BP] 
 HP] [-1,APD]  an[0]:DEN] [-1EA]D] [-1,BP] 
     PA] [N]=[M[0]:DEN] [-1,BA] [-1,PG A] [ [MKL][1]:[1,HEA]    becau[GH,HD]=[NM,MP]$.
sim$seg[1]:MN]=[MK,KM be$[KMN[GH,m [MKL][1]:[1,HBL [-1,NMD][GH,HD]=[NM,MP]$.
sim$seg[1]:MN]=[MK,KMDbe$[KMN[GH,m [MKL][1]:11,HBL [-1,NMD][GH,HHEA]    bHP] [-1,APD]  an[0]:DEN] [-1EA]    becau[GH,HN]=[M[0]:DEN] [-1,B [-1,NMD][GH,H [MKL][1]:[1,HBLF,FP]$]=[PL,MD]=[NM,MP]$.
sim$*PH =OM,AC]=[NM,M[1,$[KMNOtri[0]MKL][1]:[1,HBLF,FP]$]$.
sim-D]=[NM,MP]$.
sim$*PH =OM,AC]=[NM,M[1,$[KMNOtDim [MKL][1]:[1,HEA]F,FP]$]=[P:[1,PHD] L,MD]=[NM,MP]$.
simMKL][1]:[1,HBLF,FP]$]=[PL,MN]=[M[0]:DEN] [-1,BF,FP]$]$.
sim- [MKL][1]:[1,HBLA,FP]$]$.
sL,MD]=[NM,MP]$.
sim$APH =OM,AC]=[NMAM[1,$[KMNOtri[0]MKL][1]:11,HBLA,FP]$]$.
sL,MHEA]F,FP]$]=[P:[1HP] [-1,APD]  an[0]:DEN] [-1EA]F,FP]$]=[P:[1,PHD] L,MN]=[M[0]:DEN] [-1,BA,FP]$]$.
sL,M[0[0]:DEN] [-1,B N=[NK,KP]$ aause $[OP,PK]=[POcNon-seMN]=[MK, N be$[KMN]Lri[0][0]:DEN] [-1,B
]$ and $[HE,EA]=[KN,NP]$.
sim$L = PKNMN]=[MK,
]$ a$[KMNKtri[1][0]:DEN]1[-1,B
]$ and $[HE,E1,B N=[NHP] [-1,APD]  an[0]:DEN] [-1,B N=[NK,KP]$ aN]=[M[0]:DEN] [-1,B
]$ and $[HE,E1][0]:DEN]1[-1,Bsim-tri[0]:[1,DNP] [1,PLC]  an:OK = PM,MH]=[GN,Kd $[KMNOPMi[1][0]:DEN]1[-1,BPKMP]$]=[Pbecause $[OP,PK]=[PO*ML =O:MN]=[MK,PKMP]$[KMNOtKi[1][0]:DEN]1[-1,BPG] 
       because $[MC,CH]=[PfigurPM,MH]=[GN,Ld $[KMNOPtri[0[0]:DEN]1[-1,B]$, $[PN,PL]=[PN,ON]$, $[OL,PL*PD =OeMN]=[MK,]$, $$[KMNOLtri[0[0]:DEN] [-1,Bnfigu      [1,BKL] [1,BAC]  andcauseLNMN]=[MK,nfigu$[KMNL-Kri[0[0]:DEN] [-1,Bnfigu      [1,[ .

Thecause $[ED,DN]=[[0]:DEN] [-1,B

The configuN]=[M[0]:DEN] [-1,Bnfigu      [1,1][0]:DEN]1[-1,Bnfigu      [1,[ .

The      because $[MK,KN]=[KMKL][1]:[1,HEA]L] 
       becN]=[M[0]:DEN] [-1,Bnfigu      [1,[ .

The0][0]:AK*KF = PK*KH .
con-seg[1]:NE = NG 
  equal PK*KHs
]1[-BC*DM.
con*Cation contains thecon-seML,D becaFH$].B*M .
coD*g[0]:OM = PK 
     con-seBD$[HbecaFH$].B**PG =AK*BC0]:OM = PK 
     con-seLK,CAbecaFH$].B*MMG =AK*Cation contains theFH$].B**PG =AK*BC0N]=[M]1[-BC*DM.
con*CatcaFH$].D**PG =BC*Dbecause con-seg[0]:H$].B**PG =AK*BC0N]=[M]1[-.B*M .
coD*g[0caFH$].D*DM.
cCD*Dbecause con-seg[0]:H$].B*MMG =AK*CatN]=[M]1[-.B*M .
coD*g[0caFH$]PK*GKM = H*]:NK = ML 
       be[se si$[KMNKtri[0]FH$]PL*GKM = G*]:NK = ML 
       be[se si$[KMNKtri[0]FH$]PL*$KM = MK$ .
con-seg[1]:KH =e[se si$[KMNKtri[0]FH$].B*K = PBC*PNL = MK 
       becH$]PL*$KM = MK$ .N]=[M]1[-.B**PG =AK*BC00]FH$].B*GKM =AC*PNL = MK 
       bee[se si$[KMN] [-[0]FH$].C*K = PBC*GNL = MK 
       bee[se si$[KMN] [-[0]FH$].B*]:N =AC*P:NM = LK 
       beH$].B*GKM =AC*PNLN]=[M]1[-PK*GKM = H*]:N0]FH$].C*KPG =BC*]:NK = ML 
       bFH$].C*K = PBC*GNLN]=[M]1[-PL*GKM = G*]:N0]FH$].C*M .
coD*]:NK = ML 
       bFH$].B*]:N =AC*P:NN]=[M]1[-.B*M .
coD*g[0caFH$].C*DM.
cCD*]:NK = ML 
       bFH$].B*]:N =AC*P:NN]=[M]1[-.B*MMG =AK*CatcaFH$]BF*GKM = H*FEL = MK 
       bee[se si$[KMN] [-[0]FH$]BE*GKM = G*FEL = MK 
       bee[se si$[KMN] [-[0]FH$]BF*K = PBE*PNL = MK 
       bee[se si$[KMN] [-[0]FH$]BF*LPH = P*FEL = MK 
       beFH$]BF*GKM = H*FELN]=[M]1[-PK*GKM = H*]:N0]FH$].B*FEL =AC*B .
con-seg[1]:LE = H$]BF*GKM = H*FELN]=[M]1[-.B*GKM =AC*PNL0]FH$]BE*LPH = L*FEL = MK 
       beFH$]BE*GKM = G*FELN]=[M]1[-PL*GKM = G*]:N0]FH$].C*BE= PBC*FEL = MK 
       beFH$]BE*GKM = G*FELN]=[M]1[-.C*K = PBC*GNL0]FH$]BF*KL= PBE*P:NK = ML 
       bFH$]BF*K = PBE*PNLN]=[M]1[-PL*$KM = MK$ .0]FH$].B*BE= PBC*B .
con-seg[1]:LE = H$]BF*K = PBE*PNLN]=[M]1[-.B*K = PBC*PNL0]FH$]OM*GKM = H*N[0]:OK = PM 
     e[se si$[KMN]tri[0]0]:OM *GKM = G*N[0]:OK = PM 
     e[se si$[KMN]tri[0]0]:OM *$KM =se $ .
con-seg[1]:KH =e[se si$[KMN]tri[0]00ause $KM = MK$ a
con-seg[1]:LE = H$]OM*GKM = H*N[0N]=[M]1[-PK*GKM = H*]:N0]F0K = BK = PK  and
con-seg[1]:LE = H$]O *GKM = G*N[0N]=[M]1[-PL*GKM = G*]:N0]FH$]BD*PM.
cCD*seg[0]:AK = BK = PK [se si$[KMN]PL-[0]FH$]BCCL = PBD*Peg[0]:AK = BK = PK [se si$[KMN]PL-[0]FH$].B*MPG =AK*Bation contains theFH$]BD*PM.
cCD*segN]=[M]1[-.B*MMG =AK*CatcaFH$]ADCL = PBD*Dbecause con-seg[0]:H$]BD*PM.
cCD*segN]=[M]1[-.D*DM.
cCD*Dbe0]FH$].C*L = PBD*]:NK = ML 
       bFH$]BD*PM.
cCD*segN]=[M]1[-.C*DM.
cCD*]:N0]FH$]BD*PH.
cCD*ENL = MK 
       bee[se si$[KMNH-tri[0FH$]BCCPH.
cCD*PEL = MK 
       bee[se si$[KMNH-tri[0FH$]BCCEH= PBD*PEL = MK 
       bee[se si$[KMNH-tri[0FH$]PMCEH= P H*seg[0]:AK = BK = PKFH$]BD*PH.
cCD*ENLN]=[M]1[-BD*PM.
cCD*seg[0FH$]BL*$KM = E*g[0]:OK = PM 
     FH$]BCCPH.
cCD*PELN]=[M]1[-BC*DM.
con*CatcaFH$].B*K = PCD*PEL = MK 
       beFH$]BCCPH.
cCD*PELN]=[M]1[-.B*K = PBC*PNL0]FH$]PL*EKM = E*seg[0]:AK = BK = PKFH$]BCCEH= PBD*PELN]=[M]1[-BC*L = PBD*PegcaFH$]PK*K = PKE*g[0]:OK = PM 
     FH$]BL*$KM = E*g[0N]=[M]1[-PL*$KM = MK$ .0]FH$]BD*CF.
cCD*G .
con-seg[1]:LE =e[se si$[KMNFtri[1]]1[-BC*CF.
cCD*C .
con-seg[1]:KH =e[se si$[KMNFtri[1]]1[-BC*GF= PBD*C .
con-seg[1]:KH =e[se si$[KMNFtri[1]]1[-CFCL = PL  G .
con-seg[1]:LE =FH$]BD*CF.
cCD*G .N]=[M]1[-BD*PM.
cCD*seg[0FH$]CFCEH= P H*G .
con-seg[1]:LE =FH$]BD*CF.
cCD*G .N]=[M]1[-BD*PH.
cCD*ENL[0FH$]BL*CF.
cCG*g[0]:OK = PM 
     FH$]BCCCF.
cCD*C .N]=[M]1[-BC*DM.
con*CatcaFH$]CG*PH.
cCF*PEL = MK 
       beFH$]BCCCF.
cCD*C .N]=[M]1[-BC*PH.
cCD*PELcaFH$]CG*L = PLL*G .
con-seg[1]:LE =FH$]BC*GF= PBD*C .N]=[M]1[-BC*L = PBD*PegcaFH$]CG*EKM = E*G .
con-seg[1]:LE =FH$]BC*GF= PBD*C .N]=[M]1[-BC*EH= PBD*PEL0]FH$]BD*:OK =CD*N:NK = ML 
       be[se si$[KMNK-tri[0]1[-BC*:OK =CD*Obecause con-seg[0]e[se si$[KMNK-tri[0]0L = CL = PL  and
con-seg[1]:LE =FH$]BD*:OK =CD*N:NN]=[M]1[-BD*PM.
cCD*seg[0FH$]AOPM = D.D*DM.cause con-seg[0]e[sM be$[KMNi  b$[0FH$]AOPM = DCD*Dbecause con-seg[0]:H$].OPM = D.D*DM.N]=[M]1[-.D*DM.
cCD*Dbe0]FH$].P*MMG =AK*Pation contains thee[sM be$[KMNiKri[0]FH$].B*K = D.P*Cation contains theFH$].P*MMG =AK*PatN]=[M]1[-.B*MMG =AK*CatcaFH$]AP*K = PKD*PEL = MK 
       beFH$].P*MMG =AK*PatN]=[M]1[-PK*K = PKE*g[0[0FH$]AOPAP= D.D*A:NK = ML 
       bFH$].P*MMG =AK*PatN]=[M]1[-.OPM = D.D*DM.caFH$].B*M .
coOPAP=K = ML 
       bFH$].B*K = D.P*CatN]=[M]1[-.OPM = DCD*Dbe0]FH$]CPPM = DCD*$ .
con-seg[1]:KH =e[ON] [$[KMNPtri[1]]1[-CP*CP.
cCD*C .
con-seg[1]:KH =e[se] [$[KMNPtri[1]]1[-CP*K = PCG*Pation contains thee[se] [$[KMNPtri[1]]1[-.OPM = PCP*Dbecause con-seg[0]:H$]CPPM = DCD*$ .N]=[M]1[-.OPM = DCD*Dbe0]FH$].B*K = PAP*CP.cause con-seg[0]:H$]CPPM = DCD*$ .N]=[M]1[-.B*K = D.P*CatcaFH$]AP*CP.
cCD*PEL = MK 
       beFH$]CPPM = DCD*$ .N]=[M]1[-.P*K = PKD*PEL1]]1[-BC*CF.
cCP*CP.cause con-seg[0]:H$]CPPCP.
cCD*C .N]=[M]1[-BC*CF.
cCD*C .caFH$]AP*C = PCP*PEL = MK 
       beFH$]CPPM = PCG*PatN]=[M]1[-.P*K = PKD*PEL1]]1[-AP*CP.
cBC*PNL = MK 
       becH$].B*K = PAP*CP.N]=[M]1[-.B*K = PBC*PNL0]FH$]AP*CF.
cCP*PNL = MK 
       becH$].P*C = PCP*PELN]=[M]1[-CG*PH.
cCF*PEL0]FH$]AP*GF.
cCP*ENL = MK 
       becH$].P*C = PCP*PELN]=[M]1[-CG*EKM = E*G .1]]1[-BPPM = DCD*$EL = MK 
       bee[se] [$[KMNPBtri[0FH$]BP*CP.
cBE*Cation contains thee[se] [$[KMNPBtri[0FH$]BEPM = DCP*PEL = MK 
       bee[se] [$[KMNPBtri[0FH$]BC*PH.
cBPPM =
con-seg[1]:LE =FH$]BPPM = DCD*$ELN]=[M]1[-BC*PH.
cCD*PELcaFH$].B*K = PBPPM =
con-seg[1]:LE =FH$]BPPM = DCD*$ELN]=[M]1[-.B*K = PCD*PEL1]]1[-.OPME= PBPPDbecause con-seg[0]:H$]BPPM = DCD*$ELN]=[M]1[-.OPM = DCD*Dbe0]FH$].B*KE= PAP*BP.cause con-seg[0]:H$]BPPM = DCD*$ELN]=[M]1[-.B*K = D.P*CatcaFH$]BPPM = PCP*PEL = MK 
       beFH$]BPPM = DCD*$ELN]=[M]1[-CPPM = DCD*$ .1]]1[-AP*CP.
cBPPM =
con-seg[1]:LE =FH$]BPPM = DCD*$ELN]=[M]1[-.P*CP.
cCD*PELcaFH$]BPPM = PBEPM =
con-seg[1]:LE =FH$]BPPCP.
cBE*CatN]=[M]1[-CPPM = DCD*$ .1]]1[-BP*C = PBE*CP.cause con-seg[0]:H$]BPPCP.
cBE*CatN]=[M]1[-CPPCP.
cCD*C .1]]1[-AP*BE= PBPPPEL = MK 
       beFH$]BPPCP.
cBE*CatN]=[M]1[-.P*CP.
cCD*PELcaFH$]BEPM = PCG*PEL = MK 
       beFH$]BE*M = DCP*PELN]=[M]1[-CPPM = PCG*PatcaFH$]AP*C = PBEPM =
con-seg[1]:LE =FH$]BE*M = DCP*PELN]=[M]1[-.P*C = PCP*PELcaFH$].B**P.
cBPPCation contains theFH$].B*K = PBPPM =N]=[M]1[-CPPM = DCD*$ .1]]1[-AB*C = PBP*CP.cause con-seg[0]:H$].B*K = PBPPM =N]=[M]1[-CPPM = PCG*PatcaFH$]BP*MMG =CPPMnd
con-seg[1]:LE =FH$]BPPM = PCP*PELN]=[M]1[-PK*K = PKE*g[0[0FH$]AP*C = PBP*and
con-seg[1]:LE = H$]BPPM = PCP*PELN]=[M]1[-.P*C = PCP*PELcaFH$]BPPGKM = D*FEL = MK 
       beFH$]BPPM = PBEPM =N]=[M]1[-BE*GKM = G*FELcaFH$]BPPMH.
cBFPM =
con-seg[1]:LE =FH$]BPPM = PBEPM =N]=[M]1[-BF*K = PBE*PNL0]FH$].B*BE= PBP*BP.cause con-seg[0]:H$]BPPM = PBEPM =N]=[M]1[-.B*K = PBPPM =1]]1[-AB*C = PBE*Cation contains theFH$]BEPM = PCG*PELN]=[M]1[-.B*K = PCD*PEL1]]1[-BE*MMG =CGPMnd
con-seg[1]:LE =FH$]BEPM = PCG*PELN]=[M]1[-PK*K = PKE*g[0[0FH$]BC*B . PBP*BP.cause con-seg[0]:H$]BPPMH.
cBFPM =N]=[M]1[-BC*PH.
cBPPM =1]]1[-CP*ODK =CD*Obecause con-seg[0]e[se] [$[KMND-tri[0]1[-CP*Dbe= = BKation contains thee[se] [$[KMND-tri[0]1[-BC*MMG =CPPOation contains theFH$]CP*ODK =CD*ObeN]=[M]1[-BC*DM.
con*CatcaFH$]OD*K = P= BKation contains theFH$]CP*ODK =CD*ObeN]=[M]1[-CPPM = DCD*$ .1]]1[-CP*ON= PCG*Oation contains theFH$]CP*ODK =CD*ObeN]=[M]1[-CPPCP.
cCD*C .1]]1[-AP*ON= POD*PEL = MK 
       beFH$]CPPODK =CD*ObeN]=[M]1[-.P*CP.
cCD*PELcaFH$]BPPON= PBEPOation contains theFH$]CP*ODK =CD*ObeN]=[M]1[-BPPCP.
cBE*Cat1]]1[-AB*ON= PBPPOation contains theFH$]CP*ODK =CD*ObeN]=[M]1[-.B**P.
cBPPCatcaFH$].D**P.
cBC*Pation contains theFH$]CP*Dbe= = BKatN]=[M]1[-.D**PG =BC*DbecaFH$]CG*Dbe= = BKnd
con-seg[1]:LE = H$]CP*Dbe= = BKatN]=[M]1[-CPPM = PCG*PatcaFH$]BE*Dbe= = BKEL = MK 
       beFH$]CPPDbe= = BKatN]=[M]1[-BE*M = DCP*PEL1]]1[-AP*ON= PBPPDbecause con-seg[0]:H$]CP*Dbe= = BKatN]=[M]1[-.P*CP.
cBPPM =[0]1[-CP*DM.
cCF*Oation contains theFH$]BC*MMG =CPPOatN]=[M]1[-BC*CF.
cCP*CP.0]FH$].P*MMG =OD*PNL = MK 
       becH$]BC*MMG =CPPOatN]=[M]1[-AP*CP.
cBC*PNL[0FH$]BC*PK= PBPPOation contains theFH$]BC*MMG =CPPOatN]=[M]1[-BP*MMG =CPPMndcaFH$]OD*GKM = D*]:NK = ML 
       bFH$]OD*K = P= BKatN]=[M]1[-PL*GKM = G*]:N0]FH$]OD*PH.
cKD*P:NK = ML 
       bFH$]OD*K = P= BKatN]=[M]1[-PL*$KM = MK$ .0]FH$]CPPL = POD*G .
con-seg[1]:LE =]1[-CP*ON= PCG*OatN]=[M]1[-CG*L = PLL*G .0]FH$].P*L = POD*ENL = MK 
       becH$].P*ON= POD*PELN]=[M]1[-PL*EKM = E*segcaFH$]BPPLPH =OD*FEL = MK 
       beFH$]BPPON= PBEPOatN]=[M]1[-BE*LPH = L*FELcaFH$]BPPMK.
cBFPOation contains theFH$]BPPON= PBEPOatN]=[M]1[-BF*KL= PBE*P:NcaFH$].D** = PBC*Pnd
con-seg[1]:LE = H$].D**P.
cBC*PatN]=[M]1[-CPPM = PCG*PatcaFH$]AD*CF.
cCP*Pation contains theFH$].D**P.
cBC*PatN]=[M]1[-BC*CF.
cCP*CP.0]FH$].P*M = D.D*PNL = MK 
       becH$].D**P.
cBC*PatN]=[M]1[-AP*CP.
cBC*PNL[0FH$]oD*gE= PBC*PEL = MK 
       beFH$].D**P.
cBC*PatN]=[M]1[-BE*M = DCP*PEL1]]1[-AP*BC= D.D*BP.cause con-seg[0]:H$].D**P.
cBC*PatN]=[M]1[-AP*CP.
cBPPM =[0]1[-CF*Dbe= PG*g[0]:OK = PM 
     FH$]CG*Dbe= = BKndN]=[M]1[-BL*CF.
cCG*g[0caFH$]PG*L = PDN*G .
con-seg[1]:LE =]1[-CG*Dbe= = BKndN]=[M]1[-CG*L = PLL*G .0]FH$]PE*LPH =DN*FEL = MK 
       beFH$]BE*Dbe= = BKELN]=[M]1[-BE*LPH = L*FELcaFH$]BF*Dbe= PK*PEL = MK 
       beFH$]BE*Dbe= = BKELN]=[M]1[-BF*KL= PBE*P:NcaFH$].P*BP. D.D*B .
con-seg[1]:LE =FH$]BPPMK.
cBFPOatN]=[M]1[-.OPAP= D.D*A:NcaFH$]AD*CF.
cCD*$ .
con-seg[1]:KH =FH$].D** = PBC*PndN]=[M]1[-BC*CF.
cCD*C .caFH$]AD*GF= PBD*$ .
con-seg[1]:KH =FH$].D** = PBC*PndN]=[M]1[-BC*GF= PBD*C .0]FH$].C*KE= D.D*FEL = MK 
       beFH$]oD*gE= PBC*PELN]=[M]1[-.C*BE= PBC*FEL0]FH$].B*KE= PAD*B .
con-seg[1]:LE =FH$]oD*gE= PBC*PELN]=[M]1[-.B*BE= PBC*B .[0]1[-COPM = DCD*Oeg[0]:AK = BK = PK [se] [$[KMNOCsim []1[-CP*O]:ON*PBKation contains thee[se] [$[KMNOCsim []1[-.D*DM.
cCD*Oeg[0]:AK = BK = PK]1[-COPM = DCD*OegN]=[M]1[-.OPM = D.D*DM.caF0M = PM = DM  and[0]:AK = BK = PK]1[-COPM = DCD*OegN]=[M]1[-.OPM = DCD*Dbe0]FH$]CD*D = PKD*Pation contains thee[se] [$[KMNDPri[1]]1[-CP*D = PKD*P .
con-seg[1]:KH =e[se] [$[KMNDPri[1]]1[-.OPD = PKD*and[0]:AK = BK = PK]1[-CD*D = PKD*PatN]=[M]1[-.OPM = DCD*Dbe0]FH$].B*D = PAP*Pation contains theFH$]CD*D = PKD*PatN]=[M]1[-.B*K = D.P*CatcaFH$]BPPD = PKD*PEL = MK 
       beFH$]CD*D = PKD*PatN]=[M]1[-BPPM = DCD*$ELcaFH$]CG*DGe= PG*Knd
con-seg[1]:LE = H$]CP*D = PKD*P .N]=[M]1[-CPPM = PCG*PatcaFH$]BE*DGe= PG*KEL = MK 
       beFH$]CPPD = PKD*P .N]=[M]1[-BE*M = DCP*PEL1]]1[-AP*K = PBPPDnd
con-seg[1]:LE = H$]CP*D = PKD*P .N]=[M]1[-AP*CP.
cBPPM =[0]1[-= BDGe= PG*Dbecause con-seg[0]:H$]CP*D = PKD*P .N]=[M]1[-CPPDbe= = BKat []1[-.D*K = PBC*Dnd
con-seg[1]:LE = H$]CP*D = PKD*P .N]=[M]1[-AD**P.
cBC*Pat []1[-D *GKM =DG*]:NK = ML 
       b]1[-.OPD = PKD*andN]=[M]1[-OD*GKM = D*]:NcaFH$]PK*D = PKH*Dbecause con-seg[0]:H$].OPD = PKD*andN]=[M]1[-OD*PH.
cKD*P:N0]FH$].B*D = PAD*PNL = MK 
       becH$].B*D = PAP*PatN]=[M]1[-.P*K = D.D*PNL0]FH$]PE*GKM =DG*FEL = MK 
       beFH$]BPPD = PKD*PELN]=[M]1[-BPPGKM = D*FELcaFH$]BF*D = PKE*PNL = MK 
       becH$]BPPD = PKD*PELN]=[M]1[-BPPMH.
cBFPM =0]FH$].C*D = PAD*GNL = MK 
       be]1[-.D*K = PBC*DndN]=[M]1[-.C*K = PBC*GNL0]FH$].P*K = D.E*Cation contains thee[se] [$[KMNA-tri[0FH$]AE**P.
cKD*PEL = MK 
       bee[se] [$[KMNA-tri[0FH$]AOPAE= PAP*Dbecause con-seg[0]:H$].P*K = D.E*CatN]=[M]1[-.OPM = DCD*Dbe0]FH$].B*AE= PAP*AP=K = ML 
       bFH$].P*K = D.E*CatN]=[M]1[-.B*K = D.P*CatcaFH$]AP*K = PAE*CP.cause con-seg[0]:H$].P*K = D.E*CatN]=[M]1[-CPPM = DCD*$ .1]]1[-AP*KE= PAE*BP.cause con-seg[0]:H$].P*K = D.E*CatN]=[M]1[-BPPM = DCD*$ELcaFH$]AD*PH.
c.E*Cation contains theFH$].P*K = D.E*CatN]=[M]1[-.P*K = D.D*PNL0]FH$]APPD = PAEPM =
con-seg[1]:LE =FH$].P*K = D.E*CatN]=[M]1[-CD*D = PKD*Pat0]FH$].B*D = PAE*Cation contains theFH$].P*K = D.E*CatN]=[M]1[-.B*D = PAP*Pat[0FH$]AE**Ge= PG*KEL = MK 
       beFH$]AE**P.
cKD*PELN]=[M]1[-CPPM = PCG*PatcaFH$]AE*BE= PKE*PEL = MK 
       beFH$]AE**P.
cKD*PELN]=[M]1[-BE*M = DCP*PEL1]]1[-AEPON= PPE*Dbecause con-seg[0]:H$].E**P.
cKD*PELN]=[M]1[-CPPDbe= = BKat []1[-.D*KE= PAE*BC0]:OM = PK 
     :H$].E**P.
cKD*PELN]=[M]1[-AD**P.
cBC*Pat []1[-AE*K = PKE*g .
con-seg[1]:KH =FH$].E**P.
cKD*PELN]=[M]1[-CPPD = PKD*P .[0FH$]AE**P.
cBPPDnd
con-seg[1]:LE = H$]AE**P.
cKD*PELN]=[M]1[-BPPD = PKD*PEL[0FH$]AE*DM.
cKH*Dbecause con-seg[0]:H$].OPAE= PAP*DbeN]=[M]1[-.P*MMG =OD*PNL[0FH$]AE*L = PDN*ENL = MK 
       becH$].OPAE= PAP*DbeN]=[M]1[-.P*L = POD*ENL[0FH$]AE**Fe= PG*KNL = MK 
       becH$].P*K = PAE*CP.N]=[M]1[-AP*CF.
cCP*PNL[0FH$]AE*GFe= PG*ENL = MK 
       becH$].P*K = PAE*CP.N]=[M]1[-AP*GF.
cCP*ENL []1[-.D*EH.
c.E*Bation contains theFH$]AD*PH.
c.E*CatN]=[M]1[-BD*PH.
cCD*ENL[0FH$]AK*D = PAE*g[0]:OK = PM 
     FH$]APPD = PAEPM =N]=[M]1[-.P*MMG =AK*Pat[0FH$]AE**Ge= BE*g .
con-seg[1]:KH =FH$].PPD = PAEPM =N]=[M]1[-.P*C = PBEPM =0]FH$]PM*GKM =DH*N[0]:OK = PM 
     e[e sim$[KMNNMri[0]FH$]P BDKM = M*g .
con-seg[1]:KH =e[e sim$[KMNNMri[0]FH$]Pe $KM = MKDNL = MK 
       becH$]PM*GKM =DH*N[0N]=[M]1[-PK*GKM = H*]:N0]FH$]PMBK = PK  DNL = MK 
       becH$]PM*GKM =DH*N[0N]=[M]1[-PL*GKM = G*]:N0]FH$].C*MH.
cCD*GNL = MK 
       be]1[-PM*GKM =DH*N[0N]=[M]1[-.C*DM.
cCD*]:N0]FH$]OD*DH.
cKD*P[0]:OK = PM 
     FH$]PM*GKM =DH*N[0N]=[M]1[-OD*GKM = D*]:NcaFH$]AD*MH.
cCD*g .
con-seg[1]:KH =FH$]P BDKM = M*g .N]=[M]1[-.D*DM.
cCD*Dbe0]FH$]CFBDGe= PG*DNL = MK 
       be]1[-P BDKM = M*g .N]=[M]1[-CF*Dbe= PG*g[0[0FH$]AE*DKM = H*g .
con-seg[1]:KH =FH$]P BDKM = M*g .N]=[M]1[-.E*DM.
cKH*Dbe0]FH$].B*DH.
cCD*PNL = MK 
       be]1[-PM*$KM = MKDNLN]=[M]1[-.B*MMG =AK*CatcaFH$]PG*$KM = E*gNL = MK 
       be]1[-PM*$KM = MKDNLN]=[M]1[-PK*K = PKE*g[0[0FH$]AP*DH.
cKD*PNL = MK 
       be]1[-PM*$KM = MKDNLN]=[M]1[-.P*MMG =AK*Pat[0FH$]BP*DH.
cCP*PNL = MK 
       becH$]PM*$KM = MKDNLN]=[M]1[-BP*MMG =CPPMndcaFH$]BE*DKM =CG*KNL = MK 
       becH$]PM*$KM = MKDNLN]=[M]1[-BE*MMG =CGPMnd[0]1[-BC*MH.
cCD*P .
con-seg[1]:KH =FH$]PMBK = PK  DNLN]=[M]1[-BC*DM.
con*CatcaFH$]CG*DH.
cCF*P .
con-seg[1]:KH =FH$]PMBK = PK  DNLN]=[M]1[-BL*CF.
cCG*g[0caFH$]CF*Dbe= P  DNL = MK 
       becH$]PM*K = PK  DNLN]=[M]1[-CF*Dbe= PG*g[0[0FH$]AD*DH.
cKD*Pation contains theFH$]OD*DH.
cKD*P[0N]=[M]1[-.OPM = D.D*DM.caFH$]BC*MH.
cCP*Pation contains theFH$]OD*DH.
cKD*P[0N]=[M]1[-BC*MMG =CPPOat1]]1[-CP*DH.
cCF*Pation contains theFH$]OD*DH.
cKD*P[0N]=[M]1[-CP*DM.
cCF*OatcaFH$]BE*DKM =CF*PEL = MK 
       beFH$]CFBDGe= PG*DNLN]=[M]1[-BE*MGe= PG*KEL0]FH$]AP*CF.
cBP*DH. = MK 
       beFH$]CFBDGe= PG*DNLN]=[M]1[-AP*K = PBPPDndcaFH$]AD*CF.
cBC*MH. = MK 
       beFH$]CFBDGe= PG*DNLN]=[M]1[-AD*K = PBC*Dnd[0FH$]AE**Fe= PE*gNL = MK 
       be]1[-CFBDGe= PG*DNLN]=[M]1[-AE*K = PKE*g .caFH$]PF*GKM =DH*FEL = MK 
       bee[e sim$[KMN [1,$caFH$]PF*D = PKE*gNL = MK 
       bee[e sim$[KMN [1,$caFH$]BF*DH.
cKF*KNL = MK 
       becH$]PF*GKM =DH*FELN]=[M]1[-BF*GKM = H*FELcaFH$]BE*DKM =PG*K .
con-seg[1]:LE =FH$]PF*GKM =DH*FELN]=[M]1[-BE*GKM = G*FELcaFH$]BPPDH.
cKD*P .
con-seg[1]:LE =FH$]PF*GKM =DH*FELN]=[M]1[-BPPGKM = D*FELcaFH$]PM*FEL =PF*N[0]:OK = PM 
     FH$]PF*GKM =DH*FELN]=[M]1[-PM*GKM =DH*N[00]FH$].C*KF.
cCD*FEL = MK 
       beFH$]PF*GKM =DH*FELN]=[M]1[-.C*MH.
cCD*GNL0]FH$]P BKF.
cPM*KEL = MK 
       beFH$]PF*D = PKE*gNLN]=[M]1[-P BDKM = M*g . []1[-.D*KF.
cCD*$EL = MK 
       beFH$]PF*D = PKE*gNLN]=[M]1[-AD*MH.
cCD*g .caFH$]CF*KE= PPG*K .
con-seg[1]:LE =FH$]PF*D = PKE*gNLN]=[M]1[-CFBDGe= PG*DNL []1[-AE*KFe= PE*KNL = MK 
       becH$]PF*D = PKE*gNLN]=[M]1[-AE*DKM = H*g .caFH$]PG*$KM = F*g .
con-seg[1]:KH =FH$]PF*D = PKE*gNLN]=[M]1[-PG*$KM = E*gNL[0FH$]AE**Fe= PF*g .
con-seg[1]:KH =FH$]PF*D = PKE*gNLN]=[M]1[-AE**Fe= PE*gNLcaFH$]BF*PM.
cPK*K .
con-seg[1]:LE = H$]BF*DH.
cKF*KNLN]=[M]1[-PM*$KM = MKDNL []1[-AE*KFe= BF*g .
con-seg[1]:KH =FH$]BF*DH.
cKF*KNLN]=[M]1[-AE*DKM = H*g .caFH$].B*KFe= BF*Cation contains theFH$]BF*DH.
cKF*KNLN]=[M]1[-AB*DH.
cCD*PNLcaFH$]BF*PG.
cKF*KEL = MK 
       beFH$]BF*DH.
cKF*KNLN]=[M]1[-PG*$KM = E*gNL[0FH$]AP*KFe= BF*M =
con-seg[1]:LE =FH$]BF*DH.
cKF*KNLN]=[M]1[-AP*DH.
cKD*PNLcaFH$]BPPMFe= BF*CP.cause con-seg[0]:H$]BF*DH.
cKF*KNLN]=[M]1[-BP*DH.
cCP*PNLcaFH$]BF*C = PBEPM .
con-seg[1]:LE = H$]BF*DH.
cKF*KNLN]=[M]1[-BE*DKM =CG*KNLcaFH$]BE*PM.
cPLPM .
con-seg[1]:LE = H$]BE*DKM =PG*K .N]=[M]1[-PM*$ = PK  DNLcaFH$]BE*PH.
cKF*KEL = MK 
       beFH$]BE*DKM =PG*K .N]=[M]1[-PG*$KM = E*gNL[0FH$]CG*PH.
cPG*K .
con-seg[1]:LE =FH$]BE*DKM =PG*K .N]=[M]1[-BE*DKM =CG*KNLcaFH$]BC*KF.
cBE*Cation contains theFH$]BEPDKM =PG*K .N]=[M]1[-BC*MH.
cCD*P .caFH$]BE*CF.
cCG*K .
con-seg[1]:LE =FH$]BE*DKM =PG*K .N]=[M]1[-CG*DH.
cCF*P .caFH$]BPPMMG =OD*P .
con-seg[1]:LE =FH$]BPPDH.
cKD*P .N]=[M]1[-OD*DH.
cKD*P[0[0FH$]AP*KFe= BP*PNL = MK 
       becH$]BPPDH.
cKD*P .N]=[M]1[-AP*DH.
cKD*PNLcaFH$]CP*PH.
cKD*P .
con-seg[1]:LE =FH$]BPPDH.
cKD*P .N]=[M]1[-BP*DH.
cCP*PNLcaFH$].D*KF.
cBPPM =
con-seg[1]:LE =FH$]BPPDH.
cKD*P .N]=[M]1[-AD*DH.
cKD*PatcaFH$]BC*KF.
cBP*CP.cause con-seg[0]:H$]BPPDH.
cKD*P .N]=[M]1[-BC*MH.
cCP*PatcaFH$]BPPCF.
cCP*P .
con-seg[1]:LE =FH$]BPPDH.
cKD*P .N]=[M]1[-CP*DH.
cCF*Pat0]FH$]AP*CF.
cKD*P .
con-seg[1]:LE =FH$]BPPDH.
cKD*P .N]=[M]1[-AP*CF.
cBP*DH.caFH$]BL*$KM = N*K .
con-seg[1]:LE =FH$]P BKF.
cPM*KELN]=[M]1[-BL*$KM = E*g[00]FH$]P BKF.
cPMK$ .
con-seg[1]:KH =FH$]P BKF.
cPM*KELN]=[M]1[-PK*K = PKE*g[0[0FH$]AD*KF.
cBC*PNL = MK 
       becH$].D*KF.
cCD*$ELN]=[M]1[-BC*PH.
cCD*PELcaFH$].B*K = PAD*P .
con-seg[1]:LE =FH$].D*KF.
cCD*$ELN]=[M]1[-.B*K = PCD*PEL1]]1[-.P*CP.
cAD*P .
con-seg[1]:LE =FH$].D*KF.
cCD*$ELN]=[M]1[-.P*CP.
cCD*PELcaFH$].B**F.
cCD*$ .
con-seg[1]:LE =]1[-CF*KE= PPG*K .N]=[M]1[-.B*K = PCD*PEL1]]1[-CF*KKe= PF*g[0]:OK = PM 
     FH$]CF*KE= PPG*K .N]=[M]1[-PK*K = PKE*g[0[0FH$]CF*$KM = F*gNL = MK 
       be]1[-CFBKE= PPG*K .N]=[M]1[-PG*$KM = E*gNL[0FH$]BF*CFe= PF*P .
con-seg[1]:LE =FH$].E*KFe= BF*g .N]=[M]1[-AE**Fe= PF*g .caFH$]CPPMK.
cOD*P .
con-seg[1]:LE =FH$]BPPMFe= BF*CP.N]=[M]1[-BPPMK.
cBFPOat[0FH$]CG*PK.
cPLPM .
con-seg[1]:LE = H$]BF*C = PBEPM .N]=[M]1[-BF*KL= PBE*P:NcaFH$].B** = PBC*P .
con-seg[1]:LE = H$]BF*C = PBEPM .N]=[M]1[-.B*BE= PBC*B .[0]1[-PK*GFe= PF*seg[0]:AK = BK = PKFH$]P BKF.
cPMK$ .N]=[M]1[-PG*L = PDN*G .caFH$].B*GF= PBD*$ .
con-seg[1]:LE =FH$].B*K = PAD*P .N]=[M]1[-AD*GF= PBD*$ .[0FH$]AP*KH.
c.H*Pation contains thee[P sim$[KMNAHri[0]FH$].H*DKM = H*PNL = MK 
       bee[P sim$[KMNAHri[0]FH$].K*KH.
c.H*g[0]:OK = PM 
     FH$]APPKH.
c.H*PatN]=[M]1[-.P*MMG =AK*Pat[0FH$]ABPKH.
c.H*Cation contains theFH$].P*KH.
c.H*PatN]=[M]1[-.B*K = D.P*CatcaFH$]AH*K = PKE*KNL = MK 
       becH$].P*KH.
c.H*PatN]=[M]1[-.P*K = PKD*PEL1]]1[-AP*BP. D.H*BC0]:OM = PK 
     :H$].P*KH.
c.H*PatN]=[M]1[-BC*PH.
cBPPM =1]]1[-.H*CP.
cBPPMNL = MK 
       becH$].P*KH.
c.H*PatN]=[M]1[-.P*CP.
cBPPM =[0]1[-.H*C = PBE*PNL = MK 
       becH$].P*KH.
c.H*PatN]=[M]1[-.P*C = PBEPM =0]FH$]AP*BF. D.H*BP.cause con-seg[0]:H$].P*KH.
c.H*PatN]=[M]1[-BPPMH.
cBFPM =0]FH$].PPMK.
c.H*Oation contains theFH$].P*KH.
c.H*PatN]=[M]1[-OD*PH.
cKD*P:N0]FH$].PPAP= D.D*ANL = MK 
       becH$].P*KH.
c.H*PatN]=[M]1[-.P*K = D.D*PNL0]FH$]AE*KH.
c.H*g .
con-seg[1]:KH =FH$].PPKH.
c.H*PatN]=[M]1[-.P*M = PAEPM =caFH$]AH*KFe= BF*MNL = MK 
       becH$].P*KH.
c.H*PatN]=[M]1[-.P*KF.
cBFPM =0]FH$].PPMF. D.H*CP.cause con-seg[0]:H$].P*KH.
c.H*PatN]=[M]1[-CP*PH.
cKD*P .[0]1[-.H*CFe= PF*PNL = MK 
       becH$].P*KH.
c.H*PatN]=[M]1[-.P*CF.
cKD*P .[0]1[-.K*EH.
c.H*seg[0]:AK = BK = PKFH$].K*KH.
c.H*g[0N]=[M]1[-PM*EH= P H*seg0]FH$].K*KE. D.H*Beg[0]:AK = BK = PKFH$].K*KH.
c.H*g[0N]=[M]1[-BL*$KM = E*g[00]FH$].K*AE. D.H*Dbecause con-seg[0]:H$].K*KH.
c.H*g[0N]=[M]1[-.E*DM.
cKH*Dbe0]FH$].B*EH.
c.H*Bation contains theFH$]ABPKH.
c.H*CatN]=[M]1[-BD*PH.
cCD*ENL[0FH$]AB*KE. D.H*BC0]:OM = PK 
     :H$].BPKH.
c.H*CatN]=[M]1[-BC*PH.
cCD*PELcaFH$].B*AE= D.D*ANL = MK 
       becH$].BPKH.
c.H*CatN]=[M]1[-AD*PH.
c.E*Cat[0]1[-.H*BE= PBF*KEL = MK 
       beFH$]AH*K = PKE*KNLN]=[M]1[-BF*K = PBE*PNL0]FH$].H*K = PBF*g .
con-seg[1]:KH =FH$]AH*K = PKE*KNLN]=[M]1[-BF*D = PKE*PNL []1[-AE*KFe= AH*K =
con-seg[1]:KH =FH$]AH*K = PKE*KNLN]=[M]1[-AE*KFe= PE*KNL[0]1[-.H*C = PKF*KEL = MK 
       beFH$]AH*K = PKE*KNLN]=[M]1[-CG*PH.
cPG*K .[0FH$]AD*BF. D.H*BC0]:OM = PK 
     :H$].P*BP. D.H*BC0N]=[M]1[-.P*BP. D.D*B .1]]1[-.H*CP.
cBF*M =
con-seg[1]:LE =FH$].H*CP.
cBPPMNLN]=[M]1[-BPPMH.
cBFPM =0]FH$].H*C = PBF*P .
con-seg[1]:KH =FH$].H*C = PBE*PNLN]=[M]1[-BF*K = PBE*PNL0]FH$].H*ON= PBF*Dbecause con-seg[0]:H$].P*BF. D.H*BP.N]=[M]1[-.P*ON= PBPPDbecaFH$]AE*BFe= AH*KEL = MK 
       beFH$].P*BF. D.H*BP.N]=[M]1[-.P*KE= PAE*BP.[0]1[-.H*CFe= BF*gNL = MK 
       be]1[-.P*BF. D.H*BP.N]=[M]1[-.P*CF.
cBP*DH.caFH$]AH*GFe= PF*ENL = MK 
       becH$].P*KF. D.H*CP.N]=[M]1[-AP*GF.
cCP*ENL []1[-PG*KE= PKE*M .
con-seg[1]:KH =e[OK] 
$[KMNPEKi[0]FH$]Pe KE= PKK*M .
con-seg[1]:KH =e[OK] 
$[KMNPEKi[0]FH$].B*M = PCD*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-.B*K = PCD*PEL1]]1[-.P*M = PKD*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-.P*K = PKD*PEL1]]1[-BP*M = PCP*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-BPPM = PCP*PELcaFH$]BE*M = PCG*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-BEPM = PCG*PEL[0FH$]AE*L = PDG*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-AE*K = PKE*g .caFH$]PH*L = PDH*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-PG*$KM = E*gNL[0FH$]CF*KE= PKF*M .
con-seg[1]:KH =FH$]PG*KE= PKE*M .N]=[M]1[-CFBKE= PPG*K .[0FH$]BF*M = PKF*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-BF*PG.
cKF*KELcaFH$]AH*M = PKH*KEL = MK 
       beFH$]PG*KE= PKE*M .N]=[M]1[-AH*K = PKE*KNL[0FH$]BG*CF.
cCG*g .
con-seg[1]:LE =e[EK] 
$[KMNG [-[0]FH$]BC*MF.
cBG*Cation contains thee[EK] 
$[KMNG [-[0]FH$]BL*MF.
cBG*g[0]:OK = PM 
     FH$]BG*CF.
cCG*g .N]=[M]1[-BL*CF.
cCG*g[0caFH$]BG*$KM = E*g .
con-seg[1]:LE = H$]BG*CF.
cCG*g .N]=[M]1[-CG*PH.
cCF*PEL0]FH$]BG*DKM =PG*g .
con-seg[1]:LE = H$]BG*CF.
cCG*g .N]=[M]1[-CG*DH.
cCF*P .caFH$]BG*KF.
cBE*g .
con-seg[1]:LE = H$]BG*CF.
cCG*g .N]=[M]1[-BE*CF.
cCG*K .0]FH$]BL*MF.
cDM*L .
con-seg[1]:KH =e[s  be$[KMNBLri[1]]1[-BG*MF.
cDF*L .
con-seg[1]:KH =e[s  be$[KMNBLri[1]]1[-BC*MF.
cCD*] .
con-seg[1]:KH =FH$]BL*MF.
cDM*L .N]=[M]1[-BC*DM.
con*CatcaFH$]KE*MF= PKH*] .
con-seg[1]:KH =FH$]BL*MF.
cDM*L .N]=[M]1[-BL*$KM = E*g[00]FH$]CG*MF.
cCF*L .
con-seg[1]:KH =FH$]BL*MF.
cDM*L .N]=[M]1[-BL*CF.
cCG*g[0caFH$]PG*LF= PDH*L .
con-seg[1]:KH =FH$]BL*MF.
cDM*L .N]=[M]1[-PM*$ = PK  DNLcaFH$]BE*MFe= PF*L .
con-seg[1]:KH =FH$]BL*MF.
cDM*L .N]=[M]1[-BE*PM.
cPLPM .0]FH$].K*MKM = M*KNL = MK 
       bee[PA] 
$[KMNiKHi[0]FH$].H*MH= P H*KNL = MK 
       bee[PA] 
$[KMNiKHi[0]FH$].B*MH= PCD*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-.B*MMG =AK*CatcaFH$]PG*KKM = E*MNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-PK*K = PKE*g[0[0FH$]AP*MH.
cKD*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-.P*MMG =AK*Pat[0FH$]BP*MH.
cCP*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-BP*MMG =CPPMndcaFH$]BE*MKM =CG*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-BE*MMG =CGPMnd[0]1[-AE*MKM =DG*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-AK*D = PAE*g[0caFH$]PH*LKM =DH*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-PM*$KM = MKDNL []1[-BF*MKM = F*KNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-BF*PM.
cPK*K .[0FH$]CF*KKM = F*MNL = MK 
       becH$].K*MKM = M*KNLN]=[M]1[-CF*KKe= PF*g[0[0FH$]MG*KKM =MH*KEL = MK 
       beFH$].K*MKM = M*KNLN]=[M]1[-PM*KE= PKK*M .[0]1[-AN*MKM = M*NEL = MK 
       bee[PA] 
$[KMNiNtri[0FH$]AE*MH= P H*NEL = MK 
       bee[PA] 
$[KMNiNtri[0FH$]AD*MH= PCD*NEL = MK 
       be]1[-AN*MKM = M*NELN]=[M]1[-.D*DM.
cCD*Dbe0]FH$]CFBNE= PPG*MNL = MK 
       becH$].N*MKM = M*NELN]=[M]1[-CF*Dbe= PG*g[0[0FH$]DG*LKM =DH*NEL = MK 
       be]1[-AN*MKM = M*NELN]=[M]1[-P BDKM = M*g . []1[-PFBNE= PPE*MNL = MK 
       becH$].N*MKM = M*NELN]=[M]1[-P BKF.
cPM*KEL[0]1[-AN*KKM =.K*NEL = MK 
       be]1[-AN*MKM = M*NELN]=[M]1[-.K*MKM = M*KNL[0FH$]AE*KH.
c.H*NEL = MK 
       be]1[-AE*MH= P H*NELN]=[M]1[-AH*MH= P H*KNLcaFH$]PH*NE= PDG*KNL = MK 
       becH$].E*MH= P H*NELN]=[M]1[-AE*MKM =DG*KNL0]FH$].B*NE= D.D*KNL = MK 
       becH$].D*MH= PCD*NELN]=[M]1[-.B*MH= PCD*KNLcaFH$]PG*KKM = F*NEL = MK 
       be]1[-CFBNE= PPG*MNLN]=[M]1[-CF*KKM = F*MNL []1[-BF*NE= PPE*KNL = MK 
       becH$]PFBNE= PPE*MNLN]=[M]1[-BF*MKM = F*KNL0]FH$]Pe LE.
cPLPMNL = MK 
       bee[PA] 
$[KMNPLtri[0FH$]PE*MH= P H*LEL = MK 
       bee[PA] 
$[KMNPLtri[0FH$]BC*MH= PCD*LEL = MK 
       beFH$]Pe LE.
cPLPMNLN]=[M]1[-BC*DM.
con*CatcaFH$]CG*MH.
cCF*LEL = MK 
       beFH$]Pe LE.
cPLPMNLN]=[M]1[-BL*CF.
cCG*g[0caFH$]PG*LKM =DH*LEL = MK 
       beFH$]Pe LE.
cPLPMNLN]=[M]1[-PM*$ = PK  DNLcaFH$]BE*MKM = F*LEL = MK 
       beFH$]Pe LE.
cPLPMNLN]=[M]1[-BE*PM.
cPLPM .0]FH$]BG*LKM =DF*LEL = MK 
       beFH$]Pe LE.
cPLPMNLN]=[M]1[-BL*MF.
cBG*g[0[0FH$]MF LE.
cMH*L .
con-seg[1]:KH =FH$]Pe LE.
cPLPMNLN]=[M]1[-BL*MF.
cDM*L .0]FH$].K*LE.
cPLPKNL = MK 
       becH$]Pe LE.
cPLPMNLN]=[M]1[-.K*MKM = M*KNL[0FH$]AN LE.
cPLPNEL = MK 
       be]1[-Pe LE.
cPLPMNLN]=[M]1[-.N*MKM = M*NEL0]FH$].H*LE= PPE*KNL = MK 
       becH$]PE*MH= P H*LELN]=[M]1[-AH*MH= P H*KNLcaFH$]PG*KKM = H*LEL = MK 
       beFH$]PE*MH= P H*LELN]=[M]1[-PG*KKM = E*MNL[0FH$]AE*LE= PPE*NEL = MK 
       be]1[-PE*MH= P H*LELN]=[M]1[-AE*MH= P H*NEL []1[-PFBNE= PPH*LEL = MK 
       beFH$]PE*MH= P H*LELN]=[M]1[-PFBNE= PPE*MNL0]FH$].B*LE= PBC*KNL = MK 
       becH$]BC*MH= PCD*LELN]=[M]1[-.B*MH= PCD*KNLcaFH$]AD*LE= PBC*NEL = MK 
       be]1[-BC*MH= PCD*LELN]=[M]1[-.D*MH= PCD*NELcaFH$]CG*KKM = F*LEL = MK 
       beFH$]CG*MH.
cCF*LELN]=[M]1[-CF*KKM = F*MNL []1[-CG*NE= PPG*LEL = MK 
       beFH$]CG*MH.
cCF*LELN]=[M]1[-CF*NE= PPG*MNL0]FH$]CFBNE= PDH*LEL = MK 
       beFH$]PG*LKM =DH*LELN]=[M]1[-CF*NE= PPG*MNL0]FH$]PG*NE= PDG*LEL = MK 
       beFH$]PG*LKM =DH*LELN]=[M]1[-DG*LKM =DH*NEL []1[-BF*LE= PBE*KNL = MK 
       becH$]BE*MKM = F*LELN]=[M]1[-BF*MKM = F*KNL0]FH$]BEBNE= PPE*LEL = MK 
       beFH$]BE*MKM = F*LELN]=[M]1[-PFBNE= PPE*MNL0]FH$]CPPNE= PPD*LEL = MK 
       beFH$]AN LE.
cPLPNELN]=[M]1[-CP*Dbe= = BKat []1[-.P*LE= PBP*NEL = MK 
       be]1[-AN*LE.
cPLPNELN]=[M]1[-.P*ON= PBPPDbecaFH$]AH*LE= PBF*NEL = MK 
       be]1[-AN*LE.
cPLPNELN]=[M]1[-.H*ON= PBF*Dbe0]FH$]CLPN = PKN*L .
con-seg[1]:KH =e[P  be$[KMNCLri[1]]1[-CG*NGe= PG*L .
con-seg[1]:KH =e[P  be$[KMNCLri[1]]1[-AD*] . PBC*Nnd
con-seg[1]:LE = H$]CLPN = PKN*L .N]=[M]1[-.D**PG =BC*DbecaFH$]CPPN = PKD*] .
con-seg[1]:KH =FH$]CLPN = PKN*L .N]=[M]1[-CP*Dbe= = BKat []1[-BEBN = PKE*] .
con-seg[1]:KH =FH$]CLPN = PKN*L .N]=[M]1[-BE*Dbe= = BKEL []1[-.P*L = PBPPNnd
con-seg[1]:LE = H$]CLPN = PKN*L .N]=[M]1[-.P*ON= PBPPDbecaFH$]PG*NGe= DG*L .
con-seg[1]:KH = H$]CLPN = PKN*L .N]=[M]1[-= BDGe= PG*Dbe[0FH$]AE*L = PKE*Nnd
con-seg[1]:LE = H$]CLPN = PKN*L .N]=[M]1[-.EPON= PPE*Dbe0]FH$]CFBN = PDH*] .
con-seg[1]:KH =FH$]CLPN = PKN*L .N]=[M]1[-CF*Dbe= P  DNL []1[-PFBN = PKH*] .
con-seg[1]:KH =FH$]CLPN = PKN*L .N]=[M]1[-BL*$KM = N*K .caFH$]AH*L = PBF*Nnd
con-seg[1]:LE = H$]CLPN = PKN*L .N]=[M]1[-.H*ON= PBF*Dbe0]FH$]KN*MF.
cDM*Nnd
con-seg[1]:LE = H$]CLPN = PKN*L .N]=[M]1[-BL*MF.
cDM*L .0]FH$]NG*LE.
cNE*] .
con-seg[1]:KH =FH$]CLPN = PKN*L .N]=[M]1[-AN*LE.
cPLPNEL0]FH$]CFBN = PPG*LF= = MK 
       beFH$]CG*NGe= PG*L .N]=[M]1[-CG*MF.
cCF*L .[0FH$]AD*MF.
cCD*Nnd
con-seg[1]:LE = H$]AD*] . PBC*NndN]=[M]1[-BC*MF.
cCD*] . []1[-PFBN = PKE*LF= = MK 
       beFH$]BEBN = PKE*] .N]=[M]1[-BE*MFe= PF*L .[0FH$]DG*LF= PDH*Nnd
con-seg[1]:LE = H$]PG*NGe= DG*L .N]=[M]1[-PG*LF= PDH*L .[0FH$]AE*MF= PKH*Nnd
con-seg[1]:LE = H$]AE*L = PKE*NndN]=[M]1[-PE*MF= PKH*] .0]FH$]NG*MKM =NE*LF= = MK 
       beFH$]KN*MF.
cDM*NndN]=[M]1[-.N*MKM = M*NEL0]FH$]KN*KF.
cPMKNnd
con-seg[1]:LE =e[P  be$[KMNPKFri[0FH$]PG*KF.
cPF*Nnd
con-seg[1]:LE =e[P  be$[KMNPKFri[0FH$].B*N = PAD*KF= = MK 
       beFH$]KN*KF.
cPMKNndN]=[M]1[-.B*M .
coD*g[0caFH$]BFBN = PKE*KF= = MK 
       beFH$]KN*KF.
cPMKNndN]=[M]1[-BF*Dbe= PK*PELcaFH$]PH*N = PDG*KF= = MK 
       beFH$]KN*KF.
cPMKNndN]=[M]1[-PK*D = PKH*Dbe[0FH$]AE*KF. D.H*Nnd
con-seg[1]:LE = H$]PN*KF.
cPMKNndN]=[M]1[-.K*AE. D.H*Dbe0]FH$]NG*KKM =NE*KF= = MK 
       beFH$]KN*KF.
cPMKNndN]=[M]1[-.N*KKM =.K*NEL0]FH$]CLPKF.
cPMKL .
con-seg[1]:KH =FH$]PN*KF.
cPMKNndN]=[M]1[-CLPN = PKN*L .caFH$]PK*MF.
cDM*KF= = MK 
       beFH$]KN*KF.
cPMKNndN]=[M]1[-PN*MF.
cDM*NndcaFH$]CG*KFe= PF*L .
con-seg[1]:KH =FH$]PG*KF.
cPF*NndN]=[M]1[-CG*NGe= PG*L .[0FH$]PG*KF.
cPH*L .
con-seg[1]:KH =FH$]PG*KF.
cPF*NndN]=[M]1[-PFBN = PKH*] .[0FH$]CF*KFe= PF*sF= = MK 
       beFH$]KG*KF.
cPF*NndN]=[M]1[-CFBN = PPG*LF=[0FH$]PG*KF.
cPE*LF= = MK 
       beFH$]KG*KF.
cPF*NndN]=[M]1[-PFBN = PKE*LF=[0FH$].B*L . PBC*KF= = MK 
       beFH$].B*N = PAD*KF=N]=[M]1[-.D*] . PBC*Nnd0]FH$].B*MF.
cCD*KF= = MK 
       beFH$].B*N = PAD*KF=N]=[M]1[-.D*MF.
cCD*Nnd []1[-BF*L = PBE*KF= = MK 
       beFH$]BFBN = PKE*KF=N]=[M]1[-BE*N = PKE*] .caFH$]AH*L = PKE*KF= = MK 
       beFH$]BFBN = PKE*KF=N]=[M]1[-AH*L = PBF*Nnd []1[-BF*MF.
cPF*KF= = MK 
       beFH$]BFBN = PKE*KF=N]=[M]1[-PFBN = PKE*LF=[0FH$]PH*LF= PDH*KF= = MK 
       beFH$]KH*N = PDG*KF=N]=[M]1[-DG*LF= PDH*Nnd[0FH$]AE*MF= PDG*KF= = MK 
       beFH$]KH*N = PDG*KF=N]=[M]1[-AE*MF= PKH*Nnd0]FH$].H*MF= PKH*KF= = MK 
       beFH$].E*KF. D.H*NndN]=[M]1[-AE*MF= PKH*Nnd0]FH$]LG*KKM =LE*KF= = MK 
       beFH$]NG*KKM =NE*KF=N]=[M]1[-NG*LE.
cNE*] .[0FH$]MF KKM =MH*KF= = MK 
       beFH$]NG*KKM =NE*KF=N]=[M]1[-NG*MKM =NE*LF=[0FH$]AP*MF.
cKD*KF= = MK 
       beFH$]KK*MF.
cDM*KF=N]=[M]1[-.P*MMG =AK*Pat[0FH$]BP*MF.
cCP*KF= = MK 
       beFH$]KK*MF.
cDM*KF=N]=[M]1[-BP*MMG =CPPMndcaFH$]BE*MF.
cCG*KF= = MK 
       beFH$]KK*MF.
cDM*KF=N]=[M]1[-BE*MMG =CGPMnd[0]1[-MG*KF.
cMF*KEL = MK 
       beFH$]PK*MF.
cDM*KF=N]=[M]1[-PM*KE= PKK*M .[0]1[-PLPMNL
cDM*L .
con-seg[1]:KH =e[PL be$[KMNDMHri[0FH$]PG*LKM =DH*L .
con-seg[1]:KH =e[PL be$[KMNDMHri[0FH$]BC*MH= PCD*L .
con-seg[1]:KH =FH$]PLPMNL
cDM*L .N]=[M]1[-BC*DM.
con*CatcaFH$]KE*MH= P H*L .
con-seg[1]:KH =FH$]PLPMNL
cDM*L .N]=[M]1[-BL*$KM = E*g[00]FH$]CG*MH.
cCF*L .
con-seg[1]:KH =FH$]PLPMNL
cDM*L .N]=[M]1[-BL*CF.
cCG*g[0caFH$]BE*MKM = F*L .
con-seg[1]:KH =FH$]PLPMNL
cDM*L .N]=[M]1[-BE*PM.
cPLPM .0]FH$]BG*LKM =DF*L .
con-seg[1]:KH =FH$]PLPMNL
cDM*L .N]=[M]1[-BL*MF.
cBG*g[0[0F0$]MF LG 
cMH*L .
con-seg[1]:KH =FH$]PLPMNL
cDM*L .N]=[M]1[-BL*MF.
cDM*L .0]FH$].K*L = PK  KNL = MK 
       becH$]PLPMNL
cDM*L .N]=[M]1[-.K*MKM = M*KNL[0FH$]AN LG.
cPLPNEL = MK 
       becH$]PLPMNL
cDM*L .N]=[M]1[-.N*MKM = M*NEL0]F0$]PLPLG.
cPLPLEL = MK 
       beFH$]PLPMNL
cDM*L .N]=[M]1[-Pe LE.
cPLPMNL0]FH$].K*N = PKN*KNL = MK 
       becH$].K*L = PK  KNLN]=[M]1[-CLPN = PKN*L .caF0$].K*KF.
cPMKKNL = MK 
       becH$].K*L = PK  KNLN]=[M]1[-CLPKF.
cPMKL .caF0$].NPN = PKN*NEL = MK 
       be]1[-AN*LG.
cPLPNELN]=[M]1[-CLPN = PKN*L .caFH$]AF*BE= PBF*CEL = MK 
       bee[EBCsi$[KMNFBAri[0FH$].B*CE. D.F*BC0]:OM = PK 
     e[EBCsi$[KMNFBAri[0FH$].F*PG.
cCE*PNL = MK 
       becH$].F*BE= PBF*CELN]=[M]1[-BF*K = PBE*PNL0]FH$].F*KL= PCE*P:NK = ML 
       bFH$].F*BE= PBF*CELN]=[M]1[-BF*KL= PBE*P:NcaFH$].F*C = PCEPM .
con-seg[1]:LE = H$].F*BE= PBF*CELN]=[M]1[-BF*C = PBEPM .0]FH$].F*KE. D.H*CEL = MK 
       be H$].F*BE= PBF*CELN]=[M]1[-.H*BE= PBF*KEL0]FH$].F*LE.
cCE*KNL = MK 
       becH$].F*BE= PBF*CELN]=[M]1[-BF*LE= PBE*KNL0]FH$].G*DH.
cCH*g .
con-seg[1]:KH =$[HDCsi$[KMNGDAi[1]]1[-AD*CKM =.G*Cation contains thee[HDCsi$[KMNGDAi[1]]1[-AG*PM.
cCH*Pbecause con-seg[0]:H$].G*DH.
cCH*g .N]=[M]1[-P BDKM = M*g . []1[-AG*CF.
cCH*K =
con-seg[1]:KH =FH$]AG*DH.
cCH*g .N]=[M]1[-CFBDGe= PG*DNL []1[-AG*PH.
c.E*CNL = MK 
       becH$].G*DH.
cCH*g .N]=[M]1[-AE*DKM = H*g .caFH$].G*KF.
cCH*KEL = MK 
       beFH$].G*DH.
cCH*g .N]=[M]1[-PF*D = PKE*gNLcaFH$].G*MH.
cCH*NEL = MK 
       be]1[-AG*DH.
cCH*g .N]=[M]1[-DG*LKM =DH*NEL []1[-PFBNH= P H*L .
con-seg[1]:LE =e[PFigu$[KMNPHNi[0]FH$]P BLF.
cPLPNNL = MK 
       bee[PFigu$[KMNPHNi[0]FH$]AE*LF.
cPE*NNL = MK 
       be]1[-PFBNH= P H*L .N]=[M]1[-AE*KFe= PE*KNL[0]1[-PG*NKM =DG*L .
con-seg[1]:LE =]1[-PFBNH= P H*L .N]=[M]1[-PG*$KM = F*g . []1[-BEBNKM = E*L .
con-seg[1]:LE =]1[-PFBNH= P H*L .N]=[M]1[-BE*PH.
cKF*KEL1]]1[-CG*NKM =PG*L .
con-seg[1]:LE =]1[-PFBNH= P H*L .N]=[M]1[-CG*PH.
cPG*K .[0FH$]AP*LF.
cBPPNNL = MK 
       be]1[-PFBNH= P H*L .N]=[M]1[-AP*KFe= BP*PNLcaFH$]CPPNH.
cKD*L .
con-seg[1]:LE =]1[-PFBNH= P H*L .N]=[M]1[-CP*PH.
cKD*P .[0]1[-.D*LF.
cBCPNNL = MK 
       be]1[-PFBNH= P H*L .N]=[M]1[-AD*KF.
cBC*PNL0]FH$]CFBNKM =DH*L .
con-seg[1]:LE =]1[-PFBNH= P H*L .N]=[M]1[-CF*$KM = F*gNLcaFH$]AH*LFe= BF*NNL = MK 
       be]1[-PFBNH= P H*L .N]=[M]1[-AH*KFe= BF*MNL0]FH$]NE*LF.
cNH*LEL = MK 
       beFH$]PFBNH= P H*L .N]=[M]1[-PFBNE= PPH*LEL0]FH$]P BFH= P H*Neg[0]:AK = BK = PK []$, $$[KMNPHFri[0FH$]PLBFH= P F*Neg[0]:AK = BK = PK []$, $$[KMNPHFri[0FH$]AE*BH.
c.H*DEL = MK 
       bee[sBArm$[KMN DAri[0FH$].B*DE= PAD*BNL = MK 
       bee[sBArm$[KMN DAri[0FH$]BH*D = PKH*DEL = MK 
       be]1[-AE*BH.
c.H*DELN]=[M]1[-AE*KH.
c.H*D .0]FH$].K*DE= PBH*Dbecause con-seg[0]:H$].E*BH.
c.H*DELN]=[M]1[-AK*AE. D.H*Dbe0]FH$]BH*PG.
cKF*DEL = MK 
       be]1[-AE*BH.
c.H*DELN]=[M]1[-AE*KFe= AH*K = []1[-BF*DE= PBH*PEL = MK 
       beFH$]AE*BH.
c.H*DELN]=[M]1[-AE*BFe= AH*KEL0]FH$]BH*NE= PDE*KNL = MK 
       becH$].E*BH.
c.H*DELN]=[M]1[-AE*KH.
c.H*NEL[0FH$]PD*LE= PPG*Neg[0]:AK = BK = PK [LEN $$[KMNPG b$[0FH$]PD*NE= PDG*Neg[0]:AK = BK = PK [LEN $$[KMNPG b$[0FH$].P*LE= PPE*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-AP*K = PKD*PEL1]]1[-CPPNL= PCD*LEL = MK 
       beFH$]PD*LE= PPG*NegN]=[M]1[-CPPM = DCD*$ .1]]1[-CP*LE.
cCG*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-CPPM = PCG*Pat[0FH$]ABPLE= PBP*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-AB*K = PBPPM =1]]1[-BP*LE= PBE*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-BPPM = PBEPM =1]]1[-OD*LE= PON*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-OD*K = P= BKat[0FH$]PD*LF= PDH*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-PG*LF= PDH*L .[0FH$]KD*KNL P H*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-PG*KKM = H*LEL0]FH$]CPPNE= PPG*Neg[0]:AK = BK = PKFH$]PD*LE= PPG*NegN]=[M]1[-CPPNE= PPD*LEL0]FH$]CDPNE= PPD*Neg[0]:AK = BK = PKFH$]PD*NE= PDG*NegN]=[M]1[-CD*D = PKD*Pat0]FH$].O*NE= PDN*Neg[0]:AK = BK = PKFH$]PD*NE= PDG*NegN]=[M]1[-.OPD = PKD*and[0FH$].B*NE= PAP*Neg[0]:AK = BK = PKFH$]PD*NE= PDG*NegN]=[M]1[-.B*D = PAP*Pat[0FH$]BPBNE= PPE*Neg[0]:AK = BK = PKFH$]PD*NE= PDG*NegN]=[M]1[-BPPD = PKD*PEL[0FH$]APBNE= PAE*Neg[0]:AK = BK = PKFH$]PD*NE= PDG*NegN]=[M]1[-.P*M = PAEPM =caFH$]AP*MF= PKH*Neg[0]:AK = BK = PKFH$].P*LE= PPE*NegN]=[M]1[-PE*MF= PKH*] .0]FH$]AP*KH.
c.H*Neg[0]:AK = BK = PKFH$].P*LE= PPE*NegN]=[M]1[-.H*LE= PPE*KNL[0FH$]BC*MF.
cCP*Neg[0]:AK = BK = PKFH$]CPPNL= PCD*LELN]=[M]1[-BC*MF.
cCD*] . []1[-CP*MF.
cCF*Neg[0]:AK = BK = PK]1[-CP*LE.
cCG*NegN]=[M]1[-CG*MF.
cCF*L .[0FH$]CP*KKM = F*Neg[0]:AK = BK = PK]1[-CP*LE.
cCG*NegN]=[M]1[-CG*KKM = F*LEL[0FH$]BC*KKM =BP*Neg[0]:AK = BK = PKFH$]ABPLE= PBP*NegN]=[M]1[-.B*LE= PBC*KNL[0FH$]BP*MF.
c F*Neg[0]:AK = BK = PK]1[-BP*LE= PBE*NegN]=[M]1[-BE*MFe= PF*L .[0FH$]BP*KKM =BF*Neg[0]:AK = BK = PK]1[-BP*LE= PBE*NegN]=[M]1[-BF*LE= PBE*KNL0]FH$]OD*MF.
cDM*Neg[0]:AK = BK = PK]1[-OD*LE= PON*NegN]=[M]1[-BL*MF.
cDM*L .0]FH$].K*N = POD*KNL = MK 
       becH$]OD*LE= PON*NegN]=[M]1[-.K*LE.
cPLPKNL[0FH$]AD*MF.
cPD*Neg[0]:AK = BK = PKFH$]PD*LF= PDH*NegN]=[M]1[-AD*DH.
cKD*PatcaFH$]APPNL= P.D*KNL = MK 
       becH$]KD*KNL P H*NegN]=[M]1[-AP*K = D.D*PNL0]FH$]PD*KNL P  BFH= = MK 
       becH$]KD*KNL P H*NegN]=[M]1[-P BFH= P H*NegcaFH$]AP*MF= PK BFH= = MK 
       becH$]AP*MF= PKH*NegN]=[M]1[-P BFH= P H*NegcaFH$]CP*KKM = LBFH= = MK 
       becH$]CP*KKM = F*NegN]=[M]1[-PLBFH= P F*Neg[0FH$]BP*MF.
c LBFH= = MK 
       becH$]BP*MF.
c F*NegN]=[M]1[-PLBFH= P F*Neg[0FH$].OPFH= PCD*KNL = MK 
       becH$]KD*KNL P  BFH=N]=[M]1[-.OPM = DCD*Dbe0]FH$].B*MF.
c.OPFH= = MK 
       becH$]AP*MF= PK BFH=N]=[M]1[-.B*M .
coOPAP=[0FH$]BPBNE= PPK*GEL = MK 
       bee[PBKrm$[KMN Gtri[0]1[-CP*NE= PDM*GEL = MK 
       beFH$]BPBNE= PPK*GELN]=[M]1[-BP*MMG =CPPMndcaFH$]BCBNE= POD*GEL = MK 
       beFH$]BPBNE= PPK*GELN]=[M]1[-BC*PK= PBPPOat[0FH$].P*LE= PPK*GEL = MK 
       beFH$]BPBNE= PPK*GELN]=[M]1[-.P*LE= PBP*NELcaFH$]PK*GE= PPE*Neg[0]:AK = BK = PKFH$]BPBNE= PPK*GELN]=[M]1[-BP*NE= PPE*Neg[0FH$]PD*LE= PDM*GEL = MK 
       beFH$]CP*NE= PDM*GELN]=[M]1[-CPPNE= PPD*LEL0]FH$]PG*Neg PDM*GEL = MK 
       beFH$]CP*NE= PDM*GELN]=[M]1[-CPPNE= PPG*Neg[0FH$].D*LE= POD*GEL = MK 
       beFH$]BCBNE= POD*GELN]=[M]1[-.D*]E= PBC*NEL.
peano%                                                                            