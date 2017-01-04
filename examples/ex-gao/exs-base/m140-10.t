time: 6 minues


 The Geometry Data Base  at level (1)
***************************************
midpoint[S,AH]
midpoint[M,CA]
       because para[$CH,MS$] and midpoint[$S,AH$].
midpoint[Q,BA]
       because para[$BH,QS$] and midpoint[$S,AH$].
midpoint[K,BH]
       because para[$BA,KS$] and midpoint[$S,AH$].
midpoint[L,CH]
       because para[$CA,LS$] and midpoint[$S,AH$].

The configuration contains the following lines
line [1]A C M N 
line [1]A B Q P 
line [1]B C H K L 
line [1]A H S 
line [1]S M Q 
line [1]S K N 
line [1]S L P 
line [1]B S 
line [1]C S 
line [1]B M 
line [1]H M 
line [1]M K 
line [1]M L 
line [1]C Q 
line [1]H Q 
line [1]Q K 
line [1]Q L 
line [1]A K 
line [1]K P 
line [1]B N 
line [1]Q N 
line [1]N P 
line [1]A L 
line [1]N L 
line [1]C P 
line [1]M P 
line [1]H N 
line [1]H P 

The configuration contains the following parallel lines
pline [1]: line [1]S M Q ; line [1]B C H K L ; 
pline [1]: line [1]S K N ; line [1]A B Q P ; 
pline [1]: line [1]S L P ; line [1]A C M N ; 
pline [0]: line [1]M L ; line [1]A H S ; 
       because midpoint[$L,CH$] and midpoint[$M,CA$].
pline [0]: line [1]Q K ; line [1]A H S ; 
       because midpoint[$K,BH$] and midpoint[$Q,BA$].

pline [1]: line [1]Q K ; line [1]A H S ; line [1]M L ; 
       because pline [0]: line [1]M L ; line [1]A H S ;  and pline [0]: line [1]Q K ; line [1]A H S ; .

The configuration contains the following perp lines
tline [1]line [1]A C M N  perp line [1]A B Q P 
tline [1]line [1]A H S  perp line [1]B C H K L 

tline [1]line [1]S K N  perp line [1]A C M N 
       because para[$SK,AB$] and perp[$AC,AB$].
tline [1]line [1]S L P  perp line [1]A B Q P 
       because para[$SL,AC$] and perp[$AB,AC$].
tline [1]line [1]S M Q  perp line [1]A H S 
       because para[$SM,BC$] and perp[$AH,BC$].
tline [1]line [1]S K N  perp line [1]S L P 
       because para[$SK,AB$] and perp[$SL,AB$].
tline [1]line [1]B C H K L  perp line [1]Q K 
       because circle[$(Q)BH$] and midpoint[$K,BH$].
tline [1]line [1]B C H K L  perp line [1]M L 
       because circle[$(M)CH$] and midpoint[$L,CH$].
tline [1]line [1]M L  perp line [1]S M Q 
       because $[ML,BC]=[ML,SM]$ and perp[$ML,BC$].
tline [1]line [1]Q K  perp line [1]S M Q 
       because $[QK,BC]=[QK,SM]$ and perp[$QK,BC$].
tline [1]line [1]C S  perp line [1]A K 
       because perp[$AH,BC$] and $[CS,AH]=[AK,BC]$.
tline [1]line [1]B S  perp line [1]A L 
       because perp[$AH,BC$] and $[BS,AH]=[AL,BC]$.
tline [1]line [1]H Q  perp line [1]H M 
       because $[HQ,HM]=[HQ,HM]$ and perp[$AC,AB$].
tline [1]line [1]H N  perp line [1]C Q 
       because perp[$QK,BC$] and $[QK,HN]=[BC,CQ]$.
tline [1]line [1]H P  perp line [1]B M 
       because perp[$ML,BC$] and $[ML,HP]=[BC,BM]$.
tline [1]line [1]Q N  perp line [1]N L 
       because perp[$AB,SL$] and $[AB,QN]=[SL,NL]$.
tline [1]line [1]M P  perp line [1]K P 
       because perp[$SL,AB$] and $[MP,SL]=[KP,AB]$.

The configuration contains the following circles
cir [1]circle[CHSN]
       because perp[$NS,NC$] and perp[$HS,HC$].
cir [1]circle[AHKN]
       because perp[$NA,NK$] and perp[$HA,HK$].
cir [1]circle[BHSP]
       because perp[$PS,PB$] and perp[$HS,HB$].
cir [1]circle[AHLP]
       because perp[$PA,PL$] and perp[$HA,HL$].
cir [0]circle[(M)AH]
       because perp[$MS,AH$] and midpoint[$S,AH$].
cir [0]circle[(Q)AH]
       because perp[$QS,AH$] and midpoint[$S,AH$].
cir [1]circle[ASNP]
       because perp[$PS,PA$] and perp[$NS,NA$].
cir [1]circle[(M)ACH]
       because perp[$HC,HA$] and midpoint[$M,CA$].
cir [1]circle[(Q)ABH]
       because perp[$HB,HA$] and midpoint[$Q,BA$].
cir [1]circle[ACQK]
       because perp[$AC,AQ$] and perp[$KC,KQ$].
cir [0]circle[QKLP]
       because perp[$PL,PQ$] and perp[$KL,KQ$].
cir [1]circle[HSQK]
       because perp[$SH,SQ$] and perp[$KH,KQ$].
cir [0]circle[MQKL]
       because perp[$ML,MQ$] and perp[$KL,KQ$].
cir [0]circle[MQKLP]
       because cir [0]circle[QKLP] and cir [0]circle[MQKL].
cir [1]circle[ABML]
       because perp[$AB,AM$] and perp[$LB,LM$].
cir [0]circle[MKNL]
       because perp[$NK,NM$] and perp[$LK,LM$].
cir [1]circle[MQKNLP]
       because cir [0]circle[MQKLP] and cir [0]circle[MKNL].
cir [1]circle[HSML]
       because perp[$SH,SM$] and perp[$LH,LM$].
cir [1]circle[AHMQ]
       because perp[$AQ,AM$] and perp[$HQ,HM$].
cir [1]circle[BCNP]
       because $[CB,CN]=[PB,PN]$.


The configuration contains the following eqangles
eqang [0][BA,BC] = [AC,AH]
       because perp[$AH,BC$] and perp[$AC,AB$].
eqang [0][AB,AH] = [CA,CB]
       because perp[$AH,BC$] and perp[$AC,AB$].
eqang [0][BS,BC] = [SB,SM]
       because para[$SM,BC$].
eqang [0][BA,BC] = [SK,SM]
       because para[$KS,AB$].
eqang [0][AC,AH] = [SK,SM] = [BA,BC]
       because eqang [0][BA,BC] = [AC,AH] and eqang [0][BA,BC] = [SK,SM].
eqang [0][CS,CB] = [SC,SM]
       because para[$SM,CB$].
eqang [0][CA,CB] = [SL,SM]
       because para[$LS,AC$].
eqang [0][AB,AH] = [SL,SM] = [CA,CB]
       because eqang [0][AB,AH] = [CA,CB] and eqang [0][CA,CB] = [SL,SM].
eqang [0][KS,KB] = [SK,SM]
       because para[$SM,KB$].
eqang [0][BA,BC] = [AC,AH] = [SK,SM] = [KS,KB]
       because eqang [0][AC,AH] = [SK,SM] = [BA,BC] and eqang [0][KS,KB] = [SK,SM].
eqang [0][LS,LB] = [SL,SM]
       because para[$SM,LB$].
eqang [0][CA,CB] = [AB,AH] = [SL,SM] = [LS,LB]
       because eqang [0][AB,AH] = [SL,SM] = [CA,CB] and eqang [0][LS,LB] = [SL,SM].
eqang [0][BM,BC] = [MB,MS]
       because para[$MS,BC$].
eqang [0][CA,CB] = [MA,MS]
       because para[$MS,CB$].
eqang [0][LS,LB] = [SL,SM] = [AB,AH] = [MA,MS] = [CA,CB]
       because eqang [0][CA,CB] = [AB,AH] = [SL,SM] = [LS,LB] and eqang [0][CA,CB] = [MA,MS].
eqang [0][HM,HB] = [MH,MS]
       because para[$MS,HB$].
eqang [0][KM,KB] = [MK,MS]
       because para[$MS,KB$].
eqang [0][LM,LB] = [ML,MS]
       because para[$MS,LB$].
eqang [0][BA,BC] = [QA,QS]
       because para[$QS,BC$].
eqang [0][KS,KB] = [SK,SM] = [AC,AH] = [QA,QS] = [BA,BC]
       because eqang [0][BA,BC] = [AC,AH] = [SK,SM] = [KS,KB] and eqang [0][BA,BC] = [QA,QS].
eqang [0][CQ,CB] = [QC,QS]
       because para[$QS,CB$].
eqang [0][HQ,HB] = [QH,QS]
       because para[$QS,HB$].
eqang [0][KQ,KB] = [QK,QS]
       because para[$QS,KB$].
eqang [0][LQ,LB] = [QL,QS]
       because para[$QS,LB$].
eqang [0][AH,AB] = [SA,SK]
       because para[$SK,AB$].
eqang [0][CA,CB] = [MA,MS] = [SL,SM] = [LS,LB] = [SK,SA] = [AB,AH]
       because eqang [0][LS,LB] = [SL,SM] = [AB,AH] = [MA,MS] = [CA,CB] and eqang [0][AH,AB] = [SA,SK].
eqang [0][BS,BA] = [SB,SK]
       because para[$SK,BA$].
eqang [0][AK,AB] = [KA,KS]
       because para[$KS,AB$].
eqang [0][QK,QA] = [KQ,KS]
       because para[$KS,QA$].
eqang [0][PK,PA] = [KP,KS]
       because para[$KS,PA$].
eqang [0][BN,BA] = [NB,NS]
       because para[$NS,BA$].
eqang [0][QN,QA] = [NQ,NS]
       because para[$NS,QA$].
eqang [0][PN,PA] = [NP,NS]
       because para[$NS,PA$].
eqang [0][AH,AC] = [SA,SL]
       because para[$SL,AC$].
eqang [0][BA,BC] = [QA,QS] = [SK,SM] = [KS,KB] = [SL,SA] = [AC,AH]
       because eqang [0][KS,KB] = [SK,SM] = [AC,AH] = [QA,QS] = [BA,BC] and eqang [0][AH,AC] = [SA,SL].
eqang [0][CS,CA] = [SC,SL]
       because para[$SL,CA$].
eqang [0][AL,AC] = [LA,LS]
       because para[$LS,AC$].
eqang [0][ML,MA] = [LM,LS]
       because para[$LS,MA$].
eqang [0][NL,NA] = [LN,LS]
       because para[$LS,NA$].
eqang [0][CP,CA] = [PC,PS]
       because para[$PS,CA$].
eqang [0][MP,MA] = [PM,PS]
       because para[$PS,MA$].
eqang [0][NP,NA] = [PN,PS]
       because para[$PS,NA$].
eqang [0][NA,NH] = [SC,SA]
       because circle[$CHSN$].
eqang [0][SC,SK] = [HB,HN]
       because circle[$CNHS$].
eqang [0][NH,NS] = [CB,CS]
       because circle[$HSCN$].
eqang [0][SC,SM] = [CS,CB] = [NS,NH]
       because eqang [0][CS,CB] = [SC,SM] and eqang [0][NH,NS] = [CB,CS].
eqang [0][HA,HN] = [CS,CA]
       because circle[$SNCH$].
eqang [0][SC,SL] = [CS,CA] = [HA,HN]
       because eqang [0][CS,CA] = [SC,SL] and eqang [0][HA,HN] = [CS,CA].
eqang [0][NA,NH] = [KA,KB]
       because circle[$AHKN$].
eqang [0][SC,SA] = [KA,KB] = [NA,NH]
       because eqang [0][NA,NH] = [SC,SA] and eqang [0][NA,NH] = [KA,KB].
eqang [0][KA,KS] = [HA,HN]
       because circle[$ANHK$].
eqang [0][AK,AB] = [HA,HN] = [KA,KS]
       because eqang [0][AK,AB] = [KA,KS] and eqang [0][KA,KS] = [HA,HN].
eqang [0][CS,CA] = [SC,SL] = [KA,KS] = [HA,HN] = [AK,AB]
       because eqang [0][SC,SL] = [CS,CA] = [HA,HN] and eqang [0][AK,AB] = [HA,HN] = [KA,KS].
eqang [0][NH,NS] = [AH,AK]
       because circle[$HKAN$].
eqang [0][CS,CB] = [SC,SM] = [AK,AH] = [NS,NH]
       because eqang [0][SC,SM] = [CS,CB] = [NS,NH] and eqang [0][NH,NS] = [AH,AK].
eqang [0][HB,HN] = [AK,AC]
       because circle[$KNAH$].
eqang [0][SC,SK] = [AK,AC] = [HB,HN]
       because eqang [0][SC,SK] = [HB,HN] and eqang [0][HB,HN] = [AK,AC].
eqang [0][PA,PH] = [SB,SA]
       because circle[$BHSP$].
eqang [0][SB,SL] = [HB,HP]
       because circle[$BPHS$].
eqang [0][PH,PS] = [BC,BS]
       because circle[$HSBP$].
eqang [0][SB,SM] = [BS,BC] = [PS,PH]
       because eqang [0][BS,BC] = [SB,SM] and eqang [0][PH,PS] = [BC,BS].
eqang [0][HA,HP] = [BS,BA]
       because circle[$SPBH$].
eqang [0][SB,SK] = [BS,BA] = [HA,HP]
       because eqang [0][BS,BA] = [SB,SK] and eqang [0][HA,HP] = [BS,BA].
eqang [0][PA,PH] = [LA,LB]
       because circle[$AHLP$].
eqang [0][SB,SA] = [LA,LB] = [PA,PH]
       because eqang [0][PA,PH] = [SB,SA] and eqang [0][PA,PH] = [LA,LB].
eqang [0][LA,LS] = [HA,HP]
       because circle[$APHL$].
eqang [0][AL,AC] = [HA,HP] = [LA,LS]
       because eqang [0][AL,AC] = [LA,LS] and eqang [0][LA,LS] = [HA,HP].
eqang [0][BS,BA] = [SB,SK] = [LA,LS] = [HA,HP] = [AL,AC]
       because eqang [0][SB,SK] = [BS,BA] = [HA,HP] and eqang [0][AL,AC] = [HA,HP] = [LA,LS].
eqang [0][PH,PS] = [AH,AL]
       because circle[$HLAP$].
eqang [0][BS,BC] = [SB,SM] = [AL,AH] = [PS,PH]
       because eqang [0][SB,SM] = [BS,BC] = [PS,PH] and eqang [0][PH,PS] = [AH,AL].
eqang [0][HB,HP] = [AL,AB]
       because circle[$LPAH$].
eqang [0][SB,SL] = [AL,AB] = [HB,HP]
       because eqang [0][SB,SL] = [HB,HP] and eqang [0][HB,HP] = [AL,AB].
eqang [0][QK,QH] = [QA,QK]
       because circle[$(Q)BH$] and midpoint[$K,BH$].
eqang [0][KQ,KS] = [QK,QA] = [QH,QK]
       because eqang [0][QK,QA] = [KQ,KS] and eqang [0][QK,QH] = [QA,QK].
eqang [0][HB,HQ] = [BA,BC]
       because circle[$(Q)BH$] and midpoint[$K,BH$].
eqang [0][QH,QS] = [BC,BA] = [HQ,HB]
       because eqang [0][HQ,HB] = [QH,QS] and eqang [0][HB,HQ] = [BA,BC].
eqang [0][AC,AH] = [SL,SA] = [KS,KB] = [SK,SM] = [QA,QS] = [HB,HQ] = [BA,BC] = [QS,QH]
       because eqang [0][BA,BC] = [QA,QS] = [SK,SM] = [KS,KB] = [SL,SA] = [AC,AH] and eqang [0][QH,QS] = [BC,BA] = [HQ,HB].
eqang [0][AB,AH] = [QA,QK]
       because circle[$(Q)BHA$] and midpoint[$K,BH$].
eqang [0][SK,SA] = [LS,LB] = [SL,SM] = [MA,MS] = [CA,CB] = [QA,QK] = [AB,AH]
       because eqang [0][CA,CB] = [MA,MS] = [SL,SM] = [LS,LB] = [SK,SA] = [AB,AH] and eqang [0][AB,AH] = [QA,QK].
eqang [0][QH,QK] = [KQ,KS] = [AH,AB] = [QK,QA] = [CB,CA] = [MS,MA] = [SM,SL] = [LB,LS] = [SA,SK]
       because eqang [0][KQ,KS] = [QK,QA] = [QH,QK] and eqang [0][SK,SA] = [LS,LB] = [SL,SM] = [MA,MS] = [CA,CB] = [QA,QK] = [AB,AH].
eqang [0][ML,MH] = [MA,ML]
       because circle[$(M)CH$] and midpoint[$L,CH$].
eqang [0][LM,LS] = [ML,MA] = [MH,ML]
       because eqang [0][ML,MA] = [LM,LS] and eqang [0][ML,MH] = [MA,ML].
eqang [0][HB,HM] = [CA,CB]
       because circle[$(M)CH$] and midpoint[$L,CH$].
eqang [0][MH,MS] = [CB,CA] = [HM,HB]
       because eqang [0][HM,HB] = [MH,MS] and eqang [0][HB,HM] = [CA,CB].
eqang [0][SA,SK] = [LB,LS] = [SM,SL] = [MS,MA] = [QK,QA] = [AH,AB] = [KQ,KS] = [QH,QK] = [HM,HB] = [CB,CA] = [MH,MS]
       because eqang [0][QH,QK] = [KQ,KS] = [AH,AB] = [QK,QA] = [CB,CA] = [MS,MA] = [SM,SL] = [LB,LS] = [SA,SK] and eqang [0][MH,MS] = [CB,CA] = [HM,HB].
eqang [0][AC,AH] = [MA,ML]
       because circle[$(M)CHA$] and midpoint[$L,CH$].
eqang [0][QS,QH] = [BA,BC] = [HB,HQ] = [QA,QS] = [SK,SM] = [KS,KB] = [SL,SA] = [MA,ML] = [AC,AH]
       because eqang [0][AC,AH] = [SL,SA] = [KS,KB] = [SK,SM] = [QA,QS] = [HB,HQ] = [BA,BC] = [QS,QH] and eqang [0][AC,AH] = [MA,ML].
eqang [0][MH,ML] = [LM,LS] = [AH,AC] = [ML,MA] = [SA,SL] = [KB,KS] = [SM,SK] = [QS,QA] = [HQ,HB] = [BC,BA] = [QH,QS]
       because eqang [0][LM,LS] = [ML,MA] = [MH,ML] and eqang [0][QS,QH] = [BA,BC] = [HB,HQ] = [QA,QS] = [SK,SM] = [KS,KB] = [SL,SA] = [MA,ML] = [AC,AH].
eqang [0][LQ,LM] = [QL,QK]
       because $[QL,LK]=[LQ,QM]$ and $[LK,KQ]=[QM,ML]$.
eqang [0][SL,QK] = [KS,KB]
       because perp[$BC,QK$] and perp[$SK,SL$].
eqang [0][QH,QS] = [BC,BA] = [HQ,HB] = [QS,QA] = [SM,SK] = [SA,SL] = [ML,MA] = [AH,AC] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL]
       because eqang [0][MH,ML] = [LM,LS] = [AH,AC] = [ML,MA] = [SA,SL] = [KB,KS] = [SM,SK] = [QS,QA] = [HQ,HB] = [BC,BA] = [QH,QS] and eqang [0][SL,QK] = [KS,KB].
eqang [0][LS,LB] = [SK,ML]
       because perp[$BC,ML$] and perp[$SK,SL$].
eqang [0][MH,MS] = [CB,CA] = [HM,HB] = [QH,QK] = [KQ,KS] = [AH,AB] = [QK,QA] = [MS,MA] = [SM,SL] = [SA,SK] = [ML,SK] = [LB,LS]
       because eqang [0][SA,SK] = [LB,LS] = [SM,SL] = [MS,MA] = [QK,QA] = [AH,AB] = [KQ,KS] = [QH,QK] = [HM,HB] = [CB,CA] = [MH,MS] and eqang [0][LS,LB] = [SK,ML].
eqang [0][PS,PN] = [SA,SL]
       because circle[$ANSP$] and para[$AN,SP$].
eqang [0][NP,NA] = [SL,SA] = [PN,PS]
       because eqang [0][NP,NA] = [PN,PS] and eqang [0][PS,PN] = [SA,SL].
eqang [0][QK,SL] = [KB,KS] = [MH,ML] = [LM,LS] = [AH,AC] = [ML,MA] = [SM,SK] = [QS,QA] = [HQ,HB] = [BC,BA] = [QH,QS] = [PS,PN] = [SA,SL] = [NA,NP]
       because eqang [0][QH,QS] = [BC,BA] = [HQ,HB] = [QS,QA] = [SM,SK] = [SA,SL] = [ML,MA] = [AH,AC] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL] and eqang [0][NP,NA] = [SL,SA] = [PN,PS].
eqang [0][PA,PN] = [SA,SK]
       because circle[$ANSP$].
eqang [0][NP,NS] = [SK,SA] = [PN,PA]
       because eqang [0][PN,PA] = [NP,NS] and eqang [0][PA,PN] = [SA,SK].
eqang [0][LB,LS] = [ML,SK] = [SM,SL] = [MS,MA] = [QK,QA] = [AH,AB] = [KQ,KS] = [QH,QK] = [HM,HB] = [CB,CA] = [MH,MS] = [PA,PN] = [SA,SK] = [NS,NP]
       because eqang [0][MH,MS] = [CB,CA] = [HM,HB] = [QH,QK] = [KQ,KS] = [AH,AB] = [QK,QA] = [MS,MA] = [SM,SL] = [SA,SK] = [ML,SK] = [LB,LS] and eqang [0][NP,NS] = [SK,SA] = [PN,PA].
eqang [0][SM,AK] = [SA,SC]
       because $[CS,BC]=[CS,SM]$ and $[CS,AH]=[AK,BC]$.
eqang [0][NA,NH] = [KA,KB] = [SC,SA] = [AK,SM]
       because eqang [0][SC,SA] = [KA,KB] = [NA,NH] and eqang [0][SM,AK] = [SA,SC].
eqang [0][AL,HN] = [CS,HP]
       because $[AH,HP]=[AL,AC]$ and $[CS,AH]=[AC,HN]$.
eqang [0][ML,HN] = [CS,CA]
       because $[AH,AC]=[ML,AC]$ and $[CS,AH]=[AC,HN]$.
eqang [0][AK,AB] = [HA,HN] = [KA,KS] = [SC,SL] = [CS,CA] = [ML,HN]
       because eqang [0][CS,CA] = [SC,SL] = [KA,KS] = [HA,HN] = [AK,AB] and eqang [0][ML,HN] = [CS,CA].
eqang [0][SL,HN] = [SC,SA]
       because $[AH,AC]=[AH,SL]$ and $[CS,AH]=[AC,HN]$.
eqang [0][AK,SM] = [KA,KB] = [NA,NH] = [SC,SA] = [SL,HN]
       because eqang [0][NA,NH] = [KA,KB] = [SC,SA] = [AK,SM] and eqang [0][SL,HN] = [SC,SA].
