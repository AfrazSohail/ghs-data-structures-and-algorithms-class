// /**
// * Test harness for WolfNode and SheepNode behaviors.
// * Documentation done by AI.
// *
// * @author AfrazSohail
// */
// public class Driver {
// private static final boolean RUN_ONLY_TEST_7E = false;

// /**
// * Program entry point to run the configured test suite.
// *
// * @param args command-line arguments (unused)
// */
// public static void main(String args[]) {
// if (RUN_ONLY_TEST_7E) {
// testSheepWinPath7e();
// return;
// }

// System.out.println("╔══════════════════════════════════════════════════════════╗");
// System.out.println("║ COMPREHENSIVE WOLF & SHEEP TEST SUITE ║");
// System.out.println("╚══════════════════════════════════════════════════════════╝\n");

// testWolfNodeConstructor();
// testGetUnsortedPathSet();
// testHasPath();
// testGetIntersections();
// testCanBlock();
// testSheepNodeConstructor();
// testSheepWinPath();

// System.out.println("\n╔══════════════════════════════════════════════════════════╗");
// System.out.println("║ ALL TESTS COMPLETE ║");
// System.out.println("╚══════════════════════════════════════════════════════════╝");
// }

// /** Tests WolfNode constructor validation. */
// private static void testWolfNodeConstructor() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: WolfNode Constructor & Validation");
// System.out.println("═══════════════════════════════════════════════════════════");

// // Valid positions
// System.out.println("Test 1a: Valid corner positions");
// try {
// WolfNode w1 = new WolfNode("A1");
// WolfNode w2 = new WolfNode("H8");
// WolfNode w3 = new WolfNode("A8");
// WolfNode w4 = new WolfNode("H1");
// System.out.println("✓ A1, H8, A8, H1 created successfully");
// } catch (Exception e) {
// System.out.println("✗ Failed: " + e.getMessage());
// }

// System.out.println("\nTest 1b: Valid middle positions");
// try {
// WolfNode w5 = new WolfNode("D4");
// WolfNode w6 = new WolfNode("E5");
// System.out.println("✓ D4, E5 created successfully");
// } catch (Exception e) {
// System.out.println("✗ Failed: " + e.getMessage());
// }

// // Invalid positions
// System.out.println("\nTest 1c: Invalid - position too long");
// try {
// WolfNode w = new WolfNode("ABC");
// System.out.println("✗ Should have thrown exception");
// } catch (IllegalArgumentException e) {
// System.out.println("✓ Correctly rejected: " + e.getMessage());
// }

// System.out.println("\nTest 1d: Invalid - wrong format");
// try {
// WolfNode w = new WolfNode("12");
// System.out.println("✗ Should have thrown exception");
// } catch (IllegalArgumentException e) {
// System.out.println("✓ Correctly rejected: " + e.getMessage());
// }

// System.out.println("\nTest 1e: Invalid - out of bounds column");
// try {
// WolfNode w = new WolfNode("I5");
// System.out.println("✗ Should have thrown exception");
// } catch (IllegalArgumentException e) {
// System.out.println("✓ Correctly rejected: " + e.getMessage());
// }

// System.out.println("\nTest 1f: Invalid - out of bounds row");
// try {
// WolfNode w = new WolfNode("D9");
// System.out.println("✗ Should have thrown exception");
// } catch (IllegalArgumentException e) {
// System.out.println("✓ Correctly rejected: " + e.getMessage());
// }
// System.out.println();
// }

// /** Tests WolfNode.getUnsortedPathSet(). */
// private static void testGetUnsortedPathSet() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: getUnsortedPathSet()");
// System.out.println("═══════════════════════════════════════════════════════════");

// System.out.println("Test 2a: Wolf at A1 - path to row 8");
// WolfNode wolf1 = new WolfNode("A1");
// System.out.println("Path size: " + wolf1.getUnsortedPathSet().size());
// System.out.println("Path contains A1: " +
// wolf1.getUnsortedPathSet().contains("A1"));
// System.out.println("Path contains H8: " +
// wolf1.getUnsortedPathSet().contains("H8"));

