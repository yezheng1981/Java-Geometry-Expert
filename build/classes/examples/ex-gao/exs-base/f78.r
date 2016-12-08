***************************************

 The Geometry Data Base  at level (1)
***************************************
midpoint[G,BC]
midpoint[N,AB]
       because para[$AC,NG$].
There are 2 midpoints

The configuration contains the following lines
line [1]B C G 
line [1]O G E H 
line [0]A B K 
line [1]E K 
line [1]A H 
line [1]C O 
line [1]B O 
line [1]C E 
line [1]B E 
line [1]C H 
line [1]B H 
line [1]A C 
line [1]A O 
line [1]A E 
line [0]K N 
line [1]G K 
line [1]G N 
line [1]H N 
line [1]K H 
line [1]A G 
line [0]A K N 
       because para[$KN,KA$].
line [1]A B K N 
       because line [0]A B K  and line [0]A K N .
line [1]C N 
line [1]O N 
line [1]O K 
line [1]E N 
There are 23 lines

The configuration contains the following parallel lines
pline [1]: line [1]B C G ; line [1]A H ; 
       because $[EK,BC]=[EK,AH]$.
pline [0]: line [0]K N ; line [0]A B K ; 
       because tline [0]line [0]K N  perp line [1]E K  and tline [0]line [1]E K  perp line [0]A B K .
pline [1]: line [1]A C ; line [1]G N ; 
       because $[OG,AC]=[OG,GN]$.
pline [0]: line [0]K N ; line [1]A B K N ; 
       because pline [0]: line [0]K N ; line [0]A B K ;  and line [1]A B K N .
pline [0]: line [1]A B K N ; 
       because pline [0]: line [0]K N ; line [1]A B K N ;  and line [1]A B K N .
pline [1]: line [1]O N ; line [1]E K ; 
       because para[$BC,AH$] and $[BC,ON]=[AH,EK]$.
There are 3 sets of parallel lines

The configuration contains the following perp lines
tline [0]line [1]E K  perp line [0]A B K 
tline [1]line [1]A H  perp line [1]O G E H 
tline [1]line [1]B C G  perp line [1]O G E H 
       because circle[$(O)BC$] and midpoint[$G,BC$].
tline [1]line [1]B E  perp line [1]K H 
       because perp[$OG,AH$] and $[OG,BE]=[AH,KH]$.
tline [1]line [1]A E  perp line [1]G K 
       because perp[$BC,OG$] and $[BC,AE]=[OG,GK]$.
tline [0]line [0]K N  perp line [1]E K 
       because circle[$(N)KGH$] and $[EK,KH]=[KG,GH]$.
tline [1]line [1]E K  perp line [1]A B K N 
       because tline [0]line [1]E K  perp line [0]A B K  and line [1]A B K N .
tline [1]line [1]A B K N  perp line [1]E K 
       because tline [0]line [0]K N  perp line [1]E K  and line [1]A B K N .
tline [1]line [1]A B K N  perp line [1]O N 
       because circle[$(O)AB$] and midpoint[$N,AB$].
There are 7 pair of perpendicular lines

The configuration contains the following circles
cir [0]circle[(O)ABC]
cir [0]circle[(O)BE]
cir [1]circle[(O)ABCE]
       because cir [0]circle[(O)ABC] and cir [0]circle[(O)BE].
cir [1]circle[(N)GKH]
cir [1]circle[(E)BC]
       because coll[$EGO$].
cir [1]circle[(H)BC]
       because coll[$HGO$].
cir [1]circle[AEKH]
       because perp[$KA,KE$] and perp[$HA,HE$].
cir [1]circle[BGEK]
       because perp[$KB,KE$] and perp[$GB,GE$].
cir [1]circle[BOGN]
       because $[OG,OB]=[NG,NB]$.
cir [1]circle[AOHN]
       because $[HO,HN]=[AO,AN]$.
There are 8 circles


The configuration contains the following eqangles
[1][OC,OG] = [OG,OB]
       because circle[$(O)BC$] and midpoint[$G,BC$].
[1][CO,CB] = [BC,BO]
[1][CO,CE] = [BE,BO]
       because coll[$GOE$].
[1][EC,EO] = [EO,EB]
       because [1][CO,CE] = [BE,BO] and [1][OC,OG] = [OG,OB].
[1][CE,CB] = [BC,BE]
       because [1][CO,CE] = [BE,BO] and [1][CO,CB] = [BC,BO].
[1][CO,CH] = [BH,BO]
       because coll[$GOH$].
[1][HC,HO] = [HO,HB]
       because [1][CO,CH] = [BH,BO] and [1][OC,OG] = [OG,OB].
[1][CH,CB] = [BC,BH]
       because [1][CO,CH] = [BH,BO] and [1][CO,CB] = [BC,BO].
[1][CE,CH] = [BH,BE]
       because [1][CO,CH] = [BH,BO] and [1][CO,CE] = [BE,BO].
[1][OG,OB] = [AC,AB]
       because circle[$(O)BCA$].
[1][OG,OC] = [AB,AC]
       because [1][OG,OB] = [AC,AB] and [1][OC,OG] = [OG,OB].
[1][BO,BA] = [AB,AO]
       because circle[$(O)AB$].
[1][OG,AC] = [AB,AO]
       because [1][BO,BA] = [AB,AO] and [1][OG,OB] = [AC,AB].
[1][CO,CA] = [AC,AO]
       because [1][OG,AC] = [AB,AO] and [1][OG,OC] = [AB,AC].
[1][CB,CA] = [EB,EA]
       because circle[$ABCE$].
[1][CB,CE] = [AE,AC]
       because [1][CB,CA] = [EB,EA] and [1][CE,CB] = [BC,BE].
[1][BC,BA] = [EC,EA]
       because circle[$ACBE$].
[1][BC,BE] = [AE,AB]
       because [1][BC,BA] = [EC,EA] and [1][CE,CB] = [BC,BE].
[1][AB,AC] = [EB,EC]
       because [1][BC,BA] = [EC,EA] and [1][CB,CA] = [EB,EA].
[1][AB,AE] = [AE,AC]
       because [1][BC,BA] = [EC,EA] and [1][CB,CE] = [AE,AC].
[1][OG,OB] = [EC,EB]
       because [1][AB,AC] = [EB,EC] and [1][OG,OB] = [AC,AB].
[1][OG,OC] = [EB,EC]
       because [1][AB,AC] = [EB,EC] and [1][OG,OC] = [AB,AC].
[1][EO,EA] = [AE,AO]
       because [1][AB,AE] = [AE,AC] and [1][OG,AC] = [AB,AO].
[1][EO,EC] = [CE,CO]
       because [1][OG,OB] = [EC,EB] and [1][CO,CE] = [BE,BO].
[1][EO,EB] = [BE,BO]
       because [1][OG,OB] = [EC,EB] and [1][EC,EO] = [EO,EB].
[1][OG,AB] = [AH,EK]
       because perp[$EK,AB$] and perp[$AH,OG$].
[1][EK,AH] = [CA,CO]
       because [1][OG,AB] = [AH,EK] and [1][OG,OC] = [AB,AC].