eqang [0][SL,HN] = [CS,ML]
       because $[ML,AC]=[AH,SL]$ and $[CS,AH]=[AC,HN]$.
eqang [0][SC,SA] = [NA,NH] = [KA,KB] = [AK,SM] = [CS,ML] = [SL,HN]
       because eqang [0][AK,SM] = [KA,KB] = [NA,NH] = [SC,SA] = [SL,HN] and eqang [0][SL,HN] = [CS,ML].
eqang [0][AB,HN] = [SC,SM]
       because $[SM,AC]=[AH,AB]$ and $[CS,AH]=[AC,HN]$.
eqang [0][NS,NH] = [AK,AH] = [CS,CB] = [SC,SM] = [AB,HN]
       because eqang [0][CS,CB] = [SC,SM] = [AK,AH] = [NS,NH] and eqang [0][AB,HN] = [SC,SM].
eqang [0][HB,HN] = [CS,AB]
       because $[AH,AB]=[BC,AC]$ and $[CS,AH]=[AC,HN]$.
eqang [0][AK,AC] = [SC,SK] = [CS,AB] = [HB,HN]
       because eqang [0][SC,SK] = [AK,AC] = [HB,HN] and eqang [0][HB,HN] = [CS,AB].
eqang [0][HP,AC] = [AH,AL]
       because $[AL,HN]=[CS,HP]$ and $[CS,AH]=[AC,HN]$.
eqang [0][PS,PH] = [SB,SM] = [BS,BC] = [AL,AH] = [AC,HP]
       because eqang [0][BS,BC] = [SB,SM] = [AL,AH] = [PS,PH] and eqang [0][HP,AC] = [AH,AL].
eqang [0][HP,HN] = [CS,AL]
       because $[AL,AH]=[AC,HP]$ and $[CS,AH]=[AC,HN]$.
eqang [0][AB,HN] = [AK,ML]
       because $[ML,AC]=[BC,AB]$ and $[AK,BC]=[AC,HN]$.
eqang [0][SC,SM] = [CS,CB] = [AK,AH] = [NS,NH] = [AK,ML] = [AB,HN]
       because eqang [0][NS,NH] = [AK,AH] = [CS,CB] = [SC,SM] = [AB,HN] and eqang [0][AB,HN] = [AK,ML].
eqang [0][NP,NH] = [AK,HQ]
       because $[HQ,BC]=[AC,NP]$ and $[AK,BC]=[AC,HN]$.
eqang [0][SM,HN] = [AK,SL]
       because $[BC,SL]=[SM,AC]$ and $[AK,BC]=[AC,HN]$.
eqang [0][HB,HN] = [AK,SL]
       because $[BC,SL]=[BC,AC]$ and $[AK,BC]=[AC,HN]$.
eqang [0][CS,AB] = [SC,SK] = [AK,AC] = [AK,SL] = [HB,HN]
       because eqang [0][AK,AC] = [SC,SK] = [CS,AB] = [HB,HN] and eqang [0][HB,HN] = [AK,SL].
eqang [0][SM,HN] = [HB,HN] = [AK,SL] = [AK,AC] = [SC,SK] = [CS,AB]
       because eqang [0][SM,HN] = [AK,SL] and eqang [0][CS,AB] = [SC,SK] = [AK,AC] = [AK,SL] = [HB,HN].
eqang [0][HP,HN] = [AK,BS]
       because $[BS,BC]=[AC,HP]$ and $[AK,BC]=[AC,HN]$.
eqang [0][CS,AL] = [AK,BS] = [HP,HN]
       because eqang [0][HP,HN] = [CS,AL] and eqang [0][HP,HN] = [AK,BS].
eqang [0][HQ,AC] = [BC,NP]
       because $[NP,HN]=[AK,HQ]$ and $[AK,BC]=[AC,HN]$.
eqang [0][SM,HP] = [BS,AC]
       because $[AH,AC]=[SM,AB]$ and $[BS,AH]=[AB,HP]$.
eqang [0][HB,HP] = [BS,AC]
       because $[AH,AC]=[BC,AB]$ and $[BS,AH]=[AB,HP]$.
eqang [0][AL,AB] = [SB,SL] = [BS,AC] = [HB,HP]
       because eqang [0][SB,SL] = [AL,AB] = [HB,HP] and eqang [0][HB,HP] = [BS,AC].
eqang [0][SM,HP] = [HB,HP] = [BS,AC] = [SB,SL] = [AL,AB]
       because eqang [0][SM,HP] = [BS,AC] and eqang [0][AL,AB] = [SB,SL] = [BS,AC] = [HB,HP].
eqang [0][PA,PH] = [BS,QK]
       because $[QK,AB]=[AH,AB]$ and $[BS,AH]=[AB,HP]$.
eqang [0][LA,LB] = [SB,SA] = [BS,QK] = [PA,PH]
       because eqang [0][SB,SA] = [LA,LB] = [PA,PH] and eqang [0][PA,PH] = [BS,QK].
eqang [0][SK,HP] = [BS,QK]
       because $[QK,AB]=[AH,SK]$ and $[BS,AH]=[AB,HP]$.
eqang [0][PA,PH] = [SB,SA] = [LA,LB] = [BS,QK] = [SK,HP]
       because eqang [0][LA,LB] = [SB,SA] = [BS,QK] = [PA,PH] and eqang [0][SK,HP] = [BS,QK].
eqang [0][SM,HP] = [AL,SK]
       because $[BC,SK]=[SM,AB]$ and $[AL,BC]=[AB,HP]$.
eqang [0][AL,AB] = [SB,SL] = [BS,AC] = [HB,HP] = [AL,SK] = [SM,HP]
       because eqang [0][SM,HP] = [HB,HP] = [BS,AC] = [SB,SL] = [AL,AB] and eqang [0][SM,HP] = [AL,SK].
eqang [0][PA,PH] = [AL,SM]
       because $[SM,AB]=[BC,AB]$ and $[AL,BC]=[AB,HP]$.
eqang [0][SK,HP] = [BS,QK] = [LA,LB] = [SB,SA] = [AL,SM] = [PA,PH]
       because eqang [0][PA,PH] = [SB,SA] = [LA,LB] = [BS,QK] = [SK,HP] and eqang [0][PA,PH] = [AL,SM].
eqang [0][QK,HP] = [LA,LS]
       because $[BC,SL]=[QK,AB]$ and $[AL,BC]=[AB,HP]$.
eqang [0][AL,AC] = [HA,HP] = [SB,SK] = [BS,BA] = [LA,LS] = [QK,HP]
       because eqang [0][BS,BA] = [SB,SK] = [LA,LS] = [HA,HP] = [AL,AC] and eqang [0][QK,HP] = [LA,LS].
eqang [0][AC,HP] = [AL,QK]
       because $[QK,AB]=[BC,AC]$ and $[AL,BC]=[AB,HP]$.
eqang [0][AL,AH] = [BS,BC] = [SB,SM] = [PS,PH] = [AL,QK] = [AC,HP]
       because eqang [0][PS,PH] = [SB,SM] = [BS,BC] = [AL,AH] = [AC,HP] and eqang [0][AC,HP] = [AL,QK].
eqang [0][PN,PH] = [AL,HM]
       because $[HM,BC]=[AB,NP]$ and $[AL,BC]=[AB,HP]$.
eqang [0][HM,AB] = [BC,NP]
       because $[NP,HP]=[AL,HM]$ and $[AL,BC]=[AB,HP]$.
eqang [0][HQ,AC] = [BC,NP] = [HM,AB]
       because eqang [0][HQ,AC] = [BC,NP] and eqang [0][HM,AB] = [BC,NP].
eqang [0][HM,HP] = [AL,NP]
       because $[BC,NP]=[HM,AB]$ and $[AL,BC]=[AB,HP]$.
eqang [0][HA,HM] = [AC,AH]
       because circle[$(M)AH$].
eqang [0][NA,NP] = [SA,SL] = [PS,PN] = [QH,QS] = [BC,BA] = [HQ,HB] = [QS,QA] = [SM,SK] = [ML,MA] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL] = [AH,AC] = [HM,HA]
       because eqang [0][QK,SL] = [KB,KS] = [MH,ML] = [LM,LS] = [AH,AC] = [ML,MA] = [SM,SK] = [QS,QA] = [HQ,HB] = [BC,BA] = [QH,QS] = [PS,PN] = [SA,SL] = [NA,NP] and eqang [0][HA,HM] = [AC,AH].
eqang [0][HQ,HM] = [AC,AB]
       because coll[$SMQ$], midpoint[$S,AH$], circle[$(M)AH$].
eqang [0][HQ,HA] = [QH,QK]
       because para[$QK,HA$].
eqang [0][NS,NP] = [SA,SK] = [PA,PN] = [MH,MS] = [CB,CA] = [HM,HB] = [KQ,KS] = [AH,AB] = [QK,QA] = [MS,MA] = [SM,SL] = [ML,SK] = [LB,LS] = [QH,QK] = [HQ,HA]
       because eqang [0][LB,LS] = [ML,SK] = [SM,SL] = [MS,MA] = [QK,QA] = [AH,AB] = [KQ,KS] = [QH,QK] = [HM,HB] = [CB,CA] = [MH,MS] = [PA,PN] = [SA,SK] = [NS,NP] and eqang [0][HQ,HA] = [QH,QK].
eqang [0][AK,AH] = [KA,KQ]
       because para[$KQ,AH$].
eqang [0][AB,HN] = [AK,ML] = [NS,NH] = [CS,CB] = [SC,SM] = [KA,KQ] = [AK,AH]
       because eqang [0][SC,SM] = [CS,CB] = [AK,AH] = [NS,NH] = [AK,ML] = [AB,HN] and eqang [0][AK,AH] = [KA,KQ].
eqang [0][MK,ML] = [KM,KQ]
       because para[$KQ,ML$].
eqang [0][LA,LM] = [AL,AH]
       because para[$AH,LM$].
eqang [0][AC,HP] = [AL,QK] = [PS,PH] = [SB,SM] = [BS,BC] = [AL,AH] = [LA,LM]
       because eqang [0][AL,AH] = [BS,BC] = [SB,SM] = [PS,PH] = [AL,QK] = [AC,HP] and eqang [0][LA,LM] = [AL,AH].
eqang [0][QK,AC] = [BC,BA]
       because perp[$AC,AB$] and perp[$BC,QK$].
eqang [0][HM,HA] = [AH,AC] = [QK,SL] = [KB,KS] = [MH,ML] = [LM,LS] = [ML,MA] = [SM,SK] = [QS,QA] = [HQ,HB] = [QH,QS] = [PS,PN] = [SA,SL] = [NA,NP] = [BC,BA] = [QK,AC]
       because eqang [0][NA,NP] = [SA,SL] = [PS,PN] = [QH,QS] = [BC,BA] = [HQ,HB] = [QS,QA] = [SM,SK] = [ML,MA] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL] = [AH,AC] = [HM,HA] and eqang [0][QK,AC] = [BC,BA].
eqang [0][QK,CS] = [KB,KA]
       because perp[$CS,AK$] and perp[$BC,QK$].
eqang [0][SL,HN] = [CS,ML] = [AK,SM] = [NA,NH] = [SC,SA] = [KA,KB] = [CS,QK]
       because eqang [0][SC,SA] = [NA,NH] = [KA,KB] = [AK,SM] = [CS,ML] = [SL,HN] and eqang [0][QK,CS] = [KB,KA].
eqang [0][ML,AB] = [CB,CA]
       because perp[$AC,AB$] and perp[$BC,ML$].
eqang [0][HQ,HA] = [QH,QK] = [LB,LS] = [ML,SK] = [SM,SL] = [MS,MA] = [QK,QA] = [AH,AB] = [KQ,KS] = [HM,HB] = [MH,MS] = [PA,PN] = [SA,SK] = [NS,NP] = [CB,CA] = [ML,AB]
       because eqang [0][NS,NP] = [SA,SK] = [PA,PN] = [MH,MS] = [CB,CA] = [HM,HB] = [KQ,KS] = [AH,AB] = [QK,QA] = [MS,MA] = [SM,SL] = [ML,SK] = [LB,LS] = [QH,QK] = [HQ,HA] and eqang [0][ML,AB] = [CB,CA].
eqang [0][ML,BS] = [LB,LA]
       because perp[$BS,AL$] and perp[$BC,ML$].
eqang [0][PA,PH] = [AL,SM] = [SB,SA] = [BS,QK] = [SK,HP] = [LA,LB] = [BS,ML]
       because eqang [0][SK,HP] = [BS,QK] = [LA,LB] = [SB,SA] = [AL,SM] = [PA,PH] and eqang [0][ML,BS] = [LB,LA].
eqang [0][AK,AL] = [SC,SB]
       because perp[$BS,AL$] and perp[$CS,AK$].
eqang [0][ML,HP] = [LA,LS]
       because $[CS,SL]=[ML,HN]$ and $[AL,HN]=[CS,HP]$.
eqang [0][QK,HP] = [BS,BA] = [SB,SK] = [HA,HP] = [AL,AC] = [LA,LS] = [ML,HP]
       because eqang [0][AL,AC] = [HA,HP] = [SB,SK] = [BS,BA] = [LA,LS] = [QK,HP] and eqang [0][ML,HP] = [LA,LS].
eqang [0][HM,CS] = [NH,NP]
       because $[NP,HP]=[AL,HM]$ and $[AL,HN]=[CS,HP]$.
eqang [1][AK,HQ] = [NP,NH] = [CS,HM]
       because eqang [0][NP,NH] = [AK,HQ] and eqang [0][HM,CS] = [NH,NP].
eqang [0][NP,CS] = [HN,HM]
       because $[HM,HP]=[AL,NP]$ and $[AL,HN]=[CS,HP]$.
eqang [0][SL,SC] = [HN,QK]
       because $[AL,QK]=[SL,HP]$ and $[AL,HN]=[CS,HP]$.
eqang [0][ML,HN] = [CS,CA] = [KA,KS] = [HA,HN] = [AK,AB] = [QK,HN] = [SC,SL]
       because eqang [0][AK,AB] = [HA,HN] = [KA,KS] = [SC,SL] = [CS,CA] = [ML,HN] and eqang [0][SL,SC] = [HN,QK].
eqang [0][HP,AK] = [HN,BS]
       because $[BS,SL]=[SM,HP]$ and $[SM,HN]=[AK,SL]$.
eqang [0][NP,AK] = [HN,HQ]
       because $[HQ,SM]=[SL,NP]$ and $[SM,HN]=[AK,SL]$.
eqang [0][HQ,SL] = [SM,NP]
       because $[NP,AK]=[HN,HQ]$ and $[SM,HN]=[AK,SL]$.
eqang [0][HQ,AC] = [SM,NP]
       because $[NP,AK]=[HN,HQ]$ and $[SM,HN]=[AK,AC]$.
eqang [0][HM,AB] = [BC,NP] = [SM,NP] = [HQ,AC]
       because eqang [0][HQ,AC] = [BC,NP] = [HM,AB] and eqang [0][HQ,AC] = [SM,NP].
eqang [0][HQ,SL] = [HQ,AC] = [SM,NP] = [BC,NP] = [HM,AB]
       because eqang [0][HQ,SL] = [SM,NP] and eqang [0][HM,AB] = [BC,NP] = [SM,NP] = [HQ,AC].
eqang [0][HM,SK] = [SM,NP]
       because $[NP,HN]=[CS,HM]$ and $[SM,HN]=[CS,SK]$.
eqang [1][HM,AB] = [BC,NP] = [HQ,AC] = [HQ,SL] = [SM,NP] = [HM,SK]
       because eqang [0][HQ,SL] = [HQ,AC] = [SM,NP] = [BC,NP] = [HM,AB] and eqang [0][HM,SK] = [SM,NP].
eqang [0][PN,PH] = [BS,HQ]
       because $[AK,HQ]=[NP,HN]$ and $[AK,BS]=[HP,HN]$.
eqang [1][AL,HM] = [BS,HQ] = [PN,PH]
       because eqang [0][PN,PH] = [AL,HM] and eqang [0][PN,PH] = [BS,HQ].
eqang [0][HQ,HP] = [BS,NP]
       because $[NP,AK]=[HN,HQ]$ and $[AK,BS]=[HP,HN]$.
eqang [0][QK,NP] = [MH,MA]
       because $[AC,HP]=[AL,QK]$ and $[HM,HP]=[AL,NP]$.
eqang [0][AH,NP] = [MH,MA]
       because $[AC,HP]=[AL,AH]$ and $[HM,HP]=[AL,NP]$.
eqang [0][QK,NP] = [MH,MA] = [AH,NP]
       because eqang [0][QK,NP] = [MH,MA] and eqang [0][AH,NP] = [MH,MA].
eqang [0][ML,NP] = [MH,MA]
       because $[AC,HP]=[AL,ML]$ and $[HM,HP]=[AL,NP]$.
eqang [0][AH,NP] = [QK,NP] = [MH,MA] = [ML,NP]
       because eqang [0][QK,NP] = [MH,MA] = [AH,NP] and eqang [0][ML,NP] = [MH,MA].
eqang [0][PS,PN] = [HM,QK]
       because $[AL,QK]=[SL,HP]$ and $[HM,HP]=[AL,NP]$.
eqang [0][QK,AC] = [BC,BA] = [NA,NP] = [SA,SL] = [QH,QS] = [HQ,HB] = [QS,QA] = [SM,SK] = [ML,MA] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL] = [AH,AC] = [HM,HA] = [HM,QK] = [PS,PN]
       because eqang [0][HM,HA] = [AH,AC] = [QK,SL] = [KB,KS] = [MH,ML] = [LM,LS] = [ML,MA] = [SM,SK] = [QS,QA] = [HQ,HB] = [QH,QS] = [PS,PN] = [SA,SL] = [NA,NP] = [BC,BA] = [QK,AC] and eqang [0][PS,PN] = [HM,QK].
eqang [0][AH,NP] = [HM,SL]
       because $[SL,HP]=[AL,AH]$ and $[HM,HP]=[AL,NP]$.
eqang [0][ML,NP] = [MH,MA] = [QK,NP] = [HM,SL] = [AH,NP]
       because eqang [0][AH,NP] = [QK,NP] = [MH,MA] = [ML,NP] and eqang [0][AH,NP] = [HM,SL].
eqang [0][ML,AB] = [HQ,ML]
       because $[ML,AC]=[HM,ML]$ and $[HQ,HM]=[AC,AB]$.
eqang [0][CB,CA] = [NS,NP] = [SA,SK] = [PA,PN] = [MH,MS] = [HM,HB] = [KQ,KS] = [AH,AB] = [QK,QA] = [MS,MA] = [SM,SL] = [ML,SK] = [LB,LS] = [QH,QK] = [HQ,HA] = [HQ,ML] = [ML,AB]
       because eqang [0][HQ,HA] = [QH,QK] = [LB,LS] = [ML,SK] = [SM,SL] = [MS,MA] = [QK,QA] = [AH,AB] = [KQ,KS] = [HM,HB] = [MH,MS] = [PA,PN] = [SA,SK] = [NS,NP] = [CB,CA] = [ML,AB] and eqang [0][ML,AB] = [HQ,ML].
eqang [0][PS,PA] = [HQ,HM]
       because $[HM,AC]=[HM,SL]$ and $[HQ,HM]=[AC,AB]$.
eqang [0][AC,AB] = [HQ,HM] = [PS,PA]
       because eqang [0][HQ,HM] = [AC,AB] and eqang [0][PS,PA] = [HQ,HM].
eqang [0][NP,ML] = [QA,QH]
       because $[AK,HQ]=[NP,HN]$ and $[AB,HN]=[AK,ML]$.
eqang [0][AH,NP] = [HM,SL] = [QK,NP] = [MH,MA] = [QH,QA] = [ML,NP]
       because eqang [0][ML,NP] = [MH,MA] = [QK,NP] = [HM,SL] = [AH,NP] and eqang [0][NP,ML] = [QA,QH].
eqang [0][HQ,SK] = [ML,NP]
       because $[NP,AK]=[HN,HQ]$ and $[AK,ML]=[SK,HN]$.
eqang [1][QH,QA] = [MH,MA] = [QK,NP] = [HM,SL] = [AH,NP] = [ML,NP] = [HQ,SK]
       because eqang [0][AH,NP] = [HM,SL] = [QK,NP] = [MH,MA] = [QH,QA] = [ML,NP] and eqang [0][HQ,SK] = [ML,NP].
eqang [0][KA,KB] = [QA,QC]
       because circle[$ACQK$].
eqang [0][CS,QK] = [SC,SA] = [NA,NH] = [AK,SM] = [CS,ML] = [SL,HN] = [QA,QC] = [KA,KB]
       because eqang [0][SL,HN] = [CS,ML] = [AK,SM] = [NA,NH] = [SC,SA] = [KA,KB] = [CS,QK] and eqang [0][KA,KB] = [QA,QC].
eqang [0][KA,KQ] = [CA,CQ]
       because circle[$AQCK$].
eqang [0][AK,AH] = [SC,SM] = [CS,CB] = [NS,NH] = [AK,ML] = [AB,HN] = [CA,CQ] = [KA,KQ]
       because eqang [0][AB,HN] = [AK,ML] = [NS,NH] = [CS,CB] = [SC,SM] = [KA,KQ] = [AK,AH] and eqang [0][KA,KQ] = [CA,CQ].
eqang [0][QC,QK] = [AC,AK]
       because circle[$CKAQ$].
eqang [0][CS,AB] = [SC,SK] = [AK,SL] = [HB,HN] = [SM,HN] = [AK,AC] = [QK,QC]
       because eqang [0][SM,HN] = [HB,HN] = [AK,SL] = [AK,AC] = [SC,SK] = [CS,AB] and eqang [0][QC,QK] = [AC,AK].
eqang [0][CQ,CB] = [AB,AK]
       because circle[$QKAC$].
eqang [0][QC,QS] = [AB,AK] = [CQ,CB]
       because eqang [0][CQ,CB] = [QC,QS] and eqang [0][CQ,CB] = [AB,AK].
eqang [1][SC,SL] = [QK,HN] = [HA,HN] = [KA,KS] = [CS,CA] = [ML,HN] = [CB,CQ] = [AK,AB] = [QS,QC]
       because eqang [0][ML,HN] = [CS,CA] = [KA,KS] = [HA,HN] = [AK,AB] = [QK,HN] = [SC,SL] and eqang [0][QC,QS] = [AB,AK] = [CQ,CB].
eqang [0][LA,LB] = [MA,MB]
       because circle[$ABML$].
eqang [0][BS,ML] = [SK,HP] = [BS,QK] = [SB,SA] = [AL,SM] = [PA,PH] = [MA,MB] = [LA,LB]
       because eqang [0][PA,PH] = [AL,SM] = [SB,SA] = [BS,QK] = [SK,HP] = [LA,LB] = [BS,ML] and eqang [0][LA,LB] = [MA,MB].
eqang [0][LA,LM] = [BA,BM]
       because circle[$AMBL$].
eqang [0][AL,AH] = [BS,BC] = [SB,SM] = [PS,PH] = [AL,QK] = [AC,HP] = [BA,BM] = [LA,LM]
       because eqang [0][AC,HP] = [AL,QK] = [PS,PH] = [SB,SM] = [BS,BC] = [AL,AH] = [LA,LM] and eqang [0][LA,LM] = [BA,BM].
