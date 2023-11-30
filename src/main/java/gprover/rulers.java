package gprover;

public class rulers { // This should be "rules". FIXME.

    final public static String[] GDD_English = {
            "* 1",
            "If AB ∥ BC, then Collinear(A,B,C).",

            "* 2",
            "For two angles ∠[l1,l2], ∠[l3,l4]. If l1 = l3 and l2 ∥ l4, then ∠[l1,l2] = ∠[l3,l4].",
            "For example, in parallelogram ABCD, AD ∥ BC and AC = CA, hence ∠[CAD] = ∠[ACB].",

            "* 3",
            "if AB ∥ CD and E is the intersection of AC and BD, then EA / EC = EB / ED.",

            "* 4 ",
            "If AB ∥ CD and CD ⊥ EF, then AB ⊥ EF.",

            "* 5",
            "If AB ⊥ CD, then ∠[AB,CD] = [1] (or 90 degrees).",

            "* 6",
            "In right triangle ACB, ∠C = [1], if  midpoint(E, A B), then Circumcenter(E,A B C) and EA = EB = EC.",

            "* 7",
            "If AB ⊥ CD and CD ⊥ EF, then AB ∥ EF.",

            "* 8",
            "For four points A,B,C,D, if AC ⊥ BC and AD ⊥ BD, then Cyclic(A,B,C,D).",

            "* 9",
            "For a circle c(O,AB) and a point C on Circle c, if Collinear(A,B,O), then AC ⊥ BC and ∠ACB = [1].",

            "* 10",
            "If AB is the Diameter of a circle and point C is on the circle, then AC ⊥ BC",

            "* 11",
            "Angle of circumference equals to half of angle of center",

            "* 12",
            "If AB ∥ CD and Cyclic(A,B,C,D), then ∠ABC = ∠DAB.",

            "* 13",
            "If Cyclic(A,B,C,D), then ∠ADB = ∠ACB and vice verse.",

            "* 14",
            "Chord tangent angle ***** ",

            "* 15 ",
            "Line passing the centers of two circles is perpendicular to the common chord of two circles.  ",
            "For example, two circles C1(O,AB) and C2(O1,AB), AB are two common points, then OO1 ⊥ AB.",

            "* 16",
            "The Addition for Full Angle.",
            "If ∠[l1,l2] = ∠[l3,l4] and ∠[l5,l6] = ∠[l7,l8], and l2 = l6 and l4 = l7, then ∠[l1,l4] = ∠[l5,l8] ",

            "* 17",
            "ASPP12.",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ∥ l2, then l3 ∥ l4.",
            "Because if l1 ∥ l2, ∠[l1,l2] = [0]. Hence ∠[l3,l4] = [0].",

            "* 18",
            "ASPP13",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ∥ l3, then l2 ∥ l4.",
            " ",

            "* 19",
            "ASTT12",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ⊥ l2, then l3 ⊥ l4.",

            "* 20",
            "ASTT13",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ⊥ l3, then l2 ⊥ l4.",

            "* 21",
            "Special Angles",

            "* 22",
            "Supplementary Angle",

            "* 23",
            "Isosceles Triangle.",

            "* 24 Isosceles Triangle Theorem.",
            "For a isosceles triangle ABC and a point D on line BC, if any one of following statement is true, the other two are also true .",
            "1. AD ⊥ BC     2. D is the midpoint of BC   3. ∠BAD = ∠DAC",

            "* 25 ",
            "Congruent Triangle. ",
            "Triangles are called congruent if they have the same size and shape. That is, corresponding angles have the same measure, and corresponding sides have the same length.",

            "* 26  #ASA",
            "ASA (angle-side-angle) congruence: ",
            "Two triangles are congruent if a pair of corresponding angles and the included side are equal. ",

            "* 27 #SAS",
            "SAS(side-angle-side) congruence",
            "Two triangles are congruent if a pair of corresponding sides and the included angle are equal.",

            "* 28 #SSS",
            "SSS (side-side-side) congruence",
            "Two triangles are congruent if their corresponding sides are equal.",

            "* 29 #SAS",
            "SAS (side-angle-side) congruence for right triangles.",
            "Two right triangle are congruent if their two corresponding cartesian edges are equal. ",

            "* 30",
            "Similar triangles",
            "Similar triangles are triangles that have the same shape but possibly different size. In particular, corresponding angles are congruent, and corresponding sides are in proportion.",

            "* 31 #AAA",
            "AAA (angle-angle-angle) similarity",
            "If two triangles have two corresponding angles that are congruent, then the triangles are similar. Because the sum of the angles in a triangle must be PI, we really only need to know that two pairs of corresponding angles are congruent to know the triangles are similar.",

            "* 32 #SAS",
            "SAS (side-angle-side) similarity",
            "The side-angle-side (SAS) similarity test says that if two triangles have two pairs of sides that are proportional and the included angles are congruent, then the triangles are similar. ",

            "* 33 #SSS",
            "SSS (side-side-side) similarity",
            "If two triangles have all three pairs of sides in proportion, the triangles are similar.",

            "* 34",
            "Ratio by similarity",

            "* 35",
            "Middle Connection Theorem for Triangles",
            "The line connects the midpoints of two sides of triangle is parallel to the third side of the triangle.",

            "* 36",
            "The hypotenuse of a right triangle is the diameter of the circumscribed circle of the triangle, and the midpoint of the hypotenuse is the center of the circle.",
            "For a right triangle ACB, if AC ⊥ BC and D is the midpoint of AB,  then DC = DA = DB, D is the center of circle ABC.",

            "* 37",
            "Equilateral Triangles",
            "An equilateral triangle is a triangle in which all three sides have equal lengths.",

            "* 38",
            "Pythagorean Theorem",
            "In any right triangle, the area of the square whose side is the hypotenuse (the side of a right triangle opposite the right angle) is equal to the sum of areas of the squares whose sides are the two legs (i.e. the two sides other than the hypotenuse). ",

            "* 39",
            "The sum of three interior angles of a triangle is [0]. (or 180 degree for traditional angle).",

            "* 40",
            "Parallelogram ",
            "A parallelogram is a quadrilateral with two sets of opposite parallel sides.",

            "* 41",
            "Middle Connection Theorem for Trapezoid",

            "* 42",
            "Ratio ",
            "Use Ratio in Deductive Database Method. ",

            "* 43",
            "Ratio by angle bisector",
            "In triangle ABC, if AD is the bisector of angle A, and D is on line BC, then AB/BD = AC/DC. ",
            "*END",
    };