[1][EK,AH] = [AO,AC]
       because [1][OG,AB] = [AH,EK] and [1][OG,AC] = [AB,AO].
[1][KA,KG] = [GK,GN]
       because circle[$(N)GK$].
[1][GN,GO] = [HO,HN]
       because circle[$(N)GH$].
[1][CO,GN] = [HN,BO]
       because [1][GN,GO] = [HO,HN] and [1][OC,OG] = [OG,OB].
[1][CE,GN] = [HN,BE]
       because [1][GN,GO] = [HO,HN] and [1][EC,EO] = [EO,EB].
[1][CH,GN] = [HN,HB]
       because [1][GN,GO] = [HO,HN] and [1][HC,HO] = [HO,HB].
[1][GN,GB] = [BC,HN]
       because [1][CO,GN] = [HN,BO] and [1][CO,CB] = [BC,BO].
[1][KA,KH] = [HK,HN]
       because circle[$(N)KH$].
[1][BC,EK] = [OG,AB]
       because perp[$EK,AB$] and perp[$BC,OG$].
[1][BC,EK] = [CO,CA]
       because [1][BC,EK] = [OG,AB] and [1][OG,OC] = [AB,AC].
[1][BC,EK] = [AC,AO]
       because [1][BC,EK] = [OG,AB] and [1][OG,AC] = [AB,AO].
[1][EO,EA] = [EB,EK]
       because [1][BC,EK] = [OG,AB] and [1][BC,BE] = [AE,AB].
[1][EK,BC] = [EK,AH]
       because [1][BC,EK] = [OG,AB] and [1][OG,AB] = [AH,EK].
[1][BA,BC] = [AB,AH]
       because [1][BC,EK] = [OG,AB] and [1][OG,AB] = [AH,EK].
[1][BC,BO] = [AC,EK]
       because [1][BC,EK] = [CO,CA] and [1][CO,CB] = [BC,BO].
[1][EK,EC] = [AE,CO]
       because [1][BC,EK] = [CO,CA] and [1][CB,CE] = [AE,AC].
[1][CO,CB] = [CO,AH]
       because [1][BC,EK] = [CO,CA] and [1][EK,AH] = [CA,CO].
[1][EK,AO] = [EB,EA]
       because [1][BC,EK] = [AC,AO] and [1][CB,CA] = [EB,EA].
[1][CA,CB] = [AC,AH]
       because [1][BC,EK] = [AC,AO] and [1][EK,AH] = [AO,AC].
[1][EO,EC] = [EK,EA]
       because [1][EO,EA] = [EB,EK] and [1][EC,EO] = [EO,EB].
[1][EK,EA] = [BO,BE]
       because [1][EO,EA] = [EB,EK] and [1][EO,EB] = [BE,BO].
[1][AB,AE] = [BE,AH]
       because [1][EO,EA] = [EB,EK] and [1][OG,AB] = [AH,EK].
[1][AB,AH] = [EA,EC]
       because [1][BA,BC] = [AB,AH] and [1][BC,BA] = [EC,EA].
[1][BC,BO] = [CO,AH]
       because [1][BC,BO] = [AC,EK] and [1][EK,AH] = [CA,CO].
[1][BO,EK] = [EK,AO]
       because [1][BC,BO] = [AC,EK] and [1][BC,EK] = [AC,AO].
[1][EK,BO] = [AC,AH]
       because [1][BC,BO] = [AC,EK] and [1][EK,BC] = [EK,AH].
[1][AH,CE] = [AE,AC]
       because [1][EK,EC] = [AE,CO] and [1][EK,AH] = [CA,CO].
[1][AH,AC] = [EB,EA]
       because [1][EK,AO] = [EB,EA] and [1][EK,AH] = [AO,AC].
[1][BC,BA] = [EO,EK]
       because [1][EO,EC] = [EK,EA] and [1][BC,BA] = [EC,EA].
[1][CB,CE] = [BE,AH]
       because [1][AB,AE] = [BE,AH] and [1][BC,BA] = [EC,EA].
[1][BE,BC] = [BE,AH]
       because [1][AB,AE] = [BE,AH] and [1][BC,BE] = [AE,AB].
[1][AE,BC] = [AE,AH]
       because [1][AB,AE] = [BE,AH] and [1][BC,BE] = [AE,AB].
[1][CE,CB] = [CE,AH]
       because [1][AB,AH] = [EA,EC] and [1][BC,BA] = [EC,EA].
[1][EO,EK] = [AH,AB]
       because [1][AB,AH] = [EA,EC] and [1][EO,EC] = [EK,EA].
[1][CE,AH] = [AH,BE]
       because [1][AB,AH] = [EA,EC] and [1][AB,AE] = [BE,AH].
[1][BO,BC] = [BO,AH]
       because [1][BC,BO] = [CO,AH] and [1][CO,CB] = [BC,BO].
[1][CB,CH] = [HB,HA]
       because [1][BC,BO] = [CO,AH] and [1][CO,CH] = [BH,BO].
[1][GB,GN] = [HN,HA]
       because [1][BC,BO] = [CO,AH] and [1][CO,GN] = [HN,BO].
[1][CO,AH] = [AH,BO]
       because [1][BC,BO] = [CO,AH] and [1][CO,CB] = [CO,AH].
[1][HC,HA] = [HA,HB]
       because [1][CE,AH] = [AH,BE] and [1][CE,CH] = [BH,BE].
[1][GN,AH] = [HA,HN]
       because [1][CE,AH] = [AH,BE] and [1][CE,GN] = [HN,BE].
[1][BH,BC] = [HB,HA]
       because [1][CB,CH] = [HB,HA] and [1][CH,CB] = [BC,BH].
[1][CH,CB] = [HC,HA]
       because [1][CB,CH] = [HB,HA] and [1][CH,CB] = [BC,BH].
[1][HN,BC] = [HN,HA]
       because [1][GB,GN] = [HN,HA] and [1][GN,GB] = [BC,HN].
[1][GN,GB] = [GN,AH]
       because [1][GB,GN] = [HN,HA] and [1][GN,GB] = [BC,HN].
[1][EK,EA] = [HK,HA]
       because circle[$AKEH$].
[1][EO,EB] = [HA,HK]
       because [1][EK,EA] = [HK,HA] and [1][EO,EA] = [EB,EK].
[1][BC,AE] = [KH,KE]
       because [1][EK,EA] = [HK,HA] and [1][EK,BC] = [EK,AH].
[1][HA,HK] = [CO,CE]
       because [1][EK,EA] = [HK,HA] and [1][EK,EC] = [AE,CO].
[1][EO,EC] = [HK,HA]
       because [1][EK,EA] = [HK,HA] and [1][EO,EC] = [EK,EA].
[1][HA,HK] = [BE,BO]
       because [1][EK,EA] = [HK,HA] and [1][EK,EA] = [BO,BE].
[1][BA,BE] = [KE,KH]
       because [1][EK,EA] = [HK,HA] and [1][AB,AE] = [BE,AH].
[1][BO,AE] = [KH,AC]
       because [1][EK,EA] = [HK,HA] and [1][EK,BO] = [AC,AH].