eqang [0][MB,ML] = [AB,AL]
       because circle[$BLAM$].
eqang [0][SM,HP] = [AL,SK] = [HB,HP] = [BS,AC] = [SB,SL] = [AL,AB] = [ML,MB]
       because eqang [0][AL,AB] = [SB,SL] = [BS,AC] = [HB,HP] = [AL,SK] = [SM,HP] and eqang [0][MB,ML] = [AB,AL].
eqang [0][BM,BC] = [AC,AL]
       because circle[$MLAB$].
eqang [0][MB,MS] = [AC,AL] = [BM,BC]
       because eqang [0][BM,BC] = [MB,MS] and eqang [0][BM,BC] = [AC,AL].
eqang [1][ML,HP] = [LA,LS] = [HA,HP] = [SB,SK] = [BS,BA] = [QK,HP] = [BC,BM] = [AL,AC] = [MS,MB]
       because eqang [0][QK,HP] = [BS,BA] = [SB,SK] = [HA,HP] = [AL,AC] = [LA,LS] = [ML,HP] and eqang [0][MB,MS] = [AC,AL] = [BM,BC].
eqang [0][LB,LQ] = [KM,KB]
       because circle[$MQKL$] and para[$MQ,KL$].
eqang [0][MK,MS] = [KM,KB] = [LB,LQ]
       because eqang [0][KM,KB] = [MK,MS] and eqang [0][LB,LQ] = [KM,KB].
eqang [0][QL,QS] = [LQ,LB] = [KB,KM] = [MS,MK]
       because eqang [0][LQ,LB] = [QL,QS] and eqang [0][MK,MS] = [KM,KB] = [LB,LQ].
eqang [0][NA,NQ] = [KM,KQ]
       because circle[$MQKN$].
eqang [0][MK,ML] = [KM,KQ] = [NA,NQ]
       because eqang [0][MK,ML] = [KM,KQ] and eqang [0][NA,NQ] = [KM,KQ].
eqang [0][LM,LQ] = [KM,KQ]
       because circle[$MQKL$].
eqang [0][QL,QK] = [KQ,KM] = [LQ,LM]
       because eqang [0][LQ,LM] = [QL,QK] and eqang [0][LM,LQ] = [KM,KQ].
eqang [0][NA,NQ] = [MK,ML] = [LM,LQ] = [KM,KQ] = [QK,QL]
       because eqang [0][MK,ML] = [KM,KQ] = [NA,NQ] and eqang [0][QL,QK] = [KQ,KM] = [LQ,LM].
eqang [0][PM,PA] = [KM,KQ]
       because circle[$MQKP$].
eqang [0][QK,QL] = [LM,LQ] = [MK,ML] = [NA,NQ] = [KM,KQ] = [PM,PA]
       because eqang [0][NA,NQ] = [MK,ML] = [LM,LQ] = [KM,KQ] = [QK,QL] and eqang [0][PM,PA] = [KM,KQ].
eqang [0][PM,PK] = [QS,QK]
       because circle[$MKQP$].
eqang [0][PS,PM] = [LN,LS]
       because circle[$MNLP$] and para[$MN,LP$].
eqang [0][NL,NA] = [LN,LS] = [PS,PM]
       because eqang [0][NL,NA] = [LN,LS] and eqang [0][PS,PM] = [LN,LS].
eqang [0][MP,MA] = [PM,PS] = [LS,LN] = [NA,NL]
       because eqang [0][MP,MA] = [PM,PS] and eqang [0][NL,NA] = [LN,LS] = [PS,PM].
eqang [0][KM,KS] = [QS,QN]
       because circle[$MNQK$].
eqang [0][LM,LN] = [QS,QN]
       because circle[$MNQL$].
eqang [0][KM,KS] = [QS,QN] = [LM,LN]
       because eqang [0][KM,KS] = [QS,QN] and eqang [0][LM,LN] = [QS,QN].
eqang [0][PM,PN] = [QS,QN]
       because circle[$MNQP$].
eqang [0][LM,LN] = [KM,KS] = [QS,QN] = [PM,PN]
       because eqang [0][KM,KS] = [QS,QN] = [LM,LN] and eqang [0][PM,PN] = [QS,QN].
eqang [0][NA,NL] = [QS,QL]
       because circle[$MLQN$].
eqang [0][MS,MK] = [KB,KM] = [LQ,LB] = [QL,QS] = [NL,NA]
       because eqang [0][QL,QS] = [LQ,LB] = [KB,KM] = [MS,MK] and eqang [0][NA,NL] = [QS,QL].
eqang [0][LS,LN] = [PM,PS] = [MP,MA] = [NA,NL] = [QS,QL] = [LB,LQ] = [KM,KB] = [MK,MS]
       because eqang [0][MP,MA] = [PM,PS] = [LS,LN] = [NA,NL] and eqang [0][MS,MK] = [KB,KM] = [LQ,LB] = [QL,QS] = [NL,NA].
eqang [0][KM,KP] = [QS,QA]
       because circle[$MPQK$].
eqang [0][PS,PN] = [HM,QK] = [HM,HA] = [AH,AC] = [QK,SL] = [KB,KS] = [MH,ML] = [LM,LS] = [ML,MA] = [SM,SK] = [HQ,HB] = [QH,QS] = [SA,SL] = [NA,NP] = [BC,BA] = [QK,AC] = [QS,QA] = [KM,KP]
       because eqang [0][QK,AC] = [BC,BA] = [NA,NP] = [SA,SL] = [QH,QS] = [HQ,HB] = [QS,QA] = [SM,SK] = [ML,MA] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL] = [AH,AC] = [HM,HA] = [HM,QK] = [PS,PN] and eqang [0][KM,KP] = [QS,QA].
eqang [0][NQ,NS] = [MS,MK]
       because circle[$QKMN$].
eqang [0][QN,QA] = [MS,MK] = [NQ,NS]
       because eqang [0][QN,QA] = [NQ,NS] and eqang [0][NQ,NS] = [MS,MK].
eqang [0][KM,KB] = [LB,LQ] = [QS,QL] = [NA,NL] = [MP,MA] = [PM,PS] = [LS,LN] = [NS,NQ] = [MK,MS] = [QA,QN]
       because eqang [0][LS,LN] = [PM,PS] = [MP,MA] = [NA,NL] = [QS,QL] = [LB,LQ] = [KM,KB] = [MK,MS] and eqang [0][QN,QA] = [MS,MK] = [NQ,NS].
eqang [0][PA,PK] = [MS,MK]
       because circle[$QKMP$].
eqang [0][KP,KS] = [MK,MS] = [PK,PA]
       because eqang [0][PK,PA] = [KP,KS] and eqang [0][PA,PK] = [MS,MK].
eqang [1][QA,QN] = [NS,NQ] = [LS,LN] = [PM,PS] = [MP,MA] = [NA,NL] = [QS,QL] = [LB,LQ] = [KM,KB] = [PK,PA] = [MK,MS] = [KP,KS]
       because eqang [0][KM,KB] = [LB,LQ] = [QS,QL] = [NA,NL] = [MP,MA] = [PM,PS] = [LS,LN] = [NS,NQ] = [MK,MS] = [QA,QN] and eqang [0][KP,KS] = [MK,MS] = [PK,PA].
eqang [0][LQ,LN] = [MS,MA]
       because circle[$QNML$].
eqang [0][ML,AB] = [HQ,ML] = [HQ,HA] = [QH,QK] = [LB,LS] = [ML,SK] = [SM,SL] = [QK,QA] = [AH,AB] = [KQ,KS] = [HM,HB] = [MH,MS] = [PA,PN] = [SA,SK] = [NS,NP] = [CB,CA] = [MS,MA] = [LQ,LN]
       because eqang [0][CB,CA] = [NS,NP] = [SA,SK] = [PA,PN] = [MH,MS] = [HM,HB] = [KQ,KS] = [AH,AB] = [QK,QA] = [MS,MA] = [SM,SL] = [ML,SK] = [LB,LS] = [QH,QK] = [HQ,HA] = [HQ,ML] = [ML,AB] and eqang [0][LQ,LN] = [MS,MA].
eqang [0][NQ,NL] = [MS,ML]
       because circle[$QLMN$].
eqang [0][KQ,KP] = [MS,MP]
       because circle[$QPMK$].
eqang [0][NQ,NP] = [MS,MP]
       because circle[$QPMN$].
eqang [0][KQ,KP] = [MS,MP] = [NQ,NP]
       because eqang [0][KQ,KP] = [MS,MP] and eqang [0][NQ,NP] = [MS,MP].
eqang [0][LQ,LS] = [MS,MP]
       because circle[$QPML$].
eqang [0][NQ,NP] = [KQ,KP] = [MS,MP] = [LQ,LS]
       because eqang [0][KQ,KP] = [MS,MP] = [NQ,NP] and eqang [0][LQ,LS] = [MS,MP].
eqang [0][QK,QN] = [MK,MA]
       because circle[$KNMQ$].
eqang [0][LB,LN] = [MK,MA]
       because circle[$KNML$].
eqang [0][QK,QN] = [MK,MA] = [LB,LN]
       because eqang [0][QK,QN] = [MK,MA] and eqang [0][LB,LN] = [MK,MA].
eqang [0][PK,PN] = [MK,MA]
       because circle[$KNMP$].
eqang [0][LB,LN] = [QK,QN] = [MK,MA] = [PK,PN]
       because eqang [0][QK,QN] = [MK,MA] = [LB,LN] and eqang [0][PK,PN] = [MK,MA].
eqang [0][NS,NL] = [MK,ML]
       because circle[$KLMN$].
eqang [0][PM,PA] = [KM,KQ] = [NA,NQ] = [LM,LQ] = [QK,QL] = [MK,ML] = [NS,NL]
       because eqang [0][QK,QL] = [LM,LQ] = [MK,ML] = [NA,NQ] = [KM,KQ] = [PM,PA] and eqang [0][NS,NL] = [MK,ML].
eqang [0][PK,PS] = [MK,ML]
       because circle[$KLMP$].
eqang [0][NS,NL] = [QK,QL] = [LM,LQ] = [NA,NQ] = [KM,KQ] = [PM,PA] = [MK,ML] = [PK,PS]
       because eqang [0][PM,PA] = [KM,KQ] = [NA,NQ] = [LM,LQ] = [QK,QL] = [MK,ML] = [NS,NL] and eqang [0][PK,PS] = [MK,ML].
eqang [0][QK,QA] = [MK,MP]
       because circle[$KPMQ$].
eqang [1][LQ,LN] = [MS,MA] = [CB,CA] = [NS,NP] = [SA,SK] = [PA,PN] = [MH,MS] = [HM,HB] = [KQ,KS] = [AH,AB] = [SM,SL] = [ML,SK] = [LB,LS] = [QH,QK] = [HQ,HA] = [HQ,ML] = [ML,AB] = [MK,MP] = [QK,QA]
       because eqang [0][ML,AB] = [HQ,ML] = [HQ,HA] = [QH,QK] = [LB,LS] = [ML,SK] = [SM,SL] = [QK,QA] = [AH,AB] = [KQ,KS] = [HM,HB] = [MH,MS] = [PA,PN] = [SA,SK] = [NS,NP] = [CB,CA] = [MS,MA] = [LQ,LN] and eqang [0][QK,QA] = [MK,MP].
eqang [0][QN,QL] = [MA,ML]
       because circle[$NLMQ$].
eqang [1][KM,KP] = [QS,QA] = [QK,AC] = [BC,BA] = [NA,NP] = [SA,SL] = [QH,QS] = [HQ,HB] = [SM,SK] = [LM,LS] = [MH,ML] = [KB,KS] = [QK,SL] = [AH,AC] = [HM,HA] = [HM,QK] = [PS,PN] = [ML,MA] = [QL,QN]
       because eqang [0][PS,PN] = [HM,QK] = [HM,HA] = [AH,AC] = [QK,SL] = [KB,KS] = [MH,ML] = [LM,LS] = [ML,MA] = [SM,SK] = [HQ,HB] = [QH,QS] = [SA,SL] = [NA,NP] = [BC,BA] = [QK,AC] = [QS,QA] = [KM,KP] and eqang [0][QN,QL] = [MA,ML].
eqang [0][QL,QA] = [ML,MP]
       because circle[$LPMQ$].
eqang [0][KB,KP] = [ML,MP]
       because circle[$LPMK$].
eqang [0][QL,QA] = [ML,MP] = [KB,KP]
       because eqang [0][QL,QA] = [ML,MP] and eqang [0][KB,KP] = [ML,MP].
eqang [0][NL,NP] = [ML,MP]
       because circle[$LPMN$].
eqang [0][KB,KP] = [QL,QA] = [ML,MP] = [NL,NP]
       because eqang [0][QL,QA] = [ML,MP] = [KB,KP] and eqang [0][NL,NP] = [ML,MP].
eqang [0][AK,NP] = [HM,CQ]
       because $[AB,CQ]=[AK,BC]$ and $[HM,AB]=[BC,NP]$.
eqang [1][HN,HQ] = [CQ,HM] = [NP,AK]
       because eqang [0][NP,AK] = [HN,HQ] and eqang [0][AK,NP] = [HM,CQ].
eqang [0][BM,NP] = [HM,BS]
       because $[BS,BC]=[AB,BM]$ and $[HM,AB]=[BC,NP]$.
eqang [0][MK,NP] = [HM,QN]
       because $[AB,QN]=[MK,BC]$ and $[HM,AB]=[BC,NP]$.
eqang [0][PK,PN] = [HM,QL]
       because $[BC,QL]=[KP,AB]$ and $[HM,AB]=[BC,NP]$.
eqang [0][MK,MA] = [QK,QN] = [LB,LN] = [HM,QL] = [PK,PN]
       because eqang [0][LB,LN] = [QK,QN] = [MK,MA] = [PK,PN] and eqang [0][PK,PN] = [HM,QL].
eqang [0][QL,NP] = [HM,KP]
       because $[BC,KP]=[QL,AB]$ and $[HM,AB]=[BC,NP]$.
eqang [0][QN,BC] = [AB,MK]
       because $[MK,NP]=[HM,QN]$ and $[HM,AB]=[BC,NP]$.
eqang [0][QL,QH] = [AB,MK]
       because $[MK,AC]=[HM,QL]$ and $[HM,AB]=[HQ,AC]$.
eqang [0][QN,BC] = [AB,MK] = [QL,QH]
       because eqang [0][QN,BC] = [AB,MK] and eqang [0][QL,QH] = [AB,MK].
eqang [0][QL,AC] = [MH,MK]
       because $[AB,MK]=[QL,HQ]$ and $[HM,AB]=[HQ,AC]$.
eqang [0][MK,HQ] = [QA,QL]
       because $[QL,AC]=[HM,MK]$ and $[HM,AB]=[HQ,AC]$.
eqang [0][NL,NP] = [ML,MP] = [KB,KP] = [QL,QA] = [HQ,MK]
       because eqang [0][KB,KP] = [QL,QA] = [ML,MP] = [NL,NP] and eqang [0][MK,HQ] = [QA,QL].
eqang [0][LQ,LS] = [MH,MK]
       because $[AB,MK]=[QL,HQ]$ and $[HM,AB]=[HQ,SL]$.
eqang [0][MS,MP] = [KQ,KP] = [NQ,NP] = [MH,MK] = [LQ,LS]
       because eqang [0][NQ,NP] = [KQ,KP] = [MS,MP] = [LQ,LS] and eqang [0][LQ,LS] = [MH,MK].
eqang [0][QL,AC] = [LQ,LS] = [MH,MK] = [NQ,NP] = [KQ,KP] = [MS,MP]
       because eqang [0][QL,AC] = [MH,MK] and eqang [0][MS,MP] = [KQ,KP] = [NQ,NP] = [MH,MK] = [LQ,LS].
eqang [0][MK,SL] = [HM,QL]
       because $[QL,AB]=[HQ,MK]$ and $[HM,AB]=[HQ,SL]$.
eqang [0][PK,PN] = [LB,LN] = [QK,QN] = [MK,MA] = [HM,QL] = [MK,SL]
       because eqang [0][MK,MA] = [QK,QN] = [LB,LN] = [HM,QL] = [PK,PN] and eqang [0][MK,SL] = [HM,QL].
eqang [0][CQ,NP] = [HM,AK]
       because $[AK,SM]=[AB,CQ]$ and $[HM,AB]=[SM,NP]$.
eqang [0][QN,QS] = [AB,MK]
       because $[MK,NP]=[HM,QN]$ and $[HM,AB]=[SM,NP]$.
eqang [0][PM,PN] = [KM,KS] = [LM,LN] = [MK,AB] = [QS,QN]
       because eqang [0][LM,LN] = [KM,KS] = [QS,QN] = [PM,PN] and eqang [0][QN,QS] = [AB,MK].
eqang [0][QL,QH] = [QN,BC] = [QN,QS] = [AB,MK] = [LN,LM] = [KS,KM] = [PN,PM]
       because eqang [0][QN,BC] = [AB,MK] = [QL,QH] and eqang [0][PM,PN] = [KM,KS] = [LM,LN] = [MK,AB] = [QS,QN].
eqang [0][KP,SM] = [QA,QL]
       because $[QL,NP]=[HM,KP]$ and $[HM,AB]=[SM,NP]$.
eqang [0][HQ,MK] = [KB,KP] = [ML,MP] = [NL,NP] = [QL,QA] = [SM,KP]
       because eqang [0][NL,NP] = [ML,MP] = [KB,KP] = [QL,QA] = [HQ,MK] and eqang [0][KP,SM] = [QA,QL].
eqang [0][QC,QH] = [NP,CS]
       because $[CS,BC]=[AC,CQ]$ and $[BC,NP]=[HQ,AC]$.
eqang [1][HN,HM] = [NP,CS] = [QC,QH]
       because eqang [0][NP,CS] = [HN,HM] and eqang [0][QC,QH] = [NP,CS].
eqang [0][AL,HQ] = [NP,BM]
       because $[AC,BM]=[AL,BC]$ and $[BC,NP]=[HQ,AC]$.
eqang [1][HM,BS] = [BM,NP] = [HQ,AL]
       because eqang [0][BM,NP] = [HM,BS] and eqang [0][AL,HQ] = [NP,BM].
eqang [0][BM,HQ] = [NP,AL]
       because $[AL,SL]=[BC,BM]$ and $[BC,NP]=[HQ,SL]$.
eqang [1][HM,HP] = [AL,NP] = [HQ,BM]
       because eqang [0][HM,HP] = [AL,NP] and eqang [0][BM,HQ] = [NP,AL].
eqang [0][CQ,SL] = [CB,CS]
       because $[NP,CS]=[CQ,HQ]$ and $[BC,NP]=[HQ,SL]$.
eqang [1][KA,KQ] = [CA,CQ] = [AB,HN] = [AK,ML] = [NS,NH] = [SC,SM] = [AK,AH] = [CS,CB] = [SL,CQ]
       because eqang [0][AK,AH] = [SC,SM] = [CS,CB] = [NS,NH] = [AK,ML] = [AB,HN] = [CA,CQ] = [KA,KQ] and eqang [0][CQ,SL] = [CB,CS].
eqang [0][BM,SL] = [LB,LA]
       because $[AL,NP]=[HQ,BM]$ and $[BC,NP]=[HQ,SL]$.
eqang [1][MA,MB] = [PA,PH] = [AL,SM] = [SB,SA] = [BS,QK] = [SK,HP] = [BS,ML] = [LA,LB] = [SL,BM]
       because eqang [0][BS,ML] = [SK,HP] = [BS,QK] = [SB,SA] = [AL,SM] = [PA,PH] = [MA,MB] = [LA,LB] and eqang [0][BM,SL] = [LB,LA].
eqang [0][NL,SM] = [PN,PK]
       because $[KP,NP]=[BC,NL]$ and $[BC,NP]=[SM,NP]$.
eqang [0][MK,SL] = [HM,QL] = [MK,MA] = [QK,QN] = [LB,LN] = [PK,PN] = [SM,NL]
       because eqang [0][PK,PN] = [LB,LN] = [QK,QN] = [MK,MA] = [HM,QL] = [MK,SL] and eqang [0][NL,SM] = [PN,PK].
eqang [0][MB,MH] = [NP,BS]
       because $[BS,SK]=[BC,BM]$ and $[BC,NP]=[HM,SK]$.
eqang [1][HQ,HP] = [BS,NP] = [MH,MB]
       because eqang [0][HQ,HP] = [BS,NP] and eqang [0][MB,MH] = [NP,BS].
eqang [0][BM,SK] = [BC,BS]
       because $[HM,BS]=[BM,NP]$ and $[BC,NP]=[HM,SK]$.
eqang [1][LA,LM] = [BA,BM] = [AC,HP] = [AL,QK] = [PS,PH] = [SB,SM] = [AL,AH] = [BS,BC] = [SK,BM]
       because eqang [0][AL,AH] = [BS,BC] = [SB,SM] = [PS,PH] = [AL,QK] = [AC,HP] = [BA,BM] = [LA,LM] and eqang [0][BM,SK] = [BC,BS].
eqang [0][CQ,NP] = [HQ,CS]
       because $[CS,AC]=[SM,CQ]$ and $[HQ,AC]=[SM,NP]$.
eqang [1][HM,AK] = [HQ,CS] = [CQ,NP]
       because eqang [0][CQ,NP] = [HM,AK] and eqang [0][CQ,NP] = [HQ,CS].
eqang [0][QL,NP] = [HQ,MP]
       because $[MP,AC]=[SM,QL]$ and $[HQ,AC]=[SM,NP]$.
eqang [1][HM,KP] = [HQ,MP] = [QL,NP]
       because eqang [0][QL,NP] = [HM,KP] and eqang [0][QL,NP] = [HQ,MP].
eqang [0][MK,NP] = [HQ,NL]
       because $[AC,NL]=[MK,SM]$ and $[HQ,AC]=[SM,NP]$.
eqang [1][HM,QN] = [HQ,NL] = [MK,NP]
       because eqang [0][MK,NP] = [HM,QN] and eqang [0][MK,NP] = [HQ,NL].
eqang [0][QL,SK] = [HQ,MK]
       because $[MK,SL]=[HM,QL]$ and $[HQ,SL]=[HM,SK]$.
eqang [0][SM,KP] = [QL,QA] = [NL,NP] = [ML,MP] = [KB,KP] = [HQ,MK] = [QL,SK]
       because eqang [0][HQ,MK] = [KB,KP] = [ML,MP] = [NL,NP] = [QL,QA] = [SM,KP] and eqang [0][QL,SK] = [HQ,MK].
eqang [0][CQ,SK] = [SM,AK]
       because $[HM,AK]=[CQ,NP]$ and $[SM,NP]=[HM,SK]$.
eqang [1][KA,KB] = [QA,QC] = [SL,HN] = [CS,ML] = [NA,NH] = [SC,SA] = [CS,QK] = [AK,SM] = [SK,CQ]
       because eqang [0][CS,QK] = [SC,SA] = [NA,NH] = [AK,SM] = [CS,ML] = [SL,HN] = [QA,QC] = [KA,KB] and eqang [0][CQ,SK] = [SM,AK].
