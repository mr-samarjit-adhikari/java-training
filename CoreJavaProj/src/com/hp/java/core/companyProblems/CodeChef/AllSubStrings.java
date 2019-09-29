package com.hp.java.core.companyProblems.CodeChef;

/**
 * You are given two strings S and R. You may reorder the characters in the string R in any way;
 * let's denote the resulting string by Rr. This reordered string is valid if it contains all substrings of S, i.e.
 * for each substring s of S (including S itself), Rr contains s as a substring.
 *
 * Find the lexicographically smallest valid reordered string or determine that there is no such valid string.
 *
 * Input
 * The first line of the input contains a single integer T denoting the number of test cases. The description of T
 * test cases follows.
 * The first line of each test case contains a single string S.
 * The second line contains a single string R.
 * Output
 * For each test case, print a single line containing one string — the lexicographically smallest valid reordered string,
 * or "Impossible" if there is no such string.
 *
 * Constraints
 * 1≤T≤1,000
 * 1≤|S|,|R|≤105
 * S and R contain only lowercase English letters
 * the sum of |S| over all test cases does not exceed 106
 * the sum of |R| over all test cases does not exceed 106
 * Subtasks
 * Subtask #1 (50 points):
 *
 * 1≤|S|,|R|≤1,000
 * S and R contain only the characters 'a' and 'b'
 * the sum of |S| in all test cases does not exceed 104
 * the sum of |R| in all test cases does not exceed 104
 * Subtask #2 (50 points): original constraints
 *
 * Example Input
 * 4
 * aa
 * ababab
 * aaa
 * ramialsadaka
 * said
 * sryhieni
 * code
 * codeisfun
 * Example Output
 * aaabbb
 * aaaaadiklmrs
 * Impossible
 * codefinsu
 */
public class AllSubStrings {
}