[1][KE,KH] = [CA,CE]
       because [1][EK,EA] = [HK,HA] and [1][AH,CE] = [AE,AC].
[1][EO,EA] = [KH,KA]
       because [1][EK,EA] = [HK,HA] and [1][EO,EK] = [AH,AB].
[1][BC,KH] = [EO,EB]
       because [1][BC,AE] = [KH,KE] and [1][EO,EA] = [EB,EK].
[1][BC,KH] = [CO,CE]
       because [1][BC,AE] = [KH,KE] and [1][EK,EC] = [AE,CO].
[1][BC,KH] = [EC,EO]
       because [1][BC,AE] = [KH,KE] and [1][EO,EC] = [EK,EA].
[1][BC,KH] = [BE,BO]
       because [1][BC,AE] = [KH,KE] and [1][EK,EA] = [BO,BE].
[1][KH,BC] = [HK,HA]
       because [1][BC,AE] = [KH,KE] and [1][EK,EA] = [HK,HA].
[1][BC,BO] = [CE,KH]
       because [1][HA,HK] = [CO,CE] and [1][BC,BO] = [CO,AH].
[1][AH,BO] = [CE,KH]
       because [1][HA,HK] = [CO,CE] and [1][CO,AH] = [AH,BO].
[1][HO,HK] = [AC,AE]
       because [1][EO,EC] = [HK,HA] and [1][AH,CE] = [AE,AC].
[1][BC,BE] = [HO,HK]
       because [1][EO,EC] = [HK,HA] and [1][CB,CE] = [BE,AH].
[1][HO,HK] = [AH,BE]
       because [1][EO,EC] = [HK,HA] and [1][CE,AH] = [AH,BE].
[1][AB,AE] = [BO,KH]
       because [1][HA,HK] = [BE,BO] and [1][AB,AE] = [BE,AH].
[1][KA,KH] = [AO,AE]
       because [1][EO,EA] = [KH,KA] and [1][EO,EA] = [AE,AO].
[1][KA,KH] = [EK,EB]
       because [1][EO,EA] = [KH,KA] and [1][EO,EA] = [EB,EK].
[1][HO,HK] = [KH,BO]
       because [1][BC,BO] = [CE,KH] and [1][BC,KH] = [EC,EO].
[1][OG,AB] = [HN,BO]
       because [1][HO,HK] = [KH,BO] and [1][KA,KH] = [HK,HN].
[1][BA,BE] = [BE,HN]
       because [1][OG,AB] = [HN,BO] and [1][EO,EB] = [BE,BO].
[1][GO,GN] = [BO,BA]
       because [1][OG,AB] = [HN,BO] and [1][GN,GO] = [HO,HN].
[1][OG,AB] = [CO,GN]
       because [1][OG,AB] = [HN,BO] and [1][CO,GN] = [HN,BO].
[1][CE,GN] = [BE,BA]
       because [1][BA,BE] = [BE,HN] and [1][CE,GN] = [HN,BE].
[1][AB,AC] = [NA,NG]
       because [1][GO,GN] = [BO,BA] and [1][OG,OB] = [AC,AB].
[1][AO,BC] = [AO,AH]
       because para[$BC,AH$].
[1][GK,GB] = [GK,AH]
[1][GA,GB] = [AG,AH]
       because para[$GB,AH$].
[1][EO,EB] = [KG,KA]
       because circle[$BGEK$].
[1][EO,EC] = [KA,KG]
       because [1][EO,EB] = [KG,KA] and [1][EC,EO] = [EO,EB].
[1][BO,BE] = [GK,AC]
       because [1][EO,EB] = [KG,KA] and [1][OG,OB] = [AC,AB].
[1][BC,AE] = [GO,GK]
       because [1][EO,EB] = [KG,KA] and [1][BC,BE] = [AE,AB].
[1][GO,GK] = [CE,CA]
       because [1][EO,EB] = [KG,KA] and [1][AB,AC] = [EB,EC].
[1][KA,KG] = [CE,CO]
       because [1][EO,EB] = [KG,KA] and [1][OG,OC] = [EB,EC].
[1][KA,KG] = [BO,BE]
       because [1][EO,EB] = [KG,KA] and [1][EO,EB] = [BE,BO].
[1][KA,KG] = [EK,EA]
       because [1][EO,EB] = [KG,KA] and [1][EO,EA] = [EB,EK].
[1][GO,GK] = [AH,AE]
       because [1][EO,EB] = [KG,KA] and [1][AB,AE] = [BE,AH].
[1][BC,BE] = [KG,KE]
       because [1][EO,EB] = [KG,KA] and [1][BC,BA] = [EO,EK].
[1][EK,EB] = [GK,AH]
       because [1][EO,EB] = [KG,KA] and [1][EO,EK] = [AH,AB].
[1][KA,KG] = [HK,HA]
       because [1][EO,EB] = [KG,KA] and [1][EO,EB] = [HA,HK].
[1][GO,GK] = [KH,KE]
       because [1][EO,EB] = [KG,KA] and [1][BA,BE] = [KE,KH].
[1][EB,EA] = [KH,KG]
       because [1][EO,EB] = [KG,KA] and [1][EO,EA] = [KH,KA].
[1][BC,KH] = [KG,KA]
       because [1][EO,EB] = [KG,KA] and [1][BC,KH] = [EO,EB].
[1][CO,CA] = [CE,GK]
       because [1][EO,EC] = [KA,KG] and [1][OG,OC] = [AB,AC].
[1][CE,GK] = [AC,AO]
       because [1][EO,EC] = [KA,KG] and [1][OG,AC] = [AB,AO].
[1][OG,AC] = [BE,GK]
       because [1][EO,EC] = [KA,KG] and [1][AB,AC] = [EB,EC].
[1][EK,AH] = [GK,CE]
       because [1][EO,EC] = [KA,KG] and [1][OG,AB] = [AH,EK].
[1][BC,EK] = [CE,GK]
       because [1][EO,EC] = [KA,KG] and [1][BC,EK] = [OG,AB].
[1][EK,EA] = [GK,AC]
       because [1][BO,BE] = [GK,AC] and [1][EK,EA] = [BO,BE].
[1][HA,HK] = [AC,GK]
       because [1][BO,BE] = [GK,AC] and [1][HA,HK] = [BE,BO].
[1][BC,KH] = [AC,GK]
       because [1][BO,BE] = [GK,AC] and [1][BC,KH] = [BE,BO].
[1][KA,KG] = [GK,AC]
       because [1][GO,GK] = [CE,CA] and [1][EO,EC] = [KA,KG].
[1][AB,AO] = [BE,GK]
       because [1][KA,KG] = [BO,BE] and [1][BO,BA] = [AB,AO].
[1][BE,GK] = [KH,AE]
       because [1][KA,KG] = [BO,BE] and [1][AB,AE] = [BO,KH].
[1][EK,BO] = [KG,KH]
       because [1][KA,KG] = [BO,BE] and [1][KA,KH] = [EK,EB].