eqang [0][AK,HP] = [AL,CQ]
       because $[CQ,HM]=[NP,AK]$ and $[AL,HM]=[NP,HP]$.
eqang [0][HN,BS] = [CQ,AL] = [HP,AK]
       because eqang [0][HP,AK] = [HN,BS] and eqang [0][AK,HP] = [AL,CQ].
eqang [0][QN,HP] = [AL,MK]
       because $[HM,MK]=[QN,NP]$ and $[AL,HM]=[NP,HP]$.
eqang [0][BM,HP] = [AL,BS]
       because $[HM,BS]=[BM,NP]$ and $[AL,HM]=[NP,HP]$.
eqang [0][PK,PH] = [LA,LQ]
       because $[HM,QL]=[KP,NP]$ and $[AL,HM]=[NP,HP]$.
eqang [0][CQ,HP] = [AL,AK]
       because $[HM,AK]=[CQ,NP]$ and $[AL,HM]=[NP,HP]$.
eqang [0][SC,SB] = [AK,AL] = [HP,CQ]
       because eqang [0][AK,AL] = [SC,SB] and eqang [0][CQ,HP] = [AL,AK].
eqang [0][QL,HP] = [AL,KP]
       because $[HM,KP]=[QL,NP]$ and $[AL,HM]=[NP,HP]$.
eqang [0][MK,HP] = [AL,QN]
       because $[HM,QN]=[MK,NP]$ and $[AL,HM]=[NP,HP]$.
eqang [0][PM,PH] = [BS,QL]
       because $[QL,HQ]=[NP,MP]$ and $[BS,HQ]=[NP,HP]$.
eqang [1][AL,HP] = [BS,BM]
       because $[BM,NP]=[HQ,AL]$ and $[BS,HQ]=[NP,HP]$.
eqang [0][BM,HP] = [BS,AL]
       because $[AL,NP]=[HQ,BM]$ and $[BS,HQ]=[NP,HP]$.
eqang [0][AL,BS] = [BS,AL] = [BM,HP]
       because eqang [0][BM,HP] = [AL,BS] and eqang [0][BM,HP] = [BS,AL].
eqang [0][QL,HP] = [BS,MP]
       because $[HQ,MP]=[QL,NP]$ and $[BS,HQ]=[NP,HP]$.
eqang [1][AL,KP] = [BS,MP] = [QL,HP]
       because eqang [0][QL,HP] = [AL,KP] and eqang [0][QL,HP] = [BS,MP].
eqang [0][MK,HP] = [BS,NL]
       because $[HQ,NL]=[MK,NP]$ and $[BS,HQ]=[NP,HP]$.
eqang [1][AL,QN] = [BS,NL] = [MK,HP]
       because eqang [0][MK,HP] = [AL,QN] and eqang [0][MK,HP] = [BS,NL].
eqang [0][MP,QK] = [QA,QL]
       because $[QL,HQ]=[NP,MP]$ and $[HQ,AB]=[QK,NP]$.
eqang [0][QL,SK] = [HQ,MK] = [KB,KP] = [ML,MP] = [NL,NP] = [SM,KP] = [QL,QA] = [QK,MP]
       because eqang [0][SM,KP] = [QL,QA] = [NL,NP] = [ML,MP] = [KB,KP] = [HQ,MK] = [QL,SK] and eqang [0][MP,QK] = [QA,QL].
eqang [0][BM,QK] = [AB,AL]
       because $[AL,NP]=[HQ,BM]$ and $[HQ,AB]=[QK,NP]$.
eqang [0][ML,MB] = [SB,SL] = [BS,AC] = [HB,HP] = [AL,SK] = [SM,HP] = [AL,AB] = [QK,BM]
       because eqang [0][SM,HP] = [AL,SK] = [HB,HP] = [BS,AC] = [SB,SL] = [AL,AB] = [ML,MB] and eqang [0][BM,QK] = [AB,AL].
eqang [0][KM,KQ] = [AB,NL]
       because $[HQ,NL]=[MK,NP]$ and $[HQ,AB]=[QK,NP]$.
eqang [0][PK,PS] = [MK,ML] = [PM,PA] = [NA,NQ] = [LM,LQ] = [QK,QL] = [NS,NL] = [AB,NL] = [KM,KQ]
       because eqang [0][NS,NL] = [QK,QL] = [LM,LQ] = [NA,NQ] = [KM,KQ] = [PM,PA] = [MK,ML] = [PK,PS] and eqang [0][KM,KQ] = [AB,NL].
eqang [0][NL,QK] = [AB,MK]
       because $[HQ,MK]=[NL,NP]$ and $[HQ,AB]=[QK,NP]$.
eqang [0][PN,PM] = [KS,KM] = [LN,LM] = [QN,QS] = [QN,BC] = [QL,QH] = [AB,MK] = [NL,QK]
       because eqang [0][QL,QH] = [QN,BC] = [QN,QS] = [AB,MK] = [LN,LM] = [KS,KM] = [PN,PM] and eqang [0][NL,QK] = [AB,MK].
eqang [0][CQ,AH] = [AB,CS]
       because $[NP,CS]=[CQ,HQ]$ and $[HQ,AB]=[AH,NP]$.
eqang [0][QK,QC] = [AK,AC] = [SM,HN] = [HB,HN] = [AK,SL] = [SC,SK] = [CS,AB] = [AH,CQ]
       because eqang [0][CS,AB] = [SC,SK] = [AK,SL] = [HB,HN] = [SM,HN] = [AK,AC] = [QK,QC] and eqang [0][CQ,AH] = [AB,CS].
eqang [0][BM,AH] = [AB,AL]
       because $[AL,NP]=[HQ,BM]$ and $[HQ,AB]=[AH,NP]$.
eqang [1][QK,BM] = [SM,HP] = [AL,SK] = [HB,HP] = [BS,AC] = [SB,SL] = [ML,MB] = [AL,AB] = [AH,BM]
       because eqang [0][ML,MB] = [SB,SL] = [BS,AC] = [HB,HP] = [AL,SK] = [SM,HP] = [AL,AB] = [QK,BM] and eqang [0][BM,AH] = [AB,AL].
eqang [0][QL,AH] = [PA,PM]
       because $[HQ,MP]=[QL,NP]$ and $[HQ,AB]=[AH,NP]$.
eqang [0][KM,KQ] = [AB,NL] = [NS,NL] = [QK,QL] = [LM,LQ] = [NA,NQ] = [MK,ML] = [PK,PS] = [PM,PA] = [AH,QL]
       because eqang [0][PK,PS] = [MK,ML] = [PM,PA] = [NA,NQ] = [LM,LQ] = [QK,QL] = [NS,NL] = [AB,NL] = [KM,KQ] and eqang [0][QL,AH] = [PA,PM].
eqang [0][MK,AH] = [AB,NL]
       because $[HQ,NL]=[MK,NP]$ and $[HQ,AB]=[AH,NP]$.
eqang [0][AH,QL] = [PM,PA] = [PK,PS] = [MK,ML] = [NA,NQ] = [LM,LQ] = [QK,QL] = [NS,NL] = [KM,KQ] = [AB,NL] = [MK,AH]
       because eqang [0][KM,KQ] = [AB,NL] = [NS,NL] = [QK,QL] = [LM,LQ] = [NA,NQ] = [MK,ML] = [PK,PS] = [PM,PA] = [AH,QL] and eqang [0][MK,AH] = [AB,NL].
eqang [0][NL,AH] = [AB,MK]
       because $[HQ,MK]=[NL,NP]$ and $[HQ,AB]=[AH,NP]$.
eqang [1][NL,QK] = [QL,QH] = [QN,BC] = [QN,QS] = [LN,LM] = [KS,KM] = [PN,PM] = [AB,MK] = [NL,AH]
       because eqang [0][PN,PM] = [KS,KM] = [LN,LM] = [QN,QS] = [QN,BC] = [QL,QH] = [AB,MK] = [NL,QK] and eqang [0][NL,AH] = [AB,MK].
eqang [0][MP,AH] = [QA,QL]
       because $[QL,HQ]=[NP,MP]$ and $[HQ,AB]=[AH,NP]$.
eqang [1][QK,MP] = [SM,KP] = [NL,NP] = [ML,MP] = [KB,KP] = [HQ,MK] = [QL,SK] = [QL,QA] = [AH,MP]
       because eqang [0][QL,SK] = [HQ,MK] = [KB,KP] = [ML,MP] = [NL,NP] = [SM,KP] = [QL,QA] = [QK,MP] and eqang [0][MP,AH] = [QA,QL].
eqang [0][CQ,ML] = [AB,CS]
       because $[NP,CS]=[CQ,HQ]$ and $[HQ,AB]=[ML,NP]$.
eqang [1][AH,CQ] = [SC,SK] = [AK,SL] = [HB,HN] = [SM,HN] = [AK,AC] = [QK,QC] = [CS,AB] = [ML,CQ]
       because eqang [0][QK,QC] = [AK,AC] = [SM,HN] = [HB,HN] = [AK,SL] = [SC,SK] = [CS,AB] = [AH,CQ] and eqang [0][CQ,ML] = [AB,CS].
eqang [0][QL,QK] = [AC,KP]
       because $[HM,KP]=[QL,NP]$ and $[HM,AC]=[QK,NP]$.
eqang [0][MK,AH] = [AB,NL] = [KM,KQ] = [NS,NL] = [LM,LQ] = [NA,NQ] = [MK,ML] = [PK,PS] = [PM,PA] = [AH,QL] = [KP,AC] = [QK,QL]
       because eqang [0][AH,QL] = [PM,PA] = [PK,PS] = [MK,ML] = [NA,NQ] = [LM,LQ] = [QK,QL] = [NS,NL] = [KM,KQ] = [AB,NL] = [MK,AH] and eqang [0][QL,QK] = [AC,KP].
eqang [0][QN,AH] = [MA,MK]
       because $[HM,MK]=[QN,NP]$ and $[HM,AC]=[AH,NP]$.
eqang [0][SM,NL] = [PK,PN] = [LB,LN] = [QK,QN] = [HM,QL] = [MK,SL] = [MK,MA] = [AH,QN]
       because eqang [0][MK,SL] = [HM,QL] = [MK,MA] = [QK,QN] = [LB,LN] = [PK,PN] = [SM,NL] and eqang [0][QN,AH] = [MA,MK].
eqang [0][QN,ML] = [MA,MK]
       because $[HM,MK]=[QN,NP]$ and $[HM,AC]=[ML,NP]$.
eqang [1][AH,QN] = [MK,SL] = [HM,QL] = [QK,QN] = [LB,LN] = [PK,PN] = [SM,NL] = [MK,MA] = [ML,QN]
       because eqang [0][SM,NL] = [PK,PN] = [LB,LN] = [QK,QN] = [HM,QL] = [MK,SL] = [MK,MA] = [AH,QN] and eqang [0][QN,ML] = [MA,MK].
eqang [0][KP,ML] = [AC,QL]
       because $[HM,QL]=[KP,NP]$ and $[HM,AC]=[ML,NP]$.
eqang [0][MS,MP] = [KQ,KP] = [NQ,NP] = [MH,MK] = [LQ,LS] = [QL,AC] = [ML,KP]
       because eqang [0][QL,AC] = [LQ,LS] = [MH,MK] = [NQ,NP] = [KQ,KP] = [MS,MP] and eqang [0][KP,ML] = [AC,QL].
eqang [0][KP,AH] = [NP,NQ]
       because $[QK,QN]=[KP,NP]$ and $[QK,NP]=[AH,NP]$.
eqang [0][ML,KP] = [QL,AC] = [LQ,LS] = [MH,MK] = [KQ,KP] = [MS,MP] = [NQ,NP] = [AH,KP]
       because eqang [0][MS,MP] = [KQ,KP] = [NQ,NP] = [MH,MK] = [LQ,LS] = [QL,AC] = [ML,KP] and eqang [0][KP,AH] = [NP,NQ].
eqang [0][MP,SK] = [QK,QL]
       because $[QL,HQ]=[NP,MP]$ and $[QK,NP]=[HQ,SK]$.
eqang [0][KP,AC] = [AH,QL] = [PM,PA] = [PK,PS] = [MK,ML] = [NA,NQ] = [LM,LQ] = [NS,NL] = [KM,KQ] = [AB,NL] = [MK,AH] = [QK,QL] = [MP,SK]
       because eqang [0][MK,AH] = [AB,NL] = [KM,KQ] = [NS,NL] = [LM,LQ] = [NA,NQ] = [MK,ML] = [PK,PS] = [PM,PA] = [AH,QL] = [KP,AC] = [QK,QL] and eqang [0][MP,SK] = [QK,QL].
eqang [0][MK,AH] = [SL,QN]
       because $[HM,QN]=[MK,NP]$ and $[HM,SL]=[AH,NP]$.
eqang [1][MP,SK] = [QK,QL] = [AB,NL] = [KM,KQ] = [NS,NL] = [LM,LQ] = [NA,NQ] = [MK,ML] = [PK,PS] = [PM,PA] = [AH,QL] = [KP,AC] = [SL,QN] = [MK,AH]
       because eqang [0][KP,AC] = [AH,QL] = [PM,PA] = [PK,PS] = [MK,ML] = [NA,NQ] = [LM,LQ] = [NS,NL] = [KM,KQ] = [AB,NL] = [MK,AH] = [QK,QL] = [MP,SK] and eqang [0][MK,AH] = [SL,QN].
eqang [0][CQ,HN] = [CS,AK]
       because $[AK,QK]=[SL,CQ]$ and $[CS,SL]=[QK,HN]$.
eqang [0][BM,HN] = [SC,SB]
       because $[BS,QK]=[SL,BM]$ and $[CS,SL]=[QK,HN]$.
eqang [1][HP,CQ] = [AK,AL] = [SC,SB] = [BM,HN]
       because eqang [0][SC,SB] = [AK,AL] = [HP,CQ] and eqang [0][BM,HN] = [SC,SB].
eqang [0][BS,HN] = [CS,BM]
       because $[QK,BM]=[BS,SL]$ and $[CS,SL]=[QK,HN]$.
eqang [1][HP,AK] = [CQ,AL] = [BM,CS] = [HN,BS]
       because eqang [0][HN,BS] = [CQ,AL] = [HP,AK] and eqang [0][BS,HN] = [CS,BM].
eqang [0][NQ,NH] = [CS,MK]
       because $[MK,SL]=[QK,QN]$ and $[CS,SL]=[QK,HN]$.
eqang [0][KP,HN] = [CS,QL]
       because $[QL,SL]=[QK,KP]$ and $[CS,SL]=[QK,HN]$.
eqang [1][AK,HN] = [CS,CQ]
       because $[AH,CQ]=[AK,SL]$ and $[CS,SL]=[AH,HN]$.
eqang [0][MK,HN] = [CS,QN]
       because $[AH,QN]=[MK,SL]$ and $[CS,SL]=[AH,HN]$.
eqang [0][QL,HN] = [CS,KP]
       because $[KP,SL]=[AH,QL]$ and $[CS,SL]=[AH,HN]$.
eqang [0][BM,CQ] = [CS,AL]
       because $[AL,SL]=[BC,BM]$ and $[CS,SL]=[BC,CQ]$.
eqang [1][HP,HN] = [AK,BS] = [CS,AL] = [BM,CQ]
       because eqang [0][CS,AL] = [AK,BS] = [HP,HN] and eqang [0][BM,CQ] = [CS,AL].
eqang [0][MK,CQ] = [CS,NL]
       because $[SL,NL]=[MK,BC]$ and $[CS,SL]=[BC,CQ]$.
eqang [0][QL,QC] = [CS,MP]
       because $[MP,SL]=[BC,QL]$ and $[CS,SL]=[BC,CQ]$.
eqang [0][BS,CQ] = [CS,HP]
       because $[SL,HP]=[BS,BC]$ and $[CS,SL]=[BC,CQ]$.
eqang [0][AL,HN] = [CS,HP] = [BS,CQ]
       because eqang [0][AL,HN] = [CS,HP] and eqang [0][BS,CQ] = [CS,HP].
eqang [0][HN,CQ] = [CS,AK]
       because $[AK,BC]=[SL,HN]$ and $[CS,SL]=[BC,CQ]$.
eqang [0][CS,AK] = [AK,CS] = [CQ,HN]
       because eqang [0][CQ,HN] = [CS,AK] and eqang [0][HN,CQ] = [CS,AK].
eqang [0][HN,CQ] = [CS,AK]
       because $[AK,SL]=[BC,HN]$ and $[CS,SL]=[BC,CQ]$.
eqang [0][NL,CQ] = [CS,MK]
       because $[MK,SL]=[BC,NL]$ and $[CS,SL]=[BC,CQ]$.
eqang [1][NQ,NH] = [CS,MK] = [NL,CQ]
       because eqang [0][NQ,NH] = [CS,MK] and eqang [0][NL,CQ] = [CS,MK].
eqang [0][MP,BC] = [LS,LQ]
       because $[QL,CQ]=[CS,MP]$ and $[CS,SL]=[BC,CQ]$.
eqang [1][AH,KP] = [NQ,NP] = [MS,MP] = [KQ,KP] = [MH,MK] = [QL,AC] = [ML,KP] = [LQ,LS] = [BC,MP]
       because eqang [0][ML,KP] = [QL,AC] = [LQ,LS] = [MH,MK] = [KQ,KP] = [MS,MP] = [NQ,NP] = [AH,KP] and eqang [0][MP,BC] = [LS,LQ].
eqang [0][MP,CQ] = [CS,QL]
       because $[QL,SL]=[BC,MP]$ and $[CS,SL]=[BC,CQ]$.
eqang [1][KP,HN] = [CS,QL] = [MP,CQ]
       because eqang [0][KP,HN] = [CS,QL] and eqang [0][MP,CQ] = [CS,QL].
eqang [1][AK,CQ] = [CS,HN]
       because $[SL,HN]=[AK,SM]$ and $[CS,SL]=[SM,CQ]$.
eqang [0][HN,CQ] = [CS,AK]
       because $[AK,SL]=[SM,HN]$ and $[CS,SL]=[SM,CQ]$.
eqang [0][BM,AK] = [HN,AL]
       because $[AL,QK]=[SK,BM]$ and $[QK,HN]=[AK,SK]$.
eqang [1][BS,CQ] = [CS,HP] = [AL,HN] = [AK,BM]
       because eqang [0][AL,HN] = [CS,HP] = [BS,CQ] and eqang [0][BM,AK] = [HN,AL].
eqang [0][KM,KA] = [NH,NL]
       because $[NL,QK]=[SK,MK]$ and $[QK,HN]=[AK,SK]$.
eqang [0][QL,AK] = [HN,MP]
       because $[QK,MP]=[QL,SK]$ and $[QK,HN]=[AK,SK]$.
eqang [0][NL,AK] = [HN,MK]
       because $[MK,QK]=[SK,NL]$ and $[QK,HN]=[AK,SK]$.
eqang [1][CS,QN] = [MK,HN] = [AK,NL]
       because eqang [0][MK,HN] = [CS,QN] and eqang [0][NL,AK] = [HN,MK].
eqang [0][CQ,HN] = [KQ,KB]
       because $[BC,HN]=[ML,CQ]$ and $[QK,HN]=[ML,HN]$.
eqang [0][AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN]
       because eqang [0][CS,AK] = [AK,CS] = [CQ,HN] and eqang [0][CQ,HN] = [KQ,KB].
eqang [0][CQ,HN] = [QK,QS]
       because $[SM,HN]=[ML,CQ]$ and $[QK,HN]=[ML,HN]$.
eqang [0][PM,PK] = [QS,QK] = [HN,CQ]
       because eqang [0][PM,PK] = [QS,QK] and eqang [0][CQ,HN] = [QK,QS].
eqang [0][KQ,KB] = [CS,AK] = [AK,CS] = [CQ,HN] = [QK,QS] = [PK,PM]
       because eqang [0][AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] and eqang [0][PM,PK] = [QS,QK] = [HN,CQ].
eqang [0][MS,ML] = [HN,CQ]
       because $[CQ,HN]=[QK,SM]$ and $[QK,HN]=[ML,HN]$.
eqang [0][NQ,NL] = [HN,CQ] = [MS,ML]
       because eqang [0][NQ,NL] = [MS,ML] and eqang [0][MS,ML] = [HN,CQ].
