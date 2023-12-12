package gprover;

public class rules {

    // All of these translations are now in the keys.pot and *.po files in the gettext system.
    // In case you edit these entries, make sure that the other files will also be updated.

    final public static String[] GDD_English = {
            "* 1",
            "If AB ∥ BC, then A, B and C are collinear.",

            "* 2",
            "For two angles ∠[l1,l2], ∠[l3,l4]. If l1 = l3 and l2 ∥ l4, then ∠[l1,l2] = ∠[l3,l4].",
            "For example, in parallelogram ABCD, AD ∥ BC and AC = CA, hence ∠[CAD] = ∠[ACB].",

            "* 3",
            "If AB ∥ CD and E is the intersection of AC and BD, then EA / EC = EB / ED.",

            "* 4 ",
            "If AB ∥ EF and CD ⊥ EF, then CD ⊥ EF.",

            "* 5",
            "If AB ⊥ CD, then ∠[AB,CD] = [1] (or 90 degrees).",

            "* 6",
            "In a right triangle ACB, ∠C = [1], if E is the midpoint of AB, then E is the circumcenter of ABC with EA = EB = EC.",

            "* 7",
            "If AB ⊥ CD and CD ⊥ EF, then AB ∥ EF.",

            "* 8",
            "For four points A,B,C,D, if AC ⊥ BC and AD ⊥ BD, then A,B,C,D are concyclic.",

            "* 9",
            "For a circle c(O,AB) and a point C on circle c, if A,B,O are collinear, then AC ⊥ BC and ∠ACB = [1].",

            "* 10",
            "If AB is the diameter of a circle and point D is on the circle, then AD ⊥ BD." ,

            "* 11",
            "Angle of circumference equals to half of the angle at the center.",

            "* 12",
            "If AB ∥ CD and A,B,C,D are concyclic, then ∠ABC = ∠DAB.",

            "* 13",
            "If A,B,C,D are concyclic, then ∠ADB = ∠ACB and vice versa.",

            "* 14",
            "Chord tangent angle theorem.",

            "* 15",
            "A line passing the centers of two circles is perpendicular to the common chord of two circles.",
            "For example, given two circles C1(O,AB) and C2(O1,AB), A and B are two common points, then OO1 ⊥ AB.",

            "* 16",
            "The Addition for Full Angle.",
            "If ∠[l1,l2] = ∠[l3,l4] and ∠[l5,l6] = ∠[l7,l8], and l2 = l6 and l4 = l7, then ∠[l1,l4] = ∠[l5,l8].",

            "* 17 #ASPP12",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ∥ l2, then l3 ∥ l4.",
            "Because if l1 ∥ l2, then ∠[l1,l2] = [0]. Hence ∠[l3,l4] = [0].",

            "* 18 #ASPP13",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ∥ l3, then l2 ∥ l4.",

            "* 19 #ASTT12",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ⊥ l2, then l3 ⊥ l4.",

            "* 20 #ASTT13",
            "If ∠[l1,l2] = ∠[l3,l4] and l1 ⊥ l3, then l2 ⊥ l4.",

            "* 21",
            "Special Angles.",

            "* 22",
            "Supplementary Angle.",

            "* 23",
            "Isosceles Triangle.",

            "* 24 #Isosceles Triangle Theorem",
            "For an isosceles triangle ABC and a point D on line BC, if one of the following statements is true, the other two are also true:",
            "1. AD ⊥ BC, 2. D is the midpoint of BC, 3. ∠BAD = ∠DAC.",

            "* 25",
            "Congruent Triangles.",
            "Triangles are called congruent if they have the same size and shape. That is, corresponding angles have the same measure, and corresponding sides have the same length.",

            "* 26 #ASA",
            "ASA (angle-side-angle) congruence.",
            "Two triangles are congruent if a pair of corresponding angles and the included side are equal.",

            "* 27 #SAS",
            "SAS (side-angle-side) congruence.",
            "Two triangles are congruent if a pair of corresponding sides and the included angle are equal.",

            "* 28 #SSS",
            "SSS (side-side-side) congruence.",
            "Two triangles are congruent if their corresponding sides are equal.",

            "* 29 #SAS",
            "SAS (side-angle-side) congruence for right triangles.",
            "Two right triangles are congruent if their two corresponding sides are equal.",

            "* 30",
            "Similar triangles.",
            "Similar triangles are triangles that have the same shape but possibly different size. In particular, corresponding angles are congruent, and corresponding sides are in proportion.",

            "* 31 #AAA",
            "AAA (angle-angle-angle) similarity.",
            "If two triangles have two corresponding angles that are congruent, then the triangles are similar. Because the sum of the angles in a triangle must be π, we really only need to know that two pairs of corresponding angles are congruent to know that the triangles are similar.",

            "* 32 #SAS",
            "SAS (side-angle-side) similarity.",
            "The side-angle-side (SAS) similarity test says that if two triangles have two pairs of sides that are proportional and the included angles are congruent, then the triangles are similar.",

            "* 33 #SSS",
            "SSS (side-side-side) similarity.",
            "If two triangles have all three pairs of sides in proportion, the triangles are similar.",

            "* 34",
            "Ratio by similarity.",

            "* 35",
            "Middle Connection Theorem for Triangles.",
            "The line connects the midpoints of two sides of a triangle is parallel to the third side of the triangle.",

            "* 36",
            "The hypotenuse of a right triangle is the diameter of the circumscribed circle of the triangle, and the midpoint of the hypotenuse is the center of the circle.",
            "For a right triangle ACB, if AC ⊥ BC and D is the midpoint of AB, then DC = DA = DB, D is the center of circle ABC.",

            "* 37",
            "Equilateral Triangles.",
            "An equilateral triangle is a triangle in which all three sides have equal lengths.",

            "* 38",
            "Pythagorean Theorem.",
            "In any right triangle, the area of the square whose side is the hypotenuse (the side of a right triangle opposite the right angle) is equal to the sum of areas of the squares whose sides are the two legs (i.e. the two sides other than the hypotenuse).",

            "* 39",
            "The sum of three interior angles of a triangle is [0] (or 180 degree for traditional angles).",

            "* 40",
            "Parallelogram.",
            "A parallelogram is a quadrilateral with two sets of opposite parallel sides.",

            "* 41",
            "Middle Connection Theorem for Trapezoid.",

            "* 42",
            "Ratio.",
            "Use Ratio in Deductive Database Method.",

            "* 43",
            "Ratio by angle bisector.",
            "In triangle ABC, if AD is the bisector of angle A, and D is on line BC, then AB/BD = AC/DC.",
            "*END",
    };