[1][EK,AO] = [KH,KG]
       because [1][KA,KG] = [EK,EA] and [1][KA,KH] = [AO,AE].
[1][AB,AE] = [KE,KG]
       because [1][BC,BE] = [KG,KE] and [1][BC,BE] = [AE,AB].
[1][GB,GK] = [EO,EA]
       because [1][BC,BE] = [KG,KE] and [1][EO,EA] = [EB,EK].
[1][GB,GK] = [AE,AO]
       because [1][BC,BE] = [KG,KE] and [1][EK,AO] = [EB,EA].
[1][EO,EA] = [AH,GK]
       because [1][EK,EB] = [GK,AH] and [1][EO,EA] = [EB,EK].
[1][AH,GK] = [AE,AO]
       because [1][EK,EB] = [GK,AH] and [1][EK,AO] = [EB,EA].
[1][GO,GK] = [GK,AO]
       because [1][CE,GK] = [AC,AO] and [1][GO,GK] = [CE,CA].
[0][AB,AB] = [GN,AC]
       because [1][KA,KG] = [GK,AC] and [1][KA,KG] = [GK,GN].
[1][OG,AB] = [GN,AO]
       because [1][GO,GK] = [GK,AO] and [1][KA,KG] = [GK,GN].
[1][AB,AE] = [AE,GN]
       because [0][AB,AB] = [GN,AC] and [1][AB,AE] = [AE,AC].
[0][AC,GN] = [GN,AC]
       because [0][AB,AB] = [GN,AC] and [1][AB,AC] = [NA,NG].
[0][AB,AB] = [AB,AB]
       because [0][AB,AB] = [GN,AC] and [1][AB,AC] = [NA,NG].
[1][HO,HN] = [AO,AB]
       because [1][OG,AB] = [GN,AO] and [1][GN,GO] = [HO,HN].
[1][BO,HN] = [AO,GN]
       because [1][OG,AB] = [GN,AO] and [1][OG,AB] = [HN,BO].
[1][BO,BA] = [AB,AO]
       because [1][OG,AB] = [GN,AO] and [1][GO,GN] = [BO,BA].
[1][CO,GN] = [GN,AO]
       because [1][OG,AB] = [GN,AO] and [1][OG,AB] = [CO,GN].
[0][KE,KA] = [KA,KE]
       because [1][BO,BA] = [AB,AO] and [1][BO,EK] = [EK,AO].
[1][EO,EK] = [AH,AB]
       because perp[$AH,OG$] and perp[$KN,EK$].
[1][AH,AB] = [EC,EA]
       because [1][EO,EK] = [AH,AB] and [1][EO,EC] = [EK,EA].
[1][OG,OB] = [AC,AB]
       because [1][EO,EK] = [AH,AB] and [1][EK,BO] = [AC,AH].
[1][BC,BA] = [AH,AB]
       because [1][EO,EK] = [AH,AB] and [1][BC,BA] = [EO,EK].
[1][AH,AB] = [AH,AB]
       because [1][EO,EK] = [AH,AB] and [1][EO,EK] = [AH,AB].
[1][EO,EA] = [KH,KA]
       because [1][EO,EK] = [AH,AB] and [1][EK,EA] = [HK,HA].
[1][EK,GN] = [BO,AH]
       because [1][EO,EK] = [AH,AB] and [1][GO,GN] = [BO,BA].
[1][EO,EB] = [KG,KA]
       because [1][EO,EK] = [AH,AB] and [1][EK,EB] = [GK,AH].
[1][EK,HN] = [AO,AH]
       because [1][EO,EK] = [AH,AB] and [1][HO,HN] = [AO,AB].
[1][AE,AB] = [AE,AB]
       because [1][AH,AB] = [EC,EA] and [1][AB,AH] = [EA,EC].
[1][AC,AE] = [AE,AB]
       because [1][AH,AB] = [EC,EA] and [1][AH,CE] = [AE,AC].
[1][EC,EB] = [AC,AB]
       because [1][AH,AB] = [EC,EA] and [1][AH,AC] = [EB,EA].
[1][BC,BE] = [AE,AB]
       because [1][AH,AB] = [EC,EA] and [1][CB,CE] = [BE,AH].
[1][BC,BA] = [EC,EA]
       because [1][AH,AB] = [EC,EA] and [1][AE,BC] = [AE,AH].
[1][AH,BE] = [AE,AB]
       because [1][AH,AB] = [EC,EA] and [1][CE,AH] = [AH,BE].
[1][BO,KH] = [AB,AE]
       because [1][AH,AB] = [EC,EA] and [1][AH,BO] = [CE,KH].
[1][AH,GN] = [EB,EA]
       because [1][AH,AB] = [EC,EA] and [1][CE,GN] = [BE,BA].
[1][KE,KG] = [AB,AE]
       because [1][AH,AB] = [EC,EA] and [1][EK,AH] = [GK,CE].
[1][OG,AB] = [CE,GK]
       because [1][AH,AB] = [EC,EA] and [1][EO,EA] = [AH,GK].
[1][AH,CE] = [AE,GN]
       because [1][AH,AB] = [EC,EA] and [1][AB,AE] = [AE,GN].
[1][OG,OC] = [AB,AC]
       because [1][OG,OB] = [AC,AB] and [1][OC,OG] = [OG,OB].
[1][AC,AB] = [AC,AB]
       because [1][OG,OB] = [AC,AB] and [1][OG,OB] = [AC,AB].
[1][BO,BA] = [BO,BA]
       because [1][OG,OB] = [AC,AB] and [1][OG,OB] = [AC,AB].
[1][AB,AO] = [BO,BA]
       because [1][OG,OB] = [AC,AB] and [1][OG,AC] = [AB,AO].
[1][AC,OG] = [HO,HN]
       because [1][OG,OB] = [AC,AB] and [1][OG,AB] = [HN,BO].
[1][OG,AC] = [GO,GN]
       because [1][OG,OB] = [AC,AB] and [1][GO,GN] = [BO,BA].
[1][AB,AC] = [NA,NG]
       because [1][OG,OB] = [AC,AB] and [1][GO,GN] = [BO,BA].
[1][BO,AC] = [BO,GN]
       because [1][OG,OB] = [AC,AB] and [1][GO,GN] = [BO,BA].
[1][BO,BA] = [BE,GK]
       because [1][OG,OB] = [AC,AB] and [1][OG,AC] = [BE,GK].
[1][OG,OB] = [NG,NA]
       because [1][OG,OB] = [AC,AB] and [0][AB,AB] = [GN,AC].
[1][BO,HN] = [AO,AC]
       because [1][OG,OB] = [AC,AB] and [1][HO,HN] = [AO,AB].
[1][OG,AC] = [AB,AO]
       because [1][OG,OB] = [AC,AB] and [1][BO,BA] = [AB,AO].
[1][GB,GK] = [KH,KA]
       because [1][BC,BA] = [AH,AB] and [1][KA,KG] = [HK,HA].
[1][AH,GK] = [KH,KA]
       because [1][AH,AB] = [AH,AB] and [1][KA,KG] = [HK,HA].