    final public static String[] FULL_English =
            {
                    "* 1 # The Definition of Full Angle.",
                    "A full angle is defined as an ordered pair of two lines u and v denoted by ∠[u,v].",

                    "* 2",
                    "∠[u,v] =  - ∠[v,u].",

                    "* 3",
                    "∠[u,v] = ∠[0] if and only if u ∥ v  (including u = v).",

                    "* 4",
                    "∠[u,v] = ∠[1] if and only if u ⊥ v.",

                    "* 5 # Addition of Full Angle.",
                    "∠[u,s] + ∠[s,v] = ∠[u,v].  This rule is to split an angle into two or more angles.",
                    "For example, for any line m, we have  ∠[u,v] =  ∠[u,m] +  ∠[m,v]",

                    "* 6",
                    "If l ⊥ v or ∠[l,v] = ∠[1],  then ∠[u,v] = ∠[u,l] + ∠[l,v] = ∠[u,l] + ∠[1].",
                    "For example, if we have AC ⊥ BC, then  ∠[CBA] = ∠[BAC] + ∠[1].",

                    "* 7",
                    " The Isosceles Triangle Theorem for Full Angle.",
                    " If AC = BC, we have ∠[ABC] = ∠[CAB]. Conversely, if ∠[ABC] = ∠[CAB], then AB = AC or A, B and C are collinear.",

                    "* 8",
                    " The Inscribed Angle Theorem.",
                    " Points A,B,C and D are cyclic iff ∠[ACB] = ∠[ADB].",

                    "* 9",
                    " If Cyclic(A,B,M,N),  then ∠[AB, AM] = ∠[BN,NM].  Hence we have  ∠[AB, CD] = ∠[AB, AM] + ∠[AM, CD] = ∠[BN,NM]] + ∠[AM, CD].",

                    "* 10",
                    " Rule 10.",

                    "* 11",
                    " If AB is the diameter of the circumcircle of triangle ABC and D is on the circumcircle, then for any line FG, we have ∠[CD,FG] = ∠[DBA] + ∠[ACB] + ∠[BC, FG]].",

                    "* 12",
                    "If O is the intersection of Circle(O1, O P1 P3 I) and Circle(O2, O P2 P4 J) and Collinear(O, I, J), then ∠[P1O, OP2] = ∠[P1P2, IP3] + ∠[JP4, P2P4].",

                    "* 13",
                    " If O is the circumcenter of triangle ABC, then ∠[BOC] = 2 * ∠[BAC].",

                    "* 14",
                    " For a circle(O, A B C D), if O is the intersection of AD and BC, then ∠[AOB] = 2 * ∠[CDA].",

                    "* 15",
                    " For a circle(O, A B), O is the center of circle, we have ∠[OAB] = ∠[ABO]. ",

                    "* 16",
                    " If triangle ABE is a right triangle and O is the midpoint of the hypotenuse(i.e. AB), then for any line CD, we have",
                    " ∠[AB, CD] =  ∠[BAE] + ∠[1] + ∠[BE, CD]  ",

                    "* 17",
                    " For a circle(O, ABC) and D is midpoint of BC, we have ∠[AB, EF] = ∠[BOD] + ∠[AC,EF]. ",

                    "* 18",
                    " For a circle(O,ABF) and E is the midpoint of AF, we have ∠[AB, CD] = ∠[EOA] + ∠[BF, CD].",

                    "* 19",
                    " If G is the orthocenter of A, B, C, ∠[AE, HI] = ∠[AE, BE] + ∠[BE, HI] = ∠[1] + ∠[BE, HI]",

                    "* 20",
                    "If A is the incenter of P2, K, I,  ∠[AB, CD] = ∠[AB, BK] + ∠[BK, CD] = ∠[1] + ∠[KI, KA] + ∠[IA, IK] + ∠[BK, CD].",

                    "* 21",
                    " Equal Angle found in Geometry Deductive Database.",

                    "*  22",
                    " If AB ⊥ AC and AB = AC, then ∠[AB,AC] = 2 * ∠[AB,BC].",

                    "*  23",
                    " If AB ⊥ AC and AB = AC, then 2 * ∠[AB,DE] = 2 * ∠[AB,BC] + 2 * ∠[BC,DE] = ∠[1] +2 * ∠[BC,DE].",

                    "*  24",
                    " If OB // DE and A is on Circle(O,B), then 2 * ∠[AB, DE] = ∠[OA,OB]",

                    "*  25",
                    " A is on Circle(O,B), then 2 * ∠[AB, DE] = ∠[OA,OB] + 2 * ∠[OA,DE]",

                    "*  26",
                    " CD is the diameter of Circle(A, BCD), AB ⊥ CD, then  2 * ∠[DB,BA]  = ∠[0].",

                    "*  27",
                    " A, B, C, D, E are cyclic and AC = AB, then 2*∠[AB, BC] =  ∠[CE, ED].",

                    "*  28",
                    " If A, B, E, F, G are cyclic and AE = AF, then 2 * ∠[AB, CD] =  2 * ∠[BE, CD] +  ∠[EG, GF].",

                    "*  29",
                    " If AB = AC = BC, i.e., triangle ABC is an equilateral triangle, then 3 * ∠[AB, BC] = ∠[0].  ",

                    "* END.",

            };