eqang [0][PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [ML,MS] = [CQ,HN] = [NL,NQ]
       because eqang [0][KQ,KB] = [CS,AK] = [AK,CS] = [CQ,HN] = [QK,QS] = [PK,PM] and eqang [0][NQ,NL] = [HN,CQ] = [MS,ML].
eqang [0][MP,AK] = [HN,QL]
       because $[QK,QL]=[MP,AB]$ and $[QK,HN]=[AK,AB]$.
eqang [1][CS,KP] = [QL,HN] = [AK,MP]
       because eqang [0][QL,HN] = [CS,KP] and eqang [0][MP,AK] = [HN,QL].
eqang [0][MK,CQ] = [AK,QN]
       because $[SK,QN]=[MK,BC]$ and $[AK,SK]=[BC,CQ]$.
eqang [1][CS,NL] = [AK,QN] = [MK,CQ]
       because eqang [0][MK,CQ] = [CS,NL] and eqang [0][MK,CQ] = [AK,QN].
eqang [0][KP,CQ] = [AK,QL]
       because $[BC,QL]=[KP,SK]$ and $[AK,SK]=[BC,CQ]$.
eqang [1][HN,MP] = [QL,AK] = [CQ,KP]
       because eqang [0][QL,AK] = [HN,MP] and eqang [0][KP,CQ] = [AK,QL].
eqang [0][QL,QC] = [KA,KP]
       because $[BC,KP]=[QL,SK]$ and $[AK,SK]=[BC,CQ]$.
eqang [1][CS,MP] = [KA,KP] = [QL,QC]
       because eqang [0][QL,QC] = [CS,MP] and eqang [0][QL,QC] = [KA,KP].
eqang [0][KM,KA] = [QC,QN]
       because $[AB,QN]=[MK,BC]$ and $[BC,CQ]=[AK,AB]$.
eqang [1][NH,NL] = [QC,QN] = [KM,KA]
       because eqang [0][KM,KA] = [NH,NL] and eqang [0][KM,KA] = [QC,QN].
eqang [1][BM,AL] = [HP,BS]
       because $[BS,ML]=[SL,BM]$ and $[ML,HP]=[AL,SL]$.
eqang [0][BM,HP] = [ML,MS]
       because $[SM,HP]=[AH,BM]$ and $[ML,HP]=[AH,HP]$.
eqang [0][BS,AL] = [AL,BS] = [ML,MS] = [BM,HP]
       because eqang [0][AL,BS] = [BS,AL] = [BM,HP] and eqang [0][BM,HP] = [ML,MS].
eqang [0][NL,NQ] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [PK,PM] = [BM,HP] = [ML,MS] = [AL,BS] = [BS,AL]
       because eqang [0][PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [ML,MS] = [CQ,HN] = [NL,NQ] and eqang [0][BS,AL] = [AL,BS] = [ML,MS] = [BM,HP].
eqang [0][BM,HP] = [LM,LB]
       because $[BC,HP]=[AH,BM]$ and $[ML,HP]=[AH,HP]$.
eqang [0][BS,AL] = [AL,BS] = [ML,MS] = [PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [NL,NQ] = [LM,LB] = [BM,HP]
       because eqang [0][NL,NQ] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [PK,PM] = [BM,HP] = [ML,MS] = [AL,BS] = [BS,AL] and eqang [0][BM,HP] = [LM,LB].
eqang [0][MK,BS] = [HP,NL]
       because $[NL,ML]=[SK,MK]$ and $[ML,HP]=[BS,SK]$.
eqang [0][MK,MB] = [LA,LN]
       because $[SL,NL]=[MK,BC]$ and $[AL,SL]=[BC,BM]$.
eqang [0][QL,BM] = [AL,MP]
       because $[MP,SL]=[BC,QL]$ and $[AL,SL]=[BC,BM]$.
eqang [0][NL,BM] = [AL,MK]
       because $[MK,SL]=[BC,NL]$ and $[AL,SL]=[BC,BM]$.
eqang [1][QN,HP] = [AL,MK] = [NL,BM]
       because eqang [0][QN,HP] = [AL,MK] and eqang [0][NL,BM] = [AL,MK].
eqang [0][MP,MB] = [LA,LQ]
       because $[QL,SL]=[BC,MP]$ and $[AL,SL]=[BC,BM]$.
eqang [1][PK,PH] = [LA,LQ] = [MP,MB]
       because eqang [0][PK,PH] = [LA,LQ] and eqang [0][MP,MB] = [LA,LQ].
eqang [0][MK,MB] = [BS,QN]
       because $[SK,QN]=[MK,BC]$ and $[BS,SK]=[BC,BM]$.
eqang [1][LA,LN] = [BS,QN] = [MK,MB]
       because eqang [0][MK,MB] = [LA,LN] and eqang [0][MK,MB] = [BS,QN].
eqang [0][KP,BM] = [BS,QL]
       because $[BC,QL]=[KP,SK]$ and $[BS,SK]=[BC,BM]$.
eqang [1][PM,PH] = [BS,QL] = [KP,BM]
       because eqang [0][PM,PH] = [BS,QL] and eqang [0][KP,BM] = [BS,QL].
eqang [0][QL,BM] = [BS,KP]
       because $[BC,KP]=[QL,SK]$ and $[BS,SK]=[BC,BM]$.
eqang [1][AL,MP] = [BS,KP] = [QL,BM]
       because eqang [0][QL,BM] = [AL,MP] and eqang [0][QL,BM] = [BS,KP].
eqang [0][NA,NS] = [NQ,NL]
       because $[AB,NL]=[AC,QN]$ and $[AB,QN]=[SK,QN]$.
eqang [0][BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [PK,PM] = [ML,MS] = [AL,BS] = [BS,AL] = [NL,NQ] = [NS,NA]
       because eqang [0][BS,AL] = [AL,BS] = [ML,MS] = [PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [NL,NQ] = [LM,LB] = [BM,HP] and eqang [0][NA,NS] = [NQ,NL].
eqang [0][SL,SK] = [NQ,NL]
       because $[AB,NL]=[SL,QN]$ and $[AB,QN]=[SK,QN]$.
eqang [0][NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [NL,NQ] = [SK,SL]
       because eqang [0][BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [PK,PM] = [ML,MS] = [AL,BS] = [BS,AL] = [NL,NQ] = [NS,NA] and eqang [0][SL,SK] = [NQ,NL].
eqang [0][NL,NQ] = [AB,AC]
       because $[SK,AC]=[NL,QN]$ and $[AB,QN]=[SK,QN]$.
eqang [0][SK,SL] = [BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [PK,PM] = [ML,MS] = [AL,BS] = [BS,AL] = [NS,NA] = [AB,AC] = [NL,NQ]
       because eqang [0][NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [NL,NQ] = [SK,SL] and eqang [0][NL,NQ] = [AB,AC].
eqang [0][NL,NQ] = [PA,PS]
       because $[SK,SL]=[NL,QN]$ and $[AB,QN]=[SK,QN]$.
eqang [0][AB,AC] = [NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [SK,SL] = [PA,PS] = [NL,NQ]
       because eqang [0][SK,SL] = [BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [PK,PM] = [ML,MS] = [AL,BS] = [BS,AL] = [NS,NA] = [AB,AC] = [NL,NQ] and eqang [0][NL,NQ] = [PA,PS].
eqang [0][PK,PM] = [NQ,NL]
       because $[AB,NL]=[KP,SL]$ and $[AB,QN]=[MP,SL]$.
eqang [0][NL,NQ] = [PA,PS] = [SK,SL] = [BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [ML,MS] = [AL,BS] = [BS,AL] = [NS,NA] = [AB,AC] = [NQ,NL] = [PK,PM]
       because eqang [0][AB,AC] = [NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [PK,PM] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [SK,SL] = [PA,PS] = [NL,NQ] and eqang [0][PK,PM] = [NQ,NL].
eqang [0][BS,MK] = [QN,BM]
       because $[AB,BM]=[BS,BC]$ and $[AB,QN]=[MK,BC]$.
eqang [1][HP,NL] = [BM,QN] = [MK,BS]
       because eqang [0][MK,BS] = [HP,NL] and eqang [0][BS,MK] = [QN,BM].
eqang [0][QL,MK] = [QN,KP]
       because $[BC,KP]=[QL,AB]$ and $[AB,QN]=[MK,BC]$.
eqang [0][PM,PK] = [NQ,NL]
       because $[AB,NL]=[MP,AB]$ and $[AB,QN]=[KP,AB]$.
eqang [1][NL,KP] = [QN,MP]
       because $[MP,SK]=[AB,NL]$ and $[AB,QN]=[KP,SK]$.
eqang [0][PM,PK] = [NQ,NL]
       because $[SK,NL]=[MP,AB]$ and $[AB,QN]=[KP,SK]$.
eqang [0][PM,PK] = [NQ,NL]
       because $[SK,NL]=[MP,AB]$ and $[SK,QN]=[KP,AB]$.
eqang [0][QN,KP] = [NL,MP]
       because $[MP,AB]=[SL,QN]$ and $[SL,NL]=[KP,AB]$.
eqang [1][QL,MK] = [NL,MP] = [QN,KP]
       because eqang [0][QL,MK] = [QN,KP] and eqang [0][QN,KP] = [NL,MP].
eqang [0][PK,PM] = [SL,SK]
       because $[MP,SK]=[KP,AC]$ and $[MP,SL]=[MP,AC]$.
eqang [0][NQ,NL] = [AB,AC] = [NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [SK,SL] = [PA,PS] = [NL,NQ] = [SL,SK] = [PK,PM]
       because eqang [0][NL,NQ] = [PA,PS] = [SK,SL] = [BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [ML,MS] = [AL,BS] = [BS,AL] = [NS,NA] = [AB,AC] = [NQ,NL] = [PK,PM] and eqang [0][PK,PM] = [SL,SK].
eqang [0][AB,AC] = [PM,PK]
       because $[KP,SL]=[MP,AB]$ and $[MP,SL]=[MP,AC]$.
eqang [0][PK,PM] = [SL,SK] = [NL,NQ] = [PA,PS] = [SK,SL] = [BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [ML,MS] = [AL,BS] = [BS,AL] = [NS,NA] = [NQ,NL] = [PM,PK] = [AB,AC]
       because eqang [0][NQ,NL] = [AB,AC] = [NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [SK,SL] = [PA,PS] = [NL,NQ] = [SL,SK] = [PK,PM] and eqang [0][AB,AC] = [PM,PK].
eqang [0][HA,HB] = [PM,PK]
       because $[MK,KP]=[AH,SL]$ and $[MP,SL]=[MK,BC]$.
eqang [0][AB,AC] = [NQ,NL] = [NS,NA] = [BS,AL] = [AL,BS] = [ML,MS] = [QK,QS] = [AK,CS] = [CS,AK] = [KQ,KB] = [CQ,HN] = [LM,LB] = [BM,HP] = [SK,SL] = [PA,PS] = [NL,NQ] = [SL,SK] = [PK,PM] = [PM,PK] = [HA,HB]
       because eqang [0][PK,PM] = [SL,SK] = [NL,NQ] = [PA,PS] = [SK,SL] = [BM,HP] = [LM,LB] = [CQ,HN] = [KQ,KB] = [CS,AK] = [AK,CS] = [QK,QS] = [ML,MS] = [AL,BS] = [BS,AL] = [NS,NA] = [NQ,NL] = [PM,PK] = [AB,AC] and eqang [0][HA,HB] = [PM,PK].
eqang [1][QN,QH] = [NL,HM]
       because $[HM,QK]=[QL,QN]$ and $[QL,NL]=[HQ,QK]$.
eqang [1][MH,MP] = [HQ,KP]
       because $[MK,KP]=[HM,QK]$ and $[HQ,QK]=[MK,MP]$.
eqang [0][PA,PC] = [NB,NA]
       because circle[$BCNP$].
eqang [0][NB,NP] = [CB,CP]
       because circle[$BPCN$].
eqang [0][PC,PN] = [BC,BN]
       because circle[$CNBP$].
eqang [0][CA,CP] = [BN,BA]
       because circle[$NPBC$].
eqang [0][NB,NS] = [BN,BA] = [CA,CP]
       because eqang [0][BN,BA] = [NB,NS] and eqang [0][CA,CP] = [BN,BA].
eqang [1][PC,PS] = [CP,CA] = [BA,BN] = [NS,NB]
       because eqang [0][CP,CA] = [PC,PS] and eqang [0][NB,NS] = [BN,BA] = [CA,CP].
eqang [1][MP,BN] = [CP,QN]
       because $[AB,QN]=[MP,AC]$ and $[AB,CP]=[BN,AC]$.
eqang [1][KP,BN] = [CP,NL]
       because $[AC,NL]=[KP,AB]$ and $[AB,CP]=[BN,AC]$.
eqang [0][NP,NB] = [CP,SM]
       because $[SM,AC]=[AB,NP]$ and $[AB,CP]=[BN,AC]$.
eqang [1][CB,CP] = [SM,CP] = [NB,NP]
       because eqang [0][NB,NP] = [CB,CP] and eqang [0][NP,NB] = [CP,SM].
eqang [0][SM,BN] = [PC,PN]
       because $[BC,NP]=[SM,NP]$ and $[CP,NP]=[BC,BN]$.
eqang [1][BC,BN] = [PC,PN] = [SM,BN]
       because eqang [0][PC,PN] = [BC,BN] and eqang [0][SM,BN] = [PC,PN].
eqang [0][SL,BN] = [CP,SK]
       because $[SK,NP]=[BC,SL]$ and $[CP,NP]=[BC,BN]$.
eqang [0][SL,BN] = [PC,PA]
       because $[AB,NP]=[BC,SL]$ and $[CP,NP]=[BC,BN]$.
eqang [0][NB,NA] = [PA,PC] = [BN,SL]
       because eqang [0][PA,PC] = [NB,NA] and eqang [0][SL,BN] = [PC,PA].
eqang [1][CP,SK] = [SL,BN] = [PC,PA] = [NA,NB]
       because eqang [0][SL,BN] = [CP,SK] and eqang [0][NB,NA] = [PA,PC] = [BN,SL].
eqang [1][NQ,NB] = [PC,PM]
       because $[MP,AB]=[SL,QN]$ and $[CP,SL]=[AB,BN]$.
eqang [1][PK,PC] = [NB,NL]
       because $[NL,MP]=[QN,KP]$ and $[MP,BN]=[CP,QN]$.


The configuration contains the following congruent segments
con-seg[0]:AS = HS 
      because midpoint[$S,AH$].
con-seg[0]:AM = CM 
       because [0]:AS*MC = SH*AM .
con-seg[0]:AQ = BQ 
       because [0]:AS*QB = SH*AQ .
con-seg[0]:HK = BK 
       because [0]:HS*KB = SA*HK .
con-seg[0]:SQ = HK 
       because [0]:SQ*HB = HB*HK .
con-seg[1]:SQ = HK = BK 
       because con-seg[0]:HK = BK  and con-seg[0]:SQ = HK .
con-seg[0]:AQ = SK 
       because [0]:AQ*AB = AB*SK .
con-seg[0]:AQ = SK = BQ 
       because con-seg[0]:AQ = BQ  and con-seg[0]:AQ = SK .
con-seg[0]:HL = CL 
       because [0]:HS*LC = SA*HL .
con-seg[0]:SM = HL 
       because [0]:SM*HC = HC*HL .
con-seg[1]:SM = HL = CL 
       because con-seg[0]:HL = CL  and con-seg[0]:SM = HL .
con-seg[0]:AM = SL 
       because [0]:AM*AC = AC*SL .
con-seg[0]:AM = SL = CM 
       because con-seg[0]:AM = CM  and con-seg[0]:AM = SL .
con-seg[1]:KL = MQ 
       because [0]:SK*QM = LK*QA .
con-seg[1]:AN = SP 
       because [0]:MA*SP = AN*MA .
con-seg[1]:SN = AP 
       because [0]:SN*QA = QA*AP .
con-seg[0]:QK = ML 
       because $[LQK] con [QLM]$.
con-seg[0]:AS = NP 
       because circle[$ANSP$] and para[$AN,SP$].
con-seg[0]:AS = NP = HS 
       because con-seg[0]:AS = HS  and con-seg[0]:AS = NP .
con-seg[0]:AM = CM = HM 
       because cir [1]circle[(M)ACH].
con-seg[1]:AM = CM = HM = SL 
       because con-seg[0]:AM = SL = CM  and con-seg[0]:AM = CM = HM .
con-seg[0]:AS = QK 
       because [0]:AS*AH = AH*QK .
con-seg[0]:AS = QK = ML 
       because con-seg[0]:QK = ML  and con-seg[0]:AS = QK .
con-seg[1]:AS = QK = ML = NP = HS 
       because con-seg[0]:AS = NP = HS  and con-seg[0]:AS = QK = ML .
con-seg[0]:AQ = BQ = HQ 
       because cir [1]circle[(Q)ABH].
con-seg[1]:AQ = BQ = HQ = SK 
       because con-seg[0]:AQ = SK = BQ  and con-seg[0]:AQ = BQ = HQ .
con-seg[1]:MK = QL 
       because circle[$MQKL$] and para[$MQ,KL$].
con-seg[1]:MP = NL 
       because circle[$MNLP$] and para[$MN,LP$].
con-seg[1]:QN = KP 
       because circle[$QPKN$] and para[$QP,KN$].

The configuration contains the following similar triangles
sim-tri[0]:[1,LKQ] [1,QML] 
       because $[QL,LK]=[LQ,QM]$ and $[LK,KQ]=[QM,ML]$.
sim-tri[0]:[1,ANP] [1,SPN] 
       because $[NP,PA]=[PN,NS]$ and $[PN,NA]=[NP,PS]$.
sim-tri[0]:[1,SCA] [1,KAB] 
       because $[CS,SA]=[AK,KB]$ and $[SC,CA]=[KA,AB]$.
sim-tri[0]:[-1,CHS] [-1,AHK] 
       because $[CS,SH]=[AK,KH]$ and $[SC,CH]=[KA,AH]$.
sim-tri[0]:[1,SCA] [-1,NHA] 
       because $[CS,SA]=[AN,NH]$ and $[SC,CA]=[AH,HN]$.
sim-tri[0]:[-1,CAS] [1,HAN] [-1,ABK] 
       because sim-tri[0]:[1,SCA] [1,KAB]  and sim-tri[0]:[1,SCA] [-1,NHA] .
sim-tri[1]:[1,KAC] [-1,NHC] 
       because $[AK,KC]=[CN,NH]$ and $[KA,AC]=[CH,HN]$.
sim-tri[0]:[1,SBA] [1,LAC] 
       because $[BS,SA]=[AL,LC]$ and $[SB,BA]=[LA,AC]$.
sim-tri[0]:[-1,SHB] [-1,LHA] 
       because $[BS,SH]=[AL,LH]$ and $[SB,BH]=[LA,AH]$.
sim-tri[0]:[1,SBA] [-1,PHA] 
       because $[BS,SA]=[AP,PH]$ and $[SB,BA]=[AH,HP]$.
sim-tri[0]:[-1,BAS] [1,HAP] [-1,ACL] 
       because sim-tri[0]:[1,SBA] [1,LAC]  and sim-tri[0]:[1,SBA] [-1,PHA] .
sim-tri[1]:[1,LAB] [-1,PHB] 
       because $[AL,LB]=[BP,PH]$ and $[LA,AB]=[BH,HP]$.
sim-tri[1]:[1,HKN] [-1,SKC] 
       because $[KH,HN]=[CS,SK]$ and $[HK,KN]=[CK,KS]$.
sim-tri[0]:[1,KAN] [1,CSN] 
       because $[KA,AN]=[CS,SN]$ and $[AK,KN]=[SC,CN]$.
sim-tri[0]:[1,LAP] [1,BSP] 
       because $[LA,AP]=[BS,SP]$ and $[AL,LP]=[SB,BP]$.
sim-tri[1]:[1,SBL] [-1,HPL] 
       because $[BS,SL]=[LH,HP]$ and $[SB,BL]=[LP,PH]$.
sim-tri[0]:[1,HQM] [-1,ABC] 
       because $[QH,HM]=[CA,AB]$ and $[HQ,QM]=[CB,BA]$.
sim-tri[0]:[1,HQM] [1,ANP] 
       because $[QH,HM]=[NA,AP]$ and $[HQ,QM]=[AN,NP]$.
sim-tri[0]:[1,HQM] [1,ANP] [1,SPN] 
       because sim-tri[0]:[1,ANP] [1,SPN]  and sim-tri[0]:[1,HQM] [1,ANP] .
sim-tri[0]:[-1,MQH] [-1,PNA] [-1,NPS] [1,CBA] 
       because sim-tri[0]:[1,HQM] [-1,ABC]  and sim-tri[0]:[1,HQM] [1,ANP] [1,SPN] .
sim-tri[0]:[1,NSH] [-1,CLS] 
       because $[SN,NH]=[SC,CL]$ and $[NS,SH]=[SL,LC]$.
sim-tri[0]:[1,NSH] [-1,SMC] 
       because $[SN,NH]=[CS,SM]$ and $[NS,SH]=[CM,MS]$.
sim-tri[0]:[1,NSH] [-1,SMC] [-1,CLS] 
       because sim-tri[0]:[1,NSH] [-1,CLS]  and sim-tri[0]:[1,NSH] [-1,SMC] .
sim-tri[0]:[1,NSH] [-1,KQA] 
       because $[SN,NH]=[AK,KQ]$ and $[NS,SH]=[AQ,QK]$.
sim-tri[0]:[1,NSH] [-1,KQA] [-1,SMC] [-1,CLS] 
       because sim-tri[0]:[1,NSH] [-1,SMC] [-1,CLS]  and sim-tri[0]:[1,NSH] [-1,KQA] .
sim-tri[0]:[1,NSH] [-1,ASK] 
       because $[SN,NH]=[KA,AS]$ and $[NS,SH]=[KS,SA]$.
sim-tri[0]:[-1,HNS] [1,KAS] [1,AKQ] [1,CSM] [1,SCL] 
       because sim-tri[0]:[1,NSH] [-1,KQA] [-1,SMC] [-1,CLS]  and sim-tri[0]:[1,NSH] [-1,ASK] .
sim-tri[0]:[-1,KLM] [-1,MQK] 
       because $[KM,ML]=[MK,KQ]$ and $[MK,KL]=[KM,MQ]$.
sim-tri[0]:[1,PSH] [-1,SQB] 
       because $[SP,PH]=[BS,SQ]$ and $[PS,SH]=[BQ,QS]$.
sim-tri[0]:[1,PSH] [-1,BKS] 
       because $[SP,PH]=[SB,BK]$ and $[PS,SH]=[SK,KB]$.
sim-tri[0]:[1,PSH] [-1,BKS] [-1,SQB] 
       because sim-tri[0]:[1,PSH] [-1,SQB]  and sim-tri[0]:[1,PSH] [-1,BKS] .
sim-tri[0]:[1,PSH] [-1,ASL] 
       because $[SP,PH]=[LA,AS]$ and $[PS,SH]=[LS,SA]$.
sim-tri[0]:[1,PSH] [-1,ASL] [-1,BKS] [-1,SQB] 
       because sim-tri[0]:[1,PSH] [-1,BKS] [-1,SQB]  and sim-tri[0]:[1,PSH] [-1,ASL] .
sim-tri[0]:[1,PSH] [-1,LMA] 
       because $[SP,PH]=[AL,LM]$ and $[PS,SH]=[AM,ML]$.
sim-tri[0]:[-1,HPS] [1,ALM] [1,LAS] [1,SBK] [1,BSQ] 
       because sim-tri[0]:[1,PSH] [-1,ASL] [-1,BKS] [-1,SQB]  and sim-tri[0]:[1,PSH] [-1,LMA] .
sim-tri[1]:[1,QHB] [1,MHA] 
       because $[HQ,QB]=[HM,MA]$ and $[QH,HB]=[MH,HA]$.
sim-tri[1]:[1,QHA] [1,MHC] 
       because $[HQ,QA]=[HM,MC]$ and $[QH,HA]=[MH,HC]$.
sim-tri[0]:[1,SCL] [-1,QCM] 
       because $[CS,SL]=[MQ,QC]$ and $[SC,CL]=[MC,CQ]$.
sim-tri[1]:[1,SCL] [-1,QCM] [-1,HNS] [1,KAS] [1,AKQ] [1,CSM] 
       because sim-tri[0]:[-1,HNS] [1,KAS] [1,AKQ] [1,CSM] [1,SCL]  and sim-tri[0]:[1,SCL] [-1,QCM] .
sim-tri[0]:[1,HAN] [1,CBQ] 
       because $[AH,HN]=[BC,CQ]$ and $[HA,AN]=[CB,BQ]$.
sim-tri[1]:[1,HAN] [1,CBQ] [-1,CAS] [-1,ABK] 
       because sim-tri[0]:[-1,CAS] [1,HAN] [-1,ABK]  and sim-tri[0]:[1,HAN] [1,CBQ] .
sim-tri[0]:[1,KAN] [-1,CQK] 
       because $[AK,KN]=[KC,CQ]$ and $[KA,AN]=[KQ,QC]$.
sim-tri[1]:[1,KAN] [-1,CQK] [1,CSN] 
       because sim-tri[0]:[1,KAN] [1,CSN]  and sim-tri[0]:[1,KAN] [-1,CQK] .
sim-tri[1]:[1,CLQ] [-1,APK] 
       because $[LC,CQ]=[KA,AP]$ and $[CL,LQ]=[KP,PA]$.
sim-tri[0]:[1,LAP] [-1,BML] 
       because $[AL,LP]=[LB,BM]$ and $[LA,AP]=[LM,MB]$.
sim-tri[1]:[1,LAP] [-1,BML] [1,BSP] 
       because sim-tri[0]:[1,LAP] [1,BSP]  and sim-tri[0]:[1,LAP] [-1,BML] .
sim-tri[0]:[1,LAS] [-1,MBQ] 
       because $[AL,LS]=[QM,MB]$ and $[LA,AS]=[QB,BM]$.
sim-tri[1]:[1,LAS] [-1,MBQ] [-1,HPS] [1,ALM] [1,SBK] [1,BSQ] 
       because sim-tri[0]:[-1,HPS] [1,ALM] [1,LAS] [1,SBK] [1,BSQ]  and sim-tri[0]:[1,LAS] [-1,MBQ] .
sim-tri[0]:[1,HAP] [1,BCM] 
       because $[AH,HP]=[CB,BM]$ and $[HA,AP]=[BC,CM]$.
sim-tri[1]:[1,HAP] [1,BCM] [-1,BAS] [-1,ACL] 
       because sim-tri[0]:[-1,BAS] [1,HAP] [-1,ACL]  and sim-tri[0]:[1,HAP] [1,BCM] .
sim-tri[1]:[1,BKM] [-1,ANL] 
       because $[KB,BM]=[LA,AN]$ and $[BK,KM]=[LN,NA]$.
sim-tri[0]:[1,QAN] [1,LSN] 
       because $[AQ,QN]=[SL,LN]$ and $[QA,AN]=[LS,SN]$.
sim-tri[0]:[1,QPN] [-1,PSM] 
       because $[PQ,QN]=[MP,PS]$ and $[QP,PN]=[MS,SP]$.
sim-tri[0]:[1,QAN] [-1,MAP] 
       because $[AQ,QN]=[PM,MA]$ and $[QA,AN]=[PA,AM]$.
sim-tri[0]:[1,QAN] [-1,MAP] [1,LSN] 
       because sim-tri[0]:[1,QAN] [1,LSN]  and sim-tri[0]:[1,QAN] [-1,MAP] .
sim-tri[0]:[1,QAN] [1,QML] 
       because $[AQ,QN]=[MQ,QL]$ and $[QA,AN]=[QM,ML]$.
sim-tri[0]:[1,QAN] [1,QML] [1,LKQ] 
       because sim-tri[0]:[1,LKQ] [1,QML]  and sim-tri[0]:[1,QAN] [1,QML] .
sim-tri[0]:[1,QAN] [1,QML] [1,LKQ] [-1,MAP] [1,LSN] 
       because sim-tri[0]:[1,QAN] [-1,MAP] [1,LSN]  and sim-tri[0]:[1,QAN] [1,QML] [1,LKQ] .
sim-tri[0]:[1,QPN] [1,QSL] 
       because $[PQ,QN]=[SQ,QL]$ and $[QP,PN]=[QS,SL]$.
sim-tri[0]:[1,QPN] [1,QSL] [-1,PSM] 
       because sim-tri[0]:[1,QPN] [-1,PSM]  and sim-tri[0]:[1,QPN] [1,QSL] .
sim-tri[0]:[1,QAN] [-1,KLM] 
       because $[AQ,QN]=[MK,KL]$ and $[QA,AN]=[ML,LK]$.
sim-tri[0]:[1,QAN] [-1,KLM] [-1,MQK] 
       because sim-tri[0]:[-1,KLM] [-1,MQK]  and sim-tri[0]:[1,QAN] [-1,KLM] .
sim-tri[0]:[1,QAN] [-1,KLM] [-1,MQK] [1,QML] [1,LKQ] [-1,MAP] [1,LSN] 
       because sim-tri[0]:[1,QAN] [1,QML] [1,LKQ] [-1,MAP] [1,LSN]  and sim-tri[0]:[1,QAN] [-1,KLM] [-1,MQK] .
sim-tri[0]:[1,QPN] [-1,KHM] 
       because $[PQ,QN]=[MK,KH]$ and $[QP,PN]=[MH,HK]$.
sim-tri[0]:[1,QPN] [-1,KHM] [1,QSL] [-1,PSM] 
       because sim-tri[0]:[1,QPN] [1,QSL] [-1,PSM]  and sim-tri[0]:[1,QPN] [-1,KHM] .
sim-tri[0]:[1,QPN] [-1,PQK] 
       because $[PQ,QN]=[KP,PQ]$ and $[QP,PN]=[KQ,QP]$.
sim-tri[1]:[1,QPN] [-1,PQK] [-1,KHM] [1,QSL] [-1,PSM] 
       because sim-tri[0]:[1,QPN] [-1,KHM] [1,QSL] [-1,PSM]  and sim-tri[0]:[1,QPN] [-1,PQK] .
sim-tri[0]:[1,QAN] [-1,KSP] 
       because $[AQ,QN]=[PK,KS]$ and $[QA,AN]=[PS,SK]$.
sim-tri[1]:[1,QAN] [-1,KSP] [-1,KLM] [-1,MQK] [1,QML] [1,LKQ] [-1,MAP] [1,LSN] 
       because sim-tri[0]:[1,QAN] [-1,KLM] [-1,MQK] [1,QML] [1,LKQ] [-1,MAP] [1,LSN]  and sim-tri[0]:[1,QAN] [-1,KSP] .
sim-tri[0]:[1,NSQ] [-1,MNP] 
       because $[SN,NQ]=[PM,MN]$ and $[NS,SQ]=[PN,NM]$.
sim-tri[0]:[1,NSQ] [1,NML] 
       because $[SN,NQ]=[MN,NL]$ and $[NS,SQ]=[NM,ML]$.
sim-tri[0]:[1,NSQ] [1,NML] [-1,MNP] 
       because sim-tri[0]:[1,NSQ] [-1,MNP]  and sim-tri[0]:[1,NSQ] [1,NML] .
sim-tri[0]:[1,NKQ] [1,NCL] 
       because $[KN,NQ]=[CN,NL]$ and $[NK,KQ]=[NC,CL]$.
sim-tri[0]:[1,NSQ] [1,LHQ] 
       because $[SN,NQ]=[HL,LQ]$ and $[NS,SQ]=[LH,HQ]$.
sim-tri[0]:[1,NSQ] [1,LHQ] [1,NML] [-1,MNP] 
       because sim-tri[0]:[1,NSQ] [1,NML] [-1,MNP]  and sim-tri[0]:[1,NSQ] [1,LHQ] .
sim-tri[0]:[1,NKQ] [-1,KCM] 
       because $[KN,NQ]=[MK,KC]$ and $[NK,KQ]=[MC,CK]$.
sim-tri[0]:[1,NKQ] [-1,KCM] [1,NCL] 
       because sim-tri[0]:[1,NKQ] [1,NCL]  and sim-tri[0]:[1,NKQ] [-1,KCM] .
sim-tri[0]:[1,NSQ] [-1,MSK] 
       because $[SN,NQ]=[KM,MS]$ and $[NS,SQ]=[KS,SM]$.
sim-tri[1]:[1,NSQ] [-1,MSK] [1,LHQ] [1,NML] [-1,MNP] 
       because sim-tri[0]:[1,NSQ] [1,LHQ] [1,NML] [-1,MNP]  and sim-tri[0]:[1,NSQ] [-1,MSK] .
sim-tri[0]:[1,NKQ] [-1,KNP] 
       because $[KN,NQ]=[PK,KN]$ and $[NK,KQ]=[PN,NK]$.
sim-tri[1]:[1,NKQ] [-1,KNP] [-1,KCM] [1,NCL] 
       because sim-tri[0]:[1,NKQ] [-1,KCM] [1,NCL]  and sim-tri[0]:[1,NKQ] [-1,KNP] .
sim-tri[0]:[1,LPN] [-1,PLM] 
       because $[PL,LN]=[MP,PL]$ and $[LP,PN]=[ML,LP]$.
sim-tri[0]:[1,LPN] [1,LBQ] 
       because $[PL,LN]=[BL,LQ]$ and $[LP,PN]=[LB,BQ]$.
sim-tri[0]:[1,LPN] [1,LBQ] [-1,PLM] 
       because sim-tri[0]:[1,LPN] [-1,PLM]  and sim-tri[0]:[1,LPN] [1,LBQ] .
sim-tri[0]:[1,LPN] [-1,PBK] 
       because $[PL,LN]=[KP,PB]$ and $[LP,PN]=[KB,BP]$.
sim-tri[1]:[1,LPN] [-1,PBK] [1,LBQ] [-1,PLM] 
       because sim-tri[0]:[1,LPN] [1,LBQ] [-1,PLM]  and sim-tri[0]:[1,LPN] [-1,PBK] .
sim-tri[0]:[1,LQN] [1,MQA] 
       because $[QL,LN]=[QM,MA]$ and $[LQ,QN]=[MQ,QA]$.
sim-tri[0]:[1,LQN] [-1,MAS] 
       because $[QL,LN]=[SM,MA]$ and $[LQ,QN]=[SA,AM]$.
sim-tri[0]:[1,LQN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,MAS] .
sim-tri[0]:[1,LQN] [1,MSN] 
       because $[QL,LN]=[SM,MN]$ and $[LQ,QN]=[MS,SN]$.
sim-tri[0]:[1,LQN] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,MSN] .
sim-tri[0]:[1,LQN] [1,CBA] 
       because $[QL,LN]=[BC,CA]$ and $[LQ,QN]=[CB,BA]$.
sim-tri[0]:[1,LQN] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] 
       because sim-tri[0]:[-1,MQH] [-1,PNA] [-1,NPS] [1,CBA]  and sim-tri[0]:[1,LQN] [1,CBA] .
sim-tri[0]:[1,LQN] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] .
sim-tri[0]:[1,LQN] [-1,CAH] 
       because $[QL,LN]=[HC,CA]$ and $[LQ,QN]=[HA,AC]$.
sim-tri[0]:[1,LQN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,CAH] .
sim-tri[0]:[1,LQN] [1,CKN] 
       because $[QL,LN]=[KC,CN]$ and $[LQ,QN]=[CK,KN]$.
sim-tri[0]:[1,LQN] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,CKN] .
sim-tri[0]:[1,LQN] [-1,CML] 
       because $[QL,LN]=[LC,CM]$ and $[LQ,QN]=[LM,MC]$.
sim-tri[0]:[1,LQN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,CML] .
sim-tri[0]:[1,LQN] [1,SAN] 
       because $[QL,LN]=[AS,SN]$ and $[LQ,QN]=[SA,AN]$.
sim-tri[0]:[1,LQN] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,SAN] .
sim-tri[0]:[1,LQN] [-1,SKH] 
       because $[QL,LN]=[HS,SK]$ and $[LQ,QN]=[HK,KS]$.
sim-tri[0]:[1,LQN] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,SKH] .
sim-tri[0]:[1,LQN] [1,MHS] 
       because $[QL,LN]=[HM,MS]$ and $[LQ,QN]=[MH,HS]$.
sim-tri[0]:[1,LQN] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,MHS] .
sim-tri[0]:[1,LQN] [1,HML] 
       because $[QL,LN]=[MH,HL]$ and $[LQ,QN]=[HM,ML]$.
sim-tri[0]:[1,LQN] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,HML] .
sim-tri[0]:[1,LQN] [-1,KSQ] 
       because $[QL,LN]=[QK,KS]$ and $[LQ,QN]=[QS,SK]$.
sim-tri[0]:[1,LQN] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,KSQ] .
sim-tri[0]:[1,LQN] [-1,ABH] 
       because $[QL,LN]=[HA,AB]$ and $[LQ,QN]=[HB,BA]$.
sim-tri[0]:[1,LQN] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,ABH] .
sim-tri[0]:[1,LQN] [-1,AQS] 
       because $[QL,LN]=[SA,AQ]$ and $[LQ,QN]=[SQ,QA]$.
sim-tri[0]:[1,LQN] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,AQS] .
sim-tri[0]:[1,LQN] [1,ASP] 
       because $[QL,LN]=[SA,AP]$ and $[LQ,QN]=[AS,SP]$.
sim-tri[0]:[1,LQN] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,ASP] .
sim-tri[0]:[1,LQN] [1,SQP] 
       because $[QL,LN]=[QS,SP]$ and $[LQ,QN]=[SQ,QP]$.
sim-tri[0]:[1,LQN] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,SQP] .
sim-tri[0]:[1,LQN] [-1,SLM] 
       because $[QL,LN]=[MS,SL]$ and $[LQ,QN]=[ML,LS]$.
sim-tri[0]:[1,LQN] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,SLM] .
sim-tri[0]:[1,LQN] [1,LBP] 
       because $[QL,LN]=[BL,LP]$ and $[LQ,QN]=[LB,BP]$.
sim-tri[0]:[1,LQN] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,LBP] .
sim-tri[0]:[1,LQN] [-1,LSH] 
       because $[QL,LN]=[HL,LS]$ and $[LQ,QN]=[HS,SL]$.
sim-tri[0]:[1,LQN] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,LSH] .
sim-tri[0]:[1,LQN] [1,LKS] 
       because $[QL,LN]=[KL,LS]$ and $[LQ,QN]=[LK,KS]$.
sim-tri[0]:[1,LQN] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,LKS] .
sim-tri[0]:[1,LQN] [1,QHK] 
       because $[QL,LN]=[HQ,QK]$ and $[LQ,QN]=[QH,HK]$.