[1][AO,AE] = [KA,KH]
       because [1][EO,EA] = [KH,KA] and [1][EO,EA] = [AE,AO].
[1][EO,EA] = [HN,HK]
       because [1][EO,EA] = [KH,KA] and [1][KA,KH] = [HK,HN].
[1][EK,EB] = [KA,KH]
       because [1][EO,EA] = [KH,KA] and [1][EO,EA] = [EB,EK].
[1][KH,KA] = [KH,KA]
       because [1][EO,EA] = [KH,KA] and [1][EO,EA] = [KH,KA].
[1][BO,KH] = [AE,GN]
       because [1][EO,EA] = [KH,KA] and [1][GO,GN] = [BO,BA].
[1][HO,HK] = [GN,AE]
       because [1][EO,EA] = [KH,KA] and [1][AB,AE] = [AE,GN].
[1][AO,KH] = [AE,HN]
       because [1][EO,EA] = [KH,KA] and [1][HO,HN] = [AO,AB].
[1][GB,GN] = [BO,EK]
       because [1][EK,GN] = [BO,AH] and [1][EK,BC] = [EK,AH].
[1][CB,CA] = [AH,GN]
       because [1][EK,GN] = [BO,AH] and [1][BC,BO] = [AC,EK].
[1][CB,CO] = [EK,GN]
       because [1][EK,GN] = [BO,AH] and [1][BC,BO] = [CO,AH].
[1][EK,AO] = [AH,GN]
       because [1][EK,GN] = [BO,AH] and [1][BO,EK] = [EK,AO].
[1][AH,AC] = [AH,GN]
       because [1][EK,GN] = [BO,AH] and [1][EK,BO] = [AC,AH].
[1][EK,AC] = [EK,GN]
       because [1][EK,GN] = [BO,AH] and [1][EK,BO] = [AC,AH].
[1][BC,HN] = [EK,BO]
       because [1][EK,GN] = [BO,AH] and [1][GB,GN] = [HN,HA].
[1][EK,GN] = [AH,CO]
       because [1][EK,GN] = [BO,AH] and [1][CO,AH] = [AH,BO].
[1][EK,BO] = [HA,HN]
       because [1][EK,GN] = [BO,AH] and [1][GN,AH] = [HA,HN].
[1][EK,GN] = [KH,CE]
       because [1][EK,GN] = [BO,AH] and [1][AH,BO] = [CE,KH].
[1][BO,GK] = [BE,GN]
       because [1][EK,GN] = [BO,AH] and [1][EK,EB] = [GK,AH].
[1][AH,GN] = [KH,KG]
       because [1][EK,GN] = [BO,AH] and [1][EK,BO] = [KG,KH].
[1][CO,CE] = [KG,KA]
       because [1][EO,EB] = [KG,KA] and [1][OG,OC] = [EB,EC].
[1][EO,EB] = [GN,GK]
       because [1][EO,EB] = [KG,KA] and [1][KA,KG] = [GK,GN].
[1][EK,EA] = [KA,KG]
       because [1][EO,EB] = [KG,KA] and [1][EO,EA] = [EB,EK].
[1][GO,GK] = [HN,BE]
       because [1][EO,EB] = [KG,KA] and [1][BA,BE] = [BE,HN].
[1][GO,GK] = [CE,GN]
       because [1][EO,EB] = [KG,KA] and [1][CE,GN] = [BE,BA].
[1][KG,KA] = [KG,KA]
       because [1][EO,EB] = [KG,KA] and [1][EO,EB] = [KG,KA].
[1][BE,BA] = [BE,BA]
       because [1][EO,EB] = [KG,KA] and [1][EO,EB] = [KG,KA].
[1][KE,KH] = [BA,BE]
       because [1][EO,EB] = [KG,KA] and [1][GO,GK] = [KH,KE].
[1][AC,GK] = [KG,KA]
       because [1][EO,EB] = [KG,KA] and [1][OG,AC] = [BE,GK].
[1][BE,BA] = [GK,AO]
       because [1][EO,EB] = [KG,KA] and [1][GO,GK] = [GK,AO].
[1][BE,HN] = [AO,GK]
       because [1][EO,EB] = [KG,KA] and [1][HO,HN] = [AO,AB].
[1][AC,AH] = [HA,HN]
       because [1][EK,HN] = [AO,AH] and [1][EK,AH] = [AO,AC].
[1][CB,CA] = [HN,HA]
       because [1][EK,HN] = [AO,AH] and [1][BC,EK] = [AC,AO].
[1][BC,HN] = [AO,EK]
       because [1][EK,HN] = [AO,AH] and [1][EK,BC] = [EK,AH].
[1][HA,HN] = [EA,EB]
       because [1][EK,HN] = [AO,AH] and [1][EK,AO] = [EB,EA].
[1][HO,HN] = [AO,AB]
       because [1][EK,HN] = [AO,AH] and [1][EO,EK] = [AH,AB].
[1][GB,GN] = [EK,AO]
       because [1][EK,HN] = [AO,AH] and [1][GB,GN] = [HN,HA].
[1][HA,HN] = [KG,KH]
       because [1][EK,HN] = [AO,AH] and [1][EK,AO] = [KH,KG].
[1][AB,AE] = [AE,GN]
       because [1][AE,AB] = [AE,AB] and [1][AB,AE] = [AE,GN].
[1][AE,AC] = [AE,GN]
       because [1][AC,AE] = [AE,AB] and [1][AB,AE] = [AE,GN].
[1][CE,AB] = [CE,AB]
       because [1][EC,EB] = [AC,AB] and [1][AB,AC] = [EB,EC].
[1][CE,CA] = [HN,BE]
       because [1][EC,EB] = [AC,AB] and [1][BA,BE] = [BE,HN].
[1][CE,CA] = [CE,GN]
       because [1][EC,EB] = [AC,AB] and [1][CE,GN] = [BE,BA].
[1][BE,AC] = [BE,GN]
       because [1][EC,EB] = [AC,AB] and [1][CE,GN] = [BE,BA].
[1][NA,NG] = [EB,EC]
       because [1][EC,EB] = [AC,AB] and [0][AB,AB] = [GN,AC].
[1][BC,AE] = [HN,BE]
       because [1][BC,BE] = [AE,AB] and [1][BA,BE] = [BE,HN].
[1][BC,AE] = [CE,GN]
       because [1][BC,BE] = [AE,AB] and [1][CE,GN] = [BE,BA].
[1][BC,BE] = [GN,AE]
       because [1][BC,BE] = [AE,AB] and [1][AB,AE] = [AE,GN].
[1][BC,BA] = [BC,BA]
       because [1][BC,BA] = [EC,EA] and [1][BC,BA] = [EC,EA].
[1][BC,BA] = [EO,EK]
       because [1][BC,BA] = [EC,EA] and [1][EO,EC] = [EK,EA].
[1][BC,BA] = [AH,AB]
       because [1][BC,BA] = [EC,EA] and [1][AB,AH] = [EA,EC].
[1][BA,BC] = [AB,AH]
       because [1][BC,BA] = [EC,EA] and [1][AH,AB] = [EC,EA].