    final public static String[] GDD_Serbian = {
            "* 1",
            "Ako AB ∥ BC, onda Kolinearne(A,B,C).",

            "* 2",
            "Za dva ugla ∠[l1,l2], ∠[l3,l4]. Ako l1 = l3 i l2 ∥ l4, onda ∠[l1,l2] = ∠[l3,l4].",
            "Na primer, u paralelogramu ABCD, AD ∥ BC i AC = CA, pa ∠[CAD] = ∠[ACB].",

            "* 3",
            "Ako AB ∥ CD i E je presek AC i BD, onda EA / EC = EB / ED.",

            "* 4 ",
            "Ako AB ∥ CD i CD ⊥ EF, onda AB ⊥ EF.",

            "* 5",
            "Ako AB ⊥ CD, onda ∠[AB,CD] = [1] (ili 90 stepeni).",

            "* 6",
            "U pravouglom trouglu ACB, ∠C = [1], ako središte(E, A B), onda Circumcenter(E,A B C) i EA = EB = EC.",

            "* 7",
            "Ako AB ⊥ CD i CD ⊥ EF, onda AB ∥ EF.",

            "* 8",
            "Za četiri tačke A,B,C,D, ako AC ⊥ BC i AD ⊥ BD, onda Ciklične(A,B,C,D).",

            "* 9",
            "Za krug c(O,AB) i tačku C na krugu c, ako Kolinearne(A,B,O), onda AC ⊥ BC i ∠ACB = [1].",

            "* 10",
            "Ako je AB prečnik kruga i tačka C je na krugu, onda AC ⊥ BC",

            "* 11",
            "Ugao obima jednak je polovini ugla centra",

            "* 12",
            "Ako AB ∥ CD i Ciklične(A,B,C,D), onda ∠ABC = ∠DAB.",

            "* 13",
            "Ako Ciklične(A,B,C,D), onda ∠ADB = ∠ACB i obrnuto.",

            "* 14",
            "Ugao tangente tetive*** ",

            "* 15 ",
            "Linija koja prolazi kroz centre dva kruga je normalna na zajedničku horu dva kruga. ",
            "Na primer, dva kruga C1(O,AB) i C2(O1,AB), AB su dve zajedničke tačke, onda OO1 ⊥ AB.",

            "* 16",
            "Dodatak za puni ugao.",
            "Ako ∠[l1,l2] = ∠[l3,l4] i ∠[l5,l6] = ∠[l7,l8], i l2 = l6 i l4 = l7, onda ∠[l1,l4] = ∠[l5,l8] ",

            "* 17",
            "ASPP12.",
            "Ako ∠[l1,l2] = ∠[l3,l4] i l1 ∥ l2, onda l3 ∥ l4.",
            "Jer ako l1 ∥ l2, ∠[l1,l2] = [0]. Dakle ∠[l3,l4] = [0].",

            "* 18",
            "ASPP13",
            "Ako ∠[l1,l2] = ∠[l3,l4] i l1 ∥ l3, onda l2 ∥ l4.",
            " ",

            "* 19",
            "ASTT12",
            "Ako ∠[l1,l2] = ∠[l3,l4] i l1 ⊥ l2, onda l3 ⊥ l4.",

            "* 20",
            "ASTT13",
            "Ako ∠[l1,l2] = ∠[l3,l4] i l1 ⊥ l3, onda l2 ⊥ l4.",

            "* 21",
            "Posebni uglovi",

            "* 22",
            "Dopunski ugao",

            "* 23",
            "Jednokraki trougao.",

            "* 24 Teorema jednokrakog trougla.",
            "Za jednokraki trougao ABC i tačku D na pravoj BC, ako je bilo koja od sledećih tvrdnji tačna, druge dve su takođe tačne.",
            "1. AD ⊥ BC 2. D je sredina BC 3. ∠BAD = ∠DAC",

            "* 25 ",
            "Kongruentni trougao. ",
            "Trouglovi se nazivaju kongruentni ako imaju istu veličinu i oblik. To jest, odgovarajući uglovi imaju istu meru, a odgovarajuće stranice imaju istu dužinu. ",

            "* 26 #USU",
            "Teorema kongruencije USU (ugao–strani–ugao): ",
            "Dva trougla su podudarna ako su jedna ivica i na njoj nalegli uglovi jednog trougla podudarni sa odgovarajućom ivicom i odgovarajućim uglovima drugog trougla, ",

            "* 27 #SUS",
            "Teorema kongruencije SUS (strani-ugao-strani):",
            "Dva trougla su podudarna ako su dve ivice i njima zahvaćeni ugao jednog trougla podudarni sa odgovarajućim ivicama i uglovima drugog trougla.",

            "* 28 #SSS",
            "Teorema kongruencije SSS (strani-strani-strani):",
            "Dva trougla su kongruentna ako su im odgovarajuće stranice jednake.",

            "* 29 #SUS",
            "Teorema kongruencije SUS (strani-ugao-strani) za pravougle trouglove:",
            "Dva pravougla trougla su kongruentna ako su im dva odgovarajuća kartezijanska ruba jednaka.",

            "* 30",
            "Slicni trouglovi",
            "Slični trouglovi su trouglovi koji imaju istu formu, ali moguće različitu veličinu. Posebno, odgovarajući uglovi su kongruentni, a odgovarajuće stranice su proporcionalne.",

            "* 31 #UUU",
            "Sličnost trouglova po uglovima (ugao-ugao-ugao, UUU):",
            "Ako dva trougla imaju dva kongruentna odgovarajuća ugla, onda su trouglovi slični. Budući da je zbroja uglova u trouglu jednak PI, dovoljno je znati da su dva para odgovarajućih uglova kongruentna kako bi se utvrdilo da su trouglovi slični.",

            "* 32 #SUS",
            "Sličnost trouglova po SUS (strani-ugao-strani, SUS):",
            "Test sličnosti trouglova po strani-ugao-strani (SUS) kaže da, ako dva trougla imaju dvoje proporcionalne stranice i kongruentne uključen ugao, onda su trouglovi slični.",

            "* 33 #SSS",
            "Sličnost trouglova po SSS (strani-strani-strani):",
            " Ako su dve stranice svakog para stranica dvaju trougla proporcionalne, onda su trouglovi slični.",

            "* 34",
            "Odnos sličnosti",

            "* 35",
            "Teorema spojnice središta trougla",
            "Prava koja spaja središta dve stranice trougla je paralelna trećoj stranici trougla.",

            "* 36",
            "Hipotenuza pravouglog trougla predstavlja promjera opisanog kruga, a središte kruga je u središtu hipotenuze. (vidi Talesova teorema)",
            "Za pravi trougao ACB, Ako AC ⊥ BC i D je središte stranice AB, onda je DC = DA = DB, a točka D je središte kružnice opisane oko trougla ABC.",

            "* 37",
            "Jednakostranični trougao",
            "Jednakostranični trougao je trougao u kojem su sve tri stranice jednake dužine.",

            "* 38",
            "Pitagorina teorema",
            "U bilo kojem pravougli trouglu, površina kvadrata čija stranica je hipotenuza (stranica pravougli trougla nasuprot pravog kuta) jednaka je zbir površina kvadrata čije su stranice katete (dve preostale stranice pravougli trougla). ",

            "* 39",
            "Zbir mjera unutarnjih kutova u trouglu iznosi [0]. (ili 180° prema tradicionalnoj mjeri kuta).",

            "* 40",
            "Paralelogram ",
            "Paralelogram je četverokut s dvije suprotne paralelne stranice.",

            "* 41",
            "Teorema srednja linija trapeza",

            "* 42",
            "Razmera ??? ",
            "Razmeru koristimo u deduktivnoj metodi baze podataka. ",

            "* 43",
            "Odnos prema simetrali ugla",
            "U trouglu ABC, ako je AD polovina ugla A, a D leži na liniji BC, onda važi AB/BD = AC/DC.",
            "*END",
    };