sim-tri[0]:[1,LQN] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,QHK] .
sim-tri[0]:[1,LQN] [1,HQS] 
       because $[QL,LN]=[QH,HS]$ and $[LQ,QN]=[HQ,QS]$.
sim-tri[0]:[1,LQN] [1,HQS] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,HQS] .
sim-tri[0]:[1,LQN] [1,MKP] 
       because $[QL,LN]=[KM,MP]$ and $[LQ,QN]=[MK,KP]$.
sim-tri[0]:[1,LQN] [1,MKP] [1,HQS] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,HQS] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [1,MKP] .
sim-tri[0]:[1,LQN] [-1,QBK] 
       because $[QL,LN]=[KQ,QB]$ and $[LQ,QN]=[KB,BQ]$.
sim-tri[1]:[1,LQN] [-1,QBK] [1,MKP] [1,HQS] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA] 
       because sim-tri[0]:[1,LQN] [1,MKP] [1,HQS] [1,QHK] [1,LKS] [-1,LSH] [1,LBP] [-1,SLM] [1,SQP] [1,ASP] [-1,AQS] [-1,ABH] [-1,KSQ] [1,HML] [1,MHS] [-1,SKH] [1,SAN] [-1,CML] [1,CKN] [-1,CAH] [1,CBA] [-1,MQH] [-1,PNA] [-1,NPS] [1,MSN] [-1,MAS] [1,MQA]  and sim-tri[0]:[1,LQN] [-1,QBK] .
sim-tri[0]:[1,CAQ] [-1,AHK] 
       because $[AC,CQ]=[KA,AH]$ and $[CA,AQ]=[KH,HA]$.
sim-tri[1]:[1,CAQ] [-1,AHK] [-1,CHS] 
       because sim-tri[0]:[-1,CHS] [-1,AHK]  and sim-tri[0]:[1,CAQ] [-1,AHK] .
sim-tri[0]:[1,MAB] [-1,SHB] 
       because $[AM,MB]=[BS,SH]$ and $[MA,AB]=[BH,HS]$.
sim-tri[1]:[1,MAB] [-1,SHB] [-1,LHA] 
       because sim-tri[0]:[-1,SHB] [-1,LHA]  and sim-tri[0]:[1,MAB] [-1,SHB] .
sim-tri[1]:[1,HKP] [-1,AQL] 
       because $[KH,HP]=[LA,AQ]$ and $[HK,KP]=[LQ,QA]$.
sim-tri[1]:[1,HLN] [-1,AMK] 
       because $[LH,HN]=[KA,AM]$ and $[HL,LN]=[KM,MA]$.
sim-tri[1]:[1,PAC] [-1,NAB] 
       because $[AP,PC]=[BN,NA]$ and $[PA,AC]=[BA,AN]$.
sim-tri[1]:[1,PCN] [-1,BNK] 
       because $[CP,PN]=[KB,BN]$ and $[PC,CN]=[KN,NB]$.
sim-tri[1]:[1,PCL] [-1,BNP] 
       because $[CP,PL]=[PB,BN]$ and $[PC,CL]=[PN,NB]$.
sim-tri[1]:[1,CPM] [-1,BNQ] 
       because $[PC,CM]=[QB,BN]$ and $[CP,PM]=[QN,NB]$.

The configuration contains the following congruent triangles
con-tri[0]:[-1,LKQ] [-1,QML] 
       because $KL = MQ$ and $[LQK] sim [QLM]$.
con-tri[0]:[-1,PNA] [-1,NPS] 
       because $AN = SP$ and $[PNA] sim [NPS]$.
con-tri[1]:[1,BKS] [1,SQB] 
       because $SK = BQ$ and $[BKS] sim [SQB]$.
con-tri[1]:[1,ASK] [1,KQA] 
       because $AS = KQ$ and $[ASK] sim [KQA]$.
con-tri[1]:[1,SMC] [1,CLS] 
       because $SM = CL$ and $[SMC] sim [CLS]$.
con-tri[0]:[1,KLM] [1,MQK] 
       because $MK = KM$ and $[MKL] sim [KMQ]$.
con-tri[1]:[1,LMA] [1,ASL] 
       because $LM = AS$ and $[LMA] sim [ASL]$.
con-tri[1]:[1,QAN] [-1,KSP] 
       because $QN = KP$ and $[QAN] sim [KSP]$.
con-tri[1]:[1,QPN] [-1,PQK] 
       because $QP = PQ$ and $[QPN] sim [PQK]$.
con-tri[1]:[1,KHM] [-1,QSL] 
       because $KH = QS$ and $[KHM] sim [QSL]$.
con-tri[0]:[1,KLM] [-1,QML] 
       because $KL = QM$ and $[KLM] sim [QML]$.
con-tri[0]:[1,KLM] [-1,QML] [-1,LKQ] 
       because con-tri[0]:[-1,LKQ] [-1,QML]  and con-tri[0]:[1,KLM] [-1,QML] .
con-tri[1]:[1,KLM] [-1,QML] [-1,LKQ] [1,MQK] 
       because con-tri[0]:[1,KLM] [1,MQK]  and con-tri[0]:[1,KLM] [-1,QML] [-1,LKQ] .
con-tri[1]:[1,MAP] [-1,LSN] 
       because $MA = LS$ and $[MAP] sim [LSN]$.
con-tri[1]:[1,MSK] [-1,LHQ] 
       because $MS = LH$ and $[MSK] sim [LHQ]$.
con-tri[1]:[1,NML] [-1,MNP] 
       because $NM = MN$ and $[NML] sim [MNP]$.
con-tri[1]:[1,NKQ] [-1,KNP] 
       because $NK = KN$ and $[NKQ] sim [KNP]$.
con-tri[1]:[1,LPN] [-1,PLM] 
       because $LP = PL$ and $[LPN] sim [PLM]$.
con-tri[1]:[1,LQN] [1,MKP] 
       because $LQ = MK$ and $[LQN] sim [MKP]$.
con-tri[0]:[1,QBK] [-1,HQS] 
       because $QB = HQ$ and $[QBK] sim [HQS]$.
con-tri[0]:[1,QBK] [1,AQS] 
       because $QB = AQ$ and $[QBK] sim [AQS]$.
con-tri[0]:[1,QBK] [1,AQS] [-1,HQS] 
       because con-tri[0]:[1,QBK] [-1,HQS]  and con-tri[0]:[1,QBK] [1,AQS] .
con-tri[0]:[1,QBK] [1,KSQ] 
       because $QB = KS$ and $[QBK] sim [KSQ]$.
con-tri[0]:[1,QBK] [1,KSQ] [1,AQS] [-1,HQS] 
       because con-tri[0]:[1,QBK] [1,AQS] [-1,HQS]  and con-tri[0]:[1,QBK] [1,KSQ] .
con-tri[0]:[1,QBK] [1,SKH] 
       because $QB = SK$ and $[QBK] sim [SKH]$.
con-tri[0]:[-1,QBK] [-1,SKH] [-1,KSQ] [-1,AQS] [1,HQS] 
       because con-tri[0]:[1,QBK] [1,KSQ] [1,AQS] [-1,HQS]  and con-tri[0]:[1,QBK] [1,SKH] .
con-tri[0]:[1,HQS] [1,QHK] 
       because $HQ = QH$ and $[HQS] sim [QHK]$.
con-tri[1]:[1,HQS] [1,QHK] [-1,QBK] [-1,SKH] [-1,KSQ] [-1,AQS] 
       because con-tri[0]:[-1,QBK] [-1,SKH] [-1,KSQ] [-1,AQS] [1,HQS]  and con-tri[0]:[1,HQS] [1,QHK] .
con-tri[0]:[1,LKS] [-1,MQH] 
       because $LK = MQ$ and $[LKS] sim [MQH]$.
con-tri[0]:[1,LKS] [1,MQA] 
       because $LK = MQ$ and $[LKS] sim [MQA]$.
con-tri[1]:[1,LKS] [1,MQA] [-1,MQH] 
       because con-tri[0]:[1,LKS] [-1,MQH]  and con-tri[0]:[1,LKS] [1,MQA] .
con-tri[0]:[1,LSH] [1,SLM] 
       because $LS = SL$ and $[LSH] sim [SLM]$.
con-tri[0]:[1,LSH] [-1,HML] 
       because $LS = HM$ and $[LSH] sim [HML]$.
con-tri[0]:[1,LSH] [-1,HML] [1,SLM] 
       because con-tri[0]:[1,LSH] [1,SLM]  and con-tri[0]:[1,LSH] [-1,HML] .
con-tri[0]:[1,LSH] [-1,MHS] 
       because $LS = MH$ and $[LSH] sim [MHS]$.
con-tri[0]:[1,LSH] [-1,MHS] [-1,HML] [1,SLM] 
       because con-tri[0]:[1,LSH] [-1,HML] [1,SLM]  and con-tri[0]:[1,LSH] [-1,MHS] .
con-tri[0]:[1,LSH] [1,CML] 
       because $LS = CM$ and $[LSH] sim [CML]$.
con-tri[0]:[1,LSH] [1,CML] [-1,MHS] [-1,HML] [1,SLM] 
       because con-tri[0]:[1,LSH] [-1,MHS] [-1,HML] [1,SLM]  and con-tri[0]:[1,LSH] [1,CML] .
con-tri[0]:[1,LSH] [1,MAS] 
       because $LS = MA$ and $[LSH] sim [MAS]$.
con-tri[1]:[1,LSH] [1,MAS] [1,CML] [-1,MHS] [-1,HML] [1,SLM] 
       because con-tri[0]:[1,LSH] [1,CML] [-1,MHS] [-1,HML] [1,SLM]  and con-tri[0]:[1,LSH] [1,MAS] .
con-tri[0]:[1,ASP] [1,SAN] 
       because $AS = SA$ and $[ASP] sim [SAN]$.
con-tri[0]:[1,ASP] [-1,PNA] 
       because $AS = PN$ and $[ASP] sim [PNA]$.
con-tri[0]:[1,ASP] [-1,PNA] [-1,NPS] 
       because con-tri[0]:[-1,PNA] [-1,NPS]  and con-tri[0]:[1,ASP] [-1,PNA] .
con-tri[1]:[1,ASP] [-1,PNA] [-1,NPS] [1,SAN] 
       because con-tri[0]:[1,ASP] [1,SAN]  and con-tri[0]:[1,ASP] [-1,PNA] [-1,NPS] .

The configuration contains the following equal ratios
[1]:AS*AC = AH*AM 
       because para[$SM,HC$].
[1]:AS*HC = AH*SM 
       because para[$SM,HC$].
[1]:AM*HC = AC*SM 
       because para[$SM,HC$].
[0]:AS*MC = SH*AM 
       because para[$SM,HC$].
[1]:AS*AB = AH*AQ 
       because para[$SQ,HB$].