    final public static String[] FULL_English =
            {
                    "* 1 #The Definition of Full Angle",
                    "A full angle is defined as an ordered pair of two lines u and v denoted by ∠[u,v].",

                    "* 2",
                    "∠[u,v] = -∠[v,u].",

                    "* 3",
                    "∠[u,v] = ∠[0] if and only if u ∥ v (including u = v).",

                    "* 4",
                    "∠[u,v] = ∠[1] if and only if u ⊥ v.",

                    "* 5 #Addition of Full Angles",
                    "∠[u,s] + ∠[s,v] = ∠[u,v]. This rule is to split an angle into two or more angles.",
                    "For example, for any line m, we have ∠[u,v] = ∠[u,m] + ∠[m,v].",

                    "* 6",
                    "If l ⊥ v or ∠[l,v] = ∠[1], then ∠[u,v] = ∠[u,l] + ∠[l,v] = ∠[u,l] + ∠[1].",
                    "For example, if we have AC ⊥ BC, then ∠[CBA] = ∠[BAC] + ∠[1].",

                    "* 7",
                    "The Isosceles Triangle Theorem for Full Angle.",
                    "If AC = BC, we have ∠[ABC] = ∠[CAB]. Conversely, if ∠[ABC] = ∠[CAB], then AB = AC or A, B and C are collinear.",

                    "* 8",
                    "The Inscribed Angle Theorem.",
                    "Points A,B,C and D are concyclic if and only if ∠[ACB] = ∠[ADB].",

                    "* 9",
                    "If A,B,M,N are concyclic, then ∠[AB, AM] = ∠[BN,NM]. Hence we have ∠[AB, CD] = ∠[AB, AM] + ∠[AM, CD] = ∠[BN,NM] + ∠[AM, CD].",

                    "* 10", // FIXME: This rule seems to be completely missing.
                    "Rule 10.",

                    "* 11",
                    "If AB is the diameter of the circumcircle of triangle ABC and D is on the circumcircle, then for any line FG, we have ∠[CD,FG] = ∠[DBA] + ∠[ACB] + ∠[BC, FG].",

                    "* 12",
                    "If O is the intersection of circle (O1,OP1P3I) and circle (O2,OP2P4J) and O,I,J are collinear, then ∠[P1O, OP2] = ∠[P1P2, IP3] + ∠[JP4, P2P4].",

                    "* 13",
                    "If O is the circumcenter of triangle ABC, then ∠[BOC] = 2 · ∠[BAC].",

                    "* 14",
                    "For a circle (O,ABCD), if O is the intersection of AD and BC, then ∠[AOB] = 2 · ∠[CDA].",

                    "* 15",
                    "For a circle (O,AB), O is the center of circle, we have ∠[OAB] = ∠[ABO].",

                    "* 16",
                    "If triangle ABE is a right triangle and O is the midpoint of the hypotenuse (i.e. AB), then for any line CD, we have ∠[AB, CD] = ∠[BAE] + ∠[1] + ∠[BE, CD].",

                    "* 17",
                    "For a circle (O,ABC) and D is midpoint of BC, we have ∠[AB, EF] = ∠[BOD] + ∠[AC, EF].",

                    "* 18",
                    "For a circle (O,ABF), if E is the midpoint of AF, we have ∠[AB, CD] = ∠[EOA] + ∠[BF, CD].",

                    "* 19",
                    "If G is the orthocenter of ABC, ∠[AE, HI] = ∠[AE, BE] + ∠[BE, HI] = ∠[1] + ∠[BE, HI].",

                    "* 20",
                    "If A is the incenter of P2KI, ∠[AB, CD] = ∠[AB, BK] + ∠[BK, CD] = ∠[1] + ∠[KI, KA] + ∠[IA, IK] + ∠[BK, CD].",

                    "* 21",
                    "Equal angle found in the Geometry Deductive Database.",

                    "* 22",
                    "If AB ⊥ AC and AB = AC, then ∠[AB,AC] = 2 · ∠[AB,BC].",

                    "* 23",
                    "If AB ⊥ AC and AB = AC, then 2 · ∠[AB,DE] = 2 · ∠[AB,BC] + 2 · ∠[BC,DE] = ∠[1] + 2 · ∠[BC,DE].",

                    "* 24",
                    "If OB // DE and A is on the circle (O,B), then 2 · ∠[AB, DE] = ∠[OA,OB].",

                    "* 25",
                    "A is on circle (O,B), then 2 · ∠[AB, DE] = ∠[OA,OB] + 2 · ∠[OA,DE].",

                    "* 26",
                    "CD is the diameter of a circle (A,BCD), AB ⊥ CD, then 2 · ∠[DB,BA] = ∠[1].",

                    "* 27",
                    "A, B, C, D, E are concyclic and AC = AD, then 2 · ∠[AB, BC] = ∠[CE, ED].",

                    "* 28",
                    "If A, B, E, F, G are concyclic and AE = AF, then 2 · ∠[AB, CD] = 2 · ∠[BE, CD] + ∠[EG, GF].",

                    "* 29",
                    "If AB = AC = BC, i.e., triangle ABC is an equilateral triangle, then 3 · ∠[AB, BC] = ∠[0].",

                    "* END.",

            };

}