    final public static String[] FULL_Serbian =
            {
                    "* 1 # Definicija pune veličine ugla.",
                    "Puna veličina ugla se definiše kao uređeni par dviju linija u i v, označen kao ∠[u,v].",

                    "* 2",
                    "∠[u,v] = - ∠[v,u].",

                    "* 3",
                    "∠[u,v] = ∠[0] Ako i samo ako u ∥ v (uključujući u = v).",

                    "* 4",
                    "∠[u,v] = ∠[1] ako i samo ako u ⊥ v.",

                    "* 5 #Dodavanje pune ugla.",
                    "∠[u,s] + ∠[s,v] = ∠[u,v]. (Ovo pravilo služi da bi se ugao podijelio na dva ili više manjih uglova.)",
                    "Na primer, za bilo koju liniju m, imamo ∠[u,v] = ∠[u,m] + ∠[m,v]",

                    "* 6",
                    "Ako l ⊥ v ili ∠[l,v] = ∠[1], onda ∠[u,v] = ∠[u,l] + ∠[l,v] = ∠[u,l] + ∠[1].",
                    "Na primer, ako imamo AC ⊥ BC, onda ∠[CBA] = ∠[BAC] + ∠[1].",

                    "* 7",
                    "Teorema o jednakokrakom trokutu za pune uglove.",
                    "Ako je AC = BC, tada imamo ∠[ABC] = ∠[CAB]. Obrnuto, ako je ∠[ABC] = ∠[CAB], tada su AB = AC ili tada su A, B i C ko-linearni.",

                    "* 8",
                    "Teorema o tangentom uglu",
                    "A, B, C i D su tačke na kružnici, ako je ∠[ACB] = ∠[ADB].",

                    "* 9",
                    "Ako su tačke A,B,C i D deo kruga, onda ako važi ∠[ACB] = ∠[ADB] i krug prolazi kroz te tačke. Takođe, ako je krug(A,B,M,N), onda važi ∠[AB, AM] = ∠[BN,NM]. Iz ovoga sledi da važi ∠[AB, CD] = ∠[AB, AM] + ∠[AM, CD] = ∠[BN,NM] + ∠[AM, CD].",

                    "* 10",
                    "Pravilo 10.",

                    "* 11",
                    "Ako je AB prečnik opisanog kruga trougla ABC, a D se nalazi na tom krugu, tada za bilo koju liniju FG važi ∠[CD,FG] = ∠[DBA] + ∠[ACB] + ∠[BC, FG].",

                    "* 12",
                    "Ako je O presek kruga(O1, O P1 P3 I) & kruga(O2, O P2 P4 J) & O, I i J su kolinearne, tada važi ∠[P1O, OP2] = ∠[P1P2, IP3] + ∠[JP4, P2P4].",

                    "* 13",
                    "Ako je O centar opisanog kruga trougla ABC, tada važi ∠[BOC] = 2 * ∠[BAC].",

                    "* 14",
                    "Za krug(O, A B C D), ako je O presek AD i BC, tada važi ∠[AOB] = 2 * ∠[CDA].",

                    "* 15",
                    "Za krug(O, A B), gdje je O centar kruga, važi ∠[OAB] = ∠[ABO].",

                    "* 16",
                    "Ako je trougao ABE pravougli sa hipotenuzom (tj. stranicom AB) koju deli tačka O, tada za bilo koju liniju CD važi",
                    "∠[AB, CD] = ∠[BAE] + ∠[1] + ∠[BE, CD].",

                    "* 17",
                    "Za krug(O, ABC) i tačku D koja je središnja tačka stranice BC, važi ∠[AB, EF] = ∠[BOD] + ∠[AC,EF].",

                    "* 18",
                    "Za krug(O,ABF) i tačku E koja je središnja tačka stranice AF, važi ∠[AB, CD] = ∠[EOA] + ∠[BF, CD].",

                    "* 19",
                    "Ako je G ortocentar trougla ABC, tada važi ∠[AE, HI] = ∠[AE, BE] + ∠[BE, HI] = ∠[1] + ∠[BE, HI].",

                    "* 20",
                    "Ako je A centar (incenter) od P2, K, I, ∠[AB, CD] = ∠[AB, BK] + ∠[BK, CD] = ∠[1] + ∠[KI, KA] + ∠[IA, IK] + ∠[BK, CD].",

                    "* 21",
                    "Jednak ugao pronađen u deduktivnoj geometrijskoj bazi podataka.",

                    "* 22",
                    "Ako je AB ⊥ AC i AB = AC, onda ∠[AB,AC] = 2 * ∠[AB,BC].",

                    "* 23",
                    "Ako AB ⊥ AC i AB = AC, onda 2 * ∠[AB,DE] = 2 * ∠[AB,BC] + 2 * ∠[BC,DE] = ∠[1] +2 * ∠[BC,DE].",

                    "* 24",
                    "Ako je OB // DE i A se nalazi na krugu(O,B), tada 2 * ∠[AB, DE] = ∠[OA,OB]",

                    "* 25",
                    "A se nalazi na krugu(O,B), tada 2 * ∠[AB, DE] = ∠[OA,OB] + 2 * ∠[OA,DE]",

                    "* 26",
                    "CD je prečnik kruga(A, BCD), AB ⊥ CD, tada 2 * ∠[DB,BA] = ∠[0].",

                    "* 27",
                    "A, B, C, D, E su ciklični i AC = AB, tada 2*∠[AB, BC] = ∠[CE, ED].",

                    "* 28",
                    "Ako su A, B, E, F, G ciklični i AE = AF, tada 2 * ∠[AB, CD] = 2 * ∠[BE, CD] + ∠[EG, GF].",

                    "* 29",
                    "Ako je AB = AC = BC, tj. trougao ABC je jednakostranični trougao, tada 3 * ∠[AB, BC] = ∠[0].",

                    "* END.",

            };