[1][KE,KG] = [AE,GN]
       because [1][AH,GN] = [EB,EA] and [1][EK,EB] = [GK,AH].
[1][BO,HN] = [GK,CE]
       because [1][OG,AB] = [CE,GK] and [1][OG,AB] = [HN,BO].
[1][CO,GN] = [CE,GK]
       because [1][OG,AB] = [CE,GK] and [1][OG,AB] = [CO,GN].
[1][OG,AB] = [OG,AB]
       because [1][OG,AB] = [CE,GK] and [1][EO,EC] = [KA,KG].
[1][OG,AB] = [AH,EK]
       because [1][OG,AB] = [CE,GK] and [1][EK,AH] = [GK,CE].
[1][BC,EK] = [OG,AB]
       because [1][OG,AB] = [CE,GK] and [1][BC,EK] = [CE,GK].
[1][CE,GK] = [GN,AO]
       because [1][OG,AB] = [CE,GK] and [1][OG,AB] = [GN,AO].
[1][CO,CA] = [HN,BO]
       because [1][OG,OC] = [AB,AC] and [1][OG,AB] = [HN,BO].
[1][CO,CA] = [CO,GN]
       because [1][OG,OC] = [AB,AC] and [1][OG,AB] = [CO,GN].
[1][OG,OC] = [NA,NG]
       because [1][OG,OC] = [AB,AC] and [0][AB,AB] = [GN,AC].
[1][CO,CA] = [GN,AO]
       because [1][OG,OC] = [AB,AC] and [1][OG,AB] = [GN,AO].
[1][AB,AC] = [NA,NG]
       because [1][AC,AB] = [AC,AB] and [0][AB,AB] = [GN,AC].
[1][HO,HN] = [BA,BO]
       because [1][BO,BA] = [BO,BA] and [1][OG,AB] = [HN,BO].
[1][AO,AB] = [AO,AB]
       because [1][AB,AO] = [BO,BA] and [1][BO,BA] = [AB,AO].
[1][GO,GN] = [AB,AO]
       because [1][AB,AO] = [BO,BA] and [1][GO,GN] = [BO,BA].
[1][CH,CA] = [HN,HB]
       because [1][AC,OG] = [HO,HN] and [1][HC,HO] = [HO,HB].
[1][NG,NA] = [NG,NA]
       because [1][AB,AC] = [NA,NG] and [0][AB,AB] = [GN,AC].
[1][AO,AC] = [AO,GN]
       because [1][BO,HN] = [AO,AC] and [1][BO,HN] = [AO,GN].
[1][HN,AC] = [NH,NG]
       because [1][BO,HN] = [AO,AC] and [1][BO,HN] = [AO,GN].
[1][BC,KH] = [GN,GK]
       because [1][GB,GK] = [KH,KA] and [1][KA,KG] = [GK,GN].
[1][GB,GK] = [HN,HK]
       because [1][GB,GK] = [KH,KA] and [1][KA,KH] = [HK,HN].
[1][EK,EB] = [HK,HN]
       because [1][EO,EA] = [HN,HK] and [1][EO,EA] = [EB,EK].
[1][KA,KH] = [HK,HN]
       because [1][EO,EA] = [HN,HK] and [1][EO,EA] = [KH,KA].
[1][KH,AC] = [KH,GN]
       because [1][BO,KH] = [AE,GN] and [1][BO,AE] = [KH,AC].
[1][CB,CA] = [GB,GN]
       because [1][GB,GN] = [BO,EK] and [1][BC,BO] = [AC,EK].
[1][GB,GN] = [AH,AC]
       because [1][GB,GN] = [BO,EK] and [1][EK,BO] = [AC,AH].
[1][CA,CB] = [BC,HN]
       because [1][CB,CA] = [AH,GN] and [1][GB,GN] = [HN,HA].
[1][GK,AC] = [GK,GN]
       because [1][BO,GK] = [BE,GN] and [1][BO,BE] = [GK,AC].
[1][KA,KG] = [GK,GN]
       because [1][BO,GK] = [BE,GN] and [1][KA,KG] = [BO,BE].
[1][CO,AB] = [CO,AB]
       because [1][CO,CE] = [KG,KA] and [1][KA,KG] = [CE,CO].
[1][BA,BE] = [BE,HN]
       because [1][GO,GK] = [HN,BE] and [1][EO,EB] = [KG,KA].
[1][NH,NA] = [NH,NA]
       because [1][HO,HN] = [AO,AB] and [1][HO,HN] = [AO,AB].
[1][CH,CA] = [CH,GN]
       because [1][CH,CA] = [HN,HB] and [1][CH,GN] = [HN,HB].
[1][BH,AC] = [BH,GN]
       because [1][CH,CA] = [HN,HB] and [1][CH,GN] = [HN,HB].
[1][AG,AC] = [GA,GN]
       because para[$AC,GN$].
[1][CN,CA] = [NC,NG]
       because para[$CA,NG$].
[1][OB,ON] = [ON,OA]
       because circle[$(O)AB$] and midpoint[$N,AB$].
[0][EK,ON] = [ON,EK]
       because [1][OB,ON] = [ON,OA] and [1][BO,EK] = [EK,AO].
[1][CB,CA] = [ON,OA]
       because circle[$(O)ABC$].
[1][BC,ON] = [OG,AB]
       because [1][CB,CA] = [ON,OA] and [1][OG,AC] = [AB,AO].
[1][BC,ON] = [CO,CA]
       because [1][CB,CA] = [ON,OA] and [1][CO,CA] = [AC,AO].
[1][EB,EA] = [ON,OA]
       because [1][CB,CA] = [ON,OA] and [1][CB,CA] = [EB,EA].
[1][BC,ON] = [AH,EK]
       because [1][CB,CA] = [ON,OA] and [1][EK,AH] = [AO,AC].
[1][BC,EK] = [BC,ON]
       because [1][CB,CA] = [ON,OA] and [1][BC,EK] = [AC,AO].
[1][AO,EK] = [OA,ON]
       because [1][CB,CA] = [ON,OA] and [1][BC,EK] = [AC,AO].
[1][AC,EK] = [AC,ON]
       because [1][CB,CA] = [ON,OA] and [1][BC,EK] = [AC,AO].
[1][EK,BO] = [OA,ON]
       because [1][CB,CA] = [ON,OA] and [1][BC,BO] = [AC,EK].
[1][AH,AC] = [ON,OA]
       because [1][CB,CA] = [ON,OA] and [1][CA,CB] = [AC,AH].
[1][BC,ON] = [CE,GK]
       because [1][CB,CA] = [ON,OA] and [1][CE,GK] = [AC,AO].
[1][OA,ON] = [KG,KH]
       because [1][CB,CA] = [ON,OA] and [1][BC,KH] = [AC,GK].
[1][AC,GK] = [AE,ON]
       because [1][CB,CA] = [ON,OA] and [1][GB,GK] = [AE,AO].
[1][BC,ON] = [HN,BO]
       because [1][CB,CA] = [ON,OA] and [1][BO,HN] = [AO,AC].