// System.out.println("\nTest 2b: Wolf at D4 - mid board");
// WolfNode wolf2 = new WolfNode("D4");
// System.out.println("Path size: " + wolf2.getUnsortedPathSet().size());
// System.out.println("Sample path: " + wolf2.getUnsortedPathSet());

// System.out.println("\nTest 2c: Wolf at H7 - limited paths");
// WolfNode wolf3 = new WolfNode("H7");
// System.out.println("Path size: " + wolf3.getUnsortedPathSet().size());
// System.out.println("Path: " + wolf3.getUnsortedPathSet());

// System.out.println("\nTest 2d: Wolf at A8 - only southeast");
// WolfNode wolf4 = new WolfNode("A8");
// System.out.println("Path size: " + wolf4.getUnsortedPathSet().size());

// System.out.println("\nTest 2e: Wolf at H8 - only southwest");
// WolfNode wolf5 = new WolfNode("H8");
// System.out.println("Path size: " + wolf5.getUnsortedPathSet().size());
// System.out.println();
// }

// /** Tests WolfNode.hasPath(). */
// private static void testHasPath() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: hasPath()");
// System.out.println("═══════════════════════════════════════════════════════════");

// WolfNode wolf = new WolfNode("C3");

// System.out.println("Test 3a: Wolf at C3, check reachable positions");
// System.out.println("hasPath('C3'): " + wolf.hasPath("C3"));
// System.out.println("hasPath('D4'): " + wolf.hasPath("D4"));
// System.out.println("hasPath('B4'): " + wolf.hasPath("B4"));
// System.out.println("hasPath('E5'): " + wolf.hasPath("E5"));

// System.out.println("\nTest 3b: Check unreachable positions");
// System.out.println("hasPath('A3'): " + wolf.hasPath("A3"));
// System.out.println("hasPath('C2'): " + wolf.hasPath("C2"));
// System.out.println("hasPath('F3'): " + wolf.hasPath("F3"));

// System.out.println("\nTest 3c: Edge case - checking row 8 destinations");
// System.out.println("hasPath('A8'): " + wolf.hasPath("A8"));
// System.out.println("hasPath('E8'): " + wolf.hasPath("E8"));
// System.out.println();
// }

// /** Tests WolfNode.getIntersections(). */
// private static void testGetIntersections() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: getIntersections()");
// System.out.println("═══════════════════════════════════════════════════════════");

// System.out.println("Test 4a: Two wolves with overlapping paths");
// WolfNode wolf1 = new WolfNode("A1");
// WolfNode wolf2 = new WolfNode("C1");
// System.out.println("Wolf A1 intersections with Wolf C1:");
// System.out.println(wolf1.getIntersections(wolf2));

// System.out.println("\nTest 4b: Adjacent wolves");
// WolfNode wolf3 = new WolfNode("D3");
// WolfNode wolf4 = new WolfNode("E3");
// System.out.println("Wolf D3 intersections with Wolf E3:");
// System.out.println(wolf3.getIntersections(wolf4));

// System.out.println("\nTest 4c: Wolves far apart");
// WolfNode wolf5 = new WolfNode("A1");
// WolfNode wolf6 = new WolfNode("G1");
// System.out.println("Wolf A1 intersections with Wolf H1:");
// System.out.println(wolf5.getIntersections(wolf6));

// System.out.println("\nTest 4d: Wolves at corners");
// WolfNode wolf7 = new WolfNode("A7");
// WolfNode wolf8 = new WolfNode("G7");
// System.out.println("Wolf A7 intersections with Wolf H7:");
// System.out.println(wolf7.getIntersections(wolf8));

// System.out.println("\nTest 4e: Wolves with no overlap");
// WolfNode wolf9 = new WolfNode("A8");
// WolfNode wolf10 = new WolfNode("H8");
// System.out.println("Wolf A8 intersections with Wolf H8:");
// System.out.println(wolf9.getIntersections(wolf10));
// System.out.println();
// }