    final public static String[] FULL_German =
            {
                    "* 1 # Die Definition eines Vollwinkels.",
                    "Ein Vollwinkel ist definiert als ein geordnetes Paar von zwei Geraden u und v und wird als ∠[u,v] bezeichnet.",

                    "* 2",
                    "∠[u,v] =  - ∠[v,u].",

                    "* 3",
                    "∠[u,v] = ∠[0] genau dann, wenn u ∥ v  (einschließlich u = v).",

                    "* 4",
                    "∠[u,v] = ∠[1] genau dann, wenn u ⊥ v.",

                    "* 5 # Addition eines Vollwinkels.",
                    "∠[u,s] + ∠[s,v] = ∠[u,v]. Diese Regel gilt bei der Aufteilung eines Winkels in zwei oder mehrere Winkel.",
                    "Beispielsweise gilt ∠[u,v] = ∠[u,m] + ∠[m,v] für eine beliebige Gerade m.",

                    "* 6",
                    "Wenn l ⊥ v oder ∠[l,v] = ∠[1], dann ist ∠[u,v] = ∠[u,l] + ∠[l,v] = ∠[u,l] + ∠[1].",
                    "Ist beispielsweise AC ⊥ BC, dann ist ∠[CBA] = ∠[BAC] + ∠[1].",

                    "* 7",
                    " Das Gleichschenklige Dreieck-Theorem für Vollwinkel.",
                    " Ist AC = BC, gilt ∠[ABC] = ∠[CAB]. Umgekehrt, wenn ∠[ABC] = ∠[CAB], dann gilt AB = AC oder A, B und C sind kollinear.",

                    "* 8",
                    " Das Kreiswinkel-Theorem.",
                    " Die Punkte A, B, C und D sind genau dann zyklisch, wenn ∠[ACB] = ∠[ADB].",

                    "* 9",
                    " Sind A, B, M und N zyklisch gilt ∠[AB, AM] = ∠[BN,NM]. Daher gilt  ∠[AB, CD] = ∠[AB, AM] + ∠[AM, CD] = ∠[BN,NM]] + ∠[AM, CD].",

                    "* 10",
                    " Regel 10.",

                    "* 11",
                    " Ist AB der Durchmesser des Umkreises des Dreiecks ABC und Punkt D liegt darauf, dann gilt für jede Strecke FG ∠[CD,FG] = ∠[DBA] + ∠[ACB] + ∠[BC, FG]].",

                    "* 12",
                    " Ist O der Schnittpunkt der Kreise (O1, O P1 P3 I) und (O2, O P2 P4 J) und O, I und J sind kollinear dann gilt ∠[P1O, OP2] = ∠[P1P2, IP3] + ∠[JP4, P2P4].",

                    "* 13",
                    " Ist O der Umkreismittelpunkt des Dreiecks ABC, dann gilt: ∠[BOC] = 2 * ∠[BAC].",

                    "* 14",
                    " Ist O der Schnittpunkt von AD und BC, dann gilt für einen Kreis (O, A B C D) ∠[AOB] = 2 * ∠[CDA].",

                    "* 15",
                    " Für einen Kreis (O, A B) bei dem O der Mittelpunkt ist gilt ∠[OAB] = ∠[ABO]. ",

                    "* 16",
                    " Ist das Dreieck ABE ein rechtwinkliges Dreieck und O der Mittelpunkt der Hypotenuse (i.e. AB), dann gilt für jede Strecke CD",
                    " ∠[AB, CD] =  ∠[BAE] + ∠[1] + ∠[BE, CD]  ",

                    "* 17",
                    " Für einen Kreis (O, ABC) bei dem D der Mittelpunkt von BC ist gilt ∠[AB, EF] = ∠[BOD] + ∠[AC, EF].",

                    "* 18",
                    " Für einen Kreis (O, ABF) bei dem E der Mittelpunkt von AF ist gilt ∠[AB, CD] = ∠[EOA] + ∠[BF, CD].",

                    "* 19",
                    " Ist G der Höhenschnittpunkt von A, B und C dann gilt ∠[AE, HI] = ∠[AE, BE] + ∠[BE, HI] = ∠[1] + ∠[BE, HI]",

                    "* 20",
                    " Ist A der Inkreismittelpunkt von P2, K und I dann gilt ∠[AB, CD] = ∠[AB, BK] + ∠[BK, CD] = ∠[1] + ∠[KI, KA] + ∠[IA, IK] + ∠[BK, CD].",

                    "* 21",
                    " Gleicher Winkel in der Geometry Deductive Datenbank gefunden.",

                    "*  22",
                    " Ist AB ⊥ AC und AB = AC, dann gilt ∠[AB,AC] = 2 * ∠[AB,BC].",

                    "*  23",
                    " Ist AB ⊥ AC und AB = AC, dann gilt 2 * ∠[AB,DE] = 2 * ∠[AB,BC] + 2 * ∠[BC,DE] = ∠[1] +2 * ∠[BC,DE].",

                    "*  24",
                    " Ist OB // DE und A ist auf dem Kreis (O, B) dann gilt 2 * ∠[AB, DE] = ∠[OA,OB]",

                    "*  25",
                    " Ist A auf dem Kreis (O, B) dann gilt 2 * ∠[AB, DE] = ∠[OA,OB] + 2 * ∠[OA,DE]",

                    "*  26",
                    " Ist CD ist der Durchmesser des Kreises (A, BCD) und AB ⊥ CD, dann gilt  2 * ∠[DB,BA]  = ∠[0].",

                    "*  27",
                    " Sind A, B, C, D und E zyklisch und AC = AB, dann ist 2*∠[AB, BC] =  ∠[CE, ED].",

                    "*  28",
                    " Sind A, B, E, F und G zyklisch und AE = AF, dann ist 2 * ∠[AB, CD] =  2 * ∠[BE, CD] + ∠[EG, GF].",

                    "*  29",
                    " Ist AB = AC = BC, i.e., triangle ABC is an equilateral triangle, then 3 * ∠[AB, BC] = ∠[0].  ",

                    "* END.",

            };