[1][AH,GN] = [ON,OA]
       because [1][CB,CA] = [ON,OA] and [1][CB,CA] = [AH,GN].
[1][HA,HN] = [OA,ON]
       because [1][CB,CA] = [ON,OA] and [1][CB,CA] = [HN,HA].
[1][BC,ON] = [CO,GN]
       because [1][CB,CA] = [ON,OA] and [1][CO,CA] = [GN,AO].
[1][BC,ON] = [GN,AO]
       because [1][CB,CA] = [ON,OA] and [1][AO,AC] = [AO,GN].
[1][BC,HN] = [OA,ON]
       because [1][CB,CA] = [ON,OA] and [1][CA,CB] = [BC,HN].
[1][CB,CA] = [OB,ON]
       because [1][CB,CA] = [ON,OA] and [1][OB,ON] = [ON,OA].
[1][EO,EA] = [BE,ON]
       because [1][BC,ON] = [OG,AB] and [1][BC,BE] = [AE,AB].
[1][GO,GK] = [KH,ON]
       because [1][BC,ON] = [OG,AB] and [1][BC,KH] = [KG,KA].
[1][CO,AE] = [CE,ON]
       because [1][BC,ON] = [CO,CA] and [1][CB,CE] = [AE,AC].
[1][AH,CO] = [ON,AC]
       because [1][BC,ON] = [CO,CA] and [1][CO,CB] = [CO,AH].
[1][AH,BO] = [AC,ON]
       because [1][BC,ON] = [CO,CA] and [1][BC,BO] = [CO,AH].
[1][CE,KH] = [AC,ON]
       because [1][BC,ON] = [CO,CA] and [1][BC,KH] = [CO,CE].
[1][EK,GN] = [ON,AC]
       because [1][BC,ON] = [CO,CA] and [1][CB,CO] = [EK,GN].
[1][AH,CO] = [NO,NG]
       because [1][BC,ON] = [CO,CA] and [1][GB,GN] = [AH,AC].
[1][EB,EK] = [BE,ON]
       because [1][EB,EA] = [ON,OA] and [1][EK,AO] = [EB,EA].
[1][EA,EK] = [AE,ON]
       because [1][EB,EA] = [ON,OA] and [1][EK,AO] = [EB,EA].
[1][BE,ON] = [KH,KA]
       because [1][EB,EA] = [ON,OA] and [1][KA,KH] = [AO,AE].
[1][AE,AB] = [GK,ON]
       because [1][EB,EA] = [ON,OA] and [1][AB,AO] = [BE,GK].
[1][GB,GK] = [BE,ON]
       because [1][EB,EA] = [ON,OA] and [1][GB,GK] = [AE,AO].
[1][AH,GK] = [BE,ON]
       because [1][EB,EA] = [ON,OA] and [1][AH,GK] = [AE,AO].
[1][BE,ON] = [HN,HK]
       because [1][EB,EA] = [ON,OA] and [1][AO,KH] = [AE,HN].
[1][OB,ON] = [EB,EA]
       because [1][EB,EA] = [ON,OA] and [1][OB,ON] = [ON,OA].
[1][HA,HK] = [AE,ON]
       because [1][BC,ON] = [AH,EK] and [1][BC,AE] = [KH,KE].
[1][AH,BO] = [NG,NO]
       because [1][BC,ON] = [AH,EK] and [1][GB,GN] = [BO,EK].
[1][BC,EK] = [AH,ON]
       because [1][BC,EK] = [BC,ON] and [1][EK,BC] = [EK,AH].
[1][BC,BA] = [OG,ON]
       because [1][BC,EK] = [BC,ON] and [1][BC,BA] = [EO,EK].
[1][BC,AE] = [KH,ON]
       because [1][BC,EK] = [BC,ON] and [1][BC,AE] = [KH,KE].
[1][GB,GN] = [OB,ON]
       because [1][BC,EK] = [BC,ON] and [1][GB,GN] = [BO,EK].
[1][ON,BC] = [ON,AH]
       because [1][BC,EK] = [BC,ON] and [1][BC,ON] = [AH,EK].
[1][EK,AC] = [NO,NG]
       because [1][AC,EK] = [AC,ON] and [1][EK,AC] = [EK,GN].
[1][BO,EK] = [OB,ON]
       because [1][EK,BO] = [OA,ON] and [1][BO,EK] = [EK,AO].
[1][OB,ON] = [KH,KG]
       because [1][EK,BO] = [OA,ON] and [1][EK,AO] = [KH,KG].
[1][HA,HN] = [ON,OB]
       because [1][EK,BO] = [OA,ON] and [1][EK,HN] = [AO,AH].
[1][OG,AB] = [AH,ON]
       because [1][AH,AC] = [ON,OA] and [1][OG,AC] = [AB,AO].
[1][AH,EK] = [AH,ON]
       because [1][AH,AC] = [ON,OA] and [1][EK,AH] = [AO,AC].
[1][AH,ON] = [CE,GK]
       because [1][AH,AC] = [ON,OA] and [1][CE,GK] = [AC,AO].
[1][EO,EC] = [ON,AE]
       because [1][BC,ON] = [CE,GK] and [1][BC,AE] = [GO,GK].
[1][KG,KE] = [GK,ON]
       because [1][BC,ON] = [CE,GK] and [1][BC,EK] = [CE,GK].
[1][AE,GN] = [ON,GK]
       because [1][BC,ON] = [CE,GK] and [1][BC,AE] = [CE,GN].
[1][CE,GN] = [KH,ON]
       because [1][BC,ON] = [CE,GK] and [1][BC,KH] = [GN,GK].
[1][BE,BA] = [KH,ON]
       because [1][OA,ON] = [KG,KH] and [1][AB,AO] = [BE,GK].
[1][KH,KE] = [KH,ON]
       because [1][OA,ON] = [KG,KH] and [1][EK,AO] = [KH,KG].
[1][AE,ON] = [KG,KA]
       because [1][OA,ON] = [KG,KH] and [1][AO,AE] = [KA,KH].
[1][HN,EK] = [NH,NO]
       because [1][BC,ON] = [HN,BO] and [1][BC,HN] = [EK,BO].
[1][GN,EK] = [NG,NO]
       because [1][AH,GN] = [ON,OA] and [1][EK,AO] = [AH,GN].
[1][CO,EK] = [OC,ON]
       because [1][BC,ON] = [CO,GN] and [1][CB,CO] = [EK,GN].
[1][ON,AC] = [NO,NG]
       because [1][BC,ON] = [CO,GN] and [1][BC,ON] = [CO,CA].
[1][EC,EK] = [CE,ON]
       because [1][CO,AE] = [CE,ON] and [1][EK,EC] = [AE,CO].
[1][HN,HK] = [KH,KA]
       because [1][BE,ON] = [HN,HK] and [1][BE,ON] = [KH,KA].
[1][OG,ON] = [AH,AB]
       because [1][BC,BA] = [OG,ON] and [1][BA,BC] = [AB,AH].
[1][EO,EK] = [OG,ON]
       because [1][BC,BA] = [OG,ON] and [1][BC,BA] = [EO,EK].