[1]:AS*HB = AH*SQ 
       because para[$SQ,HB$].
[1]:AQ*HB = AB*SQ 
       because para[$SQ,HB$].
[0]:AS*QB = SH*AQ 
       because para[$SQ,HB$].
[1]:AM*AB = AC*AQ 
       because [1]:AS*AB = AH*AQ  and [1]:AS*AC = AH*AM .
[1]:SM*AB = HC*AQ 
       because [1]:AS*AB = AH*AQ  and [1]:AS*HC = AH*SM .
[1]:AM*HB = AC*SQ 
       because [1]:AS*HB = AH*SQ  and [1]:AS*AC = AH*AM .
[1]:SM*HB = HC*SQ 
       because [1]:AS*HB = AH*SQ  and [1]:AS*HC = AH*SM .
[1]:NS*NC = NK*NM 
       because para[$SM,KC$].
[1]:NS*KC = NK*SM 
       because para[$SM,KC$].
[1]:NM*KC = NC*SM 
       because para[$SM,KC$].
[1]:NS*MC = SK*NM 
       because para[$SM,KC$].
[1]:NK*CM = KS*NC 
       because para[$SM,KC$].
[1]:PS*PB = PL*PQ 
       because para[$SQ,LB$].
[1]:PS*LB = PL*SQ 
       because para[$SQ,LB$].
[1]:PQ*LB = PB*SQ 
       because para[$SQ,LB$].
[1]:PS*QB = SL*PQ 
       because para[$SQ,LB$].
[1]:PL*BQ = LS*PB 
       because para[$SQ,LB$].
[1]:AM*CB = AC*MQ 
       because para[$MQ,CB$].
[1]:AQ*CB = AB*MQ 
       because para[$MQ,CB$].
[1]:AS*CB = AH*MQ 
       because [1]:AM*CB = AC*MQ  and [1]:AS*AC = AH*AM .
[1]:SM*CB = HC*MQ 
       because [1]:AM*CB = AC*MQ  and [1]:AM*HC = AC*SM .
[1]:SQ*CB = HB*MQ 
       because [1]:AM*CB = AC*MQ  and [1]:AM*HB = AC*SQ .
[1]:HS*HB = HA*HK 
       because para[$SK,AB$].
[1]:HS*AB = HA*SK 
       because para[$SK,AB$].
[1]:HK*AB = HB*SK 
       because para[$SK,AB$].
[0]:HS*KB = SA*HK 
       because para[$SK,AB$].
[1]:AM*HB = AC*HK 
       because [1]:HS*HB = HA*HK  and [1]:AS*AC = AH*AM .
[1]:SM*HB = HC*HK 
       because [1]:HS*HB = HA*HK  and [1]:AS*HC = AH*SM .
[1]:AQ*HB = AB*HK 
       because [1]:HS*HB = HA*HK  and [1]:AS*AB = AH*AQ .
[0]:SQ*HB = HB*HK 
       because [1]:HS*HB = HA*HK  and [1]:AS*HB = AH*SQ .
[1]:AM*AB = AC*SK 
       because [1]:HS*AB = HA*SK  and [1]:AS*AC = AH*AM .
[1]:SM*AB = HC*SK 
       because [1]:HS*AB = HA*SK  and [1]:AS*HC = AH*SM .
[0]:AQ*AB = AB*SK 
       because [1]:HS*AB = HA*SK  and [1]:AS*AB = AH*AQ .
[1]:NS*AC = NM*AB 
       because [1]:AM*AB = AC*SK  and [1]:NS*MC = SK*NM .
[1]:NK*AC = NC*AB 
       because [1]:AM*AB = AC*SK  and [1]:NK*CM = KS*NC .
[1]:MS*MA = MQ*MN 
       because para[$SN,QA$].
[1]:MS*QA = MQ*SN 
       because para[$SN,QA$].
[1]:MS*NA = SQ*MN 
       because para[$SN,QA$].
[1]:MQ*AN = QS*MA 
       because para[$SN,QA$].
[1]:NC*MQ = KC*MA 
       because [1]:MS*MA = MQ*MN  and [1]:NM*KC = NC*SM .
[1]:AC*MS = CB*MN 
       because [1]:MS*MA = MQ*MN  and [1]:AM*CB = AC*MQ .
[1]:HC*MA = CB*MN 
       because [1]:MS*MA = MQ*MN  and [1]:SM*CB = HC*MQ .
[1]:NK*MQ = KC*QA 
       because [1]:MS*QA = MQ*SN  and [1]:NS*KC = NK*SM .
[1]:AB*MS = CB*SN 
       because [1]:MS*QA = MQ*SN  and [1]:AQ*CB = AB*MQ .
[1]:HC*QA = CB*SN 
       because [1]:MS*QA = MQ*SN  and [1]:SM*CB = HC*MQ .
[1]:HC*NA = HB*MN 
       because [1]:MS*NA = SQ*MN  and [1]:SM*HB = HC*SQ .
[1]:NC*SQ = KC*NA 
       because [1]:MS*NA = SQ*MN  and [1]:NM*KC = NC*SM .
[1]:AC*QS = CB*AN 
       because [1]:MQ*AN = QS*MA  and [1]:AM*CB = AC*MQ .
[1]:HB*MA = CB*AN 
       because [1]:MQ*AN = QS*MA  and [1]:SQ*CB = HB*MQ .
[1]:AC*KC = CB*NC 
       because [1]:NC*MQ = KC*MA  and [1]:AM*CB = AC*MQ .
[1]:AB*KC = CB*NK 
       because [1]:NK*MQ = KC*QA  and [1]:AQ*CB = AB*MQ .
[1]:LS*LB = LP*LK 
       because para[$SK,PB$].
[1]:LK*PB = LB*SK 
       because para[$SK,PB$].
[1]:LS*KB = SP*LK 
       because para[$SK,PB$].
[1]:PQ*LK = SQ*SK 
       because [1]:LK*PB = LB*SK  and [1]:PQ*LB = PB*SQ .
[1]:HS*HC = HA*HL 
       because para[$SL,AC$].
[1]:HS*AC = HA*SL 
       because para[$SL,AC$].
[1]:HL*AC = HC*SL 
       because para[$SL,AC$].
[0]:HS*LC = SA*HL 
       because para[$SL,AC$].
[1]:AM*HC = AC*HL 
       because [1]:HS*HC = HA*HL  and [1]:AS*AC = AH*AM .
[0]:SM*HC = HC*HL 
       because [1]:HS*HC = HA*HL  and [1]:AS*HC = AH*SM .
[0]:AM*AC = AC*SL 
       because [1]:HS*AC = HA*SL  and [1]:AS*AC = AH*AM .
[1]:QS*QA = QM*QP 
       because para[$SP,MA$].
[1]:QS*MA = QM*SP 
       because para[$SP,MA$].
[1]:QS*PA = SM*QP 
       because para[$SP,MA$].
[1]:QM*AP = MS*QA 
       because para[$SP,MA$].
[1]:PB*QM = LB*QA 
       because [1]:QS*QA = QM*QP  and [1]:PQ*LB = PB*SQ .
[1]:AB*QS = CB*QP 
       because [1]:QS*QA = QM*QP  and [1]:AQ*CB = AB*MQ .
[1]:HB*QA = CB*QP 
       because [1]:QS*QA = QM*QP  and [1]:SQ*CB = HB*MQ .
[1]:MS*QP = SN*QS 
       because [1]:QS*QA = QM*QP  and [1]:MS*QA = MQ*SN .
[1]:MA*QP = AN*QA 
       because [1]:QS*QA = QM*QP  and [1]:MQ*AN = QS*MA .
[1]:NK*QS = KC*QP 
       because [1]:QS*QA = QM*QP  and [1]:NK*MQ = KC*QA .
[0]:SK*QM = LK*QA 
       because [1]:QS*QA = QM*QP  and [1]:PQ*LK = SQ*SK .
[1]:AC*QS = CB*SP 
       because [1]:QS*MA = QM*SP  and [1]:AM*CB = AC*MQ .
[1]:HB*MA = CB*SP 
       because [1]:QS*MA = QM*SP  and [1]:SQ*CB = HB*MQ .
[1]:MS*SP = MN*QS 
       because [1]:QS*MA = QM*SP  and [1]:MS*MA = MQ*MN .
[0]:MA*SP = AN*MA 
       because [1]:QS*MA = QM*SP  and [1]:MQ*AN = QS*MA .
[1]:HC*QP = HB*PA 
       because [1]:QS*PA = SM*QP  and [1]:SM*HB = HC*SQ .
[1]:PB*SM = LB*PA 
       because [1]:QS*PA = SM*QP  and [1]:PQ*LB = PB*SQ .
[1]:MN*QP = NA*PA 
       because [1]:QS*PA = SM*QP  and [1]:MS*NA = SQ*MN .
[1]:AB*MS = CB*AP 
       because [1]:QM*AP = MS*QA  and [1]:AQ*CB = AB*MQ .
[1]:HC*QA = CB*AP 
       because [1]:QM*AP = MS*QA  and [1]:SM*CB = HC*MQ .
[1]:MN*QA = MA*AP 
       because [1]:QM*AP = MS*QA  and [1]:MS*MA = MQ*MN .
[0]:SN*QA = QA*AP 
       because [1]:QM*AP = MS*QA  and [1]:MS*QA = MQ*SN .
[1]:AB*LB = CB*PB 
       because [1]:PB*QM = LB*QA  and [1]:AQ*CB = AB*MQ .
[1]:NK*LB = KC*PB 
       because [1]:PB*QM = LB*QA  and [1]:NK*MQ = KC*QA .
[1]:AC*QP = AN*AB 
       because [1]:AB*QS = CB*QP  and [1]:AC*QS = CB*AN .
[1]:NK*AN = NC*QP 
       because [1]:MA*QP = AN*QA  and [1]:NK*CM = KS*NC .
[1]:PL*CB = LB*AC 
       because [1]:AC*QS = CB*SP  and [1]:PS*LB = PL*SQ .
[1]:PL*MS = LB*MN 
       because [1]:MS*SP = MN*QS  and [1]:PS*LB = PL*SQ .
[1]:PL*PA = PB*MN 
       because [1]:MN*QP = NA*PA  and [1]:PS*PB = PL*PQ .
[1]:PL*AB = PB*AC 
       because [1]:AC*QP = AN*AB  and [1]:PS*PB = PL*PQ .
[1]:PL*NK = PB*NC 
       because [1]:NK*AN = NC*QP  and [1]:PS*PB = PL*PQ .
[1]:NC*LB = KC*PL 
       because [1]:PL*CB = LB*AC  and [1]:AC*KC = CB*NC .
[1]:BQ*AH = BA*QK 
       because para[$QK,AH$].
[1]:BK*AH = BH*QK 
       because para[$QK,AH$].
[0]:AS*AH = AH*QK 
       because [1]:BQ*AH = BA*QK  and [1]:AS*AB = AH*AQ .
[1]:SC*KH = KA*SH 
       because $[SCH] sim [KAH]$.
[1]:SC*AH = KA*CH 
       because $[SCH] sim [KAH]$.
[1]:SH*AH = KH*CH 
       because $[SCH] sim [KAH]$.
[1]:AH*KA = HB*SC 
       because [1]:SC*KH = KA*SH  and [1]:AS*HB = AH*SQ .
[1]:AS*SC = SM*KA 
       because [1]:SC*AH = KA*CH  and [1]:AS*HC = AH*SM .
[1]:AS*SH = SM*KH 
       because [1]:SH*AH = KH*CH  and [1]:AS*HC = AH*SM .
[1]:AH*AH = HB*CH 
       because [1]:SH*AH = KH*CH  and [1]:AS*HB = AH*SQ .
[1]:SM*HB = SH*AH 
       because [1]:SH*AH = KH*CH  and [1]:SM*HB = HC*SQ .
[1]:SC*NA = NH*SA 
       because $[SCA] sim [NHA]$.
[1]:SC*HA = NH*CA 
       because $[SCA] sim [NHA]$.
[1]:SA*HA = NA*CA 
       because $[SCA] sim [NHA]$.
[1]:KA*NA = KH*NH 
       because [1]:SC*NA = NH*SA  and [1]:SC*KH = KA*SH .
[1]:AS*SC = AM*NH 
       because [1]:SC*HA = NH*CA  and [1]:AS*AC = AH*AM .
[1]:KA*CH = NH*CA 
       because [1]:SC*HA = NH*CA  and [1]:SC*AH = KA*CH .
[1]:AS*SA = AM*NA 
       because [1]:SA*HA = NA*CA  and [1]:AS*AC = AH*AM .
[1]:KH*CH = NA*CA 
       because [1]:SA*HA = NA*CA  and [1]:SH*AH = KH*CH .
[1]:SM*HB = NA*CA 
       because [1]:SA*HA = NA*CA  and [1]:SM*HB = SH*AH .
[1]:PL*KA = LB*NH 
       because [1]:KA*NA = KH*NH  and [1]:PS*LB = PL*SQ .
[1]:MS*NH = MN*KA 
       because [1]:KA*NA = KH*NH  and [1]:MS*NA = SQ*MN .
[1]:MQ*NH = MA*KA 
       because [1]:KA*NA = KH*NH  and [1]:MQ*AN = QS*MA .
[1]:NC*KA = KC*NH 
       because [1]:KA*NA = KH*NH  and [1]:NC*SQ = KC*NA .
[1]:AC*KA = CB*NH 
       because [1]:KA*NA = KH*NH  and [1]:AC*QS = CB*AN .
[1]:SM*KA = AM*NH 
       because [1]:AS*SC = AM*NH  and [1]:AS*SC = SM*KA .
[1]:SM*KH = AM*NA 
       because [1]:AS*SA = AM*NA  and [1]:AS*SH = SM*KH .
[1]:PL*CA = LB*CH 
       because [1]:KH*CH = NA*CA  and [1]:PS*LB = PL*SQ .
[1]:MS*CH = MN*CA 
       because [1]:KH*CH = NA*CA  and [1]:MS*NA = SQ*MN .
[1]:MQ*CH = MA*CA 
       because [1]:KH*CH = NA*CA  and [1]:MQ*AN = QS*MA .
[1]:NC*CA = KC*CH 
       because [1]:KH*CH = NA*CA  and [1]:NC*SQ = KC*NA .
[1]:AC*CA = CB*CH 
       because [1]:KH*CH = NA*CA  and [1]:AC*QS = CB*AN .
[1]:CB*SM = MA*CA 
       because [1]:SM*HB = NA*CA  and [1]:HB*MA = CB*AN .
[1]:PL*AM = LB*SM 
       because [1]:SM*KA = AM*NH  and [1]:PL*KA = LB*NH .
[1]:MS*SM = MN*AM 
       because [1]:SM*KA = AM*NH  and [1]:MS*NH = MN*KA .
[1]:MQ*SM = MA*AM 
       because [1]:SM*KA = AM*NH  and [1]:MQ*NH = MA*KA .
[1]:NC*AM = KC*SM 
       because [1]:SM*KA = AM*NH  and [1]:NC*KA = KC*NH .
[1]:SC*AB = KA*CA 
       because $[SCA] sim [KAB]$.
[1]:SA*AB = KB*CA 
       because $[SCA] sim [KAB]$.
[1]:AM*KA = AQ*SC 
       because [1]:SC*AB = KA*CA  and [1]:AM*AB = AC*AQ .
[1]:NS*SC = NM*KA 
       because [1]:SC*AB = KA*CA  and [1]:NS*AC = NM*AB .
[1]:NK*SC = NC*KA 
       because [1]:SC*AB = KA*CA  and [1]:NK*AC = NC*AB .
[1]:AN*KA = QP*SC 
       because [1]:SC*AB = KA*CA  and [1]:AC*QP = AN*AB .
[1]:PL*KA = PB*SC 
       because [1]:SC*AB = KA*CA  and [1]:PL*AB = PB*AC .
[1]:CH*AB = AH*CA 
       because [1]:SC*AB = KA*CA  and [1]:SC*AH = KA*CH .
[1]:AH*AB = HB*CA 
       because [1]:SC*AB = KA*CA  and [1]:AH*KA = HB*SC .
[1]:AS*CA = SM*AB 
       because [1]:SC*AB = KA*CA  and [1]:AS*SC = SM*KA .
[1]:NH*AB = HA*KA 
       because [1]:SC*AB = KA*CA  and [1]:SC*HA = NH*CA .
[1]:CB*NH = SC*AB 
       because [1]:SC*AB = KA*CA  and [1]:AC*KA = CB*NH .
[1]:AH*AQ = KB*CA 
       because [1]:SA*AB = KB*CA  and [1]:AS*AB = AH*AQ .
[1]:AM*KB = AQ*SA 
       because [1]:SA*AB = KB*CA  and [1]:AM*AB = AC*AQ .
[1]:AM*HB = SA*AB 
       because [1]:SA*AB = KB*CA  and [1]:AM*HB = AC*SQ .
[1]:NS*SA = NM*KB 
       because [1]:SA*AB = KB*CA  and [1]:NS*AC = NM*AB .
[1]:NK*SA = NC*KB 
       because [1]:SA*AB = KB*CA  and [1]:NK*AC = NC*AB .
[1]:CB*AN = SA*AB 
       because [1]:SA*AB = KB*CA  and [1]:AC*QS = CB*AN .
[1]:AN*KB = QP*SA 
       because [1]:SA*AB = KB*CA  and [1]:AC*QP = AN*AB .
[1]:PL*KB = PB*SA 
       because [1]:SA*AB = KB*CA  and [1]:PL*AB = PB*AC .
[1]:NA*AB = HA*KB 
       because [1]:SA*AB = KB*CA  and [1]:SA*HA = NA*CA .
[1]:CH*AQ = AH*AM 
       because [1]:AM*KA = AQ*SC  and [1]:SC*AH = KA*CH .
[1]:AH*AQ = HB*AM 
       because [1]:AM*KA = AQ*SC  and [1]:AH*KA = HB*SC .
[1]:AS*AM = SM*AQ 
       because [1]:AM*KA = AQ*SC  and [1]:AS*SC = SM*KA .
[1]:AS*KA = NH*AQ 
       because [1]:AM*KA = AQ*SC  and [1]:AS*SC = AM*NH .
[1]:MQ*NH = AQ*SC 
       because [1]:AM*KA = AQ*SC  and [1]:MQ*NH = MA*KA .
[1]:CH*NS = AH*NM 
       because [1]:NS*SC = NM*KA  and [1]:SC*AH = KA*CH .
[1]:AH*NS = HB*NM 
       because [1]:NS*SC = NM*KA  and [1]:AH*KA = HB*SC .
[1]:AS*NM = SM*NS 
       because [1]:NS*SC = NM*KA  and [1]:AS*SC = SM*KA .
[1]:MS*NH = NS*SC 
       because [1]:NS*SC = NM*KA  and [1]:MS*NH = MN*KA .
[1]:CH*NK = AH*NC 
       because [1]:NK*SC = NC*KA  and [1]:SC*AH = KA*CH .
[1]:AH*NK = HB*NC 
       because [1]:NK*SC = NC*KA  and [1]:AH*KA = HB*SC .
[1]:AS*NC = SM*NK 
       because [1]:NK*SC = NC*KA  and [1]:AS*SC = SM*KA .
[1]:KC*NH = NK*SC 
       because [1]:NK*SC = NC*KA  and [1]:NC*KA = KC*NH .
[1]:CH*QP = AH*AN 
       because [1]:AN*KA = QP*SC  and [1]:SC*AH = KA*CH .
[1]:AH*QP = HB*AN 
       because [1]:AN*KA = QP*SC  and [1]:AH*KA = HB*SC .
[1]:AS*AN = SM*QP 
       because [1]:AN*KA = QP*SC  and [1]:AS*SC = SM*KA .
[1]:KH*NH = QP*SC 
       because [1]:AN*KA = QP*SC  and [1]:KA*NA = KH*NH .
[1]:CH*PB = AH*PL 
       because [1]:PL*KA = PB*SC  and [1]:SC*AH = KA*CH .
[1]:AH*PB = HB*PL 
       because [1]:PL*KA = PB*SC  and [1]:AH*KA = HB*SC .
[1]:AS*PL = SM*PB 
       because [1]:PL*KA = PB*SC  and [1]:AS*SC = SM*KA .
[1]:LB*NH = PB*SC 
       because [1]:PL*KA = PB*SC  and [1]:PL*KA = LB*NH .
[1]:AM*AH = SM*AB 
       because [1]:CH*AB = AH*CA  and [1]:AM*HC = AC*SM .
[1]:AS*CA = AQ*CH 
       because [1]:CH*AB = AH*CA  and [1]:AS*AB = AH*AQ .
[1]:PL*AB = LB*AH 
       because [1]:CH*AB = AH*CA  and [1]:PL*CA = LB*CH .
[1]:MS*AH = MN*AB 
       because [1]:CH*AB = AH*CA  and [1]:MS*CH = MN*CA .
[1]:MQ*AH = MA*AB 
       because [1]:CH*AB = AH*CA  and [1]:MQ*CH = MA*CA .
[1]:NC*AB = KC*AH 
       because [1]:CH*AB = AH*CA  and [1]:NC*CA = KC*CH .
[1]:AC*AB = CB*AH 
       because [1]:CH*AB = AH*CA  and [1]:AC*CA = CB*CH .
[1]:SC*HB = NH*AB 
       because [1]:AH*AB = HB*CA  and [1]:SC*HA = NH*CA .
[1]:SA*HB = NA*AB 
       because [1]:AH*AB = HB*CA  and [1]:SA*HA = NA*CA .
[1]:CB*SN = AS*CA 
       because [1]:AS*CA = SM*AB  and [1]:AB*MS = CB*SN .
[1]:MN*AB = CH*AS 
       because [1]:AS*CA = SM*AB  and [1]:MS*CH = MN*CA .
[1]:CB*AS = MA*AB 
       because [1]:AS*CA = SM*AB  and [1]:CB*SM = MA*CA .
[1]:CB*AN = AH*AQ 
       because [1]:AH*AQ = KB*CA  and [1]:AC*QS = CB*AN .
[1]:SC*KB = NH*AQ 
       because [1]:AH*AQ = KB*CA  and [1]:SC*HA = NH*CA .
[1]:SA*KB = NA*AQ 
       because [1]:AH*AQ = KB*CA  and [1]:SA*HA = NA*CA .
[1]:MQ*AN = AQ*SA 
       because [1]:AM*KB = AQ*SA  and [1]:MQ*AN = QS*MA .
[1]:MS*NA = NS*SA 
       because [1]:NS*SA = NM*KB  and [1]:MS*NA = SQ*MN .
[1]:KC*NA = NK*SA 
       because [1]:NK*SA = NC*KB  and [1]:NC*SQ = KC*NA .
[1]:PB*SA = LB*AN 
       because [1]:CB*AN = SA*AB  and [1]:AB*LB = CB*PB .
[1]:CA*QP = HA*KB 
       because [1]:AN*KB = QP*SA  and [1]:SA*HA = NA*CA .
[1]:AS*KB = AM*QP 
       because [1]:AN*KB = QP*SA  and [1]:AS*SA = AM*NA .
[1]:CB*SN = AH*AM 
       because [1]:CH*AQ = AH*AM  and [1]:HC*QA = CB*SN .