// /** Tests WolfNode.canBlock(). */
// private static void testCanBlock() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: canBlock() - Static Method");
// System.out.println("═══════════════════════════════════════════════════════════");

// System.out.println("Test 5a: All same color, already aligned");
// WolfNode w1 = new WolfNode("A1");
// WolfNode w2 = new WolfNode("C1");
// WolfNode w3 = new WolfNode("E1");
// WolfNode w4 = new WolfNode("G1");
// String result = WolfNode.canBlock(w1, w2, w3, w4);
// System.out.println("Result: " + (result != null ? result : "null"));

// System.out.println("\nTest 5b: Different colors - should fail");
// WolfNode w5 = new WolfNode("A1");
// WolfNode w6 = new WolfNode("C2");
// WolfNode w7 = new WolfNode("E1");
// WolfNode w8 = new WolfNode("G1");
// result = WolfNode.canBlock(w5, w6, w7, w8);
// System.out.println("Result: " + (result != null ? result : "null
// (expected)"));

// System.out.println("\nTest 5c: Scattered black squares");
// WolfNode w9 = new WolfNode("A3");
// WolfNode w10 = new WolfNode("C3");
// WolfNode w11 = new WolfNode("E5");
// WolfNode w12 = new WolfNode("G5");
// result = WolfNode.canBlock(w9, w10, w11, w12);
// System.out.println("Result: " + (result != null ? result : "null"));

// System.out.println("\nTest 5d: All white squares");
// WolfNode w13 = new WolfNode("B1");
// WolfNode w14 = new WolfNode("D1");
// WolfNode w15 = new WolfNode("F1");
// WolfNode w16 = new WolfNode("H1");
// result = WolfNode.canBlock(w13, w14, w15, w16);
// System.out.println("Result: " + (result != null ? result : "null"));

// System.out.println("\nTest 5e: Mixed rows, same color");
// WolfNode w17 = new WolfNode("A1");
// WolfNode w18 = new WolfNode("C3");
// WolfNode w19 = new WolfNode("E5");
// WolfNode w20 = new WolfNode("G7");
// result = WolfNode.canBlock(w17, w18, w19, w20);
// System.out.println("Result: " + (result != null ? result : "null"));

// System.out.println("\nTest 5f: Near top edge");
// WolfNode w21 = new WolfNode("B7");
// WolfNode w22 = new WolfNode("D7");
// WolfNode w23 = new WolfNode("F7");
// WolfNode w24 = new WolfNode("H7");
// result = WolfNode.canBlock(w21, w22, w23, w24);
// System.out.println("Result: " + (result != null ? result : "null"));
// System.out.println();
// }

// /** Tests SheepNode constructor validation. */
// private static void testSheepNodeConstructor() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: SheepNode Constructor & Validation");
// System.out.println("═══════════════════════════════════════════════════════════");

// System.out.println("Test 6a: Valid positions");
// try {
// SheepNode s1 = new SheepNode("D8");
// SheepNode s2 = new SheepNode("A8");
// SheepNode s3 = new SheepNode("H8");
// System.out.println("✓ D8, A8, H8 created successfully");
// } catch (Exception e) {
// System.out.println("✗ Failed: " + e.getMessage());
// }

// System.out.println("\nTest 6b: Invalid positions");
// try {
// SheepNode s = new SheepNode("I5");
// System.out.println("✗ Should have thrown exception");
// } catch (IllegalArgumentException e) {
// System.out.println("✓ Correctly rejected: " + e.getMessage());
// }

// System.out.println("\nTest 6c: Invalid format");
// try {
// SheepNode s = new SheepNode("5D");
// System.out.println("✗ Should have thrown exception");
// } catch (IllegalArgumentException e) {
// System.out.println("✓ Correctly rejected: " + e.getMessage());
// }
// System.out.println();
// }

// /** Tests SheepNode.winPath() across multiple scenarios. */
// private static void testSheepWinPath() {
// System.out.println("═══════════════════════════════════════════════════════════");
// System.out.println("TEST: Sheep winPath()");
// System.out.println("═══════════════════════════════════════════════════════════");