[1][GN,AE] = [AE,AB]
       because [1][AE,GN] = [ON,GK] and [1][AE,AB] = [GK,ON].
[1][HN,BE] = [BE,BA]
       because [1][BE,BA] = [KH,ON] and [1][BE,ON] = [HN,HK].
[1][GN,GK] = [KG,KA]
       because [1][AE,ON] = [KG,KA] and [1][AE,GN] = [ON,GK].
[1][CN,EK] = [NC,NO]
       because para[$ON,EK$].
[1][KO,KE] = [OK,ON]
       because para[$ON,KE$].
[1][EN,EK] = [NE,NO]
       because para[$NO,EK$].

There are 338 eqangles

The configuration contains the following congruent segments
con-seg[1]:BG = CG 
       because midpoint[$G,BC$].
con-seg[1]:AO = BO = CO = OE 
       because cir [1]circle[(O)ABCE].
con-seg[1]:GN = KN = HN 
       because cir [1]circle[(N)GKH].
con-seg[1]:BE = CE 
       because cir [1]circle[(E)BC].
con-seg[1]:BH = CH 
       because cir [1]circle[(H)BC].
con-seg[1]:BN = AN 
       because [0]:BN*GC = NA*BG.
There are 6 congruent segments

The configuration contains the following similar triangles
sim-tri[0]:[1,COE] [1,EOB] 
       because $[CO,OE]=[EO,OB]$ and $[OC,CE]=[OE,EB]$.
sim-tri[0]:[-1,CGE] [-1,AKE] 
       because $[GC,CE]=[KA,AE]$ and $[CG,GE]=[AK,KE]$.
sim-tri[0]:[1,BGE] [-1,AKE] 
       because $[GB,BE]=[EA,AK]$ and $[BG,GE]=[EK,KA]$.
sim-tri[1]:[1,BGE] [-1,AKE] [-1,CGE] 
       because sim-tri[0]:[-1,CGE] [-1,AKE]  and sim-tri[0]:[1,BGE] [-1,AKE] .
sim-tri[1]:[1,HEA] [-1,KEB] 
       because $[EH,HA]=[BK,KE]$ and $[HE,EA]=[BE,EK]$.
sim-tri[0]:[-1,KEH] [1,CEA] 
       because $[HE,EK]=[CE,EA]$ and $[EH,HK]=[CA,AE]$.
sim-tri[1]:[1,HGK] [-1,ABE] 
       because $[GH,HK]=[EA,AB]$ and $[HG,GK]=[EB,BA]$.
sim-tri[0]:[1,GEK] [1,CEA] 
       because $[EG,GK]=[EC,CA]$ and $[GE,EK]=[CE,EA]$.
sim-tri[1]:[1,GEK] [1,CEA] [-1,KEH] 
       because sim-tri[0]:[-1,KEH] [1,CEA]  and sim-tri[0]:[1,GEK] [1,CEA] .
sim-tri[1]:[1,KBG] [-1,HAK] 
       because $[BK,KG]=[KH,HA]$ and $[KB,BG]=[KA,AH]$.
sim-tri[1]:[1,EOA] [-1,KNH] 
       because $[OE,EA]=[HK,KN]$ and $[EO,OA]=[HN,NK]$.
sim-tri[0]:[1,EOB] [-1,KNG] 
       because $[OE,EB]=[GK,KN]$ and $[EO,OB]=[GN,NK]$.
sim-tri[1]:[1,EOB] [-1,KNG] [1,COE] 
       because sim-tri[0]:[1,COE] [1,EOB]  and sim-tri[0]:[1,EOB] [-1,KNG] .
sim-tri[1]:[1,GHN] [-1,BAO] 
       because $[HG,GN]=[OB,BA]$ and $[GH,HN]=[OA,AB]$.
There are 8 similar triangles

The configuration contains the following congruent triangles
con-tri[1]:[1,OCE] [1,OEB] 
       because $OC = OE$ and $[OCE] sim [OEB]$.
There are 1 congruent triangles

The configuration contains the following equal ratios
[1]:BG*AE = AK*BE
       because $[BGE] sim [AKE]$.
[1]:BG*KE = AK*GE
       because $[BGE] sim [AKE]$.
[1]:BE*KE = AE*GE
       because $[BGE] sim [AKE]$.
[1]:HE*KB = KE*HA
       because $[HEA] sim [KEB]$.
[1]:HE*EB = KE*EA
       because $[HEA] sim [KEB]$.
[1]:HA*EB = KB*EA
       because $[HEA] sim [KEB]$.
[1]:BG*HE = AK*KE
       because [1]:HE*EB = KE*EA and [1]:BG*AE = AK*BE.
[1]:GE*HE = KE*KE
       because [1]:HE*EB = KE*EA and [1]:BE*KE = AE*GE.
[1]:BG*HA = AK*KB
       because [1]:HA*EB = KB*EA and [1]:BG*AE = AK*BE.
[1]:GE*HA = KE*KB
       because [1]:HA*EB = KB*EA and [1]:BE*KE = AE*GE.
[1]:EH*AC = EA*HK
       because $[EHK] sim [EAC]$.
[1]:EK*AC = EC*HK
       because $[EHK] sim [EAC]$.
[1]:HG*AE = AB*HK
       because $[HGK] sim [ABE]$.
[1]:HG*BE = AB*GK
       because $[HGK] sim [ABE]$.
[1]:HK*BE = AE*GK
       because $[HGK] sim [ABE]$.
[1]:BG*HK = AK*GK
       because [1]:HK*BE = AE*GK and [1]:BG*AE = AK*BE.
[1]:GE*HK = KE*GK
       because [1]:HK*BE = AE*GK and [1]:BE*KE = AE*GE.
[1]:HE*GK = KE*HK
       because [1]:HK*BE = AE*GK and [1]:HE*EB = KE*EA.
[1]:HA*GK = KB*HK
       because [1]:HK*BE = AE*GK and [1]:HA*EB = KB*EA.
[1]:EK*AC = AE*GK
       because [1]:HK*BE = AE*GK and [1]:EK*AC = EC*HK.
[1]:EC*GK = AC*GE
       because [1]:GE*HK = KE*GK and [1]:EK*AC = EC*HK.
[1]:BA*BG = BN*BC
       because para[$AC,GN$].
[1]:BA*NG = BN*AC
       because para[$AC,GN$].
[1]:BC*NG = BG*AC
       because para[$AC,GN$].
[1]:BA*CG = AN*BC
       because para[$AC,GN$].
[0]:BN*GC = NA*BG
       because para[$AC,GN$].
[1]:EO*KH = KN*EA
       because $[EOA] sim [KNH]$.
[1]:HG*EO = AB*KN
       because [1]:EO*KH = KN*EA and [1]:HG*AE = AB*HK.
[1]:GK*EO = BE*KN
       because [1]:EO*KH = KN*EA and [1]:HK*BE = AE*GK.
[1]:BN*AC = HG*EO
       because [1]:HG*EO = AB*KN and [1]:BA*NG = BN*AC.
There are 29 ratios
The databse contains 431 (480214) datas

Proof costs  4.390625 seconds.

peano% 