[1]:MQ*AH = CA*AQ 
       because [1]:CH*AQ = AH*AM  and [1]:MQ*CH = MA*CA .
[1]:MQ*SN = AS*AM 
       because [1]:AS*AM = SM*AQ  and [1]:MS*QA = MQ*SN .
[1]:CB*AS = CA*AQ 
       because [1]:AS*AM = SM*AQ  and [1]:CB*SM = MA*CA .
[1]:PL*AQ = LB*AS 
       because [1]:AS*AM = SM*AQ  and [1]:PL*AM = LB*SM .
[1]:MS*AS = MN*AQ 
       because [1]:AS*AM = SM*AQ  and [1]:MS*SM = MN*AM .
[1]:MQ*AS = MA*AQ 
       because [1]:AS*AM = SM*AQ  and [1]:MQ*SM = MA*AM .
[1]:NC*AQ = KC*AS 
       because [1]:AS*AM = SM*AQ  and [1]:NC*AM = KC*SM .
[1]:HB*NS = NA*AH 
       because [1]:CH*NS = AH*NM  and [1]:HC*NA = HB*MN .
[1]:MS*AH = CA*NS 
       because [1]:CH*NS = AH*NM  and [1]:MS*CH = MN*CA .
[1]:HC*NA = AH*NS 
       because [1]:AH*NS = HB*NM  and [1]:HC*NA = HB*MN .
[1]:NC*AS = KC*NS 
       because [1]:AS*NM = SM*NS  and [1]:NM*KC = NC*SM .
[1]:SQ*NS = NA*AS 
       because [1]:AS*NM = SM*NS  and [1]:MS*NA = SQ*MN .
[1]:PL*AS = LB*NS 
       because [1]:AS*NM = SM*NS  and [1]:PL*MS = LB*MN .
[1]:KA*NS = NH*AS 
       because [1]:AS*NM = SM*NS  and [1]:MS*NH = MN*KA .
[1]:CA*NS = CH*AS 
       because [1]:AS*NM = SM*NS  and [1]:MS*CH = MN*CA .
[1]:AM*NS = SM*AS 
       because [1]:AS*NM = SM*NS  and [1]:MS*SM = MN*AM .
[1]:KC*AH = CA*NK 
       because [1]:CH*NK = AH*NC  and [1]:NC*CA = KC*CH .
[1]:KC*AS = AM*NK 
       because [1]:AS*NC = SM*NK  and [1]:NC*AM = KC*SM .
[1]:SA*HB = CA*QP 
       because [1]:AH*QP = HB*AN  and [1]:SA*HA = NA*CA .
[1]:KB*HB = AB*QP 
       because [1]:AH*QP = HB*AN  and [1]:NA*AB = HA*KB .
[1]:LB*AH = CA*PB 
       because [1]:CH*PB = AH*PL  and [1]:PL*CA = LB*CH .
[1]:LB*AS = AM*PB 
       because [1]:AS*PL = SM*PB  and [1]:PL*AM = LB*SM .
[1]:HB*LB = PB*AB 
       because [1]:PL*AB = LB*AH  and [1]:AH*PB = HB*PL .
[1]:HB*MS = NS*AB 
       because [1]:MS*AH = MN*AB  and [1]:AH*NS = HB*NM .
[1]:AS*AH = NS*AB 
       because [1]:MS*AH = MN*AB  and [1]:AS*NM = SM*NS .
[1]:HB*MQ = AQ*AB 
       because [1]:MQ*AH = MA*AB  and [1]:AH*AQ = HB*AM .
[1]:HB*KC = NK*AB 
       because [1]:NC*AB = KC*AH  and [1]:AH*NK = HB*NC .
[1]:HB*CB = AB*AB 
       because [1]:AC*AB = CB*AH  and [1]:AH*AB = HB*CA .
[1]:KB*CB = AQ*AB 
       because [1]:AC*AB = CB*AH  and [1]:AH*AQ = KB*CA .
[1]:AB*SN = AN*CA 
       because [1]:CB*SN = AS*CA  and [1]:CB*AN = SA*AB .
[1]:NS*AB = KB*CH 
       because [1]:MN*AB = CH*AS  and [1]:NS*SA = NM*KB .
[1]:MQ*KB = AQ*AQ 
       because [1]:SC*KB = NH*AQ  and [1]:MQ*NH = AQ*SC .
[1]:MS*KB = NS*AQ 
       because [1]:SC*KB = NH*AQ  and [1]:MS*NH = NS*SC .
[1]:KC*KB = NK*AQ 
       because [1]:SC*KB = NH*AQ  and [1]:KC*NH = NK*SC .
[1]:KH*KB = QP*AQ 
       because [1]:SC*KB = NH*AQ  and [1]:KH*NH = QP*SC .
[1]:LB*KB = PB*AQ 
       because [1]:SC*KB = NH*AQ  and [1]:LB*NH = PB*SC .
[1]:KA*NS = SC*NA 
       because [1]:MS*NA = NS*SA  and [1]:AS*SC = SM*KA .
[1]:AQ*NS = AM*NA 
       because [1]:MS*NA = NS*SA  and [1]:AS*AM = SM*AQ .
[1]:NS*NS = NM*NA 
       because [1]:MS*NA = NS*SA  and [1]:AS*NM = SM*NS .
[1]:NK*NS = NC*NA 
       because [1]:MS*NA = NS*SA  and [1]:AS*NC = SM*NK .
[1]:QP*NS = AN*NA 
       because [1]:MS*NA = NS*SA  and [1]:AS*AN = SM*QP .
[1]:PB*NS = PL*NA 
       because [1]:MS*NA = NS*SA  and [1]:AS*PL = SM*PB .
[1]:AS*AS = SN*AQ 
       because [1]:CB*AS = CA*AQ  and [1]:CB*SN = AS*CA .
[1]:SB*LH = LA*SH 
       because $[SBH] sim [LAH]$.
[1]:SB*AH = LA*BH 
       because $[SBH] sim [LAH]$.
[1]:AH*LA = HC*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*HC = AH*SM .
[1]:KA*LA = SC*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*SC = SM*KA .
[1]:KH*LA = SH*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*SH = SM*KH .
[1]:AB*LA = CA*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*CA = SM*AB .
[1]:AQ*LA = AM*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*AM = SM*AQ .
[1]:NS*LA = NM*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*NM = SM*NS .
[1]:NK*LA = NC*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*NC = SM*NK .
[1]:QP*LA = AN*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*AN = SM*QP .
[1]:PB*LA = PL*SB 
       because [1]:SB*LH = LA*SH  and [1]:AS*PL = SM*PB .
[1]:NS*SB = NA*LA 
       because [1]:SB*LH = LA*SH  and [1]:MS*NA = NS*SA .
[1]:SB*PA = PH*SA 
       because $[SBA] sim [PHA]$.
[1]:SB*HA = PH*BA 
       because $[SBA] sim [PHA]$.
[1]:SM*PH = NM*SB 
       because [1]:SB*PA = PH*SA  and [1]:AS*NM = SM*NS .
[1]:CB*PH = CA*SB 
       because [1]:SB*PA = PH*SA  and [1]:CB*SN = AS*CA .
[1]:MQ*PH = AM*SB 
       because [1]:SB*PA = PH*SA  and [1]:MQ*SN = AS*AM .
[1]:NC*SB = KC*PH 
       because [1]:SB*PA = PH*SA  and [1]:NC*AS = KC*NS .
[1]:SQ*PH = NA*SB 
       because [1]:SB*PA = PH*SA  and [1]:SQ*NS = NA*AS .
[1]:PL*SB = LB*PH 
       because [1]:SB*PA = PH*SA  and [1]:PL*AS = LB*NS .
[1]:KA*PH = NH*SB 
       because [1]:SB*PA = PH*SA  and [1]:KA*NS = NH*AS .
[1]:CA*PH = CH*SB 
       because [1]:SB*PA = PH*SA  and [1]:CA*NS = CH*AS .
[1]:AM*PH = SM*SB 
       because [1]:SB*PA = PH*SA  and [1]:AM*NS = SM*AS .
[1]:AQ*PH = AS*SB 
       because [1]:SB*PA = PH*SA  and [1]:AS*AS = SN*AQ .
[1]:LA*PA = LH*PH 
       because [1]:SB*PA = PH*SA  and [1]:SB*LH = LA*SH .
[1]:NA*LA = PH*SA 
       because [1]:SB*PA = PH*SA  and [1]:NS*SB = NA*LA .
[1]:LA*BH = PH*BA 
       because [1]:SB*HA = PH*BA  and [1]:SB*AH = LA*BH .
[1]:CA*PH = LA*HA 
       because [1]:SB*HA = PH*BA  and [1]:AB*LA = CA*SB .
[1]:AB*LA = CB*PH 
       because [1]:CB*PH = CA*SB  and [1]:AB*LA = CA*SB .
[1]:AQ*LA = MQ*PH 
       because [1]:MQ*PH = AM*SB  and [1]:AQ*LA = AM*SB .
[1]:NK*LA = KC*PH 
       because [1]:NC*SB = KC*PH  and [1]:NK*LA = NC*SB .
[1]:QP*LA = SQ*PH 
       because [1]:SQ*PH = NA*SB  and [1]:QP*LA = AN*SB .
[1]:PB*LA = LB*PH 
       because [1]:PL*SB = LB*PH  and [1]:PB*LA = PL*SB .
[1]:SC*PH = LA*NH 
       because [1]:KA*PH = NH*SB  and [1]:KA*LA = SC*SB .
[1]:LA*SH = AM*PH 
       because [1]:AM*PH = SM*SB  and [1]:SB*LH = LA*SH .
[1]:KH*LA = AQ*PH 
       because [1]:AQ*PH = AS*SB  and [1]:KH*LA = SH*SB .
[1]:SC*QM = QC*SL 
       because $[SCL] sim [QCM]$.
[1]:SC*CM = QC*CL 
       because $[SCL] sim [QCM]$.
[1]:AC*QC = CB*SC 
       because [1]:SC*QM = QC*SL  and [1]:AM*CB = AC*MQ .
[1]:MS*SC = MN*QC 
       because [1]:SC*QM = QC*SL  and [1]:MS*MA = MQ*MN .
[1]:QS*SC = AN*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*AN = QS*MA .
[1]:NC*QC = KC*SC 
       because [1]:SC*QM = QC*SL  and [1]:NC*MQ = KC*MA .
[1]:LP*QC = LB*SC 
       because [1]:SC*QM = QC*SL  and [1]:LS*LB = LP*LK .
[1]:AS*QC = NH*QM 
       because [1]:SC*QM = QC*SL  and [1]:AS*SC = AM*NH .
[1]:KA*SC = NH*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*NH = MA*KA .
[1]:CA*SC = CH*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*CH = MA*CA .
[1]:AQ*QC = KA*QM 
       because [1]:SC*QM = QC*SL  and [1]:AM*KA = AQ*SC .
[1]:AB*SC = AH*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*AH = MA*AB .
[1]:AS*SC = SN*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*SN = AS*AM .
[1]:AQ*SC = AS*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*AS = MA*AQ .
[1]:SB*SC = PH*QC 
       because [1]:SC*QM = QC*SL  and [1]:MQ*PH = AM*SB .
[1]:AS*QC = KA*CM 
       because [1]:SC*CM = QC*CL  and [1]:AS*SC = SM*KA .
[1]:NS*QC = NH*CM 
       because [1]:SC*CM = QC*CL  and [1]:MS*NH = NS*SC .
[1]:NH*CB = HA*QC 
       because [1]:AC*QC = CB*SC  and [1]:SC*HA = NH*CA .
[1]:KA*CB = AB*QC 
       because [1]:AC*QC = CB*SC  and [1]:SC*AB = KA*CA .
[1]:NS*QC = KA*MS 
       because [1]:MS*SC = MN*QC  and [1]:NS*SC = NM*KA .
[1]:KA*SH = AN*QC 
       because [1]:QS*SC = AN*QC  and [1]:SC*KH = KA*SH .
[1]:QP*QC = KA*QS 
       because [1]:QS*SC = AN*QC  and [1]:AN*KA = QP*SC .
[1]:NH*AQ = AN*QC 
       because [1]:QS*SC = AN*QC  and [1]:SC*KB = NH*AQ .
[1]:NK*QC = KA*KC 
       because [1]:NC*QC = KC*SC  and [1]:NK*SC = NC*KA .
[1]:PB*QC = KA*LB 
       because [1]:LP*QC = LB*SC  and [1]:PL*KA = PB*SC .
[1]:CA*KA = HA*QC 
       because [1]:KA*SC = NH*QC  and [1]:SC*HA = NH*CA .
[1]:AB*KA = HB*QC 
       because [1]:KA*SC = NH*QC  and [1]:SC*HB = NH*AB .
[1]:AQ*KA = KB*QC 
       because [1]:KA*SC = NH*QC  and [1]:SC*KB = NH*AQ .
[1]:LA*KA = PH*QC 
       because [1]:KA*SC = NH*QC  and [1]:SC*PH = LA*NH .
[1]:CL*PK = AP*LQ 
       because $[CLQ] sim [APK]$.
[1]:CQ*PK = AK*LQ 
       because $[CLQ] sim [APK]$.
[1]:NK*LQ = KC*PK 
       because [1]:CL*PK = AP*LQ  and [1]:NS*KC = NK*SM .
[1]:MQ*PK = QA*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:MS*QA = MQ*SN .
[1]:AB*LQ = CB*PK 
       because [1]:CL*PK = AP*LQ  and [1]:AB*MS = CB*SN .
[1]:QS*PK = QP*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:QS*PA = SM*QP .
[1]:PB*LQ = LB*PK 
       because [1]:CL*PK = AP*LQ  and [1]:PB*SM = LB*PA .
[1]:SC*PK = NH*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:MS*NH = NS*SC .
[1]:SA*PK = NA*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:MS*NA = NS*SA .
[1]:CA*PK = AH*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:MS*AH = CA*NS .
[1]:AM*PK = AS*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:AM*NS = SM*AS .
[1]:HB*LQ = AB*PK 
       because [1]:CL*PK = AP*LQ  and [1]:HB*MS = NS*AB .
[1]:AQ*PK = KB*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:MS*KB = NS*AQ .
[1]:LA*PK = PH*LQ 
       because [1]:CL*PK = AP*LQ  and [1]:LA*PA = LH*PH .
[1]:LA*BL = BM*LP 
       because $[LAP] sim [BML]$.
[1]:LA*ML = BM*AP 
       because $[LAP] sim [BML]$.
[1]:PS*BM = SQ*LA 
       because [1]:LA*BL = BM*LP  and [1]:PS*LB = PL*SQ .
[1]:LS*BM = LK*LA 
       because [1]:LA*BL = BM*LP  and [1]:LS*LB = LP*LK .
[1]:AC*BM = CB*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*CB = LB*AC .
[1]:MN*BM = MS*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*MS = LB*MN .
[1]:NC*BM = KC*LA 
       because [1]:LA*BL = BM*LP  and [1]:NC*LB = KC*PL .
[1]:NH*BM = KA*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*KA = LB*NH .
[1]:CH*BM = CA*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*CA = LB*CH .
[1]:SM*BM = AM*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*AM = LB*SM .
[1]:AH*BM = AB*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*AB = LB*AH .
[1]:AS*BM = AQ*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*AQ = LB*AS .
[1]:PB*BM = SB*BL 
       because [1]:LA*BL = BM*LP  and [1]:PB*LA = PL*SB .
[1]:PH*BM = SB*LA 
       because [1]:LA*BL = BM*LP  and [1]:PL*SB = LB*PH .
[1]:SC*BM = QC*LA 
       because [1]:LA*BL = BM*LP  and [1]:LP*QC = LB*SC .
[1]:SB*LH = BM*AP 
       because [1]:LA*ML = BM*AP  and [1]:SB*LH = LA*SH .
[1]:NA*BM = SB*ML 
       because [1]:LA*ML = BM*AP  and [1]:NS*SB = NA*LA .
[1]:AM*PH = BM*AP 
       because [1]:LA*ML = BM*AP  and [1]:LA*SH = AM*PH .
[1]:QP*BM = SB*SQ 
       because [1]:PS*BM = SQ*LA  and [1]:QP*LA = AN*SB .
[1]:AQ*PH = PS*BM 
       because [1]:PS*BM = SQ*LA  and [1]:KH*LA = AQ*PH .
[1]:AQ*BM = SB*LK 
       because [1]:LS*BM = LK*LA  and [1]:AQ*LA = AM*SB .
[1]:PH*LK = SH*BM 
       because [1]:LS*BM = LK*LA  and [1]:LA*SH = AM*PH .
[1]:AB*BM = SB*CB 
       because [1]:AC*BM = CB*LA  and [1]:AB*LA = CA*SB .
[1]:HA*BM = PH*CB 
       because [1]:AC*BM = CB*LA  and [1]:CA*PH = LA*HA .
[1]:NK*BM = SB*KC 
       because [1]:NC*BM = KC*LA  and [1]:NK*LA = NC*SB .
[1]:SC*SB = NH*BM 
       because [1]:NH*BM = KA*LA  and [1]:KA*LA = SC*SB .
[1]:PH*QC = NH*BM 
       because [1]:NH*BM = KA*LA  and [1]:LA*KA = PH*QC .
[1]:AH*BM = SB*CA 
       because [1]:CH*BM = CA*LA  and [1]:AH*LA = HC*SB .
[1]:SB*AM = SH*BM 
       because [1]:SM*BM = AM*LA  and [1]:SB*LH = LA*SH .
[1]:SB*AB = BH*BM 
       because [1]:AH*BM = AB*LA  and [1]:SB*AH = LA*BH .
[1]:KH*BM = SB*AQ 
       because [1]:AS*BM = AQ*LA  and [1]:KH*LA = SH*SB .
[1]:KA*BM = QC*SB 
       because [1]:PB*BM = SB*BL  and [1]:PB*QC = KA*LB .
[1]:PK*BM = LQ*SB 
       because [1]:PB*BM = SB*BL  and [1]:PB*LQ = LB*PK .
[1]:BK*NL = AN*KM 
       because $[BKM] sim [ANL]$.
[1]:BM*NL = AL*KM 
       because $[BKM] sim [ANL]$.
[1]:PL*KM = LB*NL 
       because [1]:BK*NL = AN*KM  and [1]:PS*LB = PL*SQ .
[1]:MS*NL = MN*KM 
       because [1]:BK*NL = AN*KM  and [1]:MS*NA = SQ*MN .
[1]:MQ*NL = MA*KM 
       because [1]:BK*NL = AN*KM  and [1]:MQ*AN = QS*MA .
[1]:NC*KM = KC*NL 
       because [1]:BK*NL = AN*KM  and [1]:NC*SQ = KC*NA .
[1]:AC*KM = CB*NL 
       because [1]:BK*NL = AN*KM  and [1]:AC*QS = CB*AN .
[1]:KA*NL = NH*KM 
       because [1]:BK*NL = AN*KM  and [1]:KA*NA = KH*NH .
[1]:CA*NL = CH*KM 
       because [1]:BK*NL = AN*KM  and [1]:KH*CH = NA*CA .
[1]:SM*KM = AM*NL 
       because [1]:BK*NL = AN*KM  and [1]:SM*KH = AM*NA .
[1]:HA*KM = AB*NL 
       because [1]:BK*NL = AN*KM  and [1]:NA*AB = HA*KB .
[1]:SA*KM = AQ*NL 
       because [1]:BK*NL = AN*KM  and [1]:SA*KB = NA*AQ .
[1]:AS*NL = NS*KM 
       because [1]:BK*NL = AN*KM  and [1]:SQ*NS = NA*AS .
[1]:SB*NL = PH*KM 
       because [1]:BK*NL = AN*KM  and [1]:SQ*PH = NA*SB .
[1]:QC*NL = SC*KM 
       because [1]:BK*NL = AN*KM  and [1]:QS*SC = AN*QC .
[1]:QP*NL = PK*AN 
       because [1]:BK*NL = AN*KM  and [1]:QS*PK = QP*LQ .
[1]:SA*PK = BK*NL 
       because [1]:BK*NL = AN*KM  and [1]:SA*PK = NA*LQ .
[1]:PK*AL = SB*NL 
       because [1]:BM*NL = AL*KM  and [1]:PK*BM = LQ*SB .
[1]:PB*NL = PK*PL 
       because [1]:PL*KM = LB*NL  and [1]:PB*LQ = LB*PK .
[1]:AP*NL = PK*MN 
       because [1]:MS*NL = MN*KM  and [1]:CL*PK = AP*LQ .
[1]:QA*NL = PK*MA 
       because [1]:MQ*NL = MA*KM  and [1]:MQ*PK = QA*LQ .
[1]:NK*NL = PK*NC 
       because [1]:NC*KM = KC*NL  and [1]:NK*LQ = KC*PK .
[1]:AB*NL = PK*AC 
       because [1]:AC*KM = CB*NL  and [1]:AB*LQ = CB*PK .
[1]:SC*PK = KA*NL 
       because [1]:KA*NL = NH*KM  and [1]:SC*PK = NH*LQ .
[1]:AH*NL = PK*CH 
       because [1]:CA*NL = CH*KM  and [1]:CA*PK = AH*LQ .
[1]:AS*NL = PK*SM 
       because [1]:SM*KM = AM*NL  and [1]:AM*PK = AS*LQ .
[1]:HB*NL = PK*HA 
       because [1]:HA*KM = AB*NL  and [1]:HB*LQ = AB*PK .
[1]:NA*NL = PK*NS 
       because [1]:AS*NL = NS*KM  and [1]:SA*PK = NA*LQ .
[1]:PA*NB = NA*PC 
       because $[PAC] sim [NAB]$.
[1]:PC*AB = NB*AC 
       because $[PAC] sim [NAB]$.
[1]:MS*NB = SA*PC 
       because [1]:PA*NB = NA*PC  and [1]:MS*NA = NS*SA .
[1]:HB*PC = AH*NB 
       because [1]:PA*NB = NA*PC  and [1]:HB*NS = NA*AH .
[1]:HC*NB = AH*PC 
       because [1]:PA*NB = NA*PC  and [1]:HC*NA = AH*NS .
[1]:SQ*PC = AS*NB 
       because [1]:PA*NB = NA*PC  and [1]:SQ*NS = NA*AS .
[1]:KA*PC = SC*NB 
       because [1]:PA*NB = NA*PC  and [1]:KA*NS = SC*NA .
[1]:AQ*PC = AM*NB 
       because [1]:PA*NB = NA*PC  and [1]:AQ*NS = AM*NA .
[1]:NS*PC = NM*NB 
       because [1]:PA*NB = NA*PC  and [1]:NS*NS = NM*NA .
[1]:NK*PC = NC*NB 
       because [1]:PA*NB = NA*PC  and [1]:NK*NS = NC*NA .
[1]:QP*PC = AN*NB 
       because [1]:PA*NB = NA*PC  and [1]:QP*NS = AN*NA .
[1]:PB*PC = PL*NB 
       because [1]:PA*NB = NA*PC  and [1]:PB*NS = PL*NA .
[1]:LA*NB = SB*PC 
       because [1]:PA*NB = NA*PC  and [1]:NS*SB = NA*LA .
[1]:PK*PC = NL*NB 
       because [1]:PA*NB = NA*PC  and [1]:NA*NL = PK*NS .
peano% 