// System.out.println("Test 7a: Sheep at D8, wolves far away");
// WolfNode w1 = new WolfNode("A1");
// WolfNode w2 = new WolfNode("B2");
// WolfNode w3 = new WolfNode("F1");
// WolfNode w4 = new WolfNode("G2");
// SheepNode sheep1 = new SheepNode("D8");
// System.out.println("Path: " + sheep1.winPath(w1, w2, w3, w4));

// System.out.println("\nTest 7b: Sheep at corner H8");
// WolfNode w5 = new WolfNode("B2");
// WolfNode w6 = new WolfNode("C3");
// WolfNode w7 = new WolfNode("D4");
// WolfNode w8 = new WolfNode("E5");
// SheepNode sheep2 = new SheepNode("H8");
// System.out.println("Path: " + sheep2.winPath(w5, w6, w7, w8));

// System.out.println("\nTest 7c: Sheep at corner A8");
// SheepNode sheep3 = new SheepNode("A8");
// System.out.println("Path: " + sheep3.winPath(w5, w6, w7, w8));

// System.out.println("\nTest 7d: Sheep at E5, wolves nearby");
// WolfNode w9 = new WolfNode("C3");
// WolfNode w10 = new WolfNode("D4");
// WolfNode w11 = new WolfNode("F4");
// WolfNode w12 = new WolfNode("G3");
// SheepNode sheep4 = new SheepNode("E5");
// System.out.println("Path: " + sheep4.winPath(w9, w10, w11, w12));

// System.out.println("\nTest 7e: Sheep at D6, wolves blocking center");
// WolfNode w13 = new WolfNode("C5");
// WolfNode w14 = new WolfNode("D5");
// WolfNode w15 = new WolfNode("E5");
// WolfNode w16 = new WolfNode("F6");
// SheepNode sheep5 = new SheepNode("D6");
// System.out.println("Path: " + sheep5.winPath(w13, w14, w15, w16));

// System.out.println("\nTest 7f: Sheep blocked completely at row 1");
// WolfNode w17 = new WolfNode("B1");
// WolfNode w18 = new WolfNode("D1");
// WolfNode w19 = new WolfNode("F1");
// WolfNode w20 = new WolfNode("H1");
// SheepNode sheep6 = new SheepNode("C2");
// System.out.println("Path: " + sheep6.winPath(w17, w18, w19, w20));

// System.out.println("\nTest 7g: Sheep starting on wolf position");
// WolfNode w21 = new WolfNode("D4");
// WolfNode w22 = new WolfNode("E4");
// WolfNode w23 = new WolfNode("F4");
// WolfNode w24 = new WolfNode("G4");
// SheepNode sheep7 = new SheepNode("D4");
// System.out.println("Path: " + sheep7.winPath(w21, w22, w23, w24) + " (should
// be null)");

// System.out.println("\nTest 7h: Sheep at B3, complex escape");
// WolfNode w25 = new WolfNode("A2");
// WolfNode w26 = new WolfNode("C2");
// WolfNode w27 = new WolfNode("E2");
// WolfNode w28 = new WolfNode("G2");
// SheepNode sheep8 = new SheepNode("B3");
// System.out.println("Path: " + sheep8.winPath(w25, w26, w27, w28));
// System.out.println();
// }

// /** Runs only the Test 7e sheep path scenario. */
// private static void testSheepWinPath7e() {
// System.out.println("TEST: Sheep winPath() - 7e only");
// System.out.println("Test 7e: Sheep at D6, wolves blocking center");
// WolfNode w13 = new WolfNode("C5");
// WolfNode w14 = new WolfNode("D5");
// WolfNode w15 = new WolfNode("E5");
// WolfNode w16 = new WolfNode("F6");
// SheepNode sheep5 = new SheepNode("D6");
// System.out.println("Path: " + sheep5.winPath(w13, w14, w15, w16));
// }
// }