    final public static String[] GDD_German =
            {
                    "* 1",
                    "Gilt AB ∥ BC, dann sind A, B und C kollinear.",

                    "* 2",
                    "Für zwei Winkel ∠[l1,l2], ∠[l3,l4] gilt: Ist l1 = l3 und l2 ∥ l4, dann ist ∠[l1,l2] = ∠[l3,l4].",
                    "Beispielsweise gilt im Parallelogramm ABCD AD ∥ BC und AC = CA, und somit auch ∠[CAD] = ∠[ACB].",

                    "* 3",
                    "Ist AB ∥ CD und E der Schnittpunkt von AC und BD, dann gilt EA / EC = EB / ED.",

                    "* 4 ",
                    "Wenn AB ∥ CD und CD ⊥ EF, dann gilt AB ⊥ EF.",

                    "* 5",
                    "Wenn AB ⊥ CD, dann gilt ∠[AB,CD] = [1] (oder 90 Grad).",

                    "* 6",  
                    "Im rechtwinkligen Dreieck ACB gilt ∠C = [1], wenn der Mittelpunkt (E, A B) ist, dann ist der Umkreismittelpunkt (E, A B C) und EA = EB = EC.",

                    "* 7",
                    "Wenn AB ⊥ CD und CD ⊥ EF, dann gilt AB ∥ EF.",

                    "* 8",
                    "Für vier Punkte A, B, C und D gilt: Wenn AC ⊥ BC und AD ⊥ BD, dann sind A,B,C,D zyklisch.",

                    "* 9",
                    "Für einen Kreis c(O, AB) und einen Punkt C auf dem Kreis c gilt: Sind A, B und O kollinear, dann sind AC ⊥ BC und ∠ACB = [1]",

                    "* 10",
                    "Wenn AB der Durchmesser eines Kreises ist und Punkt C auf diesem liegt, dann gilt AC ⊥ BC",

                    "* 11",
                    "Der Umfangwinkel ist gleich der Hälfte des Mittelpunktwinkels.",

                    "* 12",
                    "Wenn AB ∥ CD und A, B, C und D zyklisch sind, dann ist ∠ABC = ∠DAB.",

                    "* 13",
                    "Wenn A, B, C und D zyklisch sind, dann ist ∠ADB = ∠ACB und umgekehrt.",

                    "* 14",
                    "Sehne-Tangente-Winkel ***** ",

                    "* 15 ",
                    "Die Gerade, die durch die Mittelpunkte zweier Kreise verläuft, steht senkrecht auf deren gemeinsamen Sehne.",
                    "Sind beispielsweise AB gemeinsame Punkte der Kreise C1(O,AB) und C2(O1,AB), dann gilt OO1 ⊥ AB.",

                    "* 16",
                    "Die Addition für Vollwinkel.",
                    "Ist ∠[l1,l2] = ∠[l3,l4], ∠[l5,l6] = ∠[l7,l8], l2 = l6 und l4 = l7, dann gilt ∠[l1,l4] = ∠[l5,l8] ",

                    "* 17",
                    "ASPP12.",
                    "Ist ∠[l1,l2] = ∠[l3,l4] und l1 ∥ l2, dann gilt l3 ∥ l4.",
                    "Denn wenn l1 ∥ l2 gilt ∠[l1,l2] = [0]. Somit gilt ∠[l3,l4] = [0].",

                    "* 18",
                    "ASPP13",
                    "Ist ∠[l1,l2] = ∠[l3,l4] und l1 ∥ l3, dann gilt l2 ∥ l4.",
                    " ",

                    "* 19",
                    "ASTT12",
                    "Ist ∠[l1,l2] = ∠[l3,l4] und l1 ⊥ l2, dann gilt l3 ⊥ l4.",

                    "* 20",
                    "ASTT13",
                    "Ist ∠[l1,l2] = ∠[l3,l4] und l1 ⊥ l3, dann gilt l2 ⊥ l4.",

                    "* 21",
                    "Spezielle Winkel",

                    "* 22",
                    "Supplementärwinkel",

                    "* 23",
                    "Gleichschenkliges Dreieck.",

                    "* 24 Satz des gleichschenkligen Dreiecks.",
                    "Sei ABC ein gleichschenkliges Dreieck und sei Punkt D auf der Seite BC. Wenn eine der folgenden Aussagen wahr ist, sind die anderen zwei auch wahr:",
                    "1. AD ⊥ BC     2. D ist der Mittelpunkt von BC    3. ∠BAD = ∠DAC",

                    "* 25 ",
                    "Kongruentes Dreieck. ",
                    "Dreiecke sind kongruent, wenn sie in Größe und Form gleich sind. Das heißt, dass die dazugehörigen Winkel gleich groß sind und die Seiten die gleiche Länge haben.",

                    "* 26  #WSW",
                    "WSW- (Winkel-Seite-Winkel) Kongruenz: ",
                    "Zwei Dreiecke sind kongruent, wenn zwei der Winkel und die davon eingeschlossene Seite gleich sind.",

                    "* 27 #SWS",
                    "SWS- (Seite-Winkel-Seite) Kongruenz",
                    "Zwei Dreiecke sind kongruent, wenn zwei Seiten und der davon eingeschlossene Winkel gleich sind.",

                    "* 28 #SSS",
                    "SSS- (Seite-Seite-Seite) Kongruenz",
                    "Zwei Dreiecke sind kongruent, wenn deren Seiten gleich sind.",

                    "* 29 #SWS",
                    "SWS- (Seite-Winkel-Seite) Kongruenz für rechtwinklige Dreiecke.",
                    "Zwei rechtwinklige Dreiecke sind kongruent, wenn deren zwei entsprechenden kartesischen Ecken gleich sind.",

                    "* 30",
                    "Ähnliche Dreiecke",
                    "Ähnliche Dreiecke sind Dreiecke, die dieselbe Form haben aber möglicherweise unterschiedlich groß sind - die dazugehörigen Winkel sind kongruent und die dazugehörigen Seiten sind im gleichen Verhältnis zueinander.",

                    "* 31 #WWW",
                    "WWW- (Winkel-Winkel-Winkel) Ähnlichkeit",
                    "Haben zwei Dreiecke zwei entsprechende kongruente Winkel, sind die Dreiecke ähnlich. Da die Summe der Winkel in einem Dreieck PI sein muss, muss nur klar sein, dass diese beiden Paare von entsprechenden Winkeln kongruent sind, um zu wissen, dass die Dreiecke ähnlich sind.",

                    "* 32 #SWS",
                    "SWS- (Seite-Winkel-Seite) Ähnlichkeit",
                    "Die Seiten-Winkel-Seiten-Ähnlichkeit besagt, dass zwei Dreiecke ähnlich sind, wenn sie zwei  Seiten im gleichen Verhältnis haben und die eingeschlossenen Winkel kongruent sind.",

                    "* 33 #SSS",
                    "SSS (Seite-Seite-Seite) Ähnlichkeit",
                    "Sind alle drei Seiten zweier Dreiecke im gleichen Verhältnis, sind die Dreiecke ähnlich.",

                    "* 34",
                    "Verhältnis durch Ähnlichkeit",

                    "* 35",
                    "Satz der Mittelparallelen für Dreiecke",
                    "Die Strecke, die die Mittelpunkte zweier Seiten eines Dreiecks verbindet, ist parallel zur dritten Seite des Dreiecks.",

                    "* 36",
                    "Die Hypotenuse eines rechtwinkligen Dreiecks ist gleich dem Durchmesser des Umkreises des Dreiecks und deren Mittelpunkt ist gleich dem Mittelpunkt des Umkreises.",
                    "Für ein rechtwinkliges Dreieck ACB gilt: Wenn AC ⊥ BC und D der Mittelpunkt von AB ist, dann ist DC = DA = DB und D der Mittelpunkt des Kreises ABC.",

                    "* 37",
                    "Gleichseitige Dreiecke",
                    "Ein gleichseitiges Dreieck ist ein Dreieck in dem die Längen aller drei Seiten gleich sind.",

                    "* 38",
                    "Satz des Pythagoras",
                    "In jedem rechtwinkligen Dreieck ist die Fläche des Quadrats, dessen Seite die Hypotenuse (die Seite des rechtwinkligen Dreiecks, die gegenüber des rechten Winkels liegt) ist, gleich der Summe der Flächen des Quadrats deren Seiten die beiden Katheten sind (i.e. die beiden anderen Seiten als die Hypotenuse).",

                    "* 39",
                    "Die Summe der inneren Winkel eines Dreiecks ist [0]. (oder 180 Grad für herkömmliche Dreiecke).",

                    "* 40",
                    "Parallelogram ",
                    "Ein Parallelogramm ist ein Viereck mit zwei Paaren von gegenüberliegenden parallelen Seiten.",

                    "* 41",
                    "Satz der Mittelparallelen für Trapeze",

                    "* 42",
                    "Verhältnis",
                    "Verwendung des Verhältnisses in der deduktiven Datenbank-Methode.",

                    "* 43",
                    "Verhältnis durch die Winkelhalbierende",
                    "Im Dreieck ABC gilt: Ist AD die Winkelhalbierende von Winkel A und D liegt auf der Strecke BC, dann ist AB/BD = AC/DC. ",
                    "*END",
            };
